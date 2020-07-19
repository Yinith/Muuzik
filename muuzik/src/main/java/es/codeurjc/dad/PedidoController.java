package es.codeurjc.dad;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.client.support.BasicAuthenticationInterceptor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.client.RestTemplate;

import es.codeurjc.dad.anuncio.Anuncio;
import es.codeurjc.dad.anuncio.AnuncioRepository;
import es.codeurjc.dad.articulo.Articulo;
import es.codeurjc.dad.articulo.ArticuloRepository;
import es.codeurjc.dad.pedido.Pedido;
import es.codeurjc.dad.pedido.PedidoRepository;
import es.codeurjc.dad.usuario.Usuario;
import es.codeurjc.dad.usuario.UsuarioRepository;


@Controller
public class PedidoController {

	@Autowired
	private AnuncioRepository adRepo;
	@Autowired
	private UsuarioRepository userRepo;
	@Autowired
	private ArticuloRepository artRepo;
	@Autowired
	private PedidoRepository peRepo; 
	@Autowired
	private CacheManager cacheManager;
	
	
	@CacheEvict(cacheNames="anuncios", allEntries=true)
	@GetMapping("/hacerPedido/{id}")
	public String hacerPedido(Model model, @PathVariable long id, HttpServletRequest request) {
		
		Usuario comprador = userRepo.findByNick(request.getUserPrincipal().getName());
		Anuncio anuncio = adRepo.findById(id).get();
		
		cacheManager.getCache("anuncios").invalidate();
		
		model.addAttribute("userActual", comprador);
		model.addAttribute("username", comprador.getNick());
		
////////Comprobación por si la url se ha recargado y dicho artículo ya ha sido comprado:
		if(anuncio.isVendido()) {
			return "pedido_repetido";
		}
		else {
			Usuario vendedor = anuncio.getAnunciante();
			Pedido pedido = new Pedido(comprador, anuncio); // Creo una instancia de Pedido
			peRepo.save(pedido);
			//comprador.addPedido(pedido);
		
/////////// Este bloque de código asigna un nuevo dueño al articulo. Saca el articulo de la lista de posesiones del vendedor, y lo mete en la del comprador. El anuncio se marca como vendido
			Articulo aux = anuncio.getArticulo();
			Articulo newArt = new Articulo(aux.getNombre(), aux.getCategoria(), aux.isPublico(), aux.getAnoFabricacion()); //Hago una copia del articulo, ID distinto
			artRepo.save(newArt);
			vendedor.borrarArticulo(aux);   	// Le quito el objeto de su lista al vendedor, pero no lo borro de la base de datos para que lo pueda ver en su lista de articulos
			comprador.addArticulo(newArt);		// Añado el articulo a los objetos del comprador
			anuncio.setVendido();				// El anuncio se marca como vendido (luego aparecerá en una lista de anuncios vendidos en el perfil del vendedor).  
	////////////////		
			
			userRepo.save(vendedor);
			userRepo.save(comprador);
			
			//Comunicación por REST
			RestTemplate rest = new RestTemplate();
			String pedido_url = "http://lbsi/email/pedido";
			HttpEntity<Pedido> pedidoBody= new HttpEntity<>(pedido);
			
//			rest.getInterceptors().add(new BasicAuthenticationInterceptor("user", "pass"));
//			restTemplate.postForObject('','',''');
			rest.exchange(pedido_url, HttpMethod.POST,pedidoBody,Void.class);
			
		    
		    
	    
		    return "pedido_realizado";	
		}
	}

}
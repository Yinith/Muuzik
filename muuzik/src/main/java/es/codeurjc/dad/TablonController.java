package es.codeurjc.dad;

import java.util.Optional;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import es.codeurjc.dad.anuncio.Anuncio;
import es.codeurjc.dad.anuncio.AnuncioRepository;
import es.codeurjc.dad.articulo.Articulo;
import es.codeurjc.dad.usuario.Usuario;
import es.codeurjc.dad.usuario.UsuarioRepository;


@Controller
public class TablonController {
	
	//Si se abre la URL http://127.0.0.1:8080/h2-console y se configura
	//la URL JDBC con el valor jdbc:h2:mem:testdb se puede acceder a la 
	//base de datos de la aplicación 
	//Green: Voy a utilizar esto para crear un pedido de la misma forma que hacemos el anuncio a usuario.

	@Autowired
	private AnuncioRepository adRepo;
	@Autowired
	private UsuarioRepository usRepo;
	

	@GetMapping("/tablon")
	public String tablon(Model model, Pageable page, HttpServletRequest request) {
		Usuario usuarioActual = usRepo.findByNick(request.getUserPrincipal().getName());
		model.addAttribute("username", usuarioActual.getNick());
		model.addAttribute("anuncios", adRepo.findAll(page));

		return "tablon";
	}
	
	@GetMapping("/crearAnuncio")
	public String crearAnuncio() {
		return "nuevoAnuncio_form";
	}
	

	@PostMapping("/anuncio/nuevo")
	public String nuevoAnuncio(Model model, @RequestParam String nombre, @RequestParam int precio, @RequestParam(required=false) String categoria, @RequestParam(defaultValue="0") int anoFabricacion, @RequestParam String comentario) {
		
		Anuncio anuncio = new Anuncio(new Articulo(nombre, categoria, anoFabricacion), comentario, precio);
		Usuario user = usRepo.findByNick("Admin");
		user.addAnuncio(anuncio);
		adRepo.save(anuncio);
		usRepo.save(user);

		return "anuncio_guardado";

	}

	@GetMapping("/anuncio/{id}")
	public String verAnuncio(Model model, @PathVariable long id, HttpServletRequest request) {
		
		Optional<Anuncio> op = adRepo.findById(id);
		Anuncio anuncio;
		
		if(op.isPresent()) {
			anuncio = op.get();
			if(anuncio.getArticulo().getAnoFabricacion() > 0) {
				model.addAttribute("hayAno", true);
			}
			if(anuncio.getArticulo().getCategoria() != "") {
				model.addAttribute("hayCat", true);
			}
			model.addAttribute("admin", request.isUserInRole("ADMIN"));
			model.addAttribute("anuncio", anuncio);
		}
		return "ver_anuncio";
	}
	
	@GetMapping("/borrar_anuncio/{id}")
	public String borrarAnuncio(Model model, @PathVariable long id, Pageable page, HttpServletRequest request) {
		
		Usuario usuarioActual = usRepo.findByNick(request.getUserPrincipal().getName());
		model.addAttribute("username", usuarioActual.getNick());
		
		Optional<Anuncio> op = adRepo.findById(id);
		if(op.isPresent()) {
			Anuncio ad = op.get();
			Usuario propietario = ad.getUsuario();
			propietario.borrarArticulo(ad.getArticulo());
			propietario.borrarAnuncio(ad);
			adRepo.deleteById(id);
		}
		model.addAttribute("anuncios", adRepo.findAll(page));
		return "tablon";
	}

}
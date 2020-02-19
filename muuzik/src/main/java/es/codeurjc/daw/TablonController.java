package es.codeurjc.daw;

import java.util.Optional;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class TablonController {
	
	//Si se abre la URL http://127.0.0.1:8080/h2-console y se configura
	//la URL JDBC con el valor jdbc:h2:mem:testdb se puede acceder a la 
	//base de datos de la aplicación 
	//Green: Voy a utilizar esto para crear un pedido de la misma forma que hacemos el anuncio a usuario.

	@Autowired
	private AnunciosRepository adRepo;
	@Autowired
	private UsuariosRepository usRepo;
	@Autowired
	private ArticulosRepository artRepo;
	/*
	@Autowired
	private PedidoRepository pRepo;
	*/

	@PostConstruct
	public void init() {

		Articulo a1 = new Articulo("Ampli de bajo", "Amplificadores", 2005);
		Anuncio v1 = new Anuncio(a1, "Es de válvulas.", 50);
		Usuario u1 = new Usuario ("Green", "quenoquieroponeruna", "Tienda de música");
		usRepo.save(u1);
		u1.addAnuncio(v1);
		adRepo.save(v1);
		usRepo.save(u1);
		
		Anuncio v2 = new Anuncio(new Articulo("Guitarra Ibanez", "Guitarras", 1997), "No está bien conservada, la tienes que arreglar.", 800);
		Usuario u2 = new Usuario ("Cthulhu", "cthulhu", "Aprendiendo a tocar la guitarra");
		usRepo.save(u2);
		u2.addAnuncio(v2);
		adRepo.save(v2);
		usRepo.save(u2);
		/*
		pRepo.save(new Pedido(u1,v1));
		pRepo.save(new Pedido(u2,v2));
		*/
		
		// Añadimos muchos anuncios
		for(int i = 1; i<=10; i++){
			//adRepo.save(new AnuncioVenta("User "+i, "Anuncio "+i, "Contenido "+i, new Articulo("asd", "asd", true, 0), i*10 ));
			Anuncio vi = new Anuncio(new Articulo("Instrumento "+i), "Un instrumento cualquiera", i*10);
			Usuario ui = new Usuario ("Usuario"+i, "password"+i, "No soy un robot.");
			usRepo.save(ui);
			ui.addAnuncio(vi);
			adRepo.save(vi);
			usRepo.save(ui);
		}
		
	}

	@GetMapping("/tablon")
	public String tablon(Model model, Pageable page) {

		model.addAttribute("anuncios", adRepo.findAll(page));

		return "tablon";
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
	public String verAnuncio(Model model, @PathVariable long id) {
		
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
			model.addAttribute("anuncio", anuncio);
		}
		return "ver_anuncio";
	}
	
	@GetMapping("/borrar_anuncio/{id}")
	public String borrarAnuncio(Model model, @PathVariable long id, Pageable page) {
		
		Optional<Anuncio> op = adRepo.findById(id);
		if(op.isPresent()) {
			Anuncio ad = op.get();
			Usuario user = ad.getUsuario();
			user.borrarArticulo(ad.getArticulo());
			user.borrarAnuncio(ad);
			adRepo.deleteById(id);
		}
		model.addAttribute("anuncios", adRepo.findAll(page));
		return "tablon";
	}

}
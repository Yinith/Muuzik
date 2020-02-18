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

@Controller
public class TablonController {
	
	//Si se abre la URL http://127.0.0.1:8080/h2-console y se configura
	//la URL JDBC con el valor jdbc:h2:mem:testdb se puede acceder a la 
	//base de datos de la aplicación 

	@Autowired
	private AnunciosRepository adRepo;
	@Autowired
	private UsuariosRepository usRepo;


	@PostConstruct
	public void init() {
		
		// Añadimos muchos anuncios
		for(int i = 1; i<=8; i++){
			//adRepo.save(new AnuncioVenta("User "+i, "Anuncio "+i, "Contenido "+i, new Articulo("asd", "asd", true, 0), i*10 ));
			adRepo.save(new Anuncio("User "+i, "Anuncio "+i, "Contenido "+i));
		}
		
		Anuncio v1 = new Anuncio("Green", "Figuritas Warhammer", "Vendo set de figuras de enanos 50$");
		Usuario u1 = new Usuario ("Green", "quenoquieroponeruna", "Hi");
		usRepo.save(u1);
		v1.setUsuario(u1);
		u1.addAnuncio(v1);
		adRepo.save(v1);
		usRepo.save(u1);
	}

	@GetMapping("/tablon")
	public String tablon(Model model, Pageable page) {

		model.addAttribute("anuncios", adRepo.findAll(page));

		return "tablon";
	}

	@PostMapping("/anuncio/nuevo")
	public String nuevoAnuncio(Model model, Anuncio anuncio) {

		adRepo.save(anuncio);

		return "anuncio_guardado";

	}

	@GetMapping("/anuncio/{id}")
	public String verAnuncio(Model model, @PathVariable long id) {
		
		Optional<Anuncio> anuncio = adRepo.findById(id);

		if(anuncio.isPresent()) {
			model.addAttribute("anuncio", anuncio.get());
		}

		return "ver_anuncio";
	}

}
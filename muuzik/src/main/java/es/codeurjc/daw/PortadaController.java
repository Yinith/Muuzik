package es.codeurjc.daw;

import java.util.Optional;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class PortadaController {
	
	
	@Autowired
	private UsuariosRepository userRepo;
	
	private Usuario userActual; //Si un usuario ha iniciado sesión
	
	@PostConstruct
	//Son solo ejemplos
	public void init () {
		userRepo.save(new Usuario("Chema", "essolodeprueba", "Vendo Oper Corsa"));
		userRepo.save(new Usuario("Cassi", "essolodeprueba2", "Hola!! Soy Cassi, encantada de conocerte =)"));
	}
	
	//Supongo que el getMapping de usuarios no nos interesa a si que lo he omitido, 
	//aunque sopongo que habrá que hacer busquedas para autenticarlos.
	
	@GetMapping("/")
	public String tablaUsuarios(Model model) {

		model.addAttribute("usuario", userRepo.findAll());

		return "index";
	}
	
	@PostMapping("/usuario/nuevo")
	public String nuevoUsuario(Model model, Usuario usuario) {

		userRepo.save(usuario);

		return "usuario_guardado";

	}
	
	@PostMapping("/loggedIn")
	public String nuevoAnuncio(Model model, @RequestParam String nick, @RequestParam String contrasena) {
		
		Optional<Usuario> user = userRepo.findByNickAndContrasena(nick, contrasena);
		if(user.isPresent()) {
			userActual = user.get();
			model.addAttribute("nick", userActual.getNick());
			model.addAttribute("loggedIn", true);
			return "index";
		}
		else {
			return "usuarioNoExiste";
		}

	}

}

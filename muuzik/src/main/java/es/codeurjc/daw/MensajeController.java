package es.codeurjc.daw;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class MensajeController {
	
	@Autowired
	private MensajesRepository msgRepo;
	@Autowired
	private UsuariosRepository userRepo;
	@Autowired
	private ChatRepository chtRepo;




	public void init () {

		Usuario u1 = new Usuario ("uPrueba1", "pruebachat1", "pruebachat1");
		userRepo.save(u1);
		Usuario u2 = new Usuario ("uPrueba2", "pruebachat2", "pruebachat2");
		userRepo.save(u2);
		Chat c1 = new Chat(u1,u2);
		chtRepo.save(c1);

		Mensaje m1 = new Mensaje ("Prueba de que hay mensajes dentro del chat");
		Mensaje m2 = new Mensaje ("Prueba de que hay una contestación");
		c1.addMensaje(m1);
		c1.addMensaje(m2);
		chtRepo.save(c1);

	}

	@GetMapping("/chat")
	public String chatUsuarios(Model model) {

		model.addAttribute("chat", chtRepo.findAll());

		return "chat";
	}
	
	@GetMapping("/chat/mensajes")
	public String tablaMensajes(Model model) {

		model.addAttribute("mensaje", msgRepo.findAll());

		return "index";
	}

	@GetMapping("/chat/{id}")
	public String verChat(Model model, @PathVariable long id) {
		
		Optional<Chat> chat = chtRepo.findById(id);

		if(chat.isPresent()) {
			model.addAttribute("chat", chat.get());
		}

		return "ver_chat";
	}
	
	@GetMapping("/mensaje/{id}")
	public String verMensaje(Model model, @PathVariable long id) {
		
		Optional<Mensaje> mesnaje = msgRepo.findById(id);

		if(mesnaje.isPresent()) {
			model.addAttribute("chat", mesnaje.get());
		}

		return "ver_mensaje";
	}

}

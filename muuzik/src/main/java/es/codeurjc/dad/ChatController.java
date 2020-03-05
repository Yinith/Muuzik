package es.codeurjc.dad;

import java.util.ArrayList;
import java.util.Optional;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import antlr.collections.List;
import es.codeurjc.dad.chat.Chat;
import es.codeurjc.dad.chat.ChatRepository;
import es.codeurjc.dad.chat.Mensaje;
import es.codeurjc.dad.chat.MensajeRepository;
import es.codeurjc.dad.usuario.UsuarioRepository;

@Controller
public class ChatController {
	
	@Autowired
	private MensajeRepository msgRepo;
	@Autowired
	private UsuarioRepository userRepo;
	@Autowired
	private ChatRepository chtRepo;

	@GetMapping("/bandeja_entrada")
	public String chatUsuarios(Model model) {

		model.addAttribute("chats", chtRepo.findAll());

		return "bandeja_entrada";
	}
	
	@GetMapping("/chat/mensajes")
	public String tablaMensajes(Model model) {

		model.addAttribute("mensaje", msgRepo.findAll());

		return "index";
	}

	@GetMapping("/chat/{toId}")
	public String verChat(Model model, @PathVariable long toId) {
		
		//Esto va a haber que cambiarlo para buscar el remitente cuando haya inicios de sesión
		java.util.List<Chat> listachats = chtRepo.findByDestinatario_Id(toId);
		model.addAttribute("hayChat", false);
		
		if(!listachats.isEmpty()) {
			model.addAttribute("chat", listachats.get(0));
			model.addAttribute("hayChat", true);
		}

		return "ver_chat";
	}
	
	@PostMapping("mensaje/nuevo/{id}")
	public String nuevoMensaje(Model model, @PathVariable long id, @RequestParam(defaultValue="") String cuerpo) {
		
		Optional<Chat> op = chtRepo.findById(id);
		
		if(op.isPresent()) {
			
			Chat chat = op.get();
			chat.insertarMensaje(cuerpo);
			model.addAttribute("chat", chat);
			
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

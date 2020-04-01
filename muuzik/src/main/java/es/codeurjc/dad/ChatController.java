package es.codeurjc.dad;

import java.util.ArrayList;
import java.util.Optional;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import antlr.collections.List;
import es.codeurjc.dad.anuncio.Anuncio;
import es.codeurjc.dad.articulo.Articulo;
import es.codeurjc.dad.chat.Chat;
import es.codeurjc.dad.chat.ChatRepository;
import es.codeurjc.dad.chat.Mensaje;
import es.codeurjc.dad.chat.MensajeRepository;
import es.codeurjc.dad.usuario.Usuario;
import es.codeurjc.dad.usuario.UsuarioRepository;

@Controller
public class ChatController {
	
	@Autowired
	private MensajeRepository msgRepo;
	@Autowired
	private UsuarioRepository usRepo;

	@GetMapping("/bandeja_entrada")
	public String misMensajes(Model model, HttpServletRequest request) {
		
		Usuario usuarioActual = usRepo.findByNick(request.getUserPrincipal().getName());
		model.addAttribute("mensaje", msgRepo.findByDestinatario(usuarioActual));

		return "bandeja_entrada";
	}
	
	
	
	@GetMapping("/mensaje/{id}")
	public String verMensaje(Model model, @PathVariable long id) {
		
		Optional<Mensaje> mesnaje = msgRepo.findById(id);

		if(mesnaje.isPresent()) {
			model.addAttribute("chat", mesnaje.get());
		}

		return "ver_mensaje";
	}
	
	@PostMapping("/mensaje/nuevo")
	public String nuevoMensaje(Model model, @RequestParam String destinatario, @RequestParam String asunto, String cuerpo,  HttpServletRequest request) {
		Usuario usuarioActual = usRepo.findByNick(request.getUserPrincipal().getName());
		Usuario user = usRepo.findByNick("destinatario");
		Mensaje mensaje = new Mensaje(user, usuarioActual, asunto, cuerpo);
		user.addMensaje(mensaje);
		usRepo.save(user);
		return "mensaje_enviado";
	}

}
package es.codeurjc.dad;

import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

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
		model.addAttribute("mensaje", msgRepo.findAllByDestinatario(usuarioActual));

		return "mensajes";
	}
	
	@GetMapping("/mensaje/{destinatarioid}")
	public String escribirMensaje(Model model, @PathVariable long destinatarioid) {
		
		Usuario destinatario = usRepo.findById(destinatarioid).get();
		model.addAttribute("destinatario", destinatario);
		return "ver_mensaje";
	}
	
	@PostMapping("/mensaje/nuevo/")
	public String nuevoMensaje(Model model, @RequestParam String dest, @RequestParam String asunto, @RequestParam String cuerpo,  HttpServletRequest request) {
		Usuario remitente = usRepo.findByNick(request.getUserPrincipal().getName());
		Usuario destinatario = usRepo.findByNick(dest);
		Mensaje mensaje = new Mensaje(remitente, destinatario, asunto, cuerpo);
		msgRepo.save(mensaje);
		destinatario.addMensaje(mensaje);
		usRepo.save(destinatario);
		return "mensaje_enviado";
	}

}
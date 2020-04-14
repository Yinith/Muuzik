package es.codeurjc.dad;

import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;

import es.codeurjc.dad.chat.Mensaje;
import es.codeurjc.dad.chat.MensajeRepository;
import es.codeurjc.dad.pedido.Pedido;
import es.codeurjc.dad.usuario.Usuario;
import es.codeurjc.dad.usuario.UsuarioRepository;

@Controller
public class ChatController {
	
	@Autowired
	private MensajeRepository msgRepo;
	@Autowired
	private UsuarioRepository userRepo;
	
	private RestTemplate restTemplate = new RestTemplate();
	
	private final String pedidos_url = "http://localhost:8443/mensaje/nuevo";

	@GetMapping("/bandeja_entrada")
	public String misMensajes(Model model, HttpServletRequest request) {
		
		Usuario usuarioActual = userRepo.findByNick(request.getUserPrincipal().getName());
		
		model.addAttribute("mensaje", msgRepo.findAllByDestinatario(usuarioActual));
		model.addAttribute("username", usuarioActual.getNick());
		return "mensajes";
	}
	
	@GetMapping("/mensaje/{destinatarioid}")
	public String escribirMensaje(Model model, @PathVariable long destinatarioid,  HttpServletRequest request) {
		Usuario destinatario = userRepo.findById(destinatarioid).get();
		model.addAttribute("destinatario", destinatario);
		model.addAttribute("username", request.getUserPrincipal().getName());
		return "enviar_mensaje";
	}
	
	@PostMapping("/mensaje/{destinatarioid}/nuevo")
	public String nuevoMensaje(Model model, @PathVariable long destinatarioid, @RequestParam String asunto, @RequestParam String cuerpo,  HttpServletRequest request) {
		Usuario remitente = userRepo.findByNick(request.getUserPrincipal().getName());
		Usuario dest = userRepo.findById(destinatarioid).get();
		Mensaje mensaje = new Mensaje(remitente, dest, asunto, cuerpo);
		msgRepo.save(mensaje);
		dest.addMensaje(mensaje);
		userRepo.save(dest);
		
		String url = pedidos_url;
		HttpEntity<Mensaje> enviomensaje = new HttpEntity<>(mensaje);
	    restTemplate.exchange(url, HttpMethod.POST, enviomensaje, Void.class);
 
		model.addAttribute("username", request.getUserPrincipal().getName());
		return "mensaje_enviado";
	}

}
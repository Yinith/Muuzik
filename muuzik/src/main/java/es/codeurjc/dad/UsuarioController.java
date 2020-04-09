package es.codeurjc.dad;

import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import es.codeurjc.dad.anuncio.AnuncioRepository;
import es.codeurjc.dad.articulo.Articulo;
import es.codeurjc.dad.articulo.ArticuloRepository;
import es.codeurjc.dad.usuario.Usuario;
import es.codeurjc.dad.usuario.UsuarioRepository;


@Controller
public class UsuarioController {
	
	
	@Autowired
	private UsuarioRepository userRepo;
	@Autowired
	private AnuncioRepository adRepo;	
	@Autowired
	private ArticuloRepository artRepo;
	
	
	@GetMapping("/")
	public String inicio(Model model, HttpServletRequest request) {
		Boolean loggedIn = false;
		String username = "";
		if (request.getUserPrincipal() != null) {
			loggedIn = true;
			username = userRepo.findByNick(request.getUserPrincipal().getName()).getNick();
		}
		model.addAttribute("loggedIn", loggedIn);
		model.addAttribute("username", username);
		return "index";
	}
	
	@GetMapping("/login")
	public String login() {
		return "inicio_sesion";
	}
	
	@GetMapping("/loginerror")
	public String loginerror() {
		return "usuarioNoExiste";
	}
	
	@GetMapping("/register")
	public String registrarUsuario() {
		return "registro_form";
	}
	
	@PostMapping("/registerOK")
	public String nuevoUsuario(Model model, @RequestParam String nick, @RequestParam String contrasena, @RequestParam String biografia, HttpServletRequest request) {
		userRepo.save(new Usuario(nick, contrasena, biografia));
		
		Boolean loggedIn = false;
		String username = "";
		if (request.getUserPrincipal() != null) {
			loggedIn = true;
			username = userRepo.findByNick(request.getUserPrincipal().getName()).getNick();
		}
		model.addAttribute("loggedIn", loggedIn);
		model.addAttribute("username", username);

		return "usuario_guardado";
	}
	
	@GetMapping("/usuario/{userId}")
	public String verPerfil(Model model, @PathVariable Long userId, HttpServletRequest request) {
	
		Optional<Usuario> op = userRepo.findById(userId);
		if(op.isPresent()) {
			model.addAttribute("usuario", op.get());
			model.addAttribute("admin", request.isUserInRole("ADMIN"));
		}
		
		model.addAttribute("username", request.getUserPrincipal().getName());
		return "perfil_usuario";
	}
	
	@GetMapping("/zona-usuario")
	public String zonaUsuario(Model model, HttpServletRequest request) {
		Usuario userActual = userRepo.findByNick(request.getUserPrincipal().getName());
		model.addAttribute("usuario", userActual);
		return "zona_usuario";
	}
	
	@GetMapping("/usuario/{userId}/edit")
	public String usuarioEdit(Model model, @PathVariable Long userId, HttpServletRequest request) {
		Optional<Usuario> op = userRepo.findById(userId);
		if(op.isPresent()) {
			model.addAttribute("usuario", op.get());
		}
		
		model.addAttribute("username", request.getUserPrincipal().getName());
		return "perfil_usuario_edit";
	}
	
	@PostMapping("/usuario/{userId}/guardar")
	public String usuarioEditGuardar(Model model, @PathVariable Long userId, @RequestParam Optional<String> contrasena, @RequestParam Optional<String> bio, HttpServletRequest request) {
		Optional<Usuario> op = userRepo.findById(userId);
		if(op.isPresent()) {
			Usuario usuario = op.get();
			if(contrasena.isPresent()) {usuario.setContrasena(contrasena.get());}
			if(bio.isPresent()) {usuario.setBio(bio.get());}
			userRepo.save(usuario);
		}
		
		Boolean loggedIn = false;
		String username = "";
		if (request.getUserPrincipal() != null) {
			loggedIn = true;
			username = userRepo.findByNick(request.getUserPrincipal().getName()).getNick();
		}
		model.addAttribute("loggedIn", loggedIn);
		model.addAttribute("username", username);

		return "usuario_guardado";
	}
	
	
	@Transactional		//Hace falta la anotación transactional 
	@GetMapping("/borrar_usuario/{userId}")
	public String borrarUsuario(Model model, @PathVariable long userId, Pageable page, HttpServletRequest request) {
		
		Optional<Usuario> op = userRepo.findById(userId);
		if(op.isPresent()) {
			Usuario user = op.get();
			
			List<Articulo> articulos = user.getArticulos(); //Se saca la referencia a todos los articulos que aun son de este usuario
			
			user.borrarTodosAnuncios();				// Borramos la referencia que tiene el usuario de sus anuncios y artículos
			user.borrarTodosArticulos();
			
			artRepo.deleteInBatch(articulos);		// Borramos todas las instancias de sus articulos del repositorio
			adRepo.deleteByAnunciante_Id(userId);	// Borramos todas las instancias de sus anuncios
			userRepo.deleteById(userId);			// Borramos el usuario de su repo
		}
		model.addAttribute("username", request.getUserPrincipal().getName());
		return "usuario_borrado";
	}
	

}

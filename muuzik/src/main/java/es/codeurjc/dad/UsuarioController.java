package es.codeurjc.dad;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.TimeZone;

import javax.annotation.PostConstruct;

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
import es.codeurjc.dad.usuario.Usuario;
import es.codeurjc.dad.usuario.UsuarioRepository;


@Controller
public class UsuarioController {
	
	
	@Autowired
	private UsuarioRepository userRepo;
	@Autowired
	private AnuncioRepository adRepo;
	
	private Usuario userActual; //Si un usuario ha iniciado sesión
	
	
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
	
	@GetMapping("/usuario/{userId}")
	public String nuevoUsuario(Model model, @PathVariable Long userId) {
	
		Optional<Usuario> op = userRepo.findById(userId);
		if(op.isPresent()) {
			model.addAttribute("usuario", op.get());
		}

		return "perfil_usuario";
	}
	
	@GetMapping("/usuario/{userId}/edit")
	public String usuarioEdit(Model model, @PathVariable Long userId) {
		Optional<Usuario> op = userRepo.findById(userId);
		if(op.isPresent()) {
			model.addAttribute("usuario", op.get());
		}

		return "perfil_usuario_edit";
	}
	
	@PostMapping("/usuario/{userId}/guardar")
	public String usuarioEditGuardar(Model model, @PathVariable Long userId, @RequestParam Optional<String> contrasena, @RequestParam Optional<String> info_perfil) {
		Optional<Usuario> op = userRepo.findById(userId);
		if(op.isPresent()) {
			Usuario usuario = op.get();
			if(contrasena.isPresent()) {usuario.setContrasena(contrasena.get());}
			if(info_perfil.isPresent()) {usuario.setPerfil(info_perfil.get());}
			userRepo.save(usuario);
		}

		return "usuario_guardado";
	}
	
	@Transactional
	@GetMapping("/borrar_usuario/{userId}")
	public String borrarUsuario(Model model, @PathVariable long userId, Pageable page) {
		
		Optional<Usuario> op = userRepo.findById(userId);
		if(op.isPresent()) {
			Usuario user = op.get();
			//List<Articulo> articulos = user.getArticulos();
			//artRepo.deleteInBatch(articulos);
			user.borrarTodosAnuncios();
			user.borrarTodosArticulos();
			adRepo.deleteByUser_Id(userId);
			userRepo.deleteById(userId);
		}
		return "usuario_borrado";
	}
}

package es.codeurjc.daw;

import java.util.List;
import java.util.Optional;

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


@Controller
public class PortadaController {
	
	
	@Autowired
	private UsuariosRepository userRepo;
	@Autowired
	private AnunciosRepository adRepo;
	@Autowired
	private ArticulosRepository artRepo;
	
	private Usuario userActual; //Si un usuario ha iniciado sesión
	
	@PostConstruct
	//Son solo ejemplos
	public void init () {
		userRepo.save(new Usuario("Chema", "essolodeprueba", "Clarinetista en la orquesta RTVE"));
		userRepo.save(new Usuario("Cassi", "essolodeprueba2", "Luthier de zanfonas"));
		userRepo.save(new Usuario("Admin", "admin", "Administrador de la página web"));
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

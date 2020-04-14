package es.codeurjc.dad;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import es.codeurjc.dad.anuncio.Anuncio;
import es.codeurjc.dad.anuncio.AnuncioRepository;
import es.codeurjc.dad.articulo.Articulo;
import es.codeurjc.dad.articulo.ArticuloRepository;
import es.codeurjc.dad.usuario.Usuario;
import es.codeurjc.dad.usuario.UsuarioRepository;

@Component
public class DatabaseInit {

	@Autowired
	private UsuarioRepository userRepo;
	@Autowired
	private AnuncioRepository adRepo;
	@Autowired
	private ArticuloRepository artRepo;
	
	@PostConstruct
	public void init () {
/*
		////////// INICIALIZAR USUARIOS
		userRepo.save(new Usuario("chema@gmail.com", "Chema", "essolodeprueba", "Clarinetista en la orquesta RTVE"));
		userRepo.save(new Usuario("cassi@gmail.com", "Cassi", "pass", "Luthier de zanfonas", "ROLE_USER"));
		userRepo.save(new Usuario("admin@gmail.com", "Admin", "admin", "Administrador de la página web", "ROLE_USER", "ROLE_ADMIN"));
		Usuario u1 = new Usuario ("sergiomanchitero@gmail.com","Green", "quenoquieroponeruna", "Tienda de música");
		Usuario u2 = new Usuario ("clarathulhu@gmail.com","Cthulhu", "cthulhu", "Aprendiendo a tocar la guitarra");
		
		////////// INICIALIZAR ARTICULOS, ANUNCIOS Y PEDIDOS
		Articulo a1 = new Articulo("Ampli de bajo", "Amplificadores", 2005);
		artRepo.save(a1);
		Anuncio v1 = new Anuncio(a1, "Es de válvulas.", 50);
		u1.addAnuncio(v1);
		userRepo.save(u1);
		adRepo.save(v1);
		
		Articulo a2 = new Articulo("Guitarra Ibanez", "Guitarras", 1997);
		artRepo.save(a2);
		Anuncio v2 = new Anuncio(a2, "No está bien conservada, la tienes que arreglar.", 800);
		u2.addAnuncio(v2);
		userRepo.save(u2);
		adRepo.save(v2);
		
		
		// MÁS ANUNCIOS
	
		
		
		
		
		
/*
		// Añadimos anuncios en bucle
		for(int i = 1; i<=1; i++){
			//adRepo.save(new AnuncioVenta("User "+i, "Anuncio "+i, "Contenido "+i, new Articulo("asd", "asd", true, 0), i*10 ));
			Usuario ui = new Usuario ("robotmail"+i+"@gmail.com","Usuario"+i, "password"+i, "No soy un robot.");
			Articulo ai = new Articulo("Instrumento "+i);
			artRepo.save(ai);
			Anuncio vi = new Anuncio(ai, "Un instrumento cualquiera", i*10);	
			ui.addAnuncio(vi);
			userRepo.save(ui);
			adRepo.save(vi);
		}	
		//pRepo.save(new Pedido(u1,v1));
		//pRepo.save(new Pedido(u2,v2));
		

		

		////////// INICIALIZAR MENSAJES Y CHATS
		
		/* 		///// USUARIOS PARA PRUEBA CHATS
		Usuario u1 = new Usuario ("uPrueba1", "pruebachat1", "pruebachat1");
		userRepo.save(u1);
		Usuario u2 = new Usuario ("uPrueba2", "pruebachat2", "pruebachat2");
		userRepo.save(u2);
		
		Chat c1 = new Chat(u1,u2);
		chtRepo.save(c1);
		*/
		/*
		 * Usuario u1 = new Usuario ("uPrueba1", "pruebachat1", "pruebachat1");
		 * userRepo.save(u1); Usuario u2 = new Usuario ("uPrueba2", "pruebachat2",
		 * "pruebachat2"); userRepo.save(u2); Chat c1 = new Chat(u1,u2); //u1 es
		 * remitente, u2 es destinatario chtRepo.save(c1);
		 * 
		 * Mensaje m1 = new Mensaje ("Prueba de que hay mensajes dentro del chat");
		 * Mensaje m2 = new Mensaje ("Prueba de que hay una contestación");
		 * c1.addMensaje(m1); c1.addMensaje(m2); chtRepo.save(c1);
		 */
		/*
		 * Date now = new Date (); SimpleDateFormat fechaActual = new
		 * SimpleDateFormat("yyyy-MM-dd HH:mm:ssZ");
		 * fechaActual.setTimeZone(TimeZone.getTimeZone("Europe/Madrid"));
		 */
		/*
		 * for(int i = 0; i<100; i++){ //msgRepo.save(new Mensaje("Cuerpo" +i,
		 * fechaActual)); }
		 */
		
	}

}

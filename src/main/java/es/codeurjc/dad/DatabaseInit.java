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
import es.codeurjc.dad.chat.Mensaje;

@Component
public class DatabaseInit {

	@Autowired
	private UsuarioRepository userRepo;
	@Autowired
	private AnuncioRepository adRepo;
	
	@PostConstruct
	public void init () {

		////////// INICIALIZAR USUARIOS
		userRepo.save(new Usuario("Chema", "essolodeprueba", "Clarinetista en la orquesta RTVE"));
		userRepo.save(new Usuario("Cassi", "pass", "Luthier de zanfonas", "ROLE_USER"));
		userRepo.save(new Usuario("Admin", "admin", "Administrador de la página web", "ROLE_USER", "ROLE_ADMIN"));
		Usuario u1 = new Usuario ("Green", "quenoquieroponeruna", "Tienda de música");
		userRepo.save(u1);
		Usuario u2 = new Usuario ("Cthulhu", "cthulhu", "Aprendiendo a tocar la guitarra");
		userRepo.save(u2);
		
		
		
		
		////////// INICIALIZAR ARTICULOS, ANUNCIOS Y PEDIDOS
		Anuncio v1 = new Anuncio(new Articulo("Ampli de bajo", "Amplificadores", 2005), "Es de válvulas.", 50);
		u1.addAnuncio(v1);
		adRepo.save(v1);
		userRepo.save(u1);
		
		Anuncio v2 = new Anuncio(new Articulo("Guitarra Ibanez", "Guitarras", 1997), "No está bien conservada, la tienes que arreglar.", 800);
		u2.addAnuncio(v2);
		adRepo.save(v2);
		userRepo.save(u2);
		
		// Añadimos anuncios en bucle
		for(int i = 1; i<=10; i++){
			//adRepo.save(new AnuncioVenta("User "+i, "Anuncio "+i, "Contenido "+i, new Articulo("asd", "asd", true, 0), i*10 ));
			Anuncio vi = new Anuncio(new Articulo("Instrumento "+i), "Un instrumento cualquiera", i*10);
			Usuario ui = new Usuario ("Usuario"+i, "password"+i, "No soy un robot.");
			userRepo.save(ui);
			ui.addAnuncio(vi);
			adRepo.save(vi);
			userRepo.save(ui);
		}	
		
		Mensaje msg = new Mensaje(u2, u1, "mensaje de prueba", "pues eso que esto es un mensaje de prueba");
		u1.addMensaje(msg);
		Mensaje msg1 = new Mensaje(u1, u1, "mensaje de prueba2", "me puedo escribir a mi mismo?");
		u1.addMensaje(msg1);
		Mensaje msg2 = new Mensaje(u1, u1, "mensaje de prueba3", "Green, te escribo porque esto es otra prueba más");
		u1.addMensaje(msg2);
		
		
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

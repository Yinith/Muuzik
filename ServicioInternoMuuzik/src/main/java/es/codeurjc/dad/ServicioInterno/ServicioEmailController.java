package es.codeurjc.dad.ServicioInterno;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import es.codeurjc.dad.ServicioInterno.Anuncio;
import es.codeurjc.dad.ServicioInterno.Usuario;


@RestController
public class ServicioEmailController {
	
	@Autowired
	private UsuarioRepository userRepo;
	@Autowired
	private AnuncioRepository adRepo; 
	
	private JavaMailSenderImpl ServicioEmail = new JavaMailSenderImpl();
	
	private ServicioEmail email; 
	
	public ServicioEmailController(ServicioEmail emai) {
	        this.email = email;
	        this.propiedadesEmail();
	}

	public void propiedadesEmail() {
	        
		ServicioEmail.setHost(email.getHost());
		ServicioEmail.setPort(25);
		ServicioEmail.setUsername(email.getUsername());
		ServicioEmail.setPassword(email.getPassword());
	}
	
	//Mirar si esto es get o post
	@GetMapping("/hacerPedido/{id}")
	public void pedidoEmail(@PathVariable long id)
	{
		
		Anuncio anuncio = adRepo.findById(id).get();
		Usuario vendedor = anuncio.getAnunciante();;
				
		SimpleMailMessage envemail = new SimpleMailMessage();
		envemail.setFrom("Muuzik");
		//envemail.setTo(vendedor.getEmail());
		envemail.setSubject("Te han realizado un pedido");
		envemail.setText("Se ha realizado un pedido sobre tu anuncio con id: " + anuncio.getId());
		ServicioEmail.send(envemail);
		
	}

}

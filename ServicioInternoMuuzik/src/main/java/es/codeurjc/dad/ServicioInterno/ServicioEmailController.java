package es.codeurjc.dad.ServicioInterno;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import es.codeurjc.dad.ServicioInterno.Anuncio;
import es.codeurjc.dad.ServicioInterno.Usuario;


@RestController
public class ServicioEmailController {
	

	@Autowired
	private PedidoRepository peRepo;
	@Autowired
	private UsuarioRepository userRepo;
	
	private JavaMailSenderImpl servicioEmail = new JavaMailSenderImpl();
	
	private ServicioEmail email; 
	
	public ServicioEmailController(ServicioEmail email) {
	        this.email = email;
	        this.propiedadesEmail();
	}

	public void propiedadesEmail() {
	        
		servicioEmail.setHost(email.getHost());
		servicioEmail.setPort(2525);
		//servicioEmail.setPort(2525);
		servicioEmail.setUsername(email.getUsername());
		servicioEmail.setPassword(email.getPassword());
	}
	
	
	@PostMapping("/email/pedido")
	public void emailPedido(@RequestBody Pedido pedido)
	{
		Pedido ped = peRepo.findById(pedido.getId()).get();
		
		Anuncio anuncio = ped.getAnuncio();
		Usuario vendedor = anuncio.getAnunciante();
		String comprador = ped.getComprador().getNick();
		
		SimpleMailMessage envemail = new SimpleMailMessage();
		envemail.setFrom("Muuzik");
		envemail.setTo(vendedor.getEmail());
		envemail.setSubject("Te han hecho un pedido.");
		envemail.setText("El usuario "+comprador+" ha realizado un pedido sobre tu anuncio llamado: "
				+ anuncio.getArticulo().getNombre()+", con precio "+anuncio.getPrecio()+"€.");
		servicioEmail.send(envemail);
		
	}
	
	@PostMapping("/email/anuncio")
    public void notificarNuevoAnuncio(@RequestBody Anuncio anuncio)
    {
        List<Usuario> users = userRepo.findAll();
        
        
        for(Usuario singleuser : users)
        {
            SimpleMailMessage envemail = new SimpleMailMessage();
            envemail.setFrom("Muuzik");
            envemail.setTo(singleuser.getEmail());
            envemail.setSubject("Nuevo anuncio publicado");
            envemail.setText("Hay un nuevo instrumento que podria interesarte.");
            servicioEmail.send(envemail);
        }
    }

}

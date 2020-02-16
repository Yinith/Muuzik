package es.codeurjc.daw;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class MensajeController {
	
	@Autowired
	private MensajeRepository msgRepo;

}

package es.codeurjc.daw;

import java.util.Optional;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class PedidoController {

	@Autowired
	private AnunciosRepository adRepo;
	@Autowired
	private UsuariosRepository usRepo;
	@Autowired
	private PedidosRepository peRepo; 

	@PostConstruct
	public void init() {

	}

	@GetMapping("/nuevo_pedido/{idAnuncio}")
	public String tablon(Model model, @PathVariable long idAnuncio) {

		Usuario user = usRepo.findByNick("Admin");
		Optional<Anuncio> op = adRepo.findById(idAnuncio);
		if(op.isPresent()) {
			peRepo.save(new Pedido(user, op.get()));
		}
		return "pedido_realizado";
	}

}
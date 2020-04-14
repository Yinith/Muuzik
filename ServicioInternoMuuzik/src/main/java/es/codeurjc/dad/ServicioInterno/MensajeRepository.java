package es.codeurjc.dad.ServicioInterno;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface MensajeRepository extends JpaRepository<Mensaje, Long> {
	
	List<Mensaje> findAllByDestinatario(Usuario d);

}

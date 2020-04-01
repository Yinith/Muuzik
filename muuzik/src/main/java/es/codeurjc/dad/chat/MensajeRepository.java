package es.codeurjc.dad.chat;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import es.codeurjc.dad.usuario.Usuario;

public interface MensajeRepository extends JpaRepository<Mensaje, Long> {
	
	List<Mensaje> findAllByDestinatario(Usuario d);

}

package es.codeurjc.dad.chat;

import org.springframework.data.jpa.repository.JpaRepository;

import es.codeurjc.dad.usuario.Usuario;

public interface MensajeRepository extends JpaRepository<Mensaje, Long> {
	
	Mensaje findByDestinatario(Usuario d);

}

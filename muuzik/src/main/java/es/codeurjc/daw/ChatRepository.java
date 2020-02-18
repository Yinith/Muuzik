package es.codeurjc.daw;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ChatRepository extends JpaRepository<Chat, Long> {
	List<Chat> findByRemitente(Usuario remit);
	List<Chat> findByDestinatario(Usuario destin);
}

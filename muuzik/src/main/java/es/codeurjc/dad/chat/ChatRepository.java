package es.codeurjc.dad.chat;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ChatRepository extends JpaRepository<Chat, Long> {
	List<Chat> findByRemitente_Id(Long remit);
	List<Chat> findByDestinatario_Id(Long destin);
	
	Optional<Chat> findByRemitente_IdAndDestinatario_Id(Long remit, Long destin);
}

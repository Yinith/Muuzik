package es.codeurjc.daw;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ChatRepository extends JpaRepository<Chat, Long> {
	List<Chat> findByComprador(Usuario comprador);
	List<Chat> findByVendedor(Usuario vendedor);
}

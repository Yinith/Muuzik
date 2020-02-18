package es.codeurjc.daw;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuariosRepository extends JpaRepository<Usuario, Long> {
	
	Optional<Usuario> findByNickAndContrasena(String nick, String contrasena);
	Usuario findByNick(String nick);
	

}
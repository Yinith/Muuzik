package es.codeurjc.dad.usuario;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
	
	Optional<Usuario> findByNickAndContrasena(String nick, String contrasena);
	Usuario findByNick(String nick);
	

}
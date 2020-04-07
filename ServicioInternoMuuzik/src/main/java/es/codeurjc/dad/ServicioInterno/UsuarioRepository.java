package es.codeurjc.dad.ServicioInterno;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
	
	Optional<Usuario> findByNickAndContrasena(String nick, String contrasena);
	Usuario findByNick(String nick);
	

}
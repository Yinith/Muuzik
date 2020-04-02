package es.codeurjc.dad.security;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import es.codeurjc.dad.usuario.Usuario;
import es.codeurjc.dad.usuario.UsuarioRepository;

@Component
public class UsuarioRepositoryAuthProvider implements AuthenticationProvider{
	
	@Autowired
	private UsuarioRepository userRepo;
	
	@Override
	public Authentication authenticate(Authentication auth) throws AuthenticationException {
		
		Usuario usuario = userRepo.findByNick(auth.getName());
		
		if (usuario == null) {
			throw new BadCredentialsException("User not found");
		}
		
		String password = (String) auth.getCredentials();
		//Método de encriptacion de contraseñas.
		//Los métodos buenos de encriptación no permiten desencriptar.
		//De modo que encriptamos de nuevo el string con la contraseña, para compararlos.
		if (!new BCryptPasswordEncoder().matches(password, usuario.getContrasena())) {
			throw new BadCredentialsException("Wrong password");
		}
		
		List<GrantedAuthority> roles = new ArrayList<>();
		for (String role : usuario.getRoles()) {
			roles.add(new SimpleGrantedAuthority(role));
		}
		
		return new UsernamePasswordAuthenticationToken(usuario.getNick(), password, roles);
	}
	
	@Override
	public boolean supports(Class<?> authenticationObject) {
		return true;
	}
}

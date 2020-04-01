package es.codeurjc.dad.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableGlobalMethodSecurity(securedEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Autowired
	public UsuarioRepositoryAuthProvider authenticationProvider;
	
	@Override
	protected void configure(HttpSecurity http) throws Exception{
		
		///// PAGINAS PUBLICAS
		http.authorizeRequests().antMatchers("/").permitAll();
		http.authorizeRequests().antMatchers("/login").permitAll();
		http.authorizeRequests().antMatchers("/loginerror").permitAll();
		http.authorizeRequests().antMatchers("/logout").permitAll();
		http.authorizeRequests().antMatchers("/register").permitAll();
		http.authorizeRequests().antMatchers("/registerOK").permitAll();
		
		///// PAGINAS TRAS AUTENTICACION
		http.authorizeRequests().antMatchers("/borrar_anuncio/{id}").hasAnyRole("ADMIN");
		http.authorizeRequests().antMatchers("/usuario/{userId}/edit").hasAnyRole("ADMIN"); //Solo se puede editar un usuario si eres admin
		http.authorizeRequests().anyRequest().authenticated();
		
		
		///// FORMULARIO DE LOGIN
		http.formLogin().loginPage("/login");
		http.formLogin().usernameParameter("nick"); //Estos tienen que ser exactamente los nombres de los campos "nombre de usuario" y "contraseña" dentro del formulario de login, para que spring security sepa usarlos a partir de los datos que le llegan del formulario
		http.formLogin().passwordParameter("contrasena");
		http.formLogin().defaultSuccessUrl("/tablon");
		http.formLogin().failureUrl("/loginerror");

		
		///// LOGOUT
        http.logout().logoutUrl("/logout");
        http.logout().logoutSuccessUrl("/");
		
       
		//http.csrf().disable();
	
	}
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception{
		//Autenticacion de usuarios en base de datos
		auth.authenticationProvider(authenticationProvider);
	}

}

package es.codeurjc.daw;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Usuario {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	@Column(unique = true)
	private String nick;
	private String contrasena;
	private String info_perfil;
	
	public Usuario () {
		
	}
	
	public Usuario (String nick, String contrasena, String info_perfil) {
		super();
		this.nick = nick;
		this.contrasena = contrasena;
		this.info_perfil = info_perfil;
	}

	public String getNick() {
		return nick;
	}

	public void setNick(String nick) {
		this.nick = nick;
	}

	public String getContrasena() {
		return contrasena;
	}

	public void setContrasena(String contrasena) {
		this.contrasena = contrasena;
	}

	public String getPerfil() {
		return info_perfil;
	}

	public void setPerfil(String info_perfil) {
		this.info_perfil = info_perfil;
	}

	@Override
	public String toString() {
		return "Usuario [nombre=" + nick + ", contraseña=" + contrasena + ", info_perfil=" + info_perfil + "]";

	}
}
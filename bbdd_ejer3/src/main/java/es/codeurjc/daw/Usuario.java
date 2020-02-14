package es.codeurjc.daw;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Usuario {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	private String nick;
	private String contraseña;
	private String info_perfil;
	
	public Usuario () {
		
	}
	
	public Usuario (String nick, String contraseña, String info_perfil) {
		super();
		this.nick = nick;
		this.contraseña = contraseña;
		this.info_perfil = info_perfil;
	}

	public String getNick() {
		return nick;
	}

	public void setNick(String nick) {
		this.nick = nick;
	}

	public String getContraseña() {
		return contraseña;
	}

	public void setContraseña(String contraseña) {
		this.contraseña = contraseña;
	}

	public String getPerfil() {
		return info_perfil;
	}

	public void setPerfil(String info_perfil) {
		this.info_perfil = info_perfil;
	}

	@Override
	public String toString() {
		return "Usuario [nombre=" + nick + ", contraseña=" + contraseña + ", info_perfil=" + info_perfil + "]";

	}
}

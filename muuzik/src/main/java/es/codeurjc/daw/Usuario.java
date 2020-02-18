package es.codeurjc.daw;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;


@Entity
public class Usuario {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	@Column(unique = true)
	private String nick;
	private String contrasena;
	private String info_perfil
	;
	@OneToMany
	private List<Anuncio> anuncios;
	@OneToMany(mappedBy = "remitente")
	private List<Chat> c1; //Nombre provisional podría ser misChats?
	@OneToMany(mappedBy = "destinatario")
	private List<Chat> c2; //Nombre provisional

	
	public Usuario () {
		
	}
	
	public Usuario (String nick, String contrasena, String info_perfil) {
		super();
		this.nick = nick;
		this.contrasena = contrasena;
		this.info_perfil = info_perfil;
		anuncios = new ArrayList<Anuncio> ();
	}

	public List<Anuncio> getAnuncios() {
		return anuncios;
	}

	public void setAnuncios(List<Anuncio> anuncios) {
		this.anuncios = anuncios;
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
	
	public void addAnuncio(Anuncio v1) {
		v1.setUsuario(this);
		this.anuncios.add(v1);
	}

	@Override
	public String toString() {
		return "Usuario [nombre=" + nick + ", contraseña=" + contrasena + ", info_perfil=" + info_perfil + "]";

	}
}

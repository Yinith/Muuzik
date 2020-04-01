package es.codeurjc.dad.usuario;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ListIterator;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import es.codeurjc.dad.anuncio.Anuncio;
import es.codeurjc.dad.articulo.Articulo;
import es.codeurjc.dad.chat.Mensaje;
import es.codeurjc.dad.pedido.Pedido;




@Entity
public class Usuario {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	@Column(unique = true)
	private String nick;
	private String contrasena; //Contraseña se va a cifrar con una función hash
	private String biografia;
	@ElementCollection(fetch = FetchType.EAGER)
	private List<String> roles;	   //Roles que puede tener el usuario: user, admin
	
	@OneToMany
	private List<Anuncio> anuncios;
	@OneToMany
	private List<Articulo> articulos;
	@OneToMany
	private List<Mensaje> mensajes; 
	
//	@OneToMany(mappedBy = "remitente")
//	private List<Chat> c1; //Nombre provisional podría ser misChats?
//	@OneToMany(mappedBy = "destinatario")
//	private List<Chat> c2; //Nombre provisional
	@OneToMany
	private List<Pedido> pedidos;

	
	public Usuario () {	}
	
	public Usuario (String nick, String contrasena, String bio) {
		this.nick = nick;
		//Encriptacion de la contraseña; ya no se puede desencriptar nunca
		this.contrasena = new BCryptPasswordEncoder().encode(contrasena); 
		this.biografia = bio;
		
		this.roles = new ArrayList<>(); 
		this.roles.add("ROLE_USER"); //Por defecto su rol es user (no es admin)
		
		anuncios = new ArrayList<Anuncio>();
		articulos = new ArrayList<Articulo>();
	}

	// Constructor sobrecargado: permite escoger el rol del usuario desde su creacion
	public Usuario (String nick, String contrasena, String info_perfil, String ... roles) {
		this.nick = nick;
		this.contrasena = new BCryptPasswordEncoder().encode(contrasena); 
		this.biografia = info_perfil;
		this.roles = new ArrayList<>(Arrays.asList(roles));
		
		anuncios = new ArrayList<Anuncio>();
		articulos = new ArrayList<Articulo>();
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
		this.contrasena = new BCryptPasswordEncoder().encode(contrasena);
	}

	public String getBio() {
		return biografia;
	}

	public void setBio(String info_perfil) {
		this.biografia = info_perfil;
	}
	
	public List<String> getRoles() {
		return roles;
	}

	public void setRoles(List<String> roles) {
		this.roles = roles;
	}
	
	public void addRole(String role) {
		this.roles.add(role);
	}
	
	public List<Anuncio> getAnuncios() {
		return this.anuncios;
	}

	public void setAnuncios(List<Anuncio> anuncios) {
		this.anuncios = anuncios;
	}
	
	public List<Articulo> getArticulos() {
		return this.articulos;
	}

	public void setArticulos(List<Articulo> articulos) {
		this.articulos = articulos;
	}
	
	public List<Pedido> getPedido() {
		return this.pedidos;
	}

	public void addPedido(Pedido pedido) {
		this.pedidos.add(pedido);
	}

	public void addAnuncio(Anuncio v1) {
		v1.setAnunciante(this);
		this.anuncios.add(v1);
		this.articulos.add(v1.getArticulo());
	}
	
	public void addAnuncio(Anuncio ad, Articulo art) {
		ad.setAnunciante(this);
		ad.setArticulo(art);
		this.anuncios.add(ad);
		this.articulos.add(art);
	}
	
	public void addArticulo(Articulo art) {
		this.articulos.add(art);
	}
	
	public void addMensaje(Mensaje msg) {
		this.mensajes.add(msg);
	}

	
	public boolean borrarAnuncio(Anuncio ad) {
		return this.anuncios.remove(ad);
	}
	
	public void borrarTodosAnuncios() {
		ListIterator<Anuncio> iter = this.anuncios.listIterator();
		while(iter.hasNext()){
			iter.next();
			iter.remove();
		}
	}
	
	public boolean borrarArticulo(Articulo art) {
		return this.articulos.remove(art);
	}

	public void borrarTodosArticulos() {
		ListIterator<Articulo> iter = this.articulos.listIterator();
		while(iter.hasNext()){
			iter.next();
			iter.remove();
		}
	}

	/*public boolean borrarPedido(Pedido p) {
		return this.pedido.remove(p);
	}
/*
	public void borrarTodosPedidos() {
		ListIterator<Pedido> iter = this.pedidos.listIterator();
		while(iter.hasNext()){
			iter.next();
			iter.remove();
		}
	}
*/	
	@Override
	public String toString() {
		return "Usuario [id=" + id + ", nick=" + nick + ", contrasena=" + contrasena + ", info_perfil=" + biografia
				+ ", anuncios=" + anuncios + ", articulos=" + articulos + ", pedidos=" + pedidos + "]";
	}
	

}

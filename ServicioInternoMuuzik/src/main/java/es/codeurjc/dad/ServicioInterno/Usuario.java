package es.codeurjc.dad.ServicioInterno;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ListIterator;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Usuario {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	@Column(unique = true)
	private String nick;
	@Column(unique = true)
	private String email;
	@JsonIgnore
	private String contrasena; //Contraseña se va a cifrar con una función hash
	@JsonIgnore
	private String biografia;
	
	@JsonIgnore
	@ElementCollection(fetch = FetchType.EAGER)
	private List<String> roles;	   //Roles que puede tener el usuario: user, admin
	
	@JsonIgnore
	@OneToMany(mappedBy="anunciante")
	private List<Anuncio> anuncios;
	
	@JsonIgnore
	@OneToMany
	private List<Articulo> articulos;
	
	@JsonIgnore
	@OneToMany(mappedBy="destinatario")
	private List<Mensaje> mensajes;	
	
	@JsonIgnore
	@OneToMany(mappedBy="comprador")
	private List<Pedido> historialPedidos; // Lista de pedidos comprados
	
	

	
	public Usuario () {	}
	
	public Usuario (String email, String nick, String contrasena, String bio) {
		this.email = email;
		this.nick = nick;
		//Encriptacion de la contraseña; ya no se puede desencriptar nunca
//		this.contrasena = new BCryptPasswordEncoder().encode(contrasena); 
		this.biografia = bio;
		
		this.roles = new ArrayList<>(); 
		this.roles.add("ROLE_USER"); //Por defecto su rol es user (no es admin)
		
		this.anuncios = new ArrayList<Anuncio>();
		this.articulos = new ArrayList<Articulo>();
		this.historialPedidos = new ArrayList<Pedido>(); 
		this.mensajes = new ArrayList<Mensaje>();
		
		
	}

	// Constructor sobrecargado: permite escoger el rol del usuario desde su creacion
	public Usuario (String email, String nick, String contrasena, String bio, String ... roles) {
		this.email = email;
		this.nick = nick;
//		this.contrasena = new BCryptPasswordEncoder().encode(contrasena); 
		this.biografia = bio;
		this.roles = new ArrayList<>(Arrays.asList(roles));
		
		this.anuncios = new ArrayList<Anuncio>();
		this.articulos = new ArrayList<Articulo>();
		this.historialPedidos = new ArrayList<Pedido>(); 
		this.mensajes = new ArrayList<Mensaje>();
	}

	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getNick() {
		return nick;
	}

	public void setNick(String nick) {
		this.nick = nick;
	}
/*
	public String getContrasena() {
		return contrasena;
	}
/*
	public void setContrasena(String contrasena) {
		this.contrasena = new BCryptPasswordEncoder().encode(contrasena);
	}
*/
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
	
	public List<Pedido> getHistPedidos() {
		return this.historialPedidos;
	}

	public void addPedido(Pedido pedido) {
		this.historialPedidos.add(pedido);
	}

	public void addAnuncio(Anuncio v1) {
		v1.setAnunciante(this);
		this.articulos.add(v1.getArticulo());
		this.anuncios.add(v1);
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
				+ ", anuncios=" + anuncios + ", articulos=" + articulos + ", pedidos="
				+ historialPedidos + "]";
	}
	

}

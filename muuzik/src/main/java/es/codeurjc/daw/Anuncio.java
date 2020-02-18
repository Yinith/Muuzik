 package es.codeurjc.daw;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

@Entity
public class Anuncio {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	private String asunto;
	private String comentario;
	private int precio;
	
	@OneToOne
	private Articulo articulo;
	
	@ManyToOne
	private Usuario user; 

	public Anuncio() {}
	
	public Anuncio(Articulo articulo, String asunto, String comentario, int precio) {
		super();
		this.asunto = asunto;
		this.comentario = comentario;
		this.precio = precio;
		this.articulo = articulo;
	}
	
	public Anuncio(String nombre, String asunto, String comentario, int precio) {
		super();
		this.articulo = new Articulo(nombre);
		this.asunto = asunto;
		this.comentario = comentario;
		this.precio = precio;
	}

	public long getId() {
		return id;
	}

	public String getAsunto() {
		return asunto;
	}

	public String getComentario() {
		return comentario;
	}

	public int getPrecio() {
		return precio;
	}

	public Usuario getUser() {
		return user;
	}

	public Usuario getUsuario() {
		return this.user;
	}
	
	public Articulo getArticulo() {
		return this.articulo;
	}


	public void setId(long id) {
		this.id = id;
	}

	public void setAsunto(String asunto) {
		this.asunto = asunto;
	}

	public void setComentario(String comentario) {
		this.comentario = comentario;
	}
	
	public void setPrecio(int precio) {
		this.precio = precio;
	} 
	
	public void setUsuario(Usuario u){
		this.user = u;
	}
	
	public void setArticulo(Articulo art) {
		this.articulo = art;
	}


	@Override
	public String toString() {
		return "Anuncio [nombre=" + user.getNick() + ", asunto=" + asunto + ", comentario=" + comentario + "]";
	}
}


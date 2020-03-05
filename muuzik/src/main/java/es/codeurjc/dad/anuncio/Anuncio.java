 package es.codeurjc.dad.anuncio;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import es.codeurjc.dad.articulo.Articulo;
import es.codeurjc.dad.usuario.Usuario;

@Entity
public class Anuncio {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	private String comentario;
	private int precio;
	
	//@OneToOne(cascade = CascadeType.ALL)
	@OneToOne(cascade = CascadeType.ALL)
	private Articulo articulo;
	@ManyToOne
	private Usuario user;

	public Anuncio() {}
	
	public Anuncio(Articulo articulo, String comentario, int precio) {
		super();
		this.comentario = comentario;
		this.precio = precio;
		this.articulo = articulo;
	}
	
	public Anuncio(String nombre, String comentario, int precio) {
		super();
		this.articulo = new Articulo(nombre);
		this.comentario = comentario;
		this.precio = precio;
	}

	public long getId() {
		return id;
	}

	public String getComentario() {
		return comentario;
	}

	public int getPrecio() {
		return precio;
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
		return "Anuncio [id=" + id + ", comentario=" + comentario + ", precio=" + precio + ", articulo=" + articulo
				+ ", user=" + user + "]";
	}



}


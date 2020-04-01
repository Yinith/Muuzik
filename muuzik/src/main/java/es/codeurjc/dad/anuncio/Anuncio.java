 package es.codeurjc.dad.anuncio;

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
	private boolean vendido;
	
	//@OneToOne(cascade = CascadeType.ALL)
	@OneToOne
	private Articulo articulo;
	@ManyToOne
	private Usuario anunciante;
	
	

	public Anuncio() {}
	
	public Anuncio(Articulo articulo, String comentario, int precio) {
		this.comentario = comentario;
		this.precio = precio;
		this.vendido = false;
		this.articulo = articulo;
	}
	
	public Anuncio(String nombre, String comentario, int precio) {
		super();
		this.comentario = comentario;
		this.precio = precio;
		this.vendido = false;
		this.articulo = new Articulo(nombre);
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

	public Usuario getAnunciante() {
		return this.anunciante;
	}
	
	public Articulo getArticulo() {
		return this.articulo;
	}
	
	public boolean isVendido() {
		return this.vendido;
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
	
	public void setAnunciante(Usuario u){
		this.anunciante = u;
	}
	
	public void setArticulo(Articulo art) {
		this.articulo = art;
	}

	public void deleteArticulo() {
		this.articulo = null;
	}
	
	
	public void setVendido() {
		this.vendido = true;
	}
	
	@Override
	public String toString() {
		return "Anuncio [id=" + id + ", comentario=" + comentario + ", precio=" + precio + ", articulo=" + articulo
				+ ", anunciante=" + anunciante + "]";
	}



}


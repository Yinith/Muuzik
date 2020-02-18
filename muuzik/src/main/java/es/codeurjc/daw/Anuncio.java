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

	private String nombre;
	private String asunto;
	private String comentario;
	
	/*
	 * @OneToOne private Articulo articulo; private int precio;
	 */
	
	@ManyToOne
	private Usuario user; 

	public Anuncio() {}
	
	public Anuncio(String nombre, String asunto, String comentario) {
		super();
		this.nombre = nombre;
		this.asunto = asunto;
		this.comentario = comentario;
	}

	/*
	 * public AnuncioVenta(String nombre, String asunto, String comentario, Articulo
	 * articulo, int precio) { super(); this.nombre = nombre; this.asunto = asunto;
	 * this.comentario = comentario; this.articulo = articulo; this.precio = precio;
	 * }
	 */

	public long getId() {
		return id;
	}

	public String getNombre() {
		return nombre;
	}

	public String getAsunto() {
		return asunto;
	}

	public String getComentario() {
		return comentario;
	}

	/*
	 * public Articulo getArticulo() { return articulo; }
	 * 
	 * public int getPrecio() { return precio; }
	 */
	
	public Usuario getUsuario() {
		return this.user;
	}


	public void setId(long id) {
		this.id = id;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public void setAsunto(String asunto) {
		this.asunto = asunto;
	}

	public void setComentario(String comentario) {
		this.comentario = comentario;
	}

	/*
	 * public void setArticulo(Articulo articulo) { this.articulo = articulo; }
	 * 
	 * public void setPrecio(int precio) { this.precio = precio; }
	 */
	
	public void setUsuario(Usuario u){
		this.user = u;
	}

	@Override
	public String toString() {
		return "Anuncio [nombre=" + nombre + ", asunto=" + asunto + ", comentario=" + comentario + "]";
	}

//	public AnuncioVenta(String nombre, String asunto, String comentario) {
//		super();
//		this.nombre = nombre;
//		this.asunto = asunto;
//		this.comentario = comentario;
//	}
//
//	public String getNombre() {
//		return nombre;
//	}
//
//	public void setNombre(String nombre) {
//		this.nombre = nombre;
//	}
//
//	public String getAsunto() {
//		return asunto;
//	}
//
//	public void setAsunto(String asunto) {
//		this.asunto = asunto;
//	}
//
//	public String getComentario() {
//		return comentario;
//	}
//
//	public void setComentario(String comentario) {
//		this.comentario = comentario;
//	}
//
//	@Override
//	public String toString() {
//		return "Anuncio [nombre=" + nombre + ", asunto=" + asunto + ", comentario=" + comentario + "]";
//	}

}

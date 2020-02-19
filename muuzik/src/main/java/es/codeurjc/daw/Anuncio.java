 package es.codeurjc.daw;

import javax.persistence.CascadeType;
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
	private String comentario;
	private int precio;
	
	//@OneToOne(cascade = CascadeType.ALL)
	@OneToOne(cascade = CascadeType.ALL)
	private Articulo articulo;
	@ManyToOne
	private Usuario user;
	/*
	@OneToOne
	private Pedido pedido;
*/
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

	public Usuario getUser() {
		return user;
	}

	public Usuario getUsuario() {
		return this.user;
	}
	
	public Articulo getArticulo() {
		return this.articulo;
	}
	/*
	public Pedido getPedido() {
		return pedido;
	}

	public void setPedido(Pedido pedido) {
		this.pedido = pedido;
	}
*/
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
		return "Anuncio [articulo=" + articulo.getNombre() + ", vendedor=" + user.getNick() + ", comentario=" + comentario + "]";
	}
	/*
	public void addPedido(Pedido p)
	{
		this.pedido = p;
	}
	
	@Override
	public String toString() {
		return "Anuncio [articulo=" + articulo.getNombre() + ", vendedor=" + user.getNick() + ", comentario=" + comentario + ", pedido=" + pedido + "]";
	}
	*/

}


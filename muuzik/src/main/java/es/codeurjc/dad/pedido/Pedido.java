package es.codeurjc.dad.pedido;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import es.codeurjc.dad.anuncio.Anuncio;
import es.codeurjc.dad.articulo.Articulo;
import es.codeurjc.dad.usuario.Usuario;


//Entidad que va a simular de momento la compra
@Entity
public class Pedido {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	@OneToOne
	private Anuncio anuncio;
	
	@ManyToOne
	private Usuario comprador;
	
	public Pedido () {}
	
	public Pedido (Usuario c, Anuncio a) 
	{
		super ();
		this.comprador = c;
		this.anuncio = a;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Usuario getComprador() {
		return this.comprador;
	}

	public void setComprador(Usuario user) {
		this.comprador = user;
	}

	public Anuncio getAnuncio() {
		return anuncio;
	}

	public void setAnuncio(Anuncio anuncio) {
		this.anuncio = anuncio;
	}
	
/*	
	// Este método asigna un nuevo dueño al articulo. 
	public void comprado() {
		Articulo art = new Articulo(anuncio.getArticulo().getNombre());		
		//this.comprador.addPedido(this);					// La añado al historial de pedidos del usuario = historial comprados
		this.anuncio.getAnunciante().borrarArticulo(art);   // Le quito el objeto al vendedor
		this.comprador.addArticulo(art);					// Añado el articulo a los objetos del comprador
		this.anuncio.setVendido();							// El anuncio se marca como vendido (luego aparecerá en una lista de anuncios vendidos en el perfil del vendedor).  	
	}
*/
	@Override
	public String toString() {
		return "Pedido [id=" + id + ", comprador =" + comprador + ", vendedor = " + anuncio.getAnunciante() + "anuncio=" + anuncio + "]";
	}

}

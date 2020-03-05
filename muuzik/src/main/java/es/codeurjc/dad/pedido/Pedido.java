package es.codeurjc.dad.pedido;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import es.codeurjc.dad.anuncio.Anuncio;
import es.codeurjc.dad.usuario.Usuario;


//Entidad que va a simular de momento la compra
@Entity
public class Pedido {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	@ManyToOne
	private Usuario user;
	
	@OneToOne
	private Anuncio anuncio;
	
	public Pedido () {}
	
	public Pedido (Usuario u, Anuncio a) 
	{
		super ();
		this.user = u;
		this.anuncio = a;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Usuario getUser() {
		return user;
	}

	public void setUser(Usuario user) {
		this.user = user;
	}

	public Anuncio getAnuncio() {
		return anuncio;
	}

	public void setAnuncio(Anuncio anuncio) {
		this.anuncio = anuncio;
	}

	@Override
	public String toString() {
		return "Pedido [id=" + id + ", user=" + user + ", anuncio=" + anuncio + "]";
	}

}

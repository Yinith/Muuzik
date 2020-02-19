package es.codeurjc.daw;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;



@Entity
public class Usuario {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	@Column(unique = true)
	private String nick;
	private String contrasena;
	private String info_perfil;
	
	@OneToMany
	private List<Anuncio> anuncios;
	@OneToMany
	private List<Articulo> articulos;
	
	@OneToMany(mappedBy = "remitente")
	private List<Chat> c1; //Nombre provisional podría ser misChats?
	@OneToMany(mappedBy = "destinatario")
	private List<Chat> c2; //Nombre provisional
	@OneToOne
	private Pedido pedido;

	
	public Usuario () {
		
	}
	
	public Usuario (String nick, String contrasena, String info_perfil) {
		super();
		this.nick = nick;
		this.contrasena = contrasena;
		this.info_perfil = info_perfil;
		
		anuncios = new ArrayList<Anuncio>();
		articulos = new ArrayList<Articulo>();
		c1 = new ArrayList<Chat>();
		c2 = new ArrayList<Chat>();
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
	
	
	
	public Pedido getPedido() {
		return pedido;
	}

	public void setPedido(Pedido pedido) {
		this.pedido = pedido;
	}

	public void addAnuncio(Anuncio v1) {
		v1.setUsuario(this);
		this.anuncios.add(v1);
		this.articulos.add(v1.getArticulo());
	}
	
	public void addAnuncio(Anuncio ad, Articulo art) {
		ad.setUsuario(this);
		ad.setArticulo(art);
		this.anuncios.add(ad);
		this.articulos.add(art);
	}
	
	public void addArticulo(Articulo art) {
		this.articulos.add(art);
	}
	
	public void addPedido(Pedido p)
	{
		this.pedido = p;
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

	@Override
	public String toString() {
		return "Usuario [id=" + id + ", nick=" + nick + ", contrasena=" + contrasena + ", info_perfil=" + info_perfil
				+ ", anuncios=" + anuncios + ", articulos=" + articulos + ", c1=" + c1 + ", c2=" + c2 + ", pedido="
				+ pedido + "]";
	}
	

}

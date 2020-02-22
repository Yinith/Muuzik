package es.codeurjc.daw;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.*;


@Entity
public class Chat {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	@ManyToOne
	private Usuario remitente;
	
	@ManyToOne 
	private Usuario destinatario;
	
	@OneToMany(cascade=CascadeType.ALL)
	private List<Mensaje> mensajes;
	
	protected Chat() {}
	
	public Chat(Usuario u1, Usuario u2) {
		this.remitente = u1; //El que compra
		this.destinatario = u2; //El que vende
		mensajes = new ArrayList<Mensaje>();
	}

	public List<Mensaje> getMensajes() {
		return mensajes;
	}
	
	public void insertarMensaje(String mensaje) {
		this.mensajes.add(new Mensaje(mensaje)); //No le pongo:  "HH:mm:ss dd/MM/yyyy"
	}
	
	public void addMensaje(Mensaje mensaje)
	{
		this.mensajes.add(mensaje);
	}

	public long getId() {
		return id;
	}

	public Usuario getComprador() {
		return remitente;
	}

	public Usuario getVendedor() {
		return destinatario;
	}
	
	
	
	
	
}

package es.codeurjc.daw;

import java.text.SimpleDateFormat;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Mensaje {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	@Column(unique = true)
	private String remitente;
	@Column(unique = true)
	private String destinatario;
	private String mensaje;
	private SimpleDateFormat fecha = new SimpleDateFormat("HH:mm:ss dd/MM/yyyy");
	
	public Mensaje() {
		
	}

	public Mensaje(long id, String remitente, String destinatario, String mensaje, SimpleDateFormat fecha) {
		super();
		this.id = id;
		this.remitente = remitente;
		this.destinatario = destinatario;
		this.mensaje = mensaje;
		this.fecha = fecha;
	}

	public long getId() {
		return id;
	}

	public String getRemitente() {
		return remitente;
	}

	public String getDestinatario() {
		return destinatario;
	}

	public String getMensaje() {
		return mensaje;
	}

	public SimpleDateFormat getFecha() {
		return fecha;
	}

	public void setId(long id) {
		this.id = id;
	}

	public void setRemitente(String remitente) {
		this.remitente = remitente;
	}

	public void setDestinatario(String destinatario) {
		this.destinatario = destinatario;
	}

	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}

	public void setFecha(SimpleDateFormat fecha) {
		this.fecha = fecha;
	}

	@Override
	public String toString() {
		return "Mensaje [id=" + id + ", remitente=" + remitente + ", destinatario=" + destinatario + ", mensaje="
				+ mensaje + ", fecha=" + fecha + "]";
	}
			
	

}


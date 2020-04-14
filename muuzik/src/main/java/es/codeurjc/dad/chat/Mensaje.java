package es.codeurjc.dad.chat;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

import es.codeurjc.dad.usuario.Usuario;

@Entity
public class Mensaje {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	private String asunto;
	private String cuerpo;
	
	@JsonIgnore
	@OneToOne
	private Usuario destinatario;
	@JsonIgnore
	@OneToOne
	private Usuario remitente;
	
	
	protected Mensaje(){
		
	}
	
	public Mensaje(Usuario r, Usuario d, String a, String c) {
		this.remitente = r;
		this.destinatario = d;
		this.asunto = a;
		this.cuerpo = c;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getAsunto() {
		return asunto;
	}

	public void setAsunto(String asunto) {
		this.asunto = asunto;
	}

	public String getCuerpo() {
		return cuerpo;
	}

	public void setCuerpo(String cuerpo) {
		this.cuerpo = cuerpo;
	}

	public Usuario getDestinatario() {
		return destinatario;
	}

	public void setDestinatario(Usuario destinatario) {
		this.destinatario = destinatario;
	}

	public Usuario getRemitente() {
		return remitente;
	}

	public void setRemitente(Usuario remitente) {
		this.remitente = remitente;
	}

	@Override
	public String toString() {
		return "Mensaje [id=" + id + ", asunto=" + asunto + ", cuerpo=" + cuerpo + ", destinatario=" + destinatario
				+ ", remitente=" + remitente + "]";
	}

}

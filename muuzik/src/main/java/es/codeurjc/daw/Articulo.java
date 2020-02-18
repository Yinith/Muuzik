package es.codeurjc.daw;

import java.text.SimpleDateFormat;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Articulo {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	private String nombre = "";
	private String categoria = "";
	private boolean publico = true;
	private int anoFabricacion = 0;//Solo el año
	
	public Articulo() {
		
	}
	
	public Articulo(String nombre) {
		super();
		this.nombre = nombre;
	}
	
	public Articulo(String nombre, int anoFabricacion) {
		this.nombre = nombre;
		this.anoFabricacion = anoFabricacion;
	}
	
	public Articulo(String nombre, String categoria, int anoFabricacion) {
		super();
		this.nombre = nombre;
		this.categoria = categoria;
		this.anoFabricacion = anoFabricacion;
	}
	
	public Articulo(String nombre, String categoria, boolean publico) {
		super();
		this.nombre = nombre;
		this.categoria = categoria;
		this.publico = publico;
		this.anoFabricacion = 0;
	}
			
			
	public Articulo(String foto, String categoria, boolean publico, int anoFabricacion) {
		super();
		this.nombre = foto;
		this.categoria = categoria;
		this.publico = publico;
		if(anoFabricacion > 0) {
			this.anoFabricacion = anoFabricacion;
		}
		else this.anoFabricacion = 0;
	}

	public long getId() {
		return id;
	}

	public String getFoto() {
		return nombre;
	}

	public String getCategoria() {
		return categoria;
	}

	public boolean isPublico() {
		return publico;
	}

	public int getAnoFabricacion() {
		return anoFabricacion;
	}

	public void setId(long id) {
		this.id = id;
	}

	public void setFoto(String foto) {
		this.nombre = foto;
	}

	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}

	public void setPublico(boolean publico) {
		this.publico = publico;
	}

	public void setAnoFabricacion(int anoFabricacion) {
		this.anoFabricacion = anoFabricacion;
	}

	@Override
	public String toString() {
		return "Articulo [id=" + id + ", foto=" + nombre + ", categoria=" + categoria + ", publico=" + publico
				+ ", anoFabricacion=" + anoFabricacion + "]";
	}
	
	
	
}

package com.politecnicomalaga.entidadbancaria;

public class Operacion {
	long cod;
	String fecha;
	float cantidad;
	
	public Operacion(long cod, String fecha, float cantidad) {
		super();
		this.cod = cod;
		this.fecha = fecha;
		this.cantidad = cantidad;
	}

	public long getCod() {
		return cod;
	}

	public void setCod(long cod) {
		this.cod = cod;
	}

	public String getFecha() {
		return fecha;
	}

	public void setFecha(String fecha) {
		this.fecha = fecha;
	}

	public float getCantidad() {
		return cantidad;
	}

	public void setCantidad(float cantidad) {
		this.cantidad = cantidad;
	}

	@Override
	public String toString() {
		return "Operacion [cod=" + cod + ", fecha=" + fecha + ", cantidad=" + cantidad + "]";
	}
	
	
	
	
	
}

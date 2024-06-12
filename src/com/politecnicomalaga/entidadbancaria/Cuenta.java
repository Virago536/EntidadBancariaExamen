package com.politecnicomalaga.entidadbancaria;

import java.util.List;

import com.google.gson.Gson;

import java.util.ArrayList;

public class Cuenta {
	
	private String ccc; //Código de la cuenta
	private float saldo;
	private String fechaApertura;
	
	private List<Operacion> misOperaciones;
	
	private Cliente cliente;
	
	
	public Cuenta(String ccc, float saldo, String fechaApertura) {
		this.ccc = ccc;
		this.saldo = saldo;
		this.fechaApertura = fechaApertura;
		this.misOperaciones = new ArrayList<>();
		this.cliente = null;
	}

	public String getCcc() {
		return ccc;
	}

	public void setCcc(String ccc) {
		this.ccc = ccc;
	}

	public float getSaldo() {
		return saldo;
	}
	
	public void setSaldo(float saldo) {
		this.saldo = saldo;
	}


	public String getFechaApertura() {
		return fechaApertura;
	}


	public void setFechaApertura(String fechaApertura) {
		this.fechaApertura = fechaApertura;
	}
	
	public boolean isActiva() {
		return saldo>0;
	}
	
	public boolean retirarEfectivo(float cantidad) {
		if (cantidad <= 0) return false;
		if (cantidad <= saldo) {
			saldo -= cantidad;
			return true;
		}
		return false;
	}

	public boolean ingresarEfectivo(float cantidad) {
		if (cantidad <= 0) return false;
		saldo += cantidad;
		return true;
	}
	
	public boolean agregarOperacion(Operacion nuevaOperacion, long cod) {
		if(!estaOperacion(cod)) {
			misOperaciones.add(nuevaOperacion);
			return true;
		}
		return false;
	}
	
	
	public boolean estaOperacion(long cod) {
		return (buscaOperacion(cod)!=null);
	}
	
	
	public Operacion buscaOperacion(long cod) {
		for(Operacion o: misOperaciones) {
			if(o.getCod()==cod) {
				return o;
			}	
		}
		return null;
	}
	
	public Operacion[] listaDeOperaciones() {
		ArrayList<Operacion> listaDeOperaciones = new ArrayList<>();
		
		for(int i = 0; i<misOperaciones.size(); i++) {
				listaDeOperaciones.add(misOperaciones.get(i));
		}
		return listaDeOperaciones.toArray(new Operacion[0]);
	}

	public Cliente getCliente() {
		return cliente;
	}

	@Override
	public String toString() {
		return "Cuenta ccc: " + ccc + ";saldo: " + saldo + ";fechaApertura: " + fechaApertura + "cliente"+ cliente + "\n;misOperaciones: "
				+ misOperaciones;
	}
	
	public boolean agregarCliente(String ruta) {
		if(!ruta.isBlank()) {
			String json = ControladorFicheros.readText(ruta);
			Gson gson = new Gson();
			cliente = gson.fromJson(json, Cliente.class);
			return true;
		}
		return false;
	}
	

}

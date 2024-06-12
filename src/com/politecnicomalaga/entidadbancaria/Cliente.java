package com.politecnicomalaga.entidadbancaria;

import java.util.ArrayList;
import java.util.List;

public class Cliente {
	private String nombre, apellidos, mail;
	private Documento documento;
	
	public Cliente(String nombre, String apellidos, String mail) {
		this.nombre = nombre;
		this.apellidos = apellidos;
		this.mail = mail;
		this.documento = null;
	}
	
	

	public String getNombre() {
		return nombre;
	}



	public void setNombre(String nombre) {
		this.nombre = nombre;
	}



	public String getApellidos() {
		return apellidos;
	}



	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}



	public String getMail() {
		return mail;
	}



	public void setMail(String mail) {
		this.mail = mail;
	}

	public void setDocumento(Documento documento) {
		this.documento = documento;
	}

	public Documento getDocumento() {
		return documento;
	}


	@Override
	public String toString() {
		return "Cliente [nombre=" + nombre + ", apellidos=" + apellidos + ", mail=" + mail + "]";
	}
	
	
	
	
	
	
	
	
	

}

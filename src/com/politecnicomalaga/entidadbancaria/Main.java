package com.politecnicomalaga.entidadbancaria;

import java.util.Scanner;

import com.google.gson.Gson;

import java.util.InputMismatchException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class Main {
	private static Cuenta miCuenta;
	private static Cliente miCliente;
	
	public static void main(String[] args) {
		//Atributos cuenta
		String atrccc, atrFecha;
		float atrSaldo;
		
		//atributos operaciones
		long atrCod = 0;
		float atrCant = 0;
		String fecha;
		
		boolean loopOp=false;
		
		//atributos cliente
		String nombre;
		
		//atributos fichero
		String ruta;
		PrintWriter miFichero= null;
		
		Scanner sc = new Scanner(System.in);
		String resp;
		int respInt = 0;
		
		
		while(respInt!=8) {
			
		
		System.out.println("BANCO");
		System.out.println("-----------------------");
		System.out.println("A. Dar datos de la cuenta");
		System.out.println("B. Cargar cliente de la cuenta desde fichero JSON");
		System.out.println("C. Realizar ingreso efectivo");
		System.out.println("D. Realizar retirada efectivo");
		System.out.println("E. grabar cuenta a fichero JSON");
		System.out.println("F. Cargar cuenta desde fichero JSON");
		System.out.println("G. Exportar datos a Texto (toString) hacia pantalla y fichero");
		System.out.println("H. Salir");
		
		resp = sc.nextLine();
		if(resp.compareTo("A")==0) {
			respInt = 1;
		}else if(resp.compareTo("B")==0) {
			respInt = 2;
		}else if(resp.compareTo("C")==0) {
			respInt = 3;
		}else if(resp.compareTo("D")==0) {
			respInt = 4;
		}else if(resp.compareTo("E")==0) {
			respInt = 5;
		}else if(resp.compareTo("F")==0) {
			respInt = 6;
		}else if(resp.compareTo("G")==0) {
			respInt = 7;
		}else if(resp.compareTo("H")==0) {
			respInt = 8;
		}	
		switch(respInt){
		case 1:
			System.out.println("Introduzca el ccc de la cuenta");
			atrccc = sc.nextLine();
			System.out.println("Introduzca la fecha de apertura dd/mm/yyyy");
			atrFecha = sc.nextLine();
			atrSaldo = 0;
			
			miCuenta = new Cuenta(atrccc, atrSaldo, atrFecha);
			//Cliente nuevoCliente = new Cliente("Álvaro", "Millán Serrano", "greatalvaro2005@gmail.com");
			//miCuenta.agregarUnCliente(nuevoCliente);
			break;
		case 2:
			if(miCuenta==null) {
				System.out.println("No hay ninguna cuenta agregada");
				break;
			}
			System.out.println("Introduzca la ruta de la que quiere cargar el cliente");
			ruta = sc.nextLine();
			if(miCuenta.agregarCliente(ruta)) {
				System.out.println("Cliente agregado correctamente");
				Documento unDoc = new Documento("DNI", 53703984);
				miCuenta.getCliente().setDocumento(unDoc);
			}else {
				System.out.println("No se pudo agregar el cliente");
			}
			
			break;
		case 3:
			if(miCuenta==null) {
				System.out.println("No hay ninguna cuenta agregada");
				break;
			}
			System.out.println("Introduzca la cantidad de dinero que desea ingresar");
			try {
				atrCant = Integer.valueOf(sc.nextLine());
			}catch(NumberFormatException n) {
				System.out.println("Cantidad no válida");
				atrCant=0;
				break;
			}
			
			if(atrCant<=0) {
				System.out.println("Saldo inválido");
				break;
			}
			System.out.println("Introduzca la fecha en la que se realizó el ingreso");
			fecha = sc.nextLine();
			
			float saldoCambiado = miCuenta.getSaldo() + atrCant;
			miCuenta.setSaldo(saldoCambiado);
			
			loopOp=true;
			while(loopOp==true) {
				atrCod = Math.round(Math.random()*1000000);
				if(miCuenta.buscaOperacion(atrCod)==null) {
					loopOp=false;
				}
			}
			
			
			Operacion nuevaOperacion = new Operacion(atrCod, fecha, atrCant);
			
			System.out.println("código operación: " + nuevaOperacion.getCod());
			System.out.println("fecha: " + nuevaOperacion.getFecha());
			System.out.println("cantidad agregada: " + nuevaOperacion.getCantidad());
			
			if(miCuenta.agregarOperacion(nuevaOperacion, atrCod)) {
				System.out.println("Operación creada correctamente");
			}else {
				System.out.println("No se pudo crear la operación");
			}
			
			break;
		case 4:
			if(miCuenta==null) {
				System.out.println("No hay ninguna cuenta agregada");
				break;
			}
			System.out.println("Introduzca la cantidad de dinero que desea retirar");
			try {
				atrCant = Integer.valueOf(sc.nextLine());
			}catch(NumberFormatException n) {
				System.out.println("Cantidad no válida");
				atrCant=0;
				break;
			}
			if(atrCant<=0) {
				System.out.println("Saldo inválido");
				break;
			}
			System.out.println("Introduzca la fecha en la que se realizó el ingreso");
			fecha = sc.nextLine();
			
			saldoCambiado = miCuenta.getSaldo() - atrCant;
			if(saldoCambiado>=0) {
				miCuenta.setSaldo(saldoCambiado);
				
				loopOp=true;
				while(loopOp==true) {
					atrCod = Math.round(Math.random()*1000000);
					if(miCuenta.buscaOperacion(atrCod)==null) {
						loopOp=false;
					}
				}
				
				nuevaOperacion = new Operacion(atrCod, fecha, atrCant);
				
				System.out.println("código operación: " + nuevaOperacion.getCod());
				System.out.println("fecha: " + nuevaOperacion.getFecha());
				System.out.println("cantidad agregada: " + nuevaOperacion.getCantidad());
				
				if(miCuenta.agregarOperacion(nuevaOperacion, atrCod)) {
					System.out.println("Operación creada correctamente");
				}else {
					System.out.println("No se pudo crear la operación");
				}
			}else {
				System.out.println("No se pudo realizar la operación");
			}
			
			break;
		case 5:
			if(miCuenta==null) {
				System.out.println("No hay ninguna cuenta agregada");
				break;
			}
			System.out.println("Introduzca la ruta donde quiere guardar el json");
			ruta = sc.nextLine(); 
			if(miCuenta!=null) {
				Gson gson = new Gson();
				String dataJson = gson.toJson(miCuenta);
				ControladorFicheros.writeText(ruta+".json", dataJson, true);
				System.out.println("Cuenta guardada correctamente");
			}else {
				System.out.println("No se pudo guardar la cuenta");
			}
			break;
		case 6:
			System.out.println("Introduzca la ruta de la que quiere cargar el json");
			System.out.println("Atención, los datos que haya introducido y no estén guardados se borrarán");
			System.out.println("Si desea salir introduzca un intro");
			ruta = sc.nextLine();
			
			if(!ruta.isBlank()) {
				String json = ControladorFicheros.readText(ruta);
				
				Gson gson = new Gson();
				miCuenta = gson.fromJson(json, Cuenta.class);
				System.out.println("Cuenta cargada correctamente");
			}
			break;
		case 7:
			if(miCuenta==null) {
				System.out.println("No hay ninguna cuenta agregada");
				break;
			}
			if(miCuenta.getCcc()==null) {
				System.out.println("No hay ninguna cuenta agregada");
				break;
			}
			System.out.println("Introduzca la ruta donde quiere guardar el txt");
			ruta = sc.nextLine();
			PrintWriter out = null;
			
			if(miCuenta!=null) {
				try {
					miFichero = new PrintWriter(new FileWriter(ruta + ".txt"));
					miFichero.println("Datos de la cuenta: ");
					miFichero.println(miCuenta.getCcc()+";"+miCuenta.getFechaApertura()+";"+miCuenta.getSaldo());
					System.out.println(miCuenta.getCcc()+";"+miCuenta.getFechaApertura()+";"+miCuenta.getSaldo());
					miFichero.println("Datos del cliente: ");
					miFichero.println(miCuenta.getCliente().getNombre()+";"+miCuenta.getCliente().getApellidos()+";"+miCuenta.getCliente().getMail());
					System.out.println(miCuenta.getCliente().getNombre()+";"+miCuenta.getCliente().getApellidos()+";"+miCuenta.getCliente().getMail());
					miFichero.println("Datos documento: ");
					miFichero.println(miCuenta.getCliente().getDocumento().getTipo()+";"+miCuenta.getCliente().getDocumento().getNumero());
					System.out.println(miCuenta.getCliente().getDocumento().getTipo()+";"+miCuenta.getCliente().getDocumento().getNumero());
					miFichero.println("Datos de las operaciones: ");
					for(Operacion oper : miCuenta.listaDeOperaciones()) {
						miFichero.println(oper.getCod()+";"+oper.getCantidad()+";"+oper.getFecha());
						System.out.println(oper.getCod()+";"+oper.getCantidad()+";"+oper.getFecha());
					}
					
				}catch(IOException e) {
					System.out.println(e.getMessage());
				}finally{
					miFichero.flush();
					miFichero.close();
				}
				
				
				
			}
			
			break;
		case 8:
			System.out.println("Adios");
			break;
		}	
		}

		
		
		}
}	


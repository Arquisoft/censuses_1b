package es.uniovi.asw.logica;

import es.uniovi.asw.passer.GeneradorPass;
import es.uniovi.asw.passer.impl.HashedGenerator;

public class Votante {
	
	private String nombre;
	private String mail;
	private String nif;
	private String codigoColegio;
	
	
	private String pass;
	
	public Votante(String nombre, String mail, String nif, String codigoColegio){
		this.nombre=nombre;
		this.mail=mail;
		this.nif=nif;
		this.codigoColegio=codigoColegio;
		
		
		
	}

	public String getNombre() {
		return nombre;
	}

	public String getMail() {
		return mail;
	}

	public String getNif() {
		return nif;
	}
	public String getUser() {
		return mail;
	}

	public String getCodigoColegio() {
		return codigoColegio;
	}

	public String getPass() {
		return pass;
	}
	
	public void setPass(String pass) {
		this.pass=pass;
	}

	@Override
	public String toString() {
		return "Votante [nombre=" + nombre + ", mail=" + mail + ", nif=" + nif
				+ ", codigoColegio=" + codigoColegio + "]";
	}

}

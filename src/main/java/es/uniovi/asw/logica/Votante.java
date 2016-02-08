package es.uniovi.asw.logica;

public class Votante {
	
	private String nombre;
	private String mail;
	private String nif;
	private String codigoColegio;
	
	private GeneradorPass g;
	private String pass;
	
	public Votante(String nombre, String mail, String nif, String codigoColegio){
		this.nombre=nombre;
		this.mail=mail;
		this.nif=nif;
		this.codigoColegio=codigoColegio;
		
		g=new SecureRandomGenerator();
		this.pass=g.generar(this);
		
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

	@Override
	public String toString() {
		return "Votante [nombre=" + nombre + ", mail=" + mail + ", nif=" + nif
				+ ", codigoColegio=" + codigoColegio + "]";
	}

}

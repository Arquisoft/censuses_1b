package es.uniovi.asw.BD;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import es.uniovi.asw.logica.Votante;
import es.uniovi.asw.reports.ReportWriter;

/**
 * Clase para interactuar con la base de datos
 *
 */
public class DBUpdate {
	
	private static String DRIVER = "org.hsqldb.jdbcDriver";
	private static String URL = "jdbc:hsqldb:hsql://localhost";
	private static String USER = "sa";
	private static String PASS = "";
	
	private static Connection con;
	
	/**
	 * Método para establecer una conexión con la BD
	 */
	public void conectar () {
		try {
			Class.forName("org.hsqldb.jdbcDriver");
			} catch (ClassNotFoundException e){
				throw new RuntimeException("No se ha podido cargar el driver!", e);
				}
		try {
			con = DriverManager.getConnection(URL, USER, PASS);
		} catch (SQLException e) {
			throw new RuntimeException("No se ha podido establecer la conexión!", e);
		}		
	}

	/**
	 * Método para desconectarse de la BD
	 */
	public void desconectar() {
		if (con != null) 
			try { 
				con.close();
				} catch(SQLException e) {};
	}

	public void insert(Votante v){
		conectar();
		String insertar="insert into ordentrabajo values (?,?,?,?,?)";
		PreparedStatement insercion = null;
		try {
			
		insercion= con.prepareStatement(insertar);
		insercion.setString(1, v.getNombre());
		insercion.setString(2,v.getMail() );
		insercion.setString(3,v.getNif());
		insercion.setString(4, v.getCodigoColegio());
		insercion.setString(5, v.getContraseña());
		insercion.executeUpdate();
		
		
		
	} catch (SQLException e) {
		ReportWriter r = new ReportWriter();
		if(e.getSQLState().equals("08001")){
			try {
				r.WriteReport("No se puede conectar a la base de datos.");
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		else if(e.getSQLState().equals("23505")){
			try {
				r.WriteReport("Ya existe el registro en la base de datos");
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
		}
		else
		{
			try {
				r.WriteReport("Error desconocido en la BD "+e.getMessage());
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		
	}
	
		
	}

}

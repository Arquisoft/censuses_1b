package es.uniovi.asw.BD;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import es.uniovi.asw.logica.Votante;

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
			Class.forName("DRIVER");
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
		
	}

}

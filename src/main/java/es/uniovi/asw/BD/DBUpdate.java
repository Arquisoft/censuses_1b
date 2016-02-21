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
	
	private static String DRIVER_TEST = "com.mysql.jdbc.Driver";
	private static String URL_TEST = "jdbc:mysql://127.0.0.1/censuses_1b";
	private static String USER_TEST = "travis";
	private static String PASS_TEST = "";
	
	private static Connection con;
	
	public void conectar () {
		if(System.getProperty("test_local") == null){
			conectar_hsqldb();
		}
		else{
			conectar_mysql();
		}
	}
	
	/**
	 * Método para establecer una conexión con la BD(hsqldb)
	 */
	public void conectar_hsqldb () {
		try {
			Class.forName(DRIVER);
			} catch (ClassNotFoundException e){
				throw new RuntimeException("No se ha podido cargar el driver.", e);
				}
		try {
			con = DriverManager.getConnection(URL, USER, PASS);
		} catch (SQLException e) {
			throw new RuntimeException("No se ha podido establecer la conexión.", e);
		}		
	}
	
	/**
	 * Método para establecer una conexión con la BD(mysql)
	 */
	public void conectar_mysql () {
		try {
			Class.forName(DRIVER_TEST);
			} catch (ClassNotFoundException e){
				throw new RuntimeException("No se ha podido cargar el driver.", e);
				}
		try {
			con = DriverManager.getConnection(URL_TEST, USER_TEST, PASS_TEST);
		} catch (SQLException e) {
			throw new RuntimeException("No se ha podido establecer la conexión.", e);
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
		//conectar();
		String insertar="insert into usuarios(name, email, nif, censusesInfo, pass) values (?,?,?,?,?)";
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
				r.WriteReport("Ya existe el registro en la base de datos.");
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
		finally {
			try {
				insercion.close();
			} catch (SQLException e) {
				ReportWriter rw = new ReportWriter();
				try {
					rw.WriteReport("Error desconocido en la BD "+e.getMessage());
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		}
	
		
	}
	
	public int  delete (Votante v) {		
		int n = 0;
		String sentencia = "delete from usuarios where nif = ?";
		PreparedStatement ps = null;
			try {
				ps = con.prepareStatement(sentencia);
				ps.setString(1, v.getNif());
				n = ps.executeUpdate();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}		

		finally {
			try {
				ps.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				}
			}
			return n;
		
	}
	
	public boolean exists (Votante v) {		
		String sentencia = "select * from usuarios where nif = ?";
		PreparedStatement ps = null;
		ResultSet rs = null;
			try {
				ps = con.prepareStatement(sentencia);
				ps.setString(1, v.getNif());
				rs = ps.executeQuery();
				if (rs.next())
					return true;
				else
					return false;
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}		

		finally {
			try {
				ps.close();
				rs.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				}
			}
			return false;		
	}

}

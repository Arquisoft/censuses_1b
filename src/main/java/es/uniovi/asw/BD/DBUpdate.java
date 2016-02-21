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

	private static String DRIVER_HSQLDB = "org.hsqldb.jdbcDriver";
	private static String URL_HSQLDB = "jdbc:hsqldb:hsql://localhost";
	private static String USER_HSQLDB = "sa";
	private static String PASS_HSQLDB = "";

	private static String DRIVER_MYSQL = "com.mysql.jdbc.Driver";
	private static String URL_MYSQL = "jdbc:mysql://127.0.0.1/censuses_1b";
	private static String USER_MYSQL = "travis";
	private static String PASS_MYSQL = "";

	private static Connection con;

	public void conectar () {
		if(System.getenv().get("TRAVIS") == null){
			conectarse(DRIVER_HSQLDB, URL_HSQLDB, USER_HSQLDB, PASS_HSQLDB);
		}
		else{
			conectarse(DRIVER_MYSQL, URL_MYSQL, USER_MYSQL, PASS_MYSQL);
		}
	}

	/**
	 * Método para establecer una conexión con la BD
	 */
	protected void conectarse(String driver, String url, String user, String pass) {
		try {
			Class.forName(driver);
		} catch (ClassNotFoundException e){
			throw new RuntimeException("No se ha podido cargar el driver.", e);
		}
		try {
			con = DriverManager.getConnection(url, user, pass);
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
		String insertar="insert into USUARIOS(name, email, nif, censusesInfo, pass) values (?,?,?,?,?)";
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
		String sentencia = "delete from USUARIOS where nif = ?";
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
		String sentencia = "select * from USUARIOS where nif = ?";
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

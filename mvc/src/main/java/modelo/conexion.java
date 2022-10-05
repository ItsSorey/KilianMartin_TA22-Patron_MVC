package modelo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class conexion {
	final String DB_URL = "jdbc:mysql://localhost:3306/"; //url de la BD mas el puerto
	final String USERNAME = "root"; //usuario
	final String PASSWORD = ""; //contraseña
	
	public Connection conexion = null; //establecemos conexion a nulo para impedir errores
	
	public Connection connect(String BD) { //funcion para establecer conexion a la BD
	    try {
	    	conexion = DriverManager.getConnection(DB_URL+BD, USERNAME, PASSWORD);
	    	//System.out.println("Conectado a la BD "+BD); 
	    }catch(SQLException ex) {
	    	//System.out.println("No se ha podido conectar con la base de datos"); 
	    	System.out.println(ex);
	    }
	    return conexion;
	}
	
	public Connection close() { //funcion para cerrar conexion a la BD
		if (conexion!=null) {
			try {
		    	conexion.close();
		    	//System.out.println("BD cerrada correctamente"); 
		    	
		    }catch(SQLException ex) {
		    	//System.out.println("Ha ocurrido un error al cerrar la BD"); 
		    	System.out.println(ex);
		    }
		}
	    return conexion;
	}
}
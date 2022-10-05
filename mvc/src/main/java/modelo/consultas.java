package modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class consultas extends conexion{
	static String BD="Piezas_proveedores";
	
	// Inicialización de tabla (crear tablas, borrándolas si existen) solicitando confirmación ------------------------------
	public String inicializacionTabla() { 
		Connection conexion = connect(BD);
		Statement stmt = null;
		String msg;
		
		try {
			stmt = conexion.createStatement();
			//TABLAS------------------------------
			
			//borramos la tabla
			String sql="drop table IF EXISTS suministra;";
   			stmt.execute(sql); //ejecutamos
   			   			
   			//creamos la tabla Suministra
   			sql="create table Suministra (idPieza int, idProveedor char(4), Precio int, PRIMARY KEY (idPieza,idProveedor), FOREIGN KEY (idPieza) REFERENCES Piezas(idPieza), FOREIGN KEY (idProveedor) REFERENCES Proveedores(idProveedor));";
   			stmt.execute(sql); //ejecutamos
   			msg="Tabla inicializada con exito";
   			
		} catch(SQLException e) { //miramos errores
			msg=e.toString(); //convertimos el error a String
		} finally {
			try {
				conexion.close(); //cerramos conexion
			} catch (SQLException e) { //miramos errores
				msg=e.toString();
			}
		}
		return msg;
	}
	
	//INSERTADO DE PAQUETES DE DATOS AUTOMATICO -----------------------------------------------------------------------------
	public String inserirDatos() { 
		Connection conexion = connect(BD);
		Statement stmt = null;
		String msg;
		try {
			stmt = conexion.createStatement();
			//TABLAS------------------------------
			String sql="INSERT INTO suministra VALUES(1,3,'80');";
   			stmt.execute(sql);
   			sql="INSERT INTO suministra VALUES(2,2,'30');";
   			stmt.execute(sql);
   			sql="INSERT INTO suministra VALUES(3,5,'50');";
   			stmt.execute(sql);
   			sql="INSERT INTO suministra VALUES(4,1,'20');";
   			stmt.execute(sql);
   			sql="INSERT INTO suministra VALUES(5,1,'100');";
   			stmt.execute(sql);
   			
   			msg="Registros Suministra añadidos con exito";
   			
		} catch(SQLException e) { //miramos errores
			msg=e.toString(); //convertimos el error a String
		} finally {
			try {
				conexion.close(); //cerramos conexion
			} catch (SQLException e) { //miramos errores
				msg=e.toString();
			}
		}
		return msg;
	}
			
	// METODO PARA REGISTRAR ------------------------------------------------------------------------------------------------
	public String registrar (suministra suministra) {
		Connection conexion = connect(BD);
		PreparedStatement ps= null;
		String msg;
		
		// ejemplo INSERT INTO suministra VALUES(IdPieza, IdPorveedor, Precio)
		String sql="INSERT INTO suministra VALUES(?,?,?);";
		
		try {
   			ps=conexion.prepareStatement(sql); //insertamos
   			ps.setInt(1, suministra.getIdPieza()); //posicion 1 IdPieza()
   			ps.setString(2, String.valueOf(suministra.getIdProveedor())); //posicion 2 IdPorveedor() doy un String con el 
   			//valor del char IdProveedor, hago esto ya que .setChar no hay
   			ps.setInt(3, suministra.getPrecio()); //posicion 3 Precio
   			ps.execute(); //ejecutamos
   			
   			msg="Suministro registrado correctamente";
   			
		} catch(SQLException e) { //miramos errores
			msg=e.toString(); //convertimos el error a String
		} finally {
			try {
				conexion.close(); //cerramos conexion
			} catch (SQLException e) { //miramos errores
				msg=e.toString();
			}
		}
		return msg;
	}
	
	// METODO PARA MOSTRAR ---------------------------------------------------------------------------------------------------
	public String[][] mostrarTabla() {
		Connection conexion = connect(BD);
		PreparedStatement ps= null;
		int registros=0;
		
		try { //contamos el numero total de registros
   			ps=conexion.prepareStatement("SELECT count(*) as total FROM suministra;"); //insertamos
   			ResultSet rs = ps.executeQuery(); //clase dedicada a BD para pillar los datos
   			rs.next();
   			registros = rs.getInt("total");
   			rs.close();
		}catch(SQLException e) {
			System.err.println(e);
		}
		
		//creamos una matriz en funcion del numero de registros
		String[][] data = new String[registros][3];
		try {
			ps=conexion.prepareStatement("SELECT * FROM suministra;"); //seleccionamos todos los datos
   			ResultSet rs = ps.executeQuery(); 
   			int i=0;
   			
   			while (rs.next()) { //Mientras haya un registro debajo seguirá haciendo bucle (insertando datos en la matriz)
   				data[i][0] = rs.getString("IdPieza");
   				data[i][1] = rs.getString("IdProveedor");
   				data[i][2] = rs.getString("Precio");
   				i++;
   	        }
   			rs.close();
   			
		} catch(SQLException e) { //miramos errores
			System.err.println(e);
		} finally {
			try {
				conexion.close(); //cerramos conexion
			} catch (SQLException e) { //miramos errores
				System.err.println(e);
			}
		}
		return data;
	}
	
	// METODO PARA BUSCAR ----------------------------------------------------------------------------------------------------
	public String[][] buscar(int Precio) {
		Connection conexion = connect(BD);
		PreparedStatement ps= null;
		int registros=0;
		
		try { //contamos el numero total de registros
   			ps=conexion.prepareStatement("SELECT count(*) as total FROM suministra WHERE Precio="+Precio+";"); //insertamos
   			ResultSet rs = ps.executeQuery(); //clase dedicada a BD para pillar los datos
   			rs.next();
   			registros = rs.getInt("total");
   			rs.close();
		}catch(SQLException e) {
			System.err.println(e);
		}
		
		//creamos una matriz en funcion del numero de registros
		String[][] data = new String[registros][3];
		try {
			ps=conexion.prepareStatement("SELECT * FROM suministra WHERE Precio="+Precio+";"); //seleccionamos todos los datos
   			ResultSet rs = ps.executeQuery(); 
   			int i=0;
   			
   			while (rs.next()) { //Mientras haya un registro debajo seguirá haciendo bucle (insertando datos en la matriz)
   				data[i][0] = rs.getString("IdPieza");
   				data[i][1] = rs.getString("IdProveedor");
   				data[i][2] = rs.getString("Precio");
   				i++;
   	        }
   			rs.close();
   			
		} catch(SQLException e) { //miramos errores
			System.err.println(e);
		} finally {
			try {
				conexion.close(); //cerramos conexion
			} catch (SQLException e) { //miramos errores
				System.err.println(e);
			}
		}
		return data;
	}
	
	// METODO PARA ACTUALIZAR ------------------------------------------------------------------------------------------------
	public String actualizar (suministra suministra, int idPieza2, char idProveedor2) {
		Connection conexion = connect(BD);
		PreparedStatement ps= null;
		String msg;
		
		String sql="UPDATE suministra SET idPieza=?, idProveedor=?, Precio=? WHERE idPieza=? AND idProveedor=?;";
		
		try {
   			ps=conexion.prepareStatement(sql); //insertamos
   			ps.setInt(1, suministra.getIdPieza()); //posicion 1 IdPieza()
   			ps.setString(2, String.valueOf(suministra.getIdProveedor())); //posicion 2 IdPorveedor() doy un String con el 
   			//valor del char IdProveedor, hago esto ya que .setChar no hay
   			ps.setInt(3, suministra.getPrecio()); //posicion 3 Precio
   			
   			//Ya que tiene 2 PK y en este caso pueden repetirse indicamos los 2 que queremos cambiar
   			ps.setInt(4, idPieza2); //posicion 4 pieza a la que queremos cambiar
   			ps.setString(5, String.valueOf(idProveedor2));  //con el proveedor
   			
   			ps.executeUpdate(); //ejecutamos
   			msg="Registro actualizado correctamente";
   			
		} catch(SQLException e) { //miramos errores
			msg=e.toString(); //convertimos el error a String
		} finally {
			try {
				conexion.close(); //cerramos conexion
			} catch (SQLException e) { //miramos errores
				msg=e.toString();
			}
		}
		return msg;
	}
	
	// METODO PARA ELIMINAR ------------------------------------------------------------------------------------------------
	public String eliminar (int idPieza2, char idProveedor2) {
		Connection conexion = connect(BD);
		PreparedStatement ps= null;
		String msg;
		
		String sql="DELETE FROM suministra WHERE idPieza=? AND idProveedor=?;";
		
		try {
			ps=conexion.prepareStatement(sql); //insertamos
			
	   		//Ya que tiene 2 PK y en este caso pueden repetirse indicamos los 2 que queremos cambiar
	   		ps.setInt(1, idPieza2); //posicion 3 Precio
	   		ps.setString(2, String.valueOf(idProveedor2)); 
	   		ps.executeUpdate(); //ejecutamos
	   			
	   		msg="Registro eliminado correctamente";
	   	
		} catch(SQLException e) { //miramos errores
			msg=e.toString(); //convertimos el error a String
		} finally {
			try {
				conexion.close(); //cerramos conexion
			} catch (SQLException e) { //miramos errores
				msg=e.toString();
			}
		}
		return msg;
	}
	
	// METODO PARA ELIMINAR LA TABLA ---------------------------------------------------------------------------------------------
	public String borrarTabla() { 
		Connection conexion = connect(BD);
		Statement stmt = null;
		String msg;
		
		try {
			stmt = conexion.createStatement();
			
			//borramos la tabla
			String sql="drop table IF EXISTS suministra;";
   			stmt.execute(sql); //ejecutamos
   			
   			msg="Tabla eliminada correctamente";
   			
		} catch(SQLException e) { //miramos errores
			msg=e.toString(); //convertimos el error a String
		} finally {
			try {
				conexion.close(); //cerramos conexion
			} catch (SQLException e) { //miramos errores
				msg=e.toString();
			}
		}
		return msg;
	}
}

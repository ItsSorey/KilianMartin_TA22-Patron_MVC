package controlador;

import modelo.consultas;
import modelo.suministra;
import vista.vista;

public class controlador {
	public static void main(String[] args) {
		vista vista = new vista();
		consultas consulta = new consultas();
		
		int opc=0, opc2=0;
		do {
			opc = vista.menuPrincipal();
			switch(opc) {
			case 1: // Inicializar Tabla
				vista.msg(consulta.inicializacionTabla()); //enviamos el mensage en vista en funcion de si la consulta ha funcionado o no con un msg
				break;
			case 2: //Insertar datos Automaticos
				vista.msg(consulta.inserirDatos());
				break;
			case 3: //Borrar Tabla
				vista.msg(consulta.borrarTabla());
				break;
			case 4: //CRUD
				do {
					opc2 = vista.menuCRUD();
					switch(opc2) {
					case 1: // Añadir Registro
						vista.msg("Datos del registro:");
						//creo una clase suministra pidiendole datos al usuario
						suministra registrar=new suministra(vista.getIdPieza(),vista.getidProveedor(),vista.getPrecio());
						
						vista.msg(consulta.registrar(registrar)); //segundo paso envio el suministro y lo registro en la BD
						//tercer paso muestro el msg por la vista en funcion de la consulta
						break;
					case 2: //Buscar Registro por Precio
						vista.msg("Inserte el Precio que quiere buscar");
						vista.listarRegistros(consulta.buscar(vista.getPrecio()));
						break;
					case 3: // Listar Registros
						vista.listarRegistros(consulta.mostrarTabla()); //creamos una matriz en consulta y la pasamos a vista y la enseñamos
						break;
					case 4: //Modificar Registro
						vista.msg("Datos nuevos del registro:");
						//creo una clase suministra pidiendole datos al usuario
						suministra modificar=new suministra(vista.getIdPieza(),vista.getidProveedor(),vista.getPrecio());
						
						vista.msg("Registro que quiere modificar:");
						vista.msg(consulta.actualizar(modificar,vista.getIdPieza(),vista.getidProveedor()));
						//pedimos los cambios y modificamos el que quiere
						break;
					case 5: //Borrar Registro
						vista.msg("Registro que quiere eliminar");
						vista.msg(consulta.eliminar(vista.getIdPieza(),vista.getidProveedor())); //pido IdPieza y IdProveedor
						//ya que son 2 PK y los elimino
						break;
					case 6: //Salir
						vista.msg("Has salido del CRUD");
						break;
					}
				}while(opc2!=6);
				break;
			case 5:
				vista.msg("Bye");
				break;
			}
		}while(opc!=5);
		
	}
	
	
}

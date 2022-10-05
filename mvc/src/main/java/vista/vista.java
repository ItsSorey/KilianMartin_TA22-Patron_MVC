package vista;

import java.util.Scanner;

public class vista {
	public int menu;
	public int menuCRUD;
	Scanner teclat = new Scanner(System.in);
	
	//MENUS
	public int menuPrincipal() {
		System.out.println("Menu:\n"
				+ "1. Inicializar Tablas\n"
				+ "2. Insertar datos Automaticos\n"
				+ "3. Borrar Tabla\n"
				+ "4. CRUD\n"
				+ "5. Salir\n");
		
		return menu=teclat.nextInt();
	}
	
	public int menuCRUD() {
		System.out.println("Menu:\n"
				+ "1. Añadir Registro\n"
				+ "2. Buscar Registro\n"
				+ "3. Listar Registros\n"
				+ "4. Actualizar Registro\n"
				+ "5. Borrar Registro\n"
				+ "6. Salir del CRUD\n");
		
		return menuCRUD=teclat.nextInt();
	}
	
	public void msg(String msg) {//MENSAJES
		System.out.println(msg+"\n");
	}
	
	public void listarRegistros(String[][] matriz) { //recorremos la matrix y enseñamos los datos
		System.out.println("IdPieza  IdProveedor  Precio");
		for (int f=0;f<matriz.length;f++) {
			System.out.println("----------------------------");
			for(int c=0;c<matriz[f].length;c++) {
				System.out.print(matriz[f][c]+"          ");  //mostrar contingut de la matriu
			}
			System.out.println();
		}
		System.out.println("----------------------------");
	}
	
	//PEDIMOS LOS DATOS
	public int idPieza,Precio;
	public char idProveedor;
	
	public int getIdPieza() {
		System.out.println("idPieza");
		idPieza = teclat.nextInt();
		return idPieza;
	}
	public int getPrecio() {
		System.out.println("Precio");
		Precio = teclat.nextInt();
		return Precio;
	}
	public char getidProveedor() {
		System.out.println("idProveedor");
		idProveedor = teclat.next().charAt(0);
		return idProveedor;
	}
}

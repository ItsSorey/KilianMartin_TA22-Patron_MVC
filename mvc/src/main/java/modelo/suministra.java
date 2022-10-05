package modelo;

public class suministra {
	//columnas de la BD
	private int idPieza;
	private char idProveedor;
	private int Precio;
	
	//getters y setters
	public suministra(int idPieza, char idProveedor, int Precio) { //constructor entero
		this.idPieza = idPieza;
		this.idProveedor = idProveedor;
		this.Precio = Precio;	
	}
	public int getIdPieza() {return idPieza;}
	public char getIdProveedor() {return idProveedor;}
	public int getPrecio() {return Precio;}
}

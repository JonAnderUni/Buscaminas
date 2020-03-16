package codigo;

public class Casilla {

	private int fila;
	private int columna;
	private int numMinas;
	private Estado estado;

	// Constructora vacia
	public Casilla() {

	}

	public Casilla(int filas, int columnas, int bombas) {
		// Inicializamos las variables con el estado inicial como cerrado
		numMinas = bombas;
		fila = filas;
		columna = columnas;
		estado = new Cerrada();
	}

	
	public void cambiarEstado(String s) {
		/*Cambia el estado de la casilla por el estado actual
		 * Se le tiene que pasar por parametro dcho o derecho, o izq o izquierda como paramentro
		 */
		if(s.equals("dcho") || s.equals("derecho")) {
			if(estado instanceof Bandera)
				estado = new Cerrada();
			else if(estado instanceof Cerrada)
				estado = new Bandera();
		}
		if(s.equals("izq") || s.equals("izquierda")) {
			if(estado instanceof Cerrada)
				estado = new Abierto();
		}
	}

	public boolean esBomba() {
		// Comprueba si la casilla es una bomba
		return this.numMinas == -1;
	}

	public int getNumMinas() {
		// Devuelve el numero de minas que haya en la partid
		return this.numMinas;
	}

	public void incrementarNumMinas() {
		// Aumenta el numero de minas total
		if (this.numMinas != -1) {this.numMinas++;}
		else {}

	}

	public int getFila() {
		return this.fila;
	}

	public int getcolumna() {
		return this.columna;
	}

}
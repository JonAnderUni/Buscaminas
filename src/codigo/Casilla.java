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

	/**
	 * 
	 * @param pEstado
	 */
	public void cambiarEstado(Estado pEstado) {
		//Cambia el estado de la casilla por el estado actual
		estado = pEstado;
	}

	public boolean esBomba() {
		// Comprueba si la casilla es una bomba
		return numMinas == -1;
	}

	public int getNumMinas() {
		// Devuelve el numero de minas que haya en la partid
		return numMinas;
	}

	public void incrementarNumMinas() {
		// Aumenta el numero de minas total
		if (numMinas != -1) {numMinas++;}
		else {}

	}

	public int getFila() {
		return fila;
	}

	public int getcolumna() {
		return columna;
	}
	
	

}
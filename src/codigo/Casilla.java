package codigo;

public class Casilla {

	private int fila;
	private int columna;
	private int numMinas;
	private Estado Estado;

	/**
	 * 
	 * @param pFila
	 * @param pColumna
	 * @param parameter
	 */
	
	//Constructora vacia
	public Casilla() {
		
	}
	
	public Casilla(int filas,int columnas , int bombas) {
		numMinas = bombas;
		fila = filas;
		columna = columnas;
		
	}
	/**
	 * 
	 * @param pEstado
	 */
	public void cambiarEstado(Estado pEstado) {
		// TODO - implement Casilla.cambiarEstado
		throw new UnsupportedOperationException();
	}
	
	public boolean esBomba() {return numMinas == -1;}
	
	public int getNumMinas() {
		return numMinas;
	}
	
	public void incrementarNumMinas() {
		if (numMinas != -1) numMinas++;
		
	}
	
	public int getFila() {return fila;}
	public int getcolumna() {return columna;}

	
}
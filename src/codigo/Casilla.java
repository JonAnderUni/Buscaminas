package codigo;

public class Casilla {

	private int fila;
	private int columna;
	private int numMinas;
	private String codigo;
	private Estado Estado;

	/**
	 * 
	 * @param pFila
	 * @param pColumna
	 * @param parameter
	 */
	public Casilla(int pFila, int pColumna) {
		// TODO - implement Casilla.Casilla
		fila = pFila;
		columna = pColumna;
		codigo = "";
		numMinas = 0;
	}
	
	//Constructora vacia
	public Casilla() {
		codigo = "";
		
	}
	
	public Casilla(int cod,int i , int j) {
		codigo = cod + "";
		fila = i;
		columna = j;
		
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
	
	public String getCodigo() {
		return codigo;
	}
	
	public int getNumMinas() {
		return numMinas;
	}
	
	public void incrementarNumMinas() {
		if (numMinas != -1) numMinas++;
		
	}
	
	public int getFila() {return fila;}
	public int getcolumna() {return columna;}

	
}
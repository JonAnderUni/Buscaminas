package codigo;

public class Casilla {

	private int fila;
	private int columna;
	private int numMinas;
	private Estado Estado;
	private boolean esMina;

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
	}
	
	public void setMina() {
		esMina = true;
	}
	
	//Constructora vacia
	public Casilla() {
		
	}
	public int minasAlrededor() {
		// TODO - implement Casilla.minasAlrededor
		throw new UnsupportedOperationException();
	}

	public boolean esMina() {
		return esMina;
	}

	/**
	 * 
	 * @param pEstado
	 */
	public void cambiarEstado(Estado pEstado) {
		// TODO - implement Casilla.cambiarEstado
		throw new UnsupportedOperationException();
	}

}
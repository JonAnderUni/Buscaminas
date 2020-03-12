package codigo;

public class Tablero {

	private static Tablero mTablero;
	private ListaCasillas listaCasillas;

	private Tablero() {
		// TODO - implement Tablero.Tablero
	}

	public static Tablero getTablero() {
		// TODO - implement Tablero.getTablero
		if(mTablero == null) {
			mTablero = new Tablero();
		}
		return mTablero;
	}

	/**
	 * 
	 * @param pDificultad
	 */
	public void generarTablero(int filas, int columnas, int bombas) {
		// TODO - implement Tablero.generarTablero
		throw new UnsupportedOperationException();
		
	}

}
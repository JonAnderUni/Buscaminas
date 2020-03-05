package codigo;

public class Tablero {

	private static Tablero mTablero;
	private ListaCasillas listaCasillas;

	private Tablero() {
		// TODO - implement Tablero.Tablero
		listaCasillas = new ListaCasillas();
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
	public void generarTablero(int pDificultad) {
		// TODO - implement Tablero.generarTablero
		throw new UnsupportedOperationException();
	}

}
package codigo;

import java.util.concurrent.ThreadLocalRandom;

public class Tablero {

	private static Tablero mTablero;
	private ListaCasillas listaCasillas;
	private Casilla[][] tablero;

	private Tablero() {
		// TODO - implement Tablero.Tablero
	}

	public static Tablero getTablero() {
		// TODO - implement Tablero.getTablero
		if (mTablero == null) {
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
		tablero = new Casilla[filas][columnas];

		// meter casillas normales
		for (int fila = 0; fila < tablero.length; fila++) {
			for (int columna = 0; columna < tablero[0].length; columna++) {
				Casilla casilla = new Casilla(fila, columna, 0);
				tablero[fila][columna] = casilla;
			}
		}

		// anadimos las bombas
		for (int f = 0; f < bombas; f++) {
			boolean exit = false;

			int fila = ThreadLocalRandom.current().nextInt(0, tablero.length - 1);
			int columna = ThreadLocalRandom.current().nextInt(0, tablero[0].length - 1);
			if ((tablero[fila][columna]).getNumMinas() == -1) {

			} else {
				Casilla casilla = new Casilla(fila, columna, -1);
				tablero[fila][columna] = casilla;
				// listaCasillas.anadirCasilla(casilla);
				getMinasAlrededor(fila, columna);
				exit = true;
			}

		}
	}

	private boolean posicionValida(int fila, int columna) {
		if (fila < tablero.length && fila >= 0) {
			if (columna < tablero[0].length && columna >= 0)
				return true;
			else
				return false;
		} else
			return false;
	}

	private void getMinasAlrededor(int fila, int columna) {
		// TODO Auto-generated method stub
		if (posicionValida(fila + 1, columna)) {
			(tablero[fila + 1][columna]).incrementarNumMinas();
		}
		if (posicionValida(fila + 1, columna - 1)) {
			(tablero[fila + 1][columna - 1]).incrementarNumMinas();
		}
		if (posicionValida(fila + 1, columna + 1)) {
			(tablero[fila + 1][columna + 1]).incrementarNumMinas();
		}
		if (posicionValida(fila - 1, columna)) {
			(tablero[fila - 1][columna]).incrementarNumMinas();
		}
		if (posicionValida(fila - 1, columna - 1)) {
			(tablero[fila - 1][columna - 1]).incrementarNumMinas();
		}
		if (posicionValida(fila - 1, columna + 1)) {
			(tablero[fila - 1][columna + 1]).incrementarNumMinas();
		}
		if (posicionValida(fila, columna + 1)) {
			(tablero[fila][columna + 1]).incrementarNumMinas();
		}
		if (posicionValida(fila, columna - 1)) {
			(tablero[fila][columna - 1]).incrementarNumMinas();
		}
	}

	public void imprimirTablero() {

		for (int i = 0; i < tablero.length; i++) {
			System.out.println("");
			for (int j = 0; j < tablero[0].length; j++) {
				System.out.print((tablero[i][j]).getNumMinas() + "  ");
			}
		}

	}
}
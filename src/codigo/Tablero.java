package codigo;

import java.util.concurrent.ThreadLocalRandom;

import javax.print.attribute.HashAttributeSet;

public class Tablero {

	private static Tablero mTablero;
	private ListaCasillas listaCasillas;
	private ListaCasillas listaBombas;
	private Casilla[][] tablero;

	/*
	 * Para las casillas normales utilizaremos como clave cod = c + fila + columna
	 * para las bombas utilizaremos como clave cod = b + fila + columna;
	 */

	private Tablero() {
		// TODO - implement Tablero.Tablero
		listaCasillas = new ListaCasillas();
		listaBombas = new ListaCasillas();
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
		listaCasillas = new ListaCasillas();
		listaBombas = new ListaCasillas();
		// meter casillas normales
		for (int fila = 0; fila < tablero.length; fila++) {
			for (int columna = 0; columna < tablero[0].length; columna++) {
				Casilla casilla = new Casilla(fila, columna, 0);
				tablero[fila][columna] = casilla;
				listaCasillas.anadirCasilla(fila + "" + columna + "", casilla);
			}
		}

		// anadimos las bombas
		// anadimos las bombas

		/*
		 * Al añadir las bombas hay q tener en cuenta que no se añada una encima de la
		 * otra a la hora de incrementar el contador se incrementaria x2
		 * 
		 */
		for (int f = 0; f < bombas; f++) {

			Casilla casilla = listaCasillas.getCasillaAleatoria();
			Casilla bomba = new Casilla(casilla.getFila(), casilla.getcolumna(), -1);
			tablero[casilla.getFila()][casilla.getcolumna()] = bomba;
			listaBombas.anadirCasilla(casilla.getFila() + "" + casilla.getcolumna() + "", casilla);
			listaCasillas.eliminarCasillla(casilla.getFila() + "" + casilla.getcolumna() + "");
			getMinasAlrededor(casilla.getFila(), casilla.getcolumna());

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

	// Para las pruebas del Main
	public void imprimirTablero() {

		for (int i = 0; i < tablero.length; i++) {
			System.out.println("");
			for (int j = 0; j < tablero[0].length; j++) {
				System.out.print((tablero[i][j]).getNumMinas() + "  ");
			}
		}

	}

	public int getNumBombas() {
		return listaBombas.size();
	}

	public int getNumPos(int fila, int columna) {
		return (tablero[fila][columna]).getNumMinas();
	}

}
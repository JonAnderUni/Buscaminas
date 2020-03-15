package codigo;

import java.util.concurrent.ThreadLocalRandom;

public class Tablero {

	private static Tablero mTablero;
	private ListaCasillas listaCasillas;
	private ListaCasillas listaBombas;
	private Casilla[][] tablero;

	private String cod;
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
		// meter casillas normales
		for (int fila = 0; fila < tablero.length; fila++) {
			for (int columna = 0; columna < tablero[0].length; columna++) {
				try {
					Casilla casilla = new Casilla(fila, columna, 0);
					String aa = "c" + fila + columna + "";

					tablero[fila][columna] = casilla;
					listaCasillas.anadirCasilla(aa, casilla);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}

		// anadimos las bombas
		
		/*Al añadir las bombas hay q tener en cuenta que no se añada una encima de la otra
		 * a la hora de incrementar el contador se incrementaria x2
		 * 
		 * */
		for (int f = 0; f < bombas; f++) {
			int fila = ThreadLocalRandom.current().nextInt(0, tablero.length - 1);
			int columna = ThreadLocalRandom.current().nextInt(0, tablero[0].length - 1);
			if ((tablero[fila][columna]).getNumMinas() == -1) {
				Casilla casilla = listaCasillas.getCasillaAleatoria();
				fila = casilla.getFila();
				columna = casilla.getcolumna();
				listaCasillas.eliminarCasillla("c" + fila + columna + "");
				tablero[fila][columna] = new Casilla(fila, columna, -1);
				listaBombas.anadirCasilla("b" + fila + columna + "", casilla);
				getMinasAlrededor(fila, columna);
			} else {
				Casilla casilla = new Casilla(fila, columna, -1);
				tablero[fila][columna] = casilla;
				listaBombas.anadirCasilla("b" + fila + columna + "", casilla);
				getMinasAlrededor(fila, columna);
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

	public int getNumBombas() {
		return listaBombas.size();
	}
}
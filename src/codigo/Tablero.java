package codigo;

import java.util.LinkedList;
import java.util.Queue;


public class Tablero {

	private static Tablero mTablero;
	private ListaCasillas listaCasillas;
	private ListaCasillas listaBombas;
	private Casilla[][] tablero;

	private Tablero() {	}

	public static Tablero getTablero() {
			if (mTablero == null) {
			mTablero = new Tablero();
		}
		return mTablero;
	}


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
		 * Al a�adir las bombas hay q tener en cuenta que no se a�ada una encima de la
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

	public boolean destaparCasillas(int fila, int columna) {
		
		Queue<Casilla> porVisitar = new LinkedList<Casilla>();	// Creamos la lista de casillas por visitar, para ver si hay que abrirlas o no
		Boolean fin = false;									// Booleano para saber, si la casilla clicada era una mina
		Casilla act = tablero[fila][columna];					// Buscamos la casilla en el tablero
		listaCasillas = new ListaCasillas();
		listaCasillas.anadirCasilla(act.getFila() + "" + act.getcolumna() + "", act);
		//act.cambiarEstado(new Abierto());						// Abrimos la casilla
		
		if (act.esBomba()){	// Si la casilla abierta es una bomba, se termina el juego
			fin = true;
		}
		else {	// Sino es bomba comprobamos si al rededor:  1.La casilla esta en el tablero -- 2.Si es una bomba -- 3.Si la casilla actual no tiene bombas al rededor
			if (posicionValida(fila + 1, columna) && !tablero[fila + 1][columna].esBomba() && tablero[fila][columna].getNumMinas()==0 && tablero[fila + 1][columna].getEstado()==2) {
				porVisitar.add(tablero[fila + 1][columna]);
			}
			if (posicionValida(fila + 1, columna - 1) && !tablero[fila + 1][columna - 1].esBomba() && tablero[fila][columna].getNumMinas()==0 && tablero[fila + 1][columna - 1].getEstado()==2) {
				porVisitar.add(tablero[fila + 1][columna - 1]);
			}
			if (posicionValida(fila + 1, columna + 1) && !tablero[fila + 1][columna + 1].esBomba() && tablero[fila][columna].getNumMinas()==0 && tablero[fila + 1][columna + 1].getEstado()==2) {
				porVisitar.add(tablero[fila + 1][columna + 1]);
			}
			if (posicionValida(fila - 1, columna) && !tablero[fila - 1][columna].esBomba() && tablero[fila][columna].getNumMinas()==0 && tablero[fila - 1][columna].getEstado()==2) {
				porVisitar.add(tablero[fila - 1][columna]);
			}
			if (posicionValida(fila - 1, columna - 1) && !tablero[fila - 1][columna - 1].esBomba() && tablero[fila][columna].getNumMinas()==0 && tablero[fila - 1][columna - 1].getEstado()==2) {
				porVisitar.add(tablero[fila - 1][columna - 1]);
			}
			if (posicionValida(fila - 1, columna + 1) && !tablero[fila - 1][columna + 1].esBomba() && tablero[fila][columna].getNumMinas()==0 && tablero[fila - 1][columna + 1].getEstado()==2) {
				porVisitar.add(tablero[fila - 1][columna + 1]);
			}
			if (posicionValida(fila, columna + 1) && !tablero[fila][columna + 1].esBomba() && tablero[fila][columna].getNumMinas()==0 && tablero[fila][columna + 1].getEstado()==2) {
				porVisitar.add(tablero[fila][columna + 1]);
			}
			if (posicionValida(fila, columna - 1) && !tablero[fila][columna - 1].esBomba() && tablero[fila][columna].getNumMinas()==0  && tablero[fila][columna - 1].getEstado()==2) {
				porVisitar.add(tablero[fila][columna - 1]);
			}
			// Una vez comprobadas las casillas de al rededor, buscaremos todas las casillas que no tengan minas al rededor, contiguas a la original
			while (!porVisitar.isEmpty()) {
				act = porVisitar.remove();		// Sacamos la casilla de la lista de pendientes
				listaCasillas.anadirCasilla(act.getFila() + "" + act.getcolumna() + "", act);
				act.cambiarEstado(new Abierto());		// La abrimos
				fila = act.getFila();			// Cogemos su fila
				columna = act.getcolumna();		// Cogemos su columna
				
				if (posicionValida(fila + 1, columna) && !tablero[fila +1 ][columna].esBomba() && tablero[fila][columna].getNumMinas()==0 && tablero[fila + 1][columna].getEstado()==2 && !listaCasillas.estaCasilla(act.getFila() + "" + act.getcolumna() + "")) {
					porVisitar.add(tablero[fila + 1][columna]);
				}
				if (posicionValida(fila + 1, columna - 1) && !tablero[fila + 1][columna - 1].esBomba() && tablero[fila][columna].getNumMinas()==0 && tablero[fila + 1][columna - 1].getEstado()==2 && !listaCasillas.estaCasilla(act.getFila() + "" + act.getcolumna() + "")) {
					porVisitar.add(tablero[fila + 1][columna - 1]);
				}
				if (posicionValida(fila + 1, columna + 1) && !tablero[fila + 1][columna + 1].esBomba() && tablero[fila][columna].getNumMinas()==0  && tablero[fila + 1][columna + 1].getEstado()==2&& !listaCasillas.estaCasilla(act.getFila() + "" + act.getcolumna() + "")) {
					porVisitar.add(tablero[fila + 1][columna + 1]);
				}
				if (posicionValida(fila - 1, columna) && !tablero[fila - 1][columna].esBomba() && tablero[fila][columna].getNumMinas()==0  && tablero[fila - 1][columna].getEstado()==2 && !listaCasillas.estaCasilla(act.getFila() + "" + act.getcolumna() + "")) {
					porVisitar.add(tablero[fila - 1][columna]);
				}
				if (posicionValida(fila - 1, columna - 1) && !tablero[fila - 1][columna - 1].esBomba() && tablero[fila][columna].getNumMinas()==0  && tablero[fila - 1][columna - 1].getEstado()==2&& !listaCasillas.estaCasilla(act.getFila() + "" + act.getcolumna() + "")) {
					porVisitar.add(tablero[fila - 1][columna - 1]);
				}
				if (posicionValida(fila - 1, columna + 1) && !tablero[fila - 1][columna + 1].esBomba() && tablero[fila][columna].getNumMinas()==0  && tablero[fila - 1][columna + 1].getEstado()==2&& !listaCasillas.estaCasilla(act.getFila() + "" + act.getcolumna() + "")) {
					porVisitar.add(tablero[fila - 1][columna + 1]);
				}
				if (posicionValida(fila, columna + 1) && !tablero[fila][columna + 1].esBomba() && tablero[fila][columna].getNumMinas()==0  && tablero[fila][columna + 1].getEstado()==2 && !listaCasillas.estaCasilla(act.getFila() + "" + act.getcolumna() + "")) {
					porVisitar.add(tablero[fila][columna + 1]);
				}
				if (posicionValida(fila, columna - 1) && !tablero[fila][columna - 1].esBomba() && tablero[fila][columna].getNumMinas()==0  && tablero[fila][columna - 1].getEstado()==2&& !listaCasillas.estaCasilla(act.getFila() + "" + act.getcolumna() + "")) {
					porVisitar.add(tablero[fila][columna - 1]);
				}
			}
		}
		return fin;
	}
	public int getNumPos(int fila, int columna) {
		return (tablero[fila][columna]).getNumMinas();
	}

	public Casilla getCasilla(int fila, int columna) {
		return tablero[fila][columna];
	}
	
	public Casilla[][] getMatriz() {
		return tablero;
	}
		
}
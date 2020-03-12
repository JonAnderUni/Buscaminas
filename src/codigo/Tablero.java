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
		tablero = new Casilla[filas][columnas];
		//meter bombas		
		for (int fila = 0; fila<tablero.length;fila++) {
			for(int columna = 0; columna<tablero[0].length;columna++) {
				Casilla casilla = new Casilla(fila,columna);
				tablero[fila][columna] = casilla;
			}
		}	
			
		//anadimos las bombas
		for (int f = 0; f<bombas;f++) {
			int fila =  ThreadLocalRandom.current().nextInt(0,tablero.length-1);
			int columna =  ThreadLocalRandom.current().nextInt(0,tablero[0].length-1); 
			tablero[fila][columna] = new Casilla(f,fila,columna);
			getMinasAlrededor(fila, columna);
				
		}	
	}

	private boolean posicionValida(int fila, int columna) {
		if(fila<tablero.length && fila >=0) {
			if(columna<tablero[0].length && columna>=0) return true;
			else return false;
		}else return false;
	}

	private void getMinasAlrededor(int fila, int columna) {
		// TODO Auto-generated method stub
			
		if((tablero[fila +1][columna]).getNumMinas() != -1 && posicionValida(fila, columna)) (tablero[fila+1][columna]).incrementarNumMinas();
		if((tablero[fila +1][columna-1]).getNumMinas() != -1 && posicionValida(fila, columna)) (tablero[fila+1][columna-1]).incrementarNumMinas();
		if((tablero[fila +1][columna+1]).getNumMinas() != -1 && posicionValida(fila, columna))(tablero[fila+1][columna+1]).incrementarNumMinas();
		if((tablero[fila-1][columna]).getNumMinas() != -1 && posicionValida(fila, columna))  (tablero[fila-1][columna]).incrementarNumMinas();
		if((tablero[fila-1][columna-1]).getNumMinas() != -1 && posicionValida(fila, columna)) (tablero[fila-1][columna-1]).incrementarNumMinas();
		if((tablero[fila-1][columna+1]).getNumMinas() != -1 && posicionValida(fila, columna)) (tablero[fila-1][columna+1]).incrementarNumMinas();
		if((tablero[fila][columna+1]).getNumMinas() != -1 && posicionValida(fila, columna)) (tablero[fila][columna+1]).incrementarNumMinas();
		if((tablero[fila][columna-1]).getNumMinas() != -1 && posicionValida(fila, columna)) (tablero[fila][columna-1]).incrementarNumMinas();
			
	}
}
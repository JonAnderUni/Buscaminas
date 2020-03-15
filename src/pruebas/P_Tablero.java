package pruebas;

import codigo.Tablero;

public class P_Tablero {

	public static void main(String[] args) {
		
		System.out.println("Prueba para ver si se han generado bien las casillas con el mumero de bombas");
		Tablero tablero = Tablero.getTablero();
		tablero.generarTablero(12, 12, 12*12);
		tablero.imprimirTablero();

	}

}

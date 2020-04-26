package codigo;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Scanner;

public class Puntuacion {

	private static Puntuacion miPuntuacion = new Puntuacion();
	private ArrayList<String> datos;

	private Puntuacion() {
		datos = new ArrayList<String>();

	}

	public static Puntuacion getPuntuacion() {
		return miPuntuacion;

	}

	// metodo guardar datos en el fichero
	public String[] leerdatosFichero() {

		try {
			Scanner input = new Scanner(new File("bd/bd.txt"));

			while (input.hasNextLine()) {
				String line = input.nextLine();
				datos.add(line);
			}
			input.close();
		} catch (FileNotFoundException ex) {
			ex.printStackTrace();
		}

		// Podemos ordenar el array por puntuacion

		String[] array = datos.toArray(new String[datos.size()]);
		return array;

	}

	// metodo para leer los datos del fichero
	public void guardarFichero(String jugador, String nivel, int tiempo, int puntuacion) {

		String[] array = leerdatosFichero();
		boolean insertado = false;
		FileWriter fichero = null;
		PrintWriter pw = null;
		int cont = 0;
		try {
			fichero = new FileWriter("bd/bd.txt");
			pw = new PrintWriter(fichero);

			for (int i = 0; i < array.length; i++) {

				String[] a = array[i].split("\\s+--->\\s+");
				int o = Integer.parseInt(a[2]);

				if (o < puntuacion && !insertado) {

					pw.print(jugador + " ---> " + nivel + " ---> " + tiempo + " ---> " + puntuacion);
					pw.println();
					insertado = true;
					cont = i;
					break;

				} else {
					pw.print(a[0] + " ---> " + a[1] + " ---> " + a[2] + " ---> " + a[3]);
					pw.println();
				}

			}

			if (!insertado) {
				pw.print(jugador + " ---> " + nivel + " ---> " + tiempo + " ---> " + puntuacion);
				pw.println();
			} else {
				for (int i = cont; cont < array.length; i++) {
					String[] a = array[i].split("\\s+--->\\s+");
					pw.print(a[0] + " ---> " + a[1] + " ---> " + a[2] + " ---> " + a[3]);
					pw.println();

				}
			}
		} catch (Exception e) {

		} finally {
			try {
				if (null != fichero) {
					fichero.close();
					pw.close();
				}
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
	}

	public static void main(String[] args) {

		Puntuacion.miPuntuacion.guardarFichero("Alain", "m", 120, 2280);
	}
}

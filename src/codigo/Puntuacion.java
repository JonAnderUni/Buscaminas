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

	public static Puntuacion miPuntuacion() {
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
	public void guardarFichero(String nivel, String jugador, int puntuacion) {

		String[] array = leerdatosFichero();
		String[] nuevo = new String[array.length + 1];
		int i2 = 0;
		boolean insertado = false;

		for (int i = 0; i < array.length; i++) {

			try {
				String[] a = array[i].split("\\s+--->\\s+");
				int o = Integer.parseInt(a[2]);

				if (o < puntuacion && !insertado) {

					nuevo[i2] = nivel + " ---> " + jugador + " ---> " + puntuacion + "";
					nuevo[i2 + 1] = array[i];
					i2 = i2 + 2;
					insertado = true;

				} else {
					nuevo[i2] = array[i];
					i2++;
				}
			} catch (Exception e) {

				nuevo[0] = nivel + " ---> " + jugador + " ---> " + puntuacion + "";
				insertado = true;
				break;
			}
		}

		if (!insertado)
			nuevo[nuevo.length - 1] = nivel + " ---> " + jugador + " ---> " + puntuacion + "";

		FileWriter fichero = null;
		PrintWriter pw = null;

		try {
			fichero = new FileWriter("bd/bd.txt");
			pw = new PrintWriter(fichero);

			for (String data : nuevo) {
				pw.print(data);
				pw.println();

			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (null != fichero)
					fichero.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
	}
}

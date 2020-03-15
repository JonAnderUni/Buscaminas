package codigo;

import java.util.Collection;
import java.util.HashMap;
import java.util.concurrent.ThreadLocalRandom;

public class ListaCasillas {

	private HashMap<String, Casilla> lista;
	public ListaCasillas() {
		lista = new HashMap<String, Casilla>();
	}

	public void anadirCasilla(String cod, Casilla casilla) {
	
		lista.put(cod, casilla);
	}

	public void eliminarCasillla(String cod) {

		if (!lista.isEmpty()) {
			lista.remove(cod);
		}
	}

	public Casilla getCasillaAleatoria() {
		Collection<Casilla> values = lista.values();
		Casilla[] targetArray = values.toArray(new Casilla[values.size()]);
		int columna = ThreadLocalRandom.current().nextInt(0, lista.size() - 1);
		return targetArray[columna];
	}

	public int size() {
		return lista.size();
	}
}
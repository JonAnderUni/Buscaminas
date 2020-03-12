package codigo;

import java.util.HashMap;

public class ListaCasillas {

	private HashMap<String, Casilla> lista;

	public ListaCasillas() {
		lista = new HashMap<String, Casilla>();
	}

	public Casilla getCasilla(String codigo) {
		return lista.get(codigo);
	}

	public void anadirCasilla(Casilla casilla) {

		String codigo;
		if (!lista.isEmpty()) {
			if (!lista.containsKey(casilla.getCodigo())) {
				codigo = lista.size() + "";
				lista.put(codigo, casilla);
			}
		}
	}

	public void eliminarCasillla(Casilla casilla) {
		lista.remove(casilla.getCodigo());
	}
}
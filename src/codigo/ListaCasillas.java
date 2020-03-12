package codigo;

public class ListaCasillas {

	private Casilla[] lista;

	public ListaCasillas(int i) {
		lista = new Casilla[i];
	}

	public Casilla getCasilla(int  pos) {
		return lista[pos];
	}

	public void anadirCasilla(Casilla casilla) {
		int i =0;
		boolean salir=false;
		while(i<lista.length && !salir) { //Buscamos la ultima posicion libre y una vez encontradad se añade la casilla, si no, no pasa nada
			if(lista[i]==null) {
				lista[i]=casilla;
				salir=true;
			}else {
				i++;
			}
		}
	}
	private int buscarPos(Casilla casilla) {
		int i=0;
		boolean salir=false;
		while(!salir && i<lista.length) { //Buscamos la posicion de la casilla a buscar
			if(lista[i]==casilla) {
				salir=true;
			}else {
				i++;
			}
		}
		if(!salir) {
			i=-1;
		}
		return i;
	}
	private Casilla[] desplazarIzq(int pos,int length) {
		Casilla[] aux=new Casilla[length-1];
		for(int nAux=0;nAux<pos;nAux++) { //Introducimos en la lista auxiliar todas las casillas anteriores a la pos a eliminar
			aux[nAux]=this.lista[nAux];
		}
		for(int nAux2=pos;nAux2<length-1;nAux2++) { //Introducimos en la lista auxiliar todas las casillas posteriores a la pos a eliminar
			aux[nAux2]=this.lista[nAux2];
		}
		return aux;
	}
	
	public void eliminarCasillla(Casilla casilla) {
		int pos=this.buscarPos(casilla);
		this.lista=desplazarIzq(pos,this.lista.length);	
	}
}
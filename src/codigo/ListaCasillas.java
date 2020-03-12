package codigo;

import java.util.Random;

public class ListaCasillas {

	private Casilla[][] lista;
	
	public static void main(String[] args) {
		ListaCasillas listaCasilas = new ListaCasillas(3, 3, 3);
		System.out.println(listaCasilas);
		System.out.println("Minas en 1,1: " + listaCasilas.getMinasAlrededor(1, 1));
	}
	
	
	
	/* @param pX: número de filas que hay que crear
	 *        pY: número de columnas que hay que crear 
	*
	*/
	public ListaCasillas(int pX, int pY, int pNumBombas) {
		lista = new Casilla[pX][pY];
		int numBombas = pNumBombas;
		Random random = new Random();
		
		
		for (int i = 0; i < pX; i++) {
			for (int j = 0; j < pY; j++) {
				
				lista[i][j] = new Casilla(i,j);
			}
		}
		
		while(numBombas > 0) {
			int x = random.nextInt(pX);
			int y = random.nextInt(pY);
			
			if(!lista[x][y].esMina()) {
				lista[x][y].setMina();
				numBombas--;
			}
		}
	}
	
	public int getMinasAlrededor(int pX, int pY) {
		
		int numMinasAlrededor = 0;
		
		if(pX-1>0 && pY-1 > 0) {
			if(lista[pX-1][pY].esMina())
				numMinasAlrededor++;
			if(lista[pX][pY-1].esMina())
				numMinasAlrededor++;
			if(lista[pX-1][pY-1].esMina())
				numMinasAlrededor++;
		}else if(pX-1>0) {
			if(lista[pX-1][pY].esMina())
				numMinasAlrededor++;
		}else {
			if(lista[pX][pY-1].esMina())
				numMinasAlrededor++;
		}
		
		if(pX+1<lista.length && pY+1<lista[pY].length) {
			if(lista[pX+1][pY].esMina())
				numMinasAlrededor++;
			if(lista[pX][pY+1].esMina())
				numMinasAlrededor++;
			if(lista[pX+1][pY+1].esMina())
				numMinasAlrededor++;
		}else if(pX+1<lista.length) {
			if(lista[pX+1][pY].esMina())
				numMinasAlrededor++;
		}else {
			if(lista[pX][pY+1].esMina())
				numMinasAlrededor++;
		}
		
		
		
		return numMinasAlrededor;
	}
	
	@Override
	public String toString() {
		String s = new String();
		
		for (int i = 0; i < lista.length; i++) {
			for (int j = 0; j < lista[i].length; j++) {
				if(lista[i][j].esMina()) {
					s+="\tX";
				}else {
					s+="\tO";
				}
			}
			s+="\n";
		}
		
		return s;
		
	}
	
	public int getNumBombas() {
		int num=0;
		for (int i = 0; i < lista.length; i++) {
			for (int j = 0; j < lista[i].length; j++) {
				if(lista[i][j].esMina())
					num++;
			}
		}
		
		return num;
	}
}
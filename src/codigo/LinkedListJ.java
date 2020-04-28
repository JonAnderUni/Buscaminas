package codigo;

public class LinkedListJ<T> {

	private Node<T> first;
	private T data;
	private int count;

	public LinkedListJ() {
		first = null;
		data = null;
		count = 0;
	}

	public void removeFirst() {

		if (!isEmpty()) {
			first = first.next;
		}
	}

	public void anadirNuevo(String datos) {
		
		if(isEmpty()) {
			Node nuevo = new Node(datos);
			first = nuevo;
			nuevo.next = null;	
		}else {
			Node act = first.next;
			Node ant = first;
			boolean salir = false;
			
			String[] linea2 = datos.split("\\+s--->\\+s");
			while(act != null && !salir) {
				String[] linea = ((String) act.data).split("\\+s--->\\+s");
				if(Integer.parseInt(linea[2]) < Integer.parseInt(linea2[2])) {
					//Añadimos antes
					Node<String> nuevo = new Node(datos);
					ant.next = nuevo;
					nuevo.next = act;
					salir = true;
					
				}else {
					ant = act;
					act = act.next;
				}
					
			}//while
			
			if(!salir) {
				Node<String> nuevo = new Node(datos);
				ant.next = nuevo;
				nuevo.next = null;
			}
		}

	}

	
	private boolean isEmpty() {
		return first == null;
	}
	
	public Node<T> getFirst() {
		return first;
	}
}

import aed.fifo.FIFO;
import aed.fifo.FIFOList;
import aed.lifo.LIFO;
import aed.lifo.LIFOList;
import aed.positionlist.NodePositionList;
import aed.positionlist.PositionList;
import net.datastructures.Entry;
import net.datastructures.HashTableMap;
import net.datastructures.Map;
import net.datastructures.Position;
import net.datastructures.PriorityQueue;
import net.datastructures.SortedListPriorityQueue;
import net.datastructures.TreeNode;

	public class Test {
		public static void main(String[] args) {
			
			//Creamos una lista
			PositionList<String> lista2  = new NodePositionList<>();
			
			//Añadimos elementos a la lista
			for(int i=0;i<8;i++){
				lista2.addLast("Elemento "+i);
			}
			
			//Comprobamos el tamaño de la lista con size() si el tamaño es distinto a 
			//6 añadimos en la primera posicion un elemento
			if(lista2.size()!=4){
			lista2.addFirst("El primero");
			}
			
			//Sacamos el primer elemento de la lista con Position
			aed.positionlist.Position<String> pos=lista2.first();
			//Mostramos cada elemento y recorremos la lista con next
			for(int i=0;i<lista2.size();i++){
				System.out.println(pos.element());
				pos=lista2.next(pos);
			}
			//Cogemos el ultimo elemento y reccoremos la lista hacia atras
			pos=lista2.last();
			for(int i=0;i<lista2.size();i++){
				System.out.println(pos.element()+" al reves");
				pos=lista2.prev(pos);
			}
//			lista2.addLast(new Cosa(1, "Hola"));
//			lista2.addLast(new Cosa(2, "Mundo"));
//			lista2.addLast(new Cosa(3, "Madrid,Pamplona,BARCELONA,Murcia,Valencia,Montegancedo,UPM"));
//			lista2.addLast(new Cosa(4, "Que"));
//			lista2.addLast(new Cosa(5, "Tal"));
//			lista2.addLast(new Cosa(6, "Estas"));
//			lista2.addLast(new Cosa(10, "hola")); 
//			lista2.addLast(new Cosa(20, "adiosola")); 
			
			PositionList<Character> lista3  = new NodePositionList<>();
			lista3.addLast('a');
			lista3.addLast('b');

			
			
			//Creamos la cola FIFO
			FIFO<String> fifo = new FIFOList<String>();
			
			//Comprobamos que la cola esta vacia y si es asi insertamos un elemento
			if(fifo.isEmpty()){
				fifo.enqueue("Primero");
			}
			//Insertamos varios elementos
			for(int i=0;i<5;i++){
				fifo.enqueue("Elemento "+i);
			}
			//Sacamos el tamaño de la lista
			int tam=fifo.size();
			
			//Imprimimos los valores a medida que sacamos elementos de la cola
			for(int i=0;i<tam;i++){
				System.out.println(fifo.first());
				fifo.dequeue();
			}
			
			//Creamos la cola LIFO
			LIFO<String> lifo = new LIFOList<String>();
			//Comprobamos que la cola esta vacia y si es asi insertamos un elemento
			if(lifo.isEmpty()){
				lifo.push("Primero");
			}
			//Insertamos varios elementos
			for(int i=0;i<4;i++){
				lifo.push("Elemento :"+i);
			}
			//Sacamos el tamaño de la lista
			int tam2=lifo.size();
			
			//Imprimimos los valores a medida que sacamos elementos de la cola
			for(int i=0;i<tam2;i++){
				System.out.println(lifo.top());
				lifo.pop();
			}
			
			//Inicializamos el Map
			Map<String,String> map = new HashTableMap();
			//Añadimos unos cuantos elementos
			map.put("0", "Soy el primero");
			for(int i=1;i<6;i++){
				map.put(""+i+"", "Elemento"+i*1.5);
			}
			//Sacamaos elementos de la lista
			for(int i=0;i<5;i++){
				System.out.println(map.remove(""+i+""));
			}
			
			
			//Inicializamos la cola y añadimos unos cuantos pares de clave - valor
			PriorityQueue<String,String> pq = new SortedListPriorityQueue<>(); 
			pq.insert("1", "Melon");  
			pq.insert("3", "Verano"); 
			pq.insert("4", "Piscina"); 
			pq.insert("11", "Playa"); 
			pq.insert("12", "Agua"); 
			pq.insert("3", "Vacaciones");
			
			//Recorremos la lista hasta que se vacie y sacamos el valor de mayor prioridad de la cola
			while(!pq.isEmpty()){
			Entry<String, String> val=pq.removeMin();
			System.out.println(val.getKey()+" - "+val.getValue());
			}
			
//			pq.insert("5", "Sol"); 
//			pq.insert("2", "Sandia");
//			pq.insert("2", "Calor"); 
//			pq.insert("6", "VIAJAR"); 
//			pq.insert("7", "Corto"); 
//			pq.insert("81", "Examen"); 
//			
			AEDLinkedTree<String> tree = new AEDLinkedTree<>(); 
			Position<String> root = tree.addRoot("5");
			
			Position<String> hijo11 = tree.addChild("3", root); 
			Position<String> hijo12 = tree.addChild("7", root);
			Position<String> hijo13 = tree.addChild("8", root);
			
			Position<String> hijo111 = tree.addChild("2", hijo11); 
			Position<String> hijo112 = tree.addChild("3", hijo11); 

			Position<String> hijo31 = tree.addChild("1", hijo13); 
			Position<String> hijo32 = tree.addChild("4", hijo13); 
			Position<String> hijo33 = tree.addChild("9", hijo13); 
			
			Position<String> hijo311 = tree.addChild("8", hijo31); 
			Position<String> hijo312 = tree.addChild("5", hijo31); 
			Position<String> hijo313 = tree.addChild("2", hijo311); 
			Position<String> hijo314 = tree.addChild("1", hijo311);
			Position<String> hijo315 = tree.addChild("4", hijo311);
			Position<String> hijo316 = tree.addChild("9", hijo311);
			Position<String> hijo317 = tree.addChild("5", hijo311);
			Position<String> hijo318 = tree.addChild("1", hijo311);			
 			System.out.println("");
		}
	}

class Cosa {
	private Integer dato = 10; 
	private String p= "cosica";
	private Otro otro = new Otro ();   
	
	public Cosa(Integer dato, String p) {
		super();
		this.dato = dato;
		this.p = p;
		if(dato==3){
			otro.setOtro("Tomate,lechuga,huevos,Pizza,Refrescos,Jamon,Queso");
		}
	} 
	
}

class Otro {
	String hola;
	String adios;
	public Otro(){
	hola = "90"; 
	adios = "100";
	}
	
	public void setOtro(String param){
		adios=param;
	}
}

class Otro2 {
	private String Texto = "infinitoooooooooooooooooooooooooooooooooooooooo"; 
}

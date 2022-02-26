package paintfigures;

import java.util.ArrayList;

import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.graphics.Rectangle;
/**
 * @version 1.0
 * @author Mario
 *
 */
public abstract class AEDTAD implements Figura{

	public static final int width = 200; 
	public static final int height = 70; 
	
	private ArrayList<Posicion> listRectangulos;
	/**
	 * Constructor AEDTAD, inicializa la lista de posiciones
	 */
	public AEDTAD() {
		listRectangulos=new ArrayList<Posicion>();
	}
	/**
	 * Aniade un rectangulo a una lista 
	 * @param rect : Rectangulo que se va a aniadir a la lista
	 * @param text : Texto que acompaña al rectangulo
	 */
	public void addLista(Rectangle rect,Object text){
		Posicion posicion=new Posicion(rect,text);
		if(!listRectangulos.equals(posicion)){
			listRectangulos.add(posicion);
		}
	}
	/**
	 * <b>Comprueba si en la posicion en la que se ha hecho click tiene un objeto y si es asi devuelve el texto de ese objeto</b>
	 * @param x : Posicion X del cursor
	 * @param y : Posicion Y del cursor
	 * @return String : Texto que contiene el rectangulo seleccionado
	 */
	public String abrirClick(int x,int y){
		Posicion rectaux;
		String texto="";
		for(int i=0;i<listRectangulos.size();i++){
			rectaux=listRectangulos.get(i);
			int x1 = rectaux.getRectangulo().x;
			int x2 = x1+rectaux.getRectangulo().width;
			int y1 = rectaux.getRectangulo().y;
			int y2 = y1+rectaux.getRectangulo().height;
			if (x1<=x&&x<=x2&&y1<y&&y<y2){
				texto=rectaux.getTextoRectangulo().toString();
			}
		}
		return texto;
	}

	public abstract void dibujar(GC gc);

}

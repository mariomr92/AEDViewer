package paintfigures;

import java.util.ArrayList;
import java.util.StringTokenizer;

import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.graphics.Rectangle;

import pluginboton.handlers.Par;

public class PintarPriorityQueue extends AEDTAD{

	private ArrayList<Par<Object,Object>> lista;
	private int tam;
//	private int height=200;
	/**
	 * Construtor de PriorityQueue
	 * @param miestructura : Objeto leido del depurador que se va a dibujar
	 */
	public PintarPriorityQueue(Object miestructura) {
		this.lista=(ArrayList<Par<Object,Object>>) miestructura;
		tam=lista.size();
		int x = 50; 
		int y = 50;
		for(int i=tam-1;i>=0;i--) {
			Rectangle rect;
			boolean move=false;
			if(tam-i<=5||tam-i>=tam-5)
			{
			Par<Object,Object> nodo=lista.get(i);
			rect = new Rectangle(x, y, width, height);
			Object n= nodo.getF();
			Object s = nodo.getS();
			super.addLista(rect,"Clave: "+n +"\nValor: " + s);
			y=y+35;
			x=x+100;
			}
			else if(!move){
				rect = new Rectangle(x, y, width, height);
				super.addLista(rect,"");
				y=y+35;
				x=x+100;
				move=true;
			}
		}
	}

	@Override
	/**
	 * Metodo que dibuja la estructura en la vista
	 * @param gc : Contenedor donde se pinta
	 */
	public void dibujar(GC gc) {
		
		int x = 50, y = 50;
		boolean put=false;
		tam=lista.size();

		gc.drawText("Tamaño de la estructura :"+tam, 10, 10);
		
		for(int i=tam-1;i>=0;i--)
		{
			Rectangle rect;
			Par<Object, Object> nodo=lista.get(i);
			if(tam-i<=5||tam-i>=tam-5)
			{
				rect = new Rectangle(x, y, width, height);
				gc.fillRectangle(rect);
				gc.drawRectangle(rect);
				Object n= nodo.getF();
				Object s = nodo.getS();
				
				StringTokenizer st = new StringTokenizer("Clave: "+n +"\nValor: " + s,"\n");
			     String subcadena="";
			     String aux="";
			     while (st.hasMoreTokens()) {

			    	 aux=st.nextToken();
			    	 if(aux.length()>30){
			    		 subcadena+=aux.substring(0, 27)+"...\n";
			    	 }
			    	 else{
			    		 subcadena+=aux+"\n";
			    	 }
			     }
				gc.drawText(subcadena, x+10, y+8);
				
				y=y+35;
				x=x+100;
			}
			else if(!put){
				rect = new Rectangle(x, y, width, height);
				gc.fillRectangle(rect);
				gc.drawRectangle(rect);
				gc.drawText(". . . .", x+10, y+8);
				put=true;
				y=y+35;
				x=x+100;
			}


		}
	}

}

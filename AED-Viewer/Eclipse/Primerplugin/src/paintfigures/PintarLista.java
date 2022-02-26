package paintfigures;

import java.util.StringTokenizer;

import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.graphics.Rectangle;

import aed.positionlist.Position;
import aed.positionlist.PositionList;

public class PintarLista extends AEDTAD{
	private PositionList lista;
	private int tam;
	/**
	 * Construtor de Lista
	 * @param miestructura : Objeto leido del depurador que se va a dibujar
	 */
	
	public PintarLista (Object miestructura){

		this.lista=(PositionList) miestructura;
		tam=lista.size();
		int x = 50; 
		int y = 50;
		Position pos=lista.first();
		
		while (pos!=null) {
			Rectangle rect = new Rectangle(x, y, width, height);
			String n = (String) pos.element();
			super.addLista(rect,n);
			x = x + 300;
			pos=lista.next(pos);
		}

	}

	@Override
	/**
	 * Metodo que dibuja la estructura en la vista
	 * @param gc : Contenedor donde se pinta
	 */
	public void dibujar(GC gc) {

		int x = 50, y = 50,p1=60,p2=95;
		
		gc.drawText("Tamaño de la estructura :"+ tam, 10, 10);

		Position pos=lista.first();
		while (pos!=null) { 
			Rectangle rect;
			String n = (String) pos.element();
			rect = new Rectangle(x, y, width, height);
			gc.drawRectangle(rect);
			gc.fillRectangle(rect);
		     StringTokenizer st = new StringTokenizer(n,"\n");
		     String subcadena="";
		     String aux="";
		     int lineas=1;
		     while (st.hasMoreTokens()&&lineas<4) {
		    	 aux=st.nextToken();
		    	 if(aux.length()>30){
		    		 subcadena+=aux.substring(0, 27)+"...\n";
		    	 }
		    	 else{
		    		 subcadena+=aux+"\n";
		    	 }
		    	 lineas++;
		    	 if(lineas==4&&st.hasMoreTokens())
		    		 subcadena+=" ...";
		     }
			
			
			
			gc.drawText(subcadena, x+10, (y+height)/2);
			if(lista.next(pos)!=null){
				gc.drawLine(x + width, p1, x + 300, p1);
				gc.drawLine(x + 270, p1+10, x + 300, p1);
				gc.drawLine(x + 270,p1-10, x + 300, p1);
				gc.drawLine(x + width, p2, x + 300, p2);
				gc.drawLine(x + width, p2, x + width+30, p2+10);
				gc.drawLine(x + width,p2, x + width + 30, p2-10);
			}
			x = x + 300;
			pos=lista.next(pos);
		}

	}

}

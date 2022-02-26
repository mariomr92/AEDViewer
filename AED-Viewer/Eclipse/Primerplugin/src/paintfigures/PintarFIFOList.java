package paintfigures;

import java.util.StringTokenizer;

import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.graphics.Rectangle;

import aed.fifo.FIFO;
import aed.fifo.FIFOList;
import aed.positionlist.Position;
import aed.positionlist.PositionList;

public class PintarFIFOList extends AEDTAD{
	private FIFO lista;
	private int tam;
	private PositionList listaaux;
	/**
	 * Constructor de FIFOList
	 * @param miestructura : Objeto leido del depurador que se va a dibujar
	 */
	public PintarFIFOList(Object miestructura) {
		this.lista=(FIFO) miestructura;
		tam=lista.size();
		int x = 50; 
		int y = 50;
		listaaux=lista.toPositionList();
		
		Position pos=listaaux.first();
		
		while (pos!=null) {
			Rectangle rect = new Rectangle(x, y, width, height);
			String n = (String) pos.element();
			super.addLista(rect,n);
			y=y+height;
			pos=listaaux.next(pos);
		}
	}

	@Override
	/**
	 * Metodo que dibuja la estructura en la vista
	 * @param gc : Contenedor donde se pinta
	 */
	public void dibujar(GC gc) {

		int x = 50, y = 50;
		gc.drawText("Tamaño de la estructura :"+tam, 10, 10);
		boolean put=false;
		Position pos=listaaux.first();
		Rectangle rect;
		for(int i=0;i<tam&&pos!=null;i++) { 
			String n = (String) pos.element();
			if(i<7)
			{
				rect = new Rectangle(x, y, width, height);
				gc.fillRectangle(rect);
				gc.drawRectangle(rect);
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
			    		 subcadena+="\n ...";
			     }
				
				gc.drawText(subcadena, x+10, y+15);
				
				y=y+height;
			}
			pos=listaaux.next(pos);
		}
		
		if(tam>10){
			rect = new Rectangle(x, y, width, height);
			gc.drawRectangle(rect);
			gc.fillRectangle(rect);
			gc.drawText(". . . .", x+10, y+15);
			y=y+height;
		}

		
	}
}

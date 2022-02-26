package paintfigures;

import java.util.StringTokenizer;

import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.graphics.Rectangle;

import aed.lifo.LIFOList;
import aed.positionlist.Position;
import aed.positionlist.PositionList;

public class PintarLIFOList extends AEDTAD{

	private LIFOList lista;
	private int tam;
	private PositionList listaaux;
	/**
	 * Construtor de LIFOList
	 * @param miestructura : Objeto leido del depurador que se va a dibujar
	 */
	public PintarLIFOList(Object miestructura) {
		this.lista=(LIFOList) miestructura;
		tam=lista.size();
		int x = 50; 
		int y = 50;
		listaaux=lista.toPositionList();
		Position pos=listaaux.first();
		
		while (pos!=null) {
			Rectangle rect = new Rectangle(x, y, width, height);
			Object n =  pos.element();
			super.addLista(rect,String.valueOf(n));
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
		
		for(int i=0;i<tam&&pos!=null;i++) { 
			Rectangle rect;
			Object n = pos.element();
			if(i<5||tam-i<5)
			{
				rect = new Rectangle(x, y, width, height);
				gc.fillRectangle(rect);
				gc.drawRectangle(rect);
				
				StringTokenizer st = new StringTokenizer(String.valueOf(n),"\n");
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
				gc.drawText(subcadena, x+10, y+15);
				y=y+height;
			}
			else{
				rect = new Rectangle(x, y, width, height);
				gc.drawRectangle(rect);
				gc.fillRectangle(rect);
				gc.drawText(". . . .", x+10, y+15);
				y=y+height;
				put=false;
				while(!((tam-i)<5)){
					pos=listaaux.next(pos);
					i++;
				}
			}
			pos=listaaux.next(pos);
		}
		

	}



}

package paintfigures;

import java.util.ArrayList;
import java.util.StringTokenizer;

import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.graphics.Rectangle;

import pluginboton.handlers.Par;

public class PintarMap extends AEDTAD{
	
	private ArrayList<Par<Object, Object>> lista; 
	private int tam;
	
	/**
	 * Construtor de HashMap
	 * @param miestructura : Objeto leido del depurador que se va a dibujar
	 */
	
	public PintarMap(Object miestructura) {
		this.lista= (ArrayList<Par<Object,Object>>) miestructura;
		
		tam=lista.size();
		int x = 50; 
		int y = 50+70;
		Par<Object,Object> nodo=lista.get(0);
		
		for(int i=1;i<tam&&nodo!=null;i++) {
			Rectangle rect = new Rectangle(x, y, width, height);
			Rectangle rect2 =new Rectangle(x+width, y, width, height) ;
			
			Object n= nodo.getF();
			Object s = nodo.getS();
			super.addLista(rect,n);
			super.addLista(rect2,s);
			y=y+70;
			nodo=lista.get(i);
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
		Rectangle rect =new Rectangle(x, y, width, height) ;
		Rectangle rect2 =new Rectangle(x+width, y, width, height) ;
		gc.fillRectangle(rect);
		gc.fillRectangle(rect2);
		gc.drawRectangle(rect);
		gc.drawRectangle(rect2);
		
		gc.drawText("Clave", x+10, y+15);
		gc.drawText("Valor", x+width+10, y+15);
		y=y+70;
		
	    for(int i=0;i<tam;i++) {
			Par<Object, Object> nodo=lista.get(i);
	  		rect =new Rectangle(x, y, width, height) ;
			rect2 =new Rectangle(x+width, y, width, height) ;
			gc.fillRectangle(rect);
			gc.fillRectangle(rect2);
			gc.drawRectangle(rect);
			gc.drawRectangle(rect2);
			StringTokenizer st = new StringTokenizer(String.valueOf(nodo.getF()),"\n");
		     String subcadena="";
		     String aux="";
		     int lineas=1;
		     while (st.hasMoreTokens()&&lineas<4) {
		    	 aux=st.nextToken();
		    	 if(aux.length()>20){
		    		 subcadena+=aux.substring(0, 17)+"...";
		    	 }
		    	 else{
		    		 subcadena+=aux+"\n";
		    	 }
		    	 lineas++;
		    	 if(lineas==4&&st.hasMoreTokens())
		    		 subcadena+=" ...";
		     }
				gc.drawText(subcadena, x+10, y+15);
				
		     st = new StringTokenizer(String.valueOf(nodo.getS()),"\n");
		     subcadena="";
		     aux="";
		     lineas=1;
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
			gc.drawText(subcadena, x+width+10, y+15);  
			y=y+70;
	    }
		
	}

}

package paintfigures;

import java.util.Iterator;

import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.graphics.Rectangle;

import aed.lifo.LIFO;

public class PintarLIFOArray extends AEDTAD{

	LIFO<String> lista;

	public PintarLIFOArray(Object miestructura){
		super();
		this.lista=(LIFO<String>) miestructura;
	}

	public void dibujar(GC gc) {

		int x = 50, y = 50;


		Iterator<String> iterator = lista.iterator();
		int tam = 1;
		iterator.next();
		while(iterator.hasNext())
		{
			iterator.next();
			tam++;
		}
		iterator = lista.iterator();
		gc.drawText("Tamaño de la estructura :"+tam, 10, 10);
		boolean put=false;
		for(int i=0;i<tam;i++)
		{
			Rectangle rect;
			String n = iterator.next();
			if(tam-i<5||tam-i>tam-5)
			{
				rect = new Rectangle(x, y, width, height);
				gc.drawRectangle(rect);
				gc.drawText(n, x+10, y+15);
				super.addLista(rect,n);
				y=y+height;
			}
			else if(!put){
				rect = new Rectangle(x, y, width, height);
				gc.drawRectangle(rect);
				gc.drawText(". . . .", x+10, y+15);
				put=true;
				y=y+height;
			}

		}
	}
}

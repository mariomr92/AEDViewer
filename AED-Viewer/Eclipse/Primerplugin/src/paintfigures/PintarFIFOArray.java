package paintfigures;

import java.util.Iterator;

import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.graphics.Rectangle;

import aed.fifo.FIFO;

public class PintarFIFOArray extends AEDTAD{

	FIFO<String> lista;

	public PintarFIFOArray(Object miestructura) {
		this.lista=(FIFO<String>) miestructura;
		System.out.println(lista);
	}

	public void dibujar(GC gc) {

		// Dimension d = new Dimension(50,50);
		// Point p = new Point(100,100);
		int width = 200, height = 60, x = 50, y = 50;
		//		int ventana = lista.size() * 400;
		//		sc.setMinSize(500, ventana);
		//		subContainer.setSize(500, ventana);

		Iterator<String> iterator = lista.iterator();
		int tam = 1;
		iterator.next();
		while(iterator.hasNext()) {
			iterator.next();
			tam++;
		}
		iterator = lista.iterator();
		gc.drawText("Tamaño de la estructura :"+tam, 10, 10);
		for(int i=0;i<tam;i++)
		{
			Rectangle rect;
			String n = iterator.next();
			if(i<=10)
			{
				rect = new Rectangle(x, y, width, height);
				gc.drawRectangle(rect);
				gc.drawText(n, x+10, y+15);
				super.addLista(rect,n);
				y=y+height;
			}
		}
		if(tam>10){
			Rectangle rect;
			rect = new Rectangle(x, y, width, height);
			gc.drawRectangle(rect);
			gc.drawText(". . . .", x+10, y+15);
			y=y+height;
		}

	}
}

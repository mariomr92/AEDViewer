package paintfigures;

import org.eclipse.swt.graphics.GC;
/**
 * 
 * @author Mario
 *	Interfaz de todas las figuras
 */
public interface Figura {
 
	public void dibujar(GC gc);
	public String abrirClick(int x,int y);

}

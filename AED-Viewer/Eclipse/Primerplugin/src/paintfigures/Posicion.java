package paintfigures;
import org.eclipse.swt.graphics.Rectangle;

public class Posicion{
	
	private Rectangle rectangulo;
	private Object textoRectangulo;

	/**
	 * Constructor de posiciones
	 * @param rectangulo : Rectangulo que se desea guardar
	 * @param textoRectangulo : Texto que acompaniaa al rectangulo que se va a guardar
	 */
	public Posicion(Rectangle rectangulo, Object textoRectangulo) {
		super();
		this.rectangulo = rectangulo;
		this.textoRectangulo = textoRectangulo;
	}
	/**
	 * Devuelve el rectangulo con su posicion y tamanio
	 * @return rectangulo : Devuelve el rectangulo
	 */
	public Rectangle getRectangulo() {
		return rectangulo;
	}

	/**
	 * Devuelve el texto que acompañaba al rectagulo
	 * @return textoRectangulo : Devuelve el texto del rectangulo
	 */
	public Object getTextoRectangulo() {
		return textoRectangulo;
	}

	
}

package pluginboton.handlers;

public class Par <T1,T2> {
	private T1 f; 
	private T2 s; 
	/**
	 * 
	 * @param f : Valor 1
	 * @param s : Valor 2
	 */
	public Par(T1 f, T2 s) {
		this.f = f;  
		this.s = s; 
	}
	/**
	 * 
	 * @return F : Devuelve el valor 1
	 */
	public T1 getF() {
		return f;
	}
	/**
	 * 
	 * @return F : Devuelve el valor 2
	 */
	public T2 getS() {
		return s;
	}
	/**
	 * 
	 * @return String : Devuelve el contenido del objeto entero Valor1 - Valor2
	 */
	@Override
	public String toString() {
		return "<" + f + "-" + s + ">";
	}
}
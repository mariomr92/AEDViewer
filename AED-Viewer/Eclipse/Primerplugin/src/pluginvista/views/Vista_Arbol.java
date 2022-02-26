package pluginvista.views;

import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.part.*;

import net.datastructures.Tree;
import paintfigures.MiArbol;
import org.eclipse.swt.layout.FillLayout;

import org.eclipse.swt.SWT;


/**
 * 
 * @author Mario
 *
 */
public class Vista_Arbol extends ViewPart {

	/**
	 * The ID of the view as specified by the extension.
	 * Composite sirve de contenedor para el dibujado
	 * ScrollBar Anade Scrollbar a las la parte inferior y derecha de la vista
	 * Figura Objeto que representa cualquiera de las figuras
	 * GC Contenedor para pintar 
	 */
	public static final String ID = "AEDViewer.views.Vista_Arbol";
	Composite container;
	Composite cParent; 
	MiArbol tree;
	
	@Override
	public void setFocus() {

	}
	/**
	 * Metodo que se activa cuando abres la vista.
	 * Contiene eventos de raton asi como la creacion del contenedor para el pintado
	 */
	public void createPartControl(Composite parent) {

		this.cParent=parent;
		
//		container.addMouseListener(new MouseListener() {
//			@Override
//			public void mouseDoubleClick(org.eclipse.swt.events.MouseEvent arg0) {
//			}
//
//			@Override
//			public void mouseDown(org.eclipse.swt.events.MouseEvent arg0) {
//				if (figura == null) return;
//				String texto=figura.abrirClick(arg0.x, arg0.y);
//				if(texto!="")
//					MessageDialog.openInformation(
//							new Shell(),
//							"Visualizacion de datos",
//							texto);
//			}
//
//			@Override
//			public void mouseUp(org.eclipse.swt.events.MouseEvent arg0) {
//			}
//
//		});

	}
	/**
	 * Crea la figura para poder representarla en la vista
	 * @param miestructura : Estructura leida del depurador que se va a dibujar
	 * @param tipo : tipo de la estructura
	 */
	public void setObjetoPintar(Object miestructura, int tipo){
			if(tree==null){
				tree=new MiArbol();
				tree.inicializar(cParent);	
			}
			tree.dibujar((Tree) miestructura);
	}

}

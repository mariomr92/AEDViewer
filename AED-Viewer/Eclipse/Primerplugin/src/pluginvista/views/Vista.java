package pluginvista.views;

import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.part.*;

import net.datastructures.Tree;
import paintfigures.Figura;
import paintfigures.MiArbol;
import paintfigures.PintarLista;
import paintfigures.PintarMap;
import paintfigures.PintarPriorityQueue;
import paintfigures.PintarFIFOArray;
import paintfigures.PintarFIFOList;
import paintfigures.PintarLIFOArray;
import paintfigures.PintarLIFOList;

import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.layout.FillLayout;

import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.widgets.ScrollBar;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.ScrolledComposite;
import org.eclipse.swt.events.ControlAdapter;
import org.eclipse.swt.events.ControlEvent;
import org.eclipse.swt.events.MouseListener;
import org.eclipse.swt.events.PaintEvent;
import org.eclipse.swt.events.PaintListener;

/**
 * 
 * @author Mario
 *
 */
public class Vista extends ViewPart {

	/**
	 * The ID of the view as specified by the extension.
	 * Composite sirve de contenedor para el dibujado
	 * ScrollBar Anade Scrollbar a las la parte inferior y derecha de la vista
	 * Figura Objeto que representa cualquiera de las figuras
	 * GC Contenedor para pintar 
	 */
	public static final String ID = "AEDViewer.views.Vista";
	Composite subContainer;
	GC gc;
	private ScrolledComposite sc;
	Figura figura;
	Composite cParent; 
	private ScrollBar vBar1;
	private ScrollBar hBar1;
	Composite container;
	
	@Override
	public void setFocus() {

	}
	/**
	 * Metodo que se activa cuando abres la vista.
	 * Contiene eventos de raton asi como la creacion del contenedor para el pintado
	 */
	public void createPartControl(Composite parent) {

		this.cParent=parent;

		parent.setLayout(new FillLayout());

		container = new Composite(parent, SWT.NONE);
		container.setLayout(new FillLayout());

		sc = new ScrolledComposite(container, SWT.BORDER | SWT.H_SCROLL | SWT.V_SCROLL);
		sc.setLayout(new FillLayout());
		sc.setExpandHorizontal(true);
		sc.setExpandVertical(true);

		vBar1 = sc.getVerticalBar();
		hBar1 = sc.getHorizontalBar();

		subContainer = new Composite(sc, SWT.NONE);
		sc.setContent(subContainer);
		sc.addControlListener(new ControlAdapter() {
			public void controlResized(ControlEvent e) {
				sc.setMinSize(6000, 1000);
			}
		});

		gc = new GC(subContainer);
		gc.setBackground(new Color(subContainer.getDisplay(), 255, 255, 255));
		subContainer.addPaintListener(new PaintListener() {

			@Override
			public void paintControl(PaintEvent arg0) {
				System.out.println("Dibujando.. " + figura);
				if (figura != null) 
					figura.dibujar(gc);
			}
		});

		subContainer.addMouseListener(new MouseListener() {
			@Override
			public void mouseDoubleClick(org.eclipse.swt.events.MouseEvent arg0) {
			}

			@Override
			public void mouseDown(org.eclipse.swt.events.MouseEvent arg0) {
				if (figura == null) return;

				String texto=figura.abrirClick(arg0.x, arg0.y);
				if(texto!="")
					MessageDialog.openInformation(
							new Shell(),
							"Visualizacion de datos",
							texto);
			}

			@Override
			public void mouseUp(org.eclipse.swt.events.MouseEvent arg0) {
			}

		});

	}
	/**
	 * Crea la figura para poder representarla en la vista
	 * @param miestructura : Estructura leida del depurador que se va a dibujar
	 * @param tipo : tipo de la estructura
	 */
	public void setObjetoPintar(Object miestructura, int tipo){
		//tipo=8;
		switch(tipo){
		case 1:
			figura = new PintarLista(miestructura);
			System.out.println("PositionList");
			break;
		case 2:
			figura=new PintarFIFOArray(miestructura);
			System.out.println("FIFOARRAY");
			break;
		case 3:
			figura=new PintarLIFOArray(miestructura);
			System.out.println("LIFOARRAY");
			break;
		case 4:
			figura=new PintarFIFOList(miestructura);
			System.out.println("FIFOLIST");
			break;
		case 6:
			figura=new PintarMap(miestructura);
			break;
		case 5:
			figura=new PintarLIFOList(miestructura);
			System.out.println("LIFOLIST");
			break;
		case 7:
			figura=new PintarPriorityQueue(miestructura);
			System.out.println("PriorityQueue");
			break;
		default:
			figura=null;
		} 
		subContainer.redraw();
	}

}

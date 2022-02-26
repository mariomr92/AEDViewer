package pluginboton.handlers;

import java.util.ArrayList;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.debug.core.DebugException;
import org.eclipse.debug.core.model.IStackFrame;
import org.eclipse.debug.core.model.IVariable;
import org.eclipse.debug.ui.DebugUITools;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IViewPart;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.handlers.HandlerUtil;

import aed.fifo.FIFO;
import aed.fifo.FIFOList;
import aed.lifo.LIFO;
import aed.lifo.LIFOList;
import aed.positionlist.NodePositionList;
import aed.positionlist.PositionList;
import net.datastructures.Position;
import net.datastructures.Tree;
import pluginvista.views.Vista;
import pluginvista.views.Vista_Arbol;



/**
 * Our sample handler extends AbstractHandler, an IHandler base class.
 * @see org.eclipse.core.commands.IHandler
 * @see org.eclipse.core.commands.AbstractHandler
 */
public class HandlerClean extends AbstractHandler {

	@Override
	/**
	 * Metodo que se ejecuta al pulsar en el boton "Limpiar"
	 */
	public Object execute(ExecutionEvent event) throws ExecutionException {

		
			IViewPart view1 = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().findView("pluginvista.views.Vista");
			IViewPart view2 = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().findView("pluginvista.views.Vista_Arbol");
			Vista vista1  = (Vista) view1;
			vista1.setObjetoPintar(null,0);
			Vista_Arbol vista2  = (Vista_Arbol) view2;
			vista2.setObjetoPintar(null,0);
			return null;

	}

}



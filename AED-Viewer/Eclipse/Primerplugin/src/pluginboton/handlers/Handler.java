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
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IViewPart;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PartInitException;
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
public class Handler extends AbstractHandler {

	@Override
	/**
	 * Metodo que se ejecuta al pulsar en el boton "Visualizar"
	 */
	public Object execute(ExecutionEvent event) throws ExecutionException {

		IWorkbenchWindow window = HandlerUtil.getActiveWorkbenchWindowChecked(event);

		//		lista.addLast("Nuevo elemento" + lista.size());
		//		System.out.println("Aniadido nuevo elemento: " + lista.size());
		//		vista.setObjetoPintar(lista,tipo);

		try {
			final Shell shellEclipse= HandlerUtil.getActiveWorkbenchWindowChecked(event).getShell();

			IAdaptable debugContext = DebugUITools.getDebugContext();
			if (debugContext == null) {
				MessageDialog.openInformation(
						window.getShell(),	
						"Ventana",
						"No esta arrancado el depurador");
				return null; 
			}
			Object objPintar = null; 
			int tipoEstructura = 0;
			IStackFrame frame = (IStackFrame)debugContext; 

			OptionsDialog mDialog = new OptionsDialog(shellEclipse, frame.getVariables());
			mDialog.open();

			if (mDialog.getReturnCode() == OptionsDialog.CANCEL) {
				return null;
			} 

			IVariable var  = mDialog.getSelected(); 

			//			for(int i = 0; i < frame.getVariables().length; i ++) {
			//				System.out.println();
			//				System.out.println();
			//				System.out.println("******************************************");
//			IVariable var = frame.getVariables()[selected]; 
//			System.out.println("Selected "  + selected + " " + var.getValue().getReferenceTypeName());
			String type = var.getValue().getReferenceTypeName(); 
			
			if (type.startsWith("aed.positionlist.NodePositionList")) {
				System.out.println("Tengo una lista");
				objPintar = procesarPositionList(var);
				tipoEstructura=1;
				System.out.println("RESULTADO =" + objPintar);
			}
			else if (type.startsWith("aed.fifo.FIFOList")) {
				System.out.println("Tengo una FIFO");
				objPintar = procesarFIFO(var); 
				System.out.println("RESULTADO =" + objPintar);
				tipoEstructura=4;
			}
			else if (type.startsWith("aed.lifo.LIFOList")) {
				System.out.println("Tengo una LIFO");
				objPintar = procesarLIFO(var);

				tipoEstructura=5;
				System.out.println("RESULTADO =" + objPintar);
			}
			else if (type.startsWith("net.datastructures.HashTableMap")) {
				System.out.println("Tengo un MAP");
				objPintar = procesarHashTableMap(var);
				System.out.println("RESULTADO =" + objPintar);
				tipoEstructura=6;

			}
			else if (type.startsWith("net.datastructures.SortedListPriorityQueue")) {
				System.out.println("Tengo una PQueue ");
				objPintar = procesarPQueue(var); 
				System.out.println("RESULTADO =" + objPintar);
				tipoEstructura=7;
			}
			else if (type.contains("AEDLinkedTree")) {
				System.out.println("Tengo un arbol ");
				objPintar = procesarArbol(var); 
				System.out.println("RESULTADO =" + objPintar);
				tipoEstructura=8;
			}
			else {
				MessageDialog.openInformation(
						window.getShell(),
						"Ventana",
						"El tipo de la variable seleccionada no está reconocido por el visualizador");
			}

			//			IMemoryRenderingType [] rends = DebugUITools.getMemoryRenderingManager().getRenderingTypes();
			//			System.out.println("Rendering encontrados " + rends.length);
			//			for (int i = 0; i < rends.length; i ++){
			//				System.out.println("Rendering " + rends[i].getLabel() + " " +rends[i].getId() + " " + rends[i].getClass());
			//				
			//			}
			//
			IViewPart view1 = null;
			if(tipoEstructura==8){
				view1 = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().findView("pluginvista.views.Vista_Arbol");
				Vista_Arbol vista1  = (Vista_Arbol) view1;
				vista1.setObjetoPintar(objPintar,tipoEstructura);
				try {
					PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().showView("pluginvista.views.Vista_Arbol");
				} catch (PartInitException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			else{
			view1 = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().findView("pluginvista.views.Vista");
			Vista vista1 = (Vista) view1;
			vista1.setObjetoPintar(objPintar,tipoEstructura);
			try {
				PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().showView("pluginvista.views.Vista");
			} catch (PartInitException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			}
			
		} catch (DebugException e) {
			System.out.println("Debe estar el debugger arrancado para poder visualizar las estructuras");
		}
		return null;
	}

	private PositionList<String> procesarPositionList (IVariable list) throws DebugException {

		PositionList<String> res = new NodePositionList<String>(); 

		IVariable header = getAttribute("header", list); 
		IVariable node= getAttribute("next", header);

		while (!"null".equals(node.getValue().getValueString())) {
			res.addLast(getStringValue(getAttribute("elem", node),""));
			node = getAttribute("next", node); 
		}

		res.remove(res.last()); 

		return res; 
	}

	private FIFO<String> procesarFIFO (IVariable fifo) throws DebugException {

		FIFO<String> res = new FIFOList<String>(); 
		ArrayList<String> listatmp = new ArrayList<String>(); 

		IVariable list = getAttribute("list", fifo); 
		IVariable header = getAttribute("header", list); 
		IVariable node= getAttribute("next", header);

		while (!"null".equals(node.getValue().getValueString())) {
			listatmp.add(getStringValue(getAttribute("elem", node),""));
			node = getAttribute("next", node); 
		}

		listatmp.remove(listatmp.size()-1); 
		for (int i = 0; i < listatmp.size(); i++) {
			res.enqueue(listatmp.get(i));
		}

		return res; 
	}

	private LIFO<String> procesarLIFO (IVariable fifo) throws DebugException {

		LIFO<String> res = new LIFOList<String>(); 
		ArrayList<String> listatmp = new ArrayList<String>(); 

		IVariable list = getAttribute("list", fifo); 
		IVariable header = getAttribute("header", list); 
		IVariable node= getAttribute("next", header);

		while (!"null".equals(node.getValue().getValueString())) {
			listatmp.add(0,getStringValue(getAttribute("elem", node),""));
			node = getAttribute("next", node); 
		}

		listatmp.remove(0); 
		for (int i = 0; i < listatmp.size(); i++) {
			res.push(listatmp.get(i));
		}

		return res; 
	}

	private ArrayList<Par<String,String>> procesarHashTableMap (IVariable map) throws DebugException {

		ArrayList<Par<String,String>> res = new ArrayList<Par<String,String>>(); 

		// Cogemos el bucket contenido en el array
		IVariable bucket = getAttribute("bucket", map); 
		IVariable []values = bucket.getValue().getVariables();
		for (int i = 0; i < values.length; i++) {
			if (!"null".equals(values[i].getValue().getValueString())) {
				String key = getStringValue(values[i].getValue().getVariables()[0],"");
				String value= getStringValue(values[i].getValue().getVariables()[1],"");
				System.out.println(key);
				System.out.println(value);
				if(!key.equals("") && !value.equals(""))
				res.add(new Par<String,String>(key,value));
			}

		}
		return res; 
	}

	private ArrayList<Par<String,String>> procesarPQueue (IVariable pqueue) throws DebugException {

		ArrayList<Par<String,String>> res = new ArrayList<Par<String,String>>(); 

		IVariable list = getAttribute("entries", pqueue); 
		IVariable header = getAttribute("header", list); 
		IVariable node= getAttribute("next", header);

		while (!"null".equals(node.getValue().getValueString())) {
			IVariable entry = getAttribute("element", node); 
			if (entry.getValue().hasVariables()) {
				String key = entry.getValue().getVariables()[0].getValue().getValueString(); 
				String value = entry.getValue().getVariables()[1].getValue().getValueString();
				res.add(new Par<String,String>(key,value)); 
			}
			node = getAttribute("next", node); 
		}
		return res; 
	}

	private Tree<String> procesarArbol (IVariable tree) throws DebugException {

		AEDLinkedTree<String> res = new AEDLinkedTree<String>(); 

		IVariable rootVar = getAttribute("root", tree); 

		if (getAttribute("element", rootVar) == null) {
			return res; 
		}

		Position<String> rootNode = res.addRoot(getAttribute("element", rootVar).getValue().getValueString());

		procesarNodoArbol(res, rootNode, rootVar);
		//		IVariable children = getAttribute("children", rootVar); 
		//		IVariable header = getAttribute("header", children); 
		//		IVariable node= getAttribute("next", header);
		//		System.out.println("Aniadida raiz " + rootNode);
		//
		//		while (!"null".equals(node.getValue().getValueString())) {
		//			IVariable element = getAttribute("element", node); 
		//			for (int i = 0; i < element.getValue().getVariables().length; i++) {
		//				System.out.println("    " + element.getValue().getVariables()[i].getName());
		//			}
		//			if (element!= null && getAttribute("element", element) != null) {
		//				Position<String> child = res.addChild(getStringValue(getAttribute("element", element),""),rootNode);
		//				System.out.println("Aniadido nodo " + child.element() + " " + rootNode.element());
		//				procesarNodoArbol(res, child, element);
		//			}
		//			node = getAttribute("next", node); 
		//		}

		return res; 
	}

	public void procesarNodoArbol (AEDLinkedTree<String> tree, 
			Position<String> nodo, 
			IVariable var) throws DebugException {

		IVariable children = getAttribute("children", var); 
		IVariable header = getAttribute("header", children); 
		IVariable node= getAttribute("next", header);

		while (!"null".equals(node.getValue().getValueString())) {
			IVariable element = getAttribute("element", node); 
			if (element!= null && getAttribute("element", element) != null) {
				Position<String> child = tree.addChild(getStringValue(getAttribute("element", element),""),nodo);
				procesarNodoArbol(tree, child, element);
			}
			node = getAttribute("next", node); 
		}
	}


	private IVariable getAttribute (String name, IVariable variable) throws DebugException {
		IVariable [] variables = variable.getValue().getVariables();
		IVariable attr= null; 
		for (int i = 0; i < variables.length && attr == null; i ++) {
			//System.out.println(variables[i].getName() + " " + variables[i].getValue());
			if (name.equals(variables[i].getName())) {
				attr = variables[i];
			}
		}
		return attr; 

	}

	private String getStringValue (IVariable var, String espacios) throws DebugException{
		String type = var.getValue().getReferenceTypeName(); 
		IVariable [] vars = var.getValue().getVariables(); 

		// Tipos envoltorio
		if (esTipoEnvoltorio(type)) {
			return espacios + vars[vars.length-1].getValue().toString();
		}

		if (esTipoBasico(type)) {
			return espacios + var.getValue().getValueString();
		}

		// Si es un array
		if (type.endsWith("[]")) {
			StringBuffer sb = new StringBuffer("["); 
			for (int i = 0; i < vars.length-1; i ++) {
				sb.append(getStringValue(vars[i],espacios) + ","); 
			}
			if (vars.length > 0) {
				sb.append(getStringValue(vars[vars.length-1], espacios)); 
			}
			sb.append("]"); 
			return espacios + sb.toString(); 
		}


		StringBuffer buffer = new StringBuffer(); 

		espacios += "  "; 
		for (int i = 0; i < vars.length-1; i ++) {
			buffer.append(espacios  + vars[i].getName()+": "); 
			if (!esTipoBasico(vars[i].getValue().getReferenceTypeName()) && !esTipoEnvoltorio(vars[i].getValue().getReferenceTypeName())) {
				buffer.append("\n"); 
			}
			buffer.append(getStringValue(vars[i], espacios) + "\n"); 
		}
		if (vars.length > 0) {
			buffer.append(espacios  + vars[vars.length-1].getName()+": "); 
			if (!esTipoBasico(vars[vars.length-1].getValue().getReferenceTypeName()) && !esTipoEnvoltorio(vars[vars.length-1].getValue().getReferenceTypeName())) {
				buffer.append("\n"); 
			}
			buffer.append(getStringValue(vars[vars.length-1], espacios)); 
		}

		return buffer.toString(); 


		//                System.out.println(var.getValue().getClass()    );
		//                if (var.getValue() instanceof org.eclipse.jdt.internal.debug.core.model.JDIObjectValue) {
		//                    org.eclipse.jdt.internal.debug.core.model.JDIObjectValue obj = (org.eclipse.jdt.internal.debug.core.model.JDIObjectValue) var.getValue();
		//                    //org.eclipse.jdi.internal.ObjectReferenceImpl impl = (org.eclipse.jdi.internal.ObjectReferenceImpl)obj.getUnderlyingObject(); 
		//                    System.out.println("signature " + obj.getSignature());
		//                    System.out.println("valueString " + obj.getEnclosingObject(0).getValueString());
		//                }

	}

	private boolean esTipoEnvoltorio (String type) {
		return type.equals("java.lang.Integer") || 
				type.equals("java.lang.Float") ||
				type.equals("java.lang.Boolean") ||
				type.equals("java.lang.Long") ||
				type.equals("java.lang.Character") || 
				type.equals("java.lang.Double") || 
				type.equals("java.lang.Byte") ; 
	}

	private boolean esTipoBasico (String type) {
		return type.equals("java.lang.String") ||
				type.equals("int") ||    
				type.equals("double") ||    
				type.equals("long") ||    
				type.equals("byte") ||    
				type.equals("float") ||    
				type.equals("boolean") ||    
				type.equals("char"); 
	}

}



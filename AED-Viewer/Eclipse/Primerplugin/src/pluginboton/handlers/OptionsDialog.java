package pluginboton.handlers;

import java.util.ArrayList;

import org.eclipse.debug.core.DebugException;
import org.eclipse.debug.core.model.IVariable;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
/**
 * 
 * @author Mario
 *
 */
public class OptionsDialog extends Dialog {

	private IVariable selected; 

	private ArrayList<IVariable> options; 

	private ArrayList<Button> analysisButtons;
	/**
	 * 
	 * @param type 
	 * @return boolean : Devuelve si el valor es un tipo abstracto o no
	 */
	public static boolean checkType (String type) {
		return (type.startsWith("aed.positionlist.NodePositionList") ||
			type.startsWith("aed.fifo.FIFOList") ||
			type.startsWith("aed.lifo.LIFOList") ||
			type.startsWith("net.datastructures.HashTableMap") ||
			type.startsWith("net.datastructures.SortedListPriorityQueue") ||
			type.startsWith("AEDLinkedTree")) ;
	}
	/**
	 * 
	 * @param parentShell
	 * @param options
	 * @throws DebugException
	 */
	public OptionsDialog(Shell parentShell, IVariable [] options) throws DebugException {
		super(parentShell);
		analysisButtons = new ArrayList<Button>(options.length);
		this.options = new ArrayList<IVariable>();
		
		for (int i = 0; i < options.length; i ++) {
			System.out.println("  " + options[i].getValue().getReferenceTypeName());
			if (checkType(options[i].getValue().getReferenceTypeName())) {
				System.out.println("Added");
				this.options.add(options[i]);
			}
		}
	}
	@Override
	/**
	 *  Crea la ventana con la lista de los parametros leidos del depurador
	 */
	protected Control createDialogArea(Composite parent) {
		Composite composite = new Composite(parent, SWT.NONE);

		RowLayout verticalLayout = new RowLayout();
		verticalLayout.type = SWT.VERTICAL;

		composite.setLayout(verticalLayout);

		Label label = new Label (composite, SWT.NULL);
		label.setText("Seleccione la variable que desea visualizar:");

		Composite optionsPanel = new Composite(composite,SWT.NONE);
		optionsPanel.setLayout(new RowLayout(SWT.VERTICAL));

		for(int i = 0; i < options.size(); i ++) {
			Button option = new Button(optionsPanel, SWT.RADIO);				
			try {
				option.setText(options.get(i).getName());
			} catch (DebugException e) {
				// TODO Ver que hacer...
				e.printStackTrace();
			}
			option.setData(options.get(i));
			analysisButtons.add(option);
		}
		return composite; 
	}

	@Override
	/** 
	 * Metodo que se genera cuando se pulsa una de las varaibles de la lista y cierra la ventana
	 */
	protected void okPressed() {
		for(int i = 0; i < analysisButtons.size(); i ++) {
			if (analysisButtons.get(i).getSelection()) {
				selected = (IVariable)analysisButtons.get(i).getData(); 
				break; 
			}
		}
		this.close();
		setReturnCode(OK);
	}
	/**
	 * 
	 * @return IVariable : Devuelve el parametro seleccionado
	 */
	public IVariable getSelected () {
		return selected; 
	}
}
package paintfigures;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.ISharedImages;
import org.eclipse.ui.PlatformUI;

import cbg.article.model.MovingBox;
import cbg.article.treeviewer.ui.MovingBoxContentProvider;
import cbg.article.treeviewer.ui.MovingBoxLabelProvider;
import net.datastructures.ArrayListCompleteBinaryTree;
import net.datastructures.Position;
import net.datastructures.Tree;

//public class MiArbol<G> extends JFrame
public class MiArbol{

	TreeViewer  m_treeViewer = null;
	Tree<String> arbol;
	MovingBox GII;

	public MiArbol() {
	}

	public void inicializar(Composite cParent){
		m_treeViewer = new TreeViewer(cParent);
		m_treeViewer.setContentProvider(new MovingBoxContentProvider());
		m_treeViewer.setLabelProvider(new MovingBoxLabelProvider());
	}

	class ViewLabelProvider extends LabelProvider implements ILabelProvider{
		public String getColumnText(Object obj, int index){
			return getText(obj);
		}

		public Image getColumnImage(Object obj, int index){
			return getImage(obj);
		}

		public Image getImage(Object obj){
			return PlatformUI.getWorkbench().getSharedImages().getImage(ISharedImages.IMG_OBJ_ELEMENT);
		}
	}

	class AddressContentProvider implements ITreeContentProvider{
		public Object[] getChildren(Object parentElement){
			if (parentElement instanceof List)
				return ((List<?>) parentElement).toArray();
			return new Object[0];
		}

		public Object getParent(Object element){
			return element.toString();
		}

		public boolean hasChildren(Object element){
			if (element instanceof List)
				return ((List<?>) element).size() > 0;
				return false;
		}

		public Object[] getElements(Object cities){
			// cities ist das, was oben in setInput(..) gesetzt wurde.
			return getChildren(cities);
		}

		public void dispose(){
		}

		public void inputChanged(Viewer viewer, Object oldInput, Object newInput){
		}
	}

	public MovingBox getInitalInput(){

		MovingBox root = new MovingBox();
		HashMap<Position,MovingBox> mapa = new HashMap<Position,MovingBox>();
		Iterator<Position<String>> x = arbol.positions().iterator();
		String res = "";

		while (x.hasNext()) {
			Position<?> node = x.next();
			mapa.put(node, new MovingBox(node.element().toString()));  
		}


		Iterator<Position<?>> hijos;

		x = arbol.positions().iterator();
		MovingBox treeNode = new MovingBox();
		treeNode.addBox(mapa.get(x.next()));
		while (x.hasNext())
		{
			Position<String> node = x.next();
			MovingBox dfnode = mapa.get(arbol.parent(node));
			dfnode.addBox(mapa.get(node));

		}

		return treeNode;
	}


	public void dibujar(Tree<String> miestructura) {
		//if (i == 0) {
		arbol = miestructura;
		if(arbol!=null)
			GII = getInitalInput();
		else
			GII = null;
		m_treeViewer.setInput(GII);
		m_treeViewer.expandAll();
		m_treeViewer.refresh(GII);
		//	i++;
		//}

	}

	public void rebuild(){

		//m_treeViewer.refresh(GII);
	}

}

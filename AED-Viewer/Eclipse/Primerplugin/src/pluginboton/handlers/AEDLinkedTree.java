package pluginboton.handlers;

import java.util.Iterator;

import net.datastructures.InvalidPositionException;
import net.datastructures.LinkedTree;
import net.datastructures.NodePositionList;
import net.datastructures.TreeNode;
import net.datastructures.TreePosition;
import net.datastructures.Position;

public class AEDLinkedTree<E> extends LinkedTree<E>{
	
	public AEDLinkedTree() {
		super();
	}

	public Position<E> addChild (E element, Position<E> parent) {
		TreeNode<E> pNode = (TreeNode<E>) parent; 
		TreeNode<E> node = new TreeNode<E> (element, pNode, new NodePositionList<Position<E>>());
		if (pNode.getChildren() == null) {
			pNode.setChildren(new NodePositionList<Position<E>>());
		}
		pNode.getChildren().addLast(node);
		this.size ++; 
		return node; 
	}

	public Iterable<Position<E>> children(Position<E> v) 
			throws InvalidPositionException { 
		TreePosition<E> vv = checkPosition(v);
		//if (isExternal(v))
		//	throw new InvalidPositionException("External nodes have no children"); 
		return vv.getChildren();
	}

	
	public static void main(String[] args) {
		AEDLinkedTree<String> tree = new AEDLinkedTree<>(); 
		Position<String> root = tree.addRoot("Prueba");
		
		Position<String> hijo1 = tree.addChild("hijo1", root); 
		Position<String> hijo2 = tree.addChild("hijo2", root);
		
		Position<String> hijo3 = tree.addChild("hijo3", hijo1); 
		Position<String> hijo4 = tree.addChild("hijo4", hijo1); 

		Position<String> hijo5 = tree.addChild("hijo5", hijo2); 
		Position<String> hijo6 = tree.addChild("hijo6", hijo5); 


		System.out.println(tree.isExternal(root) + "  " + tree.children(root));
		System.out.println(tree.isExternal(hijo1) + "  " + tree.children(hijo1));
		System.out.println(tree.isExternal(hijo2) + "  " + tree.children(hijo2));
		System.out.println(tree.isExternal(hijo3) + "  " + tree.children(hijo3));
		System.out.println(tree.isExternal(hijo4) + "  " + tree.children(hijo4));
		System.out.println(tree.isExternal(hijo5) + "  " + tree.children(hijo5));
		System.out.println(tree.isExternal(hijo6) + "  " + tree.children(hijo6));

		Iterator<Position<String>> it = tree.positions().iterator(); 
		
		while (it.hasNext()) {
			System.out.println(it.next().element());
		}
		
	}
	
	
}

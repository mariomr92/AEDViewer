package trees;


import net.datastructures.*;
import paintfigures.MiArbol;

import java.util.Iterator;

public class Prueba<G> {

    private Tree<G> arbol;
    private Position<G> root;
    private Position<G> actual;

    public Prueba (Tree<G> arbol) {

        this.arbol = arbol;
        this.root = arbol.root();
        this.actual = this.root;
    }

    public String obtenerHijos() {

        String res = "";
        Iterator<Position<G>> x = arbol.positions().iterator();

        while (x.hasNext())
        {
            Position<G> node = x.next();
            if (!arbol.isRoot(node) && !arbol.parent(node).equals(this.actual))
                this.actual = arbol.parent(node);

            if (arbol.isRoot(node))
                res+= "Raiz: "+ node.element().toString()+"\n";
            else res+= "Elemento: "+node.element().toString() + " Padre: "+this.actual.element().toString()+"\n";
        }
        return res;
    }
    public static void main (String [] args) {

        ArrayListCompleteBinaryTree<Integer> arbol1= new ArrayListCompleteBinaryTree<Integer>();
        for(int i=0;i<25;i++){
            arbol1.add(i);
        }
        MiArbol<Integer> prueba = new MiArbol<Integer>((Tree<Integer>)arbol1);
        prueba.pintararbol();

    }
}
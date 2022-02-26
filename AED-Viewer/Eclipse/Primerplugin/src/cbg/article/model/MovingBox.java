package cbg.article.model;

import java.util.ArrayList;
import java.util.List;

public class MovingBox extends Model {
	protected List boxes;
	protected List games;
	protected List books;
	
	private static IModelVisitor adder = new Adder();
	private static IModelVisitor remover = new Remover();
	
	public MovingBox() {
		boxes = new ArrayList();
		games = new ArrayList();
		books = new ArrayList();
	}
	
	public static class Adder implements IModelVisitor {

		/*
		 * @see ModelVisitorI#visitBoardgame(BoardGame)
		 */

		/*
		 * @see ModelVisitorI#visitBook(MovingBox)
		 */

		/*
		 * @see ModelVisitorI#visitMovingBox(MovingBox)
		 */

		/*
		 * @see ModelVisitorI#visitBoardgame(BoardGame, Object)
		 */

		/*
		 * @see ModelVisitorI#visitMovingBox(MovingBox, Object)
		 */
		public void visitMovingBox(MovingBox box, Object argument) {
			((MovingBox) argument).addBox(box);
		}
	}

	public static class Remover implements IModelVisitor {
		/*
		 * @see ModelVisitorI#visitMovingBox(MovingBox, Object)
		 */
		public void visitMovingBox(MovingBox box, Object argument) {
			((MovingBox) argument).removeBox(box);
			box.addListener(NullDeltaListener.getSoleInstance());
		}

	}
	
	public MovingBox(String name) {
		this();
		this.name = name;
	}
	
	public List getBoxes() {
		return boxes;
	}
	
	public void addBox(MovingBox box) {
		boxes.add(box);
		box.parent = this;
		fireAdd(box);
	}
	

	
	public List getBooks() {
		return books;
	}
	
	public void remove(Model toRemove) {
		toRemove.accept(remover, this);
	}

	
	public void removeBox(MovingBox box) {
		boxes.remove(box);
		box.addListener(NullDeltaListener.getSoleInstance());
		fireRemove(box);	
	}

	public void add(Model toAdd) {
		toAdd.accept(adder, this);
	}
	
	public List getGames() {
		return games;
	}
	
	/** Answer the total number of items the
	 * receiver contains. */
	public int size() {
		return getBooks().size() + getBoxes().size() + getGames().size();
	}
	/*
	 * @see Model#accept(ModelVisitorI, Object)
	 */
	public void accept(IModelVisitor visitor, Object passAlongArgument) {
		visitor.visitMovingBox(this, passAlongArgument);
	}

}

package cbg.article.model;



public interface IModelVisitor {
	public void visitMovingBox(MovingBox box, Object passAlongArgument);
}

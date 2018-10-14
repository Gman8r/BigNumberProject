//Left and right moving iterator interface to help DoublyLinkedList iteration
//By Brian Intile
public interface LeftRightIterator<T>
{
	public abstract void reset(DoublyLinkedList.Side startSide);

	public abstract boolean hasLeft();

	public abstract boolean hasRight();
	
	public abstract T left();
	
	public abstract T right();

	public abstract void set(T value); 
}

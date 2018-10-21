import java.util.NoSuchElementException;

/**
 * Data structure to store and traverse a list of objects from either side in linear time.
 * <br>Based loosely on https://algs4.cs.princeton.edu/13stacks/DoublyLinkedList.java.html
 * <br>"left" and "right" nomenclature is used instead of "next" and "previous" for ease of use with this project
 * @param <T> Data type
 * @author Brian intile
 */
public class DoublyLinkedList<T>
{
	//Node helper type for list element
	private class Node
	{
		private T value = null;
		private Node left = null;
		private Node right = null;
	}
	
	//Helper enum for iteration direction
	public enum Side
	{
		Left,
		Right;
	}
	
	//The nodes of our array, where iterating may begin
	private Node leftHead;
	private Node rightHead;
	
	int size;
	
	/**
	 * Constructor for new DoublyLinkedList
	 */
	public DoublyLinkedList()
	{
		leftHead = null;
		rightHead = null;
		size = 0;
	}
	
	/**Constructor that duplicates a DoublyLinkedList by value
	 * 
	 * @param input list values
	 */
	public DoublyLinkedList(DoublyLinkedList<T> input)
	{
		this();
		LeftRightIterator<T> iterator = input.iterator(Side.Left);
		while(iterator.hasRight())
		{
			addRight(iterator.right());	
		}
	}

	/**
	 * Adds a new value to the right of the list
	 * @param value
	 */
	public void addRight(T value)
	{
		Node newRight = new Node();
		newRight.value = value;
		if (size > 0)
		{
			//List is already populated
			rightHead.right = newRight;
			newRight.left = rightHead;
		}
		else
		{
			//List is empty
			leftHead = newRight;
		}
		rightHead = newRight;
		size++;
	}
	
	/**
	 * Removes the rightmost value
	 */
	public void removeRight()
	{
		if (size <= 0)
			//Throw exception if empty
			throw new NoSuchElementException();
		else if (size == 1)
		{
			//Removing only element
			leftHead = rightHead = null;
			size = 0;
		}
		else
		{
			//Removing one of many elements
			rightHead = leftHead.left;
			rightHead.right = null;
			size--;
		}
	}

	/**
	 * Adds a new value to the left of the list
	 * @param value
	 */
	public void addLeft(T value)
	{
		Node newLeft = new Node();
		newLeft.value = value;
		if (size > 0)
		{
			//List is already populated
			leftHead.left = newLeft;
			newLeft.right = leftHead;
		}
		else
		{
			//List is empty
			rightHead = newLeft;
		}
		leftHead = newLeft;
		size++;
	}
	
	/**
	 * Removes the leftmost value
	 */
	public void removeLeft()
	{
		if (size <= 0)
			//Throw exception if empty
			throw new NoSuchElementException();
		else if (size == 1)
		{
			//Removing only element
			leftHead = rightHead = null;
			size = 0;
		}
		else
		{
			//Removing one of many elements
			leftHead = leftHead.right;
			leftHead.left = null;
			size--;
		}
	}
	
	/**
	 * Returns current size of list (constant time)
	 * @return size
	 */
	public int getSize()
	{
		return size;
	}
	
	/**
	 * Returns whether the list size > 0
	 * @return is empty
	 */
	public boolean isEmpty()
	{
		return size > 0;
	}

	/**
	 * Creates and returns a new iterator for the list
	 * <br>WARNING: Not suitable for adding to or removing from the list while iterating. If an item is added or removed, create a new iterator  
	 * @param startSide Side to start on
	 * @return iterator
	 */
	public LeftRightIterator<T> iterator(Side startSide)
	{
		return new DoublyLinkedListIterator(startSide);
	}
	
	/**
	 * Iterator for doubly linked list
	 * @author Brian Intile
	 *
	 */
	public class DoublyLinkedListIterator implements LeftRightIterator<T>
	{
		private Node leftNode;		//Node to the left of pointer
		private Node rightNode;		//Node to the right of pointer
		private Node lastVisitedNode;		//Last visited node from left() or right()
		
		/**
		 * Constructor for iterator
		 * @param startSide
		 */
		public DoublyLinkedListIterator(Side startSide)
		{
			reset(startSide);
		}
		
		/**
		 * Reset iteration by returning to one side of the list
		 * @param startSide side to start from  
		 */
		@Override
		public void reset(Side startSide)
		{
			if (startSide == Side.Left)
			{
				leftNode = null;
				rightNode = leftHead;
			}
			else
			{
				leftNode = rightHead;
				rightNode = null;
			}
		}

		/**
		 * Determines if there are values to the left of the current iteration position
		 * @return true if there is a left value
		 */
		@Override
		public boolean hasLeft()
		{
			return leftNode != null;
		}

		/**
		 * Determines if there are values to the right of the current iteration position
		 * @return true if there is a right value
		 */
		@Override
		public boolean hasRight()
		{
			return rightNode != null;
		}

		/**
		 * Moves iterator right and returns next value
		 */
		@Override
		public T right()
		{
			if (!hasRight())
				throw new NoSuchElementException();
			lastVisitedNode = rightNode;
			T value = rightNode.value;
			leftNode = rightNode;
			rightNode = rightNode.right;
			return value;
		}

		/**
		 * Moves iterator left and returns next value
		 */
		@Override
		public T left()
		{
			if (!hasLeft())
				throw new NoSuchElementException();
			lastVisitedNode = leftNode;
			T value = leftNode.value;
			rightNode = leftNode;
			leftNode = leftNode.left;
			return value;
		}

		/**
		 * Sets last-visited (using left() or right()) value
		 */
		@Override
		public void set(T value)
		{
			if (lastVisitedNode == null)
				throw new NoSuchElementException();
			lastVisitedNode.value = value;
		}
		
	}
}

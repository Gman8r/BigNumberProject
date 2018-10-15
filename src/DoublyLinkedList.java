import java.util.NoSuchElementException;


//Simple DoublyLinkedList implementation
//By Brian Intile
//Based loosely on https://algs4.cs.princeton.edu/13stacks/DoublyLinkedList.java.html
//"left" and "right" nomenclature is used instead of "next" and "previous" for ease of use with this project
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
	
	public DoublyLinkedList()
	{
		leftHead = null;
		rightHead = null;
		size = 0;
	}
	
	//Constructor that duplicates a DoublyLinkedList by value
	public DoublyLinkedList(DoublyLinkedList<T> input)
	{
		this();
		LeftRightIterator<T> iterator = input.iterator(Side.Left);
		while(iterator.hasRight())
		{
			addRight(iterator.right());	
		}
	}

	//Adds a new value to the right of the list
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
	
	//Removes the rightmost value
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

	//Adds a new value to the left of the list
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
	
	//Removes the leftmost value
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
	
	public int getSize()
	{
		return size;
	}
	
	public boolean isEmpty()
	{
		return size > 0;
	}

	public LeftRightIterator<T> iterator(Side startSide)
	{
		return new DoublyLinkedListIterator(startSide);
	}
	
	//Iterator for doubly linked list
	public class DoublyLinkedListIterator implements LeftRightIterator<T>
	{
		private Node leftNode;		//Node to the left of pointer
		private Node rightNode;		//Node to the right of pointer
		private Node lastVisitedNode;		//Last visited node from left() or right()
		
		public DoublyLinkedListIterator(Side startSide)
		{
			reset(startSide);
		}
		
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

		@Override
		public boolean hasLeft()
		{
			return leftNode != null;
		}

		@Override
		public boolean hasRight()
		{
			return rightNode != null;
		}

		//Moves iterator right and returns next value
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

		//Moves iterator left and returns next value
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

		//Sets last-visited (using left() or right()) value
		@Override
		public void set(T value)
		{
			if (lastVisitedNode == null)
				throw new NoSuchElementException();
			lastVisitedNode.value = value;
		}
		
	}
}

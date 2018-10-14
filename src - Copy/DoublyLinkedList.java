import java.util.ListIterator;
import java.util.NoSuchElementException;


//Simple DoublyLinkedList implementation
//By Brian Intile
//Based loosely on https://algs4.cs.princeton.edu/13stacks/DoublyLinkedList.java.html
//"left" and "right" nomenclature is used in favor of "beginning" and "end" for ease of use with BigNumber
public class DoublyLinkedList<T> implements Iterable<T>
{
	//Node helper type for list element
	private class Node
	{
		private T value = null;
		private Node leftNeighbor = null;
		private Node rightNeighbor = null;
	}
	
	//Direction values for iterator
	public enum Direction
	{
		Left,
		Right;
	}
	
	//The leftmost and rightmost nodes of our array, where iterating may begin
	private Node leftHead;
	private Node rightHead;
	
	int size;
	
	public DoublyLinkedList()
	{
		leftHead = null;
		rightHead = null;
		size = 0;
	}

	//Creates new node to the right, updating all necessary values
	public void addRight(T value)
	{
		Node newRight = new Node();
		newRight.value = value;
		if (size > 0)
		{
			//List is already populated
			rightHead.rightNeighbor = newRight;
			newRight.leftNeighbor = rightHead;
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
			rightHead = leftHead.leftNeighbor;
			rightHead.rightNeighbor = null;
			size--;
		}
	}

	//Creates new node to the left, updating all necessary values
	public void addLeft(T value)
	{
		Node newLeft = new Node();
		newLeft.value = value;
		if (size > 0)
		{
			//List is already populated
			leftHead.leftNeighbor = newLeft;
			newLeft.rightNeighbor = leftHead;
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
			leftHead = leftHead.rightNeighbor;
			leftHead.leftNeighbor = null;
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

	@Override
	public ListIterator<T> iterator()
	{
		return null;
	}
	
	//Iterator for doubly linked list
	//Direction is controlled by a variable, and "next" and "previous" is relative to current direction
	public class DoublyLinkedListIterator implements ListIterator<T>
	{
		private Direction direction = Direction.Right;
		private Node previousNode;
		private Node nextNode;
		private int nextIndex;
		
		public DoublyLinkedListIterator()
		{
			resetIteration();
		}
		
		void resetIteration()
		{
			previousNode = null;
			nextNode = direction == Direction.Right ? leftHead : rightHead;
			nextIndex = 0;
		}

		//Returns which way the iterator is going
		public Direction getDirection()
		{
			return direction;
		}

		//Sets which way the iterator is going AND resets the iteration
		public void setDirection(Direction direction)
		{
			this.direction = direction;
			resetIteration();
		}
		
		//Sets which way the iterator is going WITHOUT resetting the iteration (Probably dangerous tbh)		
		public void setDirectionWithoutReset(Direction direction)
		{
			this.direction = direction;
		}

		@Override
		public void add(T value)
		{
			//I doubt we'll need to inject a digit anywhere in the middle
			throw new UnsupportedOperationException();
		}

		@Override
		public void remove()
		{
			//Or remove one
			throw new UnsupportedOperationException();
		}

		@Override
		public boolean hasNext()
		{
			return nextIndex < size;
		}

		@Override
		public boolean hasPrevious()
		{
			return nextIndex > 0;
		}

		@Override
		public T next()
		{
			if (!hasNext())
				throw new NoSuchElementException();
			T value = nextNode.value;
			nextNode = (direction == Direction.Right) ? nextNode.rightNeighbor : nextNode.leftNeighbor;
			previousNode = (direction == Direction.Right) ? nextNode.leftNeighbor : nextNode.rightNeighbor; 
			nextIndex++;
			return value;
		}

		@Override
		public int nextIndex()
		{
			return nextIndex;
		}

		@Override
		public T previous()
		{
			if (!hasPrevious())
				throw new NoSuchElementException();
//			current = (direction == Direction.Right) ? current.leftNeighbor : current.rightNeighbor;
			nextIndex--;
			return null;
		}

		@Override
		public int previousIndex()
		{
			return nextIndex - 1;
		}

		@Override
		public void set(T arg0)
		{
			// TODO Auto-generated method stub
			
		}
		
	}
}

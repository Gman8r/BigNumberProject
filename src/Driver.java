import java.util.Scanner;

public class Driver
{
	public static void main(String[] args)
	{
		testBigNumber();		
		//testDoublyLinkedList();
	}
	
	static void testBigNumber()
	{
		Scanner scanner = new Scanner (System.in);
		BigNumber x,y;

		System.out.println ("Enter two BigNumbers, on separate lines, or hit Enter to terminate");
		String line = scanner.nextLine();

		while (line.length() > 0)
		{
			x = new BigNumber (line);
			System.out.println ("Enter a second BigNumber");
			line = scanner.nextLine();
			y = new BigNumber (line);

			System.out.println ("First: " + x);
			System.out.println ("Second: " + y);
			System.out.println ("First Raw: " + x.toStringRaw());
			System.out.println ("Second Raw: " + y.toStringRaw());
			System.out.println ("Sum: " + x.add(y));
			System.out.println ("Sum: " + y.add(x));
			System.out.println ("First - Second: " + x.subtract(y));
			System.out.println ("Second - First: " + y.subtract(x));
			System.out.println ("First Negated: " + x.negate());
			System.out.println ("Second Negated: " + y.negate());
//			System.out.println ("Product: " + x.multiply(y));
//			System.out.println ("Product: " + y.multiply(x));
//			System.out.println ("First / Second: " + x.divide(y).getQuotient());
//	 		System.out.println ("Second / First: " + y.divide(x).getQuotient());
//			System.out.println ("First % Second: " + x.divide(y).getMod());
//			System.out.println ("Second % First: " + y.divide(x).getMod());
		   
			line = scanner.nextLine();
		}
		scanner.close();
	}
	
	static void testDoublyLinkedList()
	{
		System.out.println("Testing doubly linked list:");
		DoublyLinkedList<Integer> list = new DoublyLinkedList<Integer>();
		list.addRight(0);
		list.addRight(2);
		list.addLeft(-2);
		list.addRight(4);
		list.addLeft(-4);
		list.addRight(6);
		list.addLeft(-6);
		list.addRight(8);
		list.addLeft(-8);
		
		System.out.println("Going right");
		LeftRightIterator<Integer> iterator = list.iterator(DoublyLinkedList.Side.Left);
		while(iterator.hasRight())
		{
			System.out.println(iterator.right());
		}
		System.out.println("Going back left");
		while(iterator.hasLeft())
		{
			System.out.println(iterator.left());
		}
		System.out.println("Going right but adding 1 to each");
		while(iterator.hasRight())
		{
			int value = iterator.right();
			value++;
			iterator.set(value);
			System.out.println(value);
		}
		System.out.println("And back left");
		while(iterator.hasLeft())
		{
			System.out.println(iterator.left());
		}
		
		LeftRightIterator<Integer> backwardsIterator = list.iterator(DoublyLinkedList.Side.Right);
		System.out.println("New iterator starting from right side now");
		while(backwardsIterator.hasLeft())
		{
			System.out.println(backwardsIterator.left());
		}
		System.out.println("Resetting back to right side and going left again");
		backwardsIterator.reset(DoublyLinkedList.Side.Right);
		while(backwardsIterator.hasLeft())
		{
			System.out.println(backwardsIterator.left());
		}
	}

}

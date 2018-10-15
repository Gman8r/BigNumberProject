public class BigNumber 
{
	private DoublyLinkedList<Integer> digits;

	//Create a new BigNumber with value 0
	//Brian Intile
	public BigNumber()
	{
		digits = new DoublyLinkedList<Integer>();
		digits.addRight(0);
	}
	
	//Create a new BigNumber from a string representation of a number
	//Brian Intile
	public BigNumber(String value)
	{
		//TODO Optimize string reading?
		//TODO String format exception checking?
		this();
		digits.removeLeft();	//Remove default '0'
		boolean isNegative = value.charAt(0) == '-';
		int strLength = value.length();
		for(int i = (isNegative ? 1 : 0); i < strLength; i++)
		{
			digits.addRight(value.charAt(i) - '0');
		}
		if (isNegative)
			digits = negate().digits;	//Create a new, negated BigNumber and steal its digits
	}
	
	//Private constructor for creating a BigNumber from a list of digits
	//Brian Intile
	private BigNumber(DoublyLinkedList<Integer> digits)
	{
		this();
		this.digits = digits;
	}

	//TODO Brian
	public BigNumber add(BigNumber o)
	{
		//TODO Negative number support
		
		//Create our iterators and result list
		LeftRightIterator<Integer> iterator = digits.iterator(DoublyLinkedList.Side.Right);
		LeftRightIterator<Integer> oIterator = o.digits.iterator(DoublyLinkedList.Side.Right);
		DoublyLinkedList<Integer> results = new DoublyLinkedList<Integer>();
		int maxDigits = Math.max(digits.getSize(), o.digits.getSize());		//Determine total iteration size from biggest digit number
		
		boolean carry = false;
		for(int i = 0; i < maxDigits; i++)
		{
			//Iterate left and take values if any exist
			//TODO account for 0 vs 9 in tens complement?
			int digit = iterator.hasLeft() ? iterator.left() : 0;
			int oDigit = oIterator.hasLeft() ? oIterator.left() : 0;
			
			//Add and account for carrying 1's
			int digitSum = digit + oDigit + (carry ? 1 : 0);
			if (digitSum >= 10)
			{
				digitSum -= 10;
				carry = true;
			}
			else
				carry = false;
			results.addLeft(digitSum);
		}
		if (carry)	//Account for final carry
			results.addLeft(1);	//TODO account for tens complement?
		
		return new BigNumber(results);
	}

	//TODO Brian
	public BigNumber subtract (BigNumber o)
	{
		return null;
	}

	//TODO Matt
	public BigNumber negate()
	{
		return null;
	}

	//TODO Matt
	public int sign()
	{
		return 0;
	}
	
	//TODO Matt
	public void normalize()
	{
		
	}
	
	//TODO Matt
	public boolean equals(BigNumber o)
	{
		return false;
	}
	
	//TODO Matt
	public int compareTo(BigNumber o)
	{
		return 0;
	}
	
	//TODO Matt
	public String toString ()
	{
		return null;
	}
	
	//TODO Kyle
	public BigNumber multiply(BigNumber o)
	{
		return null;
	}
	
	//Helper class for division
	public class BigNumberDivision
	{
		private BigNumber quotient;
		public BigNumber getQuotient() { return quotient; }
		private BigNumber mod;
		public BigNumber getMod() { return mod; }
	}
	
	//TODO Kyle
	public BigNumberDivision divide (BigNumber o)
	{
		return null;
	}
	
	//TODO Kyle
	public BigNumber[] factor()
	{
		return null;
	}
	
}

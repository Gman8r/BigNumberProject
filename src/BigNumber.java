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
		boolean isNegative = value.charAt(0) == '-';
		
		//Remove leading '0' (added in by default) UNLESS we're parsing a positive number that starts with n > 4
		if (isNegative || value.charAt(0) <= '4')	
			digits.removeLeft(); 

		int strLength = value.length();
		for(int i = (isNegative ? 1 : 0); i < strLength; i++)
		{
			digits.addRight(value.charAt(i) - '0');
		}
		if (isNegative)					//If we're to create a negative number
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
		return null;
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

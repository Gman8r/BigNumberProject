public class BigNumber 
{
	private DoublyLinkedList<Integer> digits;
	
	//TODO Brian	
	public BigNumber()
	{
		//TODO default to "0"
		digits = new DoublyLinkedList<Integer>();
	}
	
	//TODO Brian
	public BigNumber(String initialValue)
	{
		this();
		//TODO Optimize list assignment?
		//TODO Add negative checking
		for(int i = 0; i < initialValue.length(); i++)
		{
			digits.addRight(i - '0');
		}
		
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

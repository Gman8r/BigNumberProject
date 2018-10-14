public class BigNumber 
{
	private DoublyLinkedList<Character> digits; //TODO Do we want character as our data type??
	
	public BigNumber()
	{
		digits = new DoublyLinkedList<Character>();
	}
	
	public BigNumber(String initialValue)
	{
		this();
		//TODO Optimize list assignment?
		for(int i = 0; i < initialValue.length(); i++)
		{
			digits.addRight(initialValue.charAt(i));
		}
		
	}
	
	public BigNumber add(BigNumber o)
	{
		//TODO Brian
		return null;
	}
	
	public BigNumber subtract (BigNumber o)
	{
		//TODO Brian
		return null;
	}
	
	public BigNumber multiply(BigNumber o)
	{
		//TODO ???
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
	
	public BigNumberDivision divide (BigNumber o)
	{
		//TODO ???
		return null;
	}
	
	public BigNumber negate()
	{
		//TODO ???
		return null;
	}
	
	public int sign()
	{
		//TODO ???
		return 0;
	}
	
	public void normalize()
	{
		//TODO ???
	}
	
	public BigNumber[] factor()
	{
		//TODO ???
		return null;
	}
	
	public boolean equals(BigNumber o)
	{
		//TODO ???
		return false;
	}
	
	public int compareTo(BigNumber o)
	{
		//TODO ???
		return 0;
	}
	
	public String toString ()
	{
		//TODO ???
		return null;
	}
	
}

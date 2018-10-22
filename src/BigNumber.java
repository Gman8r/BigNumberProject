/**
 * Numeric structure for performing mathematic operations on arbitrarily large numbers
 * @author Brian intile, Matthew Moore, Kyle Butera
 */
public class BigNumber 
{
	private DoublyLinkedList<Integer> digits;

	/**Creates a new BigNumber with value 0
	* @author Brian Intile
	*/
	public BigNumber()
	{
		digits = new DoublyLinkedList<Integer>();
		digits.addRight(0);
	}
	
	/**Creates a new BigNumber from a string representation of a number
	 * @param value Value to parse
	 * @author Brian Intile
	 */
	public BigNumber(String value)
	{
		this();
		boolean isNegative = value.charAt(0) == '-';

		int strLength = value.length();
		for(int i = (isNegative ? 1 : 0); i < strLength; i++)
		{
			digits.addRight(value.charAt(i) - '0');
		}
		if (isNegative)					//If we're to create a negative number
			digits = negate().digits;	//Create a new, negated BigNumber and steal its digits
		normalize();
	}
	
	/**Private constructor for creating a BigNumber from a list of digits
	 * <br>NOTE: This is passed by reference and should not be used for duplicating BigNumbers
	 * @param digits Digits of the BigNumber
	 */
	private BigNumber(DoublyLinkedList<Integer> digits)
	{
		this();
		this.digits = digits;
	}
	
	/**Private shortcut to duplicate a BigNumber by value
	 * @return Duplicated equivalent BigNumber
	 */
	private BigNumber duplicate()
	{
		BigNumber returnNum = new BigNumber();
		returnNum.digits = new DoublyLinkedList<Integer>(digits);
		return returnNum;
	}

	/**Adds a big number to this one
	 * @param o number to add
	 * @return sum
	 * @author Brian Intile
	 */
	public BigNumber add(BigNumber o)
	{
		//precalculate signs of each number
		int sign = sign();
		int oSign = o.sign();
		//If they are same-signed (and non-zero), keep that in mind to force that sign at the end
		//This is to prevent a circumstance such as "3 + 3 = 6" and change it to "3 + 3 = 06"
		int forceSign = (sign == oSign) ? sign : 0;
		
		//Determine how many digits to iterate based on largest-digit number
		int maxDigits = Math.max(digits.getSize(), o.digits.getSize());
		
		//Create our iterators and result list
		LeftRightIterator<Integer> iterator = digits.iterator(DoublyLinkedList.Side.Right);
		LeftRightIterator<Integer> oIterator = o.digits.iterator(DoublyLinkedList.Side.Right);
		DoublyLinkedList<Integer> results = new DoublyLinkedList<Integer>();
		
		boolean carry = false;
		for(int i = 0; i < maxDigits; i++)
		{
			//Iterate left and take digit values if any exist
			//Otherwise, pad 0's or 9's depending on sign
			int digit = iterator.hasLeft() ? iterator.left() : (sign >= 0 ? 0 : 9);
			int oDigit = oIterator.hasLeft() ? oIterator.left() : (oSign >= 0 ? 0 : 9);
			
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
		
		BigNumber resultNum = new BigNumber(results);	//Cast our result into a BigNumber for the next part
		
		//Check if we have to force a sign, and if so, if the result is already that sign
		if (forceSign != 0 && resultNum.sign() != forceSign)
			resultNum.digits.addLeft((forceSign == 1) ? 0 : 9);	//Add leading 0 or 9 depending on sign
		
		resultNum.normalize();
		return resultNum;
	}

	/**Subtracts a big number to this one
	 * @param o number to subtract
	 * @return difference
	 * @author Brian Intile
	 */
	public BigNumber subtract(BigNumber o)
	{
		//Just negate the subtracting number and add it
		return add(o.negate());
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

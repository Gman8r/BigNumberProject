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

	/**Negate acts identically to performing 10's compliment on a number.
	 * @return negated number
	 * @author Matt Moore
	 */
	public BigNumber negate()
	{
		int sign = sign();
		if (sign == 0)	//Edge case for 0
			return this.duplicate();
		
		//Creating iterator and results list
		LeftRightIterator<Integer> iterator = digits.iterator(DoublyLinkedList.Side.Right);
		DoublyLinkedList<Integer> results = new DoublyLinkedList<Integer>();
		
		//Iterate left "performing 9's compliment" and store it into results
		while (iterator.hasLeft())
		{
			int newDigit = 9 - iterator.left();
			results.addLeft(newDigit);
		}
		
		//Return results (by taking 9's compliment) and add 1
		BigNumber resultNum = new BigNumber(results);
		return resultNum.add(new BigNumber("1"));
	}

	/**Determines the sign of a number based on the first digit in the list
	 * @return int (the sign: -1 = negative, 1 = positive, 0 = zero)
	 * @author Matt Moore
	 */
	public int sign()
	{	//Starting from the left most side, checking value of first iteration
		if (digits.iterator(DoublyLinkedList.Side.Left).right() > 4)
			return -1;	//Negative case if greater than 4
		else
		{
			LeftRightIterator<Integer> iterator = digits.iterator(DoublyLinkedList.Side.Left);
			while(iterator.hasRight())
			{
				if (iterator.right() > 0)
					return 1;	//Positive case for anything other than 0, negatives been checked
			}
		}
		return  0;	
	}
	
	/**"Normalizes" numbers by removing any unnecessary leading 0's or 9's
	 * @author Matt Moore, Brian Intile
	 */
	public void normalize()
	{
		//Precalculate sign, create iterator and tally
		int sign = sign();
		LeftRightIterator<Integer> iterator = digits.iterator(DoublyLinkedList.Side.Left);
		int tally = 0;
		
		while (iterator.hasRight())
		{
			int digit = iterator.right();
			if (digit == ((sign < 0) ? 9 : 0))	//Leading 0 or 9
			{
				tally++;
			}
			else	//Found non 0 or 9
			{
				boolean needsLeadingDigit;	//Calculate whether we need one leading 9 or 0
				if (sign < 0)
					needsLeadingDigit = digit <= 4;
				else
					needsLeadingDigit = digit > 4;
				if (needsLeadingDigit)		//Subtract 1 from our removal tally if we do
					tally--;
				for (int i = 0; i < tally; i ++)	//Remove digits corresponding to our tally
					digits.removeLeft();
				return;
			}
		}
		
		//At this point we've reached end of list and found all 9's or 0's (or empty)
		digits = new DoublyLinkedList<Integer>();
		digits.addLeft((sign < 0) ? 9 : 0);
		return;
	}
	
	/**Determines whether the two numbers are equal or not, using the compareTo method
	 * @param o number to compare to
	 * @return boolean (If difference between the two is zero, they are equal, return true)
	 * @author Matt Moore
	 */
	public boolean equals(BigNumber o)
	{
		return compareTo(o) == 0;
	}
	
	/**Compares two numbers by subtraction, returning the sign of the difference
	 * @param o number to subtract
	 * @return int (the sign of the difference)
	 * @author Matt Moore
	 */
	public int compareTo(BigNumber o)
	{
		return this.subtract(o).sign();
	}
	
	/**Displays number in human-readable representation
	 * @return string (info - textual representation of the number)
	 * @author Matt Moore
	 */
	public String toString ()
	{	//Check for negative to add "human representation" (negative sign)
		int sign = sign();
		if (sign < 0)
			return "-" + negate().toString();

		normalize();	
		String info = "";
		LeftRightIterator<Integer> iterator = digits.iterator(DoublyLinkedList.Side.Left);
		int firstDigit = iterator.right();
		//Include first digit if it's non-zero OR if it's the only digit
		if (firstDigit != 0 || !iterator.hasRight())
			info += firstDigit;
		//Iterating right through the number, concatenating the string with each digit
		while (iterator.hasRight())
		{
			info += iterator.right();
		}
		
		return info;
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

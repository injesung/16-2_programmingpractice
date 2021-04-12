
public class MaxHeap<T extends Comparable<T>> extends Heap<T>
{

	/**
	 * Takes in two Comparable objects to find which has higher priority
	 * 
	 * @param one
	 *            An element to be prioritized
	 * @param two
	 *            An element to be prioritized
	 * @return a positive value if parameter one is greater then parameter two,
	 *         zero if compareTo returns zero, a negative value if parameter two
	 *         is greater then parameter one
	 */
	@Override
	public int priority(T one, T two)
	{
		if (one.compareTo(two) == -1)
		{
			return -1;
		}
		else if (one.compareTo(two) == 0)
		{
			return 0;
		}
		else
		{
			return 1;
		}
	}
}

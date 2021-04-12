import java.util.Set;
import java.util.LinkedList;

public class HeapSort
{

	/**
	 * This method will take in a set of Comparable values that will then be
	 * sorted using a heap
	 * 
	 * @param set
	 *            The set of type V elements to be heap sorted
	 * @return a LinkedList with the elements from the set parameter in sorted
	 *         order
	 */
	public static <T extends Comparable<T>> LinkedList<T> heapSort(Set<T> set)
	{
		LinkedList<T> ret = new LinkedList<T>();
		MinHeap<T> sorter = new MinHeap<T>();
		
		sorter.addAll(set);
		int index=sorter.size();
		for(int i=0;i<index;i++)
		{
			ret.add(sorter.remove());
		}
		
		return ret;
	}
//		LinkedList<T> ret = new LinkedList<T>();
//		MaxHeap<T> sorter = new MaxHeap<T>();
//		
//		sorter.addAll(set);
//		int index=sorter.size();
//		for(int i=0;i<index;i++)
//		{
//			ret.add(sorter.remove());
//		}
//		
//		return ret;
//	}
}


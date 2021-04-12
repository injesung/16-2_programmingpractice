import java.util.Collection;

public abstract class Heap<T extends Comparable<T>> implements HeapInterface<T>
{

	public abstract int priority(T one, T two);

	/**
	 * This is the array containing your data. You MUST leave index 0 empty or
	 * your grade will be low. So start putting stuff at index 1, this will
	 * actually make the heap easier to implement.
	 */
	private T[] array;
	private int size;

	/**
	 * Initializes the array with a default capacity of 11, and initializes the
	 * size to 0.
	 */
	
	public T[] getarray()
	{
		return array;
	}
	@SuppressWarnings("unchecked")
	public Heap()
	{
		// TODO Auto-generated method stub
		size = 0;
		array = (T[]) new Comparable[11];
		size = 0;
	}

	/**
	 * Initializes the array with the given capacity, you do not need to do any
	 * checks on the capacity, it will be valid in our tests. and initialize the
	 * size to 0.
	 * 
	 * @param capacity
	 *            the starting capacity of the heap
	 */
	@SuppressWarnings("unchecked")
	public Heap(int capacity)
	{
		// TODO Auto-generated method stub
		array = (T[]) new Comparable[capacity+1]; // remove시에 필요한 공간 1개 추가
		size = 0;
	}

	@Override
	public boolean isEmpty()
	{
		if (size == 0)
			return true;
		else
			return false;
	}

	@Override
	public int size()
	{
		// TODO Auto-generated method stub
		return size;
	}

	@Override
	public T getFirst()
	{
		// TODO Auto-generated method stub
		return array[1];
	}

	@Override
	public T remove()
	{
		// TODO Auto-generated method stub
		T ret = null;
		if (array.length > 0)
		{
			ret = array[1];
			size--;
			array[1] = array[size+1];
			int i = 1;
			boolean a = size <= 1;
			while (!a)
			{
				int l = i * 2;
				int r = i * 2 + 1;
				if (l < size)
				{
					if (r < size && priority(array[i], array[r]) == -1)
					{
						if (priority(array[l], array[r]) == -1)
						{
							l = r;
						}
					}
					if (priority(array[i], array[l]) == -1)
					{
						T temp = array[i];
						array[i] = array[l];
						array[l] = temp;
						i = l;
					}
					else
						a = true;
				}
				else
					a = true;
			}
		}
		array[size+1]=null;
		return ret;
	}

	@Override
	public void add(T data)
	{
		// TODO Auto-generated method stub
		size++;
		array[size] = data;

		int i = size;
		int p = i / 2;
		while (p != 0 && i > 1 && priority(array[p], array[i]) == -1)
		{

			T temp = array[i];
			array[i] = array[p];
			array[p] = temp;
			i = p;
			p = (p - 1) / 2 + 1;
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public void addAll(Collection<T> elements)
	{
		// TODO Auto-generated method stub
		T[] temp = elements.toArray((T[]) new Comparable[elements.size()]);
		for (int i = 0; i < temp.length; i++)
		{
			this.add(temp[i]);
		}
	}
	public void removeAll()
	{
		for(int i=0;i<array.length;i++)
		{
			array[i]=null;
		}
	}
	// do not modify methods below this point, they are for grading purposes

	@Override
	public T[] getArray()
	{
		return array;
	}

	@Override
	public void setArray(T[] array)
	{
		this.array = array;
	}

	@Override
	public void setSize(int size)
	{
		this.size = size;
	}
}

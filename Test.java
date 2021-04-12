import java.util.LinkedList;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;

public class Test
{

	public static void main(String[] args)
	{
		HeapSort temp = new HeapSort();
		int[] num = { 10,2,-20,19,29,-30,5,4,32,3};
		Set<Integer> set = new TreeSet<Integer>();
		LinkedList<Integer> res = new LinkedList<Integer>();
		for(int i=0;i<num.length;i++)
		{
			set.add(num[i]);
		}
		res = HeapSort.heapSort(set);
		int index = res.size();
		for(int i=0;i<index;i++)
		{
			System.out.println(res.removeFirst());
		}
	}
}
//		Heap<Integer> Msorter = new MaxHeap<Integer>();
//		Heap<Integer> msorter = new MinHeap<Integer>();
//		Scanner scan=new Scanner(System.in);
//		int T = scan.nextInt();
//		for(int i=0;i<T;i++)
//		{
//			int k = scan.nextInt();
//			String op;
//			int num;
//			
//			for(int j=0;j<k;j++)
//			{
//				op=scan.next();
//				num=scan.nextInt();
//				if(op.equals("I"))
//				{
//					Msorter.add(num);
//					msorter.add(num);
//				}
//				else if(op.equals("D"))
//				{
//					if(msorter.isEmpty() && Msorter.isEmpty())
//					{
//						System.out.println("EMPTY");
//					}
//					else if(num==-1)
//					{
//						msorter.remove();
//						Integer []temp = msorter.getarray();
//						Msorter.removeAll();
//						for(int i1=1;i1<=msorter.size();i1++)
//						{
//							Msorter.add(temp[i]);
//						}
//					}
//					else if(num==1)
//					{
//						Msorter.remove();
//						Integer []temp = Msorter.getarray();
//						msorter.removeAll();
//						for(int i1=1;i1<=Msorter.size();i1++)
//						{
//							msorter.add(temp[i]);
//						}
//					}
//					else
//					{
//						
//					}
//				}
//			}
//		}
//		scan.close();
//		
//		System.out.println(Msorter.remove()+" "+msorter.remove());
//		
//	}
//
//}

















import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Term2
{
	/*
	 * Complete the function below.
	 */
	static int minimalCost(int n, String[] pairs)
	{
		boolean[][] linkage = new boolean[n][n];//������踦 ������ �迭
		int[] group = new int[n];//���� �׷��� ������ ��������� ����Ǿ��ִ�
		for (int i = 0; i < n; i++)
		{
			group[i] = -1;//group�� 0������ �����Ұ��̹Ƿ� -1�� �ʱ�ȭ
						//group�� ���� -1�� ������� ���� �Ҽ��� �������� ���� �����
			linkage[i][i] = true;//�ڱ� �ڽ����� ���ƿ��� ������ �����Ѵٰ� ����
		}

		for (int i = 0; i < pairs.length; i++)
		{
			String[] temp = pairs[i].split(" ");//�Է¹��� �������踦 �迭ȭ
			int dest1 = Integer.parseInt(temp[0]);
			int dest2 = Integer.parseInt(temp[1]);
			linkage[dest1 - 1][dest1 - 1] = true;
			linkage[dest2 - 1][dest2 - 1] = true;
			linkage[dest1 - 1][dest2 - 1] = true;
			linkage[dest2 - 1][dest1 - 1] = true;
		}
		int groupindex = 0;//�׷��� �ε���
		for (int i = 0; i < n; i++)
		{
			for (int j = 0; j < n; j++)
			{
				if (linkage[i][j] && group[i] == -1 && group[j] == -1)
				{//���� ����� �ΰ��� ������� �Ѵ� group�� �������� �������
					group[i] = groupindex;
					group[j] = groupindex;
					//���ο� �׷��� ����� �ΰ��� ���� �׷��� ������.
				}
				else if (linkage[i][j] && group[i] != -1 && group[j] == -1)
				{//����� �� ������� �Ѱ��� �׷��� �ְ� �Ѱ��� �׷쿡 �������� ������
					group[j] = group[i];
					//�� ������� ���� ������ְ� �̹� �׷��� ������ �ִ� ������� ���ؼ�
					//�� �׷��� �������� ���������̹Ƿ� �׷��� ���� ������� �׷���
					//�׷��� ���� ������� �׷����� ���Եȴ�.
				}
				else if (linkage[i][j] && group[j] != -1 && group[i] == -1)
				{//���� ����.
					group[i] = group[j];
				}
				else if (linkage[i][j] && (group[i] != -1 && group[j] != -1) && group[i] != group[j])
				{//���� ��ȣ ����� �� ����� ��� �׷쿡 �������� ��
					int temp = group[j];//���� �׷쿡 �ٸ��� �׷��� �����Ű�� ���� �� �׷��� �������� �����.
					for (int l = 0; l < group.length; l++)
					{//�ٸ� �׷����� ���Խ�ų ��������� ã�Ƽ�
						//�������� ���� �׷����� ��� �ű��.
						if (group[l] == temp)
							group[l] = group[i];
					}
				}
			}
			groupindex++;//j for loop�� �ѻ���Ŭ ���� �Ǹ� ���ο� �׷� ��ȣ�� �������Ѽ�
			//���ο� �׷��� ������ش�.

		}
		int[] count = new int[n];//�� �׷쿡 ���� ������� ���� ã������ �迭
		for (int i = 0; i < n; i++)
			count[i] = 0;
		for (int i = 0; i < n; i++)
		{
			for (int j = 0; j < n; j++)
			{
				if (group[j] == i)
				{
					count[i]++;//��� ������� ���� �ش� ������� ���� �׷���� Ȯ���Ͽ�
					//�� ���� �׷쿡 ��� ������� �ִ��� Ȯ���Ѵ�.
				}
			}
		}//������.
		int res = 0;
		for (int i = 0; i < n; i++)
		{
			res = res + (int) Math.ceil(Math.sqrt(count[i]));
		}
		return res;
	}
	// ������ ���̽��� �����ϱ� ���� list�� ���� ������ �Լ�.
//	 static int minimalCost(int n, String[] pairs)
//	 {
//	 LinkedList<LinkedList<Integer>> linkage = new
//	 LinkedList<LinkedList<Integer>>();
//	
//	 for (int i = 0; i < n; i++)
//	 {
//	 linkage.add(new LinkedList<Integer>());
//	 }
//	 for (int i = 0; i < n; i++)
//	 {
//	 for (int j = 0; j < n; j++)
//	 {
//	 linkage.get(i).push(0);
//	 }
//	 }
//	 int[] group = new int[n];
//	 for (int i = 0; i < n; i++)
//	 {
//	 group[i] = -1;
//	 linkage.get(i).set(i, 1);
//	 }
//	
//	 for (int i = 0; i < pairs.length; i++)
//	 {
//	 String[] temp = pairs[i].split(" ");
//	 int dest1 = Integer.parseInt(temp[0]);
//	 int dest2 = Integer.parseInt(temp[1]);
//	 linkage.get(dest1 - 1).set(dest1 - 1, 1);
//	 linkage.get(dest2 - 1).set(dest2 - 1, 1);
//	 linkage.get(dest1 - 1).set(dest2 - 1, 1);
//	 linkage.get(dest2 - 1).set(dest1 - 1, 1);
//	 }
//	 // printArray(n, n, linkage);
//	 int groupindex = 0;
//	 for (int i = 0; i < n; i++)
//	 {
//	 for (int j = 0; j < n; j++)
//	 {
//	 if (linkage.get(i).get(j) == 1 && group[i] == -1 && group[j] == -1)
//	 {
//	 group[i] = groupindex;
//	 group[j] = groupindex;
//	 }
//	 else if (linkage.get(i).get(j) == 1 && group[i] != -1 && group[j] == -1)
//	 {
//	 group[j] = group[i];
//	 }
//	 else if (linkage.get(i).get(j) == 1 && group[j] != -1 && group[i] == -1)
//	 {
//	 group[i] = group[j];
//	 }
//	 else if (linkage.get(i).get(j) == 1 && (group[i] != -1 && group[j] != -1)
//	 && group[i] != group[j])
//	 {
//	 int temp = group[j];
//	 for (int l = 0; l < group.length; l++)
//	 {
//	 if (group[l] == temp)
//	 group[l] = group[i];
//	 }
//	 }
//	 }
//	 groupindex++;
//	
//	 }
//	 int[] count = new int[n];
//	 for (int i = 0; i < n; i++)
//	 count[i] = 0;
//	 for (int i = 0; i < n; i++)
//	 {
//	 for (int j = 0; j < n; j++)
//	 {
//	 if (group[j] == i)
//	 {
//	 count[i]++;
//	 }
//	 }
//	 }
//	 int res = 0;
//	 for (int i = 0; i < n; i++)
//	 {
//	 res = res + (int) Math.ceil(Math.sqrt(count[i]));
//	 }
//	 return res;
//	 }

	public static void main(String[] args) throws IOException
	{
		Scanner in = new Scanner(System.in);
		final String fileName = "D:\\UNIV\\����\\16-2 ���α׷��ֽǽ�\\Project2\\result.txt";// ���⿡
																					// ���ȭ���̸�
																					// ����.
		BufferedWriter bw = new BufferedWriter(new FileWriter(fileName));
		int res;
		int _n;
		_n = Integer.parseInt(in.nextLine().trim());

		int _pairs_size = 0;
		_pairs_size = Integer.parseInt(in.nextLine().trim());
		String[] _pairs = new String[_pairs_size];
		String _pairs_item;
		for (int _pairs_i = 0; _pairs_i < _pairs_size; _pairs_i++)
		{
			try
			{
				_pairs_item = in.nextLine();
			}
			catch (Exception e)
			{
				_pairs_item = null;
			}
			_pairs[_pairs_i] = _pairs_item;
		}

		res = minimalCost(_n, _pairs);
		bw.write(String.valueOf(res));
		bw.newLine();
		System.out.println("RESULT : " + res);
		bw.close();
	}
}
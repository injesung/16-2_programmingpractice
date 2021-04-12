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
		boolean[][] linkage = new boolean[n][n];//연결관계를 저장할 배열
		int[] group = new int[n];//같은 그룹을 가지는 연료봉끼리 연결되어있다
		for (int i = 0; i < n; i++)
		{
			group[i] = -1;//group을 0번부터 시작할것이므로 -1로 초기화
						//group의 값이 -1인 연료봉은 아직 소속이 정해지지 않은 연료봉
			linkage[i][i] = true;//자기 자신으로 돌아오는 간선이 존재한다고 가정
		}

		for (int i = 0; i < pairs.length; i++)
		{
			String[] temp = pairs[i].split(" ");//입력받은 간선관계를 배열화
			int dest1 = Integer.parseInt(temp[0]);
			int dest2 = Integer.parseInt(temp[1]);
			linkage[dest1 - 1][dest1 - 1] = true;
			linkage[dest2 - 1][dest2 - 1] = true;
			linkage[dest1 - 1][dest2 - 1] = true;
			linkage[dest2 - 1][dest1 - 1] = true;
		}
		int groupindex = 0;//그룹의 인덱스
		for (int i = 0; i < n; i++)
		{
			for (int j = 0; j < n; j++)
			{
				if (linkage[i][j] && group[i] == -1 && group[j] == -1)
				{//직접 연결된 두개의 연료봉이 둘다 group에 속해있지 않을경우
					group[i] = groupindex;
					group[j] = groupindex;
					//새로운 그룹을 만들어 두개가 같은 그룹을 가진다.
				}
				else if (linkage[i][j] && group[i] != -1 && group[j] == -1)
				{//연결된 두 연료봉중 한개는 그룹이 있고 한개는 그룹에 속해있지 않을때
					group[j] = group[i];
					//두 연료봉은 직접 연결되있고 이미 그룹을 가지고 있는 연료봉에 의해서
					//그 그룹의 연료봉들과 간접연결이므로 그룹이 없던 연료봉의 그룹은
					//그룹을 가진 연료봉의 그룹으로 편입된다.
				}
				else if (linkage[i][j] && group[j] != -1 && group[i] == -1)
				{//위와 같다.
					group[i] = group[j];
				}
				else if (linkage[i][j] && (group[i] != -1 && group[j] != -1) && group[i] != group[j])
				{//서로 상호 연결된 두 연료봉 모두 그룹에 속해있을 때
					int temp = group[j];//한쪽 그룹에 다른쪽 그룹을 흡수시키기 위해 한 그룹을 기준으로 잡느다.
					for (int l = 0; l < group.length; l++)
					{//다른 그룹으로 편입시킬 연료봉들을 찾아서
						//기준으로 잡은 그룹으로 모두 옮긴다.
						if (group[l] == temp)
							group[l] = group[i];
					}
				}
			}
			groupindex++;//j for loop가 한사이클 돌게 되면 새로운 그룹 번호를 증가시켜서
			//새로운 그룹을 만들어준다.

		}
		int[] count = new int[n];//각 그룹에 속한 연료봉의 수를 찾기위한 배열
		for (int i = 0; i < n; i++)
			count[i] = 0;
		for (int i = 0; i < n; i++)
		{
			for (int j = 0; j < n; j++)
			{
				if (group[j] == i)
				{
					count[i]++;//모든 연료봉에 대해 해당 연료봉이 속한 그룹들을 확인하여
					//그 각각 그룹에 몇개의 연료봉이 있는지 확인한다.
				}
			}
		}//계산과정.
		int res = 0;
		for (int i = 0; i < n; i++)
		{
			res = res + (int) Math.ceil(Math.sqrt(count[i]));
		}
		return res;
	}
	// 마지막 케이스를 실행하기 위한 list를 통해 구현한 함수.
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
		final String fileName = "D:\\UNIV\\과제\\16-2 프로그래밍실습\\Project2\\result.txt";// 여기에
																					// 출력화일이름
																					// 설정.
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
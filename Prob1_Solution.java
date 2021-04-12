import java.util.Scanner;

public class Solution
{
	public static long min(long a, long b)
	{// 조합에서 계산횟수를 줄이기 위한 최소값함수
		if (a > b)
			return b;
		else
			return a;
	}

	public static long makearray(boolean[][] array, int row, int column)
	{
		boolean[][] change = new boolean[row + 2][column + 2];
		for (int i = 0; i < row + 2; i++)
		{
			for (int j = 0; j < column + 2; j++)
			{
				change[i][j] = false;
			} // change 초기화
		}
		long room = 0;// 단독필드와 공동필드의 갯수를 담는 공간
		// Algorithm : (1,1)부터 시작해서 차례로 검사해 나갈때 자신이 Y이면 하단과 우측에 Y가 존재하는지 확인
		// 주변에 Y가 존재한다면 그것들을 모두 N으로 변경하고 change배열에서 해당위치를 true로 저장하여 변경사항 저장.
		for (int i = 1; i <= row; i++)
		{
			for (int j = 1; j <= column; j++)
			{
				if (array[i][j] && (change[i + 1][j] || change[i - 1][j] || change[i][j + 1] || change[i][j - 1]))
				{
					array[i][j] = false;
					change[i][j] = true;
					// 현재 검사하는 array가 Y이면서 그 array의 상하좌우에서 이미 변동내역이 존재한다면 Y끼리
					// 인접한 행렬이므로 자신을 N으로 바꿈.
					// 주변의 array가 N이어도 Y에서 N으로 변경되었다면 그 지점은 단독이 아닌 공동필드에 속해있다고 볼
					// 수 있으므로 그 자리를 N으로 바꾸고 변경을 저장함.
				}
				else if (array[i][j] && !change[i][j])
				{// 주변에 변동사항이 없고 현재 지점이 Y일 경우 주변을 탐사하여 자신을 제외한 모든 인접한 Y를 N으로
					// 변경하고 변경사항을 저장함.
					for (int k = 1; i + k <= row; k++)
					{
						if (array[i + k][j] && !change[i + k][j])
						{
							array[i + k][j] = false;
							change[i + k][j] = true;
							// 주변을 탐사하는 과정
							// 주변에 Y가 존재하면 그것을 N으로 변경하고 변경사항을 저장함
						}
						else
						{// 연속된 Y가 존재하다 N을 만났을 때
							if (i + k <= row)
							{
								if (change[i + k][j])
								{
									array[i][j] = false;
									change[i][j] = true;
								}
								// 만약 만난 N이 Y에서 N으로 변동되었던 N이라면
								// 처음 검사를 시작한 Y또한 다른 공동필드에 속해있는 Y이므로
								// 그 Y를 N으로 변경한다.
								break;
							}
						}
					}
					// 이하에 존재하는 코드들은 같은 방식으로 각각 방향에 따라 검사하는 코드이다.
					for (int k = 1; j + k <= column; k++)
					{
						if (!change[i][j + k] && array[i][j + k])
						{
							array[i][j + k] = false;
							change[i][j + k] = true;

						}
						else
						{
							if (j + k <= column)
							{
								if (change[i][j + k])
								{
									array[i][j] = false;
									change[i][j] = true;
								}
								break;
							}
						}
					}
					for (int k = 1; i - k >= 0; k++)
					{
						if (i - k < 0)
							break;
						if (!change[i - k][j] && array[i - k][j])
						{
							array[i - k][j] = false;
							change[i - k][j] = true;

						}
						else
						{
							if (i - k >= 0)
							{
								if (change[i - k][j])
								{
									array[i][j] = false;
									change[i][j] = true;
								}
								break;
							}
						}
					}
					for (int k = 1; j - k >= 0; k++)
					{
						if (j - k < 0)
							break;
						if (!change[i][j - k] && array[i][j - k])
						{
							array[i][j - k] = false;
							change[i][j - k] = true;

						}
						else
						{
							if (j - k >= 0)
							{
								if (change[i][j - k])
								{
									array[i][j] = false;
									change[i][j] = true;
								}
								break;
							}
						}
					}
				}
				else if (array[i][j] && change[i][j])
				{
					array[i][j] = false;
					change[i][j] = true;
				}
			}
		}
		// 위의 단계가 모두 종료되었을 때 array의 결과를 보면 단독 필드는 그 자체로 1개의 Y로 표기된다.
		// 공동필드에 대해서는 공동필드를 이루는 수많은 Y중 단 1개의 Y를 제외한 모든 Y가 N으로 바뀐 상태이다.
		// 따라서 현재 array에 남아있는 Y의 갯수를 세보면 단독필드 또는 공동필드의 갯수가 된다.
		for (int i = 1; i <= row; i++)
		{
			for (int j = 1; j <= column; j++)
			{
				if (array[i][j])
					room++;
			}
		}
		// 위의 loop를 통해 단독필드 혹은 공동필드의 갯수를 구할 수 있고 이것을 반환한다.
		return room;
	}

	public static long combination(long n, long p)
	{
		// 조합을 구하는 함수. Overflow를 최소화시키기 위해
		// factorial을 사용하지 않고 직접계산함.
		long ret = 1;
		for (int i = (int) n; i > n - p; i--)
		{
			ret = ret % 100000007 * i;
		}
		long ret2 = 1;
		for (int i = (int) p; i >= 1; i--)
		{
			ret2 = ret2 % 100000007 * i;
		}
		if (ret2 == 0)
			ret2 = 1;
		return (ret / ret2) % 100000007;
	}

	public static void main(String[] args)
	{
		// TODO 자동 생성된 메소드 스텁
		@SuppressWarnings("resource")
		Scanner scan = new Scanner(System.in);
		final int row = scan.nextInt();// 행의 갯수를 input
		String temp = scan.next();
		final int column = temp.length();// 행의갯수와 열의 갯수가 일치하는 정사각행렬
		if (row >= 1 && row <= 5000 || column >= 1 && column <= 5000)// constraints를
																		// 만족할때
		{
			String input;
			char[] inputinfo = new char[column + 1];
			boolean[][] field = new boolean[row + 2][column + 2];
			// 행렬생성 -> 가장자리에 여백을 만들어둠.
			for (int i = 1; i <= row; i++)
			{
				if (i != 1)
				{
					input = scan.next();// Y혹은 N 사이에 공백이 없으므로 줄 단위로 통째로
										// 저장
					inputinfo = new char[column + 1];// 문자열 분리를
				} // 위한 array
				else
					input = temp;
				for (int j = 1; j <= column; j++)
				{
					inputinfo[j] = input.charAt(j - 1);// 문자열을 분리하여 문자로 만든후
					if (inputinfo[j] == 'N')
					{// N이라면 field에 false
						field[i][j] = false;
					}
					else if (inputinfo[j] == 'Y')
					{// Y라면 field에 true
						field[i][j] = true;
					}
					else
					{// 잘못된 입력이 있다면 메시지를 띄우고 프로그램 종료
						System.out.println("INPUT ERROR\nProgram terminate");
						System.exit(0);
					}
				}
			}
			long room = makearray(field, row, column);// 단독필드와 공동필드를 세는 함수
			// System.out.println("ROOM : " + room);
			long Case = 0;// 결과값을 만들 공간
			for (long sheep = 0; sheep <= room; sheep = sheep + 2)
			{// 짝수마리의 양이 존재할 수 있고, 각 필드에 1마리에 소 또는 양이 들어갈 수 있다.
				// 조합을 통해 경우의 수를 구할 수 있다.
				Case = (Case + combination(room, min(sheep, (room - sheep)))) % 100000007;
				// 계산 속도의 향상을 위해 sheep과 room-sheep중에 작은것을 입력한다.
				// 10C2와 10C8은 같은 값을 나타내지만 계산속드는 10C2가 더욱 편한것과 같다.
			}
			System.out.println("ANSWER : " + Case % 100000007);// 구해진 답을
																// 100000007로 나눈
																// 나머지로 바꾸어 출력.
		}
		else// constraints를 위반했을 때 메시지와 함께 프로그램 종료.
		{
			System.out.println("Array size error\nProgram terminate");
			System.exit(0);
		}
	}
}

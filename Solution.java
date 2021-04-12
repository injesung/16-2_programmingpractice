import java.util.Scanner;

public class Solution
{
	public static long min(long a, long b)
	{// ���տ��� ���Ƚ���� ���̱� ���� �ּҰ��Լ�
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
			} // change �ʱ�ȭ
		}
		long room = 0;// �ܵ��ʵ�� �����ʵ��� ������ ��� ����
		// Algorithm : (1,1)���� �����ؼ� ���ʷ� �˻��� ������ �ڽ��� Y�̸� �ϴܰ� ������ Y�� �����ϴ��� Ȯ��
		// �ֺ��� Y�� �����Ѵٸ� �װ͵��� ��� N���� �����ϰ� change�迭���� �ش���ġ�� true�� �����Ͽ� ������� ����.
		for (int i = 1; i <= row; i++)
		{
			for (int j = 1; j <= column; j++)
			{
				if (array[i][j] && (change[i + 1][j] || change[i - 1][j] || change[i][j + 1] || change[i][j - 1]))
				{
					array[i][j] = false;
					change[i][j] = true;
					// ���� �˻��ϴ� array�� Y�̸鼭 �� array�� �����¿쿡�� �̹� ���������� �����Ѵٸ� Y����
					// ������ ����̹Ƿ� �ڽ��� N���� �ٲ�.
					// �ֺ��� array�� N�̾ Y���� N���� ����Ǿ��ٸ� �� ������ �ܵ��� �ƴ� �����ʵ忡 �����ִٰ� ��
					// �� �����Ƿ� �� �ڸ��� N���� �ٲٰ� ������ ������.
				}
				else if (array[i][j] && !change[i][j])
				{// �ֺ��� ���������� ���� ���� ������ Y�� ��� �ֺ��� Ž���Ͽ� �ڽ��� ������ ��� ������ Y�� N����
					// �����ϰ� ��������� ������.
					for (int k = 1; i + k <= row; k++)
					{
						if (array[i + k][j] && !change[i + k][j])
						{
							array[i + k][j] = false;
							change[i + k][j] = true;
							// �ֺ��� Ž���ϴ� ����
							// �ֺ��� Y�� �����ϸ� �װ��� N���� �����ϰ� ��������� ������
						}
						else
						{// ���ӵ� Y�� �����ϴ� N�� ������ ��
							if (i + k <= row)
							{
								if (change[i + k][j])
								{
									array[i][j] = false;
									change[i][j] = true;
								}
								// ���� ���� N�� Y���� N���� �����Ǿ��� N�̶��
								// ó�� �˻縦 ������ Y���� �ٸ� �����ʵ忡 �����ִ� Y�̹Ƿ�
								// �� Y�� N���� �����Ѵ�.
								break;
							}
						}
					}
					// ���Ͽ� �����ϴ� �ڵ���� ���� ������� ���� ���⿡ ���� �˻��ϴ� �ڵ��̴�.
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
		// ���� �ܰ谡 ��� ����Ǿ��� �� array�� ����� ���� �ܵ� �ʵ�� �� ��ü�� 1���� Y�� ǥ��ȴ�.
		// �����ʵ忡 ���ؼ��� �����ʵ带 �̷�� ������ Y�� �� 1���� Y�� ������ ��� Y�� N���� �ٲ� �����̴�.
		// ���� ���� array�� �����ִ� Y�� ������ ������ �ܵ��ʵ� �Ǵ� �����ʵ��� ������ �ȴ�.
		for (int i = 1; i <= row; i++)
		{
			for (int j = 1; j <= column; j++)
			{
				if (array[i][j])
					room++;
			}
		}
		// ���� loop�� ���� �ܵ��ʵ� Ȥ�� �����ʵ��� ������ ���� �� �ְ� �̰��� ��ȯ�Ѵ�.
		return room;
	}

	public static long combination(long n, long p)
	{
		// ������ ���ϴ� �Լ�. Overflow�� �ּ�ȭ��Ű�� ����
		// factorial�� ������� �ʰ� ���������.
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
		// TODO �ڵ� ������ �޼ҵ� ����
		@SuppressWarnings("resource")
		Scanner scan = new Scanner(System.in);
		final int row = scan.nextInt();// ���� ������ input
		String temp = scan.next();
		final int column = temp.length();// ���ǰ����� ���� ������ ��ġ�ϴ� ���簢���
		if (row >= 1 && row <= 5000 || column >= 1 && column <= 5000)// constraints��
																		// �����Ҷ�
		{
			String input;
			char[] inputinfo = new char[column + 1];
			boolean[][] field = new boolean[row + 2][column + 2];
			// ��Ļ��� -> �����ڸ��� ������ ������.
			for (int i = 1; i <= row; i++)
			{
				if (i != 1)
				{
					input = scan.next();// YȤ�� N ���̿� ������ �����Ƿ� �� ������ ��°��
										// ����
					inputinfo = new char[column + 1];// ���ڿ� �и���
				} // ���� array
				else
					input = temp;
				for (int j = 1; j <= column; j++)
				{
					inputinfo[j] = input.charAt(j - 1);// ���ڿ��� �и��Ͽ� ���ڷ� ������
					if (inputinfo[j] == 'N')
					{// N�̶�� field�� false
						field[i][j] = false;
					}
					else if (inputinfo[j] == 'Y')
					{// Y��� field�� true
						field[i][j] = true;
					}
					else
					{// �߸��� �Է��� �ִٸ� �޽����� ���� ���α׷� ����
						System.out.println("INPUT ERROR\nProgram terminate");
						System.exit(0);
					}
				}
			}
			long room = makearray(field, row, column);// �ܵ��ʵ�� �����ʵ带 ���� �Լ�
			// System.out.println("ROOM : " + room);
			long Case = 0;// ������� ���� ����
			for (long sheep = 0; sheep <= room; sheep = sheep + 2)
			{// ¦�������� ���� ������ �� �ְ�, �� �ʵ忡 1������ �� �Ǵ� ���� �� �� �ִ�.
				// ������ ���� ����� ���� ���� �� �ִ�.
				Case = (Case + combination(room, min(sheep, (room - sheep)))) % 100000007;
				// ��� �ӵ��� ����� ���� sheep�� room-sheep�߿� �������� �Է��Ѵ�.
				// 10C2�� 10C8�� ���� ���� ��Ÿ������ ���ӵ�� 10C2�� ���� ���ѰͰ� ����.
			}
			System.out.println("ANSWER : " + Case % 100000007);// ������ ����
																// 100000007�� ����
																// �������� �ٲپ� ���.
		}
		else// constraints�� �������� �� �޽����� �Բ� ���α׷� ����.
		{
			System.out.println("Array size error\nProgram terminate");
			System.exit(0);
		}
	}
}
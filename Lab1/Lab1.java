import java.util.LinkedList;
import java.util.Random;

public class Lab1
{
	public static void main (String[] args)
	{
		// changeable
		int SIZE = 200;
		double P = .60;
		double DELTA = .005;
		double CUTOFF = .9;
		int N = 100;

		Grid g = new Grid(SIZE);
		boolean q = false;			// found percolation constant
		int i;
		int count;

		while (!q)
		{
			count = 0;
			i = 0;
			System.out.println("*** Attempting p = " + P + " ***");
			while (i < N)
			{
				System.out.print(".");
				g.clear();
				g.populate(P);
				if (g.hasPath())
					count++;
				i++;
			}
			System.out.println();

			if (count / N > CUTOFF)
				q = true;
			else
				P += DELTA;
		}

		System.out.println("\n\n\nPc Found!  Pc = " + P);
	}

	public static void pyramid (int n)
	{
		for (int i = 0; i < n; i++)
		{
			for (int j = 0; j < n - i - 1; j++)
				System.out.print("  ");

			System.out.print("*");

			for (int k = 0; k < 2 * i; k++)
				System.out.print(" *");

			System.out.println();
		}
	}

	public static String generateHexCode (int NUM_DIGITS)
	{
		Random r = new Random();
		int digit;
		final int BASE = 16;
		String s = "";

		for (int i = 0; i < NUM_DIGITS; i++)
			if ((digit = r.nextInt(BASE)) == 0)		s += "0";
			else if (digit == 1)					s += "1";
			else if (digit == 2)					s += "2";
			else if (digit == 3)					s += "3";
			else if (digit == 4)					s += "4";
			else if (digit == 5)					s += "5";
			else if (digit == 6)					s += "6";
			else if (digit == 7)					s += "7";
			else if (digit == 8)					s += "8";
			else if (digit == 9)					s += "9";
			else if (digit == 10)					s += "A";
			else if (digit == 11)					s += "B";
			else if (digit == 12)					s += "C";
			else if (digit == 13)					s += "D";
			else if (digit == 14)					s += "E";
			else									s += "F";

			return s;
	}
}

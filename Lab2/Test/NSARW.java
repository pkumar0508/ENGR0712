import java.util.Random;

public class NSARW
{
	private int[] x;
	private int[] y;
	private int n;
	
	public NSARW (int n)
	{
		this.n = n;
		x = new int[n];
		y = new int[n];
	}
	
	public void generate ()
	{
		int X = 0, Y = 0;
		x = new int[n];
		y = new int[n];
		x[0] = X;
		y[0] = Y;
		Random r = new Random();
		int flag;
		
		for (int i = 1; i < n; i++)
		{
			flag = r.nextInt(4);
			
			if (flag == 0)
				X += 1;
			else if (flag == 1)
				X -= 1;
			else if (flag == 2)
				Y += 1;
			else if (flag == 3)
				Y -= 1;
			
			x[i] = X;
			y[i] = Y;
		}
	}
	
	public void translate (int size, int scale)
	{
		for (int i = 0; i < n; i++)
		{
			x[i] *= scale;
			y[i] *= scale;
			x[i] += size / 2;
			y[i] += size / 2;
			x[i] *= -1;
			y[i] *= -1;
		}
	}
	
	public int[] x ()
	{ return x; }
	
	public int[] y ()
	{ return y; }
}
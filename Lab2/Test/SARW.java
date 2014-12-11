import java.util.Random;
import java.util.ArrayList;

public class SARW
{
	public int[] x, y;
	public int n;
	public boolean[][] visited;
	private static Random r = new Random();
	public int crash;
	
	public SARW (int N)
	{ reset(N); }
	
	public void reset (int N)
	{
		n = N;
		x = new int[n];
		y = new int[n];
		visited = new boolean[2 * n - 1][2 * n - 1];
		crash = 5;
	}
	
	public Position positionMap (Position p)
	{ return new Position (n - 1 - p.y(), n - 1 + p.x()); }
	
	public boolean move (Position p)
	{
		ArrayList l = new ArrayList();
		int X, Y;
		Position next;
		
		validAdd(l, p.north());
		validAdd(l, p.south());
		validAdd(l, p.east());
		validAdd(l, p.west());
														//
		String s = l.toString();
		for (int q = s.length(); q < 40; q++)
			s += " ";
		System.out.print(s + "-->\t");
														//
		if (l.isEmpty())
			return false;
		
		next = (Position) l.get(r.nextInt(l.size()));
		System.out.println(next);						//
		p.setX(next.x());
		p.setY(next.y());
		return true;
	}
	
	public void validAdd (ArrayList l, Position p)
	{
		Position q = positionMap(p);
		if (q.x() >= 0 && q.x() < visited.length &&
			q.y() >= 0 && q.y() < visited.length &&
			!visited[q.x()][q.y()])
		{
			l.add(p);
		}
	}
	
	public boolean generate ()
	{
		reset(n);
		Position p = new Position();
		visit(p, 0);
		return generate(p, 1);
	}
	
	public boolean generate (Position p, int i)
	{
		while (i < n && move(p))
		{
			visit(p, i);
			i++;
		}
		
		if (i != n)
			return backtrack(i);
		else
			return true;
	}
	
	public boolean backtrack (int i)
	{
		int c = 0;
		crash = optimize(i);
		
		while (c < crash)
		{
			i--;
			Position p = new Position(x[i], y[i]);
			Position q = positionMap(p);
			visited[q.x()][q.y()] = false;
			c++;
		}
		System.out.println("Backtrack called:  " + crash + " " + i);
		return generate(new Position(x[i - 1], y[i - 1]), i);
	}
	
	public int optimize (int i)
	{
		if (i < 50)
			return 7;
		if (i < 100)
			return 10;
		if (i < 200)
			return 15;
		if (i < 400)
			return 20;
		if (i < 600)
			return 30;
		
			return 35;
	}
	
	public void visit (Position p, int i)
	{
		Position q = positionMap(p);
		visited[q.x()][q.y()] = true;
		x[i] = p.x();
		y[i] = p.y();
	}
	
	public void translate (int size)
	{
		for (int i = 0; i < n; i++)
		{
			x[i] += size / 2;
			y[i] += size / 2;
		}
	}
	
	public int[] x ()
	{ return x; }
	
	public int[] y ()
	{ return y; }
	
	public String toString()
	{
		String s = "";
		for (int i = 0; i < visited.length; i++)
		{
			for (int j = 0; j < visited[i].length; j++)
				if (visited[i][j])
					s += "1 ";
				else
					s += "0 ";
			s += "\n";
		}
		return s;
	}
}
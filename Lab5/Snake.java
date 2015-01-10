import java.util.*;
import java.awt.Graphics;

public class Snake
{
	private LinkedList<Position> snake;
	private final int SIZE;
	private final int SCALE;
	private static final Random rand = new Random();
	
	public Snake (int size, Position[] p, int scale)
	{
		SIZE = size / scale;
		SCALE = scale;
		
		snake = new LinkedList<>();
		for (int i = 0; i < p.length; i++)
			snake.add(p[i]);
	}
	
	public void nextState ()
	{
		boolean atHead[] = new boolean[6];
		Position next, temp;
		ArrayList<Position> l1, l2, l = new ArrayList<>();
		int choice;
		
		temp = (Position) snake.getFirst();
		l1 = validNextPos(temp);
		for (int i = 0; i < l1.size(); i++)
			atHead[i] = true;
		temp = (Position) snake.getLast();
		l2 = validNextPos(temp);
		
		l.addAll(l1);
		l.addAll(l2);
		
		choice = rand.nextInt(l.size());
		next = (Position) l.get(choice);
		
		if (atHead[choice])
		{
			snake.removeLast();
			snake.addFirst(next);
		}
		else
		{
			snake.removeFirst();
			snake.addLast(next);
		}
	}
	
	private Position map (Position p)
	{ return new Position(SIZE + p.x, SIZE - p.y); }
	
	public void paint (Graphics g)
	{
		int n = snake.size();
		int[] x = new int[n],
			  y = new int[n];
		Position p;
		
		for (int i = 0; i < n; i++)
		{
			p = (Position) snake.get(i);
			p = map(p);
			x[i] = p.x * (SCALE / 2);
			y[i] = p.y * (SCALE / 2);
		}
		
		g.drawPolyline(x, y, n);
	}
	
	private ArrayList<Position> validNextPos (Position p)
	{
		ArrayList<Position> l = new ArrayList<>();
		
		if (validPos(p.north()))	l.add(p.north());
		if (validPos(p.south()))	l.add(p.south());
		if (validPos(p.east()))		l.add(p.east());
		if (validPos(p.west()))		l.add(p.west());
		
		return l;
	}
	
	private boolean validPos (Position p)
	{
		return p.x >= -SIZE && p.x <= SIZE &&
			   p.y >= -SIZE && p.y <= SIZE &&
			   !(new HashSet<Position>(snake)).contains(p);
	}
}
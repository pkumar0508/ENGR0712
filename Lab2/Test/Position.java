public class Position
{
	int x, y;
	
	public Position ()
	{
		x = 0;
		y = 0;
	}
	
	public Position (int X, int Y)
	{
		x = X;
		y = Y;
	}
	
	public int x ()
	{ return x; }
	
	public int y ()
	{ return y; }
	
	public Position north ()
	{ return new Position(x - 1, y); }
	
	public Position south ()
	{ return new Position(x + 1, y); }
	
	public Position east ()
	{ return new Position(x, y + 1); }
	
	public Position west ()
	{ return new Position(x, y - 1); }
	
	public void setX (int X)
	{ x = X; }
	
	public void setY (int Y)
	{ y = Y; }
	
	public boolean equals (Object obj)
	{
		Position p = (Position) obj;
		return x == p.x() && y == p.y();
	}
	
	public String toString ()
	{ return "(" + x + ", " + y + ")"; }
}
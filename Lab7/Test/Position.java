public class Position
{
	// variables
	public int x, y;
	
	// constructors
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
	
	// directions
	public Position north ()
	{ return new Position(x - 1, y); }
	
	public Position south ()
	{ return new Position(x + 1, y); }
	
	public Position east ()
	{ return new Position(x, y + 1); }
	
	public Position west ()
	{ return new Position(x, y - 1); }
	
	// required
	public boolean equals (Object obj)
	{
		Position p = (Position) obj;
		return x == p.x && y == p.y;
	}
	
	public int hashCode ()
	{ return (250 + x * x * x * x) * (179 - y * y * y); }
	
	// toString
	public String toString ()
	{ return "(" + x + ", " + y + ")"; }
}
package app.pkumar0508.engr0712.lab8;

import java.util.ArrayList;

public class Position
{
	public int x, y;
	
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
	
	public Position north ()
	{ return new Position(x - 1, y); }
	
	public Position south ()
	{ return new Position(x + 1, y); }
	
	public Position east ()
	{ return new Position(x, y + 1); }
	
	public Position west ()
	{ return new Position(x, y - 1); }
	
	public boolean equals (Object obj)
	{
		Position p = (Position) obj;
		return x == p.x && y == p.y;
	}
	
	public String toString ()
	{ return "(" + x + ", " + y + ")"; }
	
	public int hashCode ()
	{ return (250 + x * x * x * x) * (179 - y * y * y); }
	
	public ArrayList<Position> neighbors ()
	{
		ArrayList<Position> l = new ArrayList<>();
		
		l.add(new Position(x + 1, y + 1));
		l.add(new Position(x + 1, y - 1));
		l.add(new Position(x - 1, y + 1));
		l.add(new Position(x - 1, y - 1));
		l.add(north());
		l.add(south());
		l.add(east());
		l.add(west());
		
		return l;
	}
}

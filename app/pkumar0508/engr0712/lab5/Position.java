package app.pkumar0508.engr0712.lab5;

import java.util.Random;

public class Position
{
	private static final double CIRCLE = 8 * Math.atan(1.0);
	private static final Random rand = new Random();
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
	
	public static Position random (double r)
	{
		Position p = new Position();
		double theta = CIRCLE * rand.nextDouble();
		
		p.x = (int) (r * Math.cos(theta));
		p.y = (int) (r * Math.sin(theta));
		
		return p;
	}
	
	public double norm ()
	{ return Math.sqrt(x * x + y * y); }
	
	public int hashCode ()
	{ return (250 + x * x * x * x) * (179 - y * y * y); }
}

package app.pkumar0508.engr0712.lab4;

import java.util.ArrayList;
import java.awt.Color;

public class Particle
{
	protected Position p;
	protected Color c;
	
	public Particle (Color C)
	{
		p = null;
		c = C;
	}
	
	public Particle (Position P, Color C)
	{
		p = P;
		c = C;
	}
	
	public void setPosition (Position P)
	{ p = P; }
	
	public Position position ()
	{ return p; }
	
	public Color color ()
	{ return c; }
	
	public String toString ()
	{ return "*"; }
	
	public int hashCode ()
	{ return p.hashCode() + c.hashCode(); }
}

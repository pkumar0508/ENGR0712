package app.pkumar0508.engr0712.lab6;

import java.util.HashSet;
import java.util.Random;
import java.awt.Color;
import java.awt.Graphics;

public class Grid
{
	// variables
	private static final int R = 15;
	private static final Random r = new Random();
	private static Color OIL = Color.yellow,
						 WATER = Color.blue;
	private static final int LIKE_ENERGY = -1,
							 OPPOSITE_ENERGY = 1;
	
	private final int SIZE;
	private final int SCALE;
	private HashSet<Position> s;
	
	// constructor
	public Grid (int size)
	{
		SIZE = size;
		SCALE = 1;
		s = new HashSet<>();
	}
	
	public Grid (int size, int scale)
	{
		SCALE = scale;
		SIZE = size / scale;
		s = new HashSet<>();
	}
	
	// basic functions
	public void populate (double P)
	{
		int n = (int) (SIZE * SIZE * P),
			i = 0;
		Position p;
		
		while (i < n)
		{
			p = randomPos();
			
			if (!get(p))
			{
				s.add(p);
				i++;
			}
		}
	}
	
	public boolean get (Position p)
	{ return s.contains(p); }
	
	private Position randomPos ()
	{ return new Position(r.nextInt(SIZE), r.nextInt(SIZE)); }
	
	private boolean validPos (Position p)
	{
		return p.x >= 0 && p.x < SIZE &&
			   p.y >= 0 && p.y < SIZE;
	}
	
	// method
	public void metropolis (int n, Graphics[] g)
	{
		Position p, q;
		double beta;
		
		for (int x = 0; x < n; x++)
			for (int i = 0; i < SIZE; i++)
				for (int j = 0; j < SIZE; j++)
				{
					p = new Position(i, j);
					while (get(p) == get(q = randomPos()) ||
						   p.equals(q));
					
					beta = optimize(x);
					attemptSwitch(p, q, beta, g);
					
					System.out.println(x);
				}
	}
	
	// swapping functions
	private void attemptSwitch (Position p, Position q,
								double beta, Graphics[] g)
	{
		int deltaE = deltaE(p, q);
		
		if (deltaE <= 0 || boltzmann(beta, deltaE))
			swap(p, q, g);
	}
	
	private int deltaE (Position p, Position q)
	{
		int Ei = 0, Ef = 0;
		int i, num;
		boolean flag = get(q);
		
		Ei = energy(q, flag);
		Ef = energy(p, flag);
		
		return Ef - Ei;
	}
	
	private int energy (Position p, boolean flag)
	{
		int i = 0, num = 0;
		Position[] list = { p.north(), p.east(),
							p.south(), p.west() };
		
		for (int x = 0; x < list.length; x++)
		{
			if (validPos(list[x])) {			num++;
				if (get(list[x]) == flag)		i++;
			}
		}
		
		return LIKE_ENERGY * i + OPPOSITE_ENERGY * (num - i);
	}
	
	private double optimize (int x)
	{ return Math.pow(x, .8) / 100.0; }
	
	private boolean boltzmann (double beta, int deltaE)
	{ return r.nextDouble() < Math.exp(-beta * deltaE); }
	
	private void swap (Position p, Position q, Graphics[] g)
	{
		boolean flag = get(p);
		
		s.remove(p);
		s.remove(q);
		
		if (flag)
			s.add(q);
		else
			s.add(p);
		
		drawParticle(p, g);
		drawParticle(q, g);
	}
	
	// paint functions
	public void paint (Graphics[] g)
	{
		for (int i = 0; i < SIZE; i++)
			for (int j = 0; j < SIZE; j++)
				drawParticle(new Position(i, j), g);
	}
	
	private void drawParticle (Position p, Graphics g[])
	{
		Color c;
		
		for (int x = 0; x < g.length; x++)
		{
			if (get(p))		c = OIL;
			else			c = WATER;
			
			g[x].setColor(c);
			g[x].fillOval(p.x * SCALE, p.y * SCALE, R, R);
		}
	}
	
	// overloads
	public void paint (Graphics g)
	{
		Graphics[] G = { g };
		paint(G);
	}
	
	public void metropolis (int n, Graphics g)
	{
		Graphics[] G = { g };
		metropolis(n, G);
	}
}

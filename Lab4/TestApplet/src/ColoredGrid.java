import java.awt.Graphics;
import java.awt.Color;
import java.util.Hashtable;
import java.util.ArrayList;

public class ColoredGrid extends Grid
{
	// constructors
	public ColoredGrid (int size)
	{ super(size); }
	
	public ColoredGrid (int size, int scale)
	{ super(size, scale); }
	
	// method overloads
	public void aggregate (int n, Color[] colors, Graphics g[])
	{ aggregate (n, 1.0, colors, g); }
	
	public void aggregate (int n, Color[] colors, Graphics g)
	{
		Graphics[] G = { g };
		aggregate (n, 1.0, colors, G);
	}
	
	public void aggregate (int n, double p, Color[] colors, Graphics g)
	{
		Graphics[] G = { g };
		aggregate (n, p, colors, G);
	}
	
	// method
	public void aggregate (int n, double prob, Color[] colors, Graphics g[])
	{
		int r, max = 10;
		Position p;
		Particle q;
		Color c;
		
		for (int i = 0; i < n; i++)
		{
			r = optimize(max);
			if (r == size())				break;
			
			c = colors[i % colors.length];
			p = randomWalk(r, prob, c);
			
			if (Math.abs(p.x) > max)	max = Math.abs(p.x);
			if (Math.abs(p.y) > max)	max = Math.abs(p.y);
			
			q = new Particle(p, c);
			put(q, g);
			drawParticle(g, q);
			debug(max, r, i, p, q);
		}
	}
	
	// overridden method helpers
	protected boolean hasESN (Position p, Color c)
	{
		ArrayList l = neighbors(p);
		boolean flag = false;
		Particle q;
		
		int i = 0;
		while (i < l.size() && !flag)
		{
			q = get((Position) l.get(i));
			
			if (q != null && q.color().equals(c))
				flag = true;
			i++;
		}
		
		return flag;
	}
	
	protected Position randomWalk (int r, double prob, Color c)
	{
		Position p;
		boolean endState;
		
		p = randomPosition(r);
		endState = false;
		while (!endState)
		{
			while (!hasESN(p, c))
				nextPos(p, r);
			
			if (nextDouble() < prob)
				endState = true;
			else
				nextPos(p, r);
		}
		
		return p;
	}
}
import java.util.HashMap;
import java.util.HashSet;
import java.util.Stack;

public class ClusterGrid
{
	// variables
	private Grid grid;
	private HashMap clusters;
	private int count;
	
	// constructors
	public ClusterGrid (Grid g)
	{
		grid = g;
		clusters = new HashMap();
		count = 0;
	}
	
	// accessors
	public int count ()
	{ return count; }
	
	public boolean hasPath ()
	{
		HashSet s = new HashSet();
		Position p;
		int i;
		boolean flag = false;
		
		i = grid.size();
		while (i >= -grid.size())
		{
			p = new Position(i, -grid.size());
			s.add(clusters.get(p));
			i--;
		}
		s.remove(null);
		
		i = grid.size();
		while (i >= -grid.size() && !flag)
		{
			p = new Position(i, grid.size());
			flag |= s.contains(clusters.get(p));
			i--;
		}
		
		return flag;
	}
	
	// method
	public void countClusters ()
	{
		Stack s = new Stack();
		Position p;
		clusters.clear();
		
		for (int i = -grid.size(); i <= grid.size(); i++)
			for (int j = -grid.size(); j <= grid.size(); j++)
			{
				p = new Position(i, j);
				if (notChecked(p))
				{
					count++;
					
					s.push(p);
					killBlob(s, count);
				}
			}
	}
	
	// method helper
	private void killBlob (Stack s, int c)
	{
		if (!s.empty())
		{
			Position p = (Position) s.pop();
			clusters.put(p, new Integer(c));
			
			if (validPos(p.north()))	s.push(p.north());
			if (validPos(p.south()))	s.push(p.south());
			if (validPos(p.east()))		s.push(p.east());
			if (validPos(p.west()))		s.push(p.west());
			
			killBlob(s, c);
		}
	}
	
	// tests
	private boolean notChecked (Position p)
	{ return grid.get(p) && (clusters.get(p) == null); }
	
	private boolean validPos (Position p)
	{
		return p.x >= -grid.size() && p.x <= grid.size() &&
			   p.y >= -grid.size() && p.y <= grid.size() &&
			   notChecked(p);
	}
	
	// toString
	public String toString ()
	{
		String s = "";
		Position p;
		
		for (int i = -grid.size(); i <= grid.size(); i++)
		{
			for (int j = -grid.size(); j <= grid.size(); j++)
			{
				p = new Position(i, j);
				if (clusters.get(p) == null)
					s += "0 ";
				else
					s += clusters.get(p) + " ";
			}
			
			s += "\n";
		}
		
		return s;	
	}
}
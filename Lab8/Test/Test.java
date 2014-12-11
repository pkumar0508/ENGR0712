import java.util.Random;
import java.util.Hashtable;
import java.awt.Color;
import java.io.FileOutputStream;

public class Test
{
	private static int SIZE = 100;
	
	public static void main (String[] args)
	{
		/*
		Grid g = new Grid(SIZE);
		g.populate(0.60);
		ClusterGrid clusters = new ClusterGrid(g);
		clusters.countClusters();
		//System.out.println(g);
		//System.out.println(clusters);
		System.out.println("done");
		System.out.println(clusters.count());
		System.out.println(clusters.hasPath());
		*/
		
		try
		{
			FileOutputStream fo = new FileOutputStream("test.txt");
			fo.write(128);
			fo.close();
		}
		catch (Exception e)
		{
			System.out.println("Oops.");
		}
	}
}
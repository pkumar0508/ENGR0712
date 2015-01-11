import java.awt.*;
import java.applet.*;
import java.awt.event.*;
import java.util.Random;

public class TestApplet extends Applet implements MouseListener
{
	private int SIZE;
		
	public void init ()
	{
		SIZE = (int) getSize().getHeight();
		addMouseListener(this);
	}
	
	public void paint (Graphics g)
	{
		double p = .6;
		final int N = 300;
		boolean primaryColors = true;
		
		final int R = 2;
		int count;
		Grid grid = new Grid(N);
		ClusterGrid c;
		Color[] list;
		Random r = new Random();
		boolean flag = true;
		
		grid.clear();
		grid.populate(p);
		c = new ClusterGrid(grid);
		
		c.countClusters();
		count = c.count();
		list = new Color[count + 1];
		
		list[0] = new Color(255, 255, 255);
		if (primaryColors)
		{
			Color[] cList = {Color.black, Color.blue, Color.cyan,
							 Color.darkGray, Color.gray, Color.green,
							 Color.lightGray, Color.magenta, Color.orange,
							 Color.pink, Color.red, Color.yellow};
			
			for (int q = 1; q <= count; q++)
				list[q] = cList[r.nextInt(cList.length)];
		}
		else
		{
			for (int q = 1; q <= count; q++)
				list[q] = new Color(r.nextInt(256),
									r.nextInt(256),
									r.nextInt(256));
		}
		
		for (int i = 0; i < N; i++)
			for (int j = 0; j < N; j++)
			{
				g.setColor(list[c.get(i, j)]);
				g.fillOval(i * 2, j * 2, R, R);
			}
	}
	
	public void mouseClicked (MouseEvent me) { repaint(); }
	public void mouseEntered (MouseEvent me) {}
	public void mousePressed (MouseEvent me) {}
	public void mouseReleased (MouseEvent me) {}
	public void mouseExited (MouseEvent me) {}
}
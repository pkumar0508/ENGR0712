
import java.awt.*;
import java.applet.*;
import java.awt.event.*;

public class TestApplet extends Applet implements MouseListener
{
	private Graphics[] NULL = {};
	private int SIZE;
	private Graphics bufferGraphics;
	private Image offscreen;
	private int flag;
	private Grid grid;
	
	private final double[] P = { 0.40, 0.40, 0.20 };
	private final Color[] COLORS = { Color.red, Color.blue, Color.green };
	private final int[] ENERGIES = { -2, 2, 0,
									 2, -2, 0,
									 0, 0, -1 };
	
	private int SCALE = 20;
	
	private int n = 3000;
	private int R = 15;
	
	public void init ()
	{
		SIZE = (int) getSize().getHeight();
		flag = 1;
		
		offscreen = createImage(getSize().width, getSize().height);
		bufferGraphics = offscreen.getGraphics();
		addMouseListener(this);
		
		grid = new Grid(SIZE, SCALE, COLORS, ENERGIES);
		grid.setR(R);
	}
	
	public void paint (Graphics g)
	{
		Graphics[] G1 = { g, bufferGraphics },
				   G2 = { bufferGraphics };
		
		if (flag == 1)		// Generate
		{
			grid.setG(G2);
			
			g.drawString("Generating ...", 20, 20);
			grid.populate(P);
			
			flag = 2;
		}
		
		if (flag == 3)		// metropolis
		{
			grid.setG(G1);
			
			grid.metropolis(n);
			
			flag = 4;
		}
		
		// buffer graphics
		g.clearRect(0, 0, SIZE, SIZE);
		g.drawImage(offscreen, 0, 0, this);
		grid.setG(NULL);
	}
	
	public void update (Graphics g)
	{ paint(g); }
	
	public void mouseClicked (MouseEvent me)
	{
		if (flag == 2)
			flag = 3;
		repaint();
	}
	
	public void mouseEntered  (MouseEvent me) {}
	public void mousePressed  (MouseEvent me) {}
	public void mouseReleased (MouseEvent me) {}	
	public void mouseExited   (MouseEvent me) {}
}


















/*



import java.awt.*;
import java.applet.*;
import java.awt.event.*;

public class TestApplet extends Applet implements MouseListener
{
	private int SIZE;
	private Graphics bufferGraphics;
	private Image offscreen;
	private int flag;
	private Gridq grid;
	
	private double P = 0.50;
	private int n = 1000;
	private int SCALE = 20;
	
	public void init ()
	{
		SIZE = (int) getSize().getHeight();
		flag = 1;
		offscreen = createImage(getSize().width, getSize().height);
		bufferGraphics = offscreen.getGraphics();
		grid = new Gridq(SIZE, SCALE);
		addMouseListener(this);
	}
	
	public void paint (Graphics g)
	{
		Graphics[] G1 = { g, bufferGraphics },
				   G2 = { bufferGraphics },
				   NULL = {};
		
		if (flag == 1)		// Generate
		{
			grid.setG(G2);
			
			g.drawString("Generating ...", 20, 20);
			grid.populate(P);
			grid.paint();
			
			flag = 2;
		}
		
		if (flag == 3)		// metropolis
		{
			grid.setG(G1);
			
			grid.metropolis(n);
			
			flag = 4;
		}
		
		// buffer graphics
		g.clearRect(0, 0, SIZE, SIZE);
		g.drawImage(offscreen, 0, 0, this);
		grid.setG(NULL);
	}
	
	public void update (Graphics g)
	{ paint(g); }
	
	public void mouseClicked (MouseEvent me)
	{
		if (flag == 2)
			flag = 3;
		repaint();
	}
	
	public void mouseEntered  (MouseEvent me) {}
	public void mousePressed  (MouseEvent me) {}
	public void mouseReleased (MouseEvent me) {}	
	public void mouseExited   (MouseEvent me) {}
}


*/
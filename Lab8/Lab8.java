import java.awt.*;
import java.applet.*;
import java.awt.event.*;
import java.util.ArrayList;

public class Lab8 extends Applet implements MouseListener
{
	private int SIZE;
	private Graphics bufferGraphics;
	private Image offscreen;
	private int flag;
	private Grid grid;

	private int SCALE = 10;
	private int R = 8;

	public void init ()
	{
		SIZE = (int) getSize().getHeight();
		flag = 1;

		offscreen = createImage(getSize().width, getSize().height);
		bufferGraphics = offscreen.getGraphics();
		addMouseListener(this);

		ArrayList<Position> l = new ArrayList<>();
		l.add(new Position(7, 10));
		l.add(new Position(8, 10));
		l.add(new Position(8, 11));
		l.add(new Position(8, 12));
		l.add(new Position(7, 13));
		l.add(new Position(6, 13));
		l.add(new Position(6, 12));
		grid = new Grid(l);
	}

	public void paint (Graphics g)
	{
		Graphics[] G1 = { g, bufferGraphics },
				   G2 = { bufferGraphics },
				   NULL = {};
		Graphics G = bufferGraphics;
		/*
		if (flag == 1)		// Generate
			flag = 2;

		if (flag == 3)		// metropolis
			grid.nextGeneration();
		*/

		for (int i = 0; i < 1e7; i++)
		{
			for (int j = 0; j < 1e7; j++);
			grid.paint(G, SIZE, SCALE, R);
			grid.nextGeneration();
			g.clearRect(0, 0, SIZE, SIZE);
			g.drawImage(offscreen, 0, 0, this);
		}

		// buffer graphics
		grid.paint(G, SIZE, SCALE, R);
		g.clearRect(0, 0, SIZE, SIZE);
		g.drawImage(offscreen, 0, 0, this);
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

package app.pkumar0508.engr0712.lab6;

import java.awt.*;
import java.applet.*;
import java.awt.event.*;

public class Lab6 extends Applet implements MouseListener
{
	private int SIZE;
	private Graphics bufferGraphics;
	private Image offscreen;
	private boolean done;
	private boolean goOn;
	private Grid grid;

	private double p = 0.50;
	private int n = 1000;
	private int SCALE = 20;

	public void init ()
	{
		SIZE = (int) getSize().getHeight();
		done = false;
		goOn = false;
		offscreen = createImage(getSize().width, getSize().height);
		bufferGraphics = offscreen.getGraphics();
		grid = new Grid(SIZE, SCALE);
		addMouseListener(this);
	}

	public void paint (Graphics g)
	{
		Graphics[] G = { g, bufferGraphics },
				   NULL = {};

		if (!done)
		{
			g.drawString("Generating ...", 20, 20);
			grid.populate(p);

			g.clearRect(0, 0, SIZE, SIZE);
			grid.paint(G);

			done = true;
		}

		if (goOn)
		{
			grid.metropolis(n, G);
			grid.paint(G);

			goOn = false;
		}

		if (done && !goOn)
			g.drawImage(offscreen, 0, 0, this);
	}

	public void update (Graphics g)
	{ paint(g); }

	public void mouseClicked (MouseEvent me)
	{
		goOn = true;
		repaint();
	}

	public void mouseEntered  (MouseEvent me) {}
	public void mousePressed  (MouseEvent me) {}
	public void mouseReleased (MouseEvent me) {}
	public void mouseExited   (MouseEvent me) {}
}

import java.awt.*;
import java.applet.*;

public class Lab4 extends Applet
{
	private final int N = 100000;
	private int SIZE;
	private boolean done;
	private Graphics bufferGraphics;
	private Image offscreen;

	public void init ()
	{
		SIZE = (int) getSize().getHeight() / 2;
		done = false;
		offscreen = createImage(getSize().width, getSize().height);
		bufferGraphics = offscreen.getGraphics();
	}

	public void paint (Graphics g)
	{
		if (!done)
		{
			Particle q;
			Graphics[] graphics = { g, bufferGraphics };


			Grid grid = new Grid(SIZE);
			q = new Particle(new Position(), grid.randomColor());
			grid.put(q, graphics);
			grid.setR(2);
			grid.aggregate(N, 1.0, graphics);


			/*
			Color[] colors = new Color[500];
			colors[0] = Color.green;
			for (int i = 1; i < colors.length; i++)
				colors[i] = Color.red;
			*/

			// Color[] colors = { Color.red, Color.green };
			/*
			ColoredGrid grid = new ColoredGrid(SIZE, 3);
			q = new Particle(new Position(0, 5), Color.red);
			grid.put(q, graphics);
			q = new Particle(new Position(0, -5), Color.green);
			grid.put(q, graphics);
			grid.setR(3);
			grid.aggregate(N, 1.0, colors, graphics);
			*/

			done = true;
		}
		else
			g.drawImage(offscreen, 0, 0, this);
	}
}

import java.awt.*;
import java.applet.*;
import java.awt.event.*;

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
		
		Position[] p = { new Position(-1, 1),
						 new Position(0, 1),
						 new Position(1, 1),
						 new Position(1, 2),
						 new Position(0, 2),
						 new Position(-1, 2),
						 new Position(-2, 2),
						 new Position(-3, 2),
						 new Position(-3, 1),
						 new Position(-3, 0),
						 new Position(-2, 0),
						 new Position(-1, 0),
						 new Position(0, 0),
						 new Position(1, 0),
						 new Position(1, -1),
						 new Position(1, -2),
						 new Position(0, -2),
						 new Position(-1, -2),
						 new Position(-2, -2),
						 new Position(-2, -1),
						 new Position(-1, -1) };
		
		Snake s = new Snake(SIZE, p, 50);
		g.setXORMode(Color.white);
		g.setColor(Color.blue);
		s.paint(g);
		
		for (int i = 0; i < 2000; i++)
		{
			s.paint(g);
			s.nextState();
			s.paint(g);
		}
	}
	
	public void mouseClicked  (MouseEvent me) { repaint(); }
	public void mouseEntered  (MouseEvent me) {}
	public void mousePressed  (MouseEvent me) {}
	public void mouseReleased (MouseEvent me) {}
	public void mouseExited   (MouseEvent me) {}
}
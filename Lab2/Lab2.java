import java.awt.*;
import java.applet.*;
import java.util.ArrayList;

public class Lab2 extends Applet
{
	private int SIZE;

	public void init ()
	{
		SIZE = (int) getSize().getHeight();
	}

	public void paint (Graphics g)
	{
		Position r;
		int n = 1000, step = 10, q;
		int[] x, y;
		int[] t, d;
		ArrayList<Integer> tx = new ArrayList<>(),
            dx = new ArrayList<>(),
            l = new ArrayList<>();
		NSARW w;

		w = new NSARW(n);
		w.generate();
		x = w.x();
		y = w.y();

		for (int i = 1; i < x.length; i++)
		{
			tx.add(new Integer(i));
			q = (int) Math.sqrt(x[i] * x[i] + y[i] * y[i]);
			dx.add(new Integer(q));
		}

		q = 0;
		for (int j = 0; j < tx.size(); j++)
		{
			if (q == 0)
				l.add(new Integer(j));
			q++;
			q %= step;
		}

		q = l.size();
		t = new int[q];
		d = new int[q];
		for (int j = 0; j < l.size(); j++)
		{
			q = ((Integer) l.get(j)).intValue();
			t[j] = ((Integer) tx.get(q)).intValue();
			d[j] = ((Integer) dx.get(q)).intValue();
		}

		double r2 = analyzeLogLog(t, d);
		if (r2 > .95)
		{
			r = new Position(SIZE / 2, SIZE / 2);
			drawPlot(g, x, y, r, 5, Color.blue);
			r = new Position();
			drawPlot(g, t, d, r, 1, Color.green);
		}
		else
			repaint();
	}

	public void drawPlot (Graphics g, int[] x, int[] y,
						  Position r, int scale, Color c)
	{
		Position temp, d;
		Color prev = g.getColor();
		int n = x.length;

		for (int i = 0; i < n; i++)
		{
			d = new Position(x[i] * scale, y[i] * scale);
			temp = vectorAdd(r, d);
			temp = map(temp);

			x[i] = temp.x();
			y[i] = temp.y();
		}

		g.setColor(c);
		drawAxis(g, r);
		g.drawPolyline(x, y, n);
		g.setColor(prev);
	}

	public double analyzeLogLog (int[] t, int[] d)
	{
		double[] dd = new double[d.length];
		double[] tt = new double[t.length];
		for (int i = 0; i < dd.length; i++)
		{
			tt[i] = Math.log(t[i]);
			dd[i] = Math.log(d[i]);
			System.out.println("\t" + tt[i] + " " + dd[i]);
		}
		return analysis(tt, dd);
	}

	public double analysis (double[] x, double[] y)
	{
		double Sxy, Sxx, m, b, sumX = 0,
			   sumY = 0, sumXY = 0, sumX2 = 0, R2, SSE, SST, sumY2 = 0;
		int n = x.length;
		System.out.println(n);
		for (int j = 0; j < n; j++)
		{
			sumX += x[j];
			sumY += y[j];
			sumXY += x[j] * y[j];
			sumX2 += x[j] * x[j];
			sumY2 += y[j] * y[j];
		}

		Sxx = sumX2 - sumX * sumX / (double) n;
		Sxy = sumXY - sumX * sumY / (double) n;

		m = Sxy / Sxx;
		b = (sumY - m * sumX) / n;
		SSE = sumY2 - b * sumY - m * sumXY;
		SST = sumY2 - sumY * sumY / (double) n;

		R2 = 1 - SSE / SST;
		System.out.println("m:  " + m);
		System.out.println("b:  " + b);
		System.out.println("r2: " + R2);
		return R2;
	}

	private void drawAxis (Graphics g, Position r)
	{
		Color prev = g.getColor();
		g.setColor(Color.black);
		g.drawLine(r.x(), r.y() - SIZE, r.x(), r.y() + SIZE);
		g.drawLine(r.x() - SIZE, r.y(), r.x() + SIZE, r.y());
		g.setColor(prev);
	}

	public Position map (Position p)
	{ return new Position(p.x(), SIZE - 1 - p.y()); }

	public Position vectorAdd (Position a, Position b)
	{ return new Position(a.x() + b.x(), a.y() + b.y()); }
}

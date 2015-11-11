package plot;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.geom.Line2D;
import java.util.ArrayList;
import java.util.Set;

import javax.swing.JComponent;

import voronoi.VPoint;

public class Points extends JComponent{
	private ArrayList<VPoint> p=null;
	public Points(ArrayList<VPoint> points){
		super();
		p=points;
	}
	public void paint(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		for(VPoint point:p){
			Shape k = new Line2D.Double(point.x,point.y, point.x,point.y);
			g2.draw(k);
		}
	}
}

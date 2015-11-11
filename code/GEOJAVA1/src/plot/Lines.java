package plot;

import java.awt.BasicStroke;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.geom.Line2D;
import java.util.ArrayList;
import java.util.Set;

import javax.swing.JComponent;

import voronoi.VPoint;

public class Lines extends JComponent{
	private Set<ArrayList<VPoint>> l=null;
	private ArrayList<VPoint> p=null;
	public Lines(Set<ArrayList<VPoint>> lines,ArrayList<VPoint> points){
		super();
		l=lines;
		p=points;
	}
	public void paint(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		for(ArrayList<VPoint> line:l){
			Shape k = new Line2D.Double(line.get(0).x,line.get(0).y, line.get(1).x,line.get(1).y);
			g2.draw(k);
		}
		g2.setStroke(new BasicStroke(3));
		for(VPoint point:p){
			Shape k = new Line2D.Double(point.x,point.y, point.x,point.y);
			g2.draw(k);
		}
	}
}

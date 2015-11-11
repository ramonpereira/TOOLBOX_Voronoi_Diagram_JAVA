package voronoi;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

import javax.swing.JFrame;

import plot.Lines;
import plot.Points;
import plot.Polygons;

public class VorTest {
	public static double w = 500;
	public static ArrayList<VPoint> ver;
	public static ArrayList<VPoint> dir;
	public static ArrayList<VEdge> edg; 

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Voronoi v= new Voronoi();
		ver=new ArrayList<VPoint>();
		dir=new ArrayList<VPoint>();
		Random r = new Random();
		for(int i=0; i<10; i++) 
		{
			ver.add(new VPoint( w*r.nextDouble(), w*r.nextDouble())); 
			dir.add(new VPoint( r.nextDouble()-0.5, r.nextDouble()-0.5)); 
		}
		edg=v.getEdges(ver, w, w);
		System.out.println("Voronois done!");
		for(VEdge es:edg){
			if(es.start==null)
			{
				System.out.println("There is edge start point missing");
				continue;
			}
			if(es.end==null)
			{
				System.out.println("There is edge end point missing");
				continue;
			}	
		}
		Set<ArrayList<VPoint>>lines=new HashSet<ArrayList<VPoint>>();
		for(VEdge es:edg){
			ArrayList<VPoint> vertices=new ArrayList<VPoint>();
			vertices.add(es.start);
			vertices.add(es.end);
			lines.add(vertices);
			}
		System.out.println(edg.size());
	    JFrame window = new JFrame();
	    window.setBounds(0, 0, 500, 500);
	    window.getContentPane().add(new Lines(lines,ver));
	    //window.getContentPane().add(new Points(ver));
	    window.setVisible(true);
	}
}

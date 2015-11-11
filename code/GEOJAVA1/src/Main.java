

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

import javax.swing.JFrame;

import plot.Lines;
import plot.Polygons;
import voronoi.VEdge;
import voronoi.VPoint;
import voronoi.Voronoi;

/**
 * @author Wuga
 * PhD student
 * Computer Science
 * Oregon State University
 * 
 * This code is an implementation of Fortune's algorithm to compute Voronoi Diagram.
 * 
 *  Features:
 *  ===
 *  Output edges of voronoi diagram
 *  Output ploygon for each input points with same order!
 *  Scalable! Tested for 1,000,000 points
 *  
 *  How this code implemented:
 *  ===
 *  see following blog, the author is Genius!
 *  http://blog.ivank.net/fortunes-algorithm-and-implementation.html
 *  
 *  
 *  
 */
public class Main {
	public static double w = 500;
	public static ArrayList<VPoint> ver;
	public static ArrayList<VPoint> dir;
	public static ArrayList<VEdge> edg; 

	public static Set<Double> findDuplicates(ArrayList<VPoint> ver2)
	{ 
	  final Set<Double> setToReturn = new HashSet(); 
	  final Set<Double> set1 = new HashSet();

	  for (VPoint yourInt : ver2)
	  {
	   if (!set1.add(yourInt.y))
	   {
	    setToReturn.add(yourInt.y);
	   }
	  }
	  return setToReturn;
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Voronoi v= new Voronoi();
		ver=new ArrayList<VPoint>();
		dir=new ArrayList<VPoint>();
		Random r = new Random();
		for(int i=0; i<1000; i++) 
		{
			ver.add(new VPoint( w*r.nextDouble(), w*r.nextDouble())); 
			//dir.add(new VPoint( r.nextDouble()-0.5, r.nextDouble()-0.5)); 
		}
		Collections.sort(ver);
		for(int i=1;i<ver.size();i++){
			if(ver.get(i).y==ver.get(i-1).y){
				ver.set(i, new VPoint(ver.get(i).x,ver.get(i).y+0.01));
			}
		}
		ver.add(new VPoint(0,0));
//		ver.add(new VPoint(20,20));
//		ver.add(new VPoint(20,20.0000006));
//		ver.add(new VPoint(40,20));
//		ver.add(new VPoint(60,40));
//		Collections.sort(ver);
//		for(int i=1;i<ver.size();i++){
//		if(ver.get(i).y==ver.get(i-1).y){
//			ver.set(i, new VPoint(ver.get(i).x,ver.get(i).y+0.00001));
//		}
//	}
		//ver.add(new VPoint(40,20));
		//ver.add(new VPoint(60,40));
		//ver.add(new VPoint(60,20))
		Set<Double> duplicate=findDuplicates(ver);
		System.out.println(duplicate);
//		ver.add(new VPoint(20,21));
//		ver.add(new VPoint(20,40));
//		ver.add(new VPoint(20,40.00001));
//		//ver.add(new VPoint(40,20));
//		//ver.add(new VPoint(60,40));
//		//ver.add(new VPoint(60,20));
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
		//System.out.println(edg.get(0).end+","+edg.get(1).end+","+edg.get(2).end);
		//System.out.println(v.getPolygons());
	    JFrame window = new JFrame();
	    window.setBounds(0, 0, 510, 525);
	    window.getContentPane().add(new Lines(lines,ver));
	    //window.getContentPane().add(new Polygons(v.getPolygons()));
	    window.setVisible(true);
	}
}

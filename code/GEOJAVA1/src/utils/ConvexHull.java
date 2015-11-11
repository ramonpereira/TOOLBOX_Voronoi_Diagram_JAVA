package utils;

import java.util.ArrayList;
import java.util.HashSet;

import voronoi.VPoint;

public class ConvexHull {
	private static double MINX=1e10;
	private static double MAXCOS=-1e10;
	
	public static ArrayList<VPoint> getPolygon(HashSet<VPoint> vertices){
		MINX=1e10;
		ArrayList<VPoint> poly=new ArrayList<VPoint>();
		VPoint start=null;
		
		for(VPoint p:vertices){
			if(p.x<MINX){
				start=p;
				MINX=p.x;
			}
		}
		poly.add(start);
		vertices.remove(start);
		VPoint curr=start;
		VPoint vec1=new VPoint(0,1);
		while(!vertices.isEmpty()){
			MAXCOS=-1e10;
			VPoint candidate=null;
			VPoint temp_vec=null;
			for(VPoint ver:vertices){
				VPoint vec2=new VPoint(ver.x-curr.x,ver.y-curr.y);
				double sim=Similarity.cosinSimiality(vec1, vec2);
				if(sim>MAXCOS){
					candidate=ver;
					temp_vec=vec2;
					MAXCOS=sim;
				}
			}
			poly.add(candidate);
			curr=candidate;
			vec1=temp_vec;
			vertices.remove(candidate);
		}
		return poly;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		HashSet<VPoint> vertices=new HashSet<VPoint>();
		vertices.add(new VPoint(15,7));
		vertices.add(new VPoint(5,5));
		vertices.add(new VPoint(5,10));
		vertices.add(new VPoint(10,5));
		vertices.add(new VPoint(10,10));
		ArrayList<VPoint> polygon=null;
		polygon=getPolygon(vertices);
		System.out.println(polygon);
	}

}

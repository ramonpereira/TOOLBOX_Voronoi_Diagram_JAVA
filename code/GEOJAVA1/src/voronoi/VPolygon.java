package voronoi;

import java.util.ArrayList;
import java.util.HashSet;

import utils.ConvexHull;

public class VPolygon {

	public ArrayList<VPoint> polygon;
	public VPolygon(ArrayList<VPoint> pg){
		polygon=pg;
	}
	public VPolygon(HashSet<VPoint> set){
		polygon=ConvexHull.getPolygon(set);
	}
	public String toString(){
		return polygon.toString();
	}
}

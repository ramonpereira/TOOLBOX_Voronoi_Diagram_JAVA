package utils;

import voronoi.VPoint;

public class Similarity {

	public static double cosinSimiality(VPoint a, VPoint b){
		double num=a.x*b.x+a.y*b.y;
		double den=Math.sqrt(a.x*a.x+a.y*a.y)*Math.sqrt(b.x*b.x+b.y*b.y);
		return num/den;
	}
}

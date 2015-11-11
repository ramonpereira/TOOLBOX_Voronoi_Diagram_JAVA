package voronoi;

public class VPoint implements Comparable<VPoint> {

	public double x,y;
	public VPoint(double x_axis, double y_axis){
		x=x_axis;
		y=y_axis;
	}
	public String toString(){
		return "("+x+","+y+") ";
	}
	
	@Override
	public int compareTo(VPoint arg0) {
		int compare = this.y > arg0.y ? -1 : this.y < arg0.y ? +1 : 0;
		return compare;
	}
}

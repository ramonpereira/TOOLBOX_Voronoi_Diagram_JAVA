package voronoi;

public class VEdge {

	public VPoint start;
	public VPoint end;
	public VPoint left;
	public VPoint right;
	public VPoint direction;
	public VEdge neighbour;
	
	double f;
	double g;
	
	public VEdge(VPoint s, VPoint a, VPoint b){
		start = s;
		left = a;
		right = b;
		neighbour = null;
		end =null;

		f=(b.x-a.x)/(a.y-b.y);
		g=s.y-f*s.x;
		direction=new VPoint(b.y-a.y,-(b.x-a.x));
	}
}

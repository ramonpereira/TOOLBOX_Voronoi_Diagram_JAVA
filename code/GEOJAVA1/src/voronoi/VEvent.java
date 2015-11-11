package voronoi;

public class VEvent {

	public VPoint point;
	boolean pe;
	double y;
	VParabola arch;
	
	public VEvent(VPoint p, boolean pev){
		point = p;
		pe = pev;
		y = p.y;
		arch = null;
	}	
}

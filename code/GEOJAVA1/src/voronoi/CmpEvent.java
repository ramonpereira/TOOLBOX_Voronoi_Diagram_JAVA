package voronoi;

import java.util.Comparator;

public class CmpEvent implements Comparator<VEvent> {

	public int compare(VEvent a,VEvent b){
		//int compare = b.y > a.y ? 1 : b.y < a.y ? -1:0;
		int compare = b.y > a.y ? 1 :b.y<a.y?-1:0;
		if (compare==0) {
			a.y=a.y-0.1;
		}
		return compare;
	}
}

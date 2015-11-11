package voronoi;

public class VParabola {

	public boolean isLeaf;
	public VPoint site;
	public VEdge edge;
	public VEvent cEvent;
	public VParabola parent;
	private VParabola _left;
	private VParabola _right;
	
	/*
	Constructors of the class (empty for edge, with focus parameter for an arch).
	 */
	public VParabola(){
		site = null;
		isLeaf = false;
		cEvent = null;
		edge = null;
		parent =null;
	}
	
	public VParabola(VPoint s){
		site = s;
		isLeaf = true;
		cEvent = null;
		edge= null;
		parent = null;
	}
	
	public void setLeft(VParabola p){
		_left=p;
		p.parent=this;
	}
	
	public void setRight(VParabola p){
		_right=p;
		p.parent=this;
	}
	
	public VParabola left(){
		return _left;
	}
	
	public VParabola right(){
		return _right;
	}
	
	public static VParabola getLeft(VParabola p){
		return getLeftChild(getLeftParent(p));
	}
	public static VParabola getRight(VParabola p){
		return getRightChild(getRightParent(p));
	}
	public static VParabola getLeftParent(VParabola p){
		VParabola par = p.parent;
		VParabola pLast = p;
		while(par.left()==pLast){
			if(par.parent==null) return null;
			pLast = par;
			par=par.parent;
		}
		return par;
	}
	public static VParabola getRightParent(VParabola p){
		VParabola par = p.parent;
		VParabola pLast = p;
		while(par.right()==pLast){
			if(par.parent==null) return null;
			pLast = par;
			par=par.parent;
		}
		return par;
	}
	public static VParabola getLeftChild(VParabola p){
		if(p==null) return null;
		VParabola par = p.left();
		while(par.isLeaf==false) par = par.right();
		return par;
	}
	public static VParabola getRightChild(VParabola p){
		if(p==null) return null;
		VParabola par = p.right();
		while(par.isLeaf==false) par = par.left();
		return par;
	}
}

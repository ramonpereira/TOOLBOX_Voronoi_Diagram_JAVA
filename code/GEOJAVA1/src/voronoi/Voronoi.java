package voronoi;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Set;

/**
 * @author Wuga
 * PhD student at Oregon State University
 * 
 */
public class Voronoi {
	private ArrayList<VPoint> places;
	private ArrayList<VEdge> edges;
	private Set<VEvent> deleted;	
	private ArrayList<VPoint> points;
	private PriorityQueue<VEvent> queue;
	private double width,height;
	private VParabola root;
	private double ly;
	
	
	private void insertParabola(VPoint p){
		if(root==null){
			root=new VParabola(p);
			return;
		}
		if(root.isLeaf&&root.site.y-p.y<0.01){
			VPoint fp=root.site;
			root.isLeaf=false;
			root.setLeft(new VParabola(fp));
			root.setRight(new VParabola(p));
			VPoint s=new VPoint((p.x+fp.x)/2,height);
			points.add(s);
			if(p.x>fp.x) root.edge=new VEdge(s,fp,p);
			else root.edge=new VEdge(s,p,fp);
			edges.add(root.edge);
			return;
		}
		VParabola par=getParabolaByX(p.x);
		if(par.cEvent!=null){
			deleted.add(par.cEvent);
			par.cEvent=null;
		}
		
		VPoint start=new VPoint(p.x,getY(par.site,p.x));
		//System.out.println(start);
		points.add(start);
		//System.out.println(start);
		VEdge el=new VEdge(start,par.site,p);
		VEdge er=new VEdge(start,p,par.site);
		el.neighbour=er;
		edges.add(el);
		par.edge=er;
		par.isLeaf=false;
		
		VParabola p0=new VParabola(par.site);
		VParabola p1=new VParabola(p);
		VParabola p2=new VParabola(par.site);
		
		par.setRight(p2);
		par.setLeft(new VParabola());
		par.left().edge=el;
		par.left().setLeft(p0);
		par.left().setRight(p1);
		
		checkCircle(p0);
		checkCircle(p2);
	}
	
	private void removeParabola(VEvent e){
		VParabola p1=e.arch;
		VParabola xl=VParabola.getLeftParent(p1);
		VParabola xr=VParabola.getRightParent(p1);
		VParabola p0=VParabola.getLeftChild(xl);
		VParabola p2=VParabola.getRightChild(xr);
		
		if(p0==p2){
			System.out.println("Something wrong!");
		}
		if(p0.cEvent!=null){deleted.add(p0.cEvent);p0.cEvent=null;}
		if(p2.cEvent!=null){deleted.add(p2.cEvent);p2.cEvent=null;}
		VPoint p=new VPoint(e.point.x,getY(p1.site,e.point.x));
		points.add(p);
		xl.edge.end=p;
		xr.edge.end=p;
		VParabola higher = null;
		VParabola par=p1;
		while(par!=root){
			par=par.parent;
			if(par==xl) {higher=xl;}
			if(par==xr) {higher=xr;}
		}
		higher.edge=new VEdge(p,p0.site,p2.site);
		edges.add(higher.edge);
		VParabola gparent=p1.parent.parent;
		if(p1.parent.left()==p1){
			if(gparent.left()==p1.parent) gparent.setLeft(p1.parent.right());
			if(gparent.right()==p1.parent) gparent.setRight(p1.parent.right());
		}
		else{
			if(gparent.left()==p1.parent) gparent.setLeft(p1.parent.left());
			if(gparent.right()==p1.parent) gparent.setRight(p1.parent.left());
		}
		p1.parent=null;
		p1=null;
		checkCircle(p0);
		checkCircle(p2);
	}
	
	private void finishEdge(VParabola n){
		if(n.isLeaf){
			n=null;
			return;
		}
		if(!Double.isInfinite(n.edge.f)){
			double mx;
			if(n.edge.direction.x>0){
				mx=Math.max(width, n.edge.start.x+10);
			}else{
				mx=Math.min(0, n.edge.start.x-10);
			}
			VPoint end=new VPoint(mx,mx*n.edge.f+n.edge.g);
			n.edge.end=end;
			points.add(end);
		}else{
			VPoint end=new VPoint(n.edge.start.x,0);
			n.edge.end=end;
			points.add(end);
		}
		finishEdge(n.left());
		finishEdge(n.right());
		n=null;
	}
	
	private double getXOfEdge(VParabola par, double y){
		VParabola left=VParabola.getLeftChild(par);
		VParabola right=VParabola.getRightChild(par);
		VPoint p=left.site;
		VPoint r=right.site;
		double dp=2.0*(p.y-y);
		double a1=1.0/dp;
		double b1=-2.0*p.x/dp;
		double c1=y+dp/4+p.x*p.x/dp;
		
		dp=2.0*(r.y-y);
		double a2=1.0/dp;
		double b2=-2.0*r.x/dp;
		double c2=y+dp/4+r.x*r.x/dp;
		
		double a=a1-a2;
		double b=b1-b2;
		double c=c1-c2;
		
		double disc=b*b-4*a*c;
		double x1=(-b+Math.sqrt(disc))/(2*a);
		double x2=(-b-Math.sqrt(disc))/(2*a);
		
		double ry;
		if(p.y<r.y) ry=Math.max(x1, x2);
		else ry=Math.min(x1, x2);
		
		return ry;
	}
	
	private VParabola getParabolaByX(double xx){
		VParabola par=root;
		double x = 0;
		while(!par.isLeaf){
			x=getXOfEdge(par,ly);
			if(x>xx) par=par.left();
			else par =par.right();
		}
		return par;
	}
	
	private double getY(VPoint p, double x){
		double dp= 2*(p.y-ly);
		double a1= 1/dp;
		double b1= -2*p.x/dp;
		double c1=ly+dp/4+p.x*p.x/dp;
		return (a1*x*x+b1*x+c1);
	}
	
	private void checkCircle(VParabola b){
		VParabola lp=VParabola.getLeftParent(b);
		VParabola rp=VParabola.getRightParent(b);
		VParabola a=VParabola.getLeftChild(lp);
		VParabola c=VParabola.getRightChild(rp);
		if(a==null||c==null) return;
		if(a.site==c.site) return;
		VPoint s=null;
		s=getEdgeIntersection(lp.edge,rp.edge);
		if(s==null) return;
		double dx=a.site.x-s.x;
		double dy=a.site.y-s.y;
		double d=Math.sqrt((dx*dx)+(dy*dy));
		if(s.y-d>=ly) return;
		VEvent e=new VEvent(new VPoint(s.x,s.y-d),false);
		points.add(e.point);
		b.cEvent=e;
		e.arch=b;
		queue.offer(e);
		
	}
	
	private VPoint getEdgeIntersection(VEdge a, VEdge b){
		double x= (b.g-a.g)/(a.f-b.f);
		double y= a.f*x+a.g;
		if(Double.isInfinite(a.f)){
			x=a.start.x;
			y=b.f*x+b.g;
		}else if(Double.isInfinite(b.f)){
			x=b.start.x;
			y=a.f*x+a.g;
			
		}
		if((x-a.start.x)/a.direction.x<0) return null;
		if((y-a.start.y)/a.direction.y<0) return null;
		
		if((x-b.start.x)/b.direction.x<0) return null;
		if((y-b.start.y)/b.direction.y<0) return null;
		//System.out.println(x+","+y);
		VPoint p=new VPoint(x, y);
		points.add(p);
		return p;		
	}
	
	public Voronoi(){
		edges=null;
		CmpEvent ce=new CmpEvent();
		queue=new PriorityQueue<VEvent>(10, ce);
		deleted=new HashSet<VEvent>();
		points=new ArrayList<VPoint>();
	}
	
	public ArrayList<VEdge> getEdges(ArrayList<VPoint> v, double w, double h){
		places = v;
		width = w;
		height = h;
		root = null;
		if(edges==null) {edges=new ArrayList<VEdge>();}
		else{
			points.clear();
			edges.clear();
		}
		for(VPoint p:places){
			queue.offer(new VEvent(p,true));
		}
		VEvent e;
		while(!queue.isEmpty()){
			e=queue.poll();
			ly=e.point.y;
			if(deleted.contains(e)){
				e=null; 
				deleted.remove(e);
				continue;
			}
			if(e.pe) insertParabola(e.point);
			else removeParabola(e);
			e=null;
		}
		finishEdge(root);
		
		for(VEdge edge:edges){
			//System.out.println(edge.start+","+edge.end);
			if(edge.neighbour!=null){
				edge.start=edge.neighbour.end;
				edge.neighbour=null;
			}
		}
		return edges;
	}
	
	public ArrayList<VPolygon> getPolygons(){
		ArrayList<VPolygon> polys=new ArrayList<VPolygon>();
		for(VPoint place:places){
			HashSet<VPoint> vertices=new HashSet<VPoint>();
			for(VEdge edge:edges){
				if(edge.left==place||edge.right==place){
					if(!vertices.contains(edge.start)){
						vertices.add(edge.start);
					}
					if(!vertices.contains(edge.end)){
						vertices.add(edge.end);
					}
				}
			}
			VPolygon pg=new VPolygon(vertices);
			polys.add(pg);
		}
		return polys;
	}
}

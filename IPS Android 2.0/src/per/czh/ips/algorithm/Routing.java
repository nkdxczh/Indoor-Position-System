package per.czh.ips.algorithm;

import java.util.ArrayList;
import java.util.List;

import per.czh.ips.po.Node;
import per.czh.ips.po.Path;

public class Routing {
	public ArrayList<Path> getRoute(Node start,Node end,List<Node> nodes,List<Path> paths){
		ArrayList<Path> route=new ArrayList<Path>();
		Node s=start;
		Node e=end;
		
		double minsd=Double.MAX_VALUE;
		double mined=Double.MAX_VALUE;
		Node begin = new Node(),dest = new Node();
		
		System.out.println("getse");
		for(Node n:nodes){
			double sd=calPPD(s,n);
			if(sd<minsd){
				minsd=sd;
				begin.setId(n.getId());
				begin.setX(n.getX());
				begin.setY(n.getY());
			}
			double ed=calPPD(e,n);
			if(ed<mined){
				mined=ed;
				dest.setId(n.getId());
				dest.setX(n.getX());
				dest.setY(n.getY());
			}
		}
		
		if(begin==null||dest==null)return null;
		System.out.println("start:"+start.getId()+","+start.getX()+","+start.getY());
		System.out.println("begin:"+begin.getId()+","+begin.getX()+","+begin.getY());
		System.out.println("end:"+end.getId()+","+end.getX()+","+end.getY());
		System.out.println("dest:"+dest.getId()+","+dest.getX()+","+dest.getY());
		
		
	//	route.add(new Line(begin,new Node(start.getX(),start.getY())));
		
		ArrayList<Node> S=new ArrayList<Node>();
		ArrayList<Path> teml=new ArrayList<Path>();
		
		S.add(begin);
		
		while(true){
			System.out.println("gggggg");
			double mind=Double.MAX_VALUE;
			Node addNode = null;
			Path addPath=null;
			
			for(Node present:S){
				for(Path path:paths){
					if(path.getStartP()!=present.getId())continue;
					boolean con=false;
					for(Node n:S){
						if(path.getEndP()==n.getId()){
							con=true;
							break;
						}
					}
					if(con)continue;
					System.out.println(path.getStartP()+","+path.getEndP());
					
					
					for(Node n:nodes){
						if(n.getId()!=path.getEndP())continue;
						double temd=calPPD(n,present);
						if(temd<mind){
							mind=temd;
							addNode=n;
							addPath=path;
						}
					}
				}
				
				
			}
			if(addNode==null)break;
			System.out.println("add:"+addNode.getId()+";"+addPath.getStartP()+","+addPath.getEndP());
			S.add(addNode);
			teml.add(addPath);
			if(addNode.getId()==dest.getId())break;
		}
		
		for(int n=dest.getId();n!=begin.getId();){
			for(Path l:teml){
				if(l.getEndP()==n){
					route.add(l);
					n=l.getStartP();
					break;
				}
			}
		}
		
	//	route.add(new Line(dest,new Node(end.getX(),end.getY())));
		System.out.println("end");
		
		return route;
	}
	
	private double calPPD(Node n1,Node n2){
		return Math.sqrt(Math.pow(n1.getX()-n2.getX(),2)+Math.pow(n1.getY()-n2.getY(),2));
	}
	
	/*private double calPLD(Node p,Line l){
		double distance=0;
		if(l.getN1().getX()==l.getN2().getX())distance=Math.abs(p.getX()-l.getN1().getX());
		else if(l.getN1().getY()==l.getN2().getY())distance=Math.abs(p.getY()-l.getN1().getY());
		else{
			double A=(l.getN1().getX()-l.getN2().getX())/(l.getN1().getY()-l.getN2().getY());
			double B=-1;
			double C=l.getN2().getY()+l.getN2().getX()*A;
			distance=Math.abs(A*p.getX()+B*p.getY()+C)/Math.sqrt(A*A+B*B);
		}
		return distance;
	}*/
}

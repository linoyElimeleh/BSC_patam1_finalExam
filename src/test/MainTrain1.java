package test;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class MainTrain1 {
	
	public interface PossibleRun{
		void run() throws Exception;
	}
	
	public static void test(PossibleRun r,int points,String sec) {
		try {
			r.run();
		}catch(Exception e) {
			System.out.println("your code throws an exception in "+sec+" (-"+points+")");
		}
	}

	public static void main(String[] args) {	
		// ------- test list creation ----------
		ArrayList<Point> ps=new ArrayList<>();
		Point p1=Point.random();
		Point p2=null;
		for(int i=0;i<100;i++) {
			p2=Point.random();
			while(p1.equals(p2))
				p2=Point.random();				
			ps.add(p2);
		}
		
		Point pMaxFreq=p2;
		
		Random r=new Random();
		for(int i=0;i<50;i++)
			ps.add(r.nextInt(100),pMaxFreq);
		
		Point notInList=p1;
		
		//------------------------------------
		

		// test 1.a
		test(()->{
			if(!PointManager.anyMatch(ps, pMaxFreq))
				System.out.println("problem with anyMatch (-3)");
			if(PointManager.anyMatch(ps, notInList))
				System.out.println("problem with anyMatch (-2)");
		},5,"1.a");
		
		
		// test 1.b
		test(()->{
			List<Point> ps2=PointManager.orderByX(ps);
			if(ps2!=null && ps2.size()==ps.size()) {
				boolean ordered=true;
				Point pi=ps2.get(0);
				for(Point pj : ps2) {
					if(pj.x<pi.x) {
						ordered=false;
						break;
					}
					pi=pj;
				}
				if(!ordered)
					System.out.println("orderByX returned an unsorted list (-5)");
			}else {
				System.out.println("orderByX retuyrn an empty or wrong sized list (-5)");
			}
		},5,"1.b");
		
		// test 1.c
		test(()->{
			Map<Integer,List<Point>> map=PointManager.groupingByX(ps);
			if(map!=null) {
				boolean ok=true;
				for(Point pi : ps) {
					if(!map.containsKey(pi.x) || !map.get(pi.x).contains(pi)) {
						ok=false;
						break;
					}
				}
				if(!ok)
					System.out.println("problem with groupingByX (-10)");
			}else {
				System.out.println("groupingByX returned null (-10)");
			}
		},10,"1.c");
		
		// test 1.d
		test(()->{
			Point p=PointManager.mostFrequent(ps);
			if(p==null || !p.equals(pMaxFreq))
				System.out.println("problem with mostFrequent result (-10)");
		},10,"1.d");
		
		// test 1.e
		test(()->{
			PointManager.saveToFile("points.bin", ps);
			File f=new File("points.bin");
			if(!f.exists()) {
				System.out.println("you did not create a file (-10)");
			}else {				
				List<Point> ps2=PointManager.loadFile("points.bin");				
				if(ps2==null || ps2.size()!=ps.size())
					System.out.println("wrong size of list loaded from file (-10)");
				else {
					boolean ok=true;
					for(int i=0;i<ps.size() && ok;i++) {
						if(!ps.get(i).equals(ps2.get(i)))
							ok=false;
					}
					if(!ok) 
						System.out.println("problem with the loaded list (-10)");					
				}
			}			
		},10,"1.e");
		
		System.out.println("done");
	}
}

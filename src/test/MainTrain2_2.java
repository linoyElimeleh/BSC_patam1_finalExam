package test;

import java.util.Random;
import java.util.function.Function;

public class MainTrain2_2 {

	public static void main(String[] args) {
		Random r=new Random();
		int a=r.nextInt(100);
		int b=r.nextInt(10);
		Function<Integer,Integer> h=Q2b.multiCompose(x->x*a, 3);
		
		if(h.apply(b)!=b*a*a*a)
			System.out.println("wrong result for function h (-5)");
		
		Function<Double,Double> h1=Q2b.multiCompose(x->x*a, b);
		
		if(h1.apply(10.0)!=10.0*Math.pow(a, b))
			System.out.println("wrong result for function h1 (-5)");
		
		Function<Double,Double> h2=Q2b.multiCompose(x->x-a, b);
		
		if(h2.apply(1000.0)!=1000.0-a*b)
			System.out.println("wrong result for function h2 (-5)");
		
		System.out.println("done");
	}

}

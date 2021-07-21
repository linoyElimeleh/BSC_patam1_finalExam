package test;

import java.util.Random;
import java.util.function.Function;

public class MainTrain2_1 {

	public static void main(String[] args) {
		Random r=new Random();
		
		int a=r.nextInt(10);
		int b=r.nextInt(10);
		
		Function<Integer,Integer> h=Q2a.compose(x->x*a, x->x+b); // h(x) = g(f(x)) = g(x*a) = x*a + b
		
		for(int i=0;i<5;i++) {
			int x=r.nextInt(100);
			if(h.apply(x)!=x*a+b)
				System.out.println("wrong result for function h (-1)");
		}
		
		Function<String,Integer> sumASCII = Q2a.compose((String s)->s.toCharArray(), chars->{
			int sum=0;
			for(char c : chars)
				sum+=c;
			return sum;
		});
		if(sumASCII.apply("hello world!")!=1149)
			System.out.println("wrong result for function sumASCII (-1)");
		if(sumASCII.apply("from PTM1 to PTM2")!=1340)
			System.out.println("wrong result for function sumASCII (-1)");
		if(sumASCII.apply("Functional Programming")!=2230)
			System.out.println("wrong result for function sumASCII (-1)");
		if(sumASCII.apply("OOP")!=238)
			System.out.println("wrong result for function sumASCII (-1)");
		if(sumASCII.apply("don't copy from others!")!=2145)
			System.out.println("wrong result for function sumASCII (-1)");

		
		Function<String,Integer> all=Q2a.compose(sumASCII, h);
		if(all.apply("hello world!")!=1149*a+b)
			System.out.println("wrong result for function all (-1)");
		if(all.apply("from PTM1 to PTM2")!=1340*a+b)
			System.out.println("wrong result for function all (-1)");
		if(all.apply("Functional Programming")!=2230*a+b)
			System.out.println("wrong result for function all (-1)");
		if(all.apply("OOP")!=238*a+b)
			System.out.println("wrong result for function all (-1)");
		if(all.apply("don't copy from others!")!=2145*a+b)
			System.out.println("wrong result for function all (-1)");
		
		System.out.println("done");
	}

}

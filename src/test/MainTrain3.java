package test;

import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Random;

public class MainTrain3 {
	
	public static void runServer(int port,Point p) {
		try {
			ServerSocket server=new ServerSocket(port);
			server.setSoTimeout(1000);
			Socket client = server.accept();
			PrintWriter out=new PrintWriter(client.getOutputStream());
			out.println(p.x+","+p.y);
			out.flush();
			out.close();
			client.close();
			server.close();			
		}catch(Exception e) {}		
	}
	
	public static void testClient(int port,Point p) {
		boolean[] done= {false};
		Thread t=new Thread(()->{
			try {
				MyClient c=new MyClient();
				Point r = c.getPointFromServer("localhost", port);
				if(!p.equals(r))
					System.out.println("you didn't return the correct Point (-10)");
				done[0]=true;
			}catch(Exception e) {
				System.out.println("your code throws an exception (-10)");
			}
		});
		t.start();
		try {Thread.sleep(100);} catch (InterruptedException e) {}
		if(!done[0])
			try {Thread.sleep(1000);} catch (InterruptedException e) {}
		t.interrupt();
		try {
			t.join();
		} catch (InterruptedException e) {}
	}

	public static void main(String[] args) {
		
		Point p1=Point.random();
		Point p2=Point.random();
		Point p3=Point.random();
		
		Random r=new Random();
		int port=6000+r.nextInt(1000);		
		new Thread(()->runServer(port, p1)).start();
		testClient(port, p1);
		
		int port2=6000+r.nextInt(1000);		
		new Thread(()->runServer(port2, p2)).start();
		testClient(port2, p2);		
		
		int port3=6000+r.nextInt(1000);		
		new Thread(()->runServer(port3, p3)).start();
		testClient(port3, p3);
		
		System.out.println("done");
	}

}

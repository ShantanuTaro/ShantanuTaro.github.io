import java.lang.System;
import java.net.*; 
import java.io.*;
import java.text.*;
import java.util.*;

public class cli {
	static Socket connection;

	public static void main(String a[]) throws SocketException {
		try {
			int v[] = new int[10]; 
			int n = 0;
			int j = 0;
			Random rands = new Random();
			int rand = 0; 
 			 
			InetAddress addr = InetAddress.getByName("Localhost");
			System.out.println(addr);
			connection = new Socket(addr, 8011);
			DataOutputStream dout = new DataOutputStream(
					connection.getOutputStream());
			DataInputStream din = new DataInputStream(
					connection.getInputStream());
			int p = din.read();
			System.out.println("No of frame is:" + p);

			for (int i = 0; i < p; i++) {
				v[i] = din.read();
				System.out.println(v[i]);
				//g[i] = v[i];
			}
			rand = rands.nextInt(p);//FRAME NO. IS RANDOMLY GENERATED			
			v[rand] = -1;
			for (int i = 0; i < p; i++)
			 {
					System.out.println("Received frame is: " + v[i]);

				}
			for (int i = 0; i < p; i++)
				if (v[i] == -1) {
					System.out.println("Request to retransmit from packet no "
							+ (i+1) + " again!!");
					n = i;
					j=i;
					dout.write(n);
					dout.flush();
			}
			dout.out(j);
			dout.flush();
			System.out.println();
			
			for(j=n;j!=p;j++)
			{
				v[j] = din.read();
				System.out.println("Received frame is: " + v[j]);
			}	
			
				
			

			System.out.println("quiting");
		} catch (Exception e) {
			System.out.println(e);
		}

	}
}


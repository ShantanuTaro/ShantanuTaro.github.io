//.....................SERVER SIDE (SELECTIVE REPEAT)..........//

import java.io.*;
import java.net.*;

public class ser {
	static ServerSocket Serversocket;
	static DataInputStream din;
	static DataOutputStream dout;

	public static void main(String[] args) throws SocketException {

		try {
			int a[] = { 30, 40, 50, 60, 70, 80, 90, 100 };
			Serversocket = new ServerSocket(8011);
			System.out.println("waiting for connection");
			Socket client = Serversocket.accept();
			din = new DataInputStream(client.getInputStream());
			dout = new DataOutputStream(client.getOutputStream());
			System.out.println("The number of packets sent is:" + a.length);
			int y = a.length;
			dout.write(y);
			dout.flush();

			for (int i = 0; i < a.length; i++) {
				dout.write(a[i]);
				dout.flush();
			}

			int k = din.read();

			dout.write(a[k]);
			dout.flush();

		} catch (IOException e) {
			System.out.println(e);
		} finally {
			try {
				din.close();
				dout.close();
			} catch (IOException e) {
				e.printStackTrace();
			}

		}
	}
}

/*			 OUTPUT

	[sinhgad@localhost ~]$ su
Password: 
[root@localhost sinhgad]# javac ser.java
[root@localhost sinhgad]# java ser
waiting for connection
The number of packets sent is:8
[root@localhost sinhgad]# 

*/

import java.io.*;
import java.net.*;

public class ser {
	static ServerSocket Serversocket;
	static DataInputStream din;
	static DataOutputStream dout;

	public static void main(String[] args) throws SocketException {

		try {
			int a[] = { 30, 40, 50, 60, 70, 80, 90, 100 };
			int b[] = new int[10];
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
			int l = din.read();
			for(int x=0;x<y-k;x++)
			{
				 b[x]=a[x+k];
				dout.write(b[x]);
				dout.flush();
			}
			

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


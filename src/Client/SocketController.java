package Client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class SocketController {
	
	String msgIn;
	PrintWriter out;
	BufferedReader in;
	Socket conn;
	
	public SocketController(String ip, int porta) throws IOException {
		try {
			conn = new Socket (ip, porta);
			System.out.println("Conectando");
			out = new PrintWriter(conn.getOutputStream(), true);
	        in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
	        
		} catch (IOException e) {
			System.out.println("Deu exception");
            e.printStackTrace();
		}
	}
	
	public void msgOut(String msg) throws IOException {
		out.println(msg);
		msgIn = in.readLine();
		conn.close();
	}

	public String msgIn() {
		return msgIn;
	}
	
}

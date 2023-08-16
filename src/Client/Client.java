package Client;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

public class Client {
	
	public static void main (String[] args) throws IOException, InterruptedException {
	
		System.out.println("Criando conexão...");
		
		String[] msgs = new String[8];
		msgs[0] = "INSERT;PESSOA;12345678900;DANIEL;RIO DO SUL";
		msgs[1] = "INSERT;PESSOA;12345678901;DANIEL KLUG;RIO DO SUL";
		msgs[2] = "UPDATE;PESSOA;12345678900;DANIEL LARION KLUG;RIO DO SUL";
		msgs[3] = "LIST;PESSOA";
		msgs[4] = "GET;PESSOA;12345678900";
		msgs[5] = "GET;PESSOA;12345678901";
		msgs[6] = "DELETE;PESSOA;12345678901";
		msgs[7] = "LIST;PESSOA";
		
		for(int i = 0; i < msgs.length; i++) {
			
			try (Socket conn = new Socket ("127.0.0.1", 80);) {
				//System.out.println("Conectando");
				OutputStream out = conn.getOutputStream();
				
				out.write(msgs[i].getBytes());
				
				InputStream in = conn.getInputStream();
				byte[] dadosBrutos = new byte[1024];
				int qtdBytesLidos = in.read(dadosBrutos);
				String msgIn = null;
				while(qtdBytesLidos >= 0) {
					msgIn = new String (dadosBrutos, 0, qtdBytesLidos);
					System.out.println(msgIn);
					qtdBytesLidos = in.read(dadosBrutos);
				}
				
			} catch (UnknownHostException e) {
				System.out.println("Host não encontrado");
				e.printStackTrace();
			}
			
		}
	}

}

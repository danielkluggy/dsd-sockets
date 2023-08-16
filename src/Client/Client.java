package Client;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

public class Client {
	
	public static void main (String[] args) throws IOException {
	
		System.out.println("Criando conexão...");
		try (Socket conn = new Socket ("127.0.0.1", 80);) {
			System.out.println("Conectando");
			OutputStream out = conn.getOutputStream();
			String msg = "GET;PESSOA;12345678901;DANIEL;RIO DO SUL";
			out.write(msg.getBytes());
			
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

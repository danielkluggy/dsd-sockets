package Client;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

public class Client {
	
	public static void main (String[] args) throws IOException, InterruptedException {
	
		System.out.println("Criando conexão...");
		
		String[] msgs = new String[25];
		//Pessoa
		msgs[0] = "INSERT;PESSOA;12345678900;DANIEL;RIO DO SUL";
		msgs[1] = "INSERT;PESSOA;12345678901;DANIEL KLUG;RIO DO SUL";
		msgs[2] = "INSERT;PESSOA;12345678902;DANIEL KLUG;RIO DO SUL";
		msgs[3] = "UPDATE;PESSOA;12345678903;DANIEL LARION KLUG;RIO DO SUL";
		msgs[4] = "LIST;PESSOA";
		msgs[5] = "GET;PESSOA;12345678900";
		msgs[6] = "GET;PESSOA;12345678901";
		msgs[7] = "DELETE;PESSOA;12345678901";
		msgs[8] = "LIST;PESSOA";
		//TIME
		msgs[9] = "INSERT;TIME;SAO PAULO;BRASILEIRAO";
		msgs[10] = "INSERT;TIME;FLAMENGO;BRASILEIRAO";
		msgs[11] = "INSERT;TIME;BARCELONA;LIGA ESPANHOLA";
		msgs[12] = "UPDATE;TIME;FLAMENGO;COPA DO BRASIL";
		msgs[13] = "LIST;TIME";
		msgs[14] = "GET;TIME;SAO PAULO";
		msgs[15] = "GET;TIME;FLAMENGO";
		msgs[16] = "DELETE;TIME;FLAMENGO";
		msgs[17] = "LIST;TIME";
		msgs[18] = "INSERT;PESSOATIME;12345678900;DANIEL;RIO DO SUL;SAO PAULO";
		msgs[19] = "INSERT;PESSOATIME;12345678900;DANIEL;RIO DO SUL;FLAMENGO";
		msgs[20] = "INSERT;PESSOATIME;12345678900;DANIEL;RIO DO SUL,BARCELONA";
		msgs[21] = "INSERT;PESSOATIME;12345678902;DANIEL KLUG;RIO DO SUL;BARCELONA";
		msgs[22] = "GET;PESSOATIME;12345678900";
		msgs[23] = "DELETE;PESSOATIME;12345678900;DANIEL;SAO PAULO";
		msgs[24] = "LIST;PESSOATIME";
		
		for(int i = 0; i < msgs.length; i++) {
			
			try (Socket conn = new Socket ("10.1.1.104", 80);) {
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

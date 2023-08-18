package Client;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

public class Client {
	
	public static void main (String[] args) throws IOException, InterruptedException {
	
		//System.out.println("Criando conexão...");
		
		List<String> msgs = new ArrayList<String>();
		msgs.add("INSERT;JOGADOR;111;JOGADOR 1;RIO DO SUL;ARMADOR");
		msgs.add("INSERT;JOGADOR;222;JOGADOR 22;RIO DO SUL;ARMADOR");
		msgs.add("INSERT;JOGADOR;333;JOGADOR 3;RIO DO SUL;ARMADOR");
		msgs.add("INSERT;TECNICO;444;TECNICO 1;RIO DO SUL;HEAD COACH");
		msgs.add("INSERT;TECNICO;555;TECNICO 22;RIO DO SUL;HEAD COACH");
		msgs.add("INSERT;TECNICO;666;TECNICO 3;RIO DO SUL;HEAD COACH");	
		msgs.add("INSERT;JOGADOR;TIME 1;NBA");
		msgs.add("INSERT;JOGADOR;TIME 22;NBA");
		msgs.add("INSERT;JOGADOR;TIME 3;NBA");
		msgs.add("UPDATE;JOGADOR;222;JOGADOR 2;RIO DO SUL;ARMADOR");
		msgs.add("UPDATE;TECNICO;555;TECNICO 2;RIO DO SUL;HEAD COACH");
		msgs.add("UPDATE;JOGADOR;TIME 2;NBA");
		msgs.add("ADD_JOGADOR;TIME 1;111");
		msgs.add("ADD_JOGADOR;TIME 2;222");
		msgs.add("ADD_JOGADOR;TIME 3;333");
		msgs.add("ADD_TECNICO;TIME 1;444");
		msgs.add("ADD_TECNICO;TIME 2;555");
		msgs.add("ADD_TECNICO;TIME 2;666");
		msgs.add("LIST;JOGADOR");
		msgs.add("LIST;TECNICO");
		msgs.add("LIST;TIME");
		msgs.add("GET;JOGADOR;222");
		msgs.add("GET;TECNICO;555");
		msgs.add("GET;TIME;TIME 2");		
		msgs.add("DELETE;JOGADOR;333");
		msgs.add("DELETE;TECNICO;333");
		msgs.add("DELETE;TIME;TIME 3");
		msgs.add("LIST;JOGADOR");
		msgs.add("LIST;TECNICO");
		msgs.add("LIST;TIME");
		
		for(int i = 0; i < msgs.size(); i++) {
			
			try (Socket conn = new Socket ("127.0.0.1", 80);) {
				//System.out.println("Conectando");
				OutputStream out = conn.getOutputStream();
				
				out.write(msgs.get(i).getBytes());
				
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

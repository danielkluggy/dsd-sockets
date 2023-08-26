package Server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

import Server.Controller.JogadorController;
import Server.Controller.TecnicoController;
import Server.Controller.TimeController;
import Server.Model.Database;
import Server.Model.Modelo;
import Server.Model.Operacao;

public class ServerRunning {
	
	private static int porta;
	
	public ServerRunning(int porta) {
		ServerRunning.porta = porta;
	}
	
	public static void main(String[] args) throws IOException {
		Database db = new Database();
		@SuppressWarnings("resource")
		ServerSocket server = new ServerSocket(porta);
		server.setReuseAddress(true);
		
		Socket conn = null;
		System.out.println("Servidor iniciado no IP " + InetAddress.getLocalHost().getHostAddress() + ":" + porta);
		
		while (true) {
			try {
				conn = server.accept();
				String msg;
				System.out.println("Conectado com: " + conn.getInetAddress().getHostAddress());
	            BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
	            
	            msg = in.readLine();
	            if (msg != null) {
	            	comando(conn, db, msg);
	            }
			} catch (Exception e) {
				System.out.println("Deu exception");
	            e.printStackTrace();
			} finally {
				if (conn != null) {
					conn.close();
					System.out.println("Conexão com " + conn.getInetAddress().getHostAddress() + " encerrada");
				}
			}
		}
	}
	
	private static void comando(Socket conn, Database db, String mensagem) throws IOException {
		String[] campos = mensagem.toUpperCase().split(";");
		Operacao operacao = Operacao.valueOf(campos[0]);
		Modelo modelo = Modelo.valueOf(campos[1]);
		JogadorController jogador = new JogadorController(db, campos, operacao);
		TecnicoController tecnico = new TecnicoController(db, campos, operacao);
		TimeController time = new TimeController(db, campos, operacao);
		
		
		switch (modelo) {
		case JOGADOR:
			jogador.comando(conn);
			break;
		case TECNICO:
			tecnico.comando(conn);
			break;
		case TIME:
			time.comando(conn);
		default:
			break;
		}
	}

}

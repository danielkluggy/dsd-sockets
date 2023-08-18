package Server;

import java.io.IOException;
import java.io.InputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

import Server.Controller.JogadorController;
import Server.Controller.TecnicoController;
import Server.Controller.TimeController;
import Server.Model.Database;
import Server.Model.Modelo;
import Server.Model.Operacao;

public class Server {

	public static void main(String[] args) throws IOException {
		final int porta = 80;
		Database db = new Database();
		@SuppressWarnings("resource")
		ServerSocket server = new ServerSocket(porta);
		server.setReuseAddress(true);
		
		Socket conn = null;
		System.out.println("Servidor iniciado no IP " + InetAddress.getLocalHost().getHostAddress() + ":" + porta);
		
		while (true) {
			try {
				conn = server.accept();
				System.out.println("Conectado com: " + conn.getInetAddress().getHostAddress());
				InputStream in = conn.getInputStream();
				byte[] dadosBrutos = new byte[1024];
				int qtdBytesLidos = in.read(dadosBrutos);
				String msg = new String (dadosBrutos, 0, qtdBytesLidos);
				System.out.println(msg);
				
				comando(conn, db, msg);
			} catch (Exception e) {
                System.out.println("Exception");
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
		String[] campos = mensagem.split(";");
		Operacao operacao = Operacao.valueOf(campos[0]);
		Modelo modelo = Modelo.valueOf(campos[1]);
		
		switch (modelo) {
		case JOGADOR:
			//Usar um singleton pra criar uma única pessoa controller
			JogadorController pessoa = new JogadorController(conn, db, campos, operacao);
			pessoa.comando();
			break;
		case TECNICO:
			TecnicoController tecnico = new TecnicoController(conn, db, campos, operacao);
			tecnico.comando();
			break;
		case TIME:
			TimeController time = new TimeController(conn, db, campos, operacao);
			time.comando();
		default:
			break;
		}
	}

}

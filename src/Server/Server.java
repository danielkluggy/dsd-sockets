package Server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
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
		
		try {
			conn = server.accept();
			String msg = null;
			System.out.println("Conectado com: " + conn.getInetAddress().getHostAddress());
            BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            PrintWriter out = new PrintWriter(conn.getOutputStream(), true);
            
            msg += in.readLine();
            System.out.println(in.readLine());
            

		} catch (Exception e) {
			System.out.println("Deu exception");
            e.printStackTrace();
		} finally {
			if (conn != null) {
				conn.close();
				System.out.println("Conexão com " + conn.getInetAddress().getHostAddress() + " encerrada");
			}
		}
		
		
		/*while (true) {
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
		}*/
	}
	
	private static void comando(PrintWriter out, Database db, String mensagem) throws IOException {
		String[] campos = mensagem.toUpperCase().split(";");
		Operacao operacao = Operacao.valueOf(campos[0]);
		Modelo modelo = Modelo.valueOf(campos[1]);
		
		switch (modelo) {
		case JOGADOR:
			//Usar um singleton pra criar uma única pessoa controller
			JogadorController pessoa = new JogadorController(out, db, campos, operacao);
			pessoa.comando();
			break;
		case TECNICO:
			TecnicoController tecnico = new TecnicoController(out, db, campos, operacao);
			tecnico.comando();
			break;
		case TIME:
			TimeController time = new TimeController(out, db, campos, operacao);
			time.comando();
		default:
			break;
		}
	}

}

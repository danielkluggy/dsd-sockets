package Server.Controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;

public abstract class MessageController {
	
	Database db;
	String[] campos;
	Operacao operacao;
	Socket conn;
		
	public MessageController(Database db, String[] campos, Operacao operacao) throws IOException {
		this.db = db;
		this.campos = campos;
		this.operacao = operacao;
	}

	public void comando(Socket conn) throws IOException {
		this.conn = conn;
		switch (this.operacao) {
		case INSERT:
			insert();
			break;
		case UPDATE:
			update();
			break;
		case GET:
			get();
			break;
		case DELETE:
			delete();
			break;
		case LIST:
			list();
			break;
		default:
			break;
		}
	}
	
	protected void msgOut(String msg) throws IOException {
		PrintWriter out = new PrintWriter(conn.getOutputStream(), true);
		out.println(msg);
	}
		
	public abstract void insert() throws IOException;
	public abstract void update() throws IOException;
	public abstract void get() throws IOException;
	public abstract void delete() throws IOException;
	public abstract void list() throws IOException;
}

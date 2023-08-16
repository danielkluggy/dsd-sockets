package Server.Controller;

import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;

import Server.Model.Database;
import Server.Model.Operacao;

public abstract class MessageController {
	
	Socket conn;
	Database db;
	String[] campos;
	Operacao operacao;
	OutputStream out;
		
	public MessageController(Socket conn, Database db, String[] campos, Operacao operacao) throws IOException {
		this.conn = conn;
		this.db = db;
		this.campos = campos;
		this.operacao = operacao;
		this.out = conn.getOutputStream();
	}

	public void comando() throws IOException {		
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
		
	public abstract void insert() throws IOException;
	public abstract void update() throws IOException;
	public abstract void get() throws IOException;
	public abstract void delete() throws IOException;
	public abstract void list() throws IOException;
}

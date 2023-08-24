package Server.Controller;

import java.io.IOException;
import java.io.PrintWriter;

import Server.Model.Database;
import Server.Model.Operacao;

public abstract class MessageController {
	
	PrintWriter out;
	Database db;
	String[] campos;
	Operacao operacao;
		
	public MessageController(PrintWriter out, Database db, String[] campos, Operacao operacao) throws IOException {
		this.out = out;
		this.db = db;
		this.campos = campos;
		this.operacao = operacao;
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
		case ADD_JOGADOR:
			add_jogador();
		case ADD_TECNICO:
			add_tecnico();
		default:
			break;
		}
	}
		
	public abstract void insert() throws IOException;
	public abstract void update() throws IOException;
	public abstract void get() throws IOException;
	public abstract void delete() throws IOException;
	public abstract void list() throws IOException;
	
	public void add_tecnico() throws IOException {}
	public void add_jogador() throws IOException {}
		
}

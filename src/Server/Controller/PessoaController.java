package Server.Controller;

import java.io.IOException;
import java.net.Socket;

import Server.Model.Database;
import Server.Model.Operacao;
import Server.Model.Pessoa;

public class PessoaController extends MessageController {

	String msg;
	
	public PessoaController(Socket conn, Database db, String[] campos, Operacao operacao) throws IOException {
		super(conn, db, campos, operacao);
	}

	@Override
	public void insert() throws IOException {
		boolean cpfExiste = false;
		for(Pessoa pessoa : db.pessoas) {
			if(pessoa.getCpf().equals(campos[2])) {
				cpfExiste = true;
				break;
			}
		}
		if(cpfExiste == true) {
			msg = "Pessoa já cadastrada";
		} else {
			Pessoa pessoa = new Pessoa(campos[2]);
			pessoa.setNome(campos[3]);
			pessoa.setEndereco(campos[4]);
			db.pessoas.add(pessoa);
			msg = "Pessoa cadastrada";
		}
		out.write(msg.getBytes());
		out.close();
	}

	@Override
	public void update() throws IOException {
		boolean cpfExiste = false;
		for(Pessoa pessoa : db.pessoas) {
			if(pessoa.getCpf().equals(campos[2])) {
				cpfExiste = true;
				pessoa.setNome(campos[3]);
				pessoa.setEndereco(campos[4]);
				break;
			}
		}
		if(cpfExiste == true) {
			msg = "Pessoa atualizada com sucesso";
		} else {
			msg = "Pessoa não encontrada";
		}
		out.write(msg.getBytes());
		out.close();
	}

	@Override
	public void get() throws IOException {
		boolean cpfExiste = false;
		if(db.pessoas.size() > 0) {
			for(Pessoa pessoa : db.pessoas) {
				if(pessoa.getCpf().equals(campos[2])) {
					cpfExiste = true;
					msg = pessoa.toString();
					break;
				}
			}
			if(cpfExiste == false) {
				msg = "Pessoa não encontrada";
			}
		} else {
			msg = "Sem pessoas cadastradas";
		}
		out.write(msg.getBytes());
		out.close();
	}

	@Override
	public void delete() throws IOException {
		boolean cpfExiste = false;
		if(db.pessoas.size() > 0) {
			for(Pessoa pessoa : db.pessoas) {
				if(pessoa.getCpf().equals(campos[2])) {
					cpfExiste = true;
					db.pessoas.remove(pessoa);
					msg = "Pessoa removida com sucesso";
					break;
				}
			}
			if(cpfExiste == false) {
				msg = "Pessoa não encontrada";
			}
		} else {
			msg = "Sem pessoas cadastradas";
		}
		out.write(msg.getBytes());
		out.close();
	}

	@Override
	public void list() throws IOException {
		msg = String.valueOf(db.pessoas.size());
		for(Pessoa pessoa : db.pessoas) {
			msg += "\n" + pessoa.toString();
		}
		out.write(msg.getBytes());
		out.close();
	}
}
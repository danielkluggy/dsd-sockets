package Server.Controller;

import java.io.IOException;
import java.net.Socket;

import Server.Model.Database;
import Server.Model.Operacao;
import Server.Model.Pessoa;

public class PessoaController extends MessageController {

	String cpf;
	String nome = null;
	String endereco = null;
	String msg;
	
	public PessoaController(Socket conn, Database db, String[] campos, Operacao operacao) throws IOException {
		super(conn, db, campos, operacao);
		this.cpf = campos[2];
		this.nome = campos[3];
		this.endereco = campos[4];
	}

	@Override
	public void insert() throws IOException {
		boolean cpfExiste = false;
		for(Pessoa pessoa : db.pessoas) {
			if(pessoa.getCpf().equals(cpf)) {
				cpfExiste = true;
				break;
			}
		}
		if(cpfExiste == true) {
			msg = "Pessoa já cadastrada";
		} else {
			Pessoa pessoa = new Pessoa(cpf);
			pessoa.setNome(nome);
			pessoa.setEndereco(endereco);
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
			if(pessoa.getCpf().equals(cpf)) {
				cpfExiste = true;
				pessoa.setNome(nome);
				pessoa.setEndereco(endereco);
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
				if(pessoa.getCpf().equals(cpf)) {
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
				if(pessoa.getCpf().equals(cpf)) {
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

package Server.Controller;

import java.io.IOException;

import Server.Model.Pessoa;

public class PessoaController extends MessageController {

	public PessoaController(Database db, String[] campos, Operacao operacao) throws IOException {
		super(db, campos, operacao);
	}
	
	@Override
	public void insert() throws IOException {
		String msg = "";
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
		msgOut(msg);
	}

	@Override
	public void update() throws IOException {
		String msg = "";
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
		msgOut(msg);
	}

	@Override
	public void get() throws IOException {
		String msg = "";
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
		msgOut(msg);
	}

	@Override
	public void delete() throws IOException {
		String msg = "";
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
		msgOut(msg);
	}

	@Override
	public void list() throws IOException {
		String msg = "";
		msg = String.valueOf(db.pessoas.size());
		for(Pessoa pessoa : db.pessoas) {
			msg += ("\n\t" + pessoa.toString());
		}
		msgOut(msg);
	}
}

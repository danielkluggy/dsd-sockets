package Server.Controller;

import java.io.IOException;
import java.net.Socket;

import Server.Model.Database;
import Server.Model.Operacao;
import Server.Model.Pessoa;
import Server.Model.PessoaTime;
import Server.Model.Time;

public class PessoaTimeController extends MessageController {
	String msg;

	public PessoaTimeController(Socket conn, Database db, String[] campos, Operacao operacao) throws IOException {
		super(conn, db, campos, operacao);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public void get() throws IOException {
		boolean timeExiste = false;
		if(db.pessoasTimes.size() > 0) {
			for(PessoaTime pessoaTime : db.pessoasTimes) {
				if(pessoaTime.getCpf().equals(campos[2])) {
					timeExiste = true;
					msg = pessoaTime.toString();
					break;
				}
			}
			if(timeExiste == false) {
				msg = "pessoaTime não encontrado";
			}
		} else {
			msg = "Sem Times cadastradas";
		}
		out.write(msg.getBytes());
		out.close();	
	}

	
	@Override
	public void update() throws IOException {
		
	}

	@Override
	public void list() throws IOException {
		msg = String.valueOf(db.pessoasTimes.size());
		for(PessoaTime pessoasTimes : db.pessoasTimes) {
			msg += "\n" + pessoasTimes.toString();
		}
		out.write(msg.getBytes());
		out.close();
		
	}
	
	@Override
	public void insert() throws IOException {
		if(!verificaTimeExiste(campos[5]) || verificaJogadorPossuiTime(campos[2])) {
			msg = "N�o foi possivel realizar o vinculo "
				+ "verifique os dados e tente novamente";
		}else {
			Time time = buscaTime(campos[5]);
			Pessoa pessoa = buscaJogador(campos[2]);
			PessoaTime pessoaTime = new PessoaTime(campos[2], campos[5]); 
			time.adicionarPessoas(pessoa);
			db.pessoasTimes.add(pessoaTime);
			msg = "Jogador vinculado ao time com sucesso " +pessoaTime.toString();
		}
		out.write(msg.getBytes());
		out.close();	
	}
	
	@Override
	public void delete() throws IOException {
		boolean cpfExiste = false;
		if(db.pessoasTimes.size() > 0) {
			for(PessoaTime pessoaTime : db.pessoasTimes) {
				if(pessoaTime.getCpf().equals(campos[2])) {
					cpfExiste = true;
					Time time = buscaTime(campos[5]);
					Pessoa pessoa = buscaJogador(campos[2]);
					db.pessoasTimes.remove(pessoaTime);
					time.removerPessoas(pessoa);
					msg = "Pessoa removida com sucesso";
					break;
				}
			}
			if(cpfExiste == false) {
				msg = "Pessoa não encontrada";
			}
		} else {
			msg = "Pessoa Times cadastradas";
		}
		out.write(msg.getBytes());
		out.close();
	}
	
	public boolean verificaPessoaExiste(String cpf) {
		if(buscaJogador(cpf) == null) {
			return false;
		}
		return true;
		
	}
	
	public boolean verificaTimeExiste(String time) {
		if(buscaTime(time) == null) {
			return false;
		}
		return true;
		
	}
	
	public boolean verificaJogadorPossuiTime(String cpf) {	
		if(!verificaPessoaExiste(cpf)) {
			return true;
		}
		if(db.pessoasTimes.size() == 0) {
			return false;
		}
		for(PessoaTime pessoa : db.pessoasTimes) {
			if(pessoa.getCpf().equals(cpf)) {
				return true;
			}
		}
		return false;
	}
	
	public Time buscaTime(String time) {
		if(db.times.size() > 0) {
			for(Time times : db.times) {
				if(times.getTime().equals(time)) {
					return times;
				}
			}
		}
		return null;
	}
	
	public Pessoa buscaJogador(String cpf) {
		if(db.pessoas.size() > 0) {
			for(Pessoa pessoas : db.pessoas) {
				if(pessoas.getCpf().equals(cpf)) {
					return pessoas;
				}
			}
		}
		return null;
	}
	
}

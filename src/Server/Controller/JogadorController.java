package Server.Controller;

import java.io.IOException;
import java.io.PrintWriter;

import Server.Model.Database;
import Server.Model.Jogador;
import Server.Model.Operacao;
import Server.Model.Tecnico;

public class JogadorController extends MessageController {

	String msg;
	
	public JogadorController(PrintWriter out, Database db, String[] campos, Operacao operacao) throws IOException {
		super(out, db, campos, operacao);
	}

	@Override
	public void insert() throws IOException {
		boolean cpfExiste = false;
		for(Jogador jogador : db.jogadores) {
			if(jogador.getCpf().equals(campos[2])) {
				cpfExiste = true;
				break;
			}
		}
		for(Tecnico tecnico : db.tecnicos) {
			if(tecnico.getCpf().equals(campos[2])) {
				cpfExiste = true;
				break;
			}
		}
		if(cpfExiste == true) {
			msg = "Pessoa já cadastrada";
		} else {
			Jogador jogador = new Jogador(campos[2]);
			jogador.setNome(campos[3]);
			jogador.setEndereco(campos[4]);
			jogador.setPosicao(campos[5]);
			db.jogadores.add(jogador);
			msg = "Jogador cadastrado";
		}
		out.println(msg);
		out.println("end");
		out.close();
	}

	@Override
	public void update() throws IOException {
		boolean cpfExiste = false;
		for(Jogador jogador : db.jogadores) {
			if(jogador.getCpf().equals(campos[2])) {
				cpfExiste = true;
				jogador.setNome(campos[3]);
				jogador.setEndereco(campos[4]);
				jogador.setPosicao(campos[5]);
				break;
			}
		}
		if(cpfExiste == true) {
			msg = "Jogador atualizado com sucesso";
		} else {
			msg = "Jogador não encontrado";
		}
		out.println(msg);
		out.close();
	}

	@Override
	public void get() throws IOException {
		boolean cpfExiste = false;
		if(db.jogadores.size() > 0) {
			for(Jogador jogador : db.jogadores) {
				if(jogador.getCpf().equals(campos[2])) {
					cpfExiste = true;
					msg = jogador.toString();
					break;
				}
			}
			if(cpfExiste == false) {
				msg = "Jogador não encontrado";
			}
		} else {
			msg = "Sem jogadores cadastrados";
		}
		out.println(msg);
		out.close();
	}

	@Override
	public void delete() throws IOException {
		boolean cpfExiste = false;
		if(db.jogadores.size() > 0) {
			for(Jogador jogador : db.jogadores) {
				if(jogador.getCpf().equals(campos[2])) {
					cpfExiste = true;
					db.jogadores.remove(jogador);
					if(jogador.getTime() != null) {
						jogador.getTime().removeJogador(jogador);
					}
					msg = "Jogador removido com sucesso";
					break;
				}
			}
			if(cpfExiste == false) {
				msg = "Jogador não encontrado";
			}
		} else {
			msg = "Sem jogadores cadastrados";
		}
		out.println(msg);
		out.close();
	}

	@Override
	public void list() throws IOException {
		msg = String.valueOf(db.jogadores.size());
		for(Jogador jogador : db.jogadores) {
			msg += "\n" + jogador.toString();
		}
		out.println(msg);
		out.close();
	}
}

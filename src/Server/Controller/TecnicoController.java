package Server.Controller;

import java.io.IOException;

import Server.Model.Database;
import Server.Model.Jogador;
import Server.Model.Operacao;
import Server.Model.Tecnico;

public class TecnicoController extends MessageController {

	public TecnicoController(Database db, String[] campos, Operacao operacao) throws IOException {
		super(db, campos, operacao);
	}

	@Override
	public void insert() throws IOException {
		String msg = "";
		boolean cpfExiste = false;
		for(Tecnico tecnico : db.tecnicos) {
			if(tecnico.getCpf().equals(campos[2])) {
				cpfExiste = true;
				break;
			}
		}
		for(Jogador jogador : db.jogadores) {
			if(jogador.getCpf().equals(campos[2])) {
				cpfExiste = true;
				break;
			}
		}
		if(cpfExiste == true) {
			msg = "Pessoa já cadastrada";
		} else {
			Tecnico tecnico = new Tecnico(campos[2]);
			tecnico.setNome(campos[3]);
			tecnico.setEndereco(campos[4]);
			tecnico.setEspecialidade(campos[5]);
			db.tecnicos.add(tecnico);
			msg = "Técnico cadastrado";
		}
		msgOut(msg);
	}

	@Override
	public void update() throws IOException {
		String msg = "";
		boolean cpfExiste = false;
		for(Tecnico tecnico : db.tecnicos) {
			if(tecnico.getCpf().equals(campos[2])) {
				cpfExiste = true;
				tecnico.setNome(campos[3]);
				tecnico.setEndereco(campos[4]);
				tecnico.setEspecialidade(campos[5]);
				break;
			}
		}
		if(cpfExiste == true) {
			msg = "Técnico atualizado com sucesso";
		} else {
			msg = "Técnico não encontrado";
		}
		msgOut(msg);
	}

	@Override
	public void get() throws IOException {
		String msg = "";
		boolean cpfExiste = false;
		if(db.tecnicos.size() > 0) {
			for(Tecnico tecnico : db.tecnicos) {
				if(tecnico.getCpf().equals(campos[2])) {
					cpfExiste = true;
					msg = tecnico.toString();
					break;
				}
			}
			if(cpfExiste == false) {
				msg = "Técnico não encontrado";
			}
		} else {
			msg = "Sem técnicos cadastrados";
		}
		msgOut(msg);
	}

	@Override
	public void delete() throws IOException {
		String msg = "";
		boolean cpfExiste = false;
		if(db.tecnicos.size() > 0) {
			for(Tecnico tecnico : db.tecnicos) {
				if(tecnico.getCpf().equals(campos[2])) {
					cpfExiste = true;
					db.tecnicos.remove(tecnico);
					if(tecnico.getTime() != null) {
						tecnico.getTime().removeTecnico(tecnico);
					}
					msg = "Técnico removido com sucesso";
					break;
				}
			}
			if(cpfExiste == false) {
				msg = "Técnico não encontrado";
			}
		} else {
			msg = "Sem técnicos cadastrados";
		}
		msgOut(msg);
	}

	@Override
	public void list() throws IOException {
		String msg = "";
		msg = String.valueOf(db.tecnicos.size());
		for(Tecnico tecnico : db.tecnicos) {
			msg += "\n -> " + tecnico.toString();
		}
		msgOut(msg);
	}
	
}

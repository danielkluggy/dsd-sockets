package Server.Controller;

import java.io.IOException;
import java.net.Socket;

import Server.Model.Database;
import Server.Model.Jogador;
import Server.Model.Operacao;
import Server.Model.Tecnico;
import Server.Model.Time;

public class TimeController extends MessageController {

	String msg;
	
	public TimeController(Socket conn, Database db, String[] campos, Operacao operacao) throws IOException {
		super(conn, db, campos, operacao);
	}

	@Override
	public void insert() throws IOException {
		boolean timeExiste = false;
		for(Time time : db.times) {
			if(time.getNome().equals(campos[2])) {
				timeExiste = true;
				break;
			}
		}
		if(timeExiste == true) {
			msg = "Time já cadastrado";
		} else {
			Time time = new Time(campos[2]);
			time.setLiga(campos[3]);
			db.times.add(time);
			msg = "Time cadastrado";
		}
		out.println(msg);
		out.close();
	}

	@Override
	public void update() throws IOException {
		boolean timeExiste = false;
		for(Time time : db.times) {
			if(time.getNome().equals(campos[2])) {
				timeExiste = true;
				time.setNome(campos[3]);
				time.setLiga(campos[4]);
				break;
			}
		}
		if(timeExiste == true) {
			msg = "Time atualizado com sucesso";
		} else {
			msg = "Time não encontrado";
		}
		out.println(msg);
		out.close();
	}

	@Override
	public void get() throws IOException {
		boolean timeExiste = false;
		if(db.times.size() > 0) {
			for(Time time : db.times) {
				if(time.getNome().equals(campos[2])) {
					timeExiste = true;
					msg = time.toString();
					for(Tecnico tecnico : time.getTecnicos()) {
						msg += "\n\t" + tecnico;
					}
					for(Jogador jogador : time.getJogadores()) {
						msg += "\n\t" + jogador;
					}
					break;
				}
			}
			if(timeExiste == false) {
				msg = "Time não encontrado";
			}
		} else {
			msg = "Sem times cadastrados";
		}
		out.println(msg);
		out.close();
	}

	@Override
	public void delete() throws IOException {
		boolean timeExiste = false;
		if(db.times.size() > 0) {
			for(Time time : db.times) {
				if(time.getNome().equals(campos[2])) {
					timeExiste = true;
					db.times.remove(time);
					msg = "Time removido com sucesso";
					break;
				}
			}
			if(timeExiste == false) {
				msg = "Time não encontrado";
			}
		} else {
			msg = "Sem times cadastrados";
		}
		out.println(msg);
		out.close();
	}

	@Override
	public void list() throws IOException {
		msg = String.valueOf(db.times.size());
		for(Time time : db.times) {
			msg += "\n" + time.toString();
			for(Tecnico tecnico : time.getTecnicos()) {
				msg += "\n\t" + tecnico;
			}
			for(Jogador jogador : time.getJogadores()) {
				msg += "\n\t" + jogador;
			}
		}
		out.println(msg);
		out.close();
	}
	
	@Override
	public void add_jogador() throws IOException {
		boolean timeExiste = false;
		if(db.times.size() > 0) {
			for(Time time : db.times) {
				if(time.getNome().equals(campos[2])) {
					timeExiste = true;
					boolean cpfExiste = false;
					if(db.jogadores.size() > 0) {
						for(Jogador jogador : db.jogadores) {
							if(jogador.getCpf().equals(campos[3])) {
								cpfExiste = true;
								time.addJogador(jogador);
								jogador.setTime(time);
								msg = "Jogador adicionado ao time";
								break;
							}
						}
						if(cpfExiste == false) {
							msg = "Jogador não encontrado";
						}
					} else {
						msg = "Sem jogadores cadastrados";
					}
					break;
				}
			}
			if(timeExiste == false) {
				msg = "Time não encontrado";
			}
		} else {
			msg = "Sem times cadastrados";
		}
		out.println(msg);
		out.close();
	}
	
	@Override
	public void add_tecnico() throws IOException {
		boolean timeExiste = false;
		if(db.times.size() > 0) {
			for(Time time : db.times) {
				if(time.getNome().equals(campos[2])) {
					timeExiste = true;
					boolean cpfExiste = false;
					if(db.tecnicos.size() > 0) {
						for(Tecnico tecnico : db.tecnicos) {
							if(tecnico.getCpf().equals(campos[3])) {
								cpfExiste = true;
								time.addTecnico(tecnico);
								tecnico.setTime(time);
								msg = "Técnico adicionado ao time";
								break;
							}
						}
						if(cpfExiste == false) {
							msg = "Técnico não encontrado";
						}
					} else {
						msg = "Sem técnicos cadastrados";
					}
					break;
				}
			}
			if(timeExiste == false) {
				msg = "Time não encontrado";
			}
		} else {
			msg = "Sem times cadastrados";
		}
		out.println(msg);
		out.close();
	}
}

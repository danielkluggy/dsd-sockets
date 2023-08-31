package Server.Controller;

import java.io.IOException;

import Server.Model.Pessoa;
import Server.Model.Time;

public class TimeController extends MessageController {

	public TimeController(Database db, String[] campos, Operacao operacao) throws IOException {
		super(db, campos, operacao);
	}

	@Override
	public void insert() throws IOException {
		String msg = "";
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
		msgOut(msg);
	}

	@Override
	public void update() throws IOException {
		String msg = "";
		boolean timeExiste = false;
		for(Time time : db.times) {
			if(time.getNome().equals(campos[2])) {
				timeExiste = true;
				time.setNome(campos[2]);
				time.setLiga(campos[3]);
				break;
			}
		}
		if(timeExiste == true) {
			msg = "Time atualizado com sucesso";
		} else {
			msg = "Time não encontrado";
		}
		msgOut(msg);
	}

	@Override
	public void get() throws IOException {
		String msg = "";
		boolean timeExiste = false;
		if(db.times.size() > 0) {
			for(Time time : db.times) {
				if(time.getNome().equals(campos[2])) {
					timeExiste = true;
					msg = time.toString();
					for(Pessoa pessoa : time.getPessoas()) {
						msg += "\n\t" + pessoa;
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
		msgOut(msg);
	}

	@Override
	public void delete() throws IOException {
		String msg = "";
		boolean timeExiste = false;
		if(db.times.size() > 0) {
			for(Time time : db.times) {
				if(time.getNome().equals(campos[2])) {
					timeExiste = true;
					for(Pessoa pessoa : db.times.get(db.times.indexOf(time)).getPessoas()) {
						pessoa.setTime(null);
					}
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
		msgOut(msg);
	}

	@Override
	public void list() throws IOException {
		String msg = "";
		msg = String.valueOf(db.times.size());
		for(Time time : db.times) {
			msg += "\n" + time.toString();
			for(Pessoa pessoa : time.getPessoas()) {
				msg += "\n\t" + pessoa;
			}
		}
		msgOut(msg);
	}
	
	@Override
	public void add_pessoa() throws IOException {
		String msg = "";
		boolean timeExiste = false;
		if(db.times.size() > 0) {
			for(Time time : db.times) {
				if(time.getNome().equals(campos[2])) {
					timeExiste = true;
					boolean cpfExiste = false;
					if(db.pessoas.size() > 0) {
						for(Pessoa pessoa : db.pessoas) {
							if(pessoa.getCpf().equals(campos[3])) {
								if (pessoa.getTime() == null) {
									cpfExiste = true;
									time.addPessoa(pessoa);
									pessoa.setTime(time);
									msg = "Pessoa adicionada ao time";
									break;
								} else {
									cpfExiste = true;
									msg = "Pessoa já possui time";
									break;
								}
							}
						}
						if(cpfExiste == false) {
							msg = "Pessoa não encontrada";
						}
					} else {
						msg = "Sem pessoas cadastradas";
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
		msgOut(msg);
	}
	
}

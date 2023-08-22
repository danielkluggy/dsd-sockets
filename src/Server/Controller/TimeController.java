package Server.Controller;

import java.io.IOException;
import java.net.Socket;

import Server.Model.Database;
import Server.Model.Operacao;
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
			if(time.getTime().equals(campos[2])) {
				timeExiste = true;
				break;
			}
		}
		if(timeExiste == true) {
			msg = "Time já cadastrada";
		} else {
			Time time = new Time();
			time.setTime(campos[2]);
			time.setLiga(campos[3]);
			db.times.add(time);
			msg = "Time cadastrado";
		}
		out.write(msg.getBytes());
		out.close();
	}

	@Override
	public void update() throws IOException {
		boolean cpfExiste = false;
		for(Time time : db.times) {
			if(time.getTime().equals(campos[2])) {
				cpfExiste = true;
				time.setTime(campos[2]);
				time.setLiga(campos[3]);
				break;
			}
		}
		if(cpfExiste == true) {
			msg = "Time atualizada com sucesso";
		} else {
			msg = "Time não encontrada";
		}
		out.write(msg.getBytes());
		out.close();
	}

	@Override
	public void get() throws IOException {
		boolean timeExiste = false;
		if(db.times.size() > 0) {
			for(Time Time : db.times) {
				if(Time.getTime().equals(campos[2])) {
					timeExiste = true;
					msg = Time.toString();
					break;
				}
			}
			if(timeExiste == false) {
				msg = "Time não encontrado";
			}
		} else {
			msg = "Sem Times cadastradas";
		}
		out.write(msg.getBytes());
		out.close();
	}

	@Override
	public void delete() throws IOException {
		boolean cpfExiste = false;
		if(db.times.size() > 0) {
			for(Time Time : db.times) {
				if(Time.getTime().equals(campos[2])) {
					cpfExiste = true;
					db.times.remove(Time);
					msg = "Time removida com sucesso";
					break;
				}
			}
			if(cpfExiste == false) {
				msg = "Time não encontrada";
			}
		} else {
			msg = "Sem Times cadastradas";
		}
		out.write(msg.getBytes());
		out.close();
	}

	@Override
	public void list() throws IOException {
		msg = String.valueOf(db.times.size());
		for(Time Time : db.times) {
			msg += "\n" + Time.toString();
		}
		out.write(msg.getBytes());
		out.close();
	}
}

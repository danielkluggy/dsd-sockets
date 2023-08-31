package Server.Controller;

import java.util.ArrayList;
import java.util.List;

import Server.Model.Pessoa;
import Server.Model.Time;

public class Database {
	
	public Database() {
		mock();
	}
	
	public List<Pessoa> pessoas = new ArrayList<>();
	
	public List<Time> times = new ArrayList<>();
	
	
	private void mock() {
		Pessoa p1 = new Pessoa("12345678901");
		p1.setNome("JOGADOR 1");
		p1.setEndereco("RIO DO SUL");
		pessoas.add(p1);
		
		Pessoa p2 = new Pessoa("12345678902");
		p2.setNome("JOGADOR 2");
		p2.setEndereco("IBIRAMA");
		pessoas.add(p2);
		
		Time team1 = new Time("TIME 1");
		team1.setLiga("NBA");
		times.add(team1);
		
		Time team2 = new Time("TIME 2");
		team2.setLiga("NBA");
		times.add(team2);
		
		team1.addPessoa(p1);
		p1.setTime(team1);
		team1.addPessoa(p2);
		p2.setTime(team1);
	}
	
}

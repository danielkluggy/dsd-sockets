package Server.Controller;

import java.util.ArrayList;
import java.util.List;

import Server.Model.Jogador;
import Server.Model.Tecnico;
import Server.Model.Time;

public class Database {
	
	public Database() {
		mock();
	}
	
	public List<Jogador> jogadores = new ArrayList<>();
	
	public List<Tecnico> tecnicos = new ArrayList<>();
	
	public List<Time> times = new ArrayList<>();
	
	
	private void mock() {
		Jogador j1 = new Jogador("12345678901");
		j1.setNome("JOGADOR 1");
		j1.setEndereco("RIO DO SUL");
		j1.setPosicao("ARMADOR");
		jogadores.add(j1);
		
		Jogador j2 = new Jogador("12345678902");
		j2.setNome("JOGADOR 2");
		j2.setEndereco("IBIRAMA");
		j2.setPosicao("PIVÔ");
		jogadores.add(j2);
		
		Tecnico t1 = new Tecnico("98765432101");
		t1.setNome("TECNICO 1");
		t1.setEndereco("LONTRAS");
		t1.setEspecialidade("HEAD COACH");
		tecnicos.add(t1);
		
		Tecnico t2 = new Tecnico("98765432102");
		t2.setNome("TECNICO 2");
		t2.setEndereco("PRESIDENTE GETÚLIO");
		t2.setEspecialidade("OFFENSIVE COACH");
		tecnicos.add(t2);
		
		Time team1 = new Time("TIME 1");
		team1.setLiga("NBA");
		times.add(team1);
		
		Time team2 = new Time("TIME 2");
		team2.setLiga("NBA");
		times.add(team2);
		
		team1.addJogador(j1);
		j1.setTime(team1);
		team1.addJogador(j2);
		j2.setTime(team1);
		team1.addTecnico(t1);
		t1.setTime(team1);
		team1.addTecnico(t2);
		t2.setTime(team1);
	}
	
}

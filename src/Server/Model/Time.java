package Server.Model;

import java.util.ArrayList;
import java.util.List;

public class Time {
	
	private String nome;
	private String liga;
	private List<Tecnico> tecnicos;
	private List<Jogador> jogadores;
	
	public Time(String nome) {
		this.nome = nome;
		this.tecnicos = new ArrayList<Tecnico>();
		this.jogadores = new ArrayList<Jogador>();
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getLiga() {
		return liga;
	}

	public void setLiga(String liga) {
		this.liga = liga;
	}

	public List<Tecnico> getTecnicos() {
		return tecnicos;
	}

	public void addTecnico(Tecnico tecnico) {
		this.tecnicos.add(tecnico);
	}
	
	public void removeTecnico(Tecnico tecnico) {
		this.tecnicos.remove(tecnico);
	}

	public List<Jogador> getJogadores() {
		return jogadores;
	}

	public void addJogador(Jogador jogador) {
		this.jogadores.add(jogador);
	}
	
	public void removeJogador(Jogador jogador) {
		this.jogadores.remove(jogador);
	}

	@Override
	public String toString() {
		return nome + ";" + liga + ";tecnicos=" + tecnicos.size() + ";jogadores=" + jogadores.size();
	}
	
	
	
}

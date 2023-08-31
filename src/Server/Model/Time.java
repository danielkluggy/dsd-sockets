package Server.Model;

import java.util.ArrayList;
import java.util.List;

public class Time {
	
	private String nome;
	private String liga;
	private List<Pessoa> pessoas;
	
	public Time(String nome) {
		this.nome = nome;
		this.pessoas = new ArrayList<Pessoa>();
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

	public List<Pessoa> getPessoas() {
		return pessoas;
	}

	public void addPessoa(Pessoa pessoa) {
		this.pessoas.add(pessoa);
	}
	
	public void removePessoa(Pessoa pessoa) {
		this.pessoas.remove(pessoa);
	}

	@Override
	public String toString() {
		return nome + ";" + liga + ";PESSOAS=" + pessoas.size();
	}
	
	
	
}

package Server.Model;

import java.util.ArrayList;
import java.util.List;

public class Time {
	
	private String time;
	private String liga;
	private List<Pessoa> pessoas;
	
	public Time() {
		 pessoas = new ArrayList<>();
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public String getLiga() {
		return liga;
	}

	public void setLiga(String liga) {
		this.liga = liga;
	}

	@Override
	public String toString() {
		return "Time: " + time + ", liga: " + liga;
	}
	
	public void adicionarPessoas(Pessoa pessoa) {
        this.pessoas.add(pessoa);
    }
	
	public void removerPessoas(Pessoa pessoa) {
        this.pessoas.remove(pessoa);
    }
	
	
	
}

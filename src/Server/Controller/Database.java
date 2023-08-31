package Server.Controller;

import java.util.ArrayList;
import java.util.List;

import Server.Model.Pessoa;

public class Database {
	
	public Database() {
		mock();
	}
	
	public List<Pessoa> pessoas = new ArrayList<>();
	
	private void mock() {
		Pessoa p1 = new Pessoa("12345678901");
		p1.setNome("JOGADOR 1");
		p1.setEndereco("RIO DO SUL");
		pessoas.add(p1);
		
		Pessoa p2 = new Pessoa("12345678902");
		p2.setNome("JOGADOR 2");
		p2.setEndereco("IBIRAMA");
		pessoas.add(p2);
		
	}
	
}

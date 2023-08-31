package Server.Model;

public class Pessoa {
	
	private String cpf;
	private String nome;
	private String endereco;
	private Time time;

	public Pessoa(String cpf) {
		this.cpf = cpf;
	}

	public String getCpf() {
		return cpf;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}
	
	public Time getTime() {
		return time;
	}

	public void setTime(Time time) {
		this.time = time;
	}

	@Override
	public String toString() {
		return cpf + ";" + nome + ";" + endereco;
	}
	
	

}

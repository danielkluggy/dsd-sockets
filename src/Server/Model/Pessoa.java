package Server.Model;

public class Pessoa {
	
	private String cpf;
	private String nome;
	private String endereco;

	public Pessoa() {
	}

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

	@Override
	public String toString() {
		return cpf + ";" + nome + ";" + endereco + ".";
	}
	
	

}

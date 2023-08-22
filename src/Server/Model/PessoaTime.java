package Server.Model;

public class PessoaTime {
	private String cpf;
	private String time;
	
	public PessoaTime(String cpf, String time) {
		this.cpf = cpf;
		this.time = time;
	}
	public String getCpf() {
		return cpf;
	}
	public String getTime() {
		return time;
	}
	@Override
	public String toString() {
		return "Pessoa cpf" + cpf + ", Time=" + time;
	}
	
	
	
	
}

package Server.Model;

public class Tecnico extends Pessoa {

	private String especialidade;
	
	public Tecnico(String cpf) {
		super(cpf);
	}

	public String getEspecialidade() {
		return especialidade;
	}

	public void setEspecialidade(String especialidade) {
		this.especialidade = especialidade;
	}

	@Override
	public String toString() {
		return super.toString() + ";TECNICO;" + especialidade;
	}
	
	

}

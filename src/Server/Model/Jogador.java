package Server.Model;

public class Jogador extends Pessoa {
	
	private String posicao;
	
	public Jogador(String cpf) {
		super(cpf);
	}

	public String getPosicao() {
		return posicao;
	}

	public void setPosicao(String posicao) {
		this.posicao = posicao;
	}

	@Override
	public String toString() {
		return super.toString() + ";jogador;" + posicao;
	}
	
	

}

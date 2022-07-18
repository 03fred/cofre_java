package project.model;

//através do extends consegue utilizar as funções da classe pai
public class Real extends Moeda{

	@Override
	public String info() {
		//retornar informação da moeda
		return "REAL";
		
	}

	@Override
	public void converter() {
		//converter para real
		valorPosConversao = valor;
	}

}

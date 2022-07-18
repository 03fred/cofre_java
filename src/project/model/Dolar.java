package project.model;

//através do extends consegue utilizar as funções da classe pai
public class Dolar extends Moeda {

	@Override
	//informar informação da moeda
	public String info() {
		return "DOLAR";

	}

	//converter para real
	@Override
	public void converter() {
		valorPosConversao = valor * 5.16;
	}

}

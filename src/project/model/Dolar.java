package project.model;

//atrav�s do extends consegue utilizar as fun��es da classe pai
public class Dolar extends Moeda {

	@Override
	//informar informa��o da moeda
	public String info() {
		return "DOLAR";

	}

	//converter para real
	@Override
	public void converter() {
		valorPosConversao = valor * 5.16;
	}

}

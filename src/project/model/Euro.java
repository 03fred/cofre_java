package project.model;

//através do extends consegue utilizar as funções da classe pai
public class Euro extends Moeda {

	@Override
	public String info() {
		//informar informação da moeda
		return "EURO";
	}

	@Override
	public void converter() {
		//converter para real
		valorPosConversao = valor * 5.28;
	}

}

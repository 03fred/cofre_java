package project.model;

//atrav?s do extends consegue utilizar as fun??es da classe pai
public class Euro extends Moeda {

	@Override
	public String info() {
		//informar informa??o da moeda
		return "EURO";
	}

	@Override
	public void converter() {
		//converter para real
		valorPosConversao = valor * 5.28;
	}

}

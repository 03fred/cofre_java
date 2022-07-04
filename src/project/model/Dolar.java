package project.model;

public class Dolar extends Moeda {

	@Override
	public String info() {
		return "DOLAR";

	}

	@Override
	public void converter() {
		valorPosConversao = valor * 5.16;
	}

}

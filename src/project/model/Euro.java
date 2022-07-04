package project.model;

public class Euro extends Moeda {

	@Override
	public String info() {
		return "EURO";
	}

	@Override
	public void converter() {
		valorPosConversao = valor * 5.28;
	}

}

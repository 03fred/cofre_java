package project.model;

public class Real extends Moeda{

	@Override
	public String info() {
		return "REAL";
		
	}

	@Override
	public void converter() {
		valorPosConversao = valor;
	}

}

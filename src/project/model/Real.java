package project.model;

//atrav?s do extends consegue utilizar as fun??es da classe pai
public class Real extends Moeda{

	@Override
	public String info() {
		//retornar informa??o da moeda
		return "REAL";
		
	}

	@Override
	public void converter() {
		//converter para real
		valorPosConversao = valor;
	}

}

package project.model;

public abstract class Moeda {
	
	public double valor;
	
	public double valorPosConversao;
	
	public abstract String info();
	
	public abstract void converter();

}

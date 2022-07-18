package project.model;

//objeto abstrato sem poder instanciar 
public abstract class Moeda {
	
	public double valor;
	
	public double valorPosConversao;
	
	public abstract String info();
	
	public abstract void converter();

}

package project;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JDialog;
import javax.swing.JOptionPane;

import project.model.Moeda;

public class Cofrinho {

	public List<Moeda> listaMoedas = new ArrayList<Moeda>();

	//adicionar moedas ao cofrinho
	public void adicionar(Moeda moeda, String valor) {
		//converter string para double
		moeda.valor = Double.parseDouble(valor);
		//adicionar o objeto moeda a lista de moedas
		listaMoedas.add(moeda);
	}

	//remover moeda
	public void remover(Moeda moeda, String valor) {
		//converter valor da moeda
		moeda.valor = Double.parseDouble(valor);
		//procurar o valor e o tipo de moeda na lista 
		for (Moeda m : listaMoedas) {
			if (m.valor == moeda.valor && moeda.getClass() == m.getClass()) {
				//remover a moeda
				listaMoedas.remove(m);
				break;
			}
		}
	}

	//listar as moedas
	public void listagemMoedas() {

		//se a lista estiver vazia retornar uma mensagem
		if (listaMoedas.size() == 0) {
			JOptionPane.showMessageDialog(null, "Não há informações para serem exibidas.");
			return;
		}

		String str = new String();
		//concatenar a lista de moedas
		for (Moeda moeda : listaMoedas) {
			str += " Tipo da moeda: " + moeda.info() + " | Valor " + moeda.valor + "\n";
		}

		//criar um painel para mostrar a lista de moedas
		JOptionPane optionPane = new JOptionPane();
		optionPane.setMessage(str);
		optionPane.setMessageType(JOptionPane.INFORMATION_MESSAGE);
		JDialog dialog = optionPane.createDialog(null, "Valores");
		dialog.setVisible(true);
	}
  //tomar as moedas
	public double totalConvertido() {
		double total = 0d;
		for (Moeda moeda : listaMoedas) {
			//converter para real
			moeda.converter();
			total += moeda.valorPosConversao;
		}

		return total;
	}

}
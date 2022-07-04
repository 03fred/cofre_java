package project;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JDialog;
import javax.swing.JOptionPane;

import project.model.Dolar;
import project.model.Moeda;

public class Cofrinho {

	public List<Moeda> listaMoedas = new ArrayList<Moeda>();

	public void adicionar(Moeda moeda, String valor) {
		moeda.valor = Double.parseDouble(valor);
		listaMoedas.add(moeda);
	}

	public void remover(Moeda moeda, String valor) {
		moeda.valor = Double.parseDouble(valor);
		for (Moeda m : listaMoedas) {
			if (m.valor == moeda.valor && moeda.getClass() == m.getClass()) {
				listaMoedas.remove(m);
				break;
			}
		}

	}

	public void listagemMoedas() {

		if (listaMoedas.size() == 0) {
			JOptionPane.showMessageDialog(null, "Não há informações para serem exibidas.");
			return;
		}

		String str = new String();
		for (Moeda moeda : listaMoedas) {
			str += " Tipo da moeda: " + moeda.info() + " | Valor " + moeda.valor + "\n";
		}

		JOptionPane optionPane = new JOptionPane();
		optionPane.setMessage(str);
		optionPane.setMessageType(JOptionPane.INFORMATION_MESSAGE);
		JDialog dialog = optionPane.createDialog(null, "Valores");
		dialog.setVisible(true);
	}

	public double totalConvertido() {
		double total = 0d;
		for (Moeda moeda : listaMoedas) {
			moeda.converter();
			total += moeda.valorPosConversao;
		}

		return total;
	}

}
package project;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.HashMap;
import java.util.Map;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import project.model.Dolar;
import project.model.Euro;
import project.model.Moeda;
import project.model.Real;

public class MenuController extends JFrame implements ItemListener, ActionListener {

	private static final long serialVersionUID = 1L;

	/* cria??o de botoes e estrutura de painel */
	private JButton btnAddEuro;
	private JButton btnRemoveEuro;

	private JButton btnAddDolar;
	private JButton btnARemoveDolar;

	private JButton btnAddReal;
	private JButton btnRemoveReal;
	private JButton btnListReal;

	private JButton btnSumReal;

	private JTextField textField;
	JMenu menu, submenu;
	JMenuItem item;
	private JPanel pCenter;
	private JPanel pNorth;
	/* fim da cria??o de botoes e estrutura de painel */
	
	//criando hashmap para controle da chamada dos bot?es
	private Map<JButton, Runnable> commands = new HashMap<>();
	//instanciando um novo objeto cofrinho para usar as fun??es de controle 
	private Cofrinho cofrinho = new Cofrinho();

	public void showDialog() {

		//aplicando configura??o para o painel ser exibido 
		setTitle("COFRE"); //setando o titulo 
		setSize(500, 400); //tamanho do painel
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

		// checa se o sistema tem suporte a iconTray
		getContentPane().setLayout(new BorderLayout());
		setLocationRelativeTo(null); //setando localiza??o do painel

		pNorth = new JPanel(); //criando uma nova instancia de painel
		JLabel label = new JLabel("Insira o valor da moeda : "); //criando um campo de texto para informa??o.
		label.setFont(new Font("Helvetica", Font.BOLD, 15)); //setando a fonte da letra
		label.setHorizontalAlignment(SwingConstants.CENTER); //setando a posi??o
		pNorth.add(label); //adidionando o texto no painel

		textField = new JTextField(20); 
		pNorth.add(textField);

		pCenter = new JPanel(); //criando uma nova instancia de painel
		pCenter.setLayout(new GridLayout(0, 2));  //adicionando o layout com duas colunas

		createButtons(); //criando os botoes
		pCenter.setBorder(BorderFactory.createMatteBorder(30, 30, 30, 30, new Color(240, 240, 240))); //adicionando bortda nesse painel

		//adicionando os paineis filhos ao painel principal setando sua posi??o
		getContentPane().add(pNorth, BorderLayout.NORTH); 
		getContentPane().add(pCenter, BorderLayout.CENTER);

		setLocationRelativeTo(null);
		// mostrando o painel na tela
		setVisible(true);

	}

	// fun??o privada ou seja s? pode ser chamada pelo menuController para criar os bot?es
	private void createButtons() {
		btnAddEuro = returnButton(btnAddEuro, "(+) ADICIONAR EURO"); //criar bot?o 
		commands.put(btnAddEuro, () -> cofrinho.adicionar(new Euro(), textField.getText())); //adicionar o bot?o ao hashMap
		pCenter.add(btnAddEuro); //adicionar o bot?o ao painel

		btnRemoveEuro = returnButton(btnRemoveEuro, "(-) REMOVER EURO"); //criar bot?o 
		commands.put(btnRemoveEuro, () -> cofrinho.remover(new Euro(), textField.getText()));//adicionar o bot?o ao hashMap
		pCenter.add(btnRemoveEuro);//adicionar o bot?o ao painel

		btnAddDolar = returnButton(btnAddDolar, "(+) ADICIONAR DOLAR"); //criar bot?o 
		commands.put(btnAddDolar, () -> cofrinho.adicionar(new Dolar(), textField.getText()));//adicionar o bot?o ao hashMap
		pCenter.add(btnAddDolar);//adicionar o bot?o ao painel

		btnARemoveDolar = returnButton(btnARemoveDolar, "(-) REMOVER DOLAR"); //criar bot?o 
		commands.put(btnARemoveDolar, () -> cofrinho.remover(new Dolar(), textField.getText()));//adicionar o bot?o ao hashMap
		pCenter.add(btnARemoveDolar);//adicionar o bot?o ao painel

		btnAddReal = returnButton(btnAddReal, "(+) ADICIONAR REAL"); //criar bot?o 
		commands.put(btnAddReal, () -> cofrinho.adicionar(new Real(), textField.getText()));//adicionar o bot?o ao hashMap
		pCenter.add(btnAddReal);//adicionar o bot?o ao painel

		btnRemoveReal = returnButton(btnRemoveReal, "(-) REMOVER REAL"); //criar bot?o 
		commands.put(btnRemoveReal, () -> cofrinho.remover(new Real(), textField.getText()));//adicionar o bot?o ao hashMap
		pCenter.add(btnRemoveReal);//adicionar o bot?o ao painel

		btnListReal = returnButton(btnListReal, "LISTAR MOEDAS"); //criar bot?o 
		commands.put(btnListReal, () -> cofrinho.listagemMoedas());//adicionar o bot?o ao hashMap
		pCenter.add(btnListReal);//adicionar o bot?o ao painel

		btnSumReal = returnButton(btnSumReal, "SOMA EM REAIS"); //criar bot?o 
		commands.put(btnSumReal, () -> somaTotal());//adicionar o bot?o ao hashMap
		pCenter.add(btnSumReal);//adicionar o bot?o ao painel

	}

	// fun??o criada para retornar uma instancia de bot?o com o tipo de bot?o e texto sendo recebido por paramtros.
	private JButton returnButton(JButton button, String label) {
		button = new JButton(label);
		button.addActionListener(this);
		button.setBorder(BorderFactory.createMatteBorder(5, 5, 10, 10, new Color(240, 240, 240)));
		button.setBackground(new Color(0, 109, 94));
		button.setForeground(new Color(255, 255, 255));
		return button;
	}

	//fun??o criada para executar a a??o dos bot?es
	@Override
	public void actionPerformed(ActionEvent e) {

		try {
			//se nenhuma moeda for informada o texto ? adicionado seta o valor como 0
			if (textField.getText().isEmpty() || textField.getText().trim() == "")
				textField.setText("0");

			Double.parseDouble(textField.getText().trim()); //converte texto para moeda
			commands.get(e.getSource()).run();  //pega o indice do objeto na lista e executa

		} catch (NumberFormatException ex) { //tratamento de excess?o caso o usu?rio n?o digita um valor v?lido 
			JOptionPane.showMessageDialog(null, "N?mero inv?lido !");
		}

	}

	//retorna o total convertido em reais
	public void somaTotal() {
		JOptionPane optionPane = new JOptionPane();
		optionPane.setMessage(cofrinho.totalConvertido());
		optionPane.setMessageType(JOptionPane.INFORMATION_MESSAGE);
		JDialog dialog = optionPane.createDialog(null, "Total de valores em reais.");
		dialog.setVisible(true);
	}

	@Override
	public void itemStateChanged(ItemEvent arg0) {
		// TODO Auto-generated method stub

	}

}
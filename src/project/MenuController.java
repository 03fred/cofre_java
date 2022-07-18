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

	/* criação de botoes e estrutura de painel */
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
	/* fim da criação de botoes e estrutura de painel */
	
	//criando hashmap para controle da chamada dos botões
	private Map<JButton, Runnable> commands = new HashMap<>();
	//instanciando um novo objeto cofrinho para usar as funções de controle 
	private Cofrinho cofrinho = new Cofrinho();

	public void showDialog() {

		//aplicando configuração para o painel ser exibido 
		setTitle("COFRE"); //setando o titulo 
		setSize(500, 400); //tamanho do painel
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

		// checa se o sistema tem suporte a iconTray
		getContentPane().setLayout(new BorderLayout());
		setLocationRelativeTo(null); //setando localização do painel

		pNorth = new JPanel(); //criando uma nova instancia de painel
		JLabel label = new JLabel("Insira o valor da moeda : "); //criando um campo de texto para informação.
		label.setFont(new Font("Helvetica", Font.BOLD, 15)); //setando a fonte da letra
		label.setHorizontalAlignment(SwingConstants.CENTER); //setando a posição
		pNorth.add(label); //adidionando o texto no painel

		textField = new JTextField(20); 
		pNorth.add(textField);

		pCenter = new JPanel(); //criando uma nova instancia de painel
		pCenter.setLayout(new GridLayout(0, 2));  //adicionando o layout com duas colunas

		createButtons(); //criando os botoes
		pCenter.setBorder(BorderFactory.createMatteBorder(30, 30, 30, 30, new Color(240, 240, 240))); //adicionando bortda nesse painel

		//adicionando os paineis filhos ao painel principal setando sua posição
		getContentPane().add(pNorth, BorderLayout.NORTH); 
		getContentPane().add(pCenter, BorderLayout.CENTER);

		setLocationRelativeTo(null);
		// mostrando o painel na tela
		setVisible(true);

	}

	// função privada ou seja só pode ser chamada pelo menuController para criar os botões
	private void createButtons() {
		btnAddEuro = returnButton(btnAddEuro, "(+) ADICIONAR EURO"); //criar botão 
		commands.put(btnAddEuro, () -> cofrinho.adicionar(new Euro(), textField.getText())); //adicionar o botão ao hashMap
		pCenter.add(btnAddEuro); //adicionar o botão ao painel

		btnRemoveEuro = returnButton(btnRemoveEuro, "(-) REMOVER EURO"); //criar botão 
		commands.put(btnRemoveEuro, () -> cofrinho.remover(new Euro(), textField.getText()));//adicionar o botão ao hashMap
		pCenter.add(btnRemoveEuro);//adicionar o botão ao painel

		btnAddDolar = returnButton(btnAddDolar, "(+) ADICIONAR DOLAR"); //criar botão 
		commands.put(btnAddDolar, () -> cofrinho.adicionar(new Dolar(), textField.getText()));//adicionar o botão ao hashMap
		pCenter.add(btnAddDolar);//adicionar o botão ao painel

		btnARemoveDolar = returnButton(btnARemoveDolar, "(-) REMOVER DOLAR"); //criar botão 
		commands.put(btnARemoveDolar, () -> cofrinho.remover(new Dolar(), textField.getText()));//adicionar o botão ao hashMap
		pCenter.add(btnARemoveDolar);//adicionar o botão ao painel

		btnAddReal = returnButton(btnAddReal, "(+) ADICIONAR REAL"); //criar botão 
		commands.put(btnAddReal, () -> cofrinho.adicionar(new Real(), textField.getText()));//adicionar o botão ao hashMap
		pCenter.add(btnAddReal);//adicionar o botão ao painel

		btnRemoveReal = returnButton(btnRemoveReal, "(-) REMOVER REAL"); //criar botão 
		commands.put(btnRemoveReal, () -> cofrinho.remover(new Real(), textField.getText()));//adicionar o botão ao hashMap
		pCenter.add(btnRemoveReal);//adicionar o botão ao painel

		btnListReal = returnButton(btnListReal, "LISTAR MOEDAS"); //criar botão 
		commands.put(btnListReal, () -> cofrinho.listagemMoedas());//adicionar o botão ao hashMap
		pCenter.add(btnListReal);//adicionar o botão ao painel

		btnSumReal = returnButton(btnSumReal, "SOMA EM REAIS"); //criar botão 
		commands.put(btnSumReal, () -> somaTotal());//adicionar o botão ao hashMap
		pCenter.add(btnSumReal);//adicionar o botão ao painel

	}

	// função criada para retornar uma instancia de botão com o tipo de botão e texto sendo recebido por paramtros.
	private JButton returnButton(JButton button, String label) {
		button = new JButton(label);
		button.addActionListener(this);
		button.setBorder(BorderFactory.createMatteBorder(5, 5, 10, 10, new Color(240, 240, 240)));
		button.setBackground(new Color(0, 109, 94));
		button.setForeground(new Color(255, 255, 255));
		return button;
	}

	//função criada para executar a ação dos botões
	@Override
	public void actionPerformed(ActionEvent e) {

		try {
			//se nenhuma moeda for informada o texto é adicionado seta o valor como 0
			if (textField.getText().isEmpty() || textField.getText().trim() == "")
				textField.setText("0");

			Double.parseDouble(textField.getText().trim()); //converte texto para moeda
			commands.get(e.getSource()).run();  //pega o indice do objeto na lista e executa

		} catch (NumberFormatException ex) { //tratamento de excessão caso o usuário não digita um valor válido 
			JOptionPane.showMessageDialog(null, "Número inválido !");
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
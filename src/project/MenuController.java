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
	private Map<JButton, Runnable> commands = new HashMap<>();
	private Cofrinho cofrinho = new Cofrinho();

	public void showDialog() {

		setTitle("COFRE");
		setSize(500, 400);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

		// checa se o sistema tem suporte a iconTray
		getContentPane().setLayout(new BorderLayout());
		setLocationRelativeTo(null);

		pNorth = new JPanel();
		JLabel label = new JLabel("Insira o valor da moeda : ");
		label.setFont(new Font("Helvetica", Font.BOLD, 15));
		label.setHorizontalAlignment(SwingConstants.CENTER);
		pNorth.add(label);

		textField = new JTextField(20);
		pNorth.add(textField);

		pCenter = new JPanel();
		pCenter.setLayout(new GridLayout(0, 2));
		createButtons();

		pCenter.setBorder(BorderFactory.createMatteBorder(30, 30, 30, 30, new Color(240, 240, 240)));

		getContentPane().add(pNorth, BorderLayout.NORTH);
		getContentPane().add(pCenter, BorderLayout.CENTER);

		setLocationRelativeTo(null);

		// pCenter = new JPanel();

		setVisible(true);

	}

	private void createButtons() {
		btnAddEuro = returnButton(btnAddEuro, "(+) ADICIONAR EURO");
		commands.put(btnAddEuro, () -> cofrinho.adicionar(new Euro(), textField.getText()));
		pCenter.add(btnAddEuro);

		btnRemoveEuro = returnButton(btnRemoveEuro, "(-) REMOVER EURO");
		commands.put(btnRemoveEuro, () -> cofrinho.remover(new Euro(), textField.getText()));
		pCenter.add(btnRemoveEuro);

		btnAddDolar = returnButton(btnAddDolar, "(+) ADICIONAR DOLAR");
		commands.put(btnAddDolar, () -> cofrinho.adicionar(new Dolar(), textField.getText()));
		pCenter.add(btnAddDolar);

		btnARemoveDolar = returnButton(btnARemoveDolar, "(-) REMOVER DOLAR");
		commands.put(btnARemoveDolar, () -> cofrinho.remover(new Dolar(), textField.getText()));
		pCenter.add(btnARemoveDolar);

		btnAddReal = returnButton(btnAddReal, "(+) ADICIONAR REAL");
		commands.put(btnAddReal, () -> cofrinho.adicionar(new Real(), textField.getText()));
		pCenter.add(btnAddReal);

		btnRemoveReal = returnButton(btnRemoveReal, "(-) REMOVER REAL");
		commands.put(btnRemoveReal, () -> cofrinho.remover(new Real(), textField.getText()));
		pCenter.add(btnRemoveReal);

		btnListReal = returnButton(btnListReal, "LISTAR MOEDAS");
		commands.put(btnListReal, () -> cofrinho.listagemMoedas());
		pCenter.add(btnListReal);

		btnSumReal = returnButton(btnSumReal, "SOMA EM REAIS");
		commands.put(btnSumReal, () -> somaTotal());
		pCenter.add(btnSumReal);

	}

	private JButton returnButton(JButton button, String label) {
		button = new JButton(label);
		button.addActionListener(this);
		button.setBorder(BorderFactory.createMatteBorder(5, 5, 10, 10, new Color(240, 240, 240)));
		button.setBackground(new Color(0, 109, 94));
		button.setForeground(new Color(255, 255, 255));
		return button;
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		try {
			if (textField.getText().isEmpty() || textField.getText().trim() == "")
				textField.setText("0");

			Double.parseDouble(textField.getText().trim());
			commands.get(e.getSource()).run();

		} catch (NumberFormatException ex) {
			JOptionPane.showMessageDialog(null, "Número inválido !");
		}

	}

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
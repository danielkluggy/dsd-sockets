package Client.View;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import java.awt.BorderLayout;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import java.awt.Dimension;
import javax.swing.SwingConstants;

import Client.SocketController;

import javax.swing.JButton;
import javax.swing.JTextPane;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;

@SuppressWarnings("serial")
public class ConsultarView extends JFrame {

	private MainView view;
	
	private JPanel top;
	private JComboBox<Modelo> cbSelect;
	private JPanel campos;
	private JLabel lblCPF;
	private JTextField tfCPF;
	private JLabel lblNome;
	private JTextField tfNome;
	private JButton btnConsultar;
	private JButton btnCancelar;
	private JPanel console;
	private JTextPane txtConsole;
	
	public ConsultarView(MainView view) {
		this.view = view;
		initialize();
	}

	private void initialize() {
		setResizable(false);
		setBounds(100, 100, 800, 400);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		addWindowListener(new WindowAdapter() {
			
			@Override
			public void windowClosing(WindowEvent e) {
				super.windowClosing(e);
				view.setVisible(true);
			}
		});
		
		top = new JPanel();
		getContentPane().add(top, BorderLayout.NORTH);
		cbSelect = new JComboBox<Modelo>();
		cbSelect.setPreferredSize(new Dimension(300, 25));
		cbSelect.setMaximumRowCount(4);
		cbSelect.setModel(new DefaultComboBoxModel<Modelo>(Modelo.values()));
		cbSelect.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				selectModel(e);
			}
		});
		top.add(cbSelect);
		
		campos = new JPanel();
		getContentPane().add(campos, BorderLayout.CENTER);
		
		lblCPF = new JLabel("CPF:");
		lblCPF.setHorizontalAlignment(SwingConstants.TRAILING);
		lblCPF.setPreferredSize(new Dimension(100, 20));
		campos.add(lblCPF);
		
		tfCPF = new JTextField();
		campos.add(tfCPF);
		tfCPF.setColumns(55);
		
		lblNome = new JLabel("Nome:");
		lblNome.setHorizontalAlignment(SwingConstants.TRAILING);
		lblNome.setPreferredSize(new Dimension(100, 20));
		campos.add(lblNome);
		
		tfNome = new JTextField();
		campos.add(tfNome);
		tfNome.setColumns(55);
		
		
		btnConsultar = new JButton("Consultar");
		btnConsultar.setPreferredSize(new Dimension(150, 30));
		btnConsultar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					consultar(e);
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		});
		campos.add(btnConsultar);
		
		btnCancelar = new JButton("Cancelar");
		btnCancelar.setPreferredSize(new Dimension(150, 30));
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cancelar(e);
			}
		});
		campos.add(btnCancelar);
		
		console = new JPanel();
		getContentPane().add(console, BorderLayout.SOUTH);
				
		txtConsole = new JTextPane();
		txtConsole.setEnabled(false);
		txtConsole.setEditable(false);
		txtConsole.setDisabledTextColor(new Color(255, 255, 255));
		txtConsole.setFont(new Font("Trebuchet MS", Font.PLAIN, 14));
		txtConsole.setBackground(new Color(0, 0, 51));
		txtConsole.setPreferredSize(new Dimension(750, 125));
		
		JScrollPane scroll = new JScrollPane(txtConsole);
		console.add(scroll);
		
		limpar();
		
	}
	
	private void consultar(ActionEvent e) throws IOException {	
		String msgErro = "Erro:";
		String msgEnvio = "";
		if (cbSelect.getSelectedItem() == Modelo.JOGADOR || cbSelect.getSelectedItem() == Modelo.TECNICO) {
			if (!tfCPF.getText().isEmpty() && !tfCPF.getText().matches("[0-9]+"))
				msgErro += "\nCPF inválido!";
		}
		if (msgErro != "Erro:") {
			mensagemErro(msgErro);
		} else {
			if (cbSelect.getSelectedItem() == Modelo.JOGADOR || cbSelect.getSelectedItem() == Modelo.TECNICO) {
				if(tfCPF.getText().isEmpty()) {
					msgEnvio = "LIST;" + cbSelect.getSelectedItem();
				} else {
					msgEnvio = "GET;" + cbSelect.getSelectedItem() + ";";
					msgEnvio += tfCPF.getText();
				}
			} else if (cbSelect.getSelectedItem() == Modelo.TIME) {
				if(tfNome.getText().isEmpty()) {
					msgEnvio = "LIST;" + cbSelect.getSelectedItem();
				} else {
					msgEnvio = "GET;" + cbSelect.getSelectedItem() + ";";
					msgEnvio += tfNome.getText();
				}
			}
			SocketController socket = new SocketController(view.getIp(), view.getPorta());
			socket.msgOut(msgEnvio);
			System.out.println(msgEnvio);
			txtConsole.setText(socket.msgIn());
		}
	}
	
	private void cancelar(ActionEvent e) {
		limpar();
		cbSelect.setSelectedIndex(0);
	}
	
	private void limpar() {
		tfCPF.setVisible(false);
		tfCPF.setText("");
		lblCPF.setVisible(false);
		tfNome.setVisible(false);
		tfNome.setText("");
		lblNome.setVisible(false);
		btnConsultar.setVisible(false);
		btnCancelar.setVisible(false);
		txtConsole.setVisible(false);
	}
	
	private void mensagemErro(String msg) {
		JOptionPane.showMessageDialog(null, msg, "Erro", JOptionPane.ERROR_MESSAGE);
		
	}
	
	private void selectModel(ActionEvent e) {
		if(cbSelect.getSelectedItem() == Modelo.SELECIONE) {
			limpar();
		} else if(cbSelect.getSelectedItem() == Modelo.JOGADOR || cbSelect.getSelectedItem() == Modelo.TECNICO) {
			limpar();
			tfCPF.setVisible(true);
			lblCPF.setVisible(true);
			btnConsultar.setVisible(true);
			btnCancelar.setVisible(true);
			txtConsole.setVisible(true);
		} else if(cbSelect.getSelectedItem() == Modelo.TIME) {
			limpar();
			tfNome.setVisible(true);
			lblNome.setVisible(true);
			btnConsultar.setVisible(true);
			btnCancelar.setVisible(true);
			txtConsole.setVisible(true);
		}
	}

}

package Client.View;

import javax.swing.JFrame;
import javax.swing.JPanel;
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
public class InserirView extends JFrame {

	private MainView view;
	
	private JPanel top;
	private JComboBox<Modelo> cbSelect;
	private JPanel campos;
	private JLabel lblNome;
	private JTextField tfNome;
	private JLabel lblCPF;
	private JTextField tfCPF;
	private JButton btnInserir;
	private JButton btnCancelar;
	private JPanel console;
	private JTextPane txtConsole;
	
	public InserirView(MainView view) {
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
		top.setPreferredSize(new Dimension(10, 70));
		getContentPane().add(top, BorderLayout.NORTH);
		
		lblNome = new JLabel("Nome do Time:");
		lblNome.setHorizontalAlignment(SwingConstants.TRAILING);
		lblNome.setPreferredSize(new Dimension(100, 20));
		top.add(lblNome);
		
		tfNome = new JTextField();
		tfNome.setColumns(55);
		top.add(tfNome);
		
		cbSelect = new JComboBox<Modelo>();
		cbSelect.setPreferredSize(new Dimension(300, 25));
		cbSelect.setMaximumRowCount(4);
		cbSelect.setModel(new DefaultComboBoxModel<Modelo>(Modelo.values()));
		cbSelect.removeItemAt(2);
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
				
		btnInserir = new JButton("Inserir");
		btnInserir.setPreferredSize(new Dimension(150, 30));
		btnInserir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					salvar(e);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		campos.add(btnInserir);
		
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
		console.add(txtConsole);
		
		limpar();
		
	}
	
	private void salvar(ActionEvent e) throws IOException {	
		String msgErro = "Erro:";
		String msgEnvio = "ADD_";
		if(cbSelect.getSelectedItem() == Modelo.PESSOA) {
			if (tfNome.getText().isEmpty())
				msgErro += "\nNome do time inválido!";
			if (tfCPF.getText().isEmpty() || !tfCPF.getText().matches("[0-9]+"))
				msgErro += "\nCPF inválido!";
		}
		if(msgErro != "Erro:") {
			mensagemErro(msgErro);
		} else {
			if(cbSelect.getSelectedItem() == Modelo.PESSOA) {
				msgEnvio += cbSelect.getSelectedItem() + ";TIME;";
				msgEnvio += tfNome.getText() + ";";
				msgEnvio += tfCPF.getText();
			}
			SocketController socket = new SocketController(view.getIp(), view.getPorta());
			socket.msgOut(msgEnvio);
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
		btnInserir.setVisible(false);
		btnCancelar.setVisible(false);
		txtConsole.setVisible(false);
		txtConsole.setText("");
	}
	
	private void mensagemErro(String msg) {
		JOptionPane.showMessageDialog(null, msg, "Erro", JOptionPane.ERROR_MESSAGE);
		
	}
	
	private void selectModel(ActionEvent e) {
		if(cbSelect.getSelectedItem() == Modelo.SELECIONE) {
			limpar();
			tfNome.setText("");
		} else if(cbSelect.getSelectedItem() == Modelo.PESSOA) {
			limpar();
			tfCPF.setVisible(true);
			lblCPF.setVisible(true);
			btnInserir.setVisible(true);
			btnCancelar.setVisible(true);
			txtConsole.setVisible(true);
		}
	}

}

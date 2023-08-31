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
public class ExcluirView extends JFrame {

	private MainView view;
	
	private JPanel top;
	private JComboBox<Modelo> cbSelect;
	private JPanel campos;
	private JLabel lblCPF;
	private JTextField tfCPF;
	private JLabel lblNome;
	private JTextField tfNome;
	private JButton btnExcluir;
	private JButton btnCancelar;
	private JPanel console;
	private JTextPane txtConsole;
	
	public ExcluirView(MainView view) {
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
		
		btnExcluir = new JButton("Excluir");
		btnExcluir.setPreferredSize(new Dimension(150, 30));
		btnExcluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					salvar(e);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		campos.add(btnExcluir);
		
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
		String msgEnvio = "DELETE;";
		if(cbSelect.getSelectedItem() == Modelo.JOGADOR || cbSelect.getSelectedItem() == Modelo.TECNICO) {
			if (tfCPF.getText().isEmpty() || !tfCPF.getText().matches("[0-9]+"))
				msgErro += "\nCPF inválido!";
		} else if(cbSelect.getSelectedItem() == Modelo.TIME) {
			if (tfNome.getText().isEmpty())
				msgErro += "\nNome inválido!";
		}
		if(msgErro != "Erro:") {
			mensagemErro(msgErro);
		} else {
			msgEnvio += cbSelect.getSelectedItem() + ";";
			if(cbSelect.getSelectedItem() == Modelo.JOGADOR || cbSelect.getSelectedItem() == Modelo.TECNICO) {
				msgEnvio += tfCPF.getText() + ";";
			} else if(cbSelect.getSelectedItem() == Modelo.TIME) {
				msgEnvio += tfNome.getText() + ";";
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
		tfNome.setVisible(false);
		tfNome.setText("");
		lblNome.setVisible(false);
		btnExcluir.setVisible(false);
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
			btnExcluir.setVisible(true);
			btnCancelar.setVisible(true);
			txtConsole.setVisible(true);
		} else if(cbSelect.getSelectedItem() == Modelo.TIME) {
			limpar();
			tfNome.setVisible(true);
			lblNome.setVisible(true);
			btnExcluir.setVisible(true);
			btnCancelar.setVisible(true);
			txtConsole.setVisible(true);
		}
	}

}

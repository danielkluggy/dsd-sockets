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

	private View view;
	
	private JPanel top;
	private JComboBox<Modelo> cbSelect;
	private JPanel campos;
	private JLabel lblCPF;
	private JTextField tfCPF;
	private JLabel lblNome;
	private JTextField tfNome;
	private JLabel lblEndereco;
	private JTextField tfEndereco;
	private JLabel lblPosicao;
	private JTextField tfPosicao;
	private JLabel lblEspecialidade;
	private JTextField tfEspecialidade;
	private JLabel lblLiga;
	private JTextField tfLiga;
	private JButton btnAdicionar;
	private JButton btnCancelar;
	private JPanel console;
	private JTextPane txtConsole;
	
	public ConsultarView(View view) {
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
		
		lblEndereco = new JLabel("Endereço:");
		lblEndereco.setHorizontalAlignment(SwingConstants.TRAILING);
		lblEndereco.setPreferredSize(new Dimension(100, 20));
		campos.add(lblEndereco);
		
		tfEndereco = new JTextField();
		tfEndereco.setColumns(55);
		campos.add(tfEndereco);
		
		lblPosicao = new JLabel("Posição:");
		lblPosicao.setHorizontalAlignment(SwingConstants.TRAILING);
		lblPosicao.setPreferredSize(new Dimension(100, 20));
		campos.add(lblPosicao);
		
		tfPosicao = new JTextField();
		tfPosicao.setColumns(55);
		campos.add(tfPosicao);
		
		lblEspecialidade = new JLabel("Especialidade:");
		lblEspecialidade.setPreferredSize(new Dimension(100, 20));
		lblEspecialidade.setHorizontalAlignment(SwingConstants.TRAILING);
		campos.add(lblEspecialidade);
		
		tfEspecialidade = new JTextField();
		tfEspecialidade.setColumns(55);
		campos.add(tfEspecialidade);
		
		lblLiga = new JLabel("Liga:");
		lblLiga.setPreferredSize(new Dimension(100, 20));
		lblLiga.setHorizontalAlignment(SwingConstants.TRAILING);
		campos.add(lblLiga);
		
		tfLiga = new JTextField();
		tfLiga.setColumns(55);
		campos.add(tfLiga);
		
		btnAdicionar = new JButton("Adicionar");
		btnAdicionar.setPreferredSize(new Dimension(150, 30));
		btnAdicionar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					salvar(e);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		campos.add(btnAdicionar);
		
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
		String msgEnvio = "INSERT;";
		if(cbSelect.getSelectedItem() == Modelo.JOGADOR) {
			if (tfCPF.getText().isEmpty() || !tfCPF.getText().matches("[0-9]+"))
				msgErro += "\nCPF inválido!";
			if (tfNome.getText().isEmpty())
				msgErro += "\nNome inválido!";
			if (tfEndereco.getText().isEmpty())
				msgErro += "\nEndereço inválido!";
			if (tfPosicao.getText().isEmpty())
				msgErro += "\nPosição inválida!";
		} else if(cbSelect.getSelectedItem() == Modelo.TECNICO) {
			
		} else if(cbSelect.getSelectedItem() == Modelo.TIME) {
			
		}
		if(msgErro != "Erro:") {
			mensagemErro(msgErro);
		} else {
			msgEnvio += cbSelect.getSelectedItem() + ";";
			msgEnvio += tfCPF.getText() + ";";
			msgEnvio += tfNome.getText() + ";";
			msgEnvio += tfEndereco.getText() + ";";
			msgEnvio += tfPosicao.getText();
			mensagemErro(msgEnvio);
			view.socket(msgEnvio);
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
		tfEndereco.setVisible(false);
		tfEndereco.setText("");
		lblEndereco.setVisible(false);
		tfPosicao.setVisible(false);
		tfPosicao.setText("");
		lblPosicao.setVisible(false);
		tfEspecialidade.setVisible(false);
		tfEspecialidade.setText("");
		lblEspecialidade.setVisible(false);
		tfLiga.setVisible(false);
		tfLiga.setText("");
		lblLiga.setVisible(false);
		btnAdicionar.setVisible(false);
		btnCancelar.setVisible(false);
		txtConsole.setVisible(false);
	}
	
	private void mensagemErro(String msg) {
		JOptionPane.showMessageDialog(null, msg, "Erro", JOptionPane.ERROR_MESSAGE);
		
	}
	
	private void selectModel(ActionEvent e) {
		if(cbSelect.getSelectedItem() == Modelo.SELECIONE) {
			limpar();
		} else if(cbSelect.getSelectedItem() == Modelo.JOGADOR) {
			limpar();
			tfCPF.setVisible(true);
			lblCPF.setVisible(true);
			tfNome.setVisible(true);
			lblNome.setVisible(true);
			tfEndereco.setVisible(true);
			lblEndereco.setVisible(true);
			tfPosicao.setVisible(true);
			lblPosicao.setVisible(true);
			btnAdicionar.setVisible(true);
			btnCancelar.setVisible(true);
			txtConsole.setVisible(true);
		} else if(cbSelect.getSelectedItem() == Modelo.TECNICO) {
			limpar();
			tfCPF.setVisible(true);
			lblCPF.setVisible(true);
			tfNome.setVisible(true);
			lblNome.setVisible(true);
			tfEndereco.setVisible(true);
			lblEndereco.setVisible(true);
			tfEspecialidade.setVisible(true);
			lblEspecialidade.setVisible(true);
			btnAdicionar.setVisible(true);
			btnCancelar.setVisible(true);
			txtConsole.setVisible(true);
		} else if(cbSelect.getSelectedItem() == Modelo.TIME) {
			limpar();
			tfNome.setVisible(true);
			lblNome.setVisible(true);
			tfLiga.setVisible(true);
			lblLiga.setVisible(true);
			btnAdicionar.setVisible(true);
			btnCancelar.setVisible(true);
			txtConsole.setVisible(true);
		}
	}

}

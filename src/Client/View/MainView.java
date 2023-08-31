package Client.View;

import javax.swing.JFrame;
import javax.swing.JPanel;

import Client.SocketController;

import java.awt.BorderLayout;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Dimension;

@SuppressWarnings("serial")
public class MainView extends JFrame {
	
	private String ip;
	private int porta;
	public SocketController socket;
	
	public MainView(String ip, int porta) {
		this.ip = ip;
		this.porta = porta;
		initialize();
	}
	
	public String getIp() {
		return ip;
	}

	public int getPorta() {
		return porta;
	}

	private void initialize() {
		setResizable(false);
		setBounds(100, 100, 250, 220);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel panel = new JPanel();
		getContentPane().add(panel, BorderLayout.CENTER);
		
		JButton btnAdicionar = new JButton("Adicionar");
		btnAdicionar.setPreferredSize(new Dimension(200, 30));
		btnAdicionar.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				acaoBtnAdicionar(e);
			}
		});
		panel.add(btnAdicionar);
		
		JButton btnEditar = new JButton("Editar");
		btnEditar.setPreferredSize(new Dimension(200, 30));
		btnEditar.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				acaoBtnEditar(e);
			}
		});
		panel.add(btnEditar);
		
		JButton btnExcluir = new JButton("Excluir");
		btnExcluir.setPreferredSize(new Dimension(200, 30));
		btnExcluir.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				acaoBtnExcluir(e);
			}
		});
		panel.add(btnExcluir);
		
		JButton btnConsultar = new JButton("Consultar");
		btnConsultar.setPreferredSize(new Dimension(200, 30));
		btnConsultar.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				acaoBtnConsultar(e);
			}
		});
		panel.add(btnConsultar);
		
	}
	
	public void acaoBtnAdicionar(ActionEvent acao) {
		AdicionarView adicionarView = new AdicionarView(this);
		adicionarView.setVisible(true);
		this.setVisible(false);
	}
	
	public void acaoBtnEditar(ActionEvent acao) {
		EditarView editarView = new EditarView(this);
		editarView.setVisible(true);
		this.setVisible(false);
	}
	
	public void acaoBtnExcluir(ActionEvent acao) {
		ExcluirView excluirView = new ExcluirView(this);
		excluirView.setVisible(true);
		this.setVisible(false);
	}
	
	public void acaoBtnConsultar(ActionEvent acao) {
		ConsultarView consultarView = new ConsultarView(this);
		consultarView.setVisible(true);
		this.setVisible(false);
	}
	
}

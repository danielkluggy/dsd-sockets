package Client.View;

import javax.swing.JFrame;
import javax.swing.JPanel;

import Client.Client;

import java.awt.BorderLayout;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.awt.event.ActionEvent;
import java.awt.Dimension;

@SuppressWarnings("serial")
public class View extends JFrame {
	
	public View() {
		initialize();
	}
	
	public String socket(String msgOut) throws IOException {
		Socket conn = null;
		String msgIn = "";
		try {
			conn = new Socket ("127.0.0.1", 80);
			System.out.println("Conectando");
			PrintWriter out = new PrintWriter(conn.getOutputStream(), true);
            BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            
            out.println(msgOut);
            while (in.readLine() != "end") {
            	msgIn += in.readLine();
            }
		} catch (Exception e) {
            System.out.println("Deu exception");
            e.printStackTrace();
		} finally {
            if (conn != null) {
                conn.close();
                System.out.println("Socket encerrado.");
            }
		}
		return msgIn;
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
		panel.add(btnEditar);
		
		JButton btnExcluir = new JButton("Excluir");
		btnExcluir.setPreferredSize(new Dimension(200, 30));
		panel.add(btnExcluir);
		
		JButton btnConsultar = new JButton("Consultar");
		btnConsultar.setPreferredSize(new Dimension(200, 30));
		panel.add(btnConsultar);
		
		JButton btnInserir = new JButton("Inserir no Time");
		btnInserir.setPreferredSize(new Dimension(200, 30));
		panel.add(btnInserir);
	}
	
	public void acaoBtnAdicionar(ActionEvent acao) {
		AdicionarView adicionarView = new AdicionarView(this);
		adicionarView.setVisible(true);
		this.setVisible(false);
		
	}
	
	public void acaoBtnEditar(ActionEvent acao) {
		
	}
	
	public void acaoBtnExcluir(ActionEvent acao) {
		
	}
	
	public void acaoBtnConsultar(ActionEvent acao) {
		
	}
	
	public void acaoBtnInserir(ActionEvent acao) {
		
	}

}

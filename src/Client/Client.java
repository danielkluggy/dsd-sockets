	package Client;

	import javax.swing.JFrame;
	import javax.swing.JPanel;
	import java.awt.BorderLayout;
	import javax.swing.JLabel;
	import javax.swing.JTextField;
	import java.awt.Dimension;
	import javax.swing.SwingConstants;

import Client.View.MainView;

import javax.swing.JButton;
	import java.awt.event.ActionEvent;
	import java.awt.event.ActionListener;

	@SuppressWarnings("serial")
	public class Client extends JFrame {

		private JPanel center;
		private JLabel lblIp;
		private JTextField tfIp;
		private JLabel lblPorta;
		private JTextField tfPorta;
		private JButton btnConectar;
		
		public static void main(String[] args) {
			Client client = new Client();
			client.setVisible(true);
		}
		
		public Client() {
			initialize();
		}

		private void initialize() {
			setResizable(false);
			setBounds(100, 100, 200, 180);
			setLocationRelativeTo(null);
			setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			
			center = new JPanel();
			getContentPane().add(center, BorderLayout.CENTER);
			
			lblIp = new JLabel("IP:");
			lblIp.setHorizontalAlignment(SwingConstants.CENTER);
			lblIp.setPreferredSize(new Dimension(100, 20));
			center.add(lblIp);
			
			tfIp = new JTextField();
			tfIp.setColumns(15);
			center.add(tfIp);
			
			lblPorta = new JLabel("Porta:");
			lblPorta.setHorizontalAlignment(SwingConstants.CENTER);
			lblPorta.setPreferredSize(new Dimension(100, 20));
			center.add(lblPorta);
			
			tfPorta = new JTextField();
			tfPorta.setColumns(15);
			center.add(tfPorta);
			
			btnConectar = new JButton("Consultar");
			btnConectar.setPreferredSize(new Dimension(120, 30));
			btnConectar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					conectar(e);
				}
			});
			center.add(btnConectar);
		}
		
		private void conectar(ActionEvent e) {
			MainView view = new MainView(tfIp.getText(), Integer.parseInt(tfPorta.getText()));
			view.setVisible(true);
			this.setVisible(false);
		}

	}
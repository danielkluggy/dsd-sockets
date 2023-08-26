	package Server;

	import javax.swing.JFrame;
	import javax.swing.JPanel;
	import java.awt.BorderLayout;
	import javax.swing.JLabel;
	import javax.swing.JOptionPane;
	import javax.swing.JTextField;
	import java.awt.Dimension;
	import javax.swing.SwingConstants;
	import javax.swing.JButton;
	import java.awt.event.ActionEvent;
	import java.awt.event.ActionListener;

	@SuppressWarnings("serial")
	public class Server extends JFrame {

		private JPanel center;
		private JLabel lblPorta;
		private JTextField tfPorta;
		private JButton btnConectar;
		
		public static void main(String[] args) {
			Server server = new Server();
			server.setVisible(true);
		}
				
		public Server() {
			initialize();
		}

		private void initialize() {
			setResizable(false);
			setBounds(100, 100, 200, 150);
			setLocationRelativeTo(null);
			setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			
			center = new JPanel();
			getContentPane().add(center, BorderLayout.CENTER);
			
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
			try {
				@SuppressWarnings("unused")
				ServerRunning server = new ServerRunning(Integer.parseInt(tfPorta.getText()));
				this.setVisible(false); 
				ServerRunning.main(null);
			} catch (Exception ex) {
				System.out.println("Deu exception");
				JOptionPane.showMessageDialog(null, "Porta incorreta!", "Erro", JOptionPane.ERROR_MESSAGE);
	            ex.printStackTrace();
			}
			
			
		}

	}
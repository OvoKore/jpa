package telas;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import java.awt.Toolkit;
import manager.Conexao;
import javax.persistence.TypedQuery;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import entidades.Player;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.ActionEvent;

public class Menu {

	public static JFrame frmMenu;
	private JTextField txfLogin;
	private JTextField txfSenha;
	public static String logon;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					new Conexao();
				} catch (Exception e) {
					JOptionPane.showMessageDialog(null, "Erro ao carregar.\nPor favor, reporte ao desenvolvedor.");
					e.printStackTrace();
					System.exit(1);
				}
				try {
					Menu window = new Menu();
					window.frmMenu.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public Menu(){
		initialize();
	}

	private void initialize() {
		frmMenu = new JFrame();
		frmMenu.setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Users\\note\\Documents\\jpa\\jpa\\src\\main\\resources\\icon\\icon.png"));
		frmMenu.setTitle("MENU");
		frmMenu.setBounds(100, 100, 374, 188);
		frmMenu.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmMenu.getContentPane().setLayout(null);
		
		JLabel lblLogin = new JLabel("LOGIN");
		lblLogin.setFont(new Font("Tahoma", Font.PLAIN, 25));
		lblLogin.setBounds(10, 11, 80, 24);
		frmMenu.getContentPane().add(lblLogin);
		
		JLabel lvlSenha = new JLabel("SENHA");
		lvlSenha.setFont(new Font("Tahoma", Font.PLAIN, 25));
		lvlSenha.setBounds(10, 46, 80, 24);
		frmMenu.getContentPane().add(lvlSenha);
		
		txfLogin = new JTextField();
		txfLogin.setFont(new Font("Tahoma", Font.PLAIN, 25));
		txfLogin.setBounds(100, 11, 248, 24);
		txfLogin.setColumns(10);
		txfLogin.addKeyListener(new KeyListener() {
			public void keyTyped(KeyEvent e) { }
			public void keyReleased(KeyEvent e) { }
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode() == KeyEvent.VK_ENTER)
					logon();
			}
		});
		frmMenu.getContentPane().add(txfLogin);
		
		
		txfSenha = new JPasswordField();
		txfSenha.setFont(new Font("Tahoma", Font.PLAIN, 25));
		txfSenha.setColumns(10);
		txfSenha.setBounds(100, 46, 248, 24);
		txfSenha.addKeyListener(new KeyListener() {
			public void keyTyped(KeyEvent e) {	}
			public void keyReleased(KeyEvent e) {	}
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode() == KeyEvent.VK_ENTER)
					logon();
			}
		});
		frmMenu.getContentPane().add(txfSenha);
		
		JButton btnLogon = new JButton("ENTRAR");
		btnLogon.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg) {
				logon();
			}
		});
		btnLogon.addKeyListener(new KeyListener() {
			public void keyTyped(KeyEvent e) { }
			public void keyReleased(KeyEvent e) { }
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode() == KeyEvent.VK_ENTER)
					logon();
			}
		});
		btnLogon.setFont(new Font("Tahoma", Font.PLAIN, 25));
		btnLogon.setBounds(10, 81, 338, 23);
		frmMenu.getContentPane().add(btnLogon);
		
		JButton btnOpcao = new JButton("OP\u00C7\u00C3O");	
		btnOpcao.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg) {
				new Opcao().OpenOpcao();
			}
		});
		btnOpcao.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnOpcao.setBounds(10, 115, 159, 23);
		frmMenu.getContentPane().add(btnOpcao);
		
		JButton btnCriarConta = new JButton("CRIAR CONTA");
		btnCriarConta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg) {
				new CriarConta().OpenCriaConta();
			}
		});
		btnCriarConta.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnCriarConta.setBounds(189, 115, 159, 23);
		frmMenu.getContentPane().add(btnCriarConta);
	}
	
	public void logon() {
		if (!(txfLogin.getText().length() == 0 || txfSenha.getText().length() == 0)) {
			TypedQuery<Player> query = Conexao.manager.em.createNamedQuery(Player.OBTER_POR_LOGIN_SENHA, Player.class)
					.setParameter("login", txfLogin.getText()).setParameter("senha", txfSenha.getText());
			Player player = query.getResultList().size() == 0 ? new Player() : query.setMaxResults(1).getSingleResult();
			
			if (player.getLogin() != null) {
				this.logon = txfLogin.getText();
				txfLogin.setText("");
				txfSenha.setText("");
				new SelecaoPersonagem().OpenSelecaoPersonagem();
			} else {
				JOptionPane.showMessageDialog(null, "Erro ao logar.");
			}
		}
	}
}
package telas;

import java.awt.EventQueue;
import javax.swing.JFrame;
import java.awt.Font;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.persistence.TypedQuery;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import entidades.Player;
import manager.Conexao;
import java.awt.Toolkit;

public class CriarConta {

	private JFrame frmCriarConta;
	private JTextField txfLogin;
	private JTextField txfSenha;
	private JTextField txfConfirma;

	public void OpenCriaConta() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CriarConta window = new CriarConta();
					window.frmCriarConta.addWindowListener(new WindowAdapter() {
			            @Override
			            public void windowClosing(WindowEvent e) {
			            	close();
			            }
			        });
					window.frmCriarConta.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public CriarConta() {
		initialize();
	}

	private void initialize() {
		Menu.frmMenu.hide();
		
		frmCriarConta = new JFrame();
		frmCriarConta.setTitle("CRIAR CONTA");
		frmCriarConta.setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Users\\note\\Documents\\jpa\\jpa\\src\\main\\resources\\icon\\icon.png"));
		frmCriarConta.getContentPane().setFont(new Font("Tahoma", Font.PLAIN, 25));
		frmCriarConta.setBounds(100, 100, 374, 210);
		frmCriarConta.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frmCriarConta.getContentPane().setLayout(null);
		
		JButton btnSalvar = new JButton("SALVAR");
		btnSalvar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg) {
				salvar();
			}
		});
		btnSalvar.setFont(new Font("Tahoma", Font.PLAIN, 25));
		btnSalvar.setBounds(10, 123, 338, 39);
		frmCriarConta.getContentPane().add(btnSalvar);
		
		JLabel lblLogin = new JLabel("LOGIN");
		lblLogin.setFont(new Font("Tahoma", Font.PLAIN, 25));
		lblLogin.setBounds(10, 11, 80, 31);
		frmCriarConta.getContentPane().add(lblLogin);
		
		JLabel lblSenha = new JLabel("SENHA");
		lblSenha.setFont(new Font("Tahoma", Font.PLAIN, 25));
		lblSenha.setBounds(10, 53, 80, 24);
		frmCriarConta.getContentPane().add(lblSenha);
		
		txfLogin = new JTextField();
		txfLogin.setFont(new Font("Tahoma", Font.PLAIN, 25));
		txfLogin.setColumns(10);
		txfLogin.setBounds(100, 11, 248, 31);
		txfLogin.addKeyListener(new KeyListener() {
			public void keyTyped(KeyEvent e) { }
			public void keyReleased(KeyEvent e) { }
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode() == KeyEvent.VK_ENTER)
					salvar();
			}
		});
		frmCriarConta.getContentPane().add(txfLogin);
		
		txfSenha = new JPasswordField();
		txfSenha.setFont(new Font("Tahoma", Font.PLAIN, 25));
		txfSenha.setColumns(10);
		txfSenha.setBounds(100, 53, 248, 24);
		txfSenha.addKeyListener(new KeyListener() {
			public void keyTyped(KeyEvent e) { }
			public void keyReleased(KeyEvent e) { }
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode() == KeyEvent.VK_ENTER)
					salvar();
			}
		});
		frmCriarConta.getContentPane().add(txfSenha);
		
		JLabel lblConfirma = new JLabel("CONFIRMA");
		lblConfirma.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblConfirma.setBounds(10, 88, 80, 24);
		frmCriarConta.getContentPane().add(lblConfirma);
		
		txfConfirma = new JPasswordField();
		txfConfirma.setFont(new Font("Tahoma", Font.PLAIN, 25));
		txfConfirma.setColumns(10);
		txfConfirma.setBounds(100, 88, 248, 24);
		txfConfirma.addKeyListener(new KeyListener() {
			public void keyTyped(KeyEvent e) { }
			public void keyReleased(KeyEvent e) { }
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode() == KeyEvent.VK_ENTER)
					salvar();
			}
		});
		frmCriarConta.getContentPane().add(txfConfirma);
	}
	
	private void close() {
		Menu.frmMenu.show();
		frmCriarConta.dispose();
	}
	
	private void salvar() {
		if (txfLogin.getText().length() == 0) {
			JOptionPane.showMessageDialog(null, "Login inválido.");
			return;
		}

		if (txfSenha.getText().length() == 0 && txfConfirma.getText().length() == 0) {
			JOptionPane.showMessageDialog(null, "Insira a senha.");
			return;
		}
		
		if (txfSenha.getText().length() == 0 || txfConfirma.getText().length() == 0) {
			JOptionPane.showMessageDialog(null, "Senhas incompativéis.");
			return;
		}
		
		if (!txfSenha.getText().equals(txfConfirma.getText())) {
			JOptionPane.showMessageDialog(null, "Senhas incompativéis");
			return;
		}
		
		TypedQuery<Player> query = Conexao.manager.em.createNamedQuery(Player.OBTER_POR_LOGIN, Player.class)
				.setParameter("login", txfLogin.getText());
		Player player = query.getResultList().size() == 0 ? new Player() : query.setMaxResults(1).getSingleResult();
		
		if (player.getLogin() == null) {
			player.setLogin(txfLogin.getText());
			player.setPassword(txfSenha.getText());
			Conexao.manager.Save(player);
			JOptionPane.showMessageDialog(null, "Conta cadastrada.");
			Menu.frmMenu.show();
			frmCriarConta.dispose();
		} else {
			JOptionPane.showMessageDialog(null, "Conta já existe.");
		}
	}
}

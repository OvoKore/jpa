package telas;

import java.awt.EventQueue;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JFrame;
import java.awt.Toolkit;
import javax.swing.JButton;
import java.awt.Font;
import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Opcao {

	public static JFrame frmOpcao;

	public void OpenOpcao() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Opcao window = new Opcao();
					window.frmOpcao.addWindowListener(new WindowAdapter() {
			            @Override
			            public void windowClosing(WindowEvent e) {
			            	close();
			            }
			        });
					window.frmOpcao.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public Opcao() {
		initialize();
	}

	private void initialize() {
		Menu.frmMenu.hide();
		
		frmOpcao = new JFrame();
		frmOpcao.setTitle("OP\u00C7\u00C3O");
		frmOpcao.setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Users\\note\\Documents\\jpa\\jpa\\src\\main\\resources\\icon\\icon.png"));
		frmOpcao.setBounds(100, 100, 225, 176);
		frmOpcao.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frmOpcao.getContentPane().setLayout(null);
		
		JButton btnRaca = new JButton("RA\u00C7A");
		btnRaca.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new EditarRaca().OpenEditarRaca();
			}
		});
		btnRaca.setFont(new Font("Tahoma", Font.PLAIN, 25));
		btnRaca.setBounds(10, 11, 189, 51);
		frmOpcao.getContentPane().add(btnRaca);
		
		JButton btnClasse = new JButton("CLASSE");
		btnClasse.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new EditarClasse().OpenEditarClasse();
			}
		});
		btnClasse.setVerticalAlignment(SwingConstants.BOTTOM);
		btnClasse.setFont(new Font("Tahoma", Font.PLAIN, 25));
		btnClasse.setBounds(10, 73, 189, 51);
		frmOpcao.getContentPane().add(btnClasse);
	}
	
	private void close() {
		Menu.frmMenu.show();
		frmOpcao.dispose();
	}	

}

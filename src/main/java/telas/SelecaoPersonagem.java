package telas;

import java.awt.EventQueue;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.text.NumberFormat;
import java.util.List;
import javax.swing.JFrame;
import java.awt.Toolkit;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.text.NumberFormatter;
import entidades.Personage;
import entidades.RelacaoPlayerPersonage;
import manager.Conexao;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;

public class SelecaoPersonagem {

	public static JFrame frmSelecaoPersonagem;
	private JComboBox cbxPersonagens;
	private JFormattedTextField txfFor;
	private JFormattedTextField txfInt;
	private JFormattedTextField txfVit;
	private JFormattedTextField txfCar;
	private JFormattedTextField txfDes;
	private JFormattedTextField txfVig;
	private JTextField txfRaca;
	private JTextField txfClasse;

	public void OpenSelecaoPersonagem() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SelecaoPersonagem window = new SelecaoPersonagem();
					window.frmSelecaoPersonagem.addWindowListener(new WindowAdapter() {
			            @Override
			            public void windowClosing(WindowEvent e) {
			            	close();
			            }
			        });
					window.frmSelecaoPersonagem.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public SelecaoPersonagem() {
		initialize();
	}

	private void initialize() {
		Menu.frmMenu.hide();
		
		NumberFormatter formatter = new NumberFormatter(NumberFormat.getInstance());
	    formatter.setValueClass(Integer.class);
	    formatter.setMinimum(0);
	    formatter.setMaximum(Integer.MAX_VALUE);
	    formatter.setAllowsInvalid(false);
	    formatter.setCommitsOnValidEdit(true);
		
		frmSelecaoPersonagem = new JFrame();
		frmSelecaoPersonagem.setTitle(Menu.logon);
		frmSelecaoPersonagem.setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Users\\note\\Documents\\jpa\\jpa\\src\\main\\resources\\icon\\icon.png"));
		frmSelecaoPersonagem.setBounds(100, 100, 450, 255);
		frmSelecaoPersonagem.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frmSelecaoPersonagem.getContentPane().setLayout(null);
		
		cbxPersonagens = new JComboBox();
		cbxPersonagens.setFont(new Font("Tahoma", Font.PLAIN, 20));
		cbxPersonagens.setBounds(152, 14, 272, 24);
		List<Personage> listPersonage = Conexao.manager.em.createNamedQuery(RelacaoPlayerPersonage.OBTER_PERSONAGE_POR_ID_PLAYER, Personage.class)
				.setParameter("id", Conexao.manager.ObterPlayer().getId())
				.getResultList();
		for (Personage personage : listPersonage) {
			cbxPersonagens.addItem(personage.getNome());
		}
		frmSelecaoPersonagem.getContentPane().add(cbxPersonagens);
		
		JLabel lblPersonagens = new JLabel("PERSONAGENS");
		lblPersonagens.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblPersonagens.setBounds(10, 14, 132, 20);
		frmSelecaoPersonagem.getContentPane().add(lblPersonagens);
		
		JLabel lblFor = new JLabel("FOR");
		lblFor.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblFor.setBounds(152, 49, 36, 24);
		frmSelecaoPersonagem.getContentPane().add(lblFor);
		
		JLabel lblVit = new JLabel("VIT");
		lblVit.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblVit.setBounds(152, 84, 36, 24);
		frmSelecaoPersonagem.getContentPane().add(lblVit);
		
		JLabel lblDes = new JLabel("DES");
		lblDes.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblDes.setBounds(152, 119, 36, 24);
		frmSelecaoPersonagem.getContentPane().add(lblDes);
		
		JLabel lblInt = new JLabel("INT");
		lblInt.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblInt.setBounds(294, 49, 36, 24);
		frmSelecaoPersonagem.getContentPane().add(lblInt);
		
		JLabel lblCar = new JLabel("CAR");
		lblCar.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblCar.setBounds(294, 84, 36, 24);
		frmSelecaoPersonagem.getContentPane().add(lblCar);
		
		JLabel lblVig = new JLabel("VIG");
		lblVig.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblVig.setBounds(294, 119, 36, 24);
		frmSelecaoPersonagem.getContentPane().add(lblVig);
		
		JLabel lblRaca = new JLabel("RA\u00C7A");
		lblRaca.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblRaca.setBounds(152, 154, 67, 20);
		frmSelecaoPersonagem.getContentPane().add(lblRaca);
		
		JLabel lblClasse = new JLabel("CLASSE");
		lblClasse.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblClasse.setBounds(152, 188, 67, 20);
		frmSelecaoPersonagem.getContentPane().add(lblClasse);
		
		JButton btnCriar = new JButton("NOVO");
		btnCriar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg) {
				new CriarPersonagem().OpenCriarPersonagem();
			}
		});
		btnCriar.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnCriar.setBounds(10, 45, 132, 55);
		frmSelecaoPersonagem.getContentPane().add(btnCriar);
		
		JButton btnRecarregar = new JButton("RECARREGAR");
		btnRecarregar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnRecarregar.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnRecarregar.setBounds(10, 109, 132, 34);
		frmSelecaoPersonagem.getContentPane().add(btnRecarregar);
		
		JButton btnSalvar = new JButton("SALVAR");
		btnSalvar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Salvar();
			}
		});
		btnSalvar.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnSalvar.setBounds(10, 154, 132, 55);
		frmSelecaoPersonagem.getContentPane().add(btnSalvar);
		
		txfFor = new JFormattedTextField();
		txfFor.setFont(new Font("Tahoma", Font.PLAIN, 20));
		txfFor.setBounds(198, 49, 86, 24);
		frmSelecaoPersonagem.getContentPane().add(txfFor);
		txfFor.setColumns(10);
		
		txfInt = new JFormattedTextField();
		txfInt.setFont(new Font("Tahoma", Font.PLAIN, 20));
		txfInt.setColumns(10);
		txfInt.setBounds(338, 49, 86, 24);
		frmSelecaoPersonagem.getContentPane().add(txfInt);
		
		txfVit = new JFormattedTextField();
		txfVit.setFont(new Font("Tahoma", Font.PLAIN, 20));
		txfVit.setColumns(10);
		txfVit.setBounds(198, 84, 86, 24);
		frmSelecaoPersonagem.getContentPane().add(txfVit);
		
		txfCar = new JFormattedTextField();
		txfCar.setFont(new Font("Tahoma", Font.PLAIN, 20));
		txfCar.setColumns(10);
		txfCar.setBounds(338, 84, 86, 24);
		frmSelecaoPersonagem.getContentPane().add(txfCar);
		
		txfDes = new JFormattedTextField();
		txfDes.setFont(new Font("Tahoma", Font.PLAIN, 20));
		txfDes.setColumns(10);
		txfDes.setBounds(198, 119, 86, 24);
		frmSelecaoPersonagem.getContentPane().add(txfDes);
		
		txfVig = new JFormattedTextField();
		txfVig.setFont(new Font("Tahoma", Font.PLAIN, 20));
		txfVig.setColumns(10);
		txfVig.setBounds(338, 119, 86, 24);
		frmSelecaoPersonagem.getContentPane().add(txfVig);
		
		txfRaca = new JTextField();
		txfRaca.setEditable(false);
		txfRaca.setFont(new Font("Tahoma", Font.PLAIN, 20));
		txfRaca.setBounds(229, 154, 195, 24);
		frmSelecaoPersonagem.getContentPane().add(txfRaca);
		txfRaca.setColumns(10);
		
		txfClasse = new JTextField();
		txfClasse.setEditable(false);
		txfClasse.setFont(new Font("Tahoma", Font.PLAIN, 20));
		txfClasse.setColumns(10);
		txfClasse.setBounds(229, 185, 195, 24);
		frmSelecaoPersonagem.getContentPane().add(txfClasse);
		
		if (listPersonage.size() != 0) {
			Personage personage = listPersonage.get(0);
			txfFor.setText(String.valueOf(personage.getForca()));
			txfInt.setText(String.valueOf(personage.getInteligencia()));
			txfVit.setText(String.valueOf(personage.getVitalidade()));
			txfCar.setText(String.valueOf(personage.getCarisma()));
			txfDes.setText(String.valueOf(personage.getDestreza()));
			txfVig.setText(String.valueOf(personage.getVigor()));
			txfRaca.setText(personage.getRaca().getNome());
			txfClasse.setText(personage.getClasse().getNome());
		}
	}

	private void close() {
		Menu.frmMenu.show();
		Menu.logon = null;
		frmSelecaoPersonagem.dispose();
	}
	
	private void Salvar() {
		
	}
}
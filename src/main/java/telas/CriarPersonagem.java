package telas;

import java.awt.EventQueue;
import javax.swing.JFrame;
import java.awt.Toolkit;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.text.NumberFormatter;
import entidades.Classe;
import entidades.Personage;
import entidades.Player;
import entidades.Raca;
import entidades.RelacaoPlayerPersonage;
import manager.Conexao;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class CriarPersonagem {

	private JFrame frameCriarPersonagem;
	private JFormattedTextField txtFor;
	private JFormattedTextField txtVit;
	private JFormattedTextField txtDes;
	private JFormattedTextField txtInt;
	private JFormattedTextField txtCar;
	private JFormattedTextField txtVig;
	private JTextField txtNome;
	private JComboBox cmbRaca;
	private JComboBox cmbClasse;

	public void OpenCriarPersonagem() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CriarPersonagem window = new CriarPersonagem();
					window.frameCriarPersonagem.addWindowListener(new WindowAdapter() {
			            @Override
			            public void windowClosing(WindowEvent e) {
			            	close();
			            }
			        });
					window.frameCriarPersonagem.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public CriarPersonagem() {
		initialize();
	}

	private void initialize() {
		SelecaoPersonagem.frmSelecaoPersonagem.hide();

	    NumberFormatter formatter = new NumberFormatter(NumberFormat.getInstance());
	    formatter.setValueClass(Integer.class);
	    formatter.setMinimum(0);
	    formatter.setMaximum(Integer.MAX_VALUE);
	    formatter.setAllowsInvalid(false);
	    formatter.setCommitsOnValidEdit(true);
		
		frameCriarPersonagem = new JFrame();
		frameCriarPersonagem.setTitle(Menu.logon + ": Criação de personagem");
		frameCriarPersonagem.setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Users\\note\\Documents\\jpa\\jpa\\src\\main\\resources\\icon\\icon.png"));
		frameCriarPersonagem.setBounds(100, 100, 450, 266);
		frameCriarPersonagem.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frameCriarPersonagem.getContentPane().setLayout(null);
		
		JLabel lblNome = new JLabel("Nome");
		lblNome.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNome.setBounds(10, 11, 52, 25);
		frameCriarPersonagem.getContentPane().add(lblNome);
		
		JLabel lblFor = new JLabel("FOR");
		lblFor.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblFor.setBounds(10, 47, 36, 24);
		frameCriarPersonagem.getContentPane().add(lblFor);
		
		JLabel lblInt = new JLabel("INT");
		lblInt.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblInt.setBounds(152, 47, 36, 24);
		frameCriarPersonagem.getContentPane().add(lblInt);
		
		JLabel lblVit = new JLabel("VIT");
		lblVit.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblVit.setBounds(291, 47, 36, 24);
		frameCriarPersonagem.getContentPane().add(lblVit);
		
		JLabel lblCar = new JLabel("CAR");
		lblCar.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblCar.setBounds(10, 82, 36, 24);
		frameCriarPersonagem.getContentPane().add(lblCar);
		
		JLabel lblDes = new JLabel("DES");
		lblDes.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblDes.setBounds(152, 82, 36, 24);
		frameCriarPersonagem.getContentPane().add(lblDes);
		
		JLabel lblVig = new JLabel("VIG");
		lblVig.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblVig.setBounds(294, 82, 36, 24);
		frameCriarPersonagem.getContentPane().add(lblVig);
		
		JLabel lblRaca = new JLabel("RA\u00C7A");
		lblRaca.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblRaca.setBounds(10, 117, 67, 20);
		frameCriarPersonagem.getContentPane().add(lblRaca);
		
		JLabel lblClasse = new JLabel("CLASSE");
		lblClasse.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblClasse.setBounds(10, 148, 67, 20);
		frameCriarPersonagem.getContentPane().add(lblClasse);
		
		JButton button = new JButton("SALVAR");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Salvar();
			}
		});
		button.setFont(new Font("Tahoma", Font.PLAIN, 24));
		button.setBounds(10, 179, 413, 36);
		frameCriarPersonagem.getContentPane().add(button);
		
		txtFor = new JFormattedTextField(formatter);
		txtFor.setFont(new Font("Tahoma", Font.PLAIN, 20));
		txtFor.setBounds(59, 47, 83, 26);
		frameCriarPersonagem.getContentPane().add(txtFor);
		txtFor.setColumns(10);
		txtFor.setText("0");
		
		txtVit = new JFormattedTextField(formatter);
		txtVit.setFont(new Font("Tahoma", Font.PLAIN, 20));
		txtVit.setColumns(10);
		txtVit.setBounds(340, 47, 83, 26);
		frameCriarPersonagem.getContentPane().add(txtVit);
		txtVit.setText("0");
		
		txtDes = new JFormattedTextField(formatter);
		txtDes.setFont(new Font("Tahoma", Font.PLAIN, 20));
		txtDes.setColumns(10);
		txtDes.setBounds(201, 82, 83, 26);
		frameCriarPersonagem.getContentPane().add(txtDes);
		txtDes.setText("0");
		
		txtInt = new JFormattedTextField(formatter);
		txtInt.setFont(new Font("Tahoma", Font.PLAIN, 20));
		txtInt.setColumns(10);
		txtInt.setBounds(198, 47, 83, 26);
		frameCriarPersonagem.getContentPane().add(txtInt);
		txtInt.setText("0");
		
		txtCar = new JFormattedTextField(formatter);
		txtCar.setFont(new Font("Tahoma", Font.PLAIN, 20));
		txtCar.setColumns(10);
		txtCar.setBounds(56, 82, 83, 26);
		frameCriarPersonagem.getContentPane().add(txtCar);
		txtCar.setText("0");
		
		txtVig = new JFormattedTextField(formatter);
		txtVig.setFont(new Font("Tahoma", Font.PLAIN, 20));
		txtVig.setColumns(10);
		txtVig.setBounds(340, 82, 83, 26);
		frameCriarPersonagem.getContentPane().add(txtVig);
		txtVig.setText("0");
		
		txtNome = new JTextField();
		txtNome.setFont(new Font("Tahoma", Font.PLAIN, 20));
		txtNome.setColumns(10);
		txtNome.setBounds(70, 11, 353, 26);
		frameCriarPersonagem.getContentPane().add(txtNome);
		
		cmbRaca = new JComboBox();
		cmbRaca.setFont(new Font("Tahoma", Font.PLAIN, 20));
		cmbRaca.setBounds(87, 117, 336, 24);
		List<Raca> listaRaca = new ArrayList<Raca>();
		listaRaca = Conexao.manager.em.createNamedQuery(Raca.OBTER_TODOS, Raca.class).getResultList();
		for (Raca raca : listaRaca) {
			cmbRaca.addItem(raca.getNome());
		}
		frameCriarPersonagem.getContentPane().add(cmbRaca);
		
		cmbClasse = new JComboBox();
		cmbClasse.setFont(new Font("Tahoma", Font.PLAIN, 20));
		cmbClasse.setBounds(87, 148, 336, 24);
		List<Classe> listaClasse = Conexao.manager.em.createNamedQuery(Classe.OBTER_TODOS, Classe.class).getResultList();
		for (Classe classe : listaClasse) {
			cmbClasse.addItem(classe.getNome());
		}
		frameCriarPersonagem.getContentPane().add(cmbClasse);
	}
	
	private void close() {
		SelecaoPersonagem.frmSelecaoPersonagem.show();
		frameCriarPersonagem.dispose();
	}
	
	private void Salvar() {
		if (txtNome.getText().length() == 0) {
			JOptionPane.showMessageDialog(null, "Nome obrigatório.");
			return;
		}
		
		if (cmbRaca.getSelectedItem() == null) {
			JOptionPane.showMessageDialog(null, "Cadastre uma raça nas opções.");
			return;
		}
		
		if (cmbClasse.getSelectedItem() == null) {
			JOptionPane.showMessageDialog(null, "Cadastre uma classe nas opções.");
			return;
		}
		
		Player player = Conexao.manager.ObterPlayer();
		
		if (Conexao.manager.em.createNamedQuery(RelacaoPlayerPersonage.OBTER_POR_ID_PLAYER_E_NOME_PERSONAGE)
			.setParameter("idPlayer", player.getId())
			.setParameter("nomePersonage", txtNome.getText())
			.getResultList().size() == 0) {
			
			Personage personage = new Personage();
			personage.setNome(txtNome.getText());
			personage.setForca(Integer.parseInt(txtFor.getText()));
			personage.setInteligencia(Integer.parseInt(txtInt.getText()));
			personage.setVitalidade(Integer.parseInt(txtVit.getText()));
			personage.setCarisma(Integer.parseInt(txtCar.getText()));
			personage.setDestreza(Integer.parseInt(txtDes.getText()));
			personage.setVigor(Integer.parseInt(txtVig.getText()));
			personage.setRaca(Conexao.manager.em.createNamedQuery(Raca.OBTER_POR_NOME, Raca.class)
					.setParameter("nome", cmbRaca.getSelectedItem().toString())
					.getResultList().get(0));
			personage.setClasse(Conexao.manager.em.createNamedQuery(Classe.OBTER_POR_NOME, Classe.class)
					.setParameter("nome", cmbClasse.getSelectedItem().toString())
					.getResultList().get(0));
			Conexao.manager.Save(personage);
			
			RelacaoPlayerPersonage rel = new RelacaoPlayerPersonage();
			rel.setPlayer(player);
			rel.setPersonage(personage);
			Conexao.manager.Save(rel);
			
			JOptionPane.showMessageDialog(null, "Personagem cadastrado com sucesso.");
			close();
		}
		else {
			JOptionPane.showMessageDialog(null, "Personagem já cadastrado com esse nome.");
		}
	}
}
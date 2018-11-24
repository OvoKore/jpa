package telas;

import java.awt.EventQueue;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.List;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import java.awt.Toolkit;
import javax.swing.JComboBox;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JTextField;
import entidades.Personage;
import entidades.Raca;
import manager.Conexao;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class EditarRaca {

	private JFrame frmEditarRaca;
	private JTextField txfRaca;
	private JComboBox cmbRaca;

	public void OpenEditarRaca() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					EditarRaca window = new EditarRaca();
					window.frmEditarRaca.addWindowListener(new WindowAdapter() {
			            @Override
			            public void windowClosing(WindowEvent e) {
			            	close();
			            }
			        });
					window.frmEditarRaca.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public EditarRaca() {
		initialize();
	}

	private void initialize() {
		Opcao.frmOpcao.hide();
		
		frmEditarRaca = new JFrame();
		frmEditarRaca.setTitle("EDITAR RA\u00C7A");
		frmEditarRaca.setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Users\\note\\Documents\\jpa\\jpa\\src\\main\\resources\\icon\\icon.png"));
		frmEditarRaca.setBounds(100, 100, 440, 170);
		frmEditarRaca.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frmEditarRaca.getContentPane().setLayout(null);
		
		cmbRaca = new JComboBox();
		cmbRaca.setFont(new Font("Tahoma", Font.PLAIN, 25));
		cmbRaca.setBounds(10, 11, 265, 30);
		List<Raca> listaRaca = Conexao.manager.em.createNamedQuery(Raca.OBTER_TODOS, Raca.class).getResultList();
		for (Raca raca : listaRaca) {
			cmbRaca.addItem(raca.getNome());
		}
		frmEditarRaca.getContentPane().add(cmbRaca);
		
		JButton btnExcluir = new JButton("EXCLUIR");
		btnExcluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Excluir();
			}
		});
		btnExcluir.setFont(new Font("Tahoma", Font.PLAIN, 25));
		btnExcluir.setBounds(285, 11, 132, 30);
		frmEditarRaca.getContentPane().add(btnExcluir);
		
		txfRaca = new JTextField();
		txfRaca.setFont(new Font("Tahoma", Font.PLAIN, 25));
		txfRaca.setColumns(10);
		txfRaca.setBounds(10, 52, 265, 30);
		frmEditarRaca.getContentPane().add(txfRaca);
		
		JButton btnEditar = new JButton("EDITAR");
		btnEditar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Editar();
			}
		});
		btnEditar.setFont(new Font("Tahoma", Font.PLAIN, 25));
		btnEditar.setBounds(285, 52, 132, 30);
		frmEditarRaca.getContentPane().add(btnEditar);
		
		JButton btnNovaRaca = new JButton("NOVA RA\u00C7A");
		btnNovaRaca.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Adicionar();
			}
		});
		btnNovaRaca.setFont(new Font("Tahoma", Font.PLAIN, 25));
		btnNovaRaca.setBounds(10, 93, 407, 30);
		frmEditarRaca.getContentPane().add(btnNovaRaca);
	}
	
	private void close() {
		Opcao.frmOpcao.show();
		frmEditarRaca.dispose();
	}
	
	private void Adicionar() {
		if (txfRaca.getText().length() == 0) {
			return;
		}
		
		if (Conexao.manager.em.createNamedQuery(Raca.OBTER_POR_NOME, Raca.class)
			.setParameter("nome", txfRaca.getText())
			.getResultList().size() == 0 ) {
				Raca raca = new Raca();
				raca.setNome(txfRaca.getText());
				Conexao.manager.Save(raca);
				reloadComboBox();
				JOptionPane.showMessageDialog(null, "Raça salva com sucesso.");
		}
		else {
			JOptionPane.showMessageDialog(null, "Raça já existe.");
			return;
		}
	}
	
	private void Editar() {
		if (txfRaca.getText().length() != 0 && cmbRaca.getSelectedItem() != null) {
			Raca raca = 
					Conexao.manager.em.createNamedQuery(Raca.OBTER_POR_NOME, Raca.class)
					.setParameter("nome", cmbRaca.getSelectedItem().toString())
					.getResultList().get(0);
			raca.setNome(txfRaca.getText().toString());
			Conexao.manager.Update(raca);
			reloadComboBox();
			JOptionPane.showMessageDialog(null, "Raça editada com sucesso.");
		}
	}
	
	private void Excluir() {
		if (cmbRaca.getSelectedItem() != null) {
			int dialogResult = JOptionPane.showConfirmDialog(null, "Deseja excluir o campo?");
			if(dialogResult == JOptionPane.YES_OPTION) {
				if(Conexao.manager.em.createNamedQuery(Personage.OBTER_POR_RACA_NOME, Personage.class)
				.setParameter("raca", cmbRaca.getSelectedItem().toString())
				.getResultList().size() == 0) {
					Raca raca = 
						Conexao.manager.em.createNamedQuery(Raca.OBTER_POR_NOME, Raca.class)
						.setParameter("nome", cmbRaca.getSelectedItem().toString())
						.getResultList().get(0);
					Conexao.manager.Delete(raca);
					reloadComboBox();
					JOptionPane.showMessageDialog(null, "Raça deletada com sucesso.");
				}
				else {
					JOptionPane.showMessageDialog(null, "Algum personagem possui esta raça, com isso ela não pode ser deletada.");
				}
			}			
		}
	}
	
	private void reloadComboBox() {
		cmbRaca.removeAllItems();
		List<Raca> listaRaca = Conexao.manager.em.createNamedQuery(Raca.OBTER_TODOS, Raca.class).getResultList();
		for (Raca raca : listaRaca) {
			cmbRaca.addItem(raca.getNome());
		}
		txfRaca.setText("");
	}
}
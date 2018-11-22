package telas;

import java.awt.EventQueue;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.List;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import java.awt.Toolkit;
import java.awt.Font;
import javax.swing.JTextField;
import entidades.Classe;
import entidades.Personage;
import manager.Conexao;
import javax.swing.JButton;
import javax.swing.JComboBox;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class EditarClasse {

	private JFrame frmEditarClasse;
	private JTextField txfClasse;
	private JComboBox cmbClasse;

	public void OpenEditarClasse() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					EditarClasse window = new EditarClasse();
					window.frmEditarClasse.addWindowListener(new WindowAdapter() {
			            @Override
			            public void windowClosing(WindowEvent e) {
			            	close();
			            }
			        });
					window.frmEditarClasse.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public EditarClasse() {
		initialize();
	}

	private void initialize() {
		Opcao.frmOpcao.hide();
		
		frmEditarClasse = new JFrame();
		frmEditarClasse.setTitle("EDITAR CLASSE");
		frmEditarClasse.setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Users\\note\\Documents\\jpa\\jpa\\src\\main\\resources\\icon\\icon.png"));
		frmEditarClasse.setBounds(100, 100, 440, 170);
		frmEditarClasse.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frmEditarClasse.getContentPane().setLayout(null);
		
		txfClasse = new JTextField();
		txfClasse.setFont(new Font("Tahoma", Font.PLAIN, 25));
		txfClasse.setBounds(10, 52, 265, 30);
		txfClasse.setColumns(10);
		frmEditarClasse.getContentPane().add(txfClasse);
		
		JButton btnNovaRaca = new JButton("NOVA CLASSE");
		btnNovaRaca.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Adicionar();
			}
		});
		btnNovaRaca.setFont(new Font("Tahoma", Font.PLAIN, 25));
		btnNovaRaca.setBounds(10, 93, 407, 30);
		frmEditarClasse.getContentPane().add(btnNovaRaca);
		
		JButton btnEditar = new JButton("EDITAR");
		btnEditar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Editar();
			}
		});
		btnEditar.setFont(new Font("Tahoma", Font.PLAIN, 25));
		btnEditar.setBounds(285, 52, 132, 30);
		frmEditarClasse.getContentPane().add(btnEditar);
		
		cmbClasse = new JComboBox();
		cmbClasse.setFont(new Font("Tahoma", Font.PLAIN, 25));
		cmbClasse.setBounds(10, 11, 265, 30);
		List<Classe> listaClasse = Conexao.manager.em.createNamedQuery(Classe.OBTER_TODOS, Classe.class).getResultList();
		for (Classe classe : listaClasse) {
			cmbClasse.addItem(classe.getNome());
		}
		frmEditarClasse.getContentPane().add(cmbClasse);
		
		JButton btnExcluir = new JButton("EXCLUIR");
		btnExcluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Excluir();
			}
		});
		btnExcluir.setFont(new Font("Tahoma", Font.PLAIN, 25));
		btnExcluir.setBounds(285, 11, 132, 30);
		frmEditarClasse.getContentPane().add(btnExcluir);
	}
	
	private void close() {
		Opcao.frmOpcao.show();
		frmEditarClasse.dispose();
	}
	
	private void Adicionar() {
		if (txfClasse.getText().length() == 0) {
			return;
		}
		
		if (Conexao.manager.em.createNamedQuery(Classe.OBTER_POR_NOME, Classe.class)
			.setParameter("nome", txfClasse.getText())
			.getResultList().size() == 0 ) {
				Classe classe = new Classe();
				classe.setNome(txfClasse.getText());
				Conexao.manager.Save(classe);
				reloadComboBox();
				JOptionPane.showMessageDialog(null, "Classe salva com sucesso.");
		}
		else {
			JOptionPane.showMessageDialog(null, "Classe já existe.");
			return;
		}
	}
	
	private void Editar() {
		if (txfClasse.getText().length() != 0 && cmbClasse.getSelectedItem() != null) {
			Classe classe = 
					Conexao.manager.em.createNamedQuery(Classe.OBTER_POR_NOME, Classe.class)
					.setParameter("nome", cmbClasse.getSelectedItem().toString())
					.getResultList().get(0);
			classe.setNome(txfClasse.getText().toString());
			Conexao.manager.Update(classe);
			reloadComboBox();
			JOptionPane.showMessageDialog(null, "Classe editada com sucesso.");
		}
	}
	
	private void Excluir() {
		if (cmbClasse.getSelectedItem() != null) {
			int dialogResult = JOptionPane.showConfirmDialog(null, "Deseja excluir o campo?");
			if(dialogResult == JOptionPane.YES_OPTION) {
				if(Conexao.manager.em.createNamedQuery(Personage.OBTER_POR_CLASSE_NOME, Personage.class)
				.setParameter("classe", cmbClasse.getSelectedItem().toString())
				.getResultList().size() == 0) {
					Classe classe = 
						Conexao.manager.em.createNamedQuery(Classe.OBTER_POR_NOME, Classe.class)
						.setParameter("nome", cmbClasse.getSelectedItem().toString())
						.getResultList().get(0);
					Conexao.manager.Delete(classe);
					reloadComboBox();
					JOptionPane.showMessageDialog(null, "Classe deletada com sucesso.");
				}
				else {
					JOptionPane.showMessageDialog(null, "Algum personagem possui esta classe, com isso ela não pode ser deletada.");
				}
			}			
		}
	}
	
	private void reloadComboBox() {
		cmbClasse.removeAllItems();
		List<Classe> listaClasse = Conexao.manager.em.createNamedQuery(Classe.OBTER_TODOS, Classe.class).getResultList();
		for (Classe classe : listaClasse) {
			cmbClasse.addItem(classe.getNome());
		}
		txfClasse.setText("");
	}
}
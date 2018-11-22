package entidades;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@NamedQueries({
	@NamedQuery(name = Classe.OBTER_TODOS,
		query = "SELECT cla FROM Classe cla"),
	@NamedQuery(name = Classe.OBTER_POR_ID,
		query = "SELECT cla FROM Classe cla "
				+ "WHERE cla.id = :id"),
	@NamedQuery(name = Classe.OBTER_POR_NOME,
		query = "SELECT cla FROM Classe cla "
				+ "WHERE cla.nome LIKE :nome")
})
@Entity
@Table(name = "CLASSE")
public class Classe {
	
	public static final String OBTER_TODOS = "Classe.obterTodos";
	public static final String OBTER_POR_ID = "Classe.obterPorId";
	public static final String OBTER_POR_NOME = "Classe.obterPorNome";
	
	@Id
	@GeneratedValue(generator="CLASSE_SEQ", strategy=GenerationType.SEQUENCE)
	@SequenceGenerator(name="CLASSE_SEQ", sequenceName="CLASSE_SEQ", initialValue=1, allocationSize=1)
	@Column(name = "ID", nullable = false, length = 20)
	private long id;
	
	@Column(name = "NOME", nullable = false, length = 50)
	private String nome;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
}
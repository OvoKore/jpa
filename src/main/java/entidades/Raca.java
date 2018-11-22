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
	@NamedQuery(name = Raca.OBTER_TODOS,
		query = "SELECT rac FROM Raca rac"),
	@NamedQuery(name = Raca.OBTER_POR_ID,
		query = "SELECT rac FROM Raca rac "
				+ "WHERE rac.id = :id"),
	@NamedQuery(name = Raca.OBTER_POR_NOME,
		query = "SELECT rac FROM Raca rac "
				+ "WHERE rac.nome LIKE :nome")
})
@Entity
@Table(name = "RACA")
public class Raca {
	
	public static final String OBTER_TODOS = "Raca.obterTodos";
	public static final String OBTER_POR_ID = "Raca.obterPorId";
	public static final String OBTER_POR_NOME = "Raca.obterPorNome";
	
	@Id
	@GeneratedValue(generator="RACA_SEQ", strategy=GenerationType.SEQUENCE)
	@SequenceGenerator(name="RACA_SEQ", sequenceName="RACA_SEQ", initialValue=1, allocationSize=1)
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
package entidades;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@NamedQueries({
	@NamedQuery(name = Personage.OBTER_POR_TODOS,
			query = "SELECT prs FROM Personage prs"),
	@NamedQuery(name = Personage.OBTER_POR_ID,
		query = "SELECT prs FROM Personage prs "
				+ "WHERE prs.id = :id"),
	@NamedQuery(name = Personage.OBTER_POR_NOME,
		query = "SELECT prs FROM Personage prs "
			+ "WHERE prs.nome LIKE :nome"),
	@NamedQuery(name = Personage.OBTER_POR_CLASSE_NOME,
	query = "SELECT prs FROM Personage prs "
		+ "WHERE prs.classe.nome LIKE :classe"),
	@NamedQuery(name = Personage.OBTER_POR_RACA_NOME,
	query = "SELECT prs FROM Personage prs "
		+ "WHERE prs.raca.nome LIKE :raca")
})
@Entity
@Table(name = "PERSONAGE")
public class Personage {
	
	public static final String OBTER_POR_TODOS = "Personage.obterTodos";
	public static final String OBTER_POR_ID = "Personage.obterPorId";
	public static final String OBTER_POR_NOME = "Personage.obterPorNome";
	public static final String OBTER_POR_CLASSE_NOME = "Personage.obterPorClasseNome";
	public static final String OBTER_POR_RACA_NOME = "Personage.obterPorRacaNome";
	
	@Id
	@GeneratedValue(generator="PERSONAGE_SEQ", strategy=GenerationType.SEQUENCE)
	@SequenceGenerator(name="PERSONAGE_SEQ", sequenceName="PERSONAGE_SEQ", initialValue=1, allocationSize=1)
	@Column(name = "ID", nullable = false, length = 20)
	private long id;
	
	@Column(name = "NOME", nullable = false, length = 50)
	private String nome;
	
	@Column(name = "FORCA", nullable = false, length = 3)
	private int forca;

	@Column(name = "VITALIDADE", nullable = false, length = 3)
	private int vitalidade;
	
	@Column(name = "DESTREZA", nullable = false, length = 3)
	private int destreza;
	
	@Column(name = "INTELIGENCIA", nullable = false, length = 3)
	private int inteligencia;
	
	@Column(name = "CARISMA", nullable = false, length = 3)
	private int carisma;
	
	@Column(name = "VIGOR", nullable = false, length = 3)
	private int vigor;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="ID_CLASSE", nullable = false)
	private Classe classe;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="ID_RACA", nullable = false)
	private Raca raca;

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

	public int getForca() {
		return forca;
	}

	public void setForca(int forca) {
		this.forca = forca;
	}

	public int getVitalidade() {
		return vitalidade;
	}

	public void setVitalidade(int vitalidade) {
		this.vitalidade = vitalidade;
	}

	public int getDestreza() {
		return destreza;
	}

	public void setDestreza(int destreza) {
		this.destreza = destreza;
	}

	public int getInteligencia() {
		return inteligencia;
	}

	public void setInteligencia(int inteligencia) {
		this.inteligencia = inteligencia;
	}

	public int getCarisma() {
		return carisma;
	}

	public void setCarisma(int carisma) {
		this.carisma = carisma;
	}

	public int getVigor() {
		return vigor;
	}

	public void setVigor(int vigor) {
		this.vigor = vigor;
	}

	public Classe getClasse() {
		return classe;
	}

	public void setClasse(Classe classe) {
		this.classe = classe;
	}

	public Raca getRaca() {
		return raca;
	}

	public void setRaca(Raca raca) {
		this.raca = raca;
	}
}
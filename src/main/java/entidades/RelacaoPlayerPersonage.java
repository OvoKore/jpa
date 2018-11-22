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
	@NamedQuery(name = RelacaoPlayerPersonage.OBTER_TODOS,
		query = "SELECT rpp FROM RelacaoPlayerPersonage rpp"),
	@NamedQuery(name = RelacaoPlayerPersonage.OBTER_POR_ID,
		query = "SELECT rpp FROM RelacaoPlayerPersonage rpp "
				+ "WHERE rpp.id = :id"),
	@NamedQuery(name = RelacaoPlayerPersonage.OBTER_POR_ID_PLAYER,
		query = "SELECT rpp FROM RelacaoPlayerPersonage rpp "
				+ "WHERE rpp.player.id = :id"),
	@NamedQuery(name = RelacaoPlayerPersonage.OBTER_POR_ID_PERSONAGE,
		query = "SELECT rpp FROM RelacaoPlayerPersonage rpp "
				+ "WHERE rpp.personage.id = :id"),
	@NamedQuery(name = RelacaoPlayerPersonage.OBTER_POR_ID_PLAYER_E_ID_PERSONAGE,
		query = "SELECT rpp FROM RelacaoPlayerPersonage rpp "
			+ "WHERE rpp.player.id = :idPlayer "
			+ "AND rpp.personage.id = :idPersonage"),
	@NamedQuery(name = RelacaoPlayerPersonage.OBTER_POR_ID_PLAYER_E_NOME_PERSONAGE,
		query = "SELECT rpp FROM RelacaoPlayerPersonage rpp "
			+ "WHERE rpp.player.id = :idPlayer "
			+ "AND rpp.personage.nome LIKE :nomePersonage"),
	@NamedQuery(name = RelacaoPlayerPersonage.OBTER_PERSONAGE_POR_ID_PLAYER,
	query = "SELECT rpp.personage FROM RelacaoPlayerPersonage rpp "
			+ "WHERE rpp.player.id = :id"),
})
@Entity
@Table(name = "REL_PLAYER_PERSONAGE")
public class RelacaoPlayerPersonage {
	
	public static final String OBTER_TODOS = "RelacaoPlayerPersonage.obterTodos";
	public static final String OBTER_POR_ID = "RelacaoPlayerPersonage.obterPorId";
	public static final String OBTER_POR_ID_PLAYER = "RelacaoPlayerPersonage.obterPorIdPlayer";
	public static final String OBTER_POR_ID_PERSONAGE = "RelacaoPlayerPersonage.obterPorIdPersonage";
	public static final String OBTER_POR_ID_PLAYER_E_ID_PERSONAGE = "RelacaoPlayerPersonage.obterPorIdPlayerEIdPersonage";
	public static final String OBTER_POR_ID_PLAYER_E_NOME_PERSONAGE = "RelacaoPlayerPersonage.obterPorIdPlayerENomePersonage";
	public static final String OBTER_PERSONAGE_POR_ID_PLAYER = "RelacaoPlayerPersonage.obterPersonagePorIdPlayer";
	
	@Id
	@GeneratedValue(generator="REL_PLAYER_PERSONAGE_SEQ", strategy=GenerationType.SEQUENCE)
	@SequenceGenerator(name="REL_PLAYER_PERSONAGE_SEQ", sequenceName="REL_PLAYER_PERSONAGE_SEQ", initialValue=1, allocationSize=1)
	@Column(name = "ID", nullable = false, length = 20)
	private long id;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "PLAYER_ID", nullable = false)
	private Player player;
	
	@ManyToOne(fetch = FetchType.LAZY)	
	@JoinColumn(name = "PERSONAGE_ID", nullable = false)
	private Personage personage;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Player getPlayer() {
		return player;
	}

	public void setPlayer(Player player) {
		this.player = player;
	}

	public Personage getPersonage() {
		return personage;
	}

	public void setPersonage(Personage personage) {
		this.personage = personage;
	}
}
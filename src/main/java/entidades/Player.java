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
	@NamedQuery(name = Player.OBTER_TODOS,
		query = "SELECT ply FROM Player ply"),
	@NamedQuery(name = Player.OBTER_POR_ID,
		query = "SELECT ply FROM Player ply "
				+ "WHERE ply.id = :id"),
	@NamedQuery(name = Player.OBTER_POR_LOGIN,
		query = "SELECT ply FROM Player ply "
				+ "WHERE ply.login LIKE :login"),
	@NamedQuery(name = Player.OBTER_POR_LOGIN_SENHA,
		query = "SELECT ply FROM Player ply "
			+ "WHERE ply.login LIKE :login "
			+ "AND ply.password LIKE :senha")
})
@Entity
@Table(name = "PLAYER")
public class Player {

	public static final String OBTER_TODOS = "Player.obterTodos";
	public static final String OBTER_POR_ID = "Player.obterPorId";
	public static final String OBTER_POR_LOGIN = "Player.obterPorLogin";
	public static final String OBTER_POR_LOGIN_SENHA = "Player.obterPorLoginSenha";
	
	@Id
	@GeneratedValue(generator="PLAYER_SEQ", strategy=GenerationType.SEQUENCE)
	@SequenceGenerator(name="PLAYER_SEQ", sequenceName="PLAYER_SEQ", initialValue=1, allocationSize=1)
	@Column(name = "ID", nullable = false, length = 20)
	private long id;
	
	@Column(name = "LOGIN", nullable = false, length = 20)
	private String login;
	
	@Column(name = "PASSWORD", nullable = false, length = 20)
	private String password;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}
package entidades;

import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "ITEM")
public class Item {
	
	@Id
	@GeneratedValue(generator="ITEM_SEQ", strategy=GenerationType.SEQUENCE)
	@SequenceGenerator(name="ITEM_SEQ", sequenceName="ITEM_SEQ", initialValue=1, allocationSize=1)
	@Column(name = "ID", nullable = false, length = 20)
	private long id;
	
	@Column(name = "NOME", nullable = false, length = 50)
	private String nome;
	
	@ManyToMany
	@JoinTable(name = "personagens")
	private List<Personage> personagens;
}

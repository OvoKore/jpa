package manager;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import entidades.Player;
import telas.Menu;

public class Manager {
	private EntityManagerFactory cemf;
	public EntityManager em;
	private EntityTransaction tx;
	
	public Manager() {
		cemf = Persistence.createEntityManagerFactory("jpa");
		em = cemf.createEntityManager();
		tx = em.getTransaction();
	}
	
	public void Save (Object obj) {
		tx.begin();
		em.persist(obj);
		em.flush();
		tx.commit();
	}
	
	public void Delete (Object obj) {
		tx.begin();
		em.remove(obj);
		em.flush();
		tx.commit();
	}
	
	public void Update (Object obj) {
		tx.begin();
		em.merge(obj);
		em.flush();
		tx.commit();
	}
	
	public Player ObterPlayer() {
		return em.createNamedQuery(Player.OBTER_POR_LOGIN, Player.class)
			.setParameter("login", Menu.logon)
			.getResultList().get(0);
	}
}
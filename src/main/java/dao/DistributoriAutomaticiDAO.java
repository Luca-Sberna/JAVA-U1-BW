package dao;

import java.util.UUID;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import entities.DistributoriAutomatici;

public class DistributoriAutomaticiDAO {
	private final EntityManager em;

	public DistributoriAutomaticiDAO(EntityManager em) {
		this.em = em;
	}

	public void save(DistributoriAutomatici distributoriAutomatici) {
		EntityTransaction transaction = em.getTransaction();
		transaction.begin();
		em.persist(distributoriAutomatici);
		transaction.commit();
	}

	public DistributoriAutomatici getById(UUID uuid) {
		DistributoriAutomatici found = em.find(DistributoriAutomatici.class, uuid);
		if (found != null) {
			System.out.println("Distributore" + " " + uuid + " " + "trovato");
		} else {
			System.out.println("Non abbiamo trovato niente");
		}
		return found;
	}

	public void delete(DistributoriAutomatici distributoriAutomatici) {
		em.getTransaction().begin();
		distributoriAutomatici = em.merge(distributoriAutomatici);
		em.remove(distributoriAutomatici);
		em.getTransaction().commit();
	}

	public void refresh(DistributoriAutomatici distributoriAutomatici) {
		distributoriAutomatici = em.merge(distributoriAutomatici);
		em.refresh(distributoriAutomatici);
	}
}

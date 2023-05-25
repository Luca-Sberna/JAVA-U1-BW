package dao;

import java.util.UUID;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;

import entities.DistributoriAutomatici;
import entities.DistributoriAutomatici.StatoDistributore;

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

	public DistributoriAutomatici getById(String uuid) {
		DistributoriAutomatici found = em.find(DistributoriAutomatici.class, UUID.fromString(uuid));
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

	public int findByIdAndUpdate(String id, StatoDistributore stato) {
		EntityTransaction t = em.getTransaction();
		t.begin();
		Query q = em.createQuery(
				"UPDATE DistributoriAutomatici m SET m.StatoDistributore=:StatoDistributore WHERE id = :id");
		q.setParameter("stato", stato);
		q.setParameter("id", UUID.fromString(id));
		int num = q.executeUpdate();
		t.commit();
		if (num > 0) {
			System.out.println("Distributore modificato correttamente");
		} else {
			System.out.println("non abbiamo modificato nulla");
		}
		return num;
	}

}

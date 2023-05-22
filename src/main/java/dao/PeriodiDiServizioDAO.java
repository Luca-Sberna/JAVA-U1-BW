package dao;

import java.util.UUID;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import entities.PeriodiDiServizio;

public class PeriodiDiServizioDAO {
	private final EntityManager em;

	public PeriodiDiServizioDAO(EntityManager em) {
		this.em = em;
	}

	public void save(PeriodiDiServizio e) {
		EntityTransaction transaction = em.getTransaction();
		transaction.begin();
		em.persist(e);
		transaction.commit();
	}

	public PeriodiDiServizio getById(String id) {
		PeriodiDiServizio found = em.find(PeriodiDiServizio.class, UUID.fromString(id));

		if (found != null) {
			System.out.println("Elemento" + " " + id + " " + "trovato");
		} else {
			System.out.println("Non abbiamo trovato niente");
		}
		return found;

	}
}

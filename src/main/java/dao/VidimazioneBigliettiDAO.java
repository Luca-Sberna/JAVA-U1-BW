package dao;

import java.util.UUID;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import entities.VidimazioneBiglietti;

public class VidimazioneBigliettiDAO {
	private final EntityManager em;

	public VidimazioneBigliettiDAO(EntityManager em) {
		this.em = em;
	}

	public void save(VidimazioneBiglietti e) {
		EntityTransaction transaction = em.getTransaction();
		transaction.begin();
		em.persist(e);
		transaction.commit();
	}

	public VidimazioneBiglietti getById(String id) {
		VidimazioneBiglietti found = em.find(VidimazioneBiglietti.class,
				UUID.fromString(id));

		if (found != null) {
			System.out
					.println("Biglietto vidimato" + " " + id + " " + "trovato");
		} else {
			System.out.println("Non abbiamo trovato niente");
		}
		return found;

	}
}

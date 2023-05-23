package dao;

import java.util.UUID;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import entities.Tessera;

public class TesseraDAO {
	private final EntityManager em;

	public TesseraDAO(EntityManager em) {
		this.em = em;
	}

	public void save(Tessera e) {
		EntityTransaction transaction = em.getTransaction();
		transaction.begin();
		em.persist(e);
		transaction.commit();
	}

	public Tessera getById(String id) {
		Tessera found = em.find(Tessera.class, UUID.fromString(id));

		if (found != null) {
			System.out.println("Tessera" + " " + id + " " + "trovata");
		} else {
			System.out.println("Non abbiamo trovato niente");
		}
		return found;

	}

	public void delete(Tessera tessera) {
		em.getTransaction().begin();
		tessera = em.merge(tessera);
		em.remove(tessera);
		em.getTransaction().commit();
	}

	public void refresh(Tessera tessera) {
		tessera = em.merge(tessera);
		em.refresh(tessera);
	}

}

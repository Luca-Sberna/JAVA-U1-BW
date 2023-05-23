package dao;

import java.util.UUID;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import entities.Utente;

public class UtenteDAO {
	private final EntityManager em;

	public UtenteDAO(EntityManager em) {
		this.em = em;
	}

	public void save(Utente e) {
		EntityTransaction transaction = em.getTransaction();
		transaction.begin();
		em.persist(e);
		transaction.commit();
	}

	public Utente getById(String id) {
		Utente found = em.find(Utente.class, UUID.fromString(id));

		if (found != null) {
			System.out.println("Utente" + " " + id + " " + "trovato");
		} else {
			System.out.println("Non abbiamo trovato niente");
		}
		return found;

	}

	public void delete(Utente utente) {
		em.getTransaction().begin();
		utente = em.merge(utente);
		em.remove(utente);
		em.getTransaction().commit();
	}

	public void refresh(Utente utente) {
		utente = em.merge(utente);
		em.refresh(utente);
	}

}

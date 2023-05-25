package dao;

import java.util.List;
import java.util.UUID;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;

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

	public List<Utente> getAllUsers() {
		TypedQuery<Utente> query = em.createQuery("SELECT u FROM Utente u", Utente.class);
		return query.getResultList();
	}

}

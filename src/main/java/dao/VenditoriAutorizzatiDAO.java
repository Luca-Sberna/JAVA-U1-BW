package dao;

import java.util.List;
import java.util.UUID;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;

import entities.VenditoriAutorizzati;

public class VenditoriAutorizzatiDAO {
	private final EntityManager em;

	public VenditoriAutorizzatiDAO(EntityManager em) {
		this.em = em;
	}

	public void save(VenditoriAutorizzati venditoriAutorizzati) {
		EntityTransaction transaction = em.getTransaction();
		transaction.begin();
		em.persist(venditoriAutorizzati);
		transaction.commit();
	}

	public VenditoriAutorizzati getById(UUID uuid) {
		VenditoriAutorizzati found = em.find(VenditoriAutorizzati.class, uuid);
		if (found != null) {
			System.out.println("Venditore" + " " + uuid + " " + "trovato");
		} else {
			System.out.println("Non abbiamo trovato niente");
		}
		return found;
	}

	public void delete(VenditoriAutorizzati venditoriAutorizzati) {
		em.getTransaction().begin();
		venditoriAutorizzati = em.merge(venditoriAutorizzati);
		em.remove(venditoriAutorizzati);
		em.getTransaction().commit();
	}

	public void refresh(VenditoriAutorizzati venditoriAutorizzati) {
		venditoriAutorizzati = em.merge(venditoriAutorizzati);
		em.refresh(venditoriAutorizzati);
	}

	public List<VenditoriAutorizzati> getAllVenditoriAutorizzati() {
		TypedQuery<VenditoriAutorizzati> query = em.createQuery(
				"SELECT va FROM VenditoriAutorizzati va",
				VenditoriAutorizzati.class);
		return query.getResultList();
	}
}

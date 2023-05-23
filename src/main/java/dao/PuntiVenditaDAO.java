package dao;

import java.util.UUID;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import entities.PuntiVendita;

public class PuntiVenditaDAO {
	private final EntityManager em;

	public PuntiVenditaDAO(EntityManager em) {
		this.em = em;
	}

	public void save(PuntiVendita e) {
		EntityTransaction transaction = em.getTransaction();
		transaction.begin();
		em.persist(e);
		transaction.commit();
	}

	public PuntiVendita getById(String id) {
		PuntiVendita found = em.find(PuntiVendita.class, UUID.fromString(id));

		if (found != null) {
			System.out.println("Punto vendita" + " " + id + " " + "trovato");
		} else {
			System.out.println("Non abbiamo trovato niente");
		}
		return found;

	}
}

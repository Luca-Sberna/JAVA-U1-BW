package dao;

import java.util.List;
import java.util.UUID;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;

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

	public List<PuntiVendita> getAllPuntiVendita() {
		TypedQuery<PuntiVendita> query = em.createQuery("SELECT m FROM PuntiVendita m", PuntiVendita.class);
		return query.getResultList();
	}
}

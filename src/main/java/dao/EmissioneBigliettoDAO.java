package dao;

import java.util.UUID;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import entities.EmissioneBiglietto;

public class EmissioneBigliettoDAO {
	private final EntityManager em;

	public EmissioneBigliettoDAO(EntityManager em) {
		this.em = em;
	}

	public void save(EmissioneBiglietto emissioneBiglietto) {
		EntityTransaction transaction = em.getTransaction();
		transaction.begin();
		em.persist(emissioneBiglietto);
		transaction.commit();
	}

	public EmissioneBiglietto getById(String uuid) {
		EmissioneBiglietto found = em.find(EmissioneBiglietto.class,
				UUID.fromString(uuid));
		if (found != null) {
			System.out.println(
					"Emissione biglietto" + " " + uuid + " " + "trovata");
		} else {
			System.out.println("Non abbiamo trovato nessun emissione");
		}
		return found;
	}

	public void delete(EmissioneBiglietto emissioneBiglietto) {
		em.getTransaction().begin();
		emissioneBiglietto = em.merge(emissioneBiglietto);
		em.remove(emissioneBiglietto);
		em.getTransaction().commit();
	}

	public void refresh(EmissioneBiglietto emissioneBiglietto) {
		emissioneBiglietto = em.merge(emissioneBiglietto);
		em.refresh(emissioneBiglietto);
	}
}

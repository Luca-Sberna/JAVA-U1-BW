package dao;

import java.util.UUID;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;

import entities.Tessera;
import entities.VidimazioneAbbonamenti;

public class VidimazioneAbbonamentiDAO {
	private final EntityManager em;

	public VidimazioneAbbonamentiDAO(EntityManager em) {
		this.em = em;
	}

	public void save(VidimazioneAbbonamenti e) {
		EntityTransaction transaction = em.getTransaction();
		transaction.begin();

		// Aggiorna l'abbonamento vidimato
		em.merge(e.getAbbonamentoVidimato());

		// Verifica e persisti la tessera
		Tessera tessera = e.getTessera();
		if (!em.contains(tessera)) {
			em.merge(tessera);
		} else {
			em.merge(tessera);
		}

		// Persisti la vidimazione dell'abbonamento
		em.merge(e);

		transaction.commit();
	}

	public VidimazioneAbbonamenti getById(String id) {
		VidimazioneAbbonamenti found = em.find(VidimazioneAbbonamenti.class, UUID.fromString(id));

		if (found != null) {
			System.out.println("Abbonamento vidimato" + " " + id + " " + "trovato");
		} else {
			System.out.println("Non abbiamo trovato niente");
		}
		return found;

	}

	public Long getVidimazioneAbbonamento(String numeroTessera) {
		String jpql = "SELECT COUNT(a) FROM Abbonamento a WHERE a.tessera.numero = :numeroTessera AND a.utente IS NOT NULL";
		TypedQuery<Long> query = em.createQuery(jpql, Long.class);
		query.setParameter("numeroTessera", numeroTessera);
		return query.getSingleResult();
	}

}

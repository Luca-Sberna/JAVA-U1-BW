package dao;

import java.util.UUID;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;

import entities.VidimazioneAbbonamenti;

public class VidimazioneAbbonamentiDAO {
	private final EntityManager em;

	public VidimazioneAbbonamentiDAO(EntityManager em) {
		this.em = em;
	}

	public void save(VidimazioneAbbonamenti e) {
		EntityTransaction transaction = em.getTransaction();
		transaction.begin();
		em.persist(e);
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

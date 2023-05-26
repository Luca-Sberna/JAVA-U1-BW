package dao;

import java.time.LocalDate;
import java.util.UUID;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;

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
		VidimazioneBiglietti found = em.find(VidimazioneBiglietti.class, UUID.fromString(id));

		if (found != null) {
			System.out.println("Biglietto vidimato" + " " + id + " " + "trovato");
		} else {
			System.out.println("Non abbiamo trovato niente");
		}
		return found;

	}

	public Long getBigliettiVidimatiPerMezzoInRange(String idMezzo, LocalDate inizioRange, LocalDate fineRange) {
		TypedQuery<Long> q = em.createQuery(
				"SELECT COUNT(v) FROM VidimazioneBiglietti v WHERE v.mezzo.id = :idMezzo AND v.dataVidimazione BETWEEN :inizioRange AND :fineRange",
				Long.class);
		q.setParameter("idMezzo", UUID.fromString(idMezzo));
		q.setParameter("inizioRange", inizioRange);
		q.setParameter("fineRange", fineRange);
		return q.getSingleResult();
	}

}

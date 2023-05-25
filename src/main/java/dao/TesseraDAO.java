package dao;

import java.util.List;
import java.util.UUID;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;

import entities.EmissioneAbbonamento;
import entities.Tessera;

public class TesseraDAO {
	private final EntityManager em;

	public TesseraDAO(EntityManager em) {
		this.em = em;
	}

	public void save(Tessera tessera) {
		EntityTransaction tx = em.getTransaction();
		try {
			tx.begin();
			UUID numeroTessera = generateNumeroTessera();
			tessera.setNumeroTessera(numeroTessera);
			em.merge(tessera);
			tx.commit();
		} catch (Exception e) {
			if (tx != null && tx.isActive()) {
				tx.rollback();
			}
			e.printStackTrace();
		} finally {
			if (tx != null && tx.isActive()) {
				tx.rollback();
			}
		}
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

	private UUID generateNumeroTessera() {
		// Genera un numero di tessera
		return UUID.randomUUID();
	}

	public List<EmissioneAbbonamento> getAbbonamentoAttivo(String nTessera) {
		TypedQuery<EmissioneAbbonamento> q = em.createQuery(
				"SELECT t FROM EmissioneAbbonamento t WHERE t.numeroTessera.numeroTessera = :nTessera AND t.dataEmissione IS NOT NULL AND t.dataScadenza > CURRENT_DATE()",
				EmissioneAbbonamento.class);
		q.setParameter("nTessera", UUID.fromString(nTessera));
		return q.getResultList();
	}

	public List<Tessera> trovaTessereScadutePerUtente(String idUtente) {
		TypedQuery<Tessera> query = em.createQuery(
				"SELECT t FROM Tessera t WHERE t.proprietario.idUtente = :idUtente AND t.dataScadenzaTessera < CURRENT_DATE()",
				Tessera.class);
		query.setParameter("idUtente", UUID.fromString(idUtente));
		return query.getResultList();
	}

}

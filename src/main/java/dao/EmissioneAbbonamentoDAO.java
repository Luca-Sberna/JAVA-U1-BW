package dao;

import java.util.List;
import java.util.UUID;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;

import entities.EmissioneAbbonamento;

public class EmissioneAbbonamentoDAO {
	private final EntityManager em;

	public EmissioneAbbonamentoDAO(EntityManager em) {
		this.em = em;
	}

	public void save(EmissioneAbbonamento emissioneAbbonamento) {
		EntityTransaction transaction = em.getTransaction();
		transaction.begin();
		em.merge(emissioneAbbonamento);
		transaction.commit();

	}

	public EmissioneAbbonamento save2(EmissioneAbbonamento emissioneAbbonamento) {
		EntityTransaction transaction = em.getTransaction();
		transaction.begin();
		EmissioneAbbonamento managedEmissioneAbbonamento = em.merge(emissioneAbbonamento);
		em.flush();
		transaction.commit();
		return managedEmissioneAbbonamento;
	}

	public EmissioneAbbonamento getById(UUID uuid) {
		EmissioneAbbonamento found = em.find(EmissioneAbbonamento.class, uuid);
		if (found != null) {
			System.out.println("Emissione abbonamento" + " " + uuid + " " + "trovata");
		} else {
			System.out.println("Non abbiamo trovato nessun emissione");
		}
		return found;
	}

	public void delete(EmissioneAbbonamento emissioneAbbonamento) {
		em.getTransaction().begin();
		emissioneAbbonamento = em.merge(emissioneAbbonamento);
		em.remove(emissioneAbbonamento);
		em.getTransaction().commit();
	}

	public void refresh(EmissioneAbbonamento emissioneAbbonamento) {
		emissioneAbbonamento = em.merge(emissioneAbbonamento);
		em.refresh(emissioneAbbonamento);
	}

	public List<EmissioneAbbonamento> findAbbonamentiAttiviByNumeroTessera(String tessera) {
		TypedQuery<EmissioneAbbonamento> q = em.createQuery(
				"SELECT a FROM EmissioneAbbonamento a WHERE a.tessera.numeroTessera = :tessera AND CURRENT_DATE() BETWEEN a.dataEmissione AND a.dataScadenza",
				EmissioneAbbonamento.class);
		q.setParameter("tessera", UUID.fromString(tessera));
		return q.getResultList();
	}

}

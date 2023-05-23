package dao;

import java.util.UUID;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import entities.EmissioneAbbonamento;

public class EmissioneAbbonamentoDAO {
	private final EntityManager em;

	public EmissioneAbbonamentoDAO(EntityManager em) {
		this.em = em;
	}

	public void save(EmissioneAbbonamento emissioneAbbonamento) {
		EntityTransaction transaction = em.getTransaction();
		transaction.begin();
		em.persist(emissioneAbbonamento);
		transaction.commit();
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
}

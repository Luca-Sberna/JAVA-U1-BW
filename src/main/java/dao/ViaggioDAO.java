package dao;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import entities.Viaggio;

public class ViaggioDAO {
	private final EntityManager em;

	public ViaggioDAO(EntityManager em) {
		this.em = em;
	}

	public void save(Viaggio e) {
		EntityTransaction transaction = em.getTransaction();
		transaction.begin();
		em.persist(e);
		transaction.commit();
	}

	public Viaggio getById(String id) {
		Viaggio found = em.find(Viaggio.class, UUID.fromString(id));

		if (found != null) {
			System.out.println("Viaggio" + " " + id + " " + "trovato");
		} else {
			System.out.println("Non abbiamo trovato niente");
		}
		return found;

	}

	public void delete(Viaggio viaggio) {
		em.getTransaction().begin();
		viaggio = em.merge(viaggio);
		em.remove(viaggio);
		em.getTransaction().commit();
	}

	public void refresh(Viaggio viaggio) {
		viaggio = em.merge(viaggio);
		em.refresh(viaggio);
	}

	public List<Double> getDurataViaggio() {
		List<Double> durataViaggio = new ArrayList<>();
		try {
			// la funzione TIMESTAMPDIFF di SQL sottrae i campi orarioPartenza e
			// orarioArrivo,calcolo la differenza in secondi e la divide per 60 per ottenere
			// il risultato in minuti (restituendo i risultati in lista).
			String query = "SELECT TIMESTAMPDIFF(SECOND, v.orarioPartenza, v.orarioArrivo) / 60.0 FROM Viaggio v";
			List<?> results = em.createNativeQuery(query).getResultList();
			for (Object result : results) {
				durataViaggio.add(((Number) result).doubleValue());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return durataViaggio;
	}

}

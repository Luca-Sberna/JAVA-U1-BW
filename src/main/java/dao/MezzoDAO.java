package dao;

import java.util.UUID;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import entities.Mezzo;

public class MezzoDAO {
	private final EntityManager em;

	public MezzoDAO(EntityManager em) {
		this.em = em;
	}

	public void save(Mezzo e) {
		EntityTransaction transaction = em.getTransaction();
		transaction.begin();
		em.persist(e);
		transaction.commit();
	}

	public Mezzo getById(String id) {
		Mezzo found = em.find(Mezzo.class, UUID.fromString(id));

		if (found != null) {
			System.out.println("Mezzo" + " " + id + " " + "trovato");
		} else {
			System.out.println("Non abbiamo trovato niente");
		}
		return found;

	}

	public void delete(Mezzo mezzo) {
		em.getTransaction().begin();
		mezzo = em.merge(mezzo);
		em.remove(mezzo);
		em.getTransaction().commit();
	}

	public void refresh(Mezzo mezzo) {
		mezzo = em.merge(mezzo);
		em.refresh(mezzo);
	}

	public double getTotalKilometers() {
		double totalKilometers = 0;
		try {
			String query = "SELECT SUM(t.tempoMedioTratta * m.velocitàMedia) FROM Mezzo m JOIN m.tratta t";
			totalKilometers = (double) em.createQuery(query).getSingleResult();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return totalKilometers;
	}

}

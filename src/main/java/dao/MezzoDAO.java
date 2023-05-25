package dao;

import java.util.List;
import java.util.UUID;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import entities.Mezzo;
import entities.Mezzo.statoMezzo;
import entities.Mezzo.tipoMezzo;
import entities.Tratta;

public class MezzoDAO {
	private final EntityManager em;
	private List<Mezzo> mezzi; // Lista dei mezzi

	public MezzoDAO(EntityManager em) {
		this.em = em;
	}

	public void save(Mezzo e) {
		EntityTransaction transaction = em.getTransaction();
		transaction.begin();
		em.persist(e);
		transaction.commit();
	}

	public Mezzo getById(UUID uuid) {
		Mezzo found = em.find(Mezzo.class, uuid);

		if (found != null) {
			System.out.println("Mezzo" + " " + uuid + " " + "trovato");
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

	public long getNumeroBigliettiVidimati(String id) {
		Query q = em.createQuery(
				"SELECT COUNT(vb) FROM VidimazioneBiglietti vb WHERE vb.mezzo.id = :id AND vb.dataVidimazione IS NOT NULL");
		q.setParameter("id", UUID.fromString(id));
		return (long) q.getSingleResult();
	}

	public Double getTotalKilometers() {
		Double totalKilometers = 0.0;
		try {
			String query = "SELECT SUM(t.lunghezzaTratta) FROM Mezzo m JOIN m.tratta t";
			totalKilometers = (double) em.createQuery(query).getSingleResult();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return totalKilometers;
	}

	public List<Mezzo> getAllMezzi() {
		TypedQuery<Mezzo> query = em.createQuery("SELECT m FROM Mezzo m", Mezzo.class);
		return query.getResultList();
	}

	public int findByIdAndUpdate(UUID id, long capienza, statoMezzo stato, tipoMezzo tipoMezzo, Tratta tratta) {
		EntityTransaction t = em.getTransaction();
		t.begin();
		Query q = em.createQuery(
				"UPDATE Mezzo m SET m.capienza=:capienza, m.stato=:stato,m.tipoMezzo = :tipoMezzo, m.tratta = :tratta WHERE m.id = :id");
		q.setParameter("capienza", capienza);
		q.setParameter("id", id);
		q.setParameter("stato", stato);
		q.setParameter("tipoMezzo", tipoMezzo);
		q.setParameter("tratta", tratta);
		int num = q.executeUpdate();
		t.commit();
		if (num > 0) {
			System.out.println("Mezzo modificato");
		} else {
			System.out.println("non abbiamo modificato nulla");
		}
		return num;
	}

	public void aggiungiMezzo(Mezzo nuovoMezzo) {
		// Salva il nuovo mezzo nel sistema di persistenza dei dati
		em.getTransaction().begin();
		em.persist(nuovoMezzo);
		em.getTransaction().commit();
	}

	public void saveMezzo(Mezzo mezzoDaModificare) {
		em.persist(mezzoDaModificare);
	}

}

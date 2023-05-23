package app;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import dao.DistributoriAutomaticiDAO;
import dao.EmissioneAbbonamentoDAO;
import dao.EmissioneBigliettoDAO;
import dao.MezzoDAO;
import dao.TesseraDAO;
import dao.TrattaDAO;
import dao.UtenteDAO;
import dao.VenditoriAutorizzatiDAO;
import entities.DistributoriAutomatici;
import entities.DistributoriAutomatici.StatoDistributore;
import entities.Mezzo;
import entities.Mezzo.statoMezzo;
import entities.Mezzo.tipoMezzo;
import entities.Tratta;
import entities.Utente;
import entities.VenditoriAutorizzati;
import lombok.extern.slf4j.Slf4j;
import util.JpaUtil;

@Slf4j
public class Main {
	private static EntityManagerFactory emf = JpaUtil.getEntityManagerFactory();

	public static void main(String[] args) {

		EntityManager em = emf.createEntityManager();
		MezzoDAO md = new MezzoDAO(em);
		TrattaDAO td = new TrattaDAO(em);
		EmissioneBigliettoDAO ebd = new EmissioneBigliettoDAO(em);
		EmissioneAbbonamentoDAO ead = new EmissioneAbbonamentoDAO(em);
		DistributoriAutomaticiDAO dad = new DistributoriAutomaticiDAO(em);
		VenditoriAutorizzatiDAO vad = new VenditoriAutorizzatiDAO(em);
		UtenteDAO ud = new UtenteDAO(em);
		TesseraDAO ted = new TesseraDAO(em);

		DistributoriAutomatici distributore1 = new DistributoriAutomatici(StatoDistributore.FUORI_SERVIZIO);
		DistributoriAutomatici distributore2 = new DistributoriAutomatici(StatoDistributore.FUNZIONANTE);
		DistributoriAutomatici distributore3 = new DistributoriAutomatici(StatoDistributore.FUNZIONANTE);
		VenditoriAutorizzati venditore1 = new VenditoriAutorizzati("Amazon", "E-Commerce");
		VenditoriAutorizzati venditore2 = new VenditoriAutorizzati("TuttoQui", "Edicola");
		VenditoriAutorizzati venditore3 = new VenditoriAutorizzati("Da Enrico", "Tabaccaio");

		Utente utente1 = new Utente("Luca", "Sberna");
		Utente utente2 = new Utente("Nestor", "Cicardini");
		Utente utente3 = new Utente("Matteo", "Vacca");
		Utente utente4 = new Utente("Zlatan", "Ibrahimovich");
		Utente utente5 = new Utente("Paolo", "Maldini");
		Utente utente6 = new Utente("Pop", "Smoke");

		Tratta t1 = new Tratta("Roma", "Latina", 2.30);
		Tratta t2 = new Tratta("Milano", "Roma", 5.30);
		Tratta t3 = new Tratta("Napoli", "Milano", 9.30);
		Tratta t4 = new Tratta("Torino", "Firenze", 3.30);
		Tratta t5 = new Tratta("Milano", "Rho", 1.30);

//        td.save(t1);

		Tratta foundt = td.getById("10f49095-bb8c-4c3c-8206-656a8392577a");

		Mezzo m1 = new Mezzo(60, statoMezzo.inServizio, tipoMezzo.Autobus, t2);
		Mezzo m2 = new Mezzo(300, statoMezzo.inServizio, tipoMezzo.Tram, t1);
		Mezzo m3 = new Mezzo(60, statoMezzo.inManutenzione, tipoMezzo.Autobus, t4);
		Mezzo m4 = new Mezzo(300, statoMezzo.inServizio, tipoMezzo.Tram, t1);
		Mezzo m5 = new Mezzo(60, statoMezzo.inServizio, tipoMezzo.Autobus, t3);
		Mezzo m6 = new Mezzo(300, statoMezzo.inServizio, tipoMezzo.Tram, t5);
//        md.save(m1);
//        md.save(m2);

//		EmissioneAbbonamento tessera1 = distributore2.emettiAbbonamento(utente1);
//		EmissioneAbbonamento tessera2 = distributore3.emettiAbbonamento(utente2);
//		EmissioneBiglietto biglietto1 = venditore1.emettiBiglietto(utente3);
//		EmissioneBiglietto biglietto3 = venditore3.emettiBiglietto(utente5);
//		EmissioneBiglietto biglietto2 = venditore2.emettiBiglietto(utente4);
//		EmissioneBiglietto biglietto4 = venditore3.emettiBiglietto(utente6);

		// quante volte è stata percorda una tratta
		log.info(" " + td.getTimesTrattaPercorsa("10f49095-bb8c-4c3c-8206-656a8392577a"));

		// quante volte è stata percorsa una tratta da un singolo Mezzo
		log.info(" " + td.getTimesTrattaPercorsaBySingleMezzo("10f49095-bb8c-4c3c-8206-656a8392577a",
				"b207b034-95f8-400c-99a1-ec80b45f5eb2"));

		em.close();
	}

}

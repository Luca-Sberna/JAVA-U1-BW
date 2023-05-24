package app;

import java.time.LocalDate;

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
import dao.VidimazioneBigliettiDAO;
import entities.DistributoriAutomatici;
import entities.DistributoriAutomatici.StatoDistributore;
import entities.EmissioneAbbonamento;
import entities.EmissioneBiglietto;
import entities.Mezzo;
import entities.Mezzo.statoMezzo;
import entities.Mezzo.tipoMezzo;
import entities.Tratta;
import entities.Utente;
import entities.VenditoriAutorizzati;
import entities.VidimazioneBiglietti;
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
		VidimazioneBigliettiDAO vbd = new VidimazioneBigliettiDAO(em);

		DistributoriAutomatici distributore1 = new DistributoriAutomatici(
				StatoDistributore.FUORI_SERVIZIO);
		DistributoriAutomatici distributore2 = new DistributoriAutomatici(
				StatoDistributore.FUNZIONANTE);
		DistributoriAutomatici distributore3 = new DistributoriAutomatici(
				StatoDistributore.FUNZIONANTE);
		VenditoriAutorizzati venditore1 = new VenditoriAutorizzati("Amazon",
				"E-Commerce");
		VenditoriAutorizzati venditore2 = new VenditoriAutorizzati("TuttoQui",
				"Edicola");
		VenditoriAutorizzati venditore3 = new VenditoriAutorizzati("Da Enrico",
				"Tabaccaio");

		Utente utente1 = new Utente("Luca", "Sberna");
		Utente utente2 = new Utente("Nestor", "Cicardini");
		Utente utente3 = new Utente("Matteo", "Vacca");
		Utente utente4 = new Utente("Zlatan", "Ibrahimovich");
		Utente utente5 = new Utente("Paolo", "Maldini");
		Utente utente6 = new Utente("Pop", "Smoke");

		ud.save(utente6);
		Utente foundutente6 = ud
				.getById("15431e5c-800f-4b3e-9ba3-334306a5a54b");
		if (foundutente6 != null) {
			log.info("trovato");
		} else {
			log.info("non trovato");
		}

		Tratta t1 = new Tratta("Roma", "Latina", 2.30);
		Tratta t2 = new Tratta("Milano", "Roma", 5.30);
		Tratta t3 = new Tratta("Napoli", "Milano", 9.30);
		Tratta t4 = new Tratta("Torino", "Firenze", 3.30);
		Tratta t5 = new Tratta("Milano", "Rho", 1.30);

//        td.save(t1);
//		td.save(t1);
//		td.save(t2);
//		td.save(t3);
//		td.save(t4);
//		td.save(t5);

		Tratta foundt = td.getById("10f49095-bb8c-4c3c-8206-656a8392577a");

		Mezzo m1 = new Mezzo(60, statoMezzo.inServizio, tipoMezzo.Autobus, t2);
		Mezzo m2 = new Mezzo(300, statoMezzo.inServizio, tipoMezzo.Tram, t1);
		Mezzo m3 = new Mezzo(60, statoMezzo.inManutenzione, tipoMezzo.Autobus,
				t4);
		Mezzo m4 = new Mezzo(300, statoMezzo.inServizio, tipoMezzo.Tram, t1);
		Mezzo m5 = new Mezzo(60, statoMezzo.inServizio, tipoMezzo.Autobus, t3);
//        md.save(m1);
//        md.save(m2);
		Mezzo m6 = new Mezzo(300, statoMezzo.inServizio, tipoMezzo.Tram, t5);
//		md.save(m1);
//		md.save(m2);
//		md.save(m3);
//		md.save(m4);
//		md.save(m5);
//		md.save(m6);

		EmissioneAbbonamento tessera1 = distributore2
				.emettiAbbonamento(utente1);
		EmissioneAbbonamento tessera2 = distributore3
				.emettiAbbonamento(utente2);
		EmissioneBiglietto biglietto1 = venditore1.emettiBiglietto(utente3);
		EmissioneBiglietto biglietto3 = venditore3.emettiBiglietto(utente5);
		EmissioneBiglietto biglietto2 = venditore2.emettiBiglietto(utente4);
		EmissioneBiglietto biglietto4 = venditore3.emettiBiglietto(utente6);

//		ebd.save(biglietto1);
//		ebd.save(biglietto2);
//		ebd.save(biglietto3);
//		ebd.save(biglietto4);

		DistributoriAutomatici distributore50 = new DistributoriAutomatici();

//		dad.save(distributore50);

		DistributoriAutomatici founddistributore50 = dad
				.getById("6d104827-fe97-4000-bf8b-5ac061942f52");
		EmissioneBiglietto biglietto5 = new EmissioneBiglietto(
				LocalDate.of(2000, 01, 01), foundutente6, null,
				founddistributore50);
//		ebd.save(biglietto5);

		Mezzo foundm1 = md.getById("2c548dd0-d9a8-4491-bdb3-306a14601f9d");
		EmissioneBiglietto foundeb1 = ebd
				.getById("9051a41b-81ce-4afb-ae5b-01616fb374e2");

		VidimazioneBiglietti vidimazione1 = new VidimazioneBiglietti(foundeb1,
				foundm1, LocalDate.now());

//		vbd.save(vidimazione1);

		long nVidimazioni = md.getNumeroBigliettiVidimati(
				"2c548dd0-d9a8-4491-bdb3-306a14601f9d");

		log.info("Numero di biglietti vidimati su mezzo "
				+ foundm1.getTipoMezzo() + " con id " + foundm1.getId() + ": "
				+ nVidimazioni);

		// quante volte è stata percorda una tratta
		log.info(" " + td.getTimesTrattaPercorsa(
				"10f49095-bb8c-4c3c-8206-656a8392577a"));

		// quante volte è stata percorsa una tratta da un singolo Mezzo
		log.info(" " + td.getTimesTrattaPercorsaBySingleMezzo(
				"10f49095-bb8c-4c3c-8206-656a8392577a",
				"b207b034-95f8-400c-99a1-ec80b45f5eb2"));

		// Query:trovare abbonamento attivo in base a numeroTessera in
		// step(Nestor)

		// 1Step) creo tessera e la salvo in db
//		Tessera tesserautente6 = new Tessera(foundutente6, LocalDate.of(2023, 02, 02),
//				LocalDate.of(2023, 02, 02).plusYears(1));
//		ted.save(tesserautente6);

		// 2Step)

		// Query : trovare il numero di biglietti vidimati in base al mezzo in
		// step
		// 1 Step) Creo un biglietto vidimato (biglietto5 di popsmoke) nel mezzo
		// m2 e lo
		// salvo in db
//		td.save(t5);

		Tratta foundtratta5 = td
				.getById("5c82df50-9f63-448e-98ba-532e7c73cb41");
		Mezzo m7 = new Mezzo(300, statoMezzo.inServizio, tipoMezzo.Tram,
				foundtratta5);
//		md.save(m6);
		Mezzo foundmezzo7 = md.getById("b207b034-95f8-400c-99a1-ec80b45f5eb2");
		VidimazioneBiglietti bigliettovidimato1 = new VidimazioneBiglietti(
				biglietto5, foundmezzo7, LocalDate.of(2023, 01, 01));
//		vbd.save(bigliettovidimato1);

		log.info(
				"Il numero dei biglietti vidimati nel range che stavi cercando sono  "
						+ vbd.getBigliettiVidimatiPerMezzoInRange(foundmezzo7,
								LocalDate.of(2022, 11, 11),
								LocalDate.of(2023, 12, 12)));

		em.close();
		emf.close();
	}

}

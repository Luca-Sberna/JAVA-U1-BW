package app;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

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
import entities.EmissioneAbbonamento;
import entities.EmissioneAbbonamento.TipoEvento;
import entities.EmissioneBiglietto;
import entities.Mezzo;
import entities.Mezzo.statoMezzo;
import entities.Mezzo.tipoMezzo;
import entities.Tratta;
import entities.Tessera;
import entities.Utente;
import entities.VenditoriAutorizzati;
import entities.VidimazioneBiglietti;
import util.JpaUtil;

public class MainInterattivo {
	private static EntityManagerFactory emf = JpaUtil.getEntityManagerFactory();

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
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

		Tratta t1 = new Tratta("Roma", "Latina", 2.30, 70.32);
		Tratta t2 = new Tratta("Milano", "Roma", 5.30, 477.0);
//		td.save(t1);
//		td.save(t2);
		Mezzo m1 = new Mezzo(60, statoMezzo.inServizio, tipoMezzo.Autobus, t2);
		Mezzo m2 = new Mezzo(300, statoMezzo.inServizio, tipoMezzo.Tram, t1);
//		md.save(m2);
//		md.save(m1);
		VenditoriAutorizzati venditore1 = new VenditoriAutorizzati("Amazon", "E-Commerce");
		VenditoriAutorizzati venditore2 = new VenditoriAutorizzati("TuttoQui", "Edicola");
		VenditoriAutorizzati venditore3 = new VenditoriAutorizzati("Da Enrico", "Tabaccaio");
		vad.save(venditore3);
		vad.save(venditore2);
		vad.save(venditore1);

		// Login/Register
		System.out.println("Benvenuto all'app di trasporti pubblici!");
		System.out.println("1. Accedi");
		System.out.println("2. Nuovo nell'app? Registrati");
		int sceltaUtente = scanner.nextInt();

		Utente utente = null;
		switch (sceltaUtente) {
		case 1:
			// Login
			System.out.println("Inserisci l'ID dell'utente:");
			String idUtente = scanner.next();

			utente = ud.getById(idUtente);

			if (utente == null) {
				System.out.println("Utente non valido.");
				// Gestire l'errore o uscire dall'applicazione
				break;
			}
			System.out.println("Che bello rivederti " + utente.getNome() + " " + utente.getCognome());
			break;

		case 2:
			// Creazione utente (Register)
			scanner.nextLine(); // Pulisce lo scanner
			System.out.println("Inserisci il tuo nome: ");
			String nomeScelto = scanner.nextLine();
			System.out.println("Inserisci il tuo cognome: ");
			String cognomeScelto = scanner.nextLine();
			utente = new Utente(nomeScelto, cognomeScelto);
			ud.save(utente);
			break;

		default:
			System.out.println("Selezione non valida. Uscita dall'app.");
			em.close();
			emf.close();
			System.exit(0);
		}

		// Prompt per l'utente
//		System.out.println(nomeScelto + " " + cognomeScelto + ", seleziona il venditore:");
//		System.out.println("1. Amazon (E-Commerce)");
//		System.out.println("2. TuttoQui (Edicola)");
//		System.out.println("3. Da Enrico (Tabaccaio)");
//		System.out.println("4. Distributore via carrara");
//		System.out.print("Scelta: ");

		// Recupera tutti i venditori autorizzati e visualizzali all'utente
		System.out.println("seleziona il venditore:");
		List<VenditoriAutorizzati> venditori = vad.getAllVenditoriAutorizzati();
		for (VenditoriAutorizzati venditore : venditori) {
			System.out.println(venditore.getIdPuntoVendita() + ". " + venditore.getNomeNegozio());
		}
		int venditoreScelto = scanner.nextInt();

		VenditoriAutorizzati venditoreSelezionato = null;
		if (venditoreScelto >= 1 && venditoreScelto <= venditori.size()) {
			venditoreSelezionato = venditori.get(venditoreScelto - 1);
			// Ora puoi utilizzare "mezzoSelezionato" come desideri
			System.out.println("Hai scelto il mezzo: " + venditoreSelezionato.getIdPuntoVendita() + ". "
					+ venditoreSelezionato.getNomeNegozio());
		} else {
			System.out.println("La scelta del mezzo non è valida.");
		}

		VenditoriAutorizzati venditore = null;
		DistributoriAutomatici distributore = null;
		VidimazioneBiglietti vidimazione = null;

//		switch (venditoreScelto) {
//		case 1:
//			venditore = new VenditoriAutorizzati("Amazon", "E-Commerce");
//			break;
//		case 2:
//			venditore = new VenditoriAutorizzati("TuttoQui", "Edicola");
//			break;
//		case 3:
//			venditore = new VenditoriAutorizzati("Da Enrico", "Tabaccaio");
//			break;
//		case 4:
//			distributore = new DistributoriAutomatici("Via Carrara 23 Milano");
//			dad.save(distributore);
//			break;
//		default:
//			System.out.println("Selezione non valida. Uscita dall'app.");
//			em.close();
//			emf.close();
//			System.exit(0);
//		}

		System.out.println("Cosa desideri acquistare?");
		System.out.println("1. Biglietto");
		System.out.println("2. Abbonamento");
		System.out.print("Scelta: ");

		int tipoAcquisto = scanner.nextInt();

		switch (tipoAcquisto) {
		case 1:
			// Logica per l'acquisto del biglietto <--- inserire qui
			EmissioneBiglietto biglietto = new EmissioneBiglietto(LocalDate.now(), utente, vidimazione, distributore);
			ebd.save(biglietto);
			System.out.println("Seleziona un mezzo per la tratta disponibile:");
			// Recupera i mezzi disponibili e visualizzali all'utente
			List<Mezzo> mezziDisponibili = md.getAllMezzi();
			for (Mezzo mezzo : mezziDisponibili) {
				System.out.println(mezzo.getId() + ". " + mezzo.getTipoMezzo());
			}

			int mezzoScelto = scanner.nextInt();
			Mezzo mezzoSelezionato = null;
			if (mezzoScelto >= 1 && mezzoScelto <= mezziDisponibili.size()) {
				mezzoSelezionato = mezziDisponibili.get(mezzoScelto - 1);
				// Ora puoi utilizzare "mezzoSelezionato" come desideri
				System.out.println(
						"Hai scelto il mezzo: " + mezzoSelezionato.getId() + ". " + mezzoSelezionato.getTipoMezzo());
			} else {
				System.out.println("La scelta del mezzo non è valida.");
			}

			System.out.println("Biglietto emesso!");
			System.out.println("Buon viaggio! WOOOO!");
			System.out.println("***Ricordarsi di convalidare il biglietto sul mezzo!***");

			System.out.println("Salendo sul mezzo");

			System.out
					.println("Vuoi timbrare il biglietto sul mezzo " + "(" + mezzoScelto + ")" + " selezionato? (S/N)");
			String confermaTimbro = scanner.next();

			if (confermaTimbro.equalsIgnoreCase("S")) {
				// Logica per il timbro del biglietto sul mezzo selezionato <---
				// inserire qui
				VidimazioneBiglietti vidimazioneee = new VidimazioneBiglietti(biglietto, mezzoSelezionato,
						LocalDate.now());
				vbd.save(vidimazioneee);

				System.out.println(
						"Biglietto timbrato correttamente sul mezzo " + mezzoScelto + "bravo picciotto buon viaggio!.");
			} else {
				System.out.println(
						"Ricordati di convalidare il biglietto sul mezzo una volta salito o verrai multato a sangue.");
			}
			break;
		case 2:
			LocalDate dataInizio = LocalDate.now();

			// Richiedere all'utente di selezionare il tipo di abbonamento
			System.out.println("Seleziona il tipo di abbonamento:");
			System.out.println("1. Settimanale");
			System.out.println("2. Mensile");
			System.out.print("Scelta: ");
			int tipoAbbonamentoScelto = scanner.nextInt();

			// Impostare il tipo di abbonamento in base alla scelta dell'utente
			TipoEvento tipoAbbonamento;
			switch (tipoAbbonamentoScelto) {
			case 1:
				tipoAbbonamento = TipoEvento.SETTIMANALE;
				break;

			case 2:
				tipoAbbonamento = TipoEvento.MENSILE;
				break;

			default:
				System.out.println("Selezione non valida. Uscita dall'app.");
				em.close();
				emf.close();
				System.exit(0);
				return;
			}

			// Calcolare la data di scadenza in base al tipo di abbonamento scelto
			LocalDate dataScadenza;
			if (tipoAbbonamento == TipoEvento.SETTIMANALE) {
				dataScadenza = dataInizio.plusWeeks(1);
			} else {
				dataScadenza = dataInizio.plusMonths(1);
			}

			// Creare un nuovo oggetto Tessera associato all'utente
			Tessera tessera = new Tessera(utente, dataInizio);
			ted.save(tessera);

			// Crea un nuovo oggetto EmissioneAbbonamento
			EmissioneAbbonamento abbonamento = new EmissioneAbbonamento(dataInizio, dataScadenza, tipoAbbonamento,
					tessera);
			ead.save(abbonamento);

			System.out.println("Abbonamento emesso e acquistato con successo!");

			int uscita;
			do {
				System.out.println("Ecco i mezzi disponibili: (premi 0 per uscire)");
				List<Mezzo> mezziDisponibiliPerTessera = md.getAllMezzi();
				for (Mezzo mezzo : mezziDisponibiliPerTessera) {
					System.out.println(mezzo.getId() + ". " + mezzo.getTipoMezzo());
				}
				uscita = scanner.nextInt();
				if (uscita == 0) {
					System.out.println("Uscita dall'app.");
					em.close();
					emf.close();
					System.exit(0);
				} else {
					// Altre possibili azioni in base alla selezione del mezzo
					// <--- inserire qui
					// (opzionale)
				}
			} while (uscita != 0);

			System.out.println("Buon viaggio! WOOOOOO!!!");
			System.out.println("***Ricordarsi di convalidare la tessera sul mezzo!***");

			System.out.println("Salendo sul mezzo");

			System.out.println("Vuoi timbrare la tessera sul mezzo selezionato? (S/N)");
			String confermaTessera = scanner.next();

			if (confermaTessera.equalsIgnoreCase("S")) {
				// Logica per il timbro del biglietto sul mezzo selezionato <---
				// inserire qui
				System.out.println("Tessera timbrata correttamente sul mezzo bravo picciotto buon viaggio!.");
			} else {
				System.out.println(
						"Ricordati di convalidare la tessera sul mezzo una volta saliti o verrai mandato a quel paese.");
			}
			break;

		default:
			System.out.println("Selezione non valida. Uscita dall'app.");
			em.close();
			emf.close();
			System.exit(0);
		}
		scanner.close();
		em.close();
		emf.close();
	}
}

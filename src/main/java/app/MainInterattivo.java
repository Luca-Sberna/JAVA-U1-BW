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
import entities.EmissioneAbbonamento;
import entities.EmissioneAbbonamento.TipoEvento;
import entities.Mezzo;
import entities.Tessera;
import entities.Utente;
import entities.VenditoriAutorizzati;
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

		// Creazione utente
		System.out.println("Benvenuto all'app di trasporti pubblici!");
		System.out.println("Inserisci il tuo nome: ");
		String nomeScelto = scanner.nextLine();
		System.out.println("Inserisci il tuo cognome: ");
		String cognomeScelto = scanner.nextLine();
		Utente utente = new Utente(nomeScelto, cognomeScelto);
		ud.save(utente);

		// Prompt per l'utente
		System.out.println(nomeScelto + " " + cognomeScelto + ", seleziona il venditore:");
		System.out.println("1. Amazon (E-Commerce)");
		System.out.println("2. TuttoQui (Edicola)");
		System.out.println("3. Da Enrico (Tabaccaio)");
		System.out.print("Scelta: ");

		int venditoreScelto = scanner.nextInt();
		VenditoriAutorizzati venditore = null;

		switch (venditoreScelto) {
		case 1:
			venditore = new VenditoriAutorizzati("Amazon", "E-Commerce");
			break;
		case 2:
			venditore = new VenditoriAutorizzati("TuttoQui", "Edicola");
			break;
		case 3:
			venditore = new VenditoriAutorizzati("Da Enrico", "Tabaccaio");
			break;
		default:
			System.out.println("Selezione non valida. Uscita dall'app.");
			em.close();
			emf.close();
			System.exit(0);
		}

		System.out.println("Cosa desideri acquistare?");
		System.out.println("1. Biglietto");
		System.out.println("2. Abbonamento");
		System.out.print("Scelta: ");

		int tipoAcquisto = scanner.nextInt();

		switch (tipoAcquisto) {
		case 1:
			// Logica per l'acquisto del biglietto <--- inserire qui

			System.out.println("Seleziona un mezzo per la tratta disponibile:");
			// Recupera i mezzi disponibili e visualizzali all'utente
			List<Mezzo> mezziDisponibili = md.getAllMezzi();
			for (Mezzo mezzo : mezziDisponibili) {
				System.out.println(mezzo.getId() + ". " + mezzo.getTipoMezzo());
			}

			int mezzoScelto = scanner.nextInt();

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

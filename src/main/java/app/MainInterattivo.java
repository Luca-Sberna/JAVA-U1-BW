package app;

import java.util.Scanner;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import entities.VenditoriAutorizzati;
import util.JpaUtil;

public class MainInterattivo {
	private static EntityManagerFactory emf = JpaUtil.getEntityManagerFactory();

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		EntityManager em = emf.createEntityManager();

		// Codice di configurazione iniziale, come l'inizializzazione dei DAO e
		// l'importazione dei dati

		// Prompt per l'utente
		System.out.println("Benvenuto all'app di trasporti pubblici!");
		System.out.println("Seleziona il venditore:");
		System.out.println("1. Amazon (E-Commerce)");
		System.out.println("2. TuttoQui (Edicola)");
		System.out.println("3. Da Enrico (Tabaccaio)");
		System.out.print("Scelta: ");

		int venditoreScelto = scanner.nextInt();
		VenditoriAutorizzati venditore = null;

		// Assegna il venditore selezionato in base all'input dell'utente
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

		// Prompt per l'utente per selezionare biglietti o abbonamenti
		System.out.println("Cosa desideri acquistare?");
		System.out.println("1. Biglietto");
		System.out.println("2. Abbonamento");
		System.out.print("Scelta: ");

		int tipoAcquisto = scanner.nextInt();

		switch (tipoAcquisto) {
		case 1:
			// Logica per l'acquisto del biglietto

			System.out.println("Seleziona un mezzo per la tratta disponibile:");
			// Recupera i mezzi disponibili per la tratta e visualizzali all'utente

			int mezzoScelto = scanner.nextInt();

			// Emetti il biglietto e visualizza un messaggio di augurio
			System.out.println("Biglietto emesso!");
			System.out.println("Buon viaggio! WOOOO!");
			System.out.println("***Ricordarsi di convalidare il biglietto sul mezzo!***");

			System.out.println("Salendo sul mezzo");

			System.out
					.println("Vuoi timbrare il biglietto sul mezzo " + "(" + mezzoScelto + ")" + " selezionato? (S/N)");
			String confermaTimbro = scanner.next();

			if (confermaTimbro.equalsIgnoreCase("S")) {
				// Logica per il timbro del biglietto sul mezzo selezionato
				System.out.println(
						"Biglietto timbrato correttamente sul mezzo " + mezzoScelto + "bravo picciotto buon viaggio!.");
			} else {
				System.out.println(
						"Ricordati di convalidare il biglietto sul mezzo una volta salito o verrai multato a sangue.");
			}
			break;
		case 2:
			// Logica per l'acquisto dell'abbonamento

			// Emetti l'abbonamento
			System.out.println("Abbonamento emesso!");

			// Recupera i mezzi disponibili e visualizzali all'utente

			int uscita;
			do {
				System.out.println("Ecco i mezzi disponibili per la tratta: (premi 0 per uscire)");
				// Recupera i mezzi disponibili e visualizzali all'utente

				uscita = scanner.nextInt();
				if (uscita == 0) {
					System.out.println("Uscita dall'app.");
					em.close();
					emf.close();
					System.exit(0);
				} else {
					// Altre possibili azioni in base alla selezione del mezzo
				}
			} while (uscita != 0);

			System.out.println("Buon viaggio! WOOOOOO!!!");
			System.out.println("***Ricordarsi di convalidare la tessera sul mezzo!***");

			System.out.println("Salendo sul mezzo");

			System.out.println("Vuoi timbrare la tessera sul mezzo selezionato? (S/N)");
			String confermaTessera = scanner.next();

			if (confermaTessera.equalsIgnoreCase("S")) {
				// Logica per il timbro del biglietto sul mezzo selezionato
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
	}
}

package app;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;
import java.util.UUID;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import dao.DistributoriAutomaticiDAO;
import dao.EmissioneAbbonamentoDAO;
import dao.EmissioneBigliettoDAO;
import dao.MezzoDAO;
import dao.PuntiVenditaDAO;
import dao.TesseraDAO;
import dao.TrattaDAO;
import dao.UtenteDAO;
import dao.VenditoriAutorizzatiDAO;
import dao.ViaggioDAO;
import dao.VidimazioneAbbonamentiDAO;
import dao.VidimazioneBigliettiDAO;
import entities.DistributoriAutomatici;
import entities.DistributoriAutomatici.StatoDistributore;
import entities.EmissioneAbbonamento;
import entities.EmissioneAbbonamento.TipoEvento;
import entities.EmissioneBiglietto;
import entities.Mezzo;
import entities.Mezzo.statoMezzo;
import entities.Mezzo.tipoMezzo;
import entities.Tessera;
import entities.Tratta;
import entities.Utente;
import entities.VenditoriAutorizzati;
import entities.VidimazioneAbbonamenti;
import entities.VidimazioneBiglietti;
import lombok.extern.slf4j.Slf4j;
import util.JpaUtil;

@Slf4j
public class MainInterattivo {
	private static EntityManagerFactory emf = JpaUtil.getEntityManagerFactory();

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		EntityManager em = emf.createEntityManager();

		MezzoDAO md = new MezzoDAO(em);
		PuntiVenditaDAO pvd = new PuntiVenditaDAO(em);
		TrattaDAO td = new TrattaDAO(em);
		EmissioneBigliettoDAO ebd = new EmissioneBigliettoDAO(em);
		EmissioneAbbonamentoDAO ead = new EmissioneAbbonamentoDAO(em);
		DistributoriAutomaticiDAO dad = new DistributoriAutomaticiDAO(em);
		VenditoriAutorizzatiDAO vad = new VenditoriAutorizzatiDAO(em);
		UtenteDAO ud = new UtenteDAO(em);
		TesseraDAO ted = new TesseraDAO(em);
		VidimazioneBigliettiDAO vbd = new VidimazioneBigliettiDAO(em);
		VidimazioneAbbonamentiDAO vabd = new VidimazioneAbbonamentiDAO(em);
		ViaggioDAO vd = new ViaggioDAO(em);

		DistributoriAutomatici distributore2 = new DistributoriAutomatici("Stazione Milano Centrale",
				StatoDistributore.FUNZIONANTE);

		VenditoriAutorizzati venditore1 = new VenditoriAutorizzati("Amazon", "E-Commerce");
		VenditoriAutorizzati venditore2 = new VenditoriAutorizzati("TuttoQui", "Edicola");
		VenditoriAutorizzati venditore3 = new VenditoriAutorizzati("Da Enrico", "Tabaccaio");

		// Login/Register
		System.out.println("Benvenuto all'app di trasporti pubblici!");
		System.out.println("1. Accedi");
		System.out.println("2. Nuovo nell'app? Registrati");
		System.out.println("3. Admin");
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

		case 3:
			System.out.println(" ");
			System.out.println("Sei nella sezione admin! Scegli cosa fare:");
			System.out.println(" ");
			System.out.println("1. Visualizza la lista dei mezzi");
			System.out.println("2. Visualizza la lista dei venditori e distributori");// teo
			System.out.println("3. Visualizza la lista degli utenti");
			System.out.println("4. Modifica un mezzo");
			System.out.println("5. Modifica una tratta ");// nest
			System.out.println("6. Modifica un venditore autorizzato ");
			System.out.println("7. Modifica un distributore automatico ");
			System.out.println("8. Crea e aggiungi un mezzo con una tratta propria ");
			System.out.println("9. Crea e aggiungi un venditore autorizzato ");
			System.out.println("10. Crea e aggiungi un distributore automatico ");// teo
			// query ⬇️⬇️⬇️⬇️⬇️⬇️⬇️⬇️
			System.out.println("11. Visualizza Abbonamenti attivi dal numero tessera");
			System.out.println("12. Visualizza quante volte è stata percorsa una tratta");
			System.out.println("13. Visualizza quante volte è stata percorsa una tratta da un singolo mezzo");
			System.out.println("14. Visualizza le tessere scadute che devono essere rinnovate");
			System.out.println("15. Visualizza numero biglietti vidimati in un periodo di tempo");
			int sceltaAdmin = scanner.nextInt();
			switch (sceltaAdmin) {
			case 1:
				System.out.println("Ecco la lista dei mezzi e delle loro tratte");
				md.getAllMezzi().stream().forEach(m -> log.info(m.toString()));
				break;

			case 2:
				System.out.println("Ecco la lista dei venditori e distributori");
				pvd.getAllPuntiVendita().stream().forEach(
						pv -> log.info(pv.getLuogo() + " " + pv.getNumeroVendite() + " " + pv.getIdPuntoVendita()));
				break;

			case 3:
				System.out.println("Ecco la lista degli utenti");
				ud.getAllUsers().stream()
						.forEach(u -> log.info(u.getNome() + " " + u.getCognome() + " " + u.getIdUtente()));
				break;

			case 4:
				System.out.println("Inserisci l'ID del mezzo che desideri modificare:");
				String id = scanner.next();
				UUID idMezzo = UUID.fromString(id);
				System.out.println("Inserisci la nuova capienza del mezzo:");
				long capienza = scanner.nextLong();
				scanner.nextLine();
				System.out.println("Inserisci il nuovo stato del mezzo (OPZIONI_VALIDE):");
				String statoString = scanner.nextLine();
				statoMezzo stato = statoMezzo.valueOf(statoString);
				System.out.println("Inserisci il nuovo tipo di mezzo (OPZIONI_VALIDE):");
				String tipoMezzoString = scanner.nextLine();
				tipoMezzo tipoMezzoo = tipoMezzo.valueOf(tipoMezzoString);

				System.out.println("Desideri modificare la tratta associata? (Sì/No)");
				String modificaTratta = scanner.next();

				if (modificaTratta.equalsIgnoreCase("Si")) {
					System.out.println("Inserisci la città di partenza per la tratta: ");
					String partenza = scanner.next();
					System.out.println("Inserisci la città di destinazione per la tratta: ");
					String destinazione = scanner.next();
					System.out.println("Inserisci la distanza tra la partenza e il capolinea: ");
					double distanza = scanner.nextDouble();
					System.out.println("Inserisci la durata totale del viaggio: ");
					double durata = scanner.nextDouble();

					// Crea la nuova tratta con i valori forniti dall'utente
					Tratta nuovaTratta = new Tratta(partenza, destinazione, distanza, durata);
					td.save(nuovaTratta); // Mostra all'utente i dettagli della
											// nuova tratta e richiedi conferma
					System.out.println("Nuova tratta:");
					System.out.println(nuovaTratta);

					md.findByIdAndUpdate(idMezzo, capienza, stato, tipoMezzoo, nuovaTratta);
				}
				break;

			case 5:
				System.out.println("Modifica una tratta");
				System.out.println("Inserisci l'ID della tratta da modificare: ");
				UUID trattaId = UUID.fromString(scanner.next());

				// Trova la tratta tramite l'ID
				Tratta trattaDaModificare = td.getById(trattaId);

				if (trattaDaModificare != null) {
					System.out.println("Inserisci la nuova città di partenza: ");
					String nuovaPartenza = scanner.next();
					System.out.println("Inserisci la nuova città di destinazione: ");
					String nuovaDestinazione = scanner.next();
					System.out.println("Inserisci la nuova distanza tra la partenza e il capolinea: ");
					double nuovaDistanza = scanner.nextDouble();
					System.out.println("Inserisci la nuova durata totale del viaggio: ");
					double nuovaDurata = scanner.nextDouble();

					// Mostra all'utente i dettagli delle modifiche e richiedi
					// conferma
					System.out.println("Dettagli della modifica:");
					System.out.println("Città di partenza: " + nuovaPartenza);
					System.out.println("Città di destinazione: " + nuovaDestinazione);
					System.out.println("Distanza: " + nuovaDistanza);
					System.out.println("Durata: " + nuovaDurata);
					System.out.println("Confermi le modifiche? (Sì/No)");
					String confermaModifiche = scanner.next();

					if (confermaModifiche.equalsIgnoreCase("Si")) {
						trattaDaModificare.setZonaPartenza(nuovaPartenza);
						trattaDaModificare.setCapolinea(nuovaDestinazione);
						trattaDaModificare.setLunghezzaTratta(nuovaDistanza);
						trattaDaModificare.setTempoMedioTratta(nuovaDurata);
						td.saveTratta(trattaDaModificare);
						System.out.println("Tratta modificata con successo!");
					} else {
						System.out.println("Modifica annullata.");
					}
				} else {
					System.out.println("Tratta non trovata.");
				}
				break;

			case 6:
				System.out.println(" ");
				System.out.println("Inserisci l'ID del venditore autorizzato da modificare: ");
				String venditoreIdString = scanner.next();
				UUID venditoreId = UUID.fromString(venditoreIdString);

				VenditoriAutorizzati venditoreEsistente = vad.getById(venditoreId);
				if (venditoreEsistente != null) {
					System.out.println("Inserisci il nuovo nome del negozio: ");
					String nuovoNomeNegozio = scanner.next();
					System.out.println("Inserisci il nuovo tipo di negozio: ");
					String nuovoTipoNegozio = scanner.next();

					VenditoriAutorizzati datiAggiornati = new VenditoriAutorizzati(nuovoNomeNegozio, nuovoTipoNegozio);
					vad.modificaVenditoreAutorizzato(venditoreId, datiAggiornati);
					System.out.println("Venditore autorizzato modificato con successo!");
				} else {
					System.out.println("Venditore autorizzato non trovato.");
				}
				break;

			case 7:
				System.out.println("Modifica un distributore automatico");
				System.out.println("Inserisci l'ID del distributore da modificare: ");
				String distributoreId = scanner.next();

				// Trova il distributore tramite l'ID
				DistributoriAutomatici distributoreDaModificare = dad.getById(distributoreId);

				if (distributoreDaModificare != null) {
					scanner.nextLine(); // Pulisce lo scanner
					System.out.println("Inserisci il luogo del distributore: ");
					String nuovoLuogo = scanner.nextLine();
					System.out.println("Inserisci il nuovo stato del distributore (FUNZIONANTE/FUORI_SERVIZIO):");
					String statoScelto = scanner.next();
					DistributoriAutomatici.StatoDistributore statoD = DistributoriAutomatici.StatoDistributore
							.valueOf(statoScelto);

					// Mostra all'utente i dettagli delle modifiche e richiedi
					// conferma
					System.out.println("Dettagli della modifica:");
					System.out.println("Luogo distributore: " + nuovoLuogo);
					System.out.println("Stato: " + statoScelto);
					System.out.println("Confermi le modifiche? (Sì/No)");
					String confermaModifiche = scanner.next();

					if (confermaModifiche.equalsIgnoreCase("Si")) {
						distributoreDaModificare.setLuogo(nuovoLuogo);
						distributoreDaModificare.setStato(statoD);
						dad.save(distributoreDaModificare);
						System.out.println("Distributore automatico modificato con successo!");
					} else {
						System.out.println("Modifica annullata.");
					}
				} else {
					System.out.println("Distributore non trovata.");
				}

				break;

			case 8:
				System.out.println("Inserisci il tipo di mezzo: ");
				String tipoMezzo = scanner.next();
				System.out.println("Inserisci il numero di posti disponibili: ");
				int postiDisponibili = scanner.nextInt();
				System.out.println("Inserisci lo stato del mezzo (inServizio o inManutenzione): ");
				String statoMezzo = scanner.next();
				System.out.println("Inserisci la velocità media del mezzo: ");
				int velocitàMedia = scanner.nextInt();

				System.out.println("Inserisci la città di partenza per la tratta: ");
				String partenza = scanner.next();
				System.out.println("Inserisci la città di destinazione per la tratta: ");
				String destinazione = scanner.next();
				System.out.println("Inserisci la distanza tra la partenza e il capolinea: ");
				double distanza = scanner.nextDouble();
				System.out.println("Inserisci la durata totale del viaggio: ");
				double durata = scanner.nextDouble();

				Tratta tratta = new Tratta(partenza, destinazione, distanza, durata);

				Mezzo nuovoMezzo = new Mezzo(postiDisponibili, Mezzo.statoMezzo.valueOf(statoMezzo),
						Mezzo.tipoMezzo.valueOf(tipoMezzo), tratta);
				nuovoMezzo.setVelocitàMedia(velocitàMedia);

				md.save(nuovoMezzo);
				System.out.println("Nuovo mezzo creato e aggiunto con successo!");
				break;

			case 9:
				System.out.println("Inserisci il nome del negozio: ");
				String nomeNegozio = scanner.next();
				System.out.println("Inserisci il tipo di negozio: ");
				String tipoNegozio = scanner.next();

				VenditoriAutorizzati nuovoVenditore = new VenditoriAutorizzati(nomeNegozio, tipoNegozio);
				vad.saveVenditoreAutorizzato(nuovoVenditore);
				System.out.println("Nuovo venditore autorizzato creato e aggiunto con successo!");
				break;

			case 10:
				System.out.println("Inserisci lo stato del distributore automatico (FUNZIONANTE o FUORI_SERVIZIO): ");
				String statoDistributore = scanner.next().toUpperCase();

				DistributoriAutomatici nuovoDistributore = new DistributoriAutomatici(
						DistributoriAutomatici.StatoDistributore.valueOf(statoDistributore));
				dad.saveDistributoreAutomatico(nuovoDistributore);
				System.out.println("Nuovo distributore automatico creato e aggiunto con successo!");
				break;

			case 11:
				System.out.println("Inserisci l'id tessera dell'abbonamento che vuoi verificare");
				String numTessera = scanner.next();

				// -------Trovare abbonamenti attivi dal numero tessera dell'utente-------
				System.out.println("Abbonamenti attivi dal numero tessera");
				ead.findAbbonamentiAttiviByNumeroTessera(numTessera).stream()
						.forEach(abbonamento -> log.info("L'abbonamento che cercavi è: " + abbonamento.getIdEmissione()
								+ " Data d'emissione: " + abbonamento.getDataEmissione() + " ,Data scadenza: "
								+ abbonamento.getDataScadenza()));
				break;
			case 12:
				System.out.println("Inserisci l'id della tratta che vuoi controllare ");
				String idTratta = scanner.next();
				log.info("La tratta che cercavi è stata percorsa tot: " + td.getTimesTrattaPercorsa(idTratta));
				break;
			case 13:
				System.out.println("Inserisci l'id della tratta");
				String idTrattaa = scanner.next();
				System.out.println("Inserisci l'id del mezzo");
				String idMezzoo = scanner.next();
				log.info("La tratta percorsa dal singolo mezzo che cercavi è stata percorsa tot:  "
						+ td.getTimesTrattaPercorsaBySingleMezzo(idTrattaa, idMezzoo));
				break;
			case 14:
				System.out.println("Inserisci l'id dell'utente");
				String idUtentee = scanner.next();
				ted.trovaTessereScadutePerUtente(idUtentee).stream().forEach(t -> log.info(t.toString()));
				break;
			case 15:
				System.out.println("Inserisci l'id del mezzo");
				String idMezz = scanner.next();
				log.info("Il numero dei biglietti vidimati nel range che stavi cercando sono  "
						+ vbd.getBigliettiVidimatiPerMezzoInRange(idMezz, LocalDate.of(2022, 04, 24),
								LocalDate.of(2025, 06, 26)));
				break;

			default:
				System.out.println("Selezione non valida");
			}
			break;
		default:
			System.out.println("Selezione non valida. Uscita dall'app.");
			em.close();
			emf.close();
			System.exit(0);
		}

		if (sceltaUtente != 3) {

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
				System.out.println("Hai scelto il venditore: " + venditoreSelezionato.getIdPuntoVendita() + ". "
						+ venditoreSelezionato.getNomeNegozio() + " " + venditoreSelezionato.getTipoDiNegozio());
			} else {
				System.out.println("La scelta del venditore non è valida.");
			}

			// ACQUISTO BIGLIETTO O ABBONAMENTO ⬇️⬇️⬇️
			VenditoriAutorizzati venditore = null;
			DistributoriAutomatici distributore = null;
			VidimazioneBiglietti vidimazione = null;

			System.out.println("Cosa desideri acquistare?");
			System.out.println("1. Biglietto");
			System.out.println("2. Abbonamento");
			System.out.print("Scelta: ");

			int tipoAcquisto = scanner.nextInt();

			switch (tipoAcquisto) {
			case 1:
				// CASE ACQUISTO BIGLIETTO ⬇️⬇️⬇️
				EmissioneBiglietto biglietto = new EmissioneBiglietto(LocalDate.now(), utente, vidimazione,
						distributore);
				ebd.save(biglietto);
				System.out.println("Seleziona un mezzo per la tratta disponibile:");

				// SELEZIONE MEZZO ⬇️⬇️⬇️
				List<Mezzo> mezziDisponibili = md.getAllMezzi();
				for (Mezzo mezzo : mezziDisponibili) {
					System.out.println(mezzo.getId() + ". " + mezzo.getTipoMezzo() + " " + mezzo.getTratta());
				}
				// STAMPA IN CONSOLE IL MEZZO SCELTO ⬇️⬇️⬇️
				int mezzoScelto = scanner.nextInt();
				Mezzo mezzoSelezionato = null;
				if (mezzoScelto >= 1 && mezzoScelto <= mezziDisponibili.size()) {
					mezzoSelezionato = mezziDisponibili.get(mezzoScelto - 1);

					System.out.println("Hai scelto il mezzo: " + mezzoSelezionato.getId() + ". "
							+ mezzoSelezionato.getTipoMezzo() + " " + mezzoSelezionato.getTratta());
				} else {
					System.out.println("La scelta del mezzo non è valida.");
				}

				System.out.println("Biglietto emesso!");
				System.out.println("Buon viaggio! WOOOO!");
				System.out.println("***Ricordarsi di convalidare il biglietto sul mezzo!***");

				System.out.println("Salendo sul mezzo" + " " + mezzoSelezionato);

				System.out.println(
						"Vuoi timbrare il biglietto sul mezzo " + "(" + mezzoSelezionato + ")" + " selezionato? (S/N)");
				String confermaTimbro = scanner.next();

				// VIDIMAZIONE BIGLIETTO ⬇️⬇️⬇️
				if (confermaTimbro.equalsIgnoreCase("S")) {
					VidimazioneBiglietti vidimazioneee = new VidimazioneBiglietti(biglietto, mezzoSelezionato,
							LocalDate.now());
					vbd.save(vidimazioneee);

					System.out.println("Biglietto timbrato correttamente sul mezzo " + mezzoScelto
							+ " bravo picciotto buon viaggio!.");
				} else {
					System.out.println(
							"Ricordati di convalidare il biglietto sul mezzo una volta salito o verrai multato a sangue.");
				}
				break;
			case 2:
				// CASE ACQUISTO ABBONAMENTO ⬇️⬇️⬇️
				LocalDate dataInizio = LocalDate.now();

				System.out.println("Seleziona il tipo di abbonamento:");
				System.out.println("1. Settimanale");
				System.out.println("2. Mensile");
				System.out.print("Scelta: ");
				int tipoAbbonamentoScelto = scanner.nextInt();

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

				// CALCOLO DATA SCADENZA IN BASE AL TIPO DI ABBONAMENTO SCELTO ⬇️⬇️⬇️
				LocalDate dataScadenza;
				if (tipoAbbonamento == TipoEvento.SETTIMANALE) {
					dataScadenza = dataInizio.plusWeeks(1);
				} else {
					dataScadenza = dataInizio.plusMonths(1);
				}

				// CREAZIONE NUOVO OGGETTO TESSERA ASSOCIATO ALL'UTENTE ⬇️⬇️⬇️
				Tessera tessera = new Tessera(utente, dataInizio);
				ted.save(tessera);

				// CREAZIONE UN NUOVO OGGETTO EMISSIONEABBONAMENTO ASSOCIATO ALLA TESSERA
				// DELL'UTENTE (SETTANDO L'ID MANUALMENTE) ⬇️⬇️⬇️
				EmissioneAbbonamento abbonamento = new EmissioneAbbonamento(dataInizio, dataScadenza, tipoAbbonamento,
						tessera);
				abbonamento.setIdEmissione(UUID.randomUUID());
				ead.save2(abbonamento);

				log.info(abbonamento.toString());
				System.out.println("Abbonamento emesso e acquistato con successo!");

				// SELEZIONE DEL MEZZO ⬇️⬇️⬇️
				Mezzo mezzoSelezionatoPerTessera = null;
				int uscita = 0;
				do {
					System.out.println("Ecco i mezzi disponibili: (premi 0 per uscire)");

					List<Mezzo> mezziDisponibiliPerTessera = md.getAllMezzi();
					for (Mezzo mezzo : mezziDisponibiliPerTessera) {
						System.out.println(mezzo.getId() + ". " + mezzo.getTipoMezzo() + mezzo.getTratta());
					}
					// STAMPA IN CONSOLE IL MEZZO SCELTO ⬇️⬇️⬇️
					int mezzoSceltoPerTessera = scanner.nextInt();
					if (mezzoSceltoPerTessera >= 1 && mezzoSceltoPerTessera <= mezziDisponibiliPerTessera.size()) {
						mezzoSelezionatoPerTessera = mezziDisponibiliPerTessera.get(mezzoSceltoPerTessera - 1);
						System.out.println("Hai scelto il mezzo: " + mezzoSelezionatoPerTessera.getId() + ". "
								+ mezzoSelezionatoPerTessera.getTipoMezzo() + " per la tratta: "
								+ mezzoSelezionatoPerTessera.getTratta());
					} else {
						System.out.println("La scelta del mezzo non è valida.");
					}
				} while (uscita != 0);

				System.out.println("Buon viaggio! WOOOOOO!!!");
				System.out.println("***Ricordarsi di convalidare la tessera sul mezzo!***");

				System.out.println("Salendo sul mezzo" + " " + mezzoSelezionatoPerTessera.getId());

				System.out.println("Vuoi timbrare la tessera sul mezzo selezionato? (S/N)");
				String confermaTessera = scanner.next();
				// TIMBRO TESSERA ⬇️⬇️⬇️
				if (confermaTessera.equalsIgnoreCase("S")) {

					VidimazioneAbbonamenti vidimazioneAbb = new VidimazioneAbbonamenti(abbonamento, utente,
							LocalDate.now());
					vabd.save(vidimazioneAbb);

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
}

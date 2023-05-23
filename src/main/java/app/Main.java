package app;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import dao.MezzoDAO;
import dao.PuntiVenditaDAO;
import dao.TrattaDAO;
import entities.Mezzo;
import entities.Mezzo.stato;
import entities.Mezzo.tipoMezzo;
import entities.Tratta;
import lombok.extern.slf4j.Slf4j;
import util.JpaUtil;

@Slf4j
public class Main {
	private static EntityManagerFactory emf = JpaUtil.getEntityManagerFactory();

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		EntityManager em = emf.createEntityManager();
		MezzoDAO md = new MezzoDAO(em);
		TrattaDAO td = new TrattaDAO(em);

		PuntiVenditaDAO pvd = new PuntiVenditaDAO(em);

		Tratta t1 = new Tratta("Roma", "Latina", 2.30);
//        td.save(t1);

		Tratta foundt1 = td.getById("10f49095-bb8c-4c3c-8206-656a8392577a");

		Mezzo m1 = new Mezzo(60, stato.inServizio, tipoMezzo.Autobus, foundt1);
		Mezzo m2 = new Mezzo(300, stato.inServizio, tipoMezzo.Tram, foundt1);
//        md.save(m1);
//        md.save(m2);
		// i 2 mezzi hanno la stessa tratta

		// quante volte è stata percorda una tratta
		log.info(" " + td.getTimesTrattaPercorsa("10f49095-bb8c-4c3c-8206-656a8392577a"));

		// quante volte è stata percorsa una tratta da un singolo Mezzo
		log.info(" " + td.getTimesTrattaPercorsaBySingleMezzo("10f49095-bb8c-4c3c-8206-656a8392577a",
				"b207b034-95f8-400c-99a1-ec80b45f5eb2"));

		// Per arrivare alla vidimazione del biglietto in step
		// Primo step : creare un punto vendita e salvarlo a db e trovare il suo id

	}

}

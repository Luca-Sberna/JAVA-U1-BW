package inter_face;

import entities.EmissioneAbbonamento;
import entities.EmissioneBiglietto;
import entities.Utente;

public interface Emissione {
	public EmissioneBiglietto emettiBiglietto(Utente utente);

	public EmissioneAbbonamento emettiAbbonamento(Utente utente);

}

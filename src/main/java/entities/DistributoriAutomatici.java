package entities;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class DistributoriAutomatici extends PuntiVendita {
	protected UUID idPuntoVendita;
	@Enumerated(EnumType.STRING)
	protected StatoDistributore stato;

	@OneToMany(mappedBy = "distributoreBi")
	private Set<EmissioneBiglietto> emissioneBiglietto;

	@OneToMany(mappedBy = "distributoreAb")
	private Set<EmissioneAbbonamento> emissioneAbbonamento;

	public enum StatoDistributore {
		FUNZIONANTE, FUORI_SERVIZIO
	}

	@ManyToOne
	@JoinColumn(name = "distributoriAutomatici")
	private PuntiVendita puntoVendita;

//	public DistributoriAutomatici(StatoDistributore stato) {
//		this.emissioneBiglietto = new DistributoriAutomatici(stato);
//		this.emissioneAbbonamento = new EmissioneAbbonamento();
//	}
	public DistributoriAutomatici(StatoDistributore stato) {
		this.idPuntoVendita = UUID.randomUUID();
		this.emissioneBiglietto = new HashSet<>();
		this.emissioneBiglietto.add(new EmissioneBiglietto());

		this.emissioneAbbonamento = new HashSet<>();
		this.emissioneAbbonamento.add(new EmissioneAbbonamento());

	}

	@Override
	public EmissioneBiglietto emettiBiglietto(Utente utente) {
		for (EmissioneBiglietto biglietto : emissioneBiglietto) {
			biglietto.emettiBiglietto(utente);
			return biglietto;
		}
		return null;
	}

	@Override
	public EmissioneAbbonamento emettiAbbonamento(Utente utente) {
		for (EmissioneAbbonamento abbonamento : emissioneAbbonamento) {
			abbonamento.emettiAbbonamento(utente);
			return abbonamento;
		}
		return null;
	}

	@Override
	public String toString() {
		return "DistributoriAutomatici [idPuntoVendita=" + idPuntoVendita
				+ ", stato=" + stato + ", emissioneBiglietto="
				+ emissioneBiglietto + ", emissioneAbbonamento="
				+ emissioneAbbonamento + ", puntoVendita=" + puntoVendita
				+ ", numeroVendite=" + numeroVendite + ", luogo=" + luogo
				+ ", getIdPuntoVendita()=" + getIdPuntoVendita()
				+ ", getStato()=" + getStato() + ", getEmissioneBiglietto()="
				+ getEmissioneBiglietto() + ", getEmissioneAbbonamento()="
				+ getEmissioneAbbonamento() + ", getPuntoVendita()="
				+ getPuntoVendita() + ", toString()=" + super.toString()
				+ ", getNumeroVendite()=" + getNumeroVendite() + ", getLuogo()="
				+ getLuogo() + ", getVenditori()=" + getVenditori()
				+ ", getDistibutori()=" + getDistibutori() + ", getClass()="
				+ getClass() + ", hashCode()=" + hashCode() + "]";
	}

	public DistributoriAutomatici(String luogo,
			StatoDistributore statoDistributore) {
		super(luogo);
		this.stato = statoDistributore;
	}

}

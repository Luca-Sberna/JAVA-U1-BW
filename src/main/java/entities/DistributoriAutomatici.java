package entities;

import java.util.Set;
import java.util.UUID;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

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
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "id_emissione_biglietto")
	private EmissioneBiglietto emissioneBiglietto;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "id_emissione_abbonamento")
	private EmissioneAbbonamento emissioneAbbonamento;

	public enum StatoDistributore {
		FUNZIONANTE, FUORI_SERVIZIO
	}

	@OneToMany(mappedBy = "distributoreAb")
	private Set<EmissioneAbbonamento> abbonamentiEmessi;

	@OneToMany(mappedBy = "distributoreBi")
	private Set<EmissioneBiglietto> bigliettiEmessi;

	@ManyToOne
	@JoinColumn(name = "distributoriAutomatici")
	private PuntiVendita puntoVendita;

//	public DistributoriAutomatici(StatoDistributore stato) {
//		this.emissioneBiglietto = new DistributoriAutomatici(stato);
//		this.emissioneAbbonamento = new EmissioneAbbonamento();
//	}
	public DistributoriAutomatici(StatoDistributore stato) {
		this.idPuntoVendita = UUID.randomUUID();
		this.emissioneBiglietto = new EmissioneBiglietto();
		this.emissioneAbbonamento = new EmissioneAbbonamento();
	}

	@Override
	public EmissioneBiglietto emettiBiglietto(Utente utente) {
		this.emissioneBiglietto.emettiBiglietto(utente);
		return emissioneBiglietto;
	}

	@Override
	public EmissioneAbbonamento emettiAbbonamento(Utente utente) {
		this.emissioneAbbonamento.emettiAbbonamento(utente);
		return emissioneAbbonamento;
	}

	@Override
	public String toString() {
		return "DistributoriAutomatici [idPuntoVendita=" + idPuntoVendita + ", stato=" + stato + ", emissioneBiglietto="
				+ emissioneBiglietto + ", emissioneAbbonamento=" + emissioneAbbonamento + ", abbonamentiEmessi="
				+ abbonamentiEmessi + ", bigliettiEmessi=" + bigliettiEmessi + ", puntoVendita=" + puntoVendita
				+ ", numeroVendite=" + numeroVendite + ", luogo=" + luogo + ", getIdPuntoVendita()="
				+ getIdPuntoVendita() + ", getStato()=" + getStato() + ", getEmissioneBiglietto()="
				+ getEmissioneBiglietto() + ", getEmissioneAbbonamento()=" + getEmissioneAbbonamento()
				+ ", getAbbonamentiEmessi()=" + getAbbonamentiEmessi() + ", getBigliettiEmessi()="
				+ getBigliettiEmessi() + ", getPuntoVendita()=" + getPuntoVendita() + ", toString()=" + super.toString()
				+ ", getNumeroVendite()=" + getNumeroVendite() + ", getLuogo()=" + getLuogo() + ", getVenditori()="
				+ getVenditori() + ", getDistibutori()=" + getDistibutori() + ", getClass()=" + getClass()
				+ ", hashCode()=" + hashCode() + "]";
	}

	public DistributoriAutomatici(String luogo) {
		super(luogo);
	}

}

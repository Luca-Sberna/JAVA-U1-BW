package entities;

import java.util.Set;
import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class DistributoriAutomatici extends PuntiVendita {
	protected UUID idPuntoVendita;
	@Enumerated(EnumType.STRING)
	protected TipoEvento stato;
	private EmissioneBiglietto emissioneBiglietto;
	private EmissioneAbbonamento emissioneAbbonamento;

	public enum TipoEvento {
		FUNZIONANTE, FUORI_SERVIZIO
	}

	@OneToMany(mappedBy = "distributoreAb")
	private Set<EmissioneAbbonamento> abbonamentiEmessi;

	@OneToMany(mappedBy = "distributoreBi")
	private Set<EmissioneBiglietto> bigliettiEmessi;

	@ManyToOne
	@JoinColumn(name = "distributoriAutomatici", nullable = false)
	private PuntiVendita PuntoVendita;

	public DistributoriAutomatici() {
		this.emissioneBiglietto = new EmissioneBiglietto();
		this.emissioneAbbonamento = new EmissioneAbbonamento();
	}

	@Override
	public void emettiBiglietto() {
		this.emissioneBiglietto.emettiBiglietto();
	}

	@Override
	public void emettiAbbonamento() {
		this.emissioneAbbonamento.emettiAbbonamento();
	}

	@Override
	public String toString() {
		return "DistributoriAutomatici [idPuntoVendita=" + idPuntoVendita
				+ ", stato=" + stato + ", emissioneBiglietto="
				+ emissioneBiglietto + ", emissioneAbbonamento="
				+ emissioneAbbonamento + ", numeroVendite=" + numeroVendite
				+ ", luogo=" + luogo + ", getIdPuntoVendita()="
				+ getIdPuntoVendita() + ", getStato()=" + getStato()
				+ ", getEmissioneBiglietto()=" + getEmissioneBiglietto()
				+ ", getEmissioneAbbonamento()=" + getEmissioneAbbonamento()
				+ ", toString()=" + super.toString() + ", getNumeroVendite()="
				+ getNumeroVendite() + ", getLuogo()=" + getLuogo()
				+ ", getClass()=" + getClass() + ", hashCode()=" + hashCode()
				+ "]";
	}

}

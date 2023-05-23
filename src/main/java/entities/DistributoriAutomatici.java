package entities;

import java.util.Set;

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
	@Enumerated(EnumType.STRING)
	protected TipoEvento stato;

	@OneToMany(mappedBy = "distributoreAb")
	private Set<EmissioneAbbonamento> abbonamentiEmessi;

	@OneToMany(mappedBy = "distributoreBi")
	private Set<EmissioneBiglietto> bigliettiEmessi;

	@ManyToOne
	@JoinColumn(name = "idPuntoVendite", nullable = false)
	private PuntiVendita puntoVendita;

	public DistributoriAutomatici() {
		super();
	}

	public enum TipoEvento {
		FUNZIONANTE, FUORI_SERVIZIO
	}

	@Override
	public void emettiBiglietto() {
	}

	@Override
	public void emettiAbbonamento() {
	}

	@Override
	public String toString() {
		return "DistributoriAutomatici [idPuntoVendita=" + idPuntoVendita
				+ ", stato=" + stato + ", numeroVendite=" + numeroVendite
				+ ", luogo=" + luogo + ", getClass()=" + getClass()
				+ ", hashCode()=" + hashCode() + ", toString()="
				+ super.toString() + "]";
	}

}

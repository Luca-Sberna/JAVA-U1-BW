package entities;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class VenditoriAutorizzati extends PuntiVendita {
	protected String nomeNegozio;
	protected String tipoDiNegozio;

	@ManyToOne
	@JoinColumn(name = "puntoVendita_id", nullable = false)
	private PuntiVendita puntoVendita;

	public VenditoriAutorizzati() {
		super();
	}

	@Override
	public void emettiBiglietto() {
	}

	@Override
	public void emettiAbbonamento() {
	}

	@Override
	public String toString() {
		return "VenditoriAutorizzati [idPuntoVendita=" + idPuntoVendita
				+ ", nomeNegozio=" + nomeNegozio + ", tipoDiNegozio="
				+ tipoDiNegozio + ", numeroVendite=" + numeroVendite
				+ ", luogo=" + luogo + ", getClass()=" + getClass()
				+ ", hashCode()=" + hashCode() + ", toString()="
				+ super.toString() + "]";
	}

}

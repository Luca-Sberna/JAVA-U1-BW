package entities;

import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter

public class VenditoriAutorizzati extends PuntiVendita {
	protected UUID idPuntoVendita;
	protected String nomeNegozio;
	protected String tipoDiNegozio;
	private EmissioneBiglietto emissioneBiglietto;
	private EmissioneAbbonamento emissioneAbbonamento;

	@ManyToOne
	@JoinColumn(name = "venditoriAutorizzati")
	private PuntiVendita PuntoVendita;

	public VenditoriAutorizzati() {
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
		return "VenditoriAutorizzati [idPuntoVendita=" + idPuntoVendita
				+ ", nomeNegozio=" + nomeNegozio + ", tipoDiNegozio="
				+ tipoDiNegozio + ", emissioneBiglietto=" + emissioneBiglietto
				+ ", emissioneAbbonamento=" + emissioneAbbonamento
				+ ", numeroVendite=" + numeroVendite + ", luogo=" + luogo
				+ ", getIdPuntoVendita()=" + getIdPuntoVendita()
				+ ", getNomeNegozio()=" + getNomeNegozio()
				+ ", getTipoDiNegozio()=" + getTipoDiNegozio()
				+ ", getEmissioneBiglietto()=" + getEmissioneBiglietto()
				+ ", getEmissioneAbbonamento()=" + getEmissioneAbbonamento()
				+ ", toString()=" + super.toString() + ", getNumeroVendite()="
				+ getNumeroVendite() + ", getLuogo()=" + getLuogo()
				+ ", getClass()=" + getClass() + ", hashCode()=" + hashCode()
				+ "]";
	}

}

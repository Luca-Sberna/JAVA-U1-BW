package entities;

import java.util.UUID;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class VenditoriAutorizzati extends PuntiVendita {
	protected UUID idPuntoVendita;
	protected String nomeNegozio;
	protected String tipoDiNegozio;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "id_emissione_biglietto")
	private EmissioneBiglietto emissioneBiglietto;
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "id_emissione_abbonamento")
	private EmissioneAbbonamento emissioneAbbonamento;

	@ManyToOne
	@JoinColumn(name = "venditoriAutorizzati")
	private PuntiVendita puntoVendita;

	public VenditoriAutorizzati(String nomeNegozio, String tipoDiNegozio) {
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
		return "VenditoriAutorizzati [idPuntoVendita=" + idPuntoVendita + ", nomeNegozio=" + nomeNegozio
				+ ", tipoDiNegozio=" + tipoDiNegozio + ", emissioneBiglietto=" + emissioneBiglietto
				+ ", emissioneAbbonamento=" + emissioneAbbonamento + ", numeroVendite=" + numeroVendite + ", luogo="
				+ luogo + ", getIdPuntoVendita()=" + getIdPuntoVendita() + ", getNomeNegozio()=" + getNomeNegozio()
				+ ", getTipoDiNegozio()=" + getTipoDiNegozio() + ", getEmissioneBiglietto()=" + getEmissioneBiglietto()
				+ ", getEmissioneAbbonamento()=" + getEmissioneAbbonamento() + ", toString()=" + super.toString()
				+ ", getNumeroVendite()=" + getNumeroVendite() + ", getLuogo()=" + getLuogo() + ", getClass()="
				+ getClass() + ", hashCode()=" + hashCode() + "]";
	}

}

package entities;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import javax.persistence.Entity;
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
public class VenditoriAutorizzati extends PuntiVendita {
	protected UUID idPuntoVendita;
	protected String nomeNegozio;
	protected String tipoDiNegozio;

	@OneToMany(mappedBy = "venditoreBi")
	private Set<EmissioneBiglietto> biglietti;
	@OneToMany(mappedBy = "distributoreAb")
	private Set<EmissioneAbbonamento> abbonamenti;

	@ManyToOne
	@JoinColumn(name = "venditoriAutorizzati")
	private PuntiVendita puntoVendita;

//	public VenditoriAutorizzati(String nomeNegozio, String tipoDiNegozio) {
//		this.emissioneBiglietto = new EmissioneBiglietto();
//		this.emissioneAbbonamento = new EmissioneAbbonamento();
//	}
//
//	@Override
//	public EmissioneBiglietto emettiBiglietto(Utente utente) {
//		this.emissioneBiglietto.emettiBiglietto(utente);
//		return emissioneBiglietto;
//	}
//
//	@Override
//	public EmissioneAbbonamento emettiAbbonamento(Utente utente) {
//		this.emissioneAbbonamento.emettiAbbonamento(utente);
//		return emissioneAbbonamento;
//	}
//
//	@Override
//	public String toString() {
//		return "VenditoriAutorizzati [idPuntoVendita=" + idPuntoVendita + ", nomeNegozio=" + nomeNegozio
//				+ ", tipoDiNegozio=" + tipoDiNegozio + ", emissioneBiglietto=" + emissioneBiglietto
//				+ ", emissioneAbbonamento=" + emissioneAbbonamento + ", numeroVendite=" + numeroVendite + ", luogo="
//				+ luogo + ", getIdPuntoVendita()=" + getIdPuntoVendita() + ", getNomeNegozio()=" + getNomeNegozio()
//				+ ", getTipoDiNegozio()=" + getTipoDiNegozio() + ", getEmissioneBiglietto()=" + getEmissioneBiglietto()
//				+ ", getEmissioneAbbonamento()=" + getEmissioneAbbonamento() + ", toString()=" + super.toString()
//				+ ", getNumeroVendite()=" + getNumeroVendite() + ", getLuogo()=" + getLuogo() + ", getClass()="
//				+ getClass() + ", hashCode()=" + hashCode() + "]";
//	}

	public VenditoriAutorizzati(String nomeNegozio, String tipoDiNegozio) {
		this.idPuntoVendita = UUID.randomUUID();
		this.biglietti = new HashSet<>();
		this.biglietti.add(new EmissioneBiglietto());

		this.abbonamenti = new HashSet<>();
		this.abbonamenti.add(new EmissioneAbbonamento());
		this.nomeNegozio = nomeNegozio;
		this.tipoDiNegozio = tipoDiNegozio;

	}

	@Override
	public EmissioneBiglietto emettiBiglietto(Utente utente) {
		for (EmissioneBiglietto biglietto : biglietti) {
			biglietto.emettiBiglietto(utente);
			return biglietto;
		}
		return null;
	}

	@Override
	public EmissioneAbbonamento emettiAbbonamento(Utente utente) {
		for (EmissioneAbbonamento abbonamento : abbonamenti) {
			abbonamento.emettiAbbonamento(utente);
			return abbonamento;
		}
		return null;
	}

}

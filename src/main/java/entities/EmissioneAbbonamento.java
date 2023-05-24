package entities;

import java.time.LocalDate;
import java.util.Set;
import java.util.UUID;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@Getter
@Setter
public class EmissioneAbbonamento {
	@Id
	@GeneratedValue
	protected UUID idEmissione;
	protected UUID idPuntoVendita;
	protected LocalDate dataEmissione;
	protected LocalDate dataScadenza;
	@Enumerated(EnumType.STRING)
	protected TipoEvento tipoAbbonamento;

	public enum TipoEvento {
		SETTIMANALE, MENSILE
	}

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "numeroTessera", referencedColumnName = "numeroTessera")
	private Tessera numeroTessera;

	@ManyToOne
	@JoinColumn(name = "AbbonamentoEmessoDis")
	private DistributoriAutomatici distributoreAb;

	@ManyToOne
	@JoinColumn(name = "abbonamentoEmessoVen")
	private VenditoriAutorizzati venditoriAb;

	@OneToMany(mappedBy = "bigliettoVidimato")
	private Set<VidimazioneBiglietti> vidimazioni;

	public EmissioneAbbonamento emettiAbbonamento(Utente utente) {
		// Create a new EmissioneAbbonamento object
		EmissioneAbbonamento abbonamento = new EmissioneAbbonamento();

		// Set the properties of the new EmissioneAbbonamento object
		abbonamento.setIdEmissione(UUID.randomUUID());
		abbonamento.setIdPuntoVendita(this.idPuntoVendita);
		abbonamento.setDataEmissione(LocalDate.now());

		// Set the dataScadenzaAbbonamento property based on the desired
		// TipoEvento
		if (this.tipoAbbonamento == TipoEvento.SETTIMANALE) {
			abbonamento
					.setDataScadenzaAbbonamento(LocalDate.now().plusWeeks(1));
		} else if (this.tipoAbbonamento == TipoEvento.MENSILE) {
			abbonamento
					.setDataScadenzaAbbonamento(LocalDate.now().plusMonths(1));
		}

		return abbonamento;
	}

	private void setDataScadenzaAbbonamento(LocalDate dataScadenzaAbbonamento) {

	}

	@Override
	public String toString() {
		return "EmissioneAbbonamento [idEmissione=" + idEmissione
				+ ", numeroTessera=" + numeroTessera + ", idPuntoVendita="
				+ idPuntoVendita + ", dataEmissione=" + dataEmissione
				+ ", dataScadenzaAbbonamento=" + tipoAbbonamento
				+ ", getIdEmissione()=" + getIdEmissione()
				+ ", getNumeroTessera()=" + getNumeroTessera()
				+ ", getIdPuntoVendita()=" + getIdPuntoVendita()
				+ ", getDataEmissione()=" + getDataEmissione()
				+ ", getDataScadenzaAbbonamento()=" + getTipoAbbonamento()
				+ ", getClass()=" + getClass() + ", hashCode()=" + hashCode()
				+ ", toString()=" + super.toString() + "]";
	}

	public EmissioneAbbonamento(LocalDate dataEmissione, LocalDate dataScadenza,
			TipoEvento tipoAbbonamento, Tessera numeroTessera) {
		super();
		this.dataEmissione = dataEmissione;
		this.dataScadenza = dataScadenza;
		this.tipoAbbonamento = tipoAbbonamento;
		this.numeroTessera = numeroTessera;
	}

}

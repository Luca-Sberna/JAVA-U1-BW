package entities;

import java.time.LocalDate;
import java.util.Set;
import java.util.UUID;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
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
//	@GeneratedValue
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
	private Tessera tessera;

	@ManyToOne
	@JoinColumn(name = "abbonamentoEmesso")
	private DistributoriAutomatici distributoreAb;

	@OneToMany(mappedBy = "bigliettoVidimato")
	private Set<VidimazioneBiglietti> vidimazioni;

	public EmissioneAbbonamento(LocalDate dataEmissione, LocalDate dataScadenza, TipoEvento tipoAbbonamento,
			Tessera tessera) {
		super();
		this.dataEmissione = dataEmissione;
		this.dataScadenza = dataScadenza;
		this.tipoAbbonamento = tipoAbbonamento;
		this.tessera = tessera;
	}

	public EmissioneAbbonamento emettiAbbonamento() {
		// Create a new EmissioneAbbonamento object
		EmissioneAbbonamento abbonamento = new EmissioneAbbonamento();

		// Set the properties of the new EmissioneAbbonamento object
		abbonamento.setIdEmissione(UUID.randomUUID());
		abbonamento.setIdPuntoVendita(this.idPuntoVendita);
		abbonamento.setDataEmissione(LocalDate.now());

		// Set the dataScadenzaAbbonamento property based on the desired TipoEvento
		if (this.tipoAbbonamento == TipoEvento.SETTIMANALE) {
			abbonamento.setDataScadenzaAbbonamento(LocalDate.now().plusWeeks(1));
		} else if (this.tipoAbbonamento == TipoEvento.MENSILE) {
			abbonamento.setDataScadenzaAbbonamento(LocalDate.now().plusMonths(1));
		}

		return abbonamento;
	}
//		EmissioneAbbonamento abbonamento = new EmissioneAbbonamento(this.dataEmissione, this.dataScadenza,
//				this.tipoAbbonamento, this.tessera);
//
//		// Imposta la nuova data di emissione
//		abbonamento.setDataEmissione(LocalDate.now());
//
//		// Imposta la nuova data di scadenza in base al tipo di abbonamento
//		if (this.tipoAbbonamento == TipoEvento.SETTIMANALE) {
//			abbonamento.setDataScadenza(LocalDate.now().plusWeeks(1));
//		} else if (this.tipoAbbonamento == TipoEvento.MENSILE) {
//			abbonamento.setDataScadenza(LocalDate.now().plusMonths(1));
//		}
//
//		// Assegna un nuovo UUID all'entità
//		abbonamento.setIdEmissione(UUID.randomUUID());
//
//		// Restituisci la nuova entità
//		return abbonamento;
//	}

	private void setDataScadenzaAbbonamento(LocalDate dataScadenzaAbbonamento) {

	}

	@Override
	public String toString() {
		return "EmissioneAbbonamento [idEmissione=" + idEmissione + ", idPuntoVendita=" + idPuntoVendita
				+ ", dataEmissione=" + dataEmissione + ", dataScadenza=" + dataScadenza + ", tipoAbbonamento="
				+ tipoAbbonamento + ", tessera=" + tessera + ", distributoreAb=" + distributoreAb + ", vidimazioni="
				+ vidimazioni + ", getIdEmissione()=" + getIdEmissione() + ", getIdPuntoVendita()="
				+ getIdPuntoVendita() + ", getDataEmissione()=" + getDataEmissione() + ", getDataScadenza()="
				+ getDataScadenza() + ", getTipoAbbonamento()=" + getTipoAbbonamento() + ", getTessera()="
				+ getTessera() + ", getDistributoreAb()=" + getDistributoreAb() + ", getVidimazioni()="
				+ getVidimazioni() + ", getClass()=" + getClass() + ", hashCode()=" + hashCode() + ", toString()="
				+ super.toString() + "]";
	}

}

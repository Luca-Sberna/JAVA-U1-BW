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

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class EmissioneAbbonamento {
	@Id
	protected UUID idEmissione;
	protected UUID idPuntoVendita;
	protected LocalDate dataEmissione;
	@Enumerated(EnumType.STRING)
	protected TipoEvento dataScadenzaAbbonamento;

	public enum TipoEvento {
		SETTIMANALE, MENSILE
	}

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "numeroTessera", referencedColumnName = "numeroTessera")
	private Tessera numeroTessera;

	@ManyToOne
	@JoinColumn(name = "abbonamentoEmesso")
	private DistributoriAutomatici distributoreAb;

	@OneToMany(mappedBy = "bigliettoVidimato")
	private Set<VidimazioneBiglietti> vidimazioni;

	public void emettiAbbonamento() {
		this.emettiAbbonamento();
	}

	@Override
	public String toString() {
		return "EmissioneAbbonamento [idEmissione=" + idEmissione
				+ ", numeroTessera=" + numeroTessera + ", idPuntoVendita="
				+ idPuntoVendita + ", dataEmissione=" + dataEmissione
				+ ", dataScadenzaAbbonamento=" + dataScadenzaAbbonamento
				+ ", getIdEmissione()=" + getIdEmissione()
				+ ", getNumeroTessera()=" + getNumeroTessera()
				+ ", getIdPuntoVendita()=" + getIdPuntoVendita()
				+ ", getDataEmissione()=" + getDataEmissione()
				+ ", getDataScadenzaAbbonamento()="
				+ getDataScadenzaAbbonamento() + ", getClass()=" + getClass()
				+ ", hashCode()=" + hashCode() + ", toString()="
				+ super.toString() + "]";
	}

}

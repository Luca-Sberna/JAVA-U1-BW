package entities;

import java.time.LocalDate;
import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;

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
	protected UUID numeroTessera;
	protected UUID idPuntoVendita;
	protected LocalDate dataEmissione;
	@Enumerated(EnumType.STRING)
	protected TipoEvento dataScadenzaAbbonamento;

	public enum TipoEvento {
		SETTIMANALE, MENSILE
	}

	public void emettiAbbonamento() {
		this.emettiAbbonamento();
	}

	@Override
	public String toString() {
		return "EmissioneAbbonamento [idEmissione=" + idEmissione + ", numeroTessera=" + numeroTessera
				+ ", idPuntoVendita=" + idPuntoVendita + ", dataEmissione=" + dataEmissione
				+ ", dataScadenzaAbbonamento=" + dataScadenzaAbbonamento + ", getIdEmissione()=" + getIdEmissione()
				+ ", getNumeroTessera()=" + getNumeroTessera() + ", getIdPuntoVendita()=" + getIdPuntoVendita()
				+ ", getDataEmissione()=" + getDataEmissione() + ", getDataScadenzaAbbonamento()="
				+ getDataScadenzaAbbonamento() + ", getClass()=" + getClass() + ", hashCode()=" + hashCode()
				+ ", toString()=" + super.toString() + "]";
	}

}

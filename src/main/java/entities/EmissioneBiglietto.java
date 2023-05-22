package entities;

import java.time.LocalDate;
import java.util.UUID;

import javax.persistence.Entity;
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
public class EmissioneBiglietto {
	@Id
	protected UUID idEmissione;
	protected UUID idBiglietto;
	protected UUID idPuntoVendita;
	protected LocalDate dataEmissione;
	protected UUID idUtente;

	public void emettiBiglietto() {
		this.emettiBiglietto();
	}

	@Override
	public String toString() {
		return "EmissioneBiglietto [idEmissione=" + idEmissione + ", idBiglietto=" + idBiglietto + ", idPuntoVendita="
				+ idPuntoVendita + ", dataEmissione=" + dataEmissione + ", getIdEmissione()=" + getIdEmissione()
				+ ", getIdBiglietto()=" + getIdBiglietto() + ", getIdPuntoVendita()=" + getIdPuntoVendita()
				+ ", getDataEmissione()=" + getDataEmissione() + ", getClass()=" + getClass() + ", hashCode()="
				+ hashCode() + ", toString()=" + super.toString() + "]";
	}

}

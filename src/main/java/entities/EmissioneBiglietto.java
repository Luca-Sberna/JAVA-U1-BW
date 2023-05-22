package entities;

import java.time.LocalDate;
import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@Getter
@Setter
public class EmissioneBiglietto {
	@Id
	@GeneratedValue
	private UUID idEmissione;
	private LocalDate dataEmissione;

	@ManyToOne
	@JoinColumn(name = "utenti")
	private Utente utente;

	@ManyToOne
	@JoinColumn(name = "IdPuntoVendita")
	private PuntiVendita IdPuntoVendita;

	public void emettiBiglietto() {
		this.emettiBiglietto();
	}

	@Override
	public String toString() {
		return "EmissioneBiglietti [idEmissione=" + idEmissione + ", dataEmissione=" + dataEmissione + ", utente="
				+ utente + ", getIdEmissione()=" + getIdEmissione() + ", getDataEmissione()=" + getDataEmissione()
				+ ", getUtente()=" + getUtente() + ", getClass()=" + getClass() + ", hashCode()=" + hashCode()
				+ ", toString()=" + super.toString() + "]";
	}

}

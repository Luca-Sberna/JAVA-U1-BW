package entities;

import java.time.LocalDate;
import java.util.UUID;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
public class EmissioneBiglietto {
	@Id
	@GeneratedValue
	private UUID idEmissione;
	private LocalDate dataEmissione;
	protected UUID idBiglietto;
	protected UUID idPuntoVendita;

	public void emettiBiglietto() {
		this.emettiBiglietto();
	}

	@ManyToOne
	@JoinColumn(name = "utenti")
	private Utente utente;

	@ManyToOne
	@JoinColumn(name = "IdPuntoVendita")
	private PuntiVendita IdPuntoVendita;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "idBiglietto", referencedColumnName = "idBiglietto")
	private VidimazioneBiglietto vidimazione;

	@ManyToOne
	@JoinColumn(name = "bigliettoEmesso")
	private DistributoriAutomatici distributoreBi;

	@Override
	public String toString() {
		return "EmissioneBiglietto [idEmissione=" + idEmissione
				+ ", dataEmissione=" + dataEmissione + ", idBiglietto="
				+ idBiglietto + ", idPuntoVendita=" + idPuntoVendita
				+ ", utente=" + utente + ", getIdEmissione()="
				+ getIdEmissione() + ", getDataEmissione()="
				+ getDataEmissione() + ", getIdBiglietto()=" + getIdBiglietto()
				+ ", getIdPuntoVendita()=" + getIdPuntoVendita()
				+ ", getUtente()=" + getUtente() + ", getClass()=" + getClass()
				+ ", hashCode()=" + hashCode() + ", toString()="
				+ super.toString() + "]";
	}

}

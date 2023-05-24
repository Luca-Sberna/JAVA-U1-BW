package entities;

import java.time.LocalDate;
import java.util.UUID;

import javax.persistence.CascadeType;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@Getter
@Setter
public class EmissioneBiglietto {
	@Id
	@EmbeddedId
	private EmissioneBigliettoId id;

	private UUID idEmissione;

	private LocalDate dataEmissione;
//	protected UUID idBiglietto;
//	protected UUID idPuntoVendita;

	public EmissioneBiglietto emettiBiglietto(Utente utente) {
		EmissioneBiglietto biglietto = new EmissioneBiglietto();

		biglietto.setIdPuntoVendita(this.IdPuntoVendita);
		biglietto.setDataEmissione(LocalDate.now());
		biglietto.setUtente(utente);

		return biglietto;
	}

	@ManyToOne
	@JoinColumn(name = "utenti")
	private Utente utente;

	@ManyToOne
	@JoinColumn(name = "IdPuntoVendita")
	private PuntiVendita IdPuntoVendita;

	@ManyToOne
	@JoinColumn(name = "bigliettoEmessoV")
	private VenditoriAutorizzati venditoreBi;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "id", referencedColumnName = "id")
	private VidimazioneBiglietti vidimazione;

	@ManyToOne
	@JoinColumn(name = "bigliettoEmessoD")
	private DistributoriAutomatici distributoreBi;

	public EmissioneBiglietto(LocalDate dataEmissione, Utente utente,
			VidimazioneBiglietti vidimazione,
			DistributoriAutomatici distributoreBi) {
		super();
		this.dataEmissione = dataEmissione;
		this.utente = utente;
		this.vidimazione = vidimazione;
		this.distributoreBi = distributoreBi;
	}

	public EmissioneBiglietto(LocalDate dataEmissione, Utente utente,
			PuntiVendita idPuntoVendita) {
		super();
		this.dataEmissione = dataEmissione;
		this.utente = utente;
		IdPuntoVendita = idPuntoVendita;
		idEmissione = UUID.randomUUID();

	}

	@Override
	public String toString() {
		return "EmissioneBiglietto [id=" + id + ", dataEmissione="
				+ dataEmissione + ", utente=" + utente + ", IdPuntoVendita="
				+ IdPuntoVendita + ", vidimazione=" + vidimazione
				+ ", distributoreBi=" + distributoreBi + ", getId()=" + getId()
				+ ", getDataEmissione()=" + getDataEmissione()
				+ ", getUtente()=" + getUtente() + ", getIdPuntoVendita()="
				+ getIdPuntoVendita() + ", getVidimazione()=" + getVidimazione()
				+ ", getDistributoreBi()=" + getDistributoreBi()
				+ ", getClass()=" + getClass() + ", hashCode()=" + hashCode()
				+ ", toString()=" + super.toString() + "]";
	}

}

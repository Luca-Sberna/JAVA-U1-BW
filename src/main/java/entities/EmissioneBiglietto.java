package entities;

import java.time.LocalDate;
import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "biglietti")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class EmissioneBiglietto {
	@Id
	@GeneratedValue
	protected UUID idBiglietto;
//	protected UUID idBiglietto;
	protected UUID idDistributoriAutomatici;
	protected UUID idVenditoriAutorizzati;
	protected LocalDate dataEmissione;

	public void emettiBiglietto() {
		this.emettiBiglietto();
	}

	@Override
	public String toString() {
		return "EmissioneBiglietto [idBiglietto=" + idBiglietto + ", idDistributoriAutomatici="
				+ idDistributoriAutomatici + ", idVenditoriAutorizzati=" + idVenditoriAutorizzati + ", dataEmissione="
				+ dataEmissione + "]";
	}

	public EmissioneBiglietto(UUID idDistributoriAutomatici, LocalDate dataEmissione) {
		super();
		this.idDistributoriAutomatici = idDistributoriAutomatici;
		this.dataEmissione = dataEmissione;
	}

	public EmissioneBiglietto(UUID idDistributoriAutomatici, UUID idVenditoriAutorizzati, LocalDate dataEmissione) {
		super();
		this.idDistributoriAutomatici = idDistributoriAutomatici;
		this.idVenditoriAutorizzati = idVenditoriAutorizzati;
		this.dataEmissione = dataEmissione;
	}

}

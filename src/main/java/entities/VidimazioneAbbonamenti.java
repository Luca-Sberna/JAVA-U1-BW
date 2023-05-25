package entities;

import java.time.LocalDate;
import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "abbonamenti_vidimati")
@Getter
@Setter
@NoArgsConstructor
@ToString
public class VidimazioneAbbonamenti {
	@Id
	@GeneratedValue
	private UUID id;

	@ManyToOne
	@JoinColumn(name = "utenti")
	private Utente utente;

	private LocalDate dataVidimazione;

	@ManyToOne
	@JoinColumn(name = "id_abbonamento")
	private EmissioneAbbonamento abbonamentoVidimato;

	public VidimazioneAbbonamenti(EmissioneAbbonamento abbonamentoVidimato, Utente utente, LocalDate dataVidimazione) {
		super();
		this.abbonamentoVidimato = abbonamentoVidimato;
		this.utente = utente;
		this.dataVidimazione = dataVidimazione;
	}

}
package entities;

import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "tratte")
@Getter
@Setter
@NoArgsConstructor
@ToString
public class Tratta {
	@Id
	@GeneratedValue
	private UUID id;
	private String zonaPartenza;
	private String capolinea;
	private Double tempoMedioTratta;
	private Double lunghezzaTratta;

	public Tratta(String zonaPartenza, String capolinea, double tempoMedioTratta, double lunghezzaTratta) {
		super();
		this.zonaPartenza = zonaPartenza;
		this.capolinea = capolinea;
		this.tempoMedioTratta = tempoMedioTratta;
		this.lunghezzaTratta = lunghezzaTratta;
	}
}

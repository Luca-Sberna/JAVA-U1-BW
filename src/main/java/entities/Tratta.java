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
import lombok.extern.slf4j.Slf4j;

@Entity
@Table(name = "tratte")

@Getter
@Setter
@NoArgsConstructor
@ToString
@Slf4j
public class Tratta {
	@Id
	@GeneratedValue
	private UUID id;
	private String zonaPartenza;
	private String capolinea;
	private double tempoMedioTratta;

	public Tratta(String zonaPartenza, String capolinea, double tempoMedioTratta) {
		super();
		this.zonaPartenza = zonaPartenza;
		this.capolinea = capolinea;
		this.tempoMedioTratta = tempoMedioTratta;
	}

}

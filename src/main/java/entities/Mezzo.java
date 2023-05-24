package entities;

import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

@Entity
@Table(name = "mezzi_di_trasporto")
@Inheritance(strategy = InheritanceType.JOINED)
@Getter
@Setter
@NoArgsConstructor
@ToString
@Slf4j

public class Mezzo {
	@Id
	@GeneratedValue
	private UUID id;
	private long capienza;
	@Enumerated(EnumType.STRING)
	private statoMezzo stato;
	@Enumerated(EnumType.STRING)
	private tipoMezzo tipoMezzo;
	@ManyToOne
	@JoinColumn(name = "tratta_id")
	private Tratta tratta;

	private Integer velocitàMedia;
//	private List<PeriodiDiServizio> periodiDiServizio;

	public enum statoMezzo {
		inServizio, inManutenzione
	}

	public enum tipoMezzo {
		Autobus, Tram
	}

	public Mezzo(long capienza, statoMezzo stato, tipoMezzo tipoMezzo, Tratta tratta) {
		super();
		this.capienza = capienza;
		this.stato = stato;
		this.tipoMezzo = tipoMezzo;
		this.tratta = tratta;
	}

}

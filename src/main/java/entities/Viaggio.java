package entities;

import java.time.LocalDateTime;
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

@Entity
@Table(name = "viaggi")
@Getter
@Setter
@NoArgsConstructor
public class Viaggio {
	@Id
	@GeneratedValue
	private UUID id;

	@ManyToOne
	@JoinColumn(name = "mezzo_id")
	private Mezzo mezzo;

	@ManyToOne
	@JoinColumn(name = "tratta_id")
	private Tratta tratta;

	private LocalDateTime orarioPartenza;
	private LocalDateTime orarioArrivo;

}

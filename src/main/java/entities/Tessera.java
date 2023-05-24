package entities;

import java.time.LocalDate;
import java.util.Set;
import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "tessere")
public class Tessera {
	@Id
	@GeneratedValue
	private UUID numeroTessera;

	@ManyToOne
	@JoinColumn(name = "proprietario")
	private Utente proprietario;
	private LocalDate dataEmissioneTessera;
	private LocalDate dataScadenzaTessera;

	@OneToMany(mappedBy = "numeroTessera")
	private Set<Utente> utentiTessera;

	public Tessera(Utente proprietario, LocalDate dataEmissioneTessera) {

		this.proprietario = proprietario;
		this.dataScadenzaTessera = dataEmissioneTessera.plusDays(365);
		this.dataEmissioneTessera = dataEmissioneTessera;
	}

	@Override
	public String toString() {
		return "Tessera [numeroTessera=" + numeroTessera + ", proprietario=" + proprietario + ", dataScadenzaTessera="
				+ dataScadenzaTessera + ", dataEmissioneTessera=" + dataEmissioneTessera + "]";
	}

	public Tessera(Utente proprietario, LocalDate dataScadenzaTessera, LocalDate dataEmissioneTessera) {
		super();
		this.proprietario = proprietario;
		this.dataEmissioneTessera = dataEmissioneTessera;
		this.dataScadenzaTessera = dataScadenzaTessera;
	}

}

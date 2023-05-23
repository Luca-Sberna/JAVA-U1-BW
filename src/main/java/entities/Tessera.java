package entities;

import java.time.LocalDate;
import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
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
	private Utente proprietario;
	private LocalDate dataScadenzaTessera;
	private LocalDate dataEmissioneTessera;

	public Tessera(Utente proprietario, LocalDate dataEmissioneTessera) {

		this.proprietario = proprietario;
		this.dataScadenzaTessera = dataEmissioneTessera.plusDays(365);
		this.dataEmissioneTessera = dataEmissioneTessera;
	}

	@Override
	public String toString() {
		return "Tessera [numeroTessera=" + numeroTessera + ", proprietario="
				+ proprietario + ", dataScadenzaTessera=" + dataScadenzaTessera
				+ ", dataEmissioneTessera=" + dataEmissioneTessera + "]";
	}

}
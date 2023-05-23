package entities;

import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.Id;

import inter_face.Emissione;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter

public abstract class PuntiVendita implements Emissione {
	@Id
	protected UUID idPuntoVendita;
	protected Integer numeroVendite;
	protected String luogo;

	@Override
	public String toString() {
		return "PuntiVendita [idPuntoVendita=" + idPuntoVendita + ", numeroVendite=" + numeroVendite + ", luogo="
				+ luogo + ", getIdPuntoVendita()=" + getIdPuntoVendita() + ", getNumeroVendite()=" + getNumeroVendite()
				+ ", getLuogo()=" + getLuogo() + ", getClass()=" + getClass() + ", hashCode()=" + hashCode()
				+ ", toString()=" + super.toString() + "]";
	}

	public PuntiVendita(Integer numeroVendite, String luogo) {
		super();
		this.numeroVendite = numeroVendite;
		this.luogo = luogo;
	}
}

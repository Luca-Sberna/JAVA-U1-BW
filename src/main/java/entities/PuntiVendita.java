package entities;

import java.util.Set;
import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

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
	@GeneratedValue
	protected UUID idPuntoVendita;
	protected Integer numeroVendite;
	protected String luogo;

	@OneToMany(mappedBy = "puntoVendita")
	private Set<VenditoriAutorizzati> venditori;

	@OneToMany(mappedBy = "puntoVendita")
	private Set<VenditoriAutorizzati> distibutori;

	@Override
	public String toString() {
		return "PuntiVendita [idPuntoVendita=" + idPuntoVendita + ", numeroVendite=" + numeroVendite + ", luogo="
				+ luogo + ", getIdPuntoVendita()=" + getIdPuntoVendita() + ", getNumeroVendite()=" + getNumeroVendite()
				+ ", getLuogo()=" + getLuogo() + ", getClass()=" + getClass() + ", hashCode()=" + hashCode()
				+ ", toString()=" + super.toString() + "]";
	}
}

package entities;

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
@Table(name = "utenti")

public class Utente {
	@Id
	@GeneratedValue
	private UUID idUtente;
	private String nome;
	private String cognome;

	@OneToMany(mappedBy = "utente")
	private Set<EmissioneBiglietto> biglietti;

	@ManyToOne
	@JoinColumn(name = "numeroTessera")
	private Tessera numeroTessera;

	public Utente(String nome, String cognome) {
		this.nome = nome;
		this.cognome = cognome;
	}

//	@Override
//	public String toString() {
//		return "Utente [idUtente=" + idUtente + ", nome=" + nome + ", cognome=" + cognome + ", biglietti=" + biglietti
//				+ "]";
//	}

}

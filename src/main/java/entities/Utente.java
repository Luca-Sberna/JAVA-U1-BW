package entities;

import java.util.Set;
import java.util.UUID;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
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

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "numeroTessera", referencedColumnName = "numeroTessera")
	private Tessera numeroTessera;

	@OneToMany(mappedBy = "utente")
	private Set<EmissioneBiglietti> biglietti;

	public Utente(String nome, String cognome) {
		this.nome = nome;
		this.cognome = cognome;
	}

}

package entities;

import java.io.Serializable;
import java.util.UUID;

import javax.persistence.Embeddable;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Embeddable
public class EmissioneBigliettoId implements Serializable {
	private static final long serialVersionUID = 1L;
	private UUID idBigliettoEmesso;

}
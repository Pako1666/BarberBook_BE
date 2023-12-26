package barberbook.app.bbbe.rest.conto.repository;

import barberbook.app.bbbe.rest.utente.repository.UtenteBean;
import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@EqualsAndHashCode
@Entity
@Table(name = "conto_corrente")
public class ContoBean {

    @Id
    private String id;

    @Column(name ="scadenza")
    private Date scadenza;

    @Column(name = "num_carta")
    private String numCarta;

    @Column(name = "cv")
    private String cv;

    @Column(name = "iban")
    private String iban;

    @Column(name = "ente")
    private String ente;

    @OneToOne(mappedBy = "conto")
    @PrimaryKeyJoinColumn(name = "idutente")
    private UtenteBean intestatario;

}

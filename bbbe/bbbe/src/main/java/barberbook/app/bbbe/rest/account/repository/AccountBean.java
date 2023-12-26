package barberbook.app.bbbe.rest.account.repository;

import barberbook.app.bbbe.rest.account.AccountController;
import barberbook.app.bbbe.rest.account.RuoloEnum;
import barberbook.app.bbbe.rest.attivita.repository.AttivitaBean;
import barberbook.app.bbbe.rest.utente.repository.UtenteBean;
import com.fasterxml.jackson.databind.annotation.EnumNaming;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@EqualsAndHashCode
@Entity
@Table(name = "account")
public class AccountBean {

    @Id
    private String id;

    @Column(name = "username")
    private String username;

    @Column(name = "password")
    private String password;

    @Column(name = "ruolo")
    @Enumerated(EnumType.STRING)
    private RuoloEnum ruolo;

    @ManyToOne
    @JoinColumn(name = "utente", referencedColumnName = "id")
    private UtenteBean utente;

    @ManyToMany(cascade = { CascadeType.ALL })
    @JoinTable(
            name = "accounts_attivita",
            joinColumns = { @JoinColumn(name = "idacc") },
            inverseJoinColumns = { @JoinColumn(name = "idattivita") }
    )
    private List<AttivitaBean> allAttivita;
}

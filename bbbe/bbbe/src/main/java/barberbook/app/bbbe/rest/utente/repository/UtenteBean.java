package barberbook.app.bbbe.rest.utente.repository;

import barberbook.app.bbbe.rest.account.repository.AccountBean;
import barberbook.app.bbbe.rest.conto.repository.ContoBean;
import jakarta.persistence.*;
import lombok.*;

import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@EqualsAndHashCode
@Table(name = "utente")
public class UtenteBean {


    @Id
    private String id;

    @Column(name = "nome")
    private String nome;

    @Column(name = "cognome")
    private String cognome;

    @Column(name = "email")
    private String email;

    @Column(name = "birth_date")
    private Date birthDate;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_conto_corrente", referencedColumnName = "id")
    private ContoBean conto;

    @OneToMany(mappedBy = "utente")
    private List<AccountBean> accounts;

}

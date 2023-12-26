package barberbook.app.bbbe.rest.attivita.repository;

import barberbook.app.bbbe.rest.account.repository.AccountBean;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Entity
@Table(name="attività")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AttivitaBean {

    @Id
    private String id;

    @Column(name="nome")
    private String nome;

    @Column(name="esonerata")
    private Boolean esonerata;

    @Column(name="data_iscrizione")
    private Date dataIscrizione;

    @Column(name = "proprietario")
    private String idProprietario;

    @ManyToMany(mappedBy = "allAttivita")
    private List<AccountBean> accounts;

}

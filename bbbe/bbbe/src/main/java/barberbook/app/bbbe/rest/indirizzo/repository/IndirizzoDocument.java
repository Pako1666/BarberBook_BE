package barberbook.app.bbbe.rest.indirizzo.repository;

import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document("indirizzo")
public class IndirizzoDocument {

    @Id
    private String id;
    private String paese = "IT";
    private String regione;
    private String provincia;
    private String comune;
    private Long cap;
    private String via;
    private Integer nCivico;


}

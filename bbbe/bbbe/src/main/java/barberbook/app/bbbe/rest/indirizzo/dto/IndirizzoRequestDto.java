package barberbook.app.bbbe.rest.indirizzo.dto;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Builder
public class IndirizzoRequestDto {
    private String paese;
    private String regione;
    private String provincia;
    private String comune;
    private Long cap;
    private String via;
    private Integer nCivico;
}

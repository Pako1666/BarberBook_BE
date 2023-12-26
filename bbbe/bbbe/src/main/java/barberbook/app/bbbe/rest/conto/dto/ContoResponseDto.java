package barberbook.app.bbbe.rest.conto.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ContoResponseDto {
    private String id;
    private String scadenza;
    private String numCarta;
    private String cv;
    private String iban;
    private String ente;
}

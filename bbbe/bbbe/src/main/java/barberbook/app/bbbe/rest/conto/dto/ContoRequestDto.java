package barberbook.app.bbbe.rest.conto.dto;

import lombok.Data;

import java.util.Date;

@Data
public class ContoRequestDto {
    private String iban;
    private String cv;
    private String ente;
    private String numCarta;
    private Date scadenza;
}

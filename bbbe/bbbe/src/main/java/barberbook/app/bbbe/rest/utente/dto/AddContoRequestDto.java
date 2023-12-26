package barberbook.app.bbbe.rest.utente.dto;

import barberbook.app.bbbe.rest.conto.dto.ContoRequestDto;
import barberbook.app.bbbe.rest.conto.dto.ContoResponseDto;
import lombok.Data;

@Data
public class AddContoRequestDto  {
    private String userId;
    private String iban;
    private String cv;
    private String ente;
    private String numCarta;
}

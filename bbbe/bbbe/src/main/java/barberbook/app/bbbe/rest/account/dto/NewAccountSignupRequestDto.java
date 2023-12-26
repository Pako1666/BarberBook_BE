package barberbook.app.bbbe.rest.account.dto;

import barberbook.app.bbbe.rest.account.RuoloEnum;
import barberbook.app.bbbe.rest.utente.dto.UtenteRequestDto;
import lombok.Data;

@Data
public class NewAccountSignupRequestDto extends AccountSignupRequestDto {
    private UtenteRequestDto utenteRequestDto;
}

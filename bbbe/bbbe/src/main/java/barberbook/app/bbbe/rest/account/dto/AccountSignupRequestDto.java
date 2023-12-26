package barberbook.app.bbbe.rest.account.dto;

import barberbook.app.bbbe.rest.account.RuoloEnum;
import barberbook.app.bbbe.rest.conto.dto.ContoRequestDto;
import barberbook.app.bbbe.rest.utente.dto.UtenteRequestDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AccountSignupRequestDto {

    protected String username;
    protected String password;
    protected RuoloEnum ruolo;

}

package barberbook.app.bbbe.rest.account.dto;

import barberbook.app.bbbe.rest.account.RuoloEnum;
import barberbook.app.bbbe.rest.utente.dto.UtenteResponseDto;
import barberbook.app.bbbe.rest.utente.repository.UtenteBean;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AccountResponseDto {
    private String id;
    private String username;
    private RuoloEnum ruolo;
    private UtenteResponseDto utente;

}

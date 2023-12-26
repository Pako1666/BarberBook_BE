package barberbook.app.bbbe.rest.account.dto;

import barberbook.app.bbbe.rest.account.RuoloEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AccountLoginResponseDto {
    private String username;
    private String email;
    private RuoloEnum ruolo;
    private String token;

}

package barberbook.app.bbbe.rest.account.dto;

import lombok.Data;

@Data
public class AddAccountUserRequestDto extends AccountSignupRequestDto{
    private String email;
}

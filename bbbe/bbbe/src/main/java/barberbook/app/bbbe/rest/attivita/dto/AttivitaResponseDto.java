package barberbook.app.bbbe.rest.attivita.dto;

import barberbook.app.bbbe.rest.account.dto.AccountResponseDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AttivitaResponseDto {
    private String id;
    private String nome;
    private Boolean esonerata;
    private Date dataCreazione;
    private List<AccountResponseDto> accountList;
}

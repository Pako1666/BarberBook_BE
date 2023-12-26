package barberbook.app.bbbe.rest.utente.dto;

import barberbook.app.bbbe.rest.conto.dto.ContoResponseDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UtenteResponseDto {
    private String id;
    private String nome;
    private String cognome;
    private String email;
    private Date birthDate;
    private ContoResponseDto contoResponseDto;

}

package barberbook.app.bbbe.rest.attivita.dto;

import barberbook.app.bbbe.rest.indirizzo.dto.IndirizzoResponseDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ListAttivitaItemDto {
    private String id;
    private String nome;
    private Boolean esonerata;
    private Date dataCreazione;
    private IndirizzoResponseDto indirizzoResponseDto;
}

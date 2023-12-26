package barberbook.app.bbbe.rest.attivita.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateAttivitaRequestDto {
    private String nome;
    private String idProprietario;
    private String indirizzo;
    private Boolean esonerata;
}

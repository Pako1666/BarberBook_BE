package barberbook.app.bbbe.rest.utente.dto;

import lombok.Data;

import java.util.Date;

@Data
public class UtenteRequestDto {
    private String nome;
    private String cognome;
    private Date birthDate;
    private String email;
}




package barberbook.app.bbbe.rest.utente.service;

import barberbook.app.bbbe.rest.utente.dto.AddContoRequestDto;
import barberbook.app.bbbe.rest.utente.dto.UtenteResponseDto;
import barberbook.app.bbbe.rest.utente.dto.UtenteRequestDto;

public interface UtenteService {

    UtenteResponseDto getUtenteById(String id) throws Exception;

    UtenteResponseDto addCardToUtente(AddContoRequestDto req) throws Exception;

    UtenteResponseDto insertUtente(UtenteRequestDto req);

    UtenteResponseDto getUtenteByEmailEquals(String email) throws Exception;

}

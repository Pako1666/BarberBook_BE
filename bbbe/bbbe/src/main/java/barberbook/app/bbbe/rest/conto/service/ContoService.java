package barberbook.app.bbbe.rest.conto.service;

import barberbook.app.bbbe.rest.conto.dto.ContoRequestDto;
import barberbook.app.bbbe.rest.conto.dto.ContoResponseDto;
import barberbook.app.bbbe.rest.utente.dto.AddContoRequestDto;

public interface ContoService {

    ContoResponseDto getContoById(String id);

    ContoResponseDto getContoByUser(String user);

    ContoResponseDto addContoToUser(ContoRequestDto req);

}

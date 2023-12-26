package barberbook.app.bbbe.rest.attivita.service;

import barberbook.app.bbbe.rest.attivita.dto.AttivitaResponseDto;
import barberbook.app.bbbe.rest.attivita.dto.CreateAttivitaRequestDto;
import barberbook.app.bbbe.rest.attivita.dto.ListAttivitaItemDto;

import java.util.List;

public interface AttivitaService {

    List<ListAttivitaItemDto> findAllAccountAttivita(String idAccount);

    AttivitaResponseDto createAttivita(CreateAttivitaRequestDto req);

    //creare request
    AttivitaResponseDto updateAttivita(CreateAttivitaRequestDto req);

    void deleteAttività(String idAtt);
}

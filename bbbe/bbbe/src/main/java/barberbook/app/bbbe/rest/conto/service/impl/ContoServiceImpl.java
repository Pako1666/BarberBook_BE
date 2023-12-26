package barberbook.app.bbbe.rest.conto.service.impl;

import barberbook.app.bbbe.rest.conto.dto.ContoRequestDto;
import barberbook.app.bbbe.rest.conto.dto.ContoResponseDto;
import barberbook.app.bbbe.rest.conto.repository.ContoBean;
import barberbook.app.bbbe.rest.conto.repository.ContoRepository;
import barberbook.app.bbbe.rest.conto.service.ContoService;
import barberbook.app.bbbe.utils.AbstractEntityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ContoServiceImpl extends AbstractEntityService<ContoRequestDto>  implements ContoService {

    private final String ENTITY_PREFIX = "ECC";

    @Autowired
    private ContoRepository contoRepository;

    @Override
    public ContoResponseDto getContoById(String id) {
        return null;
    }

    @Override
    public ContoResponseDto getContoByUser(String user) {
        ContoBean contoBean = contoRepository.findIntestatarioConto(user);
        return contoBean!=null?
                ContoResponseDto
                .builder()
                .id(contoBean.getId())
                .numCarta(contoBean.getNumCarta())
                .ente(contoBean.getEnte())
                .cv(contoBean.getCv())
                .iban(contoBean.getIban())
                .scadenza(contoBean.getScadenza().toString())
                .build()

                : ContoResponseDto.builder().build();
    }

    @Override
    public ContoResponseDto addContoToUser(ContoRequestDto req) {
        String id = "";

        ContoBean contoBean = contoRepository.save(ContoBean.builder()
                .iban(id)
                .scadenza(req.getScadenza())
                .numCarta(req.getNumCarta())
                .cv(req.getCv())
                .iban(req.getIban())
                .ente(req.getEnte())
                .build());
        return ContoResponseDto
                .builder()
                .id(contoBean.getId())
                .iban(contoBean.getIban())
                .cv(contoBean.getCv())
                .scadenza(contoBean.getScadenza().toString())
                .ente(contoBean.getEnte())
                .build();
    }


    @Override
    public String generateId(ContoRequestDto e) {
        String generatedId = "";
        generatedId = generatedId.concat(ENTITY_PREFIX);
        List<Character> consonantiEnte = findConsonanti(e.getEnte());
        for(Character c:consonantiEnte){
            generatedId += c.toString();
        }
        generatedId = generatedId.concat(e.getNumCarta());

        return generatedId;
    }
}

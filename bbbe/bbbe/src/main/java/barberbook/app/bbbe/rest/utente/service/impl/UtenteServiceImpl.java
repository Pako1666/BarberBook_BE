package barberbook.app.bbbe.rest.utente.service.impl;

import barberbook.app.bbbe.rest.conto.dto.ContoResponseDto;
import barberbook.app.bbbe.rest.conto.service.ContoService;
import barberbook.app.bbbe.rest.utente.repository.UtenteBean;
import barberbook.app.bbbe.rest.utente.dto.AddContoRequestDto;
import barberbook.app.bbbe.rest.utente.dto.UtenteResponseDto;
import barberbook.app.bbbe.rest.utente.repository.UtenteRepository;
import barberbook.app.bbbe.rest.utente.service.UtenteService;
import barberbook.app.bbbe.rest.utente.dto.UtenteRequestDto;
import barberbook.app.bbbe.utils.AbstractEntityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class UtenteServiceImpl extends AbstractEntityService<UtenteRequestDto> implements UtenteService {

    private String ENTITY_PREFIX = "EU";

    @Autowired
    ContoService contoService;

    @Autowired
    private UtenteRepository utenteRepository;
    @Override
    public UtenteResponseDto getUtenteById(String id) throws Exception {
        UtenteBean utenteBean = utenteRepository.findById(id).orElse(null);

        if(utenteBean==null){
            throw new Exception("Utente not found");
        }

        return UtenteResponseDto
                .builder()
                .email(utenteBean.getEmail())
                .birthDate(utenteBean.getBirthDate())
                .nome(utenteBean.getNome())
                .cognome(utenteBean.getCognome())
                .id(utenteBean.getId())
                .contoResponseDto(contoService.getContoByUser(utenteBean.getId()))
                .build();
    }

    @Override
    public UtenteResponseDto addCardToUtente(AddContoRequestDto req) throws Exception {
        UtenteResponseDto utenteResponseDto = getUtenteById(req.getUserId());
        ContoResponseDto contoResponseDto = null;
        utenteResponseDto.setContoResponseDto(contoResponseDto);
        return utenteResponseDto;
    }

    @Override
    public UtenteResponseDto insertUtente(UtenteRequestDto req) {
        String id = generateId(req);



        UtenteBean utenteBean = utenteRepository.save(
                UtenteBean.builder()
                        .id(id)
                        .nome(req.getNome())
                        .cognome(req.getCognome())
                        .email(req.getEmail())
                        .birthDate(req.getBirthDate())
                        .conto(null)
                        .build()
        );
        return UtenteResponseDto
                .builder()
                .id(utenteBean.getId())
                .cognome(utenteBean.getNome())
                .email(utenteBean.getEmail())
                .nome(utenteBean.getNome())
                .birthDate(utenteBean.getBirthDate())
                .build();
    }

    @Override
    public UtenteResponseDto getUtenteByEmailEquals(String email) throws Exception {
        UtenteBean utenteBean = utenteRepository.findUtenteByEmail(email)
                .orElseThrow(()->new Exception("Utente con email "+email+" non trovato!"));

        return UtenteResponseDto
                .builder()
                .id(utenteBean.getId())
                .email(utenteBean.getEmail())
                .contoResponseDto(null)
                .birthDate(utenteBean.getBirthDate())
                .cognome(utenteBean.getCognome())
                .nome(utenteBean.getNome())
                .build();
    }


    @Override
    public String generateId(UtenteRequestDto e) {
        String id = "";
        id = id.concat(ENTITY_PREFIX);
        Set<Character> vocaliNome = new HashSet<>(findVocali(e.getNome()));
        Set<Character> consonantiCognome = new HashSet<>(findConsonanti(e.getCognome()));

        if(vocaliNome.size()<2){
            Set<Character> vocaliCognome = new HashSet<>(findVocali(e.getCognome()));
            vocaliCognome = new HashSet<>(
                    vocaliCognome.stream().filter(
                            voc->!vocaliNome.contains(voc)
                    ).collect(Collectors.toSet())
            );
            if(!vocaliCognome.isEmpty()) {
                    if(vocaliCognome.size()>=2-vocaliNome.size())
                        vocaliNome.addAll(vocaliCognome.stream().toList().subList(0, 2 - vocaliNome.size()));
                    else
                        vocaliNome.addAll(vocaliCognome);
                }
            }

        if(consonantiCognome.size()<3){
            HashSet<Character> consonantiNome = new HashSet<Character>(findConsonanti(e.getNome()));
            consonantiNome = new HashSet<>(
                    consonantiNome.stream().filter(
                            cons->!consonantiCognome.contains(cons)
                    ).collect(Collectors.toSet())
            );
            if(!consonantiNome.isEmpty()) {
                if(consonantiNome.size()>=3 - consonantiCognome.size())
                    consonantiCognome.addAll(
                            consonantiNome.stream().toList().subList(0, 3 - consonantiCognome.size())
                    );

                else
                    consonantiCognome.addAll(consonantiNome.stream().toList());


            }
        }


        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yy");
        String dateStr = sdf.format(e.getBirthDate()).replace("/","");
        //concatenare tutti i caratteri di prima
        for(Character voc : vocaliNome)
            id += voc;

        for(Character cons: consonantiCognome)
            id += cons;


        id = id.concat(dateStr);



        return id;
    }
}

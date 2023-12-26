package barberbook.app.bbbe.rest.account.service.impl;

import barberbook.app.bbbe.exception.LoginFailedException;
import barberbook.app.bbbe.rest.account.RuoloEnum;
import barberbook.app.bbbe.rest.account.dto.*;
import barberbook.app.bbbe.rest.account.repository.AccountBean;
import barberbook.app.bbbe.rest.account.repository.AccountRepository;
import barberbook.app.bbbe.rest.account.service.AccountService;
import barberbook.app.bbbe.rest.utente.dto.UtenteRequestDto;
import barberbook.app.bbbe.rest.utente.dto.UtenteResponseDto;
import barberbook.app.bbbe.rest.utente.repository.UtenteBean;
import barberbook.app.bbbe.rest.utente.repository.UtenteRepository;
import barberbook.app.bbbe.rest.utente.service.UtenteService;
import barberbook.app.bbbe.utils.AbstractEntityService;
import barberbook.app.bbbe.utils.BearerTokenGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Base64;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AccountServiceImpl extends AbstractEntityService<AccountSignupRequestDto> implements AccountService {

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private UtenteService utenteService;

    @Autowired
    private UtenteRepository utenteRepository;

    @Override
    public AccountLoginResponseDto login(AccountLoginRequestDto req) throws Exception {
        AccountBean accountBean = accountRepository
                .findAccountByUsernameAndPassword(req.getUsername(), req.getPassword())
                .orElseThrow(() -> new LoginFailedException("Login errata"));

        String token = BearerTokenGenerator.generateBearerToken(accountBean);

        return AccountLoginResponseDto
                .builder()
                .username(accountBean.getUsername())
                .email(accountBean.getUtente().getEmail())
                .token(token)
                .ruolo(accountBean.getRuolo())
                .build();
    }

    @Override
    public AccountResponseDto addAccountToEmail(AddAccountUserRequestDto req) throws Exception {
        String id = generateId(req);
        UtenteBean utenteBean = utenteRepository.findUtenteByEmail(req.getEmail())
                .orElseThrow(()->new NoSuchElementException("impossibile trovare l'utente con email " +
                        req.getEmail()));
        AccountBean accountBeanSaved = accountRepository.save(
                AccountBean
                        .builder()
                        .id(id)
                        .username(req.getUsername())
                        .password(req.getPassword())
                        .ruolo(req.getRuolo())
                        .utente(utenteBean)
                        .build()
        );
        return AccountResponseDto
                .builder()
                .id(id)
                .username(accountBeanSaved.getUsername())
                .ruolo(accountBeanSaved.getRuolo())
                .utente(UtenteResponseDto
                        .builder()
                        .id(utenteBean.getId())
                        .email(utenteBean.getEmail())
                        .nome(utenteBean.getNome())
                        .cognome(utenteBean.getCognome())
                        .birthDate(utenteBean.getBirthDate())
                        .build())
                .build();
    }


    @Override
    public AccountResponseDto signup(NewAccountSignupRequestDto req) throws Exception {
        UtenteResponseDto utenteDto = utenteService.insertUtente(req.getUtenteRequestDto());
        UtenteBean utenteCreato = UtenteBean.builder()
                .id(utenteDto.getId())
                .birthDate(utenteDto.getBirthDate())
                .nome(utenteDto.getNome())
                .cognome(utenteDto.getCognome())
                .email(utenteDto.getEmail())
                .build();
        String id = generateId(req);

        List<String> allRolesAccounts = accountRepository
                .findAccountByUtente(utenteCreato)
                .stream()
                .map(u -> u.getRuolo().name())
                .toList();

        if(allRolesAccounts.contains(req.getRuolo().name())){
            throw new Exception("Account con il ruolo "+req.getRuolo().name()+" esiste già.");
        }



        AccountBean accountCreated = accountRepository.save(
                AccountBean
                        .builder()
                        .id(id)
                        .utente(utenteCreato)
                        .ruolo(req.getRuolo())
                        .password(req.getPassword())
                        .username(req.getUsername())
                        .build()
        );


        return AccountResponseDto
                .builder()
                .id(accountCreated.getId())
                .utente(utenteDto)
                .ruolo(accountCreated.getRuolo())
                .username(accountCreated.getUsername())
                .build();
    }

    @Override
    public String generateId(AccountSignupRequestDto e) {
        String newId = "EACC";
        newId = newId.concat("R".concat(e.getRuolo().name().substring(0,3)));
        String consonantiUsername = "";
        for(Character cons: findConsonanti(e.getUsername())){
            consonantiUsername+=cons;
        }
        if(consonantiUsername.length()>5){
            consonantiUsername = consonantiUsername.substring(0,5);
        }
        if(consonantiUsername.length()<5){
            int charsMancanti = 5 - consonantiUsername.length();

            for(int i = 0; i< charsMancanti; i++){
                consonantiUsername+="X";
            }

        }
        newId = newId.concat(consonantiUsername.substring(0,5));

        String email = "";

        if(e instanceof NewAccountSignupRequestDto){

            email = ((NewAccountSignupRequestDto) e).getUtenteRequestDto().getEmail();
        }

        else{
            email = ((AddAccountUserRequestDto) e).getEmail();
        }

        int atPos = email.indexOf("@");
        email = email.substring(0,atPos);

        newId = newId.concat(email.substring(0,5).toUpperCase());


        return newId;
    }
}

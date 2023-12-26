package barberbook.app.bbbe.utils;

import barberbook.app.bbbe.rest.account.repository.AccountBean;
import barberbook.app.bbbe.rest.utente.repository.UtenteBean;

import java.util.Base64;

public class BearerTokenGenerator {

    private static Base64.Encoder encoder = Base64.getEncoder();
    private static Base64.Decoder decoder = Base64.getDecoder();
    final static String STR_SEP = "&";
    public static String generateBearerToken(AccountBean acc){
        UtenteBean utente = acc.getUtente();
        String fullName = utente.getNome()+" "+utente.getCognome();
        String token = "bbapp_tokenauth={";
        token = token.concat("idacc="+encoder.encodeToString(acc.getId().getBytes())+STR_SEP);
        token = token.concat("username="+encoder.encodeToString(acc.getUsername().getBytes())+STR_SEP);
        token = token.concat("role="+encoder.encodeToString(acc.getRuolo().name().getBytes())+STR_SEP);
        token = token.concat("utenteinfo={")
                .concat("email="+encoder.encodeToString(utente.getId().getBytes())+STR_SEP)
                .concat("fullName="+encoder.encodeToString(fullName.getBytes())+STR_SEP)
                .concat("email="+encoder.encodeToString(utente.getEmail().getBytes()))
                .concat("}");
        token = token.concat("}");



        return encoder.encodeToString(token.getBytes());
    }

}

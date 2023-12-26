package barberbook.app.bbbe.utils;

public enum ErrorCodesEnum {

    SERVICE_ERR(145,"errore nel servizio"),
    SERVER_ERR(150),
    CLIENT_ERR(155);

    private Integer code;
    private String description;
    ErrorCodesEnum(Integer code,String description){

    }

    ErrorCodesEnum(Integer code){

    }


}

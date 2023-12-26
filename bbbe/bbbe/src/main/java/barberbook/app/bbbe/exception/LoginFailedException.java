package barberbook.app.bbbe.exception;

import org.springframework.web.bind.MethodArgumentNotValidException;

public class LoginFailedException extends Exception {

    public LoginFailedException(String msg){
        super(msg);
    }

}

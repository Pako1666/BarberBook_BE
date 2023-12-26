package barberbook.app.bbbe;

import barberbook.app.bbbe.exception.LoginFailedException;
import barberbook.app.bbbe.exception.ValidationException;
import barberbook.app.bbbe.utils.ErrorCodesEnum;
import barberbook.app.bbbe.utils.ErrorMessage;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import javax.xml.crypto.Data;
import java.time.LocalDateTime;
import java.util.Date;

@RestControllerAdvice
public class BBAdviceController {

    @ExceptionHandler(value = {LoginFailedException.class})
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public ErrorMessage loginFailed(LoginFailedException ex, WebRequest request) {
        ErrorMessage message = new ErrorMessage(
                ErrorCodesEnum.CLIENT_ERR,
                ex.getMessage(),
                LocalDateTime.now()
                );

        return message;
    }

    @ExceptionHandler(value={Exception.class})
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public ErrorMessage exceptionHandler(Exception ex,WebRequest req){
        ErrorMessage message = new ErrorMessage(
                ErrorCodesEnum.CLIENT_ERR,
                ex.getMessage(),
                LocalDateTime.now()
        );
        return message;
    }

}

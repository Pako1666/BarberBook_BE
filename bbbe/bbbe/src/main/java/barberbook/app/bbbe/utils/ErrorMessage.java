package barberbook.app.bbbe.utils;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ErrorMessage {
    private ErrorCodesEnum errCode;
    private String message;
    private LocalDateTime timestamp;


}

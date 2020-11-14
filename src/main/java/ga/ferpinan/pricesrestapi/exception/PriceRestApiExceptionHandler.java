package ga.ferpinan.pricesrestapi.exception;

import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
@Log4j2
public class PriceRestApiExceptionHandler {

    private static final String NON_CONTROLLED_EXCEPTION = "Non controlled exception: ";

	@ExceptionHandler(PriceNotFoundException.class)
	public ResponseEntity<ErrorMessage> handlePriceNotFoundException(Exception ex) {
        ErrorMessage errorMessage = ErrorMessage.builder().timeStamp(LocalDateTime.now()).message(ex.getMessage()).build();
        return new ResponseEntity<>(errorMessage, new HttpHeaders(), HttpStatus.NOT_FOUND);
	}

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorMessage> handleException(Exception ex) {
        log.error(NON_CONTROLLED_EXCEPTION + ex.getMessage());
        ErrorMessage errorMessage = ErrorMessage.builder().timeStamp(LocalDateTime.now()).message(ex.getMessage()).build();
        return new ResponseEntity<>(errorMessage, new HttpHeaders(), HttpStatus.BAD_REQUEST);
    }
}

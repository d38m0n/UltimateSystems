package ultimate.systems.pawelBuczek.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class GlobalAdvice {
    @ResponseBody
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String ValidationHandler(MethodArgumentNotValidException ex) {
        return ex.getBindingResult()
                .getFieldErrors()
                .get(0)
                .getDefaultMessage();
    }

    @ResponseBody
    @ExceptionHandler(CanNotDeleteException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public String canNotDeleteExceptionHandle(CanNotDeleteException ex) {
        return ex.getMessage();
    }
}

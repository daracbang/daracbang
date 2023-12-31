package a503.daracbang.global.exception;


import a503.daracbang.global.response.FailResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(CustomException.class)
    public ResponseEntity<?> handleCustomException(CustomException e) {
        ErrorCode errorCode = e.getErrorCode();
        FailResponse failResponse = new FailResponse(errorCode);
        return ResponseEntity.status(errorCode.getStatusCode())
                             .body(failResponse);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> handleArgumentValidException(MethodArgumentNotValidException e){
        ErrorCode errorCode = GlobalErrorCode.INVALID_INPUT;
        FailResponse failResponse = new FailResponse(errorCode);
        return ResponseEntity.status(errorCode.getStatusCode())
                             .body(failResponse);
    }
}

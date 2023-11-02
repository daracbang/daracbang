package a503.daracbang.domain.diary.exception;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionController {

    /**
     * @valid  유효성체크에 통과하지 못하면  MethodArgumentNotValidException 이 발생한다.
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<DiaryErrorCode> methodValidException(MethodArgumentNotValidException e, HttpServletRequest request){
        DiaryErrorCode errorResponse = makeErrorResponse(e.getBindingResult());
        return new ResponseEntity<DiaryErrorCode>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    private DiaryErrorCode makeErrorResponse(BindingResult bindingResult) {
        String code = "";
        String description = "";
        int detail = 0;
        if(bindingResult.hasErrors()){
            String bindResultCode = bindingResult.getFieldError().getCode();
            switch (bindResultCode){
                case "NotBlank":
                    code = DiaryErrorCode.NOTBLANK_DIARY.getErrorCode();
                    description = DiaryErrorCode.NOTBLANK_DIARY.getMessage();
                    detail = DiaryErrorCode.NOTBLANK_DIARY.getStatusCode();
                    return new DiaryNotBlankException()
                case "Size":
                    code = DiaryErrorCode.NOTBLANK_DIARY.getErrorCode();
                    description = DiaryErrorCode.NOTBLANK_DIARY.getMessage();
                    detail = DiaryErrorCode.NOTBLANK_DIARY.getStatusCode();
            }


        }
    }

}

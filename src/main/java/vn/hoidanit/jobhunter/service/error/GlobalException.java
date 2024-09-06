package vn.hoidanit.jobhunter.service.error;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalException extends Exception{
    //@ExceptionHandler(value = IdInvalidException.class) //annotation này để khai báo exception và các giá trị trả về tương ứng
    @ExceptionHandler(IdInvalidException.class) //viết thế này cho ngắn
    public ResponseEntity<String> handleIdException(IdInvalidException idException) {
        return ResponseEntity.badRequest().body(idException.getMessage());
    }

}

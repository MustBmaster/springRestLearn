package vn.hoidanit.jobhunter.service.error;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import vn.hoidanit.jobhunter.domain.RestResponse;

@RestControllerAdvice
public class GlobalException extends Exception{
    //@ExceptionHandler(value = IdInvalidException.class) //annotation này để khai báo exception và các giá trị trả về tương ứng
    @ExceptionHandler(IdInvalidException.class) //viết thế này cho ngắn
    public ResponseEntity<RestResponse<Object>> handleIdException(IdInvalidException idException) {
        // return ResponseEntity.badRequest().body(idException.getMessage());
        RestResponse<Object> res = new RestResponse<Object>();// thay thế lỗi này, thay vì trả về lỗi thông thường
        res.setStatus(HttpStatus.BAD_REQUEST.value());
        res.setError(idException.getMessage());
        res.setMessage("Id invalid");
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(res);
    }
}

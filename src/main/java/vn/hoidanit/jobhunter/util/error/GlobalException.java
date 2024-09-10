package vn.hoidanit.jobhunter.util.error;

import java.util.List;
import java.util.stream.Collectors;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
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

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<RestResponse<Object>> handleValidationException(MethodArgumentNotValidException ex) {
        BindingResult result = ex.getBindingResult();//Khởi tạo result chứa các ngoại lệ liên quan tới không đúng hoặc thiếu thông tin
        final List<FieldError> fieldErrors = result.getFieldErrors();//Khởi tạo danh sách lỗi

        RestResponse<Object> res = new RestResponse<Object>();// khởi tạo đói tượng restResponse để trả về kết quả
        res.setStatus(HttpStatus.BAD_REQUEST.value());// set mã lỗi
        res.setError(ex.getBody().getDetail());// set error cho nó

        //Khởi tạo 1 list lỗi, bằng việc lấy ra các field error, cho nó tự ánh xạ tới mess của nó, rồi convert về dạng list
        List<String> errors = fieldErrors.stream().map(f -> f.getDefaultMessage()).collect(Collectors.toList());
        res.setMessage(errors.size() > 1 ? errors : errors.get(0));//nếu  só lượng lỗi nhiều hơn 1, trả về mảng lõi, nếu bằng 1 thì trả về 1 lỗi

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(res);//trả về kết quả
    }
}

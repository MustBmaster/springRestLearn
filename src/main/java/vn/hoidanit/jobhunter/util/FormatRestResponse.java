package vn.hoidanit.jobhunter.util;

import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.http.server.ServletServerHttpResponse;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import jakarta.servlet.http.HttpServletResponse;
import vn.hoidanit.jobhunter.domain.RestResponse;


//class FormatRestResponse kế thừa thêm ResponseBodyAdvice để sử chuẩn hóa response
@RestControllerAdvice
public class FormatRestResponse implements ResponseBodyAdvice{

    //hàm này sẽ quyết định xem ghi đè response nào
    @Override
    public boolean supports(MethodParameter returnType, Class converterType) {
        // TODO Auto-generated method stub
        // throw new UnsupportedOperationException("Unimplemented method 'supports'");
        return true;//trả về true là cái nào cũng ghi đè
    }

    //hàm này sẽ quyết định nội dung ghi đè lên response
    @Override
    public Object beforeBodyWrite(Object body, MethodParameter returnType, MediaType selectedContentType,
            Class selectedConverterType, ServerHttpRequest request, ServerHttpResponse response) {
        // TODO Auto-generated method stub
        // throw new UnsupportedOperationException("Unimplemented method 'beforeBodyWrite'");
        HttpServletResponse servletResponse= ((ServletServerHttpResponse) response).getServletResponse();
        int statusCode = servletResponse.getStatus();
        //2 dòng bên trên hơi nhì nhằng tí, nhưng nó dùng phương các phương thức của 2 hàm HttpServletResponse+ServletServerHttpResponse để lấy được status code
        //xảy ra 2 trường hợp: nếu các mã code trả về là nhở hơn 400 thì là ok, lơn hơn là lỗi
        RestResponse<Object> res = new RestResponse<Object>();//khai báo thực thể rest response
        res.setStatus(statusCode);//cài status code cho nó
        //nếu lỗi
        if(statusCode>=400){
            // res.setError("Api call failed");//vì thuộc tính error kiểu string nên ép kiểu
            // res.setMessage(body);//body là kiểu object nên không cần
            return body;
        }else{
            res.setData(body);//nếu ok thì cài dữ liệu cho nó
            res.setMessage("Api call succeded");//nếu ok thì set message về null
        }
        return res;
    }
}

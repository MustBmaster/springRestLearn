package vn.hoidanit.jobhunter.domain;
// kiểu dữ liệu generic, khi chưa biết chắc trả về gì
public class RestResponse<T> {
    private int status;
    private String error;
    private Object message;
    private T data;
   
    public int getStatus() {
        return status;
    }
    public void setStatus(int status) {
        this.status = status;
    }
    public String getError() {
        return error;
    }
    public void setError(String error) {
        this.error = error;
    }
    public T getData() {
        return data;
    }
    public void setData(T data) {
        this.data = data;
    }
    public Object getMessage() {
        return message;
    }
    public void setMessage(Object message) {
        this.message = message;
    }

    
}

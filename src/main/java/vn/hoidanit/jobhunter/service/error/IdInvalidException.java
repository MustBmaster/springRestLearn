package vn.hoidanit.jobhunter.service.error;

//khai báo hàm exceptions trước
public class IdInvalidException extends Exception {
    public IdInvalidException(String message) {
        super(message);
    }
}

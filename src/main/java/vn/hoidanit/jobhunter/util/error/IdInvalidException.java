package vn.hoidanit.jobhunter.util.error;

//khai báo hàm exceptions trước
public class IdInvalidException extends Exception {
    public IdInvalidException(String message) {
        super(message);
    }
}

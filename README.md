Coi như đã đạt yêu cầu học tiếp spring Rest: biết cấu trúc mô hình mvc và spring security
Đây sẽ là ghi chú học tập cho dự án này

Dự án này sử dụng gradle làm build tools, các cấu hình của nó nằm trên file build.gradle.kts

# 1: xây dựng dự án

-đã xây thử các thao tác như với mô hình mvc

# 2: truyền vào id

-sử dụng anotaion @Pathvariable [kiểu dữ liệu] khai báo để truyền vào dữ liệu
-sử dụng anntation @RequestBody [kiểu dữ liệu]+ khai báo để truyền vào các param cho request (đã test trên postman)

# 3: chuẩn hoá response

-Các response nên nhận được các phản hồi theo chuẩn để giúp FE có thể debug dễ hơn
-Sử dụng 1 kiểu dữ liệu generic là ResponseEntity để chuẩn hoá response
-Đối với các mã lỗi thông dụng, vì viết rất nhiều các method cho các controller, nên cần có cấu hình các exeption hay xảy ra, hoặc validate tại Frontend. Và đó là lí do ta dùng global exeption
-Sư dụng các global exeption với annotation @RestControllerAdvice. Các bước thực hiện như sau
    +Tạo class exception extend Exception
    +Tạo file cấu hình global exception, với annotation @ExceptionHandler mang giá trị class exception tương ứng cho loại exception cần trả ra
    +Cho class sử dụng exception trong controller throws new exception loại đó ra là được
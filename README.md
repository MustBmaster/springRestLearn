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

# 4: Giải thích cách vận hành của Oauth
-Sau khi đăng nhập, request sẽ gửi di tk/mk về tới server. Thông tin đó sẽ được đóng gói và gen ra 1 cái token. Backend sẽ authenticate cái token đó để kiểm tra quyền của user trong request đó. Sau đó, nếu xác thực thành công, authentication đó sẽ được lưu vào context để sử dụng trong các lời gọi request tiếp theo. Authentication đó sẽ được dùng để tạo ra 1 JWT token trả về cho user trong phần header
-giải thích 1 chút về DTO: nó gần như là entity, nhưng nó chỉ bao gồm các thông tin ngắn gọn, getter và setter, không chứa logic nghiệp vụ. Mục đích của nó là đóng gói các dữ liệu sử dụng nhiều để giao tiếp giữa các tầng của java, nhằm đơn giản hóa và giảm số lượng lời gọi

# 5: tạo key để mã hoá token
cấu hình các tham số của security trong file yml
đối với việc tạo secret key base 64, chạy lệnh này trong bash: openssl rand -base64 64
các thay đổi và giải thích đã comment trong commit #6

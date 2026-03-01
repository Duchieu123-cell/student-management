+ Danh sách nhóm: Nguyễn Đức Hiếu - 2113352
+ Public URL của Web Service (đã deploy ở Lab 5): https://student-management-3-5dve.onrender.com/students

+ Hướng dẫn cách chạy dự án:
  1. Chuẩn bị
    - Đã cài đặt JDK 21.
    - Đã cài đặt Eclipse IDE for Enterprise Java and Web Developers.
    - Đã có tài khoản Neon.tech.
  2. Clone project
  3. Import vào Eclipse IDE
    - Mở Eclipse.
    - Chọn File -> Import...
    - Chọn Maven -> Existing Maven Projects -> nhấn Next.
    - Trỏ đường dẫn đến thư mục project vừa clone -> nhấn Finish.
    - Đợi Eclipse tải các dependencies từ pom.xml.
  4. Cấu hình Cơ sở dữ liệu
  Mở file src/main/resources/application.properties và cập nhật thông số kết nối của bạn:

```properties
  spring.datasource.url=jdbc:postgresql://your-hostname:port/your-db?sslmode=require
  spring.datasource.username=your-username
  spring.datasource.password=your-password
```
  5. Chạy ứng dụng
    + Cách 1: Chuột phải vào project -> Run As -> Spring Boot App.
    + Cách 2: Mở Terminal tại thư mục gốc và gõ: mvn spring-boot:run
    Sau khi ứng dụng khởi động thành công, hãy truy cập: http://localhost:8080/students

+ Câu trả lời cho các câu hỏi lý thuyết trong phần Lab:
  1. Cố tình Insert một sinh viên có id trùng với một người đã có sẵn. Quan sát thông báo lỗi: UNIQUE constraint failed. Tại sao Database lại chặn thao tác này?
  - Trong câu lệnh tạo bảng, cột id đã được định nghĩa là PRIMARY KEY. Khóa chính (PRIMARY KEY) có vai trò định danh duy nhất cho mỗi record trong một bảng.
  - Database sẽ tự động kiểm tra và chặn thao tác cố tình chèn một giá trị id đã tồn tại, đảm bảo không có hai student nào có cùng id.

  2. Database có báo lỗi không? Từ đó suy nghĩ xem sự thiếu chặt chẽ này ảnh hưởng gì khi code Java đọc dữ liệu lên?
  - Cơ sở dữ liệu không báo lỗi vì trong script tạo bảng, cột name chỉ được định nghĩa kiểu dữ liệu là TEXT, không có thêm ràng buộc NOT NULL.
  - Khi ứng dụng Spring Boot đọc dòng dữ liệu này lên thông qua Hibernate, giá trị name là null đối với kiểu String tương ứng trong Java sẽ rất nguy hiểm vì nếu tầng Service hoặc Controller thao tác trực tiếp trên thuộc tính name này
  (ví dụ: gọi hàm name.toUpperCase()) mà không có bước kiểm tra sơ bộ, ứng dụng sẽ quăng lỗi NullPointerException và gây sập luồng xử lý.

  3. Tại sao mỗi lần tắt ứng dụng và chạy lại, dữ liệu cũ trong Database lại bị mất hết?
  - File cấu hình application.properties của dự án có thuộc tính cấu hình được thiết lập là spring.jpa.hibernate.ddl-auto: create. Mỗi khi chạy lại ứng dụng, Hibernate sẽ xóa toàn bộ dữ liệu cũ đi và tạo lại bảng mới hoàn toàn.

+ Screenshot cho các module trong Lab 4:
  1. Trang danh sách
<img width="1910" height="934" alt="Screenshot 2026-03-01 160604" src="https://github.com/user-attachments/assets/c94ac2f4-379c-4e6f-a1f9-990f69bea5ba" />
<img width="1900" height="923" alt="Screenshot 2026-03-01 160623" src="https://github.com/user-attachments/assets/9674ba79-f2b5-432f-9e21-7551213459e5" />

  2. Trang chi tiết
<img width="1907" height="940" alt="Screenshot 2026-03-01 160648" src="https://github.com/user-attachments/assets/7977e674-2cfb-4939-af6f-291859c822d0" />
<img width="1896" height="938" alt="Screenshot 2026-03-01 204946" src="https://github.com/user-attachments/assets/5ebbfe5d-051d-47b5-a132-b68bdab725b8" />

  3. Trang thêm mới
<img width="1911" height="940" alt="Screenshot 2026-03-01 160635" src="https://github.com/user-attachments/assets/6cb84ae0-d7c6-493c-b7f8-7ee0ae33276e" />

  4. Trang chỉnh sửa
<img width="1908" height="938" alt="Screenshot 2026-03-01 160658" src="https://github.com/user-attachments/assets/09f208ee-0657-40cd-a106-2230ec86cd81" />

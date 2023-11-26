# SpringCommerce

## Tổng quan dự án
- Dự án này sử dụng thiết kế hướng đối tượng để tạo các lớp và đối tượng có ý nghĩa và tái sử dụng được.
- Sử dụng mô hình MVC để tách biệt giữa logic dữ liệu (Model), hiển thị (View), và điều khiển (Controller).

## Sơ đồ quan hệ

## Cấu trúc dự án

## Các bước để chạy dự án
1. Tạo một schema mới trong `MySQL` có tên `springcommerce` với Default Charset là `utf8`.
2. Import file `data.sql` vào schema vừa tạo.
3. Thay đổi các thông tin `spring.datasource.url`, `spring.datasource.username`, `spring.datasource.password` trong `application.properties` nếu cần thiết, trong đó:
- `spring.datasource.url` là đường dẫn đến schema trong `MySQL`.
- `spring.datasource.username`, `spring.datasource.password` là username và password của user dùng để kết nối đến schema `springcommerce`.
4. Chạy file `MidtermApplication.java`.

## Các API

# SpringCommerce

## Tổng quan dự án
- Dự án này sử dụng thiết kế hướng đối tượng để tạo các lớp và đối tượng có ý nghĩa và tái sử dụng được.
- Sử dụng mô hình MVC để tách biệt giữa logic dữ liệu (Model), hiển thị (View), và điều khiển (Controller).
- Các API được thiết kế theo nguyên tắc REST.
- Sử dụng `Spring Security` để xác thực người dùng và `JSON Web Token` để phân quyền người dùng.

## Sơ đồ quan hệ
![image](https://github.com/Luanardo/SpringCommerce/assets/122257380/720a88de-c862-4cc4-8871-e2ce61852b03)

## Cấu trúc dự án
Cấu trúc dự án được chia thành nhiều package

![image](https://github.com/Luanardo/SpringCommerce/assets/122257380/bdfeada6-cf6c-469d-bf3a-185a151cd5a3)

Thư mục productImages trong thư mục public chứa các hình ảnh của sản phẩm

![image](https://github.com/Luanardo/SpringCommerce/assets/122257380/043fab95-e74a-42f1-bde4-5193423b9d16)

- Package `api` chứa các endpoint cho các API mà dự án cung cấp
- Package `config` chứa các file cấu hình cho Spring Security và JWT
- Package `controller` chứa các endpoint cho các request từ trình duyệt

![image](https://github.com/Luanardo/SpringCommerce/assets/122257380/56896f85-f1f7-4a96-9a35-d17c42692d48)

- Package `dto` chứa các file cấu trúc của các Object được sử dụng cho request và response trong các API.
- Package `exception` chứa các phương thức xử lý lỗi.
- Package `model` chứa các thực thể sẽ được tạo ra trong cơ sở dữ liệu.

![image](https://github.com/Luanardo/SpringCommerce/assets/122257380/425bb35c-ac84-400e-ae19-a43f2fa7a499)

- Package `repository` chứa các interface được extends từ `JpaRepository` để hiện thực các phương thức truy xuất dữ liệu từ cơ sở dữ liệu
- Package `service` chứa các interface cho các service được cung cấp cho các API và controller sử dụng.
- Package `service.impl` chứa các class implements các service interface tương ứng trong `service`.
- Package `util` chứa các class khác hỗ trợ trong quá trình upload ảnh thông qua API.

![image](https://github.com/Luanardo/SpringCommerce/assets/122257380/98d09a4a-39da-4622-a548-5cb7e0975058)

Thư mục `resources` có các thư mục: 
- `static` chứa các file css và javascipt cho template của dự án.
- `templates` chứa các file html để hiển thị lên trang web.

![image](https://github.com/Luanardo/SpringCommerce/assets/122257380/6e5ab6a5-c3b8-4b50-83c9-9a3c9cabf072)

## Các bước để chạy dự án
1. Tạo một schema mới trong `MySQL` có tên `springcommerce` với Default Charset là `utf8`.
2. Import file `data.sql` vào schema vừa tạo.
3. Thay đổi các thông tin `spring.datasource.url`, `spring.datasource.username`, `spring.datasource.password` trong file `application.properties` nếu cần thiết, trong đó:
    - `spring.datasource.url` là đường dẫn đến schema trong `MySQL`.
    - `spring.datasource.username`, `spring.datasource.password` là username và password của user dùng để kết nối đến schema `springcommerce`.
4. Chạy file `MidtermApplication.java`.
5. Truy cập vào đường dẫn http://localhost:8080/ trên trình duyệt

## Các API
***Các API mà dự án cung cấp chỉ hỗ trợ các request với body là `form-data`, định dạng `JSON` không được hỗ trợ.***
### Login
Để sử dụng các API mà chỉ Admin mới được phép sử dụng thì cần phải gửi request với `JSON Web Token`. Dự án đã cung cấp một API `login` để người dùng có thể nhận được token.

![image](https://github.com/Luanardo/SpringCommerce/assets/122257380/c66c35cc-6862-4bba-9b10-080d3fa6761f)

**Tài khoản admin mặc định có `email` và cả `password` đều là `admin`**

### Product
#### Thêm sản phẩm
*Request đến API này cần gửi kèm token*

![image](https://github.com/Luanardo/SpringCommerce/assets/122257380/85a7ea6c-51e4-43a4-be0e-b1f2c6d43844)
![image](https://github.com/Luanardo/SpringCommerce/assets/122257380/c6aa07f1-7b00-4fa2-a2bd-e657372ec3df)

#### Cập nhật sản phẩm
*Request đến API này cần gửi kèm token*

![image](https://github.com/Luanardo/SpringCommerce/assets/122257380/cb5e9a79-3c05-4c78-9716-bd39ab0222ec)
![image](https://github.com/Luanardo/SpringCommerce/assets/122257380/c9cda6ad-7163-475c-90c8-2ccf2698f6f0)

#### Xóa sản phẩm
*Request đến API này cần gửi kèm token*

![image](https://github.com/Luanardo/SpringCommerce/assets/122257380/89ff9767-4177-486d-97ff-d64e7b58a3e2)

#### Lấy danh sách sản phẩm
![image](https://github.com/Luanardo/SpringCommerce/assets/122257380/36bee66a-4e2b-499c-9fe3-d389bdab1056)

#### Lấy thông tin chi tiết một sản phẩm
![image](https://github.com/Luanardo/SpringCommerce/assets/122257380/418111a9-8abb-4e1c-8dc0-b134c70642a1)

#### Lọc danh sách sản phẩm
Lọc danh sách sản phẩm theo cấp độ, mức giá và từ khóa. Được sử dụng trong trang web cho khách hàng

![image](https://github.com/Luanardo/SpringCommerce/assets/122257380/2664860d-a7e2-4ce7-a4f7-8b6104009d69)


### Order
#### Chuyển trạng thái của đơn hàng thành 'Đang giao hàng'
*Request đến API này cần gửi kèm token*

![image](https://github.com/Luanardo/SpringCommerce/assets/122257380/37f92da2-a6e2-487c-9e88-6f47963d834c)

#### Chuyển trạng thái đơn hàng thành 'Đã giao hàng'
*Request đến API này cần gửi kèm token*

![image](https://github.com/Luanardo/SpringCommerce/assets/122257380/4a95db34-4105-498d-9d08-ff320bbb9681)

### Cart
#### Thêm sản phẩm vào giỏ hàng
Được sử dụng trong trang web cho khách hàng để khách hàng có thể thêm sản phẩm vào giỏ.

![image](https://github.com/Luanardo/SpringCommerce/assets/122257380/57684512-2483-4559-bde5-c774c86a8893)

#### Cập nhật số lượng sản phẩm trong giỏ hàng
Được sử dụng trong trang web cho khách hàng để khách hàng có thể cập nhật số lượng sản phẩm trong giỏ hàng.

![image](https://github.com/Luanardo/SpringCommerce/assets/122257380/8015e60f-9118-4d36-b954-db2da5c5f9fa)

#### Xóa sản phẩm khỏi giỏ hàng
Được sử dụng trong trang web cho khách hàng để khách hàng có thể xóa sản phẩm khỏi giỏ hàng.

![image](https://github.com/Luanardo/SpringCommerce/assets/122257380/14170aa6-a3a5-4fe0-b363-6ff1089397b2)

# 🏢 HỆ THỐNG QUẢN LÝ KÝ TÚC XÁ PHENIKAA (Spring Boot)

<div align="center">
  <img src="https://img.shields.io/badge/Java-ED8B00?style=for-the-badge&logo=java&logoColor=white" alt="Java"/>
  <img src="https://img.shields.io/badge/Spring%20Boot-6DB33F?style=for-the-badge&logo=spring-boot&logoColor=white" alt="Spring Boot"/>
  <img src="https://img.shields.io/badge/Thymeleaf-005F0F?style=for-the-badge&logo=thymeleaf&logoColor=white" alt="Thymeleaf"/>
  <img src="https://img.shields.io/badge/JUnit-25A162?style=for-the-badge&logo=junit5&logoColor=white" alt="JUnit"/>
</div>

<div align="center">
  <img src="https://img.shields.io/badge/Version-1.0.0-blue?style=flat-square" alt="Version"/>
  <img src="https://img.shields.io/badge/Release%20Date-2025-blue?style=flat-square" alt="Release Date"/>
  <img src="https://img.shields.io/badge/License-Educational-green?style=flat-square" alt="License"/>
</div>

---

## 📝 Tổng Quan

Hệ thống quản lý ký túc xá Đại học Phenikaa, phát triển bằng Java Spring Boot, Thymeleaf, JPA và SQL. Hỗ trợ quản lý sinh viên, phòng ở, hợp đồng, phí, tìm kiếm, thống kê, báo cáo và popup giới thiệu trường.

---

## 📋 Dự Án Bao Gồm

- **Quản lý sinh viên:** Thêm, sửa, xóa, xem chi tiết sinh viên; phân phòng cho sinh viên; theo dõi trạng thái sinh viên.
- **Quản lý phòng:** Thêm, sửa, xóa, xem chi tiết phòng; theo dõi tình trạng phòng (trống, đầy, đang sửa); tìm kiếm phòng.
- **Quản lý hợp đồng:** Tạo, sửa, xóa, xem chi tiết hợp đồng thuê phòng; liên kết sinh viên và phòng; theo dõi thời hạn hợp đồng.
- **Quản lý phí:** Thêm, sửa, xóa, xem chi tiết các loại phí (dọn dẹp, điện, nước, bảo trì); liên kết phí với hợp đồng; theo dõi hạn thanh toán và trạng thái thanh toán.
- **Giao diện web:** Sử dụng Thymeleaf, có các trang danh sách, chi tiết, form cho từng đối tượng (sinh viên, phòng, hợp đồng, phí), dashboard tổng quan, popup giới thiệu trường.
- **Kiến trúc:** Java Spring Boot, Thymeleaf, JPA/Hibernate, JUnit, Chart.js; mô hình MVC, RESTful; lưu trữ dữ liệu bằng SQL (MySQL/H2/PostgreSQL).
- **Cấu trúc thư mục:** 
  - `src/main/java/com/example/dorm/`: mã nguồn backend (controller, model, repository)
  - `src/main/resources/templates/`: giao diện Thymeleaf cho dashboard, sinh viên, phòng, hợp đồng, phí, lỗi
  - `src/main/resources/static/css/`: style.css
  - `src/main/resources/application.properties`: cấu hình ứng dụng
  - `src/main/resources/database.sql`: dữ liệu mẫu
  - `test/`: mã kiểm thử JUnit

---

## ✨ Tính Năng

### 👥 Quản Lý Sinh Viên
- Thêm, sửa, xóa, xem chi tiết sinh viên
- Phân phòng cho sinh viên
- Tìm kiếm sinh viên theo nhiều tiêu chí

### 🏠 Quản Lý Phòng
- Thêm, sửa, xóa, xem chi tiết phòng
- Theo dõi tình trạng phòng (TRỐNG, ĐẦY)
- Tìm kiếm phòng theo nhiều tiêu chí

### 📄 Quản Lý Hợp Đồng
- Tạo và quản lý hợp đồng thuê phòng
- Theo dõi thời hạn hợp đồng
- Quản lý phí liên quan hợp đồng

### 💰 Quản Lý Phí
- Quản lý các loại phí: dọn dẹp, điện, nước, bảo trì
- Theo dõi hạn thanh toán
- Cập nhật trạng thái thanh toán

---

## 🏗️ Kiến Trúc Hệ Thống

### 1. Mô Hình Dữ Liệu

- `Student`: Thông tin sinh viên
- `Room`: Thông tin phòng
- `Contract`: Hợp đồng thuê phòng
- `Fee`: Quản lý phí
  
### 2. Web & API

- **Controller:** Xử lý request, trả về view hoặc JSON
- **Repository:** Tương tác cơ sở dữ liệu (Spring Data JPA)
- **Templates:** Giao diện Thymeleaf

### 3. Dashboard & Popup

- Biểu đồ thống kê tổng quan (Chart.js)
- Popup giới thiệu Đại học Phenikaa

---

## 📁 Cấu Trúc Dự Án

```
src/
├── main/
│   ├── java/
│   │   └── com/example/dorm/
│   │       ├── DormApplication.java
│   │       ├── config/
│   │       ├── controller/
│   │       ├── model/
│   │       ├── repository/
│   │       └── service/
│   └── resources/
│       ├── application.properties
│       ├── database.sql
│       ├── static/
│       │   └── css/
│       │       └── style.css
│       └── templates/
│           ├── dashboard.html
│           ├── error.html
│           ├── contracts/
│           ├── fees/
│           ├── rooms/
│           └── students/
└── test/
    └── java/
        └── com/example/dorm/
```

---

## 🛠️ Công Nghệ Sử Dụng

- **Java**: JDK 17+
- **Spring Boot**: 3.x
- **Thymeleaf**: View Template
- **JPA/Hibernate**: ORM
- **JUnit**: Testing
- **Chart.js**: Biểu đồ thống kê

---

## 📋 Yêu Cầu Hệ Thống

- JDK 17 trở lên
- IDE hỗ trợ Java (IntelliJ, VS Code, Eclipse)
- CSDL: H2/MySQL/PostgreSQL
- Trình duyệt hiện đại

---

## 🚀 Hướng Dẫn Cài Đặt & Chạy

1. Clone repository
    ```bash
    git clone https://github.com/BachNguyenn/quanlyktx-springboot.git
    ```

2. Cấu hình database trong `src/main/resources/application.properties`

3. Chạy ứng dụng
    ```bash
    mvn spring-boot:run
    ```

4. Truy cập: [http://localhost:8080](http://localhost:8080)

---

## 🧪 Kiểm Thử

Chạy unit test với JUnit:
```bash
mvn test
```

---

## 👥 Thành Viên

- Nguyễn Tùng Bách - K17 CNTTVJ tại Đại học Phenikaa

---

## 📜 Giấy Phép

> Dự án này là một phần của chương trình học tại PHENIKAA UNIVERSITY.

---

<div align="center">
  <i>Developed with ❤️ by PHENIKAA students</i>
</div>

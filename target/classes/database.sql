-- Create the database if it doesn't exist
CREATE DATABASE IF NOT EXISTS dormitory_db CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;

-- Use the database
USE dormitory_db;

-- Create the Student table
CREATE TABLE student (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    dob DATE,
    gender ENUM('Nam', 'Nữ'), 
    phone VARCHAR(20),
    address VARCHAR(255),
    email VARCHAR(255),
    department VARCHAR(255),
    year INT, -- Suggest: Add CHECK (year BETWEEN 1 AND 4) if supported
    CONSTRAINT chk_email UNIQUE (email)
) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;

-- Create the Room table
CREATE TABLE room (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    number VARCHAR(50) UNIQUE NOT NULL,
    type VARCHAR(50),
    capacity INT NOT NULL,
    price DECIMAL(10,2) DEFAULT 0.00 -- Suggest: Add CHECK (capacity > 0) if supported
) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;

-- Create the Contract table
CREATE TABLE contract (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    student_id BIGINT,
    room_id BIGINT,
    start_date DATE,
    end_date DATE,
    status ENUM('ACTIVE', 'EXPIRED'),
    FOREIGN KEY (student_id) REFERENCES student(id) ON DELETE CASCADE,
    FOREIGN KEY (room_id) REFERENCES room(id) ON DELETE CASCADE
    -- Suggest: Add trigger to ensure room capacity is not exceeded
) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;

-- Create the Fee table
CREATE TABLE fee (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    contract_id BIGINT,
    type VARCHAR(50),
    amount DECIMAL(10,2),
    due_date DATE,
    payment_status ENUM('PAID', 'UNPAID'),
    FOREIGN KEY (contract_id) REFERENCES contract(id) ON DELETE CASCADE
) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;

-- Note: Indexes for foreign keys (student_id, room_id, contract_id) are automatically created by MySQL

-- Insert sample data into Student table (đã đổi số điện thoại sang bắt đầu bằng 0)
INSERT INTO student (name, dob, gender, phone, address, email, department, year) VALUES
('Nguyễn Văn An', '2005-03-15', 'Nam', '0912345678', 'Hà Nội', 'an.nguyen@example.com', 'Công nghệ Thông tin', 3),
('Trần Thị Bình', '2005-07-22', 'Nữ', '0987654321', 'Nình Bình', 'binh.tran@example.com', 'Kinh tế', 2),
('Lê Minh Châu', '2003-11-30', 'Nam', '0911223344', 'Nghệ An', 'chau.le@example.com', 'Kỹ thuật Điện', 4),
('Phạm Quốc Đạt', '2004-05-10', 'Nam', '0933445566', 'Thái Nguyên', 'dat.pham@example.com', 'Kỹ thuật Ô tô', 3),
('Hoàng Thị Mai', '2005-09-25', 'Nữ', '0955667788', 'Thanh Hóa', 'mai.hoang@example.com', 'Tâm lý học', 2),
('Nguyễn Văn Hùng', '2004-08-05', 'Nam', '0999887766', 'Hải Phòng', 'hung.nguyen@example.com', 'Sư phạm Toán', 3),
('Trần Thị Lan', '2003-10-10', 'Nữ', '0988776655', 'Đà Nẵng', 'lan.tran@example.com', 'Kỹ thuật phần mềm', 4),
('Lê Văn Nam', '2005-04-20', 'Nam', '0977665544', 'Hồ Chí Minh', 'nam.le@example.com', 'Khoa học Máy tính', 2),
('Phạm Thị Oanh', '2004-09-30', 'Nữ', '0966544433', 'Cần Thơ', 'oanh.pham@example.com', 'Ngôn ngữ Anh', 3),
('Nguyễn Văn Quân', '2003-10-10', 'Nam', '0955443322', 'Bắc Ninh', 'quan.nguyen@example.com', 'Quản trị Kinh doanh', 4),
('Nguyễn Thị Hương', '2005-12-01', 'Nữ', '0944332211', 'Hải Dương','huong.nguyen@example.com', 'Luật Kinh tế', 2),
('Trần Văn Phúc', '2004-06-15', 'Nam', '0933221100', 'Quảng Ninh','phuc.tran@example.com', 'Kỹ thuật Xây dựng', 3);

-- Insert sample data into Room table
INSERT INTO room (number, type, capacity, price) VALUES
('101', 'Phòng tám', 8, 1200000.00),
('102', 'Phòng tám', 8, 1200000.00),
('103', 'Phòng tám', 8, 1200000.00),
('104', 'Phòng tám', 8, 1200000.00),
('105', 'Phòng tám', 8, 1200000.00),
('201', 'Phòng bốn', 4, 2000000.00),
('202', 'Phòng bốn', 4, 2000000.00),
('203', 'Phòng bốn', 4, 2000000.00),
('204', 'Phòng bốn', 4, 2000000.00),
('205', 'Phòng bốn', 4, 2000000.00),
('301', 'Phòng hai', 2, 3000000.00),
('302', 'Phòng hai', 2, 3000000.00);

-- Insert sample data into Contract table
INSERT INTO contract (student_id, room_id, start_date, end_date, status) VALUES
(1, 1, '2025-01-01', '2025-12-31', 'ACTIVE'),
(2, 1, '2025-01-01', '2025-12-31', 'ACTIVE'),
(3, 2, '2025-02-01', '2025-11-30', 'ACTIVE'),
(4, 3, '2025-03-01', '2025-12-31', 'ACTIVE'),
(5, 4, '2025-04-01', '2025-12-31', 'ACTIVE'),
(6, 5, '2025-05-01', '2025-12-31', 'ACTIVE'),
(7, 6, '2025-06-01', '2025-12-31', 'ACTIVE'),
(8, 7, '2025-07-01', '2025-12-31', 'ACTIVE'),
(9, 8, '2025-08-01', '2025-12-31', 'ACTIVE'),
(10, 9, '2025-09-01', '2025-12-31', 'ACTIVE'),
(11, 10, '2025-10-01', '2025-12-31', 'ACTIVE'),
(12, 11, '2025-11-01', '2025-12-31', 'ACTIVE');

-- Insert sample data into Fee table
INSERT INTO fee (contract_id, type, amount, due_date, payment_status) VALUES
(1, 'CLEANING', 150000, '2025-06-30', 'PAID'),
(1, 'ELECTRICITY', 200000, '2025-06-15', 'UNPAID'),
(2, 'CLEANING', 100000, '2025-06-30', 'PAID'),
(2, 'WATER', 124000, '2025-06-15', 'PAID'),
(3, 'CLEANING', 120000, '2025-07-01', 'UNPAID'),
(4, 'MAINTENANCE', 300000, '2025-06-20', 'PAID'),
(5, 'ELECTRICITY', 250000, '2025-06-15', 'UNPAID'),
(6, 'WATER', 150000, '2025-06-30', 'PAID'),
(7, 'CLEANING', 180000, '2025-07-01', 'UNPAID'),
(8, 'MAINTENANCE', 200000, '2025-06-20', 'PAID'),
(9, 'ELECTRICITY', 220000, '2025-06-15', 'UNPAID'),
(10, 'WATER', 130000, '2025-06-30', 'PAID');

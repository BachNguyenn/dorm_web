-- Create the database if it doesn't exist
CREATE DATABASE IF NOT EXISTS dormitory_db CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;

-- Use the database
USE dormitory_db;

-- Create the Student table
CREATE TABLE student (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    dob DATE,
    gender ENUM('MALE', 'FEMALE', 'OTHERS'), -- M: Male, F: Female, O: Other
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
    status ENUM('ACTIVE', 'EXPIRED', 'CANCELLED'),
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
    payment_status ENUM('PAID', 'UNPAID', 'OVERDUE'),
    FOREIGN KEY (contract_id) REFERENCES contract(id) ON DELETE CASCADE
) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;

-- Note: Indexes for foreign keys (student_id, room_id, contract_id) are automatically created by MySQL

-- Insert sample data into Student table
INSERT INTO student (name, dob, gender, phone, address, email, department, year) VALUES
('Nguyễn Văn An', '2002-03-15', 'MALE', '+84912345678', 'Hà Nội', 'an.nguyen@example.com', 'Công nghệ Thông tin', 3),
('Trần Thị Bình', '2003-07-22', 'FEMALE', '+84987654321', 'Nình Bình', 'binh.tran@example.com', 'Kinh tế', 2),
('Lê Minh Châu', '2001-11-30', 'FEMALE', '+84911223344', 'Nghệ An', 'chau.le@example.com', 'Kỹ thuật Điện', 4),
('Phạm Quốc Đạt', '2002-05-10', 'MALE', '+84933445566', 'Thái Nguyên', 'dat.pham@example.com', 'Cơ khí', 3),
('Hoàng Thị Mai', '2003-01-25', 'FEMALE', '+84955667788', 'Thanh Hóa', 'mai.hoang@example.com', 'Hóa học', 2),
('Nguyễn Văn Hùng', '2002-08-05', 'MALE', '+84999887766', 'Hải Phòng', 'hung.nguyen@example.com', 'Toán học', 3),
('Trần Thị Lan', '2001-12-15', 'FEMALE', '+84988776655', 'Đà Nẵng', 'lan.tran@example.com', 'Văn học', 4),
('Lê Văn Nam', '2003-04-20', 'MALE', '+84977665544', 'Hồ Chí Minh', 'nam.le@example.com', 'Khoa học Máy tính', 2);

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
('205', 'Phòng bốn', 4, 2000000.00);

-- Insert sample data into Contract table
INSERT INTO contract (student_id, room_id, start_date, end_date, status) VALUES
(1, 1, '2025-01-01', '2025-12-31', 'ACTIVE'),
(2, 1, '2025-01-01', '2025-12-31', 'ACTIVE'),
(3, 2, '2025-02-01', '2025-11-30', 'ACTIVE'),
(4, 3, '2025-03-01', '2025-12-31', 'ACTIVE');

-- Insert sample data into Fee table
INSERT INTO fee (contract_id, type, amount, due_date, payment_status) VALUES
(1, 'CLEANING', 150000, '2025-06-30', 'PAID'),
(1, 'ELECTRICITY', 200000, '2025-06-15', 'UNPAID'),
(2, 'CLEANING', 100000, '2025-06-30', 'PAID'),
(2, 'WATER', 124000, '2025-06-15', 'PAID'),
(3, 'CLEANING', 120000, '2025-07-01', 'UNPAID'),
(4, 'MAINTENANCE', 300000, '2025-06-20', 'PAID');



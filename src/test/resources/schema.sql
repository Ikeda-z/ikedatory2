CREATE TABLE IF NOT EXISTS students
(
 id INT AUTO_INCREMENT PRIMARY KEY,
 name VARCHAR(100) NOT NULL,
 furigana VARCHAR(100) NOT NULL,
 nickname VARCHAR (100) NOT NULL,
 mailAddress VARCHAR (100) NOT NULL,
 address VARCHAR (100) NOT NULL,
 age INT,
 gender VARCHAR(100),
 remark TEXT,
 isDeleted boolean
);

CREATE TABLE IF NOT EXISTS students_courses
(
id INT AUTO_INCREMENT PRIMARY KEY,
student_id INT NOT NULL,
status_id INT NOT NULL,
course VARCHAR(100) NOT NULL,
course_start_date TIMESTAMP,
course_end_date TIMESTAMP
);

CREATE TABLE IF NOT EXISTS students_courses_status
(
id INT AUTO_INCREMENT PRIMARY KEY,
status VARCHAR(10) NOT NULL
);
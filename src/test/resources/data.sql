INSERT INTO students (name, furigana, nickname,mailAddress, address, age, gender)
VALUES('青木慎太郎','あおきしんたろう','にょろ','hogehoge@Icloud.com','大田区',29,'F'),
      ('福島花子','ふくしまはなこ','けん','tomeid@gmail.com','江戸川区',32,'F'),
      ('池田太郎','いけだたろう','たろ','koekoe@gmail.com','横浜市',18,'M'),
      ('山田幸子','やまださちこ','やま','komikommi@icloud.com','渋谷区',49,'F'),
      ('渡瀬勝','わたせまさる','わか','wataseaaaa@gmail.com','滋賀県',42,'M');

INSERT INTO students_courses(student_id, status_id, course, course_start_date, course_end_date)
VALUES(1,1,'javaスタンダード','2024-04-01 00:00:00','2024-12-20 00:00:00'),
      (2,2,'公務員講座','2024-08-17 00:00:00','2025-08-31 00:00:00'),
      (3,3,'ITパスポート','2024-06-02 00:00:00','2024-11-22 00:00:00'),
      (4,4,'簿記検定','2024-03-04 00:00:00','2024-11-01 00:00:00'),
      (5,5,'デザインコース','2024-05-02 00:00:00','2024-10-31 00:00:00');

INSERT INTO students_courses_status(status)
VALUES('仮申し込み'),
      ('本申し込み'),
      ('受講中'),
      ('仮申し込み'),
      ('本申し込み');
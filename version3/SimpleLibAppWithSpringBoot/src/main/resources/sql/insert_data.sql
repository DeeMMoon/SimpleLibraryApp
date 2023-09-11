INSERT INTO Person (username, date_born, password)
VALUES ('username1', '1990', 'qwe'),
('username2', '1995', 'qwe'),
('username3', '1985', 'qwe');

INSERT INTO Book (title, author, taken_at, year, person_id)
VALUES ('Война и мир', 'Лев Толстой', null, 1869, null ),
('Преступление и наказание', 'Федор Достоевский', null, 1866, null),
('Мастер и Маргарита', 'Михаил Булгаков', null, 1967, null),
('Маленький принц', 'Антуан де Сент-Экзюпери', null, 1943, null),
('Три товарища', 'Эрих Мария Ремарк', null, 1936, null),
('1984', 'Джордж Оруэлл', null, 1949, null),
('451 градус по Фаренгейту', 'Рей Брэдбери', null, 1953, null);

TRUNCATE TABLE person, book
INSERT INTO Person (full_name, date_born)
VALUES ('Иванов Иван Иванович', '1990'),
('Петров Петр Петрович', '1995'),
('Сидоров Сидор Сидорович', '1985'),
('Кузнецова Елена Викторовна', '1980'),
('Смирнов Александр Иванович', '1975'),
('Николаева Ольга Сергеевна', '1992'),
('Козлов Андрей Валерьевич', '1988'),
('Макарова Татьяна Петровна', '1983'),
('Волков Дмитрий Анатольевич', '1998'),
('Романова Екатерина Игоревна', '1991');

INSERT INTO Book (title, author, taken_at, year, person_id)
VALUES ('Война и мир', 'Лев Толстой', null, 1869, null ),
('Преступление и наказание', 'Федор Достоевский', null, 1866, null),
('Мастер и Маргарита', 'Михаил Булгаков', null, 1967, null),
('Маленький принц', 'Антуан де Сент-Экзюпери', null, 1943, null),
('Три товарища', 'Эрих Мария Ремарк', null, 1936, null),
('1984', 'Джордж Оруэлл', null, 1949, null),
('451 градус по Фаренгейту', 'Рей Брэдбери', null, 1953, null);
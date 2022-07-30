insert into team(name) values ('A-02');
insert into team(name) values ('P-03');
insert into team(name) values ('F-01');
insert into team(name) values ('O-04');

insert into disciplines(name) values ('ОПАД');
insert into disciplines(name) values ('Геодезия');
insert into disciplines(name) values ('БЖД');
insert into disciplines(name) values ('Биология');
insert into disciplines(name) values ('Высшая математика');
insert into disciplines(name) values ('Строительная механика');

insert into score(value, description) values ('2','неудовлетворительно');
insert into score(value, description) values ('3','удовлетворительно');
insert into score(value, description) values ('4','хорошо');
insert into score(value, description) values ('5','отлично');

insert into student(name, surname, age, group_id) values ('Руслан','Керимов','20','1');
insert into student(name, surname, age, group_id) values ('Виктор','Верестукин','21','2');
insert into student(name, surname, age, group_id) values ('Петр','Петров','20','3');
insert into student(name, surname, age, group_id) values ('Анна','Лебедева','18','1');
insert into student(name, surname, age, group_id) values ('Светлана','Дроздова','18','2');
insert into student(name, surname, age, group_id) values ('Татьяна','Артемьева','22','3');


insert into list_score(score, discipline, student, description, ls_data) values ('4','2','1','зачет','2013-09-01');
insert into list_score(score, discipline, student, description, ls_data) values ('4','2','1','зачет','2013-10-02');
insert into list_score(score, discipline, student, description, ls_data) values ('4','2','1','зачет','2013-10-02');
insert into list_score(score, discipline, student, description, ls_data) values ('3','1','1','зачет','2013-12-02');
insert into list_score(score, discipline, student, description, ls_data) values ('3','3','1','зачет','2014-01-02');
insert into list_score(score, discipline, student, description, ls_data) values ('4','4','1','зачет','2014-02-02');
insert into list_score(score, discipline, student, description, ls_data) values ('2','5','1','зачет','2014-03-02');
insert into list_score(score, discipline, student, description, ls_data) values ('2','5','1','зачет','2014-06-30');


select * from student;
select * from team;
select * from score;
select * from disciplines;
select * from list_score;

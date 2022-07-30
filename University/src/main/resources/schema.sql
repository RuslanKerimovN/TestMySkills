create schema if not exists university;

drop table if exists team, student, disciplines, score, list_score;

create table team(
    id serial primary key,
    name varchar(50) not null
);

create table student(
    id serial primary key,
    name varchar(50) not null ,
    surname varchar(50) not null,
    age integer not null,
    group_id integer references team(id)
);

create table disciplines(
    id serial primary key,
    name varchar(50) not null
);

create table score(
    id serial primary key,
    value integer not null,
    description varchar(50) not null
);

create table list_score(
    id serial primary key,
    score integer references score(id),
    discipline integer references disciplines(id),
    student integer references student(id),
    description text not null,
    ls_data date not null
);
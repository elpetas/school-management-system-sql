CREATE DATABASE IF NOT EXISTS cs_hu_310_final_project;
USE cs_hu_310_final_project;
DROP TABLE IF EXISTS class_registrations;
DROP TABLE IF EXISTS grades;
DROP TABLE IF EXISTS class_sections;
DROP TABLE IF EXISTS instructors;
DROP TABLE IF EXISTS academic_titles;
DROP TABLE IF EXISTS students;
DROP TABLE IF EXISTS classes;
DROP FUNCTION IF EXISTS convert_to_grade_point;
CREATE TABLE IF NOT EXISTS classes(
 class_id INT AUTO_INCREMENT,
 name VARCHAR(50) NOT NULL,
 description VARCHAR(1000),
 code VARCHAR(10) UNIQUE,
 maximum_students INT DEFAULT 10,
 PRIMARY KEY(class_id)
);
CREATE TABLE IF NOT EXISTS students(
 student_id INT AUTO_INCREMENT,
 first_name VARCHAR(30) NOT NULL,
 last_name VARCHAR(50) NOT NULL,
 birthdate DATE,
 PRIMARY KEY (student_id)
);
CREATE TABLE IF NOT EXISTS academic_titles(
academic_title_id INT AUTO_INCREMENT,
title VARCHAR(255) NOT NULL,
PRIMARY KEY (academic_title_id)
);
CREATE TABLE IF NOT EXISTS instructors(
instructor_id INT AUTO_INCREMENT,
first_name VARCHAR(80) NOT NULL,
last_name VARCHAR(80) NOT NULL,
academic_title_id int REFERENCES academic_titles(academic_title_id),
PRIMARY KEY(instructor_id)
);
CREATE TABLE IF NOT EXISTS terms(
term_id int AUTO_INCREMENT,
name VARCHAR(80) NOT NULL,
primary key(term_id)
);
CREATE TABLE IF NOT EXISTS class_sections(
class_section_id int auto_increment,
class_id int references classes(class_id),
instructor_id int not null references instructors(instructor_id),
PRIMARY KEY(class_section_id),
term_id int,
foreign key (term_id) references terms(term_id) 
);
CREATE TABLE IF NOT EXISTS grades(
grade_id int auto_increment,
letter_grade char(2) not null,
primary key (grade_id) 
);
create TABLE IF NOT exists class_registrations(
class_registration_id int auto_increment,
class_section_id int not null references class_sections(class_section_id),
student_id int not null references students(student_id),
grade_id int references grades(grade_id),
signup_timestamp datetime default current_timestamp,
primary key (class_registration_id)
);
DELIMITER $$
CREATE FUNCTION convert_to_grade_point(letter_grade char(2))
 RETURNS INT
 DETERMINISTIC

BEGIN
 declare result int;
 if letter_grade ="A" then set result =4;
 elseif letter_grade = "B" then set result =3;
  elseif letter_grade = "C" then set result =2;
   elseif letter_grade = "D" then set result =1;
    elseif letter_grade = "F" then set result =0;
 end if;
 return result;
END;
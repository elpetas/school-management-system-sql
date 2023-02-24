use cs_hu_310_final_project;
SELECT first_name, last_name, 
(select count(student_id) from class_registrations where student_id=1) as number_of_classes,
(select  sum(convert_to_grade_point(letter_grade)) from class_registrations join grades on grades.grade_id=class_registrations.grade_id  where student_id=1) as "total points",
(select  avg(convert_to_grade_point(letter_grade)) from class_registrations join grades on grades.grade_id=class_registrations.grade_id  where student_id=1) as "GPA"
FROM students 
where student_id=1;


SELECT distinct first_name, last_name,
(select distinct count(student_id) from class_registrations where student_id=students.student_id) as number_of_classes,
(select  sum(convert_to_grade_point(letter_grade)) from class_registrations join grades on grades.grade_id=class_registrations.grade_id  where student_id=students.student_id) as "total points"
,(select  avg(convert_to_grade_point(letter_grade)) from class_registrations join grades on grades.grade_id=class_registrations.grade_id  where student_id=students.student_id) as "GPA"
FROM students;

select distinct code,count(class_sections.class_id) as "number_of_grades", sum(convert_to_grade_point(letter_grade)) as "total_grade_points", 
avg(convert_to_grade_point(letter_grade)) as "AVG GPA"
from class_registrations
join class_sections on class_registrations.class_section_id= class_sections.class_section_id
join classes on class_sections.class_id= classes.class_id  
join grades on class_registrations.grade_id= grades.grade_id
join terms on class_sections.term_id= terms.term_id
group by class_sections.class_id;

/**/


select first_name,last_name,title,code,classes.name as "class_name",terms.name as "term"
from class_sections
join classes on class_sections.class_id = class_sections.instructor_id
join instructors on class_sections.class_id = instructors.instructor_id
join terms on class_sections.term_id=terms.term_id
join academic_titles on instructors.academic_title_id= academic_titles.academic_title_id
where class_sections.instructor_id=1;

select code,classes.name as "name",terms.name as "term",first_name,last_name
from class_sections
join classes on class_sections.class_id = class_sections.instructor_id
join instructors on class_sections.class_id = instructors.instructor_id
join terms on class_sections.term_id=terms.term_id
join academic_titles on instructors.academic_title_id= academic_titles.academic_title_id
order by code;

/* list all class regitrations for java*/
select  students.student_id, class_registrations.class_section_id,students.first_name,students.last_name,classes.code,classes.Name,terms.name,grades.letter_grade
from class_registrations
join class_sections on class_registrations.class_section_id= class_sections.class_section_id
join classes on class_sections.class_id= classes.class_id  
join grades on class_registrations.grade_id= grades.grade_id
join terms on class_sections.term_id= terms.term_id
join students on class_registrations.student_id=students.student_id;

/*list all the classes by proffesor for java */
select first_name,last_name,title,code,classes.name,terms.name
from class_sections
join instructors on class_sections.instructor_id=instructors.instructor_id
join classes on classes.class_id = classes.class_id
join academic_titles on academic_titles.academic_title_id=instructors.academic_title_id
join terms on class_sections.term_id=terms.term_id
where first_name="arthur" and last_name="putnam";

/*class sections for java*/
select class_sections.class_id,code,classes.name,terms.name
from class_sections 
join classes on classes.class_id=class_sections.class_id
join terms on class_sections.term_id=terms.term_id
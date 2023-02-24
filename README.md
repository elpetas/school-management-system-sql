# School Management System

This is a Java application that allows managing students, classes, and grades in a school. It uses a relational database for storing the data and JDBC for communicating with the database.

## Requirements

To run this application, you will need to have Java and a MySQL database installed on your machine. You will also need to create a database and configure the Database.java file with the database details.

## Usage

To start the application, run the main() method of the SchoolManagementSystem class. You will be presented with a command-line interface where you can enter commands to manage the school data.

## Commands

test connection: Tests the database connection.
list students: Lists all the students.
list classes: Lists all the classes.
list class_sections: Lists all the class sections.
list class_registrations: Lists all the class registrations.
list instructor <first_name> <last_name>: Lists all the classes taught by the instructor.
create student <first_name> <last_name> <birthdate>: Creates a new student.
register student <student_id> <class_section_id>: Registers a student to a class section.
submit grade <student_id> <class_section_id> <letter_grade>: Submits a grade for a student in a class section.
delete student <student_id>: Deletes a student.
help: Lists help information.
quit: Exits the program.

## Classes

###### SchoolManagementSystem

This is the main class of the application. It contains the main() method, which initializes the application and starts the command-line interface.

###### Database

This class provides a database connection and some utility methods for testing the connection and closing the resources.

###### Instructor

This class represents an instructor in the school. It has fields for the first name, last name, academic title, class code, class name, and term name.

## Methods

###### getAllClassesByInstructor

This method takes the first name and last name of an instructor as parameters and retrieves all the classes taught by that instructor. It returns a list of Instructor objects, which can be printed to the console.

###### createNewStudent

This method takes the first name, last name, and birthdate of a student as parameters and creates a new student in the database.

###### registerStudent

This method takes the student ID and class section ID as parameters and registers the student to the class section.

###### deleteStudent

This method takes the student ID as a parameter and deletes the student from the database.

###### listAllClassRegistrations

This method retrieves all the class registrations from the database and prints them to the console.

###### listAllClassSections

This method retrieves all the class sections from the database and prints them to the console.

###### listAllClasses

This method retrieves all the classes from the database and prints them to the console.

###### listAllStudents

This method retrieves all the students from the database and prints them to the console.

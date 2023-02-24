import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * This application will keep track of things like what classes are offered by
 * the school, and which students are registered for those classes and provide
 * basic reporting. This application interacts with a database to store and
 * retrieve data.
 * 
 * @author Johann_Vargas
 */
public class SchoolManagementSystem {

    public static void getAllClassesByInstructor(String first_name, String last_name) {
        Connection connection = null;
        Statement sqlStatement = null;

        try {
        	connection = Database.getDatabaseConnection();
            sqlStatement = connection.createStatement();

             String sql = String.format("select first_name,last_name,title,code,classes.name,terms.name from class_sections join instructors on class_sections.instructor_id=instructors.instructor_id join classes on classes.class_id = classes.class_id "
             		+ "join academic_titles on academic_titles.academic_title_id=instructors.academic_title_id "
             		+ "join terms on class_sections.term_id=terms.term_id "
             		+ "where first_name=%s and last_name=%s;",first_name,last_name);
             ResultSet resultSet = sqlStatement.executeQuery(sql);
             List<Instructor> instructors = new ArrayList<Instructor>();
             while (resultSet.next()) {
            	 String firstNameI = resultSet.getString(1);
            	 String lastNameI = resultSet.getString(2);
            	 String titleI = resultSet.getString(3);
            	 String codeI = resultSet.getString(4);
            	 String classNameI = resultSet.getString(5);
            	 String termNameI = resultSet.getString(6);
            	Instructor instructor = new Instructor (firstNameI,lastNameI,titleI,codeI,classNameI, termNameI); 
            	instructors.add(instructor);
             }
             resultSet.close();
             connection.close();
             for(Instructor instructor:instructors) {
            	 System.out.println(instructor.toString());
             }
            
        } catch (SQLException sqlException) {
            System.out.println("Failed to get class sections");
            System.out.println(sqlException.getMessage());

        } finally {
            try {
                if (sqlStatement != null)
                    sqlStatement.close();
            } catch (SQLException se2) {
            }
            try {
                if (connection != null)
                    connection.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }
        }

    }

    public static void submitGrade(String studentId, String classSectionID, String grade) {
        Connection connection = null;
        Statement sqlStatement = null;

        try {

        	connection = Database.getDatabaseConnection();
    		sqlStatement = connection.createStatement();
    		String sql = String.format("INSERT IGNORE INTO registered_students (student_id, class_section,grade_id) VALUES ('%s' , '%s','%s');", studentId,classSectionID,grade);
    		sqlStatement.executeUpdate(sql, Statement.RETURN_GENERATED_KEYS);
    		connection.close();
        } catch (SQLException sqlException) {
            System.out.println("Failed to submit grade");
            System.out.println(sqlException.getMessage());

        } finally {
            try {
                if (sqlStatement != null)
                    sqlStatement.close();
            } catch (SQLException se2) {
            }
            try {
                if (connection != null)
                    connection.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }
        }
    }

    public static void registerStudent(String studentId, String classSectionID) {
        Connection connection = null;
        Statement sqlStatement = null;

        try {
        	connection = Database.getDatabaseConnection();
    		sqlStatement = connection.createStatement();
    		String sql = String.format("INSERT IGNORE INTO registered_students (student_id, class_section) VALUES ('%s' , '%s');", studentId,classSectionID);
    		sqlStatement.executeUpdate(sql, Statement.RETURN_GENERATED_KEYS);
    		connection.close();
        } catch (SQLException sqlException) {
            System.out.println("Failed to register student");
            System.out.println(sqlException.getMessage());

        } finally {
            try {
                if (sqlStatement != null)
                    sqlStatement.close();
            } catch (SQLException se2) {
            }
            try {
                if (connection != null)
                    connection.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }
        }
    }

    public static void deleteStudent(String studentId) {
        Connection connection = null;
        Statement sqlStatement = null;

        try {
        	connection = Database.getDatabaseConnection();
        		sqlStatement = connection.createStatement();
        		String sql = String.format("DELETE FROM students WHERE id = %s;", studentId);
                sqlStatement.executeUpdate(sql);
        		connection.close();
        } catch (SQLException sqlException) {
            System.out.println("Failed to delete student");
            System.out.println(sqlException.getMessage());

        } finally {
            try {
                if (sqlStatement != null)
                    sqlStatement.close();
            } catch (SQLException se2) {
            }
            try {
                if (connection != null)
                    connection.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }
        }
    }


    public static void createNewStudent(String firstName, String lastName, String birthdate) {
        Connection connection = null;
        Statement sqlStatement = null;

        try {
        	connection = Database.getDatabaseConnection();
           sqlStatement = connection.createStatement();

            String sql = String.format("INSERT IGNORE INTO students (first_name, last_name,birthdate) VALUES ('%s' , '%s','%s');",
                    firstName, lastName,birthdate);
            sqlStatement.executeUpdate(sql, Statement.RETURN_GENERATED_KEYS);
            connection.close();
        } catch (SQLException sqlException) {
            System.out.println("Failed to create student");
            System.out.println(sqlException.getMessage());

        } finally {
            try {
                if (sqlStatement != null)
                    sqlStatement.close();
            } catch (SQLException se2) {
            }
            try {
                if (connection != null)
                    connection.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }
        }

    }

    public static void listAllClassRegistrations() {
        Connection connection = null;
        Statement sqlStatement = null;

        try {
        	connection = Database.getDatabaseConnection();
            sqlStatement = connection.createStatement();

             String sql = String.format("select  students.student_id, class_registrations.class_section_id,students.first_name,students.last_name,classes.code,classes.Name,terms.name,grades.letter_grade\n"
             		+ "from class_registrations"
             		+ "join class_sections on class_registrations.class_section_id= class_sections.class_section_id"
             		+ "join classes on class_sections.class_id= classes.class_id"
             		+ "join grades on class_registrations.grade_id= grades.grade_id"
             		+ "join terms on class_sections.term_id= terms.term_id join students on class_registrations.student_id=students.student_id;");
             ResultSet resultSet = sqlStatement.executeQuery(sql);
             List<ClassRegistrations> classregistrations = new ArrayList<ClassRegistrations>();
             while (resultSet.next()) {
            	 int StudentID= resultSet.getInt(1);
            	 int classSectionID= resultSet.getInt(2);
            	 String firstName= resultSet.getString(3);
            	 String lastName = resultSet.getString(4);
            	 String code = resultSet.getString(5);
            	 String name = resultSet.getString(6);
            	 String term = resultSet.getString(7);
            	 String letterGrade = resultSet.getString(8);
            	ClassRegistrations classregistration =new ClassRegistrations(StudentID,classSectionID,firstName, 
            			lastName,code,name,term,letterGrade); 
            	classregistrations.add(classregistration);
             }
             resultSet.close();
             connection.close();
             for(ClassRegistrations classregistration:classregistrations) {
            	 System.out.println(classregistration.toString());
             }
        } catch (SQLException sqlException) {
            System.out.println("Failed to get class sections");
            System.out.println(sqlException.getMessage());

        } finally {
            try {
                if (sqlStatement != null)
                    sqlStatement.close();
            } catch (SQLException se2) {
            }
            try {
                if (connection != null)
                    connection.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }
        }
    }

    public static void listAllClassSections() {
        Connection connection = null;
        Statement sqlStatement = null;

        try {
        	connection = Database.getDatabaseConnection();
            sqlStatement = connection.createStatement();

             String sql = String.format("SELECT * FROM students;");
             ResultSet resultSet = sqlStatement.executeQuery(sql);
             List<ClassSection> classSections = new ArrayList<ClassSection>();
             while (resultSet.next()) {
            	 int classIDS = resultSet.getInt(1);
            	 String codeS = resultSet.getString(2);
            	 String classNameS = resultSet.getString(3);
            	 String  termnameS= resultSet.getString(4);
            	ClassSection classsection =new ClassSection(classIDS,codeS,classNameS,termnameS); 
            	classSections.add(classsection);
             }
             resultSet.close();
             connection.close();
             for(ClassSection classsection:classSections) {
            	 System.out.println(classsection.toString());
             }
        } catch (SQLException sqlException) {
            System.out.println("Failed to get class sections");
            System.out.println(sqlException.getMessage());

        } finally {
            try {
                if (sqlStatement != null)
                    sqlStatement.close();
            } catch (SQLException se2) {
            }
            try {
                if (connection != null)
                    connection.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }
        }
    }

    public static void listAllClasses() {
        Connection connection = null;
        Statement sqlStatement = null;

        try {
        	connection = Database.getDatabaseConnection();
            sqlStatement = connection.createStatement();

             String sql = String.format("SELECT class_id,code,name,description FROM classes;");
             ResultSet resultSet = sqlStatement.executeQuery(sql);
             List<Class> classes = new ArrayList<Class>();
             while (resultSet.next()) {
            	int class_id = resultSet.getInt(1);
            	 String code = resultSet.getString(2);
            	 String name = resultSet.getString(3);
            	 String description = resultSet.getString(4);
            	Class classs =new Class(class_id,code,name,description); 
            	classes.add(classs);
             }
             resultSet.close();
             connection.close();
             for(Class classs:classes) {
            	 System.out.println(classs.toString());
             }
        } catch (SQLException sqlException) {
            System.out.println("Failed to get students");
            System.out.println(sqlException.getMessage());

        } finally {
            try {
                if (sqlStatement != null)
                    sqlStatement.close();
            } catch (SQLException se2) {
            }
            try {
                if (connection != null)
                    connection.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }
        }
    }


    public static void listAllStudents() {
        Connection connection = null;
        Statement sqlStatement = null;

        try {
        	connection = Database.getDatabaseConnection();
            sqlStatement = connection.createStatement();

             String sql = String.format("SELECT * FROM students;");
             ResultSet resultSet = sqlStatement.executeQuery(sql);
             List<Student> students = new ArrayList<Student>();
             while (resultSet.next()) {
            	 String student_id = resultSet.getString(1);
            	 String first_name = resultSet.getString(2);
            	 String last_name = resultSet.getString(3);
            	 String birthdate = resultSet.getString(4);
            	Student student =new Student(student_id,first_name,last_name,birthdate); 
            	students.add(student);
             }
             resultSet.close();
             connection.close();
             for(Student student:students) {
            	 System.out.println(student.toString());
             }
        } catch (SQLException sqlException) {
            System.out.println("Failed to get students");
            System.out.println(sqlException.getMessage());

        } finally {
            try {
                if (sqlStatement != null)
                    sqlStatement.close();
            } catch (SQLException se2) {
            }
            try {
                if (connection != null)
                    connection.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }
        }
    }

    /***
     * Splits a string up by spaces. Spaces are ignored when wrapped in quotes.
     *
     * @param command - School Management System cli command
     * @return splits a string by spaces.
     */
    public static List<String> parseArguments(String command) {
        List<String> commandArguments = new ArrayList<String>();
        Matcher m = Pattern.compile("([^\"]\\S*|\".+?\")\\s*").matcher(command);
        while (m.find()) commandArguments.add(m.group(1).replace("\"", ""));
        return commandArguments;
    }

    public static void main(String[] args) {
        System.out.println("Welcome to the School Management System");
        System.out.println("-".repeat(80));

        Scanner scan = new Scanner(System.in);
        String command = "";

        do {
            System.out.print("Command: ");
            command = scan.nextLine();
            ;
            List<String> commandArguments = parseArguments(command);
            command = commandArguments.get(0);
            commandArguments.remove(0);

            if (command.equals("help")) {
                System.out.println("-".repeat(38) + "Help" + "-".repeat(38));
                System.out.println("test connection \n\tTests the database connection");

                System.out.println("list students \n\tlists all the students");
                System.out.println("list classes \n\tlists all the classes");
                System.out.println("list class_sections \n\tlists all the class_sections");
                System.out.println("list class_registrations \n\tlists all the class_registrations");
                System.out.println("list instructor <first_name> <last_name>\n\tlists all the classes taught by that instructor");


                System.out.println("delete student <studentId> \n\tdeletes the student");
                System.out.println("create student <first_name> <last_name> <birthdate> \n\tcreates a student");
                System.out.println("register student <student_id> <class_section_id>\n\tregisters the student to the class section");

                System.out.println("submit grade <studentId> <class_section_id> <letter_grade> \n\tcreates a student");
                System.out.println("help \n\tlists help information");
                System.out.println("quit \n\tExits the program");
            } else if (command.equals("test") && commandArguments.get(0).equals("connection")) {
                Database.testConnection();
            } else if (command.equals("list")) {
                if (commandArguments.get(0).equals("students")) listAllStudents();
                if (commandArguments.get(0).equals("classes")) listAllClasses();
                if (commandArguments.get(0).equals("class_sections")) listAllClassSections();
                if (commandArguments.get(0).equals("class_registrations")) listAllClassRegistrations();

                if (commandArguments.get(0).equals("instructor")) {
                    getAllClassesByInstructor(commandArguments.get(1), commandArguments.get(2));
                }
            } else if (command.equals("create")) {
                if (commandArguments.get(0).equals("student")) {
                    createNewStudent(commandArguments.get(1), commandArguments.get(2), commandArguments.get(3));
                }
            } else if (command.equals("register")) {
                if (commandArguments.get(0).equals("student")) {
                    registerStudent(commandArguments.get(1), commandArguments.get(2));
                }
            } else if (command.equals("submit")) {
                if (commandArguments.get(0).equals("grade")) {
                    submitGrade(commandArguments.get(1), commandArguments.get(2), commandArguments.get(3));
                }
            } else if (command.equals("delete")) {
                if (commandArguments.get(0).equals("student")) {
                    deleteStudent(commandArguments.get(1));
                }
            } else if (!(command.equals("quit") || command.equals("exit"))) {
                System.out.println(command);
                System.out.println("Command not found. Enter 'help' for list of commands");
            }
            System.out.println("-".repeat(80));
        } while (!(command.equals("quit") || command.equals("exit")));
        System.out.println("Bye!");
    }
}



public class Student {
private String student_id;
private String first_name;
private String last_name;
private String birthdate;
public Student(String student_id,String first_name, String last_name,String birthdate ) {
	this.student_id=student_id;
	this.first_name=first_name;
	this.last_name=last_name;
	this.birthdate=birthdate;
}
public String getStudentId() {
	return student_id;
}
public String getFirstname() {
	return first_name;
}
public String getLastname() {
	return last_name;
}
public String getBrithdate() {
	return birthdate;
}
}

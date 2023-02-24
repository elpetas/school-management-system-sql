public class ClassRegistrations {
	private int StudentID;
	private int classSectionID;
	private String firstName;
	private String lastName;
	private String code;
	private String name;
	private String term;
	private String letterGrade;
	public ClassRegistrations(int StudentID,int classSectionID, String firstName, String lastName, String code, String name
			,String term, String letterGrade) {
		this.StudentID=StudentID;
		this.classSectionID=classSectionID;
		this.firstName=firstName;
		this.lastName=lastName;
		this.code=code;
		this.name=name;
		this.term=term;
		this.letterGrade=letterGrade;	
	}
	public int getstudentID() {
		return StudentID;
	}
	public int getclassSectionID() {
		return classSectionID;
	}
	public String getfirstName() {
		return firstName;
	}
	public String getlastName() {
		return lastName;
	}
	public String getCode() {
		return code;
	}
	public String getName() {
		return name;
	}
	public String getTerm() {
		return term;
	}
	public String getletterGrade() {
		return letterGrade;
	}
}

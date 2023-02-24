public class Instructor {
private String firstNameI;
private String lastNameI;
private String titleI;
private String codeI;
private String classNameI;
private String termNameI;
public Instructor(String firstNameI,String lastNameI,String titleI,String codeI,String classNameI,String termNameI) {
	this.firstNameI=firstNameI;
	this.lastNameI=lastNameI;
	this.titleI=titleI;
	this.codeI=codeI;
	this.classNameI=classNameI;
	this.termNameI=termNameI;
}
public String getfirstNameI(){
	return firstNameI;
}
public String getlastNameI(){
	return lastNameI;
}
public String gettitleI(){
	return titleI;
}
public String getclassNameI(){
	return classNameI;
}
public String gettermNameI(){
	return termNameI;
}
public String getgetCodeI() {
	return codeI;
}
}

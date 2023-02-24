
public class Class {
private int class_id;
private String code;
private String name;
private String description;
public Class(int class_id,String code, String name, String description) {
	this.class_id=class_id;
	this.code=code;
	this.name=name;
	this.description=description;	
}

public int getClassID() {
	return class_id;
}
public String getName(){
	return name;
}
public String getCode() 
{
	return code;
}
public String getDescription() {
	return description;
}
}
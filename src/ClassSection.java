
public class ClassSection {
private int classIDS;
private String codeS;
private String classNameS;
private String termNameS;
public ClassSection(int classIDS, String codeS, String classNameS,String termNameS) {
	this.classIDS=classIDS;
	this.codeS=codeS;
	this.classNameS=classNameS;
	this.termNameS=termNameS;

}
public int getclassIDC() {
	return classIDS;
} 
public String getcodeS() {
	return codeS;
} 
public String getclassNameS() {
	return classNameS;
}
public String gettermNameS() {
	return termNameS;
}
}

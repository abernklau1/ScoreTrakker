package score_trakker;

/*
 * @class Student
 * @author Peyton Calvert
 * @author Andrew Bernklau
 * @sources
 * @collaborators
 * 
 * Purpose: To hold student name and score information, and to compare this info with other students
 */
public class Student implements Comparable<Student>{
    private String name;
    private int score;

    public Student(String name, int score) {
        this.name = name;
        this.score = score;
    }
    
    @Override
    public String toString() {
        return this.name+" "+this.score;
    }

	@Override
	public int compareTo(Student student) {
		if(this.score == student.score){
            return 0;
        }
		else if(this.score < student.score){
            return -1;
        }
        return 1;
	}
    
}

package score_trakker;
import java.io.FileNotFoundException;
import java.io.FileReader;
/*
 * @class Student
 * @author Peyton Calvert
 * @author Andrew Bernklau
 * @sources
 * @collaborators
 * 
 * Purpose: To hold student name and score information, and to compare this info with other students
 */
import java.util.ArrayList;
import java.util.Scanner;

public class ScoreTrakker {
	private ArrayList<Student> students;
	private String[] files = {"scores.txt", "badscore.txt", "nofile.txt" };
    
	ScoreTrakker() {
		students = new ArrayList<Student> ();
	}
	
	/*
	 * Reads student data from a file and stores student objects in the ArrayList
	 */
	public void loadDataFile(String fileName) throws FileNotFoundException {
        
		FileReader reader = new FileReader(fileName);
		Scanner scanner = new Scanner(reader);
		int lineNumber = 0;
		String studentName = "";
		int studentScore = 0;
		while (scanner.hasNextLine()) {
			if (lineNumber % 2 == 0) {
				studentName = scanner.nextLine();
			} else {
				String strScore = scanner.nextLine();
				try {
					studentScore = Integer.parseInt(strScore);
				} catch( Exception NumberFormatException) {
					System.out.println("Incorrect format for " + studentName + " not a valid score: " + strScore);
				}
				
				students.add(new Student(studentName, studentScore));
			}
			lineNumber++;
			
		}
	}
	
    
	/*
	 * Sorts and prints ArrayList
	 */
	public void printInOrder() {
		//first sort using selection sort
        int i, j, min_idx;
        int n = students.size();
        // One by one move boundary of
        // unsorted subarray
        for (i = 0; i < n - 1; i++) {

            // Find the minimum element in
            // unsorted array
            min_idx = i;
            for (j = i + 1; j < n; j++) {
                if (students.get(j).compareTo(students.get(min_idx)) == -1)
                    min_idx = j;
            }

            // Swap the found minimum element
            // with the first element
            Student temp = students.get(min_idx);
            students.set(min_idx, students.get(i));
            students.set(i, temp);
         }
         for(int x=0;x<n;x++){
             System.out.println(students.get(x).toString());
         }
	}


	public void processFiles(){
        try{
            this.loadDataFile("scores.txt");
        }catch(Exception FileNotFoundException){
            
        };
        this.printInOrder();
    }


	public static void main(String[] args) throws FileNotFoundException {
		ScoreTrakker st = new ScoreTrakker();
        st.processFiles();
	}
}

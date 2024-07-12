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
		students = new ArrayList<Student>();
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
			
			// Checks if the line has a name or a score. Names on evens, scores on odd
			if (lineNumber % 2 == 0) {
				studentName = scanner.nextLine();
			} else {
				String strScore = scanner.nextLine();
				
				// Checks for corrects score values
				try {
					studentScore = Integer.parseInt(strScore);
					students.add(new Student(studentName, studentScore));
				} catch( Exception NumberFormatException) {
					System.out.println("Incorrect format for " + studentName + " not a valid score: " + strScore + "\n");
					
				}

				
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
		//loop for printing the student scores list
		System.out.println("Student Score List");
		for(int x=0;x<n;x++){
			System.out.println(students.get(x).toString());
		}
	}

	/*
		Loops through each file in the array and tries to load and print their students
	*/
	public void processFiles(){
		//loop through each file name in files
		for(String str : files){
			//try loading and printing
			try{
				this.loadDataFile(str);
				this.printInOrder();
				System.out.println("");
			}catch(Exception FileNotFoundException){ //if not found print error message and continue
				System.out.println("Can't open file: " + str);
			};
			students.clear();
			

		}

	}


	public static void main(String[] args) throws FileNotFoundException {
		ScoreTrakker st = new ScoreTrakker();
		st.processFiles();
	}
}

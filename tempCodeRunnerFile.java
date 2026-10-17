import java.util.ArrayList;
import java.util.Scanner;

public class StudentGradeTracker {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ArrayList<Double> grades = new ArrayList<>();

        System.out.println("=== Student Grade Tracker ===");
        System.out.print("Enter the number of students: ");
        int numStudents = scanner.nextInt();

        // Input grades for each student
        for (int i = 1; i <= numStudents; i++) {
            System.out.print("Enter grade for student " + i + " (0-100): ");
            double grade = scanner.nextDouble();
            
            // Validate grade range
            if (grade < 0 || grade > 100) {
                System.out.println("Invalid grade! Please enter a value between 0 and 100.");
                i--; // Repeat for the same student
            } else {
                grades.add(grade);
            }
        }

        // If no grades are entered, exit
        if (grades.isEmpty()) {
            System.out.println("No grades recorded.");
            scanner.close();
            return;
        }

        // Calculate statistics
        double sum = 0;
        double highest = grades.get(0);
        double lowest = grades.get(0);

        for (double grade : grades) {
            sum += grade;
            if (grade > highest) {
                highest = grade;
            }
            if (grade < lowest) {
                lowest = grade;
            }
        }

        double average = sum / grades.size();

        // Display Summary Report
        System.out.println("\n--- Summary Report ---");
        System.out.println("Total Students: " + grades.size());
        System.out.println("Average Score: " + String.format("%.2f", average));
        System.out.println("Highest Score: " + highest);
        System.out.println("Lowest Score: " + lowest);

        scanner.close();
    }
}
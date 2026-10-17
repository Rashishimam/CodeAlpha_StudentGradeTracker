import java.util.ArrayList;
import java.util.Scanner;

/**
 * Task 1: Student Grade Tracker
 * CodeAlpha Java Programming Internship
 *
 * This program allows a teacher/user to input student grades,
 * validates the input, and computes the average, highest, and lowest scores,
 * presenting a clean and structured summary report.
 */
public class StudentGradeTracker {

    // Inner class representing a Student record
    public static class Student {
        private final String name;
        private final double score;

        public Student(String name, double score) {
            this.name = name;
            this.score = score;
        }

        public String getName() {
            return name;
        }

        public double getScore() {
            return score;
        }

        public String getLetterGrade() {
            if (score >= 90.0) return "A+";
            if (score >= 80.0) return "A";
            if (score >= 70.0) return "B";
            if (score >= 60.0) return "C";
            if (score >= 50.0) return "D";
            return "F";
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ArrayList<Student> students = new ArrayList<>();

        printHeader();

        // 1. Get total number of students with input validation
        int numStudents = getValidStudentCount(scanner);

        // 2. Input student details & scores with validation
        for (int i = 1; i <= numStudents; i++) {
            System.out.println("\n----------------------------------------");
            System.out.println("Entering details for Student #" + i + ":");
            
            System.out.print("Enter Student Name: ");
            String name = scanner.nextLine().trim();
            if (name.isEmpty()) {
                name = "Student " + i;
            }

            double score = getValidScore(scanner, name);
            students.add(new Student(name, score));
        }

        // 3. Display Detailed & Summary Reports
        displayStudentList(students);
        displaySummaryReport(students);

        System.out.println("\nThank you for using Student Grade Tracker!");
        scanner.close();
    }

    /**
     * Prints the welcoming header for the application.
     */
    private static void printHeader() {
        System.out.println("==================================================");
        System.out.println("           STUDENT GRADE TRACKER SYSTEM           ");
        System.out.println("          CodeAlpha Java Programming Task         ");
        System.out.println("==================================================");
    }

    /**
     * Prompts the user for the number of students with validation.
     */
    private static int getValidStudentCount(Scanner scanner) {
        int count = -1;
        while (count <= 0) {
            System.out.print("\nEnter the number of students: ");
            String input = scanner.nextLine().trim();
            try {
                count = Integer.parseInt(input);
                if (count <= 0) {
                    System.out.println("[Error] Please enter a positive integer greater than 0.");
                }
            } catch (NumberFormatException e) {
                System.out.println("[Error] Invalid input! Please enter a valid whole number.");
            }
        }
        return count;
    }

    /**
     * Prompts the user for a valid grade score between 0.0 and 100.0.
     */
    private static double getValidScore(Scanner scanner, String studentName) {
        double score = -1;
        while (true) {
            System.out.print("Enter score for " + studentName + " (0 - 100): ");
            String input = scanner.nextLine().trim();
            try {
                score = Double.parseDouble(input);
                if (score < 0.0 || score > 100.0) {
                    System.out.println("[Error] Score must be between 0 and 100. Please try again.");
                } else {
                    return score;
                }
            } catch (NumberFormatException e) {
                System.out.println("[Error] Invalid input! Please enter a numerical grade (e.g., 85.5).");
            }
        }
    }

    /**
     * Displays all student records in a structured table.
     */
    private static void displayStudentList(ArrayList<Student> students) {
        System.out.println("\n\n==================================================");
        System.out.println("                  STUDENT RECORDS                 ");
        System.out.println("==================================================");
        System.out.printf("%-6s | %-20s | %-8s | %-6s\n", "No.", "Name", "Score", "Grade");
        System.out.println("--------------------------------------------------");
        int index = 1;
        for (Student s : students) {
            System.out.printf("%-6d | %-20s | %-8.2f | %-6s\n", 
                index++, s.getName(), s.getScore(), s.getLetterGrade());
        }
        System.out.println("--------------------------------------------------");
    }

    /**
     * Computes and displays statistical summary (Average, Highest, Lowest).
     */
    private static void displaySummaryReport(ArrayList<Student> students) {
        if (students == null || students.isEmpty()) {
            System.out.println("\n[Warning] No records available to generate summary.");
            return;
        }

        double sum = 0.0;
        Student highestStudent = students.get(0);
        Student lowestStudent = students.get(0);

        for (Student s : students) {
            sum += s.getScore();
            if (s.getScore() > highestStudent.getScore()) {
                highestStudent = s;
            }
            if (s.getScore() < lowestStudent.getScore()) {
                lowestStudent = s;
            }
        }

        double average = sum / students.size();

        System.out.println("\n==================================================");
        System.out.println("                  SUMMARY REPORT                  ");
        System.out.println("==================================================");
        System.out.printf("Total Students : %d\n", students.size());
        System.out.printf("Average Score  : %.2f\n", average);
        System.out.printf("Highest Score  : %.2f (Achieved by: %s)\n", 
            highestStudent.getScore(), highestStudent.getName());
        System.out.printf("Lowest Score   : %.2f (Achieved by: %s)\n", 
            lowestStudent.getScore(), lowestStudent.getName());
        System.out.println("==================================================");
    }
}


import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Collections;
import java.util.Scanner;

public class Main {

    static Scanner sc = new Scanner(System.in);

    static ArrayList<Integer> studentIds = new ArrayList<>();
    static ArrayList<String> studentNames = new ArrayList<>();
    static ArrayList<Integer> studentMarks = new ArrayList<>();

    static LinkedList<String> attendance = new LinkedList<>();

    public static void main(String[] args) {

        int choice;

        do {
            System.out.println("\n====================================");
            System.out.println("       STUDENT MANAGEMENT SYSTEM");
            System.out.println("====================================");

            System.out.println("1. Add Student");
            System.out.println("2. Display All Students");
            System.out.println("3. Search Student");
            System.out.println("4. Update Student Marks");
            System.out.println("5. Remove Student");
            System.out.println("6. Add Student Attendance");
            System.out.println("7. Display Attendance");
            System.out.println("8. Remove Attendance");
            System.out.println("9. Display Student Statistics");
            System.out.println("10. Sort Student Names");
            System.out.println("11. Exit");

            System.out.print("\nEnter Your Choice: ");
            choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {

                case 1:
                    addStudent();
                    break;

                case 2:
                    displayStudents();
                    break;

                case 3:
                    searchStudent();
                    break;

                case 4:
                    updateMarks();
                    break;

                case 5:
                    removeStudent();
                    break;

                case 6:
                    addAttendance();
                    break;

                case 7:
                    displayAttendance();
                    break;

                case 8:
                    removeAttendance();
                    break;

                case 9:
                    displayStatistics();
                    break;

                case 10:
                    sortNames();
                    break;

                case 11:
                    System.out.println("Exiting Student Management System.");
                    break;

                default:
                    System.out.println("Invalid choice!");
            }

        } while (choice != 11);

        sc.close();
    }

    // 1. Add Student
    static void addStudent() {

        System.out.print("Enter Student ID: ");
        int id = sc.nextInt();
        sc.nextLine();

        if (studentIds.contains(id)) {
            System.out.println("Student ID already exists!");
            return;
        }

        System.out.print("Enter Student Name: ");
        String name = sc.nextLine();

        System.out.print("Enter Student Marks: ");
        int marks = sc.nextInt();
        sc.nextLine();

        if (marks < 0 || marks > 100) {
            System.out.println("Marks must be between 0 and 100.");
            return;
        }

        studentIds.add(id);
        studentNames.add(name);
        studentMarks.add(marks);

        System.out.println("Student Added Successfully!");
    }

    // 2. Display All Students
    static void displayStudents() {

        if (studentNames.isEmpty()) {
            System.out.println("No student records found.");
            return;
        }

        System.out.println("\n--------- STUDENT RECORDS ---------");
        System.out.printf("%-12s %-15s %s\n",
                "Student ID", "Student Name", "Marks");

        for (int i = 0; i < studentNames.size(); i++) {

            System.out.printf("%-12d %-15s %d\n",
                    studentIds.get(i),
                    studentNames.get(i),
                    studentMarks.get(i));
        }

        System.out.println("Total Students: " + studentNames.size());
    }

    // 3. Search Student
    static void searchStudent() {

        System.out.print("Enter Student Name: ");
        String name = sc.nextLine();

        int index = studentNames.indexOf(name);

        if (index != -1) {

            System.out.println("\nStudent Found!");
            System.out.println("Student ID: " + studentIds.get(index));
            System.out.println("Name: " + studentNames.get(index));
            System.out.println("Marks: " + studentMarks.get(index));

        } else {
            System.out.println("Student Not Found!");
        }
    }

    // 4. Update Student Marks
    static void updateMarks() {

        System.out.print("Enter Student Name: ");
        String name = sc.nextLine();

        int index = studentNames.indexOf(name);

        if (index != -1) {

            System.out.print("Enter New Marks: ");
            int marks = sc.nextInt();
            sc.nextLine();

            if (marks < 0 || marks > 100) {
                System.out.println("Marks must be between 0 and 100.");
                return;
            }

            studentMarks.set(index, marks);

            System.out.println("Student Marks Updated Successfully!");

        } else {
            System.out.println("Student Not Found!");
        }
    }

    // 5. Remove Student
    static void removeStudent() {

        System.out.print("Enter Student Name: ");
        String name = sc.nextLine();

        int index = studentNames.indexOf(name);

        if (index != -1) {

            studentIds.remove(index);
            studentNames.remove(index);
            studentMarks.remove(index);

            attendance.remove(name);

            System.out.println("Student Removed Successfully!");

        } else {
            System.out.println("Student Not Found!");
        }
    }

    // 6. Add Student Attendance
    static void addAttendance() {

        System.out.print("Enter Student Name: ");
        String name = sc.nextLine();

        if (!studentNames.contains(name)) {
            System.out.println("Student is not registered!");
            return;
        }

        if (attendance.contains(name)) {
            System.out.println("Attendance already marked!");
            return;
        }

        System.out.println("1. Add at Beginning");
        System.out.println("2. Add at End");
        System.out.print("Enter Choice: ");

        int choice = sc.nextInt();
        sc.nextLine();

        if (choice == 1) {
            attendance.addFirst(name);
        } else if (choice == 2) {
            attendance.addLast(name);
        } else {
            System.out.println("Invalid choice!");
            return;
        }

        System.out.println("Attendance Added Successfully!");
    }

    // 7. Display Attendance
    static void displayAttendance() {

        if (attendance.isEmpty()) {
            System.out.println("No students are present.");
            return;
        }

        System.out.println("\nPresent Students:");

        for (String name : attendance) {
            System.out.println(name);
        }

        System.out.println("Total Present Students: "
                + attendance.size());
    }

    // 8. Remove Attendance
    static void removeAttendance() {

        System.out.print("Enter Student Name: ");
        String name = sc.nextLine();

        if (attendance.contains(name)) {

            attendance.remove(name);
            System.out.println("Attendance Removed Successfully!");

        } else {
            System.out.println("Student is not in attendance list.");
        }
    }

    // 9. Display Student Statistics
    static void displayStatistics() {

        if (studentMarks.isEmpty()) {
            System.out.println("No student records available.");
            return;
        }

        int highest = Collections.max(studentMarks);
        int lowest = Collections.min(studentMarks);

        int total = 0;
        int above75 = 0;

        for (int marks : studentMarks) {

            total = total + marks;

            if (marks > 75) {
                above75++;
            }
        }

        double average = (double) total / studentMarks.size();

        System.out.println("\n--------- STUDENT STATISTICS ---------");
        System.out.println("Total Students: " + studentNames.size());
        System.out.println("Highest Marks: " + highest);
        System.out.println("Lowest Marks: " + lowest);
        System.out.println("Average Marks: " + average);
        System.out.println("Students Above 75: " + above75);
    }

    // 10. Sort Student Names
    static void sortNames() {

        if (studentNames.isEmpty()) {
            System.out.println("No student names available.");
            return;
        }

        ArrayList<String> sortedNames =
                new ArrayList<>(studentNames);

        Collections.sort(sortedNames);

        System.out.println("\nNames in Alphabetical Order:");
        System.out.println(sortedNames);

        Collections.reverse(sortedNames);

        System.out.println("\nNames in Reverse Order:");
        System.out.println(sortedNames);
    }
}

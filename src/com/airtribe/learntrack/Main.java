package com.airtribe.learntrack;

import com.airtribe.learntrack.constants.AppConstants;
import com.airtribe.learntrack.constants.MenuOptions;
import com.airtribe.learntrack.entity.Course;
import com.airtribe.learntrack.entity.Enrollment;
import com.airtribe.learntrack.entity.Student;
import com.airtribe.learntrack.exception.EntityNotFoundException;
import com.airtribe.learntrack.exception.InvalidInputException;
import com.airtribe.learntrack.repository.CourseRepository;
import com.airtribe.learntrack.repository.EnrollmentRepository;
import com.airtribe.learntrack.repository.StudentRepository;
import com.airtribe.learntrack.service.CourseService;
import com.airtribe.learntrack.service.EnrollmentService;
import com.airtribe.learntrack.service.StudentService;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    private static final Scanner scanner = new Scanner(System.in);

    private static StudentService studentService;
    private static CourseService courseService;
    private static EnrollmentService enrollmentService;

    public static void main(String[] args) {
        // Initialize repositories
        StudentRepository studentRepository = new StudentRepository();
        CourseRepository courseRepository = new CourseRepository();
        EnrollmentRepository enrollmentRepository = new EnrollmentRepository();

        // Initialize services
        studentService = new StudentService(studentRepository);
        courseService = new CourseService(courseRepository);
        enrollmentService = new EnrollmentService(enrollmentRepository, studentService, courseService);

        System.out.println(AppConstants.SEPARATOR);
        System.out.println("  Welcome to " + AppConstants.APP_NAME + " v" + AppConstants.APP_VERSION);
        System.out.println("  Student & Course Management System");
        System.out.println(AppConstants.SEPARATOR);

        boolean running = true;
        while (running) {
            printMainMenu();
            int choice = readIntInput();

            switch (choice) {
                case MenuOptions.STUDENT_MANAGEMENT:
                    handleStudentMenu();
                    break;
                case MenuOptions.COURSE_MANAGEMENT:
                    handleCourseMenu();
                    break;
                case MenuOptions.ENROLLMENT_MANAGEMENT:
                    handleEnrollmentMenu();
                    break;
                case MenuOptions.EXIT:
                    running = false;
                    System.out.println("Thank you for using " + AppConstants.APP_NAME + ". Goodbye!");
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }

        scanner.close();
    }

    // ==================== MAIN MENU ====================

    private static void printMainMenu() {
        System.out.println("\n" + AppConstants.THIN_SEPARATOR);
        System.out.println("           MAIN MENU");
        System.out.println(AppConstants.THIN_SEPARATOR);
        System.out.println("  1. Student Management");
        System.out.println("  2. Course Management");
        System.out.println("  3. Enrollment Management");
        System.out.println("  4. Exit");
        System.out.print("Enter your choice: ");
    }

    // ==================== STUDENT MENU ====================

    private static void handleStudentMenu() {
        boolean back = false;
        while (!back) {
            System.out.println("\n" + AppConstants.THIN_SEPARATOR);
            System.out.println("       STUDENT MANAGEMENT");
            System.out.println(AppConstants.THIN_SEPARATOR);
            System.out.println("  1. Add New Student");
            System.out.println("  2. View All Students");
            System.out.println("  3. Search Student by ID");
            System.out.println("  4. Deactivate Student");
            System.out.println("  5. Back to Main Menu");
            System.out.print("Enter your choice: ");

            int choice = readIntInput();

            switch (choice) {
                case MenuOptions.ADD_STUDENT:
                    addStudent();
                    break;
                case MenuOptions.VIEW_ALL_STUDENTS:
                    viewAllStudents();
                    break;
                case MenuOptions.SEARCH_STUDENT_BY_ID:
                    searchStudentById();
                    break;
                case MenuOptions.DEACTIVATE_STUDENT:
                    deactivateStudent();
                    break;
                case MenuOptions.BACK:
                    back = true;
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }

    private static void addStudent() {
        System.out.print("Enter first name: ");
        String firstName = scanner.nextLine();
        System.out.print("Enter last name: ");
        String lastName = scanner.nextLine();
        System.out.print("Enter email (or press Enter to skip): ");
        String email = scanner.nextLine();
        System.out.print("Enter batch: ");
        String batch = scanner.nextLine();

        try {
            Student student = studentService.addStudent(firstName, lastName, email, batch);
            System.out.println("Student added successfully: " + student);
        } catch (InvalidInputException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private static void viewAllStudents() {
        ArrayList<Student> students = studentService.listStudents();
        if (students.isEmpty()) {
            System.out.println("No students found.");
            return;
        }
        System.out.println("\n--- All Students ---");
        for (Student student : students) {
            System.out.println("  " + student);
        }
    }

    private static void searchStudentById() {
        System.out.print("Enter student ID: ");
        int id = readIntInput();
        try {
            Student student = studentService.findStudentById(id);
            System.out.println("Found: " + student);
        } catch (EntityNotFoundException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private static void deactivateStudent() {
        System.out.print("Enter student ID to deactivate: ");
        int id = readIntInput();
        try {
            studentService.deactivateStudent(id);
            System.out.println("Student with ID " + id + " has been deactivated.");
        } catch (EntityNotFoundException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    // ==================== COURSE MENU ====================

    private static void handleCourseMenu() {
        boolean back = false;
        while (!back) {
            System.out.println("\n" + AppConstants.THIN_SEPARATOR);
            System.out.println("       COURSE MANAGEMENT");
            System.out.println(AppConstants.THIN_SEPARATOR);
            System.out.println("  1. Add New Course");
            System.out.println("  2. View All Courses");
            System.out.println("  3. Activate Course");
            System.out.println("  4. Deactivate Course");
            System.out.println("  5. Back to Main Menu");
            System.out.print("Enter your choice: ");

            int choice = readIntInput();

            switch (choice) {
                case MenuOptions.ADD_COURSE:
                    addCourse();
                    break;
                case MenuOptions.VIEW_ALL_COURSES:
                    viewAllCourses();
                    break;
                case MenuOptions.ACTIVATE_COURSE:
                    activateCourse();
                    break;
                case MenuOptions.DEACTIVATE_COURSE:
                    deactivateCourse();
                    break;
                case MenuOptions.BACK:
                    back = true;
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }

    private static void addCourse() {
        System.out.print("Enter course name: ");
        String courseName = scanner.nextLine();
        System.out.print("Enter description: ");
        String description = scanner.nextLine();
        System.out.print("Enter duration in weeks: ");
        int duration = readIntInput();

        try {
            Course course = courseService.addCourse(courseName, description, duration);
            System.out.println("Course added successfully: " + course);
        } catch (InvalidInputException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private static void viewAllCourses() {
        ArrayList<Course> courses = courseService.listCourses();
        if (courses.isEmpty()) {
            System.out.println("No courses found.");
            return;
        }
        System.out.println("\n--- All Courses ---");
        for (Course course : courses) {
            System.out.println("  " + course);
        }
    }

    private static void activateCourse() {
        System.out.print("Enter course ID to activate: ");
        int id = readIntInput();
        try {
            courseService.activateCourse(id);
            System.out.println("Course with ID " + id + " has been activated.");
        } catch (EntityNotFoundException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private static void deactivateCourse() {
        System.out.print("Enter course ID to deactivate: ");
        int id = readIntInput();
        try {
            courseService.deactivateCourse(id);
            System.out.println("Course with ID " + id + " has been deactivated.");
        } catch (EntityNotFoundException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    // ==================== ENROLLMENT MENU ====================

    private static void handleEnrollmentMenu() {
        boolean back = false;
        while (!back) {
            System.out.println("\n" + AppConstants.THIN_SEPARATOR);
            System.out.println("     ENROLLMENT MANAGEMENT");
            System.out.println(AppConstants.THIN_SEPARATOR);
            System.out.println("  1. Enroll Student in Course");
            System.out.println("  2. View Enrollments by Student");
            System.out.println("  3. Mark Enrollment as Completed");
            System.out.println("  4. Cancel Enrollment");
            System.out.println("  5. Back to Main Menu");
            System.out.print("Enter your choice: ");

            int choice = readIntInput();

            switch (choice) {
                case MenuOptions.ENROLL_STUDENT:
                    enrollStudent();
                    break;
                case MenuOptions.VIEW_ENROLLMENTS_BY_STUDENT:
                    viewEnrollmentsByStudent();
                    break;
                case MenuOptions.MARK_ENROLLMENT_COMPLETED:
                    markEnrollmentCompleted();
                    break;
                case MenuOptions.CANCEL_ENROLLMENT:
                    cancelEnrollment();
                    break;
                case MenuOptions.BACK:
                    back = true;
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }

    private static void enrollStudent() {
        System.out.print("Enter student ID: ");
        int studentId = readIntInput();
        System.out.print("Enter course ID: ");
        int courseId = readIntInput();

        try {
            Enrollment enrollment = enrollmentService.enrollStudent(studentId, courseId);
            System.out.println("Enrollment successful: " + enrollment);
        } catch (EntityNotFoundException | InvalidInputException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private static void viewEnrollmentsByStudent() {
        System.out.print("Enter student ID: ");
        int studentId = readIntInput();

        try {
            ArrayList<Enrollment> enrollments = enrollmentService.getEnrollmentsByStudentId(studentId);
            if (enrollments.isEmpty()) {
                System.out.println("No enrollments found for student ID " + studentId + ".");
                return;
            }
            System.out.println("\n--- Enrollments for Student ID " + studentId + " ---");
            for (Enrollment enrollment : enrollments) {
                System.out.println("  " + enrollment);
            }
        } catch (EntityNotFoundException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private static void markEnrollmentCompleted() {
        System.out.print("Enter enrollment ID: ");
        int enrollmentId = readIntInput();

        try {
            enrollmentService.markCompleted(enrollmentId);
            System.out.println("Enrollment " + enrollmentId + " marked as COMPLETED.");
        } catch (EntityNotFoundException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private static void cancelEnrollment() {
        System.out.print("Enter enrollment ID: ");
        int enrollmentId = readIntInput();

        try {
            enrollmentService.cancelEnrollment(enrollmentId);
            System.out.println("Enrollment " + enrollmentId + " has been CANCELLED.");
        } catch (EntityNotFoundException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    // ==================== UTILITY ====================

    private static int readIntInput() {
        while (true) {
            try {
                String input = scanner.nextLine();
                return Integer.parseInt(input.trim());
            } catch (NumberFormatException e) {
                System.out.print("Invalid input. Please enter a number: ");
            }
        }
    }
}

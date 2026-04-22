# LearnTrack - Student & Course Management System

A console-based Student & Course Management System built using Core Java.

## Features

- **Student Management** – Add, view, search, and deactivate students
- **Course Management** – Add, view, activate, and deactivate courses
- **Enrollment Management** – Enroll students in courses, view enrollments, mark as completed or cancelled

## Project Structure

```
src/
└── com/airtribe/learntrack/
    ├── Main.java                  // Menu-driven console UI (entry point)
    ├── entity/                    // Domain objects
    │   ├── Person.java            // Base class (inheritance)
    │   ├── Student.java           // Extends Person
    │   ├── Course.java
    │   └── Enrollment.java
    ├── repository/                // In-memory data storage (ArrayList)
    │   ├── StudentRepository.java
    │   ├── CourseRepository.java
    │   └── EnrollmentRepository.java
    ├── service/                   // Business logic layer
    │   ├── StudentService.java
    │   ├── CourseService.java
    │   └── EnrollmentService.java
    ├── exception/                 // Custom exceptions
    │   ├── EntityNotFoundException.java
    │   └── InvalidInputException.java
    ├── util/                      // Helper utilities
    │   ├── IdGenerator.java       // Static ID counters
    │   └── InputValidator.java
    ├── constants/                 // Application-wide constants
    │   ├── AppConstants.java
    │   └── MenuOptions.java
    └── enums/                     // Fixed values / states
        ├── EnrollmentStatus.java
        └── CourseStatus.java
docs/
    ├── Setup_Instructions.md
    ├── JVM_Basics.md
    └── Design_Notes.md
```

## Prerequisites

- **JDK 17** or later installed
- A terminal or IDE (IntelliJ IDEA / VS Code)

## How to Compile and Run

### Using Terminal

```bash
# Navigate to the project root
cd airtribe-learntrack

# Compile all Java files
javac -d out src/com/airtribe/learntrack/**/*.java src/com/airtribe/learntrack/Main.java

# Run the application
java -cp out com.airtribe.learntrack.Main
```

### Using IntelliJ IDEA

1. Open the project folder in IntelliJ IDEA
2. Mark `src` as the **Sources Root** (right-click `src` → Mark Directory as → Sources Root)
3. Run `Main.java`

## Learning Objectives Covered

- Java basics (variables, data types, control flow)
- Classes, objects, constructors (default, parameterized, overloading)
- Static vs instance members
- OOP principles (encapsulation, inheritance, polymorphism)
- Collections (ArrayList)
- Basic exception handling (custom exceptions, try-catch)
- Clean code and modular design (entity → repository → service → UI)

package com.airtribe.learntrack.service;

import com.airtribe.learntrack.entity.Student;
import com.airtribe.learntrack.exception.EntityNotFoundException;
import com.airtribe.learntrack.exception.InvalidInputException;
import com.airtribe.learntrack.repository.StudentRepository;
import com.airtribe.learntrack.util.IdGenerator;
import com.airtribe.learntrack.util.InputValidator;

import java.util.ArrayList;

public class StudentService {

    private final StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public Student addStudent(String firstName, String lastName, String email, String batch) throws InvalidInputException {
        if (InputValidator.isNullOrEmpty(firstName) || InputValidator.isNullOrEmpty(lastName)) {
            throw new InvalidInputException("First name and last name cannot be empty.");
        }
        if (InputValidator.isNullOrEmpty(batch)) {
            throw new InvalidInputException("Batch cannot be empty.");
        }

        int id = IdGenerator.getNextStudentId();
        Student student;

        if (InputValidator.isNullOrEmpty(email)) {
            student = new Student(id, firstName, lastName, batch);
        } else {
            if (!InputValidator.isValidEmail(email)) {
                throw new InvalidInputException("Invalid email format: " + email);
            }
            student = new Student(id, firstName, lastName, email, batch);
        }

        studentRepository.add(student);
        return student;
    }

    public Student addStudent(String firstName, String lastName, String batch) throws InvalidInputException {
        return addStudent(firstName, lastName, "", batch);
    }

    public ArrayList<Student> listStudents() {
        return studentRepository.findAll();
    }

    public Student findStudentById(int id) throws EntityNotFoundException {
        Student student = studentRepository.findById(id);
        if (student == null) {
            throw new EntityNotFoundException("Student with ID " + id + " not found.");
        }
        return student;
    }

    public void deactivateStudent(int id) throws EntityNotFoundException {
        Student student = findStudentById(id);
        student.setActive(false);
    }

    public void updateStudent(int id, String firstName, String lastName, String email, String batch)
            throws EntityNotFoundException, InvalidInputException {
        Student student = findStudentById(id);

        if (!InputValidator.isNullOrEmpty(firstName)) {
            student.setFirstName(firstName);
        }
        if (!InputValidator.isNullOrEmpty(lastName)) {
            student.setLastName(lastName);
        }
        if (!InputValidator.isNullOrEmpty(email)) {
            if (!InputValidator.isValidEmail(email)) {
                throw new InvalidInputException("Invalid email format: " + email);
            }
            student.setEmail(email);
        }
        if (!InputValidator.isNullOrEmpty(batch)) {
            student.setBatch(batch);
        }
    }
}

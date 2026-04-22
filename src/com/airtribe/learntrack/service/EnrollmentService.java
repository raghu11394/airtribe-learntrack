package com.airtribe.learntrack.service;

import com.airtribe.learntrack.entity.Enrollment;
import com.airtribe.learntrack.enums.EnrollmentStatus;
import com.airtribe.learntrack.exception.EntityNotFoundException;
import com.airtribe.learntrack.exception.InvalidInputException;
import com.airtribe.learntrack.repository.EnrollmentRepository;
import com.airtribe.learntrack.util.IdGenerator;

import java.time.LocalDate;
import java.util.ArrayList;

public class EnrollmentService {

    private final EnrollmentRepository enrollmentRepository;
    private final StudentService studentService;
    private final CourseService courseService;

    public EnrollmentService(EnrollmentRepository enrollmentRepository,
                             StudentService studentService,
                             CourseService courseService) {
        this.enrollmentRepository = enrollmentRepository;
        this.studentService = studentService;
        this.courseService = courseService;
    }

    public Enrollment enrollStudent(int studentId, int courseId)
            throws EntityNotFoundException, InvalidInputException {
        // Validate that the student and course exist
        studentService.findStudentById(studentId);
        courseService.findCourseById(courseId);

        // Check for duplicate active enrollment
        ArrayList<Enrollment> existing = enrollmentRepository.findByStudentId(studentId);
        for (Enrollment e : existing) {
            if (e.getCourseId() == courseId && e.getStatus() == EnrollmentStatus.ACTIVE) {
                throw new InvalidInputException("Student " + studentId
                        + " is already actively enrolled in course " + courseId + ".");
            }
        }

        int id = IdGenerator.getNextEnrollmentId();
        String today = LocalDate.now().toString();
        Enrollment enrollment = new Enrollment(id, studentId, courseId, today);
        enrollmentRepository.add(enrollment);
        return enrollment;
    }

    public ArrayList<Enrollment> getEnrollmentsByStudentId(int studentId) throws EntityNotFoundException {
        studentService.findStudentById(studentId);
        return enrollmentRepository.findByStudentId(studentId);
    }

    public void markCompleted(int enrollmentId) throws EntityNotFoundException {
        Enrollment enrollment = findEnrollmentById(enrollmentId);
        enrollment.setStatus(EnrollmentStatus.COMPLETED);
    }

    public void cancelEnrollment(int enrollmentId) throws EntityNotFoundException {
        Enrollment enrollment = findEnrollmentById(enrollmentId);
        enrollment.setStatus(EnrollmentStatus.CANCELLED);
    }

    private Enrollment findEnrollmentById(int id) throws EntityNotFoundException {
        Enrollment enrollment = enrollmentRepository.findById(id);
        if (enrollment == null) {
            throw new EntityNotFoundException("Enrollment with ID " + id + " not found.");
        }
        return enrollment;
    }
}

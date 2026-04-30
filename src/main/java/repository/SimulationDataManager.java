package repository;

import entity.Exam;
import entity.Question;
import entity.Student;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public final class SimulationDataManager {
    private static final Map<String, Student> STUDENTS_BY_ID = new HashMap<>();
    private static final Map<String, Exam> EXAMS_BY_ID = new HashMap<>();

    private SimulationDataManager() {}

    /**
     * Seeds a mock in-memory environment for the simulation.
     * This method is safe to call multiple times (it will only seed once).
     */
    public static synchronized void initializeMockEnvironment() {
        if (!STUDENTS_BY_ID.isEmpty() || !EXAMS_BY_ID.isEmpty()) {
            return;
        }

        Student student = new Student("ST123", "1234");
        STUDENTS_BY_ID.put(student.getId(), student);

        Exam exam = new Exam(
                "EX01",
                List.of(
                        Question.mcq(
                                "Q1",
                                "Which of the following is a core OOP principle?",
                                List.of("Encapsulation", "Compilation", "Defragmentation", "Overclocking")
                        ),
                        Question.text(
                                "Q2",
                                "Explain the difference between an interface and an abstract class (2-3 lines).",
                                5000
                        ),
                        Question.mcq(
                                "Q3",
                                "What does JUnit primarily help with?",
                                List.of("Unit testing", "Database indexing", "UI rendering", "Network routing")
                        )
                )
        );
        EXAMS_BY_ID.put(exam.getId(), exam);
    }

    public static Optional<Student> findStudentById(String studentId) {
        return Optional.ofNullable(STUDENTS_BY_ID.get(studentId));
    }

    public static Optional<Exam> findExamById(String examId) {
        return Optional.ofNullable(EXAMS_BY_ID.get(examId));
    }

    public static Map<String, Student> getStudentsByIdView() {
        return Map.copyOf(STUDENTS_BY_ID);
    }

    public static Map<String, Exam> getExamsByIdView() {
        return Map.copyOf(EXAMS_BY_ID);
    }
}


package control;

import entity.Exam;
import exception.ExamIdDoesNotExistException;
import exception.QuestionsNotAvailableForExamException;
import exception.SessionLockedException;
import repository.SimulationDataManager;

import java.util.List;

public final class ExamController {
    private ExamController() {}

    public static Exam loadExam(String examId) {
        SimulationDataManager.initializeMockEnvironment();

        Exam exam = SimulationDataManager
                .findExamById(examId)
                .orElseThrow(() -> new ExamIdDoesNotExistException("Exam ID Does Not Exist"));

        List<?> questions = exam.getQuestions();
        if (questions == null || questions.isEmpty()) {
            throw new QuestionsNotAvailableForExamException("Questions Not Available for this Exam.");
        }

        return exam;
    }

    public static void finalizeSubmission(Exam exam) {
        if (exam == null) {
            throw new IllegalArgumentException("exam must not be null");
        }

        if (!SessionMonitor.isSessionActive()) {
            throw new SessionLockedException("Session Locked - Cannot Finalize");
        }

        SessionMonitor.lockSession();

        int total = exam.getQuestions() == null ? 0 : exam.getQuestions().size();
        long answered = ResponseManager.getResponsesView()
                .entrySet()
                .stream()
                .filter(e -> e.getValue() != null && !e.getValue().isBlank())
                .count();

        System.out.println("Submission Summary: answered " + answered + " out of " + total + " questions.");
    }
}


package control;

import entity.Question;
import entity.QuestionType;
import exception.AnswerTextLengthExceedsMaximumLimitException;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public final class ResponseManager {
    private static final Map<String, String> RESPONSES_BY_QUESTION_ID = new HashMap<>();
    private static final Map<String, Question> QUESTIONS_BY_ID = new HashMap<>();

    private ResponseManager() {}

    /**
     * Registers the questions for the current exam so that saveResponse can validate
     * TEXT length constraints based on {@link Question#getMaxTextLength()}.
     */
    public static void registerQuestions(List<Question> questions) {
        QUESTIONS_BY_ID.clear();
        if (questions == null) {
            return;
        }
        for (Question q : questions) {
            if (q != null && q.getId() != null) {
                QUESTIONS_BY_ID.put(q.getId(), q);
            }
        }
    }

    public static void saveResponse(String questionId, String answer) {
        Question q = QUESTIONS_BY_ID.get(questionId);
        if (q != null && q.getType() == QuestionType.TEXT) {
            String safe = answer == null ? "" : answer;
            if (safe.length() > q.getMaxTextLength()) {
                throw new AnswerTextLengthExceedsMaximumLimitException("Answer Text Length Exceeds Maximum Limit");
            }
        }
        RESPONSES_BY_QUESTION_ID.put(questionId, answer);
    }

    public static Optional<String> getResponse(String questionId) {
        return Optional.ofNullable(RESPONSES_BY_QUESTION_ID.get(questionId));
    }

    public static Map<String, String> getResponsesView() {
        return Map.copyOf(RESPONSES_BY_QUESTION_ID);
    }

    static void resetForTests() {
        RESPONSES_BY_QUESTION_ID.clear();
        QUESTIONS_BY_ID.clear();
    }
}


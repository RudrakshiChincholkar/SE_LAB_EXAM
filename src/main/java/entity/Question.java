package entity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public final class Question {
    private final String id;
    private final QuestionType type;
    private final String prompt;
    private final List<String> options; // only for MCQ
    private final int maxTextLength; // only for TEXT

    private Question(String id, QuestionType type, String prompt, List<String> options, int maxTextLength) {
        this.id = Objects.requireNonNull(id, "id");
        this.type = Objects.requireNonNull(type, "type");
        this.prompt = Objects.requireNonNull(prompt, "prompt");
        this.options = options == null ? List.of() : Collections.unmodifiableList(new ArrayList<>(options));
        this.maxTextLength = maxTextLength;
    }

    public static Question mcq(String id, String prompt, List<String> options) {
        Objects.requireNonNull(options, "options");
        if (options.isEmpty()) {
            throw new IllegalArgumentException("MCQ options must not be empty");
        }
        return new Question(id, QuestionType.MCQ, prompt, options, 0);
    }

    public static Question text(String id, String prompt, int maxTextLength) {
        if (maxTextLength <= 0) {
            throw new IllegalArgumentException("maxTextLength must be positive");
        }
        return new Question(id, QuestionType.TEXT, prompt, null, maxTextLength);
    }

    public String getId() {
        return id;
    }

    public QuestionType getType() {
        return type;
    }

    public String getPrompt() {
        return prompt;
    }

    public List<String> getOptions() {
        return options;
    }

    public int getMaxTextLength() {
        return maxTextLength;
    }
}


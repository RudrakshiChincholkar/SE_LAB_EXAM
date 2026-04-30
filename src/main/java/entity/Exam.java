package entity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public final class Exam {
    private final String id;
    private final List<Question> questions;

    public Exam(String id, List<Question> questions) {
        this.id = Objects.requireNonNull(id, "id");
        Objects.requireNonNull(questions, "questions");
        this.questions = Collections.unmodifiableList(new ArrayList<>(questions));
    }

    public String getId() {
        return id;
    }

    public List<Question> getQuestions() {
        return questions;
    }
}


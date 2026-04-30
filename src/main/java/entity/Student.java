package entity;

import java.util.Objects;

public final class Student {
    private final String id;
    private final String otp;

    public Student(String id, String otp) {
        this.id = Objects.requireNonNull(id, "id");
        this.otp = Objects.requireNonNull(otp, "otp");
    }

    public String getId() {
        return id;
    }

    public String getOtp() {
        return otp;
    }
}


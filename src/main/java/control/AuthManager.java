package control;

import entity.Student;
import exception.AccountLockedException;
import exception.AuthenticationFailedException;
import exception.NullCredentialsException;
import exception.StudentRecordNotFoundException;
import repository.SimulationDataManager;

import java.util.HashMap;
import java.util.Map;

public final class AuthManager {
    private static final int MAX_FAILED_ATTEMPTS = 3;
    private static final Map<String, Integer> FAILED_ATTEMPTS_BY_STUDENT_ID = new HashMap<>();

    private AuthManager() {}

    public static boolean validateCredentials(String studentId, String otp) {
        SimulationDataManager.initializeMockEnvironment();

        if (studentId == null || otp == null) {
            throw new NullCredentialsException("Null Credentials Structure");
        }

        Student student = SimulationDataManager
                .findStudentById(studentId)
                .orElseThrow(() -> new StudentRecordNotFoundException("Student Record Not Found"));

        int failedAttempts = FAILED_ATTEMPTS_BY_STUDENT_ID.getOrDefault(studentId, 0);
        if (failedAttempts >= MAX_FAILED_ATTEMPTS) {
            throw new AccountLockedException("Account Locked");
        }

        if (!student.getOtp().equals(otp)) {
            int updated = failedAttempts + 1;
            FAILED_ATTEMPTS_BY_STUDENT_ID.put(studentId, updated);
            if (updated >= MAX_FAILED_ATTEMPTS) {
                throw new AccountLockedException("Account Locked");
            }
            throw new AuthenticationFailedException("Authentication Failed");
        }

        FAILED_ATTEMPTS_BY_STUDENT_ID.remove(studentId);
        return true;
    }

    static void resetForTests() {
        FAILED_ATTEMPTS_BY_STUDENT_ID.clear();
    }
}


package control;

import exception.AccountLockedException;
import exception.AuthenticationFailedException;
import exception.NullCredentialsException;
import exception.StudentRecordNotFoundException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import repository.SimulationDataManager;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class AuthManagerTest {

    @AfterEach
    void tearDown() {
        AuthManager.resetForTests();
        SimulationDataManager.initializeMockEnvironment();
    }

    @Test
    void A_01_01_nullStudentId_throwsNullCredentialsException() {
        assertThrows(NullCredentialsException.class, () -> AuthManager.validateCredentials(null, "1234"));
    }

    @Test
    void A_01_02_nullOtp_throwsNullCredentialsException() {
        assertThrows(NullCredentialsException.class, () -> AuthManager.validateCredentials("ST123", null));
    }

    @Test
    void A_01_03_validCredentials_returnsTrue() {
        assertTrue(AuthManager.validateCredentials("ST123", "1234"));
    }

    @Test
    void A_01_04_invalidId_throwsStudentRecordNotFoundException() {
        assertThrows(StudentRecordNotFoundException.class, () -> AuthManager.validateCredentials("EX999", "1234"));
    }

    @Test
    void A_01_05_invalidOtp_throwsAuthenticationFailedException() {
        assertThrows(AuthenticationFailedException.class, () -> AuthManager.validateCredentials("ST123", "0000"));
    }

    @Test
    void A_01_06_accountLockedOnThirdFailedAttempt_throwsAccountLockedException() {
        assertThrows(AuthenticationFailedException.class, () -> AuthManager.validateCredentials("ST123", "0000"));
        assertThrows(AuthenticationFailedException.class, () -> AuthManager.validateCredentials("ST123", "0000"));
        assertThrows(AccountLockedException.class, () -> AuthManager.validateCredentials("ST123", "0000"));

        assertDoesNotThrow(AuthManager::resetForTests);
    }
}


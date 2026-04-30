# Architectural Rules for Online Exam System (Simulated)

## 1. BCE Adherence (Boundary-Control-Entity)
- Mandatory pattern.
- Boundaries (UI simulations) must do no logic; they only call Control methods.
- Controls (Logic) must do all validations and coordinate with Entities.

## 2. Constraints & Validation
- Time Limit: 2 hours (120 minutes). SessionMonitor must enforce this.
- OTP Authentication: Secure login is inclusion requirement.
- Input Validation: Strict descriptive text limits (e.g., 5000 chars) must throw custom exceptions.
- Data Integrity: Response entity updates must be traceable.

## 3. Technology Stack
- Java, JUnit 5, Mockito. (Or Python/C++ equivalent if discussed, but assume strong OOP).
- Use Custom Exceptions for validation errors to ensure White Box test cases pass.
- NO real database connection; use "InMemoryRepositories" instead.
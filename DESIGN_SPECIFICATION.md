# Design Specification: Attempt Exam (UC-01) Happy Path Flow

## BCE Objects
- B: LoginScreen, ExamPortalUI
- C: AuthManager, ExamController, SessionMonitor
- E: Student, Exam, Question, Response

## Process Phases
1.  Authentication: LoginScreen -> AuthManager (validate)
2.  Loading the Exam: ExamPortalUI -> ExamController -> InMemoryRepository (load questions)
3.  Answering: ExamPortalUI -> ExamController -> Update Response Entity (with timestamp)
4.  Submission: ExamPortalUI -> ExamController -> SessionMonitor (lock) -> Submit.
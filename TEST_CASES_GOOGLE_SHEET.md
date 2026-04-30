

WHITE BOX TESTING 
A.01.01	Method: validateCredentials()Branch: Path: id == null	null studentID, any otp	Exception: "Null Credentials Structure"		TBD
A.01.02	Method: validateCredentials()Branch: Path: otp == null	Valid studentID, null otp	Exception: "Null Credentials Structure"		TBD
A.01.03	Method: validateCredentials()Branch: Path: Valid Credentials (Success Flow)	Valid studentID, Matching otp	Return Boolean true		TBD
A.01.04	Method: validateCredentials()Branch: Path: Invalid ID (Student not found)	studentID = "EX999" (doesn't exist)	Exception: "Student Record Not Found"		TBD
A.01.05	Method: validateCredentials()Branch: Path: Invalid OTP (Mismatched Code)	Valid studentID, otp = "0000" (wrong)	Return Boolean false		TBD
A.01.06	Method: validateCredentials()Branch: Path: Account Locked (Retry count exceeded)	Valid studentID, wrong otp, retries=3	Exception: "Account Locked"		TBD
A.01.07	Method: saveAnswer() (MSG 11-13)Branch: Valid MCQ Persistence	qType=MCQ, answer="1", elapsed=1m	:Response updated, return «return» Acknowledge Save		TBD
A.01.08	Method: saveAnswer() (MSG 11-13)Branch: MCQ Index Out of Bounds	qType=MCQ, answer="9" (max options 4)	Exception: "Invalid MCQ Index Selected"		TBD
A.01.09	Method: saveAnswer() (MSG 11-13)Branch: Valid Descriptive (Short) Persistence	qType=TEXT, answer="A short answer.", elapsed=1m	:Response updated, return «return» Acknowledge Save		TBD
A.01.10	Method: saveAnswer() (MSG 11-13)Branch: Descriptive (Long) Exceeds Max Boundary	qType=TEXT, answer.length() > 5000 (max limit)	Exception: "Answer Text Length Exceeds Maximum Limit"		TBD
A.01.11	Method: saveAnswer() (MSG 11-13)Branch: Persistence Layer/Entity Crash	Valid MCQ/TEXT inputs, DB Connection Lost	Exception: "Response Persistence Failed"		TBD
A.01.12	Method: finalizeSubmission() (MSG 15)Branch: Path: Session is already locked	Valid inputs, examStatus == "Locked" (Timer triggered)	Exception: "Session Locked - Cannot Finalize"		TBD
A.01.13	Method: finalizeSubmission() (MSG 15)Branch: Path: Valid manual submission	Valid inputs, examStatus == "Active"	Exam status updated to "Submitted"		TBD
A.01.14	Method: ExamEntity.getQuestions() (MSG 7)Branch: Exam ID not found	examID not found in database	Exception: "Exam ID Does Not Exist"		TBD



BLACKBOX TESTING 
B.01.01	BVA: Duration Boundary Lower (Exam Start)	elapsedTime = 0 minutes (exactly start time)	ExamPortalUI displays Question 1 and active timer (120:00)		TBD
B.01.02	BVA: Within Allowed Range	elapsedTime = 60 minutes (halfway point)	ExamPortalUI functions normally; timer active (60:00)		TBD
B.01.03	BVA: Maximum Limit Exact (Exam End)	elapsedTime = 120 minutes (2 hours elapsed)	System state transitions to "Locked" and displays "Session Locked" to user.		TBD
B.01.04	BVA: Auto-submission Trigger on Timeout	elapsedTime = 120 minutes, 1 second	System automatically executes finalizeSubmission() logic on current responses.		TBD
B.01.05	ECP: Invalid Duration (Negative Time)	currentTime < startTime (System clock anomaly)	System error dashboard displayed; session cannot begin.		TBD
B.01.06	State: Mandatory Login Enforcement	Student bypasses LoginScreen to access ExamPortalUI	Redirected to LoginScreen with message "Login Required."		TBD
B.01.07	State: Valid Session Workflow	Phase 1 (Success) -> Phase 2 (Success)	Student progresses smoothly to the question dashboard.		TBD
B.01.08	Integrity: Multiple Simultaneous Sessions	Same studentID logs in on Device A and Device B	Device A session is invalidated; Device B given exclusive access.		TBD
B.01.09	Integrity: Browser Lock Enforcement	Student attempts to switch browser tabs while exam is Active	Input methods blocked; Warning displayed to user on dashboard.		TBD
B.01.10	Input: Descriptive Answer Empty Save	Student leaves text area empty and clicks save/progress	System allows the save (represents no answer) with timestamp 1.		TBD
B.01.11	Input: Invalid Data Type (Security)	Student submits text answers with <script> tags (SQL Injection/XSS)	Inputs are sanitized and rendered as plain text.		TBD
B.01.12	Constraints: No questions available for exam	Valid examID selected, but :Exam entity has 0 questions	Error displayed on dashboard: "Questions Not Available for this Exam."		TBD
B.01.13	Constraints: Exam state already Submitted	Student accesses dashboard where examStatus = "Submitted"	The "Start Exam" button is disabled/removed.		TBD
B.01.14	Usability: Autosave Timing	Wait 60 seconds (Auto-save trigger point)	System confirms persistence without user action (e.g., brief message).		TBD
B.01.15	Environment: Local Network Failure during Answer	Valid inputs, Network Connection Lost	Answer is stored locally (cached) and synced when network is restored.		TBD
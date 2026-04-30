package boundary;

import control.AuthManager;
import control.ExamController;
import control.ResponseManager;
import control.SessionMonitor;
import entity.Exam;
import entity.Question;
import entity.QuestionType;
import entity.Student;
import exception.LoginRequiredException;
import repository.SimulationDataManager;

import java.util.List;
import java.util.Scanner;

public final class CLI_Launcher {
    private static final String ANSI_RED = "\u001B[31m";
    private static final String ANSI_RESET = "\u001B[0m";

    private CLI_Launcher() {}

    public static void main(String[] args) {
        SimulationDataManager.initializeMockEnvironment();
        Student loggedInStudent = null;
        Exam loadedExam = null;

        try (Scanner scanner = new Scanner(System.in)) {
            boolean running = true;
            while (running) {
                printMenu();
                String raw = scanner.nextLine().trim();

                if (raw.isEmpty()) {
                    System.out.println(red("Please enter a menu option (1-5)."));
                    continue;
                }

                int choice;
                try {
                    choice = Integer.parseInt(raw);
                } catch (NumberFormatException nfe) {
                    System.out.println(red("Invalid input. Please enter a number (1-5)."));
                    continue;
                }

                try {
                    switch (choice) {
                        case 1 -> {
                            System.out.print("Enter Student ID: ");
                            String studentId = scanner.nextLine().trim();
                            System.out.print("Enter OTP: ");
                            String otp = scanner.nextLine().trim();

                            boolean ok = AuthManager.validateCredentials(studentId, otp);
                            if (ok) {
                                loggedInStudent = SimulationDataManager.findStudentById(studentId).orElse(null);
                                System.out.println("Login successful.");
                            }
                        }
                        case 2 -> {
                            if (loggedInStudent == null) {
                                throw new LoginRequiredException("Login Required.");
                            }
                            System.out.println("Available Exam ID: EX01");
                        }
                        case 3 -> {
                            if (loggedInStudent == null) {
                                throw new LoginRequiredException("Login Required.");
                            }
                            loadedExam = ExamController.loadExam("EX01");
                            ResponseManager.registerQuestions(loadedExam.getQuestions());
                            SessionMonitor.startSession();
                            System.out.println("Exam Started. Timer: 120 minutes remaining.");

                            runExamLoop(scanner, loadedExam.getQuestions());
                        }
                        case 4 -> {
                            if (loggedInStudent == null) {
                                throw new LoginRequiredException("Login Required.");
                            }
                            if (loadedExam == null) {
                                System.out.println(red("No exam loaded. Start the exam first."));
                                break;
                            }

                            ExamController.finalizeSubmission(loadedExam);
                            loggedInStudent = null;
                            System.out.println("Exam submitted successfully. You have been logged out.");
                        }
                        case 5 -> running = false;
                        default -> System.out.println(red("Invalid menu option. Please choose 1-5."));
                    }
                } catch (RuntimeException ex) {
                    if (isCustomValidationException(ex)) {
                        String msg = ex.getMessage();
                        System.out.println(red(msg == null || msg.isBlank() ? ex.getClass().getSimpleName() : msg));
                        continue;
                    }
                    throw ex;
                }
            }
        }
    }

    private static void printMenu() {
        System.out.println();
        System.out.println("=== Online Exam System (Simulation) ===");
        System.out.println("1. Login");
        System.out.println("2. View Exam");
        System.out.println("3. Start Exam");
        System.out.println("4. Submit");
        System.out.println("5. Exit");
        System.out.print("Enter choice: ");
    }

    private static boolean isCustomValidationException(RuntimeException ex) {
        Package p = ex.getClass().getPackage();
        return p != null && "exception".equals(p.getName());
    }

    private static String red(String message) {
        return ANSI_RED + message + ANSI_RESET;
    }

    private static void runExamLoop(Scanner scanner, List<Question> questions) {
        for (int i = 0; i < questions.size(); i++) {
            Question q = questions.get(i);

            boolean saved = false;
            while (!saved) {
                System.out.println();
                System.out.println("Question " + (i + 1) + "/" + questions.size() + " [" + q.getId() + "]");
                System.out.println(q.getPrompt());

                if (q.getType() == QuestionType.MCQ) {
                    List<String> options = q.getOptions();
                    for (int idx = 0; idx < options.size(); idx++) {
                        System.out.println((idx + 1) + ". " + options.get(idx));
                    }
                    System.out.print("Enter option number: ");
                } else {
                    System.out.print("Enter your answer: ");
                }

                String answer = scanner.nextLine();
                try {
                    ResponseManager.saveResponse(q.getId(), answer);
                    saved = true;
                } catch (RuntimeException ex) {
                    if (isCustomValidationException(ex)) {
                        String msg = ex.getMessage();
                        System.out.println(red(msg == null || msg.isBlank() ? ex.getClass().getSimpleName() : msg));
                        System.out.println("Please try again.");
                        continue;
                    }
                    throw ex;
                }
            }
        }

        System.out.println();
        System.out.println("End of exam. Returning to main menu.");
    }
}


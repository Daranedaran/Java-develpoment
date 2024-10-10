import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class User {
    String username;
    String password;

    User(String username, String password) {
        this.username = username;
        this.password = password;
    }
}

class Question {
    String questionText;
    String[] options;
    int correctAnswer;

    Question(String questionText, String[] options, int correctAnswer) {
        this.questionText = questionText;
        this.options = options;
        this.correctAnswer = correctAnswer;
    }
}

public class OnlineExaminationSystem {
    static List<User> users = new ArrayList<>();
    static List<Question> questions = new ArrayList<>();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Example questions
        questions.add(new Question("What is the capital of France?", new String[]{"1. Berlin", "2. Madrid", "3. Paris", "4. Rome"}, 3));
        questions.add(new Question("What is 2 + 2?", new String[]{"1. 3", "2. 4", "3. 5", "4. 6"}, 2));

        System.out.println("Welcome to the Online Examination System");
        System.out.print("Enter your username: ");
        String username = scanner.nextLine();
        System.out.print("Enter your password: ");
        String password = scanner.nextLine();

        User currentUser = new User(username, password);
        users.add(currentUser);

        System.out.println("Login successful! Starting the exam...");

        int score = 0;
        for (Question question : questions) {
            System.out.println(question.questionText);
            for (String option : question.options) {
                System.out.println(option);
            }
            System.out.print("Enter your answer (1-4): ");
            int answer = scanner.nextInt();
            if (answer == question.correctAnswer) {
                score++;
            }
        }

        System.out.println("Exam finished! Your score is: " + score + "/" + questions.size());
    }
}
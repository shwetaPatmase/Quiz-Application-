import java.util.*;

class QuizQuestion {
    private String question;
    private List<String> options;
    private int correctOptionIndex;

    public QuizQuestion(String question, List<String> options, int correctOptionIndex) {
        this.question = question;
        this.options = options;
        this.correctOptionIndex = correctOptionIndex;
    }

    public String getQuestion() {
        return question;
    }

    public List<String> getOptions() {
        return options;
    }

    public int getCorrectOptionIndex() {
        return correctOptionIndex;
    }
}

public class QuizApplication {
    private static final int QUESTION_TIME_LIMIT_SECONDS = 30;
    private static Scanner scanner = new Scanner(System.in);
    private static int score = 0;

    private static void displayQuestion(QuizQuestion question) {
        System.out.println(question.getQuestion());
        List<String> options = question.getOptions();
        for (int i = 0; i < options.size(); i++) {
            System.out.println((i + 1) + ". " + options.get(i));
        }

        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                System.out.println("\nTime's up! Moving to the next question.");
                System.out.println("-----------------------------");
                askQuestion();
            }
        };

        Timer timer = new Timer();
        timer.schedule(task, QUESTION_TIME_LIMIT_SECONDS * 1000);

        System.out.print("Enter your choice: ");
        int userChoice = scanner.nextInt();
        timer.cancel();

        if (userChoice == question.getCorrectOptionIndex() + 1) {
            System.out.println("Correct!");
            score++;
        } else {
            System.out.println("Incorrect!");
        }
        System.out.println("-----------------------------");
    }

    private static void askQuestion() {
        QuizQuestion question1 = new QuizQuestion(
                "What is the capital of India?",
                Arrays.asList("Nagpur", "New Delhi", "Amaravti", "Mumbai"),
                1);

        QuizQuestion question2 = new QuizQuestion(
                "What is 6 * 2?",
                Arrays.asList("13", "12", "5", "6"),
                1);

        QuizQuestion question3 = new QuizQuestion(
                "What is the name of smallest planet in solar system?",
                Arrays.asList("Jupiter", "Mercury", "Venus", "Saturn"),
                1);

        QuizQuestion[] questions = { question1, question2, question3 };

        for (QuizQuestion question : questions) {
            displayQuestion(question);
        }

        System.out.println("Quiz complete! Your score: " + score + "/" + questions.length);
    }

    public static void main(String[] args) {
        askQuestion();
    }
}

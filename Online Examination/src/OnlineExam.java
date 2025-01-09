import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Timer;
import java.util.TimerTask;

// Login Class
class Login extends JFrame implements ActionListener {
    private JButton submitButton;
    private JPanel panel;
    private JLabel userLabel, passLabel;
    private JTextField usernameField;
    private JPasswordField passwordField;

    public Login() {
        userLabel = new JLabel("Username:");
        usernameField = new JTextField(15);

        passLabel = new JLabel("Password:");
        passwordField = new JPasswordField(15);

        submitButton = new JButton("Submit");
        submitButton.addActionListener(this);

        panel = new JPanel(new GridLayout(3, 2));
        panel.add(userLabel);
        panel.add(usernameField);
        panel.add(passLabel);
        panel.add(passwordField);
        panel.add(new JLabel()); // Empty space
        panel.add(submitButton);

        add(panel, BorderLayout.CENTER);
        setTitle("Login Form");
        setSize(400, 150);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String username = usernameField.getText();
        String password = new String(passwordField.getPassword());

        if (!password.isEmpty()) {
            new OnlineTest(username);
            this.dispose();
        } else {
            JOptionPane.showMessageDialog(this, "Password cannot be empty", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}

// OnlineTest Class
class OnlineTest extends JFrame implements ActionListener {
    private JLabel questionLabel, timerLabel;
    private JRadioButton[] options;
    private JButton nextButton, resultButton;
    private ButtonGroup optionsGroup;

    private int score = 0, currentQuestion = 0;
    private Timer timer;
    private int timeLeft = 600;

    private String[][] questions = {
        {"Who is known as the father of Java programming language?", "Charles Babbage", "James Gosling", "M.P. Java", "Blaise Pascal", "1"},
        {"Number of primitive data types in Java are?", "6", "7", "8", "9", "2"},
        {"Where is the System class defined?", "java.lang.package", "java.util.package", "java.io.package", "None", "0"},
        {"Exceptions created by try block are caught in which block?", "catch", "throw", "final", "thrown", "0"},
        {"Which of the following is not an OOP concept in Java?", "Polymorphism", "Inheritance", "Compilation", "Encapsulation", "2"}
    };

    public OnlineTest(String username) {
        super("Online Test for " + username);

        questionLabel = new JLabel();
        timerLabel = new JLabel("Time left: " + timeLeft);

        options = new JRadioButton[4];
        optionsGroup = new ButtonGroup();
        for (int i = 0; i < options.length; i++) {
            options[i] = new JRadioButton();
            optionsGroup.add(options[i]);
        }

        nextButton = new JButton("Next");
        nextButton.addActionListener(this);

        resultButton = new JButton("Finish");
        resultButton.addActionListener(this);
        resultButton.setEnabled(false);

        setLayout(new FlowLayout());
        add(timerLabel);
        add(questionLabel);
        for (JRadioButton option : options) {
            add(option);
        }
        add(nextButton);
        add(resultButton);

        setQuestion();
        setSize(600, 300);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);

        startTimer();
    }

    private void setQuestion() {
        if (currentQuestion < questions.length) {
            String[] q = questions[currentQuestion];
            questionLabel.setText("Q" + (currentQuestion + 1) + ": " + q[0]);
            for (int i = 0; i < options.length; i++) {
                options[i].setText(q[i + 1]);
                options[i].setSelected(false);
            }
        } else {
            nextButton.setEnabled(false);
            resultButton.setEnabled(true);
        }
    }

    private boolean checkAnswer() {
        if (currentQuestion < questions.length) {
            String correctAnswer = questions[currentQuestion][5];
            return options[Integer.parseInt(correctAnswer)].isSelected();
        }
        return false;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == nextButton) {
            if (checkAnswer()) {
                score++;
            }
            currentQuestion++;
            setQuestion();
        } else if (e.getSource() == resultButton) {
            if (checkAnswer()) {
                score++;
            }
            showResults();
        }
    }

    private void startTimer() {
        timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                timerLabel.setText("Time left: " + timeLeft);
                timeLeft--;
                if (timeLeft < 0) {
                    timer.cancel();
                    JOptionPane.showMessageDialog(null, "Time is up! Test submitted automatically.");
                    showResults();
                }
            }
        }, 0, 1000);
    }

    private void showResults() {
        JOptionPane.showMessageDialog(this, "Test submitted successfully!\nYour score: " + score + "/" + questions.length);
        int option = JOptionPane.showConfirmDialog(this, "Do you want to logout?", "Logout", JOptionPane.YES_NO_OPTION);
        if (option == JOptionPane.YES_OPTION) {
            System.exit(0);
        }
    }
}

// Main Class
public class OnlineExam {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new Login());
    }
}

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class GuessGUI extends JFrame {
    private int maxtrials = 3;
    private int attempts = 0;
    private int randomNumber;
    private JLabel instructionLabel;
    private JTextField inputField;
    private JButton submitButton;
    private JLabel resultLabel;
    private JLabel attemptsLabel;
    private List<Integer> attemptedNumbers = new ArrayList<>();

    public GuessGUI() {
        setTitle("Number Guessing Game");
        setSize(400, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new FlowLayout());

        instructionLabel = new JLabel("Enter a number between 0 and 9:");
        inputField = new JTextField(10);
        submitButton = new JButton("Submit");
        resultLabel = new JLabel("");
        attemptsLabel = new JLabel("");

        add(instructionLabel);
        add(inputField);
        add(submitButton);
        add(resultLabel);
        add(attemptsLabel);

        randomNumber = generateRandomNumber();

        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int guess = Integer.parseInt(inputField.getText());

                    if (guess < 0 || guess > 9) {
                        JOptionPane.showMessageDialog(GuessGUI.this, "Please enter a number between 0 and 9.", "Invalid Input", JOptionPane.ERROR_MESSAGE);
                    } else {
                        attemptedNumbers.add(guess);
                        updateAttemptsLabel();
                        checkGuess(guess);
                    }
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(GuessGUI.this, "Please enter a valid number.", "Invalid Input", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    }

    private int generateRandomNumber() {
        Random random = new Random();
        return random.nextInt(10);
    }

    private void checkGuess(int guess) {
        if (guess == randomNumber) {
            resultLabel.setText("You win!");
            submitButton.setEnabled(false);
        } else {
            attempts++;
            if (attempts < maxtrials) {
                resultLabel.setText("Incorrect guess. " + (maxtrials - attempts) + " attempts left.");
            } else {
                resultLabel.setText("Out of attempts. The correct number was " + randomNumber + ".");
                submitButton.setEnabled(false);
            }
        }
    }

    private void updateAttemptsLabel() {
        StringBuilder attemptsText = new StringBuilder("Attempted Numbers: ");
        for (Integer num : attemptedNumbers) {
            attemptsText.append(num).append(" ");
        }
        attemptsLabel.setText(attemptsText.toString());
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                GuessGUI game = new GuessGUI();
                game.setVisible(true);
            }
        });
    }
}
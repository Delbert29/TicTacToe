package TgsGUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class TicTacToe implements ActionListener {
    private JFrame frame;
    private JButton[][] buttons;
    private String currentPlayer;
    private JLabel messageLabel;
    private int turns;

    public TicTacToe() {
        frame = new JFrame("Tic Tac Toe");
        frame.setSize(300, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(3, 3));

        buttons = new JButton[3][3];
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                buttons[row][col] = new JButton("");
                buttons[row][col].addActionListener(this);
                panel.add(buttons[row][col]);
            }
        }

        messageLabel = new JLabel("X's turn");
        frame.add(panel, BorderLayout.CENTER);
        frame.add(messageLabel, BorderLayout.SOUTH);

        currentPlayer = "X";
        turns = 0;

        frame.setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        JButton buttonClicked = (JButton) e.getSource();
        buttonClicked.setText(currentPlayer);
        buttonClicked.setEnabled(false);
        turns++;

        if (checkWin()) {
            messageLabel.setText("Player " + currentPlayer + " WIN!");
            disableAllButtons();
        } else if (turns == 9) {
            messageLabel.setText("It's a draw!");
            disableAllButtons();
        } else {
            currentPlayer = currentPlayer.equals("X") ? "O" : "X";
            messageLabel.setText(currentPlayer + "'s turn");
        }
    }

    private boolean checkWin() {
        // check rows
        for (int row = 0; row < 3; row++) {
            if (buttons[row][0].getText().equals(currentPlayer) &&
                    buttons[row][1].getText().equals(currentPlayer) &&
                    buttons[row][2].getText().equals(currentPlayer)) {
                return true;
            }
        }

        // check columns
        for (int col = 0; col < 3; col++) {
            if (buttons[0][col].getText().equals(currentPlayer) &&
                    buttons[1][col].getText().equals(currentPlayer) &&
                    buttons[2][col].getText().equals(currentPlayer)) {
                return true;
            }
        }

        // check diagonals
        if (buttons[0][0].getText().equals(currentPlayer) &&
                buttons[1][1].getText().equals(currentPlayer) &&
                buttons[2][2].getText().equals(currentPlayer)) {
            return true;
        }

        if (buttons[0][2].getText().equals(currentPlayer) &&
                buttons[1][1].getText().equals(currentPlayer) &&
                buttons[2][0].getText().equals(currentPlayer)) {
            return true;
        }

        return false;
    }

    private void disableAllButtons() {
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                buttons[row][col].setEnabled(false);
            }
        }
    }

    public static void main(String[] args) {
        TicTacToe game = new TicTacToe();
    }
}
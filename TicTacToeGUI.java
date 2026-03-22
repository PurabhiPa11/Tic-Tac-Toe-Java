import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class TicTacToeGUI extends JFrame implements ActionListener {

    JButton[][] buttons = new JButton[3][3];
    char currentPlayer = 'X';
    JLabel statusLabel;

    public TicTacToeGUI() {
       setTitle("Tic Tac Toe");
       setSize(400, 450);
       setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        statusLabel = new JLabel("Player X's Turn", JLabel.CENTER);
        statusLabel.setFont(new Font("Arial", Font.BOLD, 18));

        // Top label
        statusLabel = new JLabel("Player X's Turn", JLabel.CENTER);
        statusLabel.setFont(new Font("Arial", Font.BOLD, 18));
        add(statusLabel, BorderLayout.NORTH);

        // Game board
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(3, 3));

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                buttons[i][j] = new JButton("");
                buttons[i][j].setFont(new Font("Arial", Font.BOLD, 40));
                buttons[i][j].setFocusPainted(false);
                buttons[i][j].addActionListener(this);
                panel.add(buttons[i][j]);
            }
        }

        add(panel, BorderLayout.CENTER);

        // Restart button
        JButton restart = new JButton("Restart");
        restart.setFont(new Font("Arial", Font.BOLD, 14));
        restart.addActionListener(e -> resetGame());
        add(restart, BorderLayout.SOUTH);

        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        JButton clicked = (JButton) e.getSource();

        if (!clicked.getText().equals("")) return;

        clicked.setText(String.valueOf(currentPlayer));

        if (checkWin()) {
            JOptionPane.showMessageDialog(this, "🎉 Player " + currentPlayer + " Wins!");
            resetGame();
        } else if (isBoardFull()) {
            JOptionPane.showMessageDialog(this, "It's a Draw!");
            resetGame();
        } else {
            switchPlayer();
            statusLabel.setText("Player " + currentPlayer + "'s Turn");
        }
    }

    void switchPlayer() {
        currentPlayer = (currentPlayer == 'X') ? 'O' : 'X';
    }

    boolean checkWin() {
        // Rows & Columns
        for (int i = 0; i < 3; i++) {
            if (buttons[i][0].getText().equals(String.valueOf(currentPlayer)) &&
                buttons[i][1].getText().equals(String.valueOf(currentPlayer)) &&
                buttons[i][2].getText().equals(String.valueOf(currentPlayer)))
                return true;

            if (buttons[0][i].getText().equals(String.valueOf(currentPlayer)) &&
                buttons[1][i].getText().equals(String.valueOf(currentPlayer)) &&
                buttons[2][i].getText().equals(String.valueOf(currentPlayer)))
                return true;
        }

        // Diagonals
        if (buttons[0][0].getText().equals(String.valueOf(currentPlayer)) &&
            buttons[1][1].getText().equals(String.valueOf(currentPlayer)) &&
            buttons[2][2].getText().equals(String.valueOf(currentPlayer)))
            return true;

        if (buttons[0][2].getText().equals(String.valueOf(currentPlayer)) &&
            buttons[1][1].getText().equals(String.valueOf(currentPlayer)) &&
            buttons[2][0].getText().equals(String.valueOf(currentPlayer)))
            return true;

        return false;
    }

    boolean isBoardFull() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (buttons[i][j].getText().equals("")) {
                    return false;
                }
            }
        }
        return true;
    }

    void resetGame() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                buttons[i][j].setText("");
            }
        }
        currentPlayer = 'X';
        statusLabel.setText("Player X's Turn");
    }

    public static void main(String[] args) {
        new TicTacToeGUI();
    }
}


package AI;


import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;


public class AITicTacToe implements ActionListener {

    JFrame frame;
    JPanel panel;
    int counter = 0;
    JButton b[] = new JButton[9];
    JButton reset, tempButton, playerButton;
    boolean playerBegins = true, player = true, gameover = false;
    int board[] = new int[9];
    int playerScore = 1, compScore = 2;


    AITicTacToe() {
        super();
        frame = new JFrame();
        panel = new JPanel();
        panel.setLayout(new GridLayout(4, 3));
        initialiseBoard();
        makeGUI();
        frame.add(panel);
        frame.setSize(500, 500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

//
    public void makeGUI() {
        int i = 0;
        while (i < 9) {
            b[i] = new JButton();
            b[i].addActionListener(this);
            b[i].setFont(new Font("Arial", Font.PLAIN, 40));
            panel.add(b[i]);
            i++;
        }
        reset = new JButton("Reset");
        playerButton = new JButton("Player : X");
        tempButton = new JButton();
        reset.addActionListener(this);
        playerButton.addActionListener(this);
        tempButton.addActionListener(this);
        panel.add(reset);
        panel.add(tempButton);
        panel.add(playerButton);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == reset) {
            resetBoard();
        } else if (e.getSource() == playerButton) {
            char symbol;
            playerBegins = !playerBegins;
            if (playerBegins) {
                symbol = 'X';
            } else {
                symbol = 'O';
            }
            ((JButton) e.getSource()).setText("Player : " + symbol);
            resetBoard();

        } else {
            if (!gameover) {
                int j = 0;
                while (j < 9) {
                    if (e.getSource() == b[j]) {
                        break;
                    }
                    j++;
                }
                if (board[j] == 3) {
                    drawBoard(j);
                }
            }

        }
    }

//Clean the board and start with the other player 1st
    public void resetBoard() {
        int i = 0;
        counter = 0;
        gameover = false;
        tempButton.setText("");
        while (i < 9) {
            b[i].setText("");
            board[i] = 3;
            i++;
        }
        if (!playerBegins) {
            player = false;
            compsTurn();
        }
    }

//Initialize board to empty
    public void initialiseBoard() {
        int i = 0;
        while (i < 9) {
            board[i] = 3;
            i++;
        }
    }

//Play Computers Turn
    public void compsTurn() {
        gameover = checkGameOver();
        if (!gameover) {
            int playerAI = posWin(3 * compScore * compScore);
            int playerPerson = posWin(3 * playerScore * playerScore);
            //System.out.println(pp + " \t " + pa);
            if (playerPerson != -1) {
                drawBoard(playerPerson);
            } else {
                if (playerAI != -1) {
                    drawBoard(playerAI);
                } else {
                    int m = make();
                    if (m != -1) {
                        drawBoard(m);
                    } else {
                        drawBoard(findGridPosition());
                    }
                }
            }
        }

    }

//Finds the Position Of mark in Grid
    public int findGridPosition() {
        int k = 0;
        while (k < 9) {
            if (board[k] == 3) {
                return k;
            }
            k++;
        }
        return -1;
    }

//Preferences of Playing Chances
    public int make() {
        if (board[4] == 3) {
            return 4;
        }
        else if(diagonalAttack())
        {
            if (board[1] == 3) {
                return 1;
            } else if (board[3] == 3) {
                return 3;
            } else if (board[5] == 3) {
                return 5;
            } else if (board[7] == 3) {
                return 7;
            }
            else
                return -1;
        }
        else if (board[8] == 3) {
            return 8;
        } else if (board[0] == 3) {
            return 0;
        } else if (board[2] == 3) {
            return 2;
        } else if (board[6] == 3) {
            return 6;
        } else {
            return -1;
        }
    }

//In the Case of a Diagonal attack
    public boolean diagonalAttack() {
        if (board[2] == playerScore && board[6] == playerScore) {
            return true;
        } else if (board[0] == playerScore && board[8] == playerScore) {
            return true;
        } else {
            return false;
        }
    }

//Check if current turn has the possibility of winning
    public int posWin(int c) {
        int j = -1, i = 0;
        if (counter >= 3) {
            return calculateScore(c);
        }
        return j;
    }

//Game Ends In a draw as nether is able to win
    public boolean drawGame() {
        int k = 0;
        while (k < 9) {
            if (board[k] == 3) {
                return false;
            }
            k++;
        }
        return true;
    }

//Updating Inputs onto the board Graphicall
    public void drawBoard(int clicked) {
        String symbol;
        if (player) {
            board[clicked] = playerScore;
            if (playerBegins) {
                symbol = "X";
            } else {
                symbol = "O";
            }
        } else {
            board[clicked] = compScore;
            if (!playerBegins) {
                symbol = "X";
            } else {
                symbol = "O";
            }
        }
        b[clicked].setText(symbol);
        counter++;
        player = !player;
        gameover = checkGameOver();
        if (!player && !gameover) {
            compsTurn();
        }
    }

//Check if game has ended by stating either Win Loss or Draw
    public boolean checkGameOver() {
        if (calculateScore(playerScore * playerScore * playerScore) != -1) {
            tempButton.setText("Player Wins");
            return true;
        } else if (calculateScore(compScore * compScore * compScore) != -1) {
            tempButton.setText("AI Wins");
            return true;
        } else if (drawGame()) {
            tempButton.setText("Game Draw");
            return true;
        } else {
            return false;
        }
    }

//Calculate score Of given Player (Person/AI)
    public int calculateScore(int c) {
        int i = 0;
        while (i < 9) {
            if ((board[i] * board[i + 1] * board[i + 2]) == c) {
                if (board[i] == 3) {
                    return i;
                } else if (board[i + 1] == 3) {
                    return i + 1;
                } else {
                    return i + 2;
                }
            }
            i = i + 3;
        }
        i = 0;
        while (i < 3) {
            if ((board[i] * board[i + 3] * board[i + 6]) == c) {
                if (board[i] == 3) {
                    return i;
                } else if (board[i + 3] == 3) {
                    return i + 3;
                } else {
                    return i + 6;
                }
            }
            i = i + 1;
        }
        if (board[0] * board[4] * board[8] == c) {
            if (board[0] == 3) {
                return 0;
            } else if (board[4] == 3) {
                return 4;
            } else {
                return 8;
            }
        }
        if (board[2] * board[4] * board[6] == c) {
            System.out.print((board[2] * board[4] * board[6]) + " ");
            if (board[2] == 3) {
                return 2;
            } else if (board[4] == 3) {
                return 4;
            } else {
                return 6;
            }
        }
        i = 0;
        return -1;
    }

    public static void main(String[] args) {
        AITicTacToe obj = new AITicTacToe();
    }

}

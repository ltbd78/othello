package han.linsu.othello;

import android.graphics.Color;
import android.widget.Toast;

import java.util.ArrayList;

// isValid() needs to be called before makeMove() to generate direction array
// hasValidMoves() reset direction array
// TODO: check/optimize logic in isValid() and makeMove()

/**
 * Android Othello Game
 *
 * @author Linsu Han, ltbd78@gmail.com, 7/8/2016
 * @version 1.3
 */
public class Othello {
    OthelloView view;
    int[][] board;
    boolean[] direction = new boolean[9];
    static final int NONE = 0;
    static final int BLACK = 1;
    static final int WHITE = 2;
    int currentPlayer;
    int numBlack;
    int numWhite;
    int mode = 0;
    ArrayList<Integer> aiMoves;
    int[] aiChoice = new int[2]; // used for debug

    public Othello(OthelloView view) {
        this.view = view;
        this.board = new int[8][8];
    }

    public void newGame() {
        for (int i = 0; i < board.length; i++)
            for (int j = 0; j < board[i].length; j++) {
                board[i][j] = NONE;
                view.disableButton(i, j);
                view.setNone(i, j);
            }
        view.setWhite(3, 3);
        board[3][3] = WHITE;

        view.setBlack(3, 4);
        board[3][4] = BLACK;

        view.setBlack(4, 3);
        board[4][3] = BLACK;

        view.setWhite(4, 4);
        board[4][4] = WHITE;

        currentPlayer = BLACK;

        view.enableButton(2, 3);
        view.setValid(2, 3);
        view.enableButton(3, 2);
        view.setValid(3, 2);
        view.enableButton(4, 5);
        view.setValid(4, 5);
        view.enableButton(5, 4);
        view.setValid(5, 4);

        updateScore();
        if (view.loop[0].isPlaying()) {
            view.loop[0].stop();
            try {
                view.loop[0].prepare();
            } catch (Exception e) {
                System.out.println("Error!:D");
            }
        }
        view.loop[0].start();
        view.loop[0].setLooping(true);
    }

    //TODO: thread sleep
    public void updateGameBoard(int x, int y) {
        aiClearLastMove();
        if (view.ai.isChecked()) {
            mode = 1;
        } else mode = 0;
        if (isValid(x, y, currentPlayer)) {
            makeMove(x, y);
            updateScore();
            currentPlayer = 3 - currentPlayer;
            if (hasValidMoves(currentPlayer)) {
                updateButtons(currentPlayer);
                if (mode == 1) aiTurn();
            } else {
                currentPlayer = 3 - currentPlayer;
                updateButtons(currentPlayer);
            }
            if (!hasValidMoves(currentPlayer))
                gameOver();
        }
    }

    public boolean isValid(int row, int col, int player) {
        boolean[] directionTemp = new boolean[9];
        if (row >= board.length || row < 0 || col >= board.length || col < 0) return false;
        if (board[row][col] == NONE) {
            for (int j = col + 1; j < board.length - 1; j++) {
                if (board[row][col + 1] == player || board[row][j] == NONE) break;
                if (board[row][j + 1] == player) {
                    directionTemp[5] = true;
                    break;
                }
            }
            for (int j = col - 1; j > 0; j--) {
                if (board[row][col - 1] == player || board[row][j] == NONE) break;
                if (board[row][j - 1] == player) {
                    directionTemp[3] = true;
                    break;
                }
            }
            for (int i = row + 1; i < board.length - 1; i++) {
                if (board[row + 1][col] == player || board[i][col] == NONE) break;
                if (board[i + 1][col] == player) {
                    directionTemp[7] = true;
                    break;
                }
            }
            for (int i = row - 1; i > 0; i--) {
                if (board[row - 1][col] == player || board[i][col] == NONE) break;
                if (board[i - 1][col] == player) {
                    directionTemp[1] = true;
                    break;
                }
            }
            int i = row;
            int j = col;
            while (i + 2 < board.length && j + 2 < board.length) {
                if (board[row + 1][col + 1] == player || board[i + 1][j + 1] == NONE) break;
                if (board[i + 2][j + 2] == player) {
                    directionTemp[8] = true;
                    break;
                }
                i++;
                j++;
            }
            i = row;
            j = col;
            while (i - 1 > 0 && j - 1 > 0) {
                if (board[row - 1][col - 1] == player || board[i - 1][j - 1] == NONE) break;
                if (board[i - 2][j - 2] == player) {
                    directionTemp[0] = true;
                    break;
                }
                i--;
                j--;
            }
            i = row;
            j = col;
            while (i - 1 > 0 && j + 2 < board.length) {
                if (board[row - 1][col + 1] == player || board[i - 1][j + 1] == NONE) break;
                if (board[i - 2][j + 2] == player) {
                    directionTemp[2] = true;
                    break;
                }
                i--;
                j++;
            }
            i = row;
            j = col;
            while (i + 2 < board.length && j - 1 > 0) {
                if (board[row + 1][col - 1] == player || board[i + 1][j - 1] == NONE) break;
                if (board[i + 2][j - 2] == player) {
                    directionTemp[6] = true;
                    break;
                }
                i++;
                j--;
            }
            for (i = 0; i < directionTemp.length; i++) {
                if (directionTemp[i]) {
                    direction = directionTemp;
                    return true;
                }
            }
        }
        return false;
    }

    public void makeMove(int x, int y) {
        board[x][y] = currentPlayer;
        view.setColor(x, y, currentPlayer);
        view.disableButton(x, y);
        for (int i = 0; i < direction.length; i++) {
            if (direction[i]) {
                switch (i) {
                    case 0:
                        int m = x;
                        int n = y;
                        while (m > 0 && n > 0) {
                            if (board[m - 1][n - 1] != currentPlayer) {
                                board[m - 1][n - 1] = currentPlayer;
                                view.setColor(m - 1, n - 1, currentPlayer);
                                view.disableButton(m - 1, n - 1);
                            }
                            if (board[m - 2][n - 2] == currentPlayer)
                                break;
                            m--;
                            n--;
                        }
                        break;
                    case 1:
                        for (int s = x - 1; 0 < s; s--) {
                            if (board[s][y] != currentPlayer) {
                                board[s][y] = currentPlayer;
                                view.setColor(s, y, currentPlayer);
                                view.disableButton(s, y);
                            }
                            if (board[s - 1][y] == currentPlayer)
                                break;
                        }
                        break;
                    case 2:
                        m = x;
                        n = y;
                        while (m > 0 && n < board.length) {
                            if (board[m - 1][n + 1] != currentPlayer) {
                                board[m - 1][n + 1] = currentPlayer;
                                view.setColor(m - 1, n + 1, currentPlayer);
                                view.disableButton(m - 1, n + 1);
                            }
                            if (board[m - 2][n + 2] == currentPlayer)
                                break;
                            m--;
                            n++;
                        }
                        break;
                    case 3:
                        for (int t = y - 1; 0 < t; t--) {
                            if (board[x][t] != currentPlayer) {
                                board[x][t] = currentPlayer;
                                view.setColor(x, t, currentPlayer);
                                view.disableButton(x, t);
                            }
                            if (board[x][t - 1] == currentPlayer)
                                break;
                        }
                        break;
                    case 5:
                        for (int t = y + 1; t < board.length; t++) {
                            if (board[x][t] != currentPlayer) {
                                board[x][t] = currentPlayer;
                                view.setColor(x, t, currentPlayer);
                                view.disableButton(x, t);
                            }
                            if (board[x][t + 1] == currentPlayer)
                                break;
                        }
                        break;
                    case 6:
                        m = x;
                        n = y;
                        while (m < board.length && n > 0) {
                            if (board[m + 1][n - 1] != currentPlayer) {
                                board[m + 1][n - 1] = currentPlayer;
                                view.setColor(m + 1, n - 1, currentPlayer);
                                view.disableButton(m + 1, n - 1);
                            }
                            if (board[m + 2][n - 2] == currentPlayer)
                                break;
                            m++;
                            n--;
                        }
                        break;
                    case 7:
                        for (int s = x + 1; s < board.length; s++) {
                            if (board[s][y] != currentPlayer) {
                                board[s][y] = currentPlayer;
                                view.setColor(s, y, currentPlayer);
                                view.disableButton(s, y);
                            }
                            if (board[s + 1][y] == currentPlayer)
                                break;
                        }
                        break;
                    case 8:
                        m = x;
                        n = y;
                        while (m < board.length && n < board.length) {
                            if (board[m + 1][n + 1] != currentPlayer) {
                                board[m + 1][n + 1] = currentPlayer;
                                view.setColor(m + 1, n + 1, currentPlayer);
                                view.disableButton(m + 1, n + 1);
                            }
                            if (board[m + 2][n + 2] == currentPlayer)
                                break;
                            m++;
                            n++;
                        }
                        break;
                    default:
                        System.out.println("FUBAR");
                        break;
                }
            }
        }
    }

    public boolean hasValidMoves(int player) {
        boolean flag = false;
        loop:
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board.length; j++) {
                flag = isValid(i, j, player);
                if (flag) break loop;
            }
        }
        direction = null;
        return flag;
    }

    // updates using isValid() and !Button.getText("▣")
    public void updateButtons(int player) {
        for (int i = 0; i < board.length; i++)
            for (int j = 0; j < board[i].length; j++)
                if (isValid(i, j, player)) {
                    view.enableButton(i, j);
                    view.setValid(i, j);
                } else {
                    view.disableButton(i, j);
                    if (!view.boardButton[i][j].getText().equals("▣")) // 2 statements if diff txt
                        view.setNone(i, j);
                }
    }

    // counts by color
    public void updateScore() {
        numBlack = 0;
        numWhite = 0;
        for (int i = 0; i < view.boardButton.length; i++)
            for (int j = 0; j < view.boardButton[i].length; j++) {
                if (view.boardButton[i][j].getCurrentTextColor() == (Color.BLACK) ||
                        (currentPlayer == 1 && view.boardButton[i][j].getCurrentTextColor() == (Color.MAGENTA)))
                    numBlack++;
                if (view.boardButton[i][j].getCurrentTextColor() == (Color.WHITE) ||
                        (currentPlayer == 2 && view.boardButton[i][j].getCurrentTextColor() == (Color.MAGENTA)))
                    numWhite++;
            }
        view.score[0].setText(String.format("Black Score: %d", numBlack));
        view.score[1].setText(String.format("White Score: %d", numWhite));
    }

    public void aiTurn() {
        aiMakeMove();
        updateScore();
        currentPlayer = 3 - currentPlayer;
        if (hasValidMoves(currentPlayer)) {
            updateButtons(currentPlayer);
        } else {
            currentPlayer = 3 - currentPlayer;
            updateButtons(currentPlayer);
            if (hasValidMoves(currentPlayer))
                aiTurn();
            else gameOver();
        }
    }

    // TODO: enhance
    public void aiMakeMove() {
        aiMoves = new ArrayList<>();
        for (int i = 0; i < board.length; i++)
            for (int j = 0; j < board[i].length; j++)
                if (view.boardButton[i][j].isEnabled()) {
                    aiMoves.add(i);
                    aiMoves.add(j);
                }
        if (aiMoves.size() == 0) return;
        int a = (int) (Math.random() * aiMoves.size()); // check end array moves
        if (a % 2 != 0) a--;
        int x = aiChoice[0] = aiMoves.get(a);
        int y = aiChoice[1] = aiMoves.get(a + 1);
        if (isValid(x, y, currentPlayer)) { // needed to create direction array
            makeMove(x, y);
            view.boardButton[x][y].setTextColor(Color.MAGENTA);
        }
    }

    public void aiClearLastMove() {
        for (int i = 0; i < board.length; i++)
            for (int j = 0; j < board[i].length; j++)
                if (view.boardButton[i][j].getCurrentTextColor() == (Color.MAGENTA)) {
                    // if curP is white, it means ai was black
                    if (currentPlayer == 2) view.boardButton[i][j].setTextColor(Color.BLACK);
                    if (currentPlayer == 1) view.boardButton[i][j].setTextColor(Color.WHITE);
                }
    }

    public void gameOver() {
        updateScore();
        if (numBlack > numWhite)
            Toast.makeText(view.activity, "The winner is Black!", Toast.LENGTH_LONG).show();
        else if (numBlack < numWhite)
            Toast.makeText(view.activity, "The winner is White!", Toast.LENGTH_LONG).show();
        else
            Toast.makeText(view.activity, "Black and White tied!", Toast.LENGTH_LONG).show();
        if (view.boop[0].isPlaying()) {
            view.boop[0].stop();
            try {
                view.boop[0].prepare();
            } catch (Exception e) {
                System.out.println("error! :D");
            }
        }
        if (view.loop[0].isPlaying()) {
            view.loop[0].stop();
            try {
                view.loop[0].prepare();
            } catch (Exception e) {
                System.out.println("error! :D");
            }
        }
        view.boop[0].start();
    }
}
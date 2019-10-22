package han.linsu.othello;

import android.app.Activity;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.provider.MediaStore;
import android.widget.Button;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

public class OthelloView implements OthelloViewInterface {

    Button[][] boardButton;
    Button newGameButton;
    Activity activity;
    TextView score[];
    MediaPlayer[] boop;
    MediaPlayer[] loop;
    Switch ai;

    public OthelloView(Button[][] boardButton, Button newGameButton, TextView[] score, MediaPlayer[] boop, MediaPlayer[] loop, Switch ai, Activity activity) {
        this.boardButton = boardButton;
        this.newGameButton = newGameButton;
        this.score = score;
        this.boop = boop;
        this.loop = loop;
        this.ai = ai;
        this.activity = activity;
    }

    public void setColor(int x, int y, int player) {
        if (player == 1) {
            setBlack(x, y);
        } else if (player == 2)
            setWhite(x, y);
    }

    public void enableButton(int x, int y) {
        boardButton[x][y].setEnabled(true);
    }

    public void disableButton(int x, int y) {
        boardButton[x][y].setEnabled(false);
    }

    public void setBlack(int x, int y) {
        boardButton[x][y].setText("▣");
        boardButton[x][y].setTextColor(Color.BLACK);
    }

    public void setWhite(int x, int y) {
        boardButton[x][y].setText("▣");
        boardButton[x][y].setTextColor(Color.WHITE);
    }

    public void setValid(int x, int y) {
        boardButton[x][y].setText("*");
        boardButton[x][y].setTextColor(Color.YELLOW);
    }

    public void setNone(int x, int y) {
        boardButton[x][y].setText(".");
        boardButton[x][y].setTextColor(Color.GRAY);
    }
}
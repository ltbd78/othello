package han.linsu.othello;

import android.content.pm.ActivityInfo;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.app.Activity;
import android.view.View;
import android.widget.Button;
import android.widget.Switch;
import android.widget.TextView;

public class MainActivity extends Activity {

    OthelloView view;
    Othello model;
    Switch ai;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState); //Default onCreate statement which is a MUST to be specified
        setContentView(R.layout.activity_main); //Sets the layout of the application as to the one specified in activity_main
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        MediaPlayer[] boop = {MediaPlayer.create(this, R.raw.stage_clear)};
        MediaPlayer[] loop = {MediaPlayer.create(this, R.raw.birabuto_kingdom)};

        Button b00 = (Button) findViewById(R.id.button00);
        Button b01 = (Button) findViewById(R.id.button01);
        Button b02 = (Button) findViewById(R.id.button02);
        Button b03 = (Button) findViewById(R.id.button03);
        Button b04 = (Button) findViewById(R.id.button04);
        Button b05 = (Button) findViewById(R.id.button05);
        Button b06 = (Button) findViewById(R.id.button06);
        Button b07 = (Button) findViewById(R.id.button07);

        Button b10 = (Button) findViewById(R.id.button10);
        Button b11 = (Button) findViewById(R.id.button11);
        Button b12 = (Button) findViewById(R.id.button12);
        Button b13 = (Button) findViewById(R.id.button13);
        Button b14 = (Button) findViewById(R.id.button14);
        Button b15 = (Button) findViewById(R.id.button15);
        Button b16 = (Button) findViewById(R.id.button16);
        Button b17 = (Button) findViewById(R.id.button17);

        Button b20 = (Button) findViewById(R.id.button20);
        Button b21 = (Button) findViewById(R.id.button21);
        Button b22 = (Button) findViewById(R.id.button22);
        Button b23 = (Button) findViewById(R.id.button23);
        Button b24 = (Button) findViewById(R.id.button24);
        Button b25 = (Button) findViewById(R.id.button25);
        Button b26 = (Button) findViewById(R.id.button26);
        Button b27 = (Button) findViewById(R.id.button27);

        Button b30 = (Button) findViewById(R.id.button30);
        Button b31 = (Button) findViewById(R.id.button31);
        Button b32 = (Button) findViewById(R.id.button32);
        Button b33 = (Button) findViewById(R.id.button33);
        Button b34 = (Button) findViewById(R.id.button34);
        Button b35 = (Button) findViewById(R.id.button35);
        Button b36 = (Button) findViewById(R.id.button36);
        Button b37 = (Button) findViewById(R.id.button37);

        Button b40 = (Button) findViewById(R.id.button40);
        Button b41 = (Button) findViewById(R.id.button41);
        Button b42 = (Button) findViewById(R.id.button42);
        Button b43 = (Button) findViewById(R.id.button43);
        Button b44 = (Button) findViewById(R.id.button44);
        Button b45 = (Button) findViewById(R.id.button45);
        Button b46 = (Button) findViewById(R.id.button46);
        Button b47 = (Button) findViewById(R.id.button47);

        Button b50 = (Button) findViewById(R.id.button50);
        Button b51 = (Button) findViewById(R.id.button51);
        Button b52 = (Button) findViewById(R.id.button52);
        Button b53 = (Button) findViewById(R.id.button53);
        Button b54 = (Button) findViewById(R.id.button54);
        Button b55 = (Button) findViewById(R.id.button55);
        Button b56 = (Button) findViewById(R.id.button56);
        Button b57 = (Button) findViewById(R.id.button57);

        Button b60 = (Button) findViewById(R.id.button60);
        Button b61 = (Button) findViewById(R.id.button61);
        Button b62 = (Button) findViewById(R.id.button62);
        Button b63 = (Button) findViewById(R.id.button63);
        Button b64 = (Button) findViewById(R.id.button64);
        Button b65 = (Button) findViewById(R.id.button65);
        Button b66 = (Button) findViewById(R.id.button66);
        Button b67 = (Button) findViewById(R.id.button67);

        Button b70 = (Button) findViewById(R.id.button70);
        Button b71 = (Button) findViewById(R.id.button71);
        Button b72 = (Button) findViewById(R.id.button72);
        Button b73 = (Button) findViewById(R.id.button73);
        Button b74 = (Button) findViewById(R.id.button74);
        Button b75 = (Button) findViewById(R.id.button75);
        Button b76 = (Button) findViewById(R.id.button76);
        Button b77 = (Button) findViewById(R.id.button77);

        Button newGame = (Button) findViewById(R.id.newGame); //Declares and initializes the New Game button

        TextView blackScore = (TextView) findViewById(R.id.blackScore);
        TextView whiteScore = (TextView) findViewById(R.id.whiteScore);
        TextView[] score = {blackScore, whiteScore};
        ai = (Switch) findViewById(R.id.AI);

        Button[][] myButtons = {{b00, b01, b02, b03, b04, b05, b06, b07},
                {b10, b11, b12, b13, b14, b15, b16, b17},
                {b20, b21, b22, b23, b24, b25, b26, b27},
                {b30, b31, b32, b33, b34, b35, b36, b37},
                {b40, b41, b42, b43, b44, b45, b46, b47},
                {b50, b51, b52, b53, b54, b55, b56, b57},
                {b60, b61, b62, b63, b64, b65, b66, b67},
                {b70, b71, b72, b73, b74, b75, b76, b77}};

        view = new OthelloView(myButtons, newGame, score, boop, loop, ai, this); //Initializes the OthelloView object
        model = new Othello(view); //Initializes the Othello object
    }

    /**
     * Gets called whenever a button is clicked as we had specified in the activity_main layout file
     *
     * @param v : The current view
     */
    public void buttonClicked(View v) {
        switch (v.getId()) { //Create a switch for all the IDs we have in the view
            case R.id.button00: //If the element clicked was button at (0, 0)
                model.updateGameBoard(0, 0); //Call updateGameBoard with (0, 0) as arguments
                break;
            case R.id.button01:
                model.updateGameBoard(0, 1);
                break;
            case R.id.button02:
                model.updateGameBoard(0, 2);
                break;
            case R.id.button03:
                model.updateGameBoard(0, 3);
                break;
            case R.id.button04:
                model.updateGameBoard(0, 4);
                break;
            case R.id.button05:
                model.updateGameBoard(0, 5);
                break;
            case R.id.button06:
                model.updateGameBoard(0, 6);
                break;
            case R.id.button07:
                model.updateGameBoard(0, 7);
                break;

            case R.id.button10:
                model.updateGameBoard(1, 0);
                break;
            case R.id.button11:
                model.updateGameBoard(1, 1);
                break;
            case R.id.button12:
                model.updateGameBoard(1, 2);
                break;
            case R.id.button13:
                model.updateGameBoard(1, 3);
                break;
            case R.id.button14:
                model.updateGameBoard(1, 4);
                break;
            case R.id.button15:
                model.updateGameBoard(1, 5);
                break;
            case R.id.button16:
                model.updateGameBoard(1, 6);
                break;
            case R.id.button17:
                model.updateGameBoard(1, 7);
                break;

            case R.id.button20:
                model.updateGameBoard(2, 0);
                break;
            case R.id.button21:
                model.updateGameBoard(2, 1);
                break;
            case R.id.button22:
                model.updateGameBoard(2, 2);
                break;
            case R.id.button23:
                model.updateGameBoard(2, 3);
                break;
            case R.id.button24:
                model.updateGameBoard(2, 4);
                break;
            case R.id.button25:
                model.updateGameBoard(2, 5);
                break;
            case R.id.button26:
                model.updateGameBoard(2, 6);
                break;
            case R.id.button27:
                model.updateGameBoard(2, 7);
                break;
            case R.id.button30:
                model.updateGameBoard(3, 0);
                break;
            case R.id.button31:
                model.updateGameBoard(3, 1);
                break;
            case R.id.button32:
                model.updateGameBoard(3, 2);
                break;
            case R.id.button33:
                model.updateGameBoard(3, 3);
                break;
            case R.id.button34:
                model.updateGameBoard(3, 4);
                break;
            case R.id.button35:
                model.updateGameBoard(3, 5);
                break;
            case R.id.button36:
                model.updateGameBoard(3, 6);
                break;
            case R.id.button37:
                model.updateGameBoard(3, 7);
                break;

            case R.id.button40:
                model.updateGameBoard(4, 0);
                break;
            case R.id.button41:
                model.updateGameBoard(4, 1);
                break;
            case R.id.button42:
                model.updateGameBoard(4, 2);
                break;
            case R.id.button43:
                model.updateGameBoard(4, 3);
                break;
            case R.id.button44:
                model.updateGameBoard(4, 4);
                break;
            case R.id.button45:
                model.updateGameBoard(4, 5);
                break;
            case R.id.button46:
                model.updateGameBoard(4, 6);
                break;
            case R.id.button47:
                model.updateGameBoard(4, 7);
                break;

            case R.id.button50:
                model.updateGameBoard(5, 0);
                break;
            case R.id.button51:
                model.updateGameBoard(5, 1);
                break;
            case R.id.button52:
                model.updateGameBoard(5, 2);
                break;
            case R.id.button53:
                model.updateGameBoard(5, 3);
                break;
            case R.id.button54:
                model.updateGameBoard(5, 4);
                break;
            case R.id.button55:
                model.updateGameBoard(5, 5);
                break;
            case R.id.button56:
                model.updateGameBoard(5, 6);
                break;
            case R.id.button57:
                model.updateGameBoard(5, 7);
                break;

            case R.id.button60:
                model.updateGameBoard(6, 0);
                break;
            case R.id.button61:
                model.updateGameBoard(6, 1);
                break;
            case R.id.button62:
                model.updateGameBoard(6, 2);
                break;
            case R.id.button63:
                model.updateGameBoard(6, 3);
                break;
            case R.id.button64:
                model.updateGameBoard(6, 4);
                break;
            case R.id.button65:
                model.updateGameBoard(6, 5);
                break;
            case R.id.button66:
                model.updateGameBoard(6, 6);
                break;
            case R.id.button67:
                model.updateGameBoard(6, 7);
                break;

            case R.id.button70:
                model.updateGameBoard(7, 0);
                break;
            case R.id.button71:
                model.updateGameBoard(7, 1);
                break;
            case R.id.button72:
                model.updateGameBoard(7, 2);
                break;
            case R.id.button73:
                model.updateGameBoard(7, 3);
                break;
            case R.id.button74:
                model.updateGameBoard(7, 4);
                break;
            case R.id.button75:
                model.updateGameBoard(7, 5);
                break;
            case R.id.button76:
                model.updateGameBoard(7, 6);
                break;
            case R.id.button77:
                model.updateGameBoard(7, 7);
                break;

            case R.id.newGame:
                model.newGame();
                break;
        }
    }
}
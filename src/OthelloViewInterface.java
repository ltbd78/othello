package han.linsu.othello;

public interface OthelloViewInterface {

    void setColor(int x, int y, int player); 

    void enableButton(int x, int y); // enables the button at position x, y

    void disableButton(int x, int y); // disables the button at position x, y

    void setBlack(int x, int y); // puts a black disk in button x,y.

    void setWhite(int x, int y); // puts a white disk in button x,y.

    void setValid(int x, int y); // marks this button as a valid position to play.

    void setNone(int x, int y); // makes this button empty

    // void gameOver(); // displays a message saying the game is over
}
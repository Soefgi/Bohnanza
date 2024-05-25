import javax.swing.*;

public class Game {
    //Driver class of the Bohnanza game

    //program game input here
    public static void main(String[] args) {

        //initialize game
        GameBoard board = new GameBoard("Leif", "Leyla");
        System.out.println(board);
    }

}

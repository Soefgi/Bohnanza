import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class GameBoard {

    final Queue<Player> queue = new LinkedList<>();
    final Stack<Card> discardPile = new Stack<>();
    final Stack<Card> stack;

    public GameBoard(String... names) {
        if (names.length >= 5) {
            System.err.println("To many arguments! Only 5 or less players are allowed.");
            throw new IllegalArgumentException();
        }

        //initialize deck
        Stack<Card> stack = new Stack<>();
        ArrayList<Card> deck = CardFactory.createDeck();
        for (Card card : deck) {
            stack.push(card);
        }
        this.stack = stack;

        //add all players to the board
        for (String name : names) {
            this.queue.add(new Player(name));
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("============ BOARD ============\n");
        sb.append("Players:" + "\n");
        for (Player player : queue) {
            sb.append(player).append("\n");
        }
        // sb.append("\n");
        sb.append("===============================");
        return sb.toString();
    }

}

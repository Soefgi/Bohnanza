import java.util.*;

public class Player {

    final String name;
    final Queue<Card> hand = new LinkedList<>();
    final List<Card> field0 = new ArrayList<>();
    final List<Card> field1 = new ArrayList<>();
    final Stack<Card> treasury = new Stack<>();
    final TradingArea tradingArea = new TradingArea();

    public Player(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }

}

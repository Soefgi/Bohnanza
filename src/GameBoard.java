import java.util.*;

public class GameBoard {
    private static final int MAX_DECK_NUMBER = 3;

    private int deckNumber = 0; // how often the deck has been fully empty
    private final Stack<Card> discardPile = new Stack<>();
    private final Stack<Card> deck = new Stack<>();

    Stack<Card> getDeck() {
        return deck;
    }

    GameBoard() {
        final ArrayList<Card> new_deck = CardFactory.createDeck();
        for (Card card : new_deck) {
            deck.push(card);
        }
    }

    boolean checkDeck() {
        if (!deck.isEmpty()) {
            return true;
        }

        deckNumber++;
        if (deckNumber >= MAX_DECK_NUMBER) return false; //TODO: GAME END

        //shuffle discard pile and use as new deck
        ArrayList<Card> new_deck = new ArrayList<>(discardPile);
        discardPile.clear();
        Collections.shuffle(new_deck);
        for (Card card : new_deck) {
            card.setFace(Card.DOWN);
            deck.push(card);
        }
        return true;
    }

    void addToDiscardPile(Card card) {
        card.setFace(Card.OPEN);
        discardPile.push(card);
    }

    public void print() {
        final StringBuilder sb = new StringBuilder();
        sb.append("============ BOARD ============\n");

        sb.append("Deck size: ").append(deck.size()).append("\n");
        sb.append("Deck has been emptied ").append(deckNumber).append(" time(s)!\n\n");

        sb.append("Most recent discarded Card:\n");
        if (!discardPile.isEmpty()) {
            sb.append(discardPile.peek().toString());
        } else {
            sb.append("No Cards discarded yet\n");
        }

        sb.append("===============================");
        System.out.println(sb);
    }

}

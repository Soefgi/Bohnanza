package io.bitbucket.plt.sdp.bohnanza.components;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Stack;

public class Player {
    final static int FIELD_0 = 0;
    final static int FIELD_1 = 1;
    private final String name;
    private final ArrayList<Card> hand = new ArrayList<>();
    private final List<Card> field0 = new ArrayList<>();
    private final List<Card> field1 = new ArrayList<>();
    private final Stack<Card> treasury = new Stack<>();
    private final TradingArea tradingArea = new TradingArea();

    //Getters
    public String getName() {
        return name;
    }

    TradingArea getTradingArea() {
        return tradingArea;
    }

    public Player(String name) {
        this.name = name;
    }

    Card getHandCard(int position) {
        if (hand.isEmpty()) {
            return null;
        }
        return hand.remove(position);
    }

    public Card drawCard(GameBoard board) {
        if (board.checkDeck()) {
            Card card = board.getDeck().pop();
            card.setFace(true);
            hand.add(card);
            return card;
        }
        return null;
    }

    void discardCard(GameBoard board, Card card) {
        board.addToDiscardPile(card);
    }

    void plantCard(Card card, int field) {
        if (field != FIELD_0 && field != FIELD_1) {
            throw new IllegalArgumentException("Invalid field number, only FIELD_0 and FIELD_1 are allowed!");
        }

        card.setFace(true);
        if (field == FIELD_0) field0.add(card);
        if (field == FIELD_1) field1.add(card);

        System.out.println("Planted " + card.getBeanType() + " into field " + field);
    }

    void harvestField(int field) {
        if (field != FIELD_0 && field != FIELD_1) {
            throw new IllegalArgumentException("Invalid field number, only 0 and FIELD_1 are allowed!");
        }

        switch (field) {
            case FIELD_0 -> {
                treasury.addAll(field0);
                field0.clear();
            }
            case FIELD_1 -> {
                treasury.addAll(field1);
                field1.clear();
            }
        }
    }

    int calculateScore() {
        int score = 0;
        HashMap<String, ArrayList<Card>> map = new HashMap<>();

        //group cards by bean type
        for (Card card : treasury) {
            map.compute(card.getBeanType().toString(), (k, v) -> {
                if (v == null) {
                    v = new ArrayList<>();
                }
                v.add(card);
                return v;
            });
        }

        //calculate coins for given bean type and add to total score
        for (ArrayList<Card> cards: map.values()) {
            score += cards.get(0).calculateCoins(cards.size());
        }

        return score;
    }

    void printHand() {
        String name = this.name;
        name += name.endsWith("s") ? "'": "'s";
        System.out.println(name + " current hand: ");
        for (Card card : hand) {
            System.out.println(card);
        }
    }
}

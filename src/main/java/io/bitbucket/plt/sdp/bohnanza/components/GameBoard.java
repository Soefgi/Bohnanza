package io.bitbucket.plt.sdp.bohnanza.components;

import io.bitbucket.plt.sdp.bohnanza.gui.GUI;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Stack;

public class GameBoard {

    private final Stack<Card> discardPile = new Stack<>();
    private final Stack<Card> deck;
    private int deckTurns = 0;

    private GUI gui;

    public Stack<Card> getDeck() {
        return deck;
    }


    public GameBoard(GUI gui) {
        this.gui = gui;
        this.deck = CardFactory.createDeck(this.gui);
    }

    public boolean checkDeck() {
        if (!deck.isEmpty()) return true;

        deckTurns++;
        if (deckTurns >= 3) return false; //TODO: GAME END

        //shuffle discard pile and use as new deck
        ArrayList<Card> new_deck = new ArrayList<>(discardPile);
        discardPile.clear();
        Collections.shuffle(new_deck);
        for (Card card : new_deck) {
            card.setFace(false);
            deck.push(card);
        }
        return true;
    }

    public void addToDiscardPile(Card card) {
        card.setFace(true);
        discardPile.push(card);
    }
}

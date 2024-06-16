package io.bitbucket.plt.sdp.bohnanza.components;

import io.bitbucket.plt.sdp.bohnanza.gui.CardObject;
import io.bitbucket.plt.sdp.bohnanza.gui.CardType;

public class Card {
    private final CardType beanType;

    private final int tier1;
    private final int tier2;
    private final int tier3;
    private final int tier4;

    private final CardObject cardObject;

    //Getters and Setters
    public CardType getBeanType() {
        return beanType;
    }

    Card(CardType cardType, int tier1, int tier2, int tier3, int tier4, CardObject cardObject) {
        this.beanType = cardType;
        this.tier1 = tier1;
        this.tier2 = tier2;
        this.tier3 = tier3;
        this.tier4 = tier4;
        this.cardObject = cardObject;
    }

    /**
     * Set the card's facing direction
     *
     * @param showFront true -> card is facing upwards
     */
    public void setFace(boolean showFront) {
        this.cardObject.showFront(showFront);
    }

    /**
     * Calculates how many coins are rewarded by harvesting the given amount
     *
     * @param amount The amount of cards to retrieve their coin value
     * @return The coin value of the given amount
     */
    public int calculateCoins(int amount) {
        int coins = 0;
        if (amount > tier1) coins = 1;
        if (amount > tier2) coins = 2;
        if (amount > tier3) coins = 3;
        if (amount > tier4) coins = 4;
        return coins;
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof Card) {
            return this.beanType.equals(((Card) o).beanType);
        } else return false;
    }

}

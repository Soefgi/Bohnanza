public class Card {
    final static int OPEN = 1;
    final static int DOWN = 0;

    private final String beanType;
    private final int numberOfCards; // number of instances of this card in the game
    private final int tier1;
    private final int tier2;
    private final int tier3;
    private final int tier4;
    private int face;

    //Getters and Setters
    String getBeanType() {
        return beanType;
    }

    Card(String input) {
        //example input: Blue Bean,20,4,6,8,10
        String[] splits = input.split(",");

        // card identity
        this.beanType = splits[0];
        this.numberOfCards = Integer.parseInt(splits[1]);

        // value tiers
        this.tier1 = Integer.parseInt(splits[2]);
        this.tier2 = Integer.parseInt(splits[3]);
        this.tier3 = Integer.parseInt(splits[4]);
        this.tier4 = Integer.parseInt(splits[5]);

        // cards facing downwards by default
        setFace(DOWN);
    }

    /**
     * Set the card's facing direction
     *
     * @param face DOWN or OPEN
     */
    void setFace(int face) {
        if (face != OPEN && face != DOWN) {
            throw new IllegalArgumentException("Invalid field number, only OPEN and DOWN are allowed!");
        }
        this.face = face;
    }

    /**
     * Calculates how many coins are rewarded by harvesting the given amount
     *
     * @param amount The amount of cards to retrieve their coin value
     * @return The coin value of the given amount
     */
    int calculateCoins(int amount) {
        int coins = 0;
        if (amount > tier1) coins = 1;
        if (amount > tier2) coins = 2;
        if (amount > tier3) coins = 3;
        if (amount > tier4) coins = 4;
        return coins;
    }

    @Override
    public String toString() {
        if (face == DOWN) {
            return "Hidden";
        }
        if (face == OPEN) {
            return beanType;
        } else {
            return "Sideways???";
        }
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof Card) {
            return this.beanType.equals(((Card) o).beanType);
        }
        else return false;
    }

}

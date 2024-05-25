public class Card {

    final String beanType;
    final int numberOfCards; // number of instances of this card in the game
    final int tier1;
    final int tier2;
    final int tier3;
    final int tier4;

    boolean faceUp;

    public Card(String input) {
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

        // cards facing down by default
        this.faceUp = false;
    }

}

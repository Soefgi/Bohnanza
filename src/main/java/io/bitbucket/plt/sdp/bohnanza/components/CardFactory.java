package io.bitbucket.plt.sdp.bohnanza.components;

import io.bitbucket.plt.sdp.bohnanza.gui.CardObject;
import io.bitbucket.plt.sdp.bohnanza.gui.CardType;
import io.bitbucket.plt.sdp.bohnanza.gui.Coordinate;
import io.bitbucket.plt.sdp.bohnanza.gui.GUI;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Stack;

public class CardFactory {

    public static Stack<Card> createDeck(GUI gui) {
        final Stack<Card> cards = new Stack<>();
        final ArrayList<String> definitions = readDefinitions();
        final ArrayList<String> init_strings = new ArrayList<>();

        //shuffle definitions BEFORE creation
        for (String definition : definitions) {
            final int amount = Integer.parseInt(definition.split(",")[1]);
            for (int i = 0; i < amount; i++) {
                init_strings.add(definition);
            }
        }

        Collections.shuffle(init_strings);

        //create cards from definitions
        for (String definition : init_strings) {
            final String[] splits = definition.split(",");
            CardType cardType = determineCardType(splits[0]);
            int tier1 = Integer.parseInt(splits[2]);
            int tier2 = Integer.parseInt(splits[3]);
            int tier3 = Integer.parseInt(splits[4]);
            int tier4 = Integer.parseInt(splits[5]);
            CardObject cardObject = gui.addCard(cardType, new Coordinate(600, 120));
            Card card = new Card(cardType, tier1, tier2, tier3, tier4, cardObject);
            card.setFace(false);
            cards.push(card);
        }

        return cards;
    }

    private static ArrayList<String> readDefinitions() {
        final ArrayList<String> definitions = new ArrayList<>();

        //read definitions of cards
        final String path = "assets/beans_definitions.txt";
        try {
            final BufferedReader reader = new BufferedReader(new FileReader(path));
            String line = reader.readLine();

            while (line != null) {
                definitions.add(line);
                line = reader.readLine();
            }
            reader.close();
        } catch (IOException e) {
            System.err.println("Beans definitions file invalid!");
            throw new RuntimeException(e);
        }
        return definitions;
    }

    private static CardType determineCardType(final String definition) {
        switch (definition.split(",")[0]) {
            case "Blue Bean" -> {
                return CardType.BLAUE_BOHNE;
            }
            case "Chili Bean" -> {
                return CardType.FEUER_BOHNE;
            }
            case "Stink Bean" -> {
                return CardType.BRECH_BOHNE;
            }
            case "Green Bean" -> {
                return CardType.STANGEN_BOHNE;
            }
            case "Soy Bean" -> {
                return CardType.SOJA_BOHNE;
            }
            case "Black-Eyed Bean" -> {
                return CardType.AUGEN_BOHNE;
            }
            case "Red Bean" -> {
                return CardType.ROTE_BOHNE;
            }
            case "Garden Bean" -> {
                return CardType.GARTEN_BOHNE;
            }
            default -> {
                throw new IllegalArgumentException("Invalid card type: " + definition);
            }
        }
    }

}

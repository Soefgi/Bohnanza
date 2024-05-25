import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

public class CardFactory {

    static ArrayList<Card> createDeck() {
        final ArrayList<Card> cards = new ArrayList<>();
        final ArrayList<String> definitions = readDefinitions();

        //create cards from definitions
        for (String definition : definitions) {
            final int amount = Integer.parseInt(definition.split(",")[1]);

            for (int i = 0; i < amount; i++) {
                cards.add(new Card(definition));
            }
        }

        Collections.shuffle(cards);
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

}

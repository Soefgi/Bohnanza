package io.bitbucket.plt.sdp.bohnanza;

import io.bitbucket.plt.sdp.bohnanza.components.GameBoard;
import io.bitbucket.plt.sdp.bohnanza.components.Player;
import io.bitbucket.plt.sdp.bohnanza.gui.*;
import io.bitbucket.plt.sdp.bohnanza.phases.*;

import java.util.*;

public class Game implements Runnable {

	private final GUI gui;
    @SuppressWarnings("unused")
    private final String[] args;
    private final GameBoard board;
    public final Queue<Player> playerQueue = new LinkedList<>();
    public final Queue<Phase> phases = new LinkedList<>();
    public Player currentPlayer;


    public Game(GUI gui, String[] args, String... playerNames) {
        super();
        this.gui = gui;
        this.args = args;
        this.board = new GameBoard(this.gui);
        if (playerNames.length == 0) {
            throw new IllegalArgumentException("You must specify at least two player names.");
        }
        phases.add(new Phase_1());
        phases.add(new Phase_2());
        phases.add(new Phase_3());
        phases.add(new Phase_4());
        addPlayers(playerNames);
    }

    public void nextPhase() {
        Phase oldPhase = phases.poll();
        phases.add(oldPhase);
    }

    public void nextPlayer() {
        //new current player is already head of queue
        currentPlayer = playerQueue.poll();
        //move current player also to back of queue
        playerQueue.add(currentPlayer);
        System.out.println("New player " + currentPlayer.getName());
    }


    @Override
    public void run() {
        //Init top "toolbar"
        final Label label = gui.addLabel(new Coordinate(3 * (BUTTON_SIZE.width + 1) + 80, 5), "Card Tooltip");
        final Label currentPlayerLabel = gui.addLabel(new Coordinate(2 * (BUTTON_SIZE.width + 1) + 10, 5), "Current Player: " + currentPlayer.getName());
        Button exitButton = gui.addButton("exit", new Coordinate(0, 0), BUTTON_SIZE, button -> gui.stop());
        Button nextPlayerButton = gui.addButton("next player", new Coordinate(BUTTON_SIZE.width + 1, 0), BUTTON_SIZE, button -> {
            nextPlayer();
            currentPlayerLabel.updateLabel("Current Player: " + currentPlayer.getName());
        });
        gui.addCompartment(new Coordinate(0, 26), new Size(800, 1), "");

        //Init deck and discard pile
        Compartment deckCompartment = gui.addCompartment(
                new Coordinate(550, 50),
                HAND_SIZE,
                "Deck"
        );
        Compartment discardCompartment = gui.addCompartment(
                new Coordinate(550, 250),
                HAND_SIZE,
                "Discard Pile"
        );

        // fill bean fields and hand card spaces for each player
        int y = 50;
        for (Player player : playerQueue) {
            gui.addCompartment(
                    new Coordinate(0, y),
                    FIELD_SIZE,
                    "Bohnenfelder von " + player.getName(),
                    "BOHNENFELD_ALLE");
            gui.addCompartment(
                    new Coordinate(FIELD_SIZE.width + 1, y),
                    HAND_SIZE,
                    "Handkarten von " + player.getName()
            );
            y += FIELD_SIZE.height;
        }


        // set the handler for drag'n'drop events. With this handler:
        // - whenever a d'n'd action finishes, the dropped card is flipped (toggle whether the front or back is shown)
        // - information on the dropped card is shown in the dedicated label
        // - the card is moved to the front, i.e., displayed top-most
        gui.setCardDnDHandler((CardObject card, Coordinate mouseCoordinate, Coordinate newCoordinate) -> {
            	card.flip();
                label.updateLabel(card.toString());
                gui.moveToTop(card);
                return newCoordinate;
        });

    }

    final Size FIELD_SIZE = new Size(300, 200);
    final Size HAND_SIZE = new Size(200, 200);
    final Size BUTTON_SIZE = new Size(100, 25);

    private void addPlayers(String... playerNames) {
        //check if player amount is allowed
        int newSize = playerQueue.size() + playerNames.length;
        if (newSize > 5) {
            throw new RuntimeException("Too many players! Only 5 players are allowed, you inserted " + newSize + ". ");
        }

        //check for duplicate names
        Set<String> players = new HashSet<>(Arrays.asList(playerNames));
        if (players.size() < playerNames.length) {
            System.err.print("No duplicate names allowed! ");
            System.err.println("Aborting adding of players...");
            return;
        }

        //check if player already exists
        for (Player existingPlayer : playerQueue) {
            for (String newPlayerName : playerNames) {
                if (existingPlayer.getName().equals(newPlayerName)) {
                    System.err.print("Player " + existingPlayer.getName() + " already exists in Game Board! ");
                    System.err.println("Aborting adding of players...");
                    return;
                }
            }
        }

        //finally add players
        for (String name : playerNames) {
            Player newPlayer = new Player(name);
            //draw five cards at the beginning
            for (int i = 0; i < 5; i++) {
                newPlayer.drawCard(this.board);
            }
            System.out.println();
            currentPlayer = newPlayer;
            playerQueue.add(newPlayer);
        }
    }

}

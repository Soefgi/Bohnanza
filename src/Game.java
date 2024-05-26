import java.util.*;

public class Game {
    public static final String ANSI_CYAN = "\u001B[36m";
    public static final String ANSI_RESET = "\u001B[0m";

    public final Queue<Player> playerQueue = new LinkedList<>();
    public final GameBoard board = new GameBoard();
    public Player currentPlayer;

    //program game input here
    public static void main(String[] args) {
        //initialize game
        Game game = new Game();
        game.addPlayers("Hans", "Peter", "Klaus", "Ingo");
        game.startGame();

        //dummy round
        //Phase 1
        System.out.println(ANSI_CYAN + "Phase 1" + ANSI_RESET);
        System.out.println(ANSI_CYAN + "Must play first card in hand into a field" + ANSI_RESET);
        Card firstCard = game.currentPlayer.getHandCard(0);
        game.currentPlayer.plantCard(firstCard, Player.FIELD_0);
        System.out.println();

        //Phase 2
        System.out.println(ANSI_CYAN + "Phase 2" + ANSI_RESET);
        System.out.println(ANSI_CYAN + "May play second card" + ANSI_RESET);
        Card secondCard = game.currentPlayer.getHandCard(0);
        if (secondCard.equals(firstCard)) {
            game.currentPlayer.plantCard(secondCard, Player.FIELD_0);
        } else {
            game.currentPlayer.plantCard(secondCard, Player.FIELD_1);
        }
        System.out.println();

        //Phase 3
        System.out.println(ANSI_CYAN + "Phase 3" + ANSI_RESET);
        System.out.println(ANSI_CYAN + "Must take top two cards from deck and place in trading area.\n" +
                "Trading goes on until active player decides it's over." + ANSI_RESET);

        game.currentPlayer.drawCard(game.board);
        game.currentPlayer.getTradingArea().addOffer(game.currentPlayer.getHandCard(3));
        game.currentPlayer.drawCard(game.board);
        game.currentPlayer.getTradingArea().addOffer(game.currentPlayer.getHandCard(3));

        Player tradePartner = ((LinkedList<Player>) game.playerQueue).get(1);
        game.currentPlayer.getTradingArea().addTradePartner(tradePartner);
        game.currentPlayer.getTradingArea().addWithdrawal(tradePartner.getHandCard(0));
        game.currentPlayer.getTradingArea().completeTrade();
        System.out.println();

        //Phase 4
        System.out.println(ANSI_CYAN + "Phase 4" + ANSI_RESET);
        System.out.println(ANSI_CYAN + "Draw 3 cards from deck" + ANSI_RESET);
        game.currentPlayer.drawCard(game.board);
        game.currentPlayer.drawCard(game.board);
        game.currentPlayer.drawCard(game.board);
    }

    /**
     * Add one or more players to the game board.
     * Maximum number of allowed players is 5.
     * Minimum number of allowed players is 2.
     *
     * @param playerNames names of the players to be inserted
     */
    public void addPlayers(String... playerNames) {
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
            System.out.println(ANSI_CYAN + "Added new Player: " + newPlayer.getName() + ANSI_RESET);
            //draw five cards at the beginning
            for (int i = 0; i < 5; i++) {
                newPlayer.drawCard(this.board);
            }
            System.out.println();
            if (currentPlayer == null) {
                currentPlayer = newPlayer;
            }
            playerQueue.add(newPlayer);
        }
    }

    public void nextPlayer() {
        //current player to end of queue
        playerQueue.offer(currentPlayer);
        //remove current player from queue head
        playerQueue.poll();
        //head is new current player
        currentPlayer = playerQueue.peek();
    }

    public void startGame() {
        //check if game is valid
        if (playerQueue.size() < 2) {
            throw new RuntimeException("Game is invalid. Not enough players!");
        }

        System.out.println(currentPlayer.getName() + " begins the game!\n");
        //TODO Phases
    }

}

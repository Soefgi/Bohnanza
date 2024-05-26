import java.util.*;

public class Game {
    public final Queue<Player> playerQueue = new LinkedList<>();
    public final GameBoard board = new GameBoard();
    public Player currentPlayer;

    //program game input here
    public static void main(String[] args) {
        //initialize game
        Game game = new Game();
        game.addPlayers("Hans", "Peter", "Klaus", "Ingo");
        game.startGame();
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
            //draw five cards at the beginning
            for (int i = 0; i < 5; i++) {
                newPlayer.drawCard(this.board);
            }
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

        //TODO Phases
    }

}

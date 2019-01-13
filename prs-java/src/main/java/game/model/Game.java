package game.model;

import game.model.player.Gesture;
import game.model.player.Player;
import game.model.player.PlayerFactory;

import java.util.Arrays;
import java.util.List;

public class Game {

    private final GameInterface gameInterface;
    private final PlayerFactory playerFactory;
    private Player player;
    private Player computer;
    private Integer maxRounds;
    private Integer currentRoundNumber = 0;

    public Game(GameInterface gameInterface, PlayerFactory playerFactory) {
        this.gameInterface = gameInterface;
        this.playerFactory = playerFactory;
    }

    public void init() {
        try {
            gameInterface.greet();
            player = registerPlayer();
            computer = playerFactory.create();
            maxRounds = registerMaxRounds();
        } catch (MaxNoOfTryoutsExceeded e) {
            gameInterface.printExitDueToInvalidInputs();
        }
    }

    public void start() {
        gameInterface.flagStart();
        while (currentRoundNumber < maxRounds) {
            playARound();
        }
        finalizeGame();
    }

    private void finalizeGame() {
        gameInterface.printScoreBoard(player.printScore(), computer.printScore());
        gameInterface.declareEnd(player.conclude(computer));
    }

    private void playARound() {
        try {
            registerGesture(player);
            player.registerOpponentsGesture(computer.getGesture());
            computer.registerOpponentsGesture(player.getGesture());
            informPlayer();
        } catch (MaxNoOfTryoutsExceeded e) {
            gameInterface.printExitDueToInvalidInputs();
        }
        currentRoundNumber++;
    }

    private void informPlayer() {
        gameInterface.printPlayersChoice(computer.printGesture());
        gameInterface.printScoreBoard(player.printScore(), computer.printScore());
        gameInterface.printRemainingRounds(maxRounds - currentRoundNumber - 1);
    }

    private Player registerPlayer() {
        String name = gameInterface.getPlayersName();
        return playerFactory.create(name, gameInterface);
    }

    private Gesture registerGesture(Player player) {
        int tryout = 0;
        while (tryout < GameInterface.MAX_NO_OF_INPUT_TRYOUTS) {
            try {
                return player.getGesture();
            } catch (Gesture.CouldNotBeGenerated e) {
                gameInterface.printNotAValidGestureChoiceMessage();
                tryout++;
            }
        }
        throw new MaxNoOfTryoutsExceeded();
    }

    private int registerMaxRounds() {
        int tryout = 0;
        while (tryout < GameInterface.MAX_NO_OF_INPUT_TRYOUTS) {
            try {
                return Integer.parseInt(gameInterface.getDesiredNoOfRounds());
            } catch (NumberFormatException e) {
                gameInterface.printNotValidChoiceForMaxRounds();
                tryout++;
            }
        }
        throw new MaxNoOfTryoutsExceeded();
    }

    public List<Player> getPlayers() {
        return Arrays.asList(player, computer);
    }

    private class MaxNoOfTryoutsExceeded extends RuntimeException {
    }
}

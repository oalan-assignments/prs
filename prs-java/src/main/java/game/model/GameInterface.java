package game.model;

import game.model.player.GameResult;

public interface GameInterface {

    int MAX_NO_OF_INPUT_TRYOUTS = 5;

    String getPlayersGestureChoice();

    String getPlayersName();

    void greet();

    String getDesiredNoOfRounds();

    void flagStart();

    void printNotAValidGestureChoiceMessage();

    void printNotValidChoiceForMaxRounds();

    void printScoreBoard(String playersScore, String opponentsScore);

    void printPlayersChoice(String gestureMessage);

    void printRemainingRounds(Integer remainingRounds);

    void printExitDueToInvalidInputs();

    void declareEnd(GameResult conclude);
}

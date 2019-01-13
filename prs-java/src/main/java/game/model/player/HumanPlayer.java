package game.model.player;

import game.model.GameInterface;

final class HumanPlayer extends Player {

    private final GameInterface gameInterface;

    HumanPlayer(String playersName, GameInterface gameInterface) {
        super(playersName);
        this.gameInterface = gameInterface;
    }

    @Override
    public Gesture getGesture() throws Gesture.CouldNotBeGenerated {
        String label = gameInterface.getPlayersGestureChoice();
        try {
            Integer position = Integer.parseInt(label);
            validateGivenPosition(position);
            actualGesture = Gesture.values()[position];
            return actualGesture;
        } catch (NumberFormatException e) {
            throw new Gesture.CouldNotBeGenerated();
        }
    }

    private void validateGivenPosition(Integer position) {
        if (position < 0 || position > Gesture.values().length - 1) {
            throw new Gesture.CouldNotBeGenerated();
        }
    }
}

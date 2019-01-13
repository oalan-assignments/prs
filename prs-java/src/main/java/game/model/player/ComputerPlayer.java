package game.model.player;

import java.util.concurrent.ThreadLocalRandom;

final class ComputerPlayer extends Player {

    ComputerPlayer() {
        super("Mr. Robot");
    }

    @Override
    public Gesture getGesture() {
        actualGesture = Gesture.values()[ThreadLocalRandom.current().nextInt(0, Gesture.values().length)];
        return actualGesture;
    }
}

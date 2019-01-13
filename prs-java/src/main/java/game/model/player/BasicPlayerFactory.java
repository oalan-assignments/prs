package game.model.player;

import game.model.GameInterface;

public class BasicPlayerFactory implements PlayerFactory {

    @Override
    public Player create(String name, GameInterface gameInterface) {
        return new HumanPlayer(name, gameInterface);
    }

    @Override
    public Player create() {
        return new ComputerPlayer();
    }
}

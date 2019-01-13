package game.model.player;

import game.model.GameInterface;

public interface PlayerFactory {

    Player create(String name, GameInterface gameInterface);

    Player create();
}

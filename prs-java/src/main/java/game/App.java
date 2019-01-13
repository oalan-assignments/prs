package game;


import game.model.CommandLineInterface;
import game.model.Game;
import game.model.GameInterface;
import game.model.player.BasicPlayerFactory;
import game.model.player.PlayerFactory;

class App
{
    public static void main( String[] args ) {
        GameInterface cli = new CommandLineInterface();
        PlayerFactory playerFactory = new BasicPlayerFactory();
        Game game = new Game(cli, playerFactory);
        game.init();
        game.start();
    }
}

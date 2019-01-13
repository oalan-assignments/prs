package game.model.player;

import java.util.Optional;

public abstract class Player {

    private static final String DEFAULT_NAME = "Stranger";
    private final String name;
    private Integer score = 0;
    protected Gesture actualGesture;

    public Player(String name)
    {
        this.name = Optional.ofNullable(name)
                .filter(n -> !n.isEmpty())
                .orElse(DEFAULT_NAME);
    }

    public void registerOpponentsGesture(Gesture opponentsGesture) {
        score += actualGesture.judgeOver(opponentsGesture).point();
    }

    public String printScore() {
        return name + "'s score is " + score;
    }

    public String printGesture() {
        return name + " chose " + actualGesture.name();
    }

    public GameResult conclude(Player opponent) {
        if (score.equals(opponent.score)) {
            return new GameResult.Tie();
        } else {
            return findTheWinner(opponent);
        }
    }

    private GameResult findTheWinner(Player opponent) {
        if(opponent.score > score) {
            return new GameResult.Win("Winner is: " + opponent.name);
        }
        return new GameResult.Win("Winner is: " + name);
    }

    public abstract Gesture getGesture();
}

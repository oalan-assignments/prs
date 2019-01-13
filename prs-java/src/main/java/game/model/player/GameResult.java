package game.model.player;

public abstract class GameResult {

    public abstract String print();

    static class Tie extends GameResult {

        @Override
        public String print() {
            return "It's a tie!";
        }
    }

    static class Win extends GameResult {

        private final String msg;

        public Win(String msg) {
            this.msg = msg;
        }

        @Override
        public String print() {
            return msg;
        }
    }
}

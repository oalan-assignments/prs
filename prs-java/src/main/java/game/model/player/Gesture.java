package game.model.player;

public enum Gesture {

    PAPER {
        @Override
        public Outcome judge(Gesture gestureOfOpponent) {
            switch (gestureOfOpponent) {
                case PAPER:
                    return Outcome.DRAW;
                case ROCK:
                    return Outcome.WIN;
                case SCISSORS:
                    return Outcome.LOSE;
                default:
                    throw new InvalidGestureError();
            }
        }
    },
    ROCK {
        @Override
        public Outcome judge(Gesture gestureOfOpponent) {
            switch (gestureOfOpponent) {
                case ROCK:
                    return Outcome.DRAW;
                case SCISSORS:
                    return Outcome.WIN;
                case PAPER:
                    return Outcome.LOSE;
                default:
                    throw new InvalidGestureError();
            }
        }
    },
    SCISSORS {
        @Override
        public Outcome judge(Gesture gestureOfOpponent) {
            switch (gestureOfOpponent) {
                case SCISSORS:
                    return Outcome.DRAW;
                case PAPER:
                    return Outcome.WIN;
                case ROCK:
                    return Outcome.LOSE;
                default:
                    throw new InvalidGestureError();
            }
        }
    };

    public enum Outcome {
        WIN {
            @Override
            Integer point() {
                return 1;
            }
        }, DRAW {
            @Override
            Integer point() {
                return 0;
            }
        }, LOSE {
            @Override
            Integer point() {
                return 0;
            }
        };

        abstract Integer point();
    }

    protected abstract Outcome judge(Gesture gestureOfOpponent);

    public Outcome judgeOver(Gesture gestureOfOpponent) {
        if(gestureOfOpponent == null) {
            throw new InvalidGestureError();
        } else {
            return judge(gestureOfOpponent);
        }
    }

    public class InvalidGestureError extends RuntimeException {}

    public static class CouldNotBeGenerated extends RuntimeException {}

}

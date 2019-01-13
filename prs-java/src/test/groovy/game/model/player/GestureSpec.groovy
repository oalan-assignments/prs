package game.model.player

import spock.lang.Specification

class GestureSpec extends Specification {

    def "Judging gesture over opponents gesture should yield correct results"(
            Gesture player, Gesture opponent, Gesture.Outcome result) {

        expect:
        player.judgeOver(opponent) == result

        where:
        player              | opponent          | result
        Gesture.PAPER       | Gesture.ROCK      | Gesture.Outcome.WIN
        Gesture.PAPER       | Gesture.SCISSORS  | Gesture.Outcome.LOSE
        Gesture.PAPER       | Gesture.PAPER     | Gesture.Outcome.DRAW
        Gesture.SCISSORS    | Gesture.PAPER     | Gesture.Outcome.WIN
        Gesture.SCISSORS    | Gesture.ROCK      | Gesture.Outcome.LOSE
        Gesture.SCISSORS    | Gesture.SCISSORS  | Gesture.Outcome.DRAW
        Gesture.ROCK        | Gesture.SCISSORS  | Gesture.Outcome.WIN
        Gesture.ROCK        | Gesture.PAPER     | Gesture.Outcome.LOSE
        Gesture.ROCK        | Gesture.ROCK      | Gesture.Outcome.DRAW
    }

    def 'Erroneously passing null to judge over method should yield to an error'() {

        when:
        Gesture.PAPER.judgeOver(null)

        then:
        thrown(Gesture.InvalidGestureError)
    }
}

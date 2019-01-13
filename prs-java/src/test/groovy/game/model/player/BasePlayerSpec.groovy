package game.model.player

import spock.lang.Specification

class BasePlayerSpec extends Specification {

    def 'Common behavior of Player class should work properly'() {

        when: 'A test player that stems from player constructed'
        Player testPlayer = new TestPlayer('Test')

        then: 'Name and score should be correct'
        testPlayer.printScore() == "Test's score is 0"

        and: 'Gesture should be correct'
        testPlayer.getGesture() == Gesture.ROCK

        when: 'Player opposed by another rock'
        testPlayer.registerOpponentsGesture(Gesture.ROCK)

        then: 'Score should still be same'
        testPlayer.printScore() == "Test's score is 0"

        when: 'Player opposed by paper'
        testPlayer.registerOpponentsGesture(Gesture.PAPER)

        then: 'Score should still be same'
        testPlayer.printScore() == "Test's score is 0"

        when: 'Player opposed by scissors'
        testPlayer.registerOpponentsGesture(Gesture.SCISSORS)

        then: 'Score should be incremented'
        testPlayer.printScore() == "Test's score is 1"

    }

    def 'If given name is empty. Use stranger as a name'() {

        when: 'A test player that stems from player constructed with empty name'
        Player testPlayer = new TestPlayer('')

        then:
        testPlayer.printScore() == "$Player.DEFAULT_NAME's score is 0"

        when:
        Player anotherPlayer = new TestPlayer(null)

        then:
        anotherPlayer.printScore() == "$Player.DEFAULT_NAME's score is 0"
    }

    class TestPlayer extends Player {

        TestPlayer(String name) {
            super(name)
        }

        @Override
        Gesture getGesture() {
            actualGesture = Gesture.ROCK
            return actualGesture
        }
    }
}

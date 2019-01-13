package game.model.player

import spock.lang.Specification

class ComputerPlayerSpec extends Specification {

    def 'Computer player should provide gestures as expected'() {

        given: 'A computer player'
        Player computerPlayer = new ComputerPlayer()

        //Chances are very very low that we do not get all possible gestures unless there is a bug
        when: 'If we call get gestures for 100 time we should be get all possible gestures'
        Set gestures = new HashSet(3)
        (1..100).each {
            gestures.add(computerPlayer.getGesture())
        }

        then: 'Set should contain all possible values'
        gestures.contains(Gesture.ROCK)
        gestures.contains(Gesture.SCISSORS)
        gestures.contains(Gesture.PAPER)

    }


}

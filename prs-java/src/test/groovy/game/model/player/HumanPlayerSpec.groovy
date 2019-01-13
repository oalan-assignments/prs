package game.model.player

import game.model.GameInterface
import spock.lang.Specification

class HumanPlayerSpec extends Specification {


    Player player
    GameInterface gameInterface

    def setup() {
        gameInterface = Mock()
        player = new HumanPlayer("Human", gameInterface)
    }


    def 'Human player should interact properly with game interface to provide a gesture'()
    {
        when: 'User input is proper generate the right gesture'
        gameInterface.getPlayersGestureChoice() >> '0'

        then:
        Gesture.values()[0] ==  player.getGesture()
    }

    def 'Give warning and throw necessary exception if the user does not input a number'() {

        when: 'User input is wrong'
        player.getGesture()

        then:
        gameInterface.getPlayersGestureChoice() >> 'test'
        thrown(Gesture.CouldNotBeGenerated)
    }

    def 'Give warning and throw necessary exception if the user does not input a number between 0 and 2 inclusive'() {

        when: 'User input is wrong'
        player.getGesture()

        then:
        gameInterface.getPlayersGestureChoice() >> '-1'
        thrown(Gesture.CouldNotBeGenerated)

        when:
        player.getGesture()

        then:
        gameInterface.getPlayersGestureChoice() >> '3'
        thrown(Gesture.CouldNotBeGenerated)

    }
}

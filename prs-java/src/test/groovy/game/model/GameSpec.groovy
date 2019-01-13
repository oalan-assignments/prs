package game.model

import game.model.player.*
import spock.lang.Specification

class GameSpec extends Specification {

    GameInterface gameInterface
    PlayerFactory playerFactory
    Game game

    def setup() {
        gameInterface = Mock()
        playerFactory = new BasicPlayerFactory()
        game = new Game(gameInterface, playerFactory)
    }

    def 'Game should be initialized properly'() {

        when: 'Game is initialised'
        game.init()

        then: 'User should be greeted'
        1 * gameInterface.greet()

        then: 'Players are registered'
        gameInterface.playersName >> 'Test'
        game.getPlayers().size() == 2
        game.getPlayers().findAll{it.printScore() == "Test's score is 0"}
        game.getPlayers().findAll{it.class == ComputerPlayer.class}

        then: 'Max rounds are decided'
        gameInterface.getDesiredNoOfRounds() >> '5'
        game.maxRounds == 5
    }

    def 'If user input for max rounds is invalid warn and request again'() {

        when: 'Game is initialised'
        game.init()

        then: 'For wrong input it should try for the number of max tryouts'
        gameInterface.desiredNoOfRounds() >> 'a'
        GameInterface.MAX_NO_OF_INPUT_TRYOUTS * gameInterface.printNotValidChoiceForMaxRounds()
        1 * gameInterface.printExitDueToInvalidInputs()

    }

    def 'Game should be played and end with expected result'() {

        setup:
        Player computer = new Player("C") {
            @Override
            Gesture getGesture() {
                actualGesture = Gesture.PAPER
                return actualGesture
            }
        }
        Player player = new Player("H") {
            @Override
            Gesture getGesture() {
                actualGesture =  Gesture.SCISSORS
                return actualGesture
            }
        }
        playerFactory = Mock()
        playerFactory.create() >> computer
        playerFactory.create(_,_) >> player
        game = new Game(gameInterface, playerFactory)

        when: 'Game is initialised and started'
        gameInterface.desiredNoOfRounds >> 5
        game.init()
        game.start()

        then: 'Based on the designated mock responses invocations on game interface should follow as'
        1 * gameInterface.flagStart()
        0 * gameInterface.printRemainingRounds(5)
        1 * gameInterface.printRemainingRounds(4)
        1 * gameInterface.printRemainingRounds(3)
        1 * gameInterface.printRemainingRounds(2)
        1 * gameInterface.printRemainingRounds(1)
        1 * gameInterface.printRemainingRounds(0)
        5 * gameInterface.printPlayersChoice("C chose ${Gesture.PAPER.name()}")
        6 * gameInterface.printScoreBoard(_, _)
        1 * gameInterface.declareEnd(_ as GameResult.Win)
    }

    def 'Tie situation should be handled properly'() {
        setup:
        Player computer = new Player("C") {
            @Override
            Gesture getGesture() {
                actualGesture = Gesture.SCISSORS
                return actualGesture
            }
        }
        Player player = new Player("H") {
            @Override
            Gesture getGesture() {
                actualGesture =  Gesture.SCISSORS
                return actualGesture
            }
        }
        playerFactory = Mock()
        playerFactory.create() >> computer
        playerFactory.create(_,_) >> player
        game = new Game(gameInterface, playerFactory)

        when: 'Game is initialised'
        gameInterface.desiredNoOfRounds >> 5
        game.init()
        game.start()

        then: 'Tie should be declared'
        1 * gameInterface.declareEnd(_ as GameResult.Tie)
    }

    def 'When user inputs invalid choice for gesture game should respond with a retry'()
    {
        setup:
        game = new Game(gameInterface, playerFactory)

        when: 'Game is initialised'
        gameInterface.getPlayersGestureChoice() >> 'invalid'
        gameInterface.desiredNoOfRounds >> 1
        game.init()
        game.start()

        then: 'There should be 5 warning invocation on game interface'
        GameInterface.MAX_NO_OF_INPUT_TRYOUTS *  gameInterface.printNotAValidGestureChoiceMessage()

        and: 'one exit invocation on game interface'
        1 * gameInterface.printExitDueToInvalidInputs()

    }

}

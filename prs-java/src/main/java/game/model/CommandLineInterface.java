package game.model;

import game.model.player.GameResult;
import game.model.player.Gesture;

import java.util.concurrent.TimeUnit;

public final class CommandLineInterface implements GameInterface {

    private static final String ANSI_GREEN = "\u001B[32m";
    private static final String ANSI_RED = "\u001B[31m";
    private static final String ANSI_BLUE = "\u001B[34m";
    private static final String ANSI_RESET = "\u001B[0m";
    private static final String ANSI_PURPLE = "\u001B[35m";

    public void greet() {
        print("Welcome to the paper-rock-scissors game stranger!!!");
        print("It is rather an old but fun game");
        print("");
        print("All you have to do is to choose one of the three gestures");
        printGestureOptions();
        print("");
        print("You will play against a computer for certain number of rounds that you can determine");
        print("Per round winner is determined by the following schema");
        print("Paper beats (wraps) rock");
        print("Rock beats (blunts) scissors");
        print("Scissors beats (cuts) paper");
        print("");
    }

    @Override
    public String getPlayersName() {
        printInPurple("What is your name stranger?");
        return System.console().readLine();
    }

    @Override
    public String getDesiredNoOfRounds()
    {
        printInPurple("How many rounds would you like to play?");
        return System.console().readLine();
    }

    @Override
    public void flagStart() {
        printInPurple("Perfect! Game is starting...");
    }

    @Override
    public void printNotAValidGestureChoiceMessage() {
        printInRed("Invalid choice!");
    }

    @Override
    public void printNotValidChoiceForMaxRounds() {
        printInPurple("Max number of rounds should be greater than 0");
    }

    @Override
    public void printScoreBoard(String playersScore, String opponentsScore) {
        printInRed(playersScore + " - " + opponentsScore);
    }

    @Override
    public void printPlayersChoice(String playerGestureMessage) {
        printInPurple(playerGestureMessage);
    }

    @Override
    public void printRemainingRounds(Integer remainingRounds) {
        printInRed("Remaining rounds : " + remainingRounds);

    }

    @Override
    public void printExitDueToInvalidInputs() {
        printInRed("Could not get the right input for " + MAX_NO_OF_INPUT_TRYOUTS + " times. Exiting the game...");
        System.exit(1);
    }

    @Override
    public void declareEnd(GameResult conclude) {
        printInRed(conclude.print());
        System.console().readLine();
    }

    @Override
    public String getPlayersGestureChoice() {
        printInPurple("Please choose a number between 0 and 2");
        printGestureOptions();
        return System.console().readLine();
    }

    private static void printGestureOptions() {
        printInRed(String.format("[%d] Open hand equals to %s", Gesture.PAPER.ordinal(), Gesture.PAPER.name()));
        printInGreen(String.format("[%d] Fist equals to %s", Gesture.ROCK.ordinal(), Gesture.ROCK.name()));
        printInBlue(String.format("[%d] Index and middle finger equals to %s", Gesture.SCISSORS.ordinal(), Gesture.SCISSORS.name()));
    }

    private static void print(String msg) {
        System.out.println(msg);
        delay();
    }

    private static void printInPurple(String msg) {
        printInColor(ANSI_PURPLE, msg);
    }

    private static void printInGreen(String msg) {
        printInColor(ANSI_GREEN, msg);
    }

    private static void printInRed(String msg) {
        printInColor(ANSI_RED, msg);
    }

    private static void printInBlue(String msg) {
        printInColor(ANSI_BLUE, msg);
    }

    private static void printInColor(String color, String msg) {
        print(color + msg + ANSI_RESET);
    }

    private static void delay() {
        try {
            TimeUnit.MILLISECONDS.sleep(500);
        } catch (InterruptedException e) {
            System.out.println("Unexpected error has occurred"); //replace with logger
        }
    }
}

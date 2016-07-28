package net.kemitix.blackjack.cli.console;

/**
 * Interface for reading and writing to the console.
 *
 * @author pcampbell
 */
public interface Console {

    /**
     * Prints the string to the console, terminating with a newline.
     *
     * @param s the string
     */
    void println(String s);

    /**
     * Reads a line of input from the console after displaying the prompt.
     *
     * @return the input from the console
     */
    String readLine();

    /**
     * Prints the string to the console.
     *
     * @param s the string
     */
    void print(String s);
}

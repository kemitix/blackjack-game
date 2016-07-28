package net.kemitix.blackjack.cli.console;

import java.io.IOException;

/**
 * Represents an error reading or writing to the console.
 *
 * @author pcampbell
 */
public class ConsoleException extends RuntimeException {

    ConsoleException(final String message, final IOException cause) {
        super(message, cause);
    }
}

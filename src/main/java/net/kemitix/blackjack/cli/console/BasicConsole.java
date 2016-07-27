package net.kemitix.blackjack.cli.console;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintStream;

import javax.inject.Inject;

import org.springframework.stereotype.Component;

/**
 * Basic Conosle implementation using direct .
 *
 * @author pcampbell
 */
@Component
class BasicConsole implements Console {

    private final BufferedReader in;

    private final PrintStream out;

    /**
     * Constructor.
     *
     * @param in  the stream to read user input from
     * @param out the stream to write output to
     */
    @Inject
    BasicConsole(final BufferedReader in, final PrintStream out) {
        this.in = in;
        this.out = out;
    }

    @Override
    public void println(final String s) {
        out.println(s);
    }

    @Override
    public String readLine() {
        try {
            return in.readLine();
        } catch (IOException e) {
            throw new ConsoleException("Error reading from console", e);
        }
    }

    @Override
    public void print(final String s) {
        out.print(s);
    }
}

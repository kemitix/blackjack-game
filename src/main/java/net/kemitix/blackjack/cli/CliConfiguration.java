package net.kemitix.blackjack.cli;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Bean Configuration for the Command Line Interface.
 *
 * @author pcampbell
 */
@Configuration
class CliConfiguration {

    @Bean
    public BufferedReader consoleInput() {
        return new BufferedReader(
                new InputStreamReader(System.in, StandardCharsets.UTF_8));
    }

    @Bean
    public PrintStream consoleOutput() {
        return System.out;
    }
}

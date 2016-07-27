package net.kemitix.blackjack;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

/**
 * Sprint Boot's main class.
 *
 * @author pcampbell
 */
@SpringBootApplication
@SuppressWarnings("hideutilityclassconstructor")
public class SpringBootBlackJack {

    /**
     * Application main() method.
     *
     * @param args command line arguments passed to Spring
     */
    public static void main(final String[] args) {
        new SpringApplicationBuilder(SpringBootBlackJack.class)
                // disable the embedded web server
                .web(false).run(args);
    }

}

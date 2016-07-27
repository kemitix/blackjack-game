package net.kemitix.blackjack.model;

import lombok.Getter;
import lombok.Setter;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * The player.
 *
 * @author pcampbell
 */
@Setter
@Getter
@Component
public class Player {

    @Value("${player.initial-chips}")
    private int chips;

}

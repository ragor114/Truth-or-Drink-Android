package de.wiese.jannik.truth_or_drink.game.cards;

public class Card {

    private final String question;

    public Card(String question) {
        this.question = question;
    }

    public String getText() {
        return question;
    }

}

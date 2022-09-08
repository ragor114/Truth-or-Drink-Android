package de.wiese.jannik.truth_or_drink.game;

public class Player {

    private String name;
    private int numberOfDrinks;

    public Player(String name){
        this.name = name;
        this.numberOfDrinks = 0;
    }

    public void takeSip() {
        numberOfDrinks++;
    }

    public String getName() {
        return name;
    }

    public int getNumberOfDrinks() {
        return numberOfDrinks;
    }

}

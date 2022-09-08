package de.wiese.jannik.truth_or_drink.game;

import java.util.ArrayList;

public class PlayerManager {

    private ArrayList<Player> players;

    public PlayerManager() {
        players = new ArrayList<Player>();
    }

    public void addPlayer(String name) {
        players.add(new Player(name));
    }

    public Player getPlayer1() {
        return players.get(0);
    }

    public Player getPlayer2() {
        return players.get(1);
    }

}

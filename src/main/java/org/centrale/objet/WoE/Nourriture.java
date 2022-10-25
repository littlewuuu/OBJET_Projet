package org.centrale.objet.WoE;

/**
 * Represents all nourriture in the world and has the ability to increase a player's ability in a certain area.
 */
public abstract class Nourriture extends Objet {
    /**
     * Maker on the map
     */
    private final int type = 10;

    /**
     * Record the number of rounds experienced since the start of use.
     */
    private int count = 0;

    public Nourriture() {
        super();
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    @Override
    public int getType() {
        return type;
    }
}

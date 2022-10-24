package org.centrale.objet.WoE;

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

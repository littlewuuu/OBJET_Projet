package org.centrale.objet.WoE;

/**
 * Represents all elements in the world, and all elements in the world
 * are subclasses of this class. The class defines some properties that
 * all elements have.
 */
public class ElementDeJeu {

    /**
     * The position in the world.
     */
    private Point2D pos;

    /**
     * marker on the map.
     */
    private int type;

    public ElementDeJeu() {
    }

    public ElementDeJeu(Point2D pos) {
        this.pos = new Point2D(pos);
    }

    public Point2D getPos() {
        return pos;
    }

    public void setPos(Point2D pos) {
        this.pos = pos;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

}


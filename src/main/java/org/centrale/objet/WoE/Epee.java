/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.centrale.objet.WoE;

/**
 * @author wuzilong
 * @author Zou Kang
 */
public class Epee extends Objet {

    /**
     * Used to indicate on the OOCUPIED table that the position is occupied by Epee
     */
    private final int type = 3;

    /**
     * The damage caused.
     */
    private int dommage = 6;

    /**
     * Initialize.
     */
    public Epee() {
        super();
        dommage = 6;
        setPos(World.createPoints(type));
    }

    /**
     * Initialize
     *
     * @param dommage point of hurt
     * @param life    points of life
     * @param valeur  price
     */
    public Epee(int dommage, int life, int valeur) {
        super(life, valeur);
        this.dommage = dommage;
        setPos(World.createPoints(type));
    }

    /**
     * Initialize
     *
     * @param dommage int
     */
    public Epee(int dommage) {
        this.dommage = dommage;
    }

    public Epee(Epee e) {
        super(e);
        dommage = e.dommage;
    }

    public Epee(int type2, int dommage2, int life2, int state2, int x2, int y2) {
        setType(type2);
        setDommage(dommage2);
        setLife(life2);
        setState(state2);
        setPos(new Point2D(x2, y2));
    }

    /**
     * Dealing damage to a creature.
     *
     * @param c creature target
     */
    public void Blesser(Creature c) {
        c.setPtVie(c.getPtVie() - dommage);
    }

    /**
     * Get dommage.
     *
     * @return int dommage
     */
    public int getDommage() {
        return dommage;
    }

    /**
     * Set dommage.
     *
     * @param dommage, int
     */
    public void setDommage(int dommage) {
        this.dommage = dommage;
    }

    public int getType() {
        return type;
    }

    @Override
    public void affiche() {
        System.out.printf("Epee : ");
        super.affiche();
    }

    @Override
    public String toString() {

        return "Epee " + type + " " + dommage + " " + getLife() + " " + getState() + " " + getPos().getX() + " " + getPos().getY() + '\n';
    }
}

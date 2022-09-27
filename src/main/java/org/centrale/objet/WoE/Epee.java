/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.centrale.objet.WoE;

/**
 * @author wuzilong
 */
public class Epee extends Objet {
    private int dommage;

    /**
     * initialize
     */
    public Epee() {
    }

    /**
     * initialize
     *
     * @param dommage point of hurt
     * @param life    points of life
     * @param valeur  price
     */
    public Epee(int dommage, int life, int valeur) {
        super(life, valeur);
        this.dommage = dommage;
    }

    /**
     * initialize
     *
     * @param dommage int
     */
    public Epee(int dommage) {
        this.dommage = dommage;
    }


    /**
     * 放伤害
     *
     * @param c creature
     */
    public void Blesser(Creature c) {
        c.setPtVie(c.getPtVie() - dommage);
    }

    /**
     * get dommage
     *
     * @return int dommage
     */
    public int getDommage() {
        return dommage;
    }

    /**
     * set dommage
     *
     * @param dommage, int
     */
    public void setDommage(int dommage) {
        this.dommage = dommage;
    }

}

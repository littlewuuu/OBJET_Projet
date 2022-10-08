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
public class Fleche extends Objet {
    /**
     * Used to indicate on the OOCUPIED table that the position is occupied by Fleche
     */
    private final int type = 2;
    /**
     * Injuries caused by Fleche
     */
    private int dommage = 4;

    public Fleche(int dommage, int life, int valeur) {
        super(life, valeur);
        this.dommage = dommage;
        setPos(World.createPoints(type));
    }

    public Fleche(int dommage) {
        this.dommage = dommage;
        setPos(World.createPoints(type));
    }

    public Fleche(Fleche f){
        super(f);
        dommage = f.getDommage();
    }
    public Fleche() {
        setPos(World.createPoints(type));
    }


    public int getDommage() {
        return dommage;
    }

    public void setDommage(int dommage) {
        this.dommage = dommage;
    }

    public int getType() {
        return type;
    }

    public void affiche() {
        System.out.printf("Fleche : ");
        super.affiche();
    }
}

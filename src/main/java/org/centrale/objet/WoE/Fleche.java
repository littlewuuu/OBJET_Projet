/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.centrale.objet.WoE;

/**
 *
 * @author wuzilong
 */
public class Fleche extends Objet {
    //Injuries caused by Fleche
    private int dommage= 4;

    public Fleche(int dommage, int life, int valeur) {
        super(life, valeur);
        this.dommage = dommage;
    }

    public Fleche(int dommage) {
        this.dommage = dommage;
    }

    public int getDommage() {
        return dommage;
    }

    public void setDommage(int dommage) {
        this.dommage = dommage;
    }
    
    
}

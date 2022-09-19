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
public class Epee extends Objet{
    private int dommage;
    
    public Epee(){}

    public Epee(int dommage, int life, int valeur) {
        super(life, valeur);
        this.dommage = dommage;
    }
    
    public Epee(int dommage) {
        this.dommage = dommage;
    }
    
    //放伤害
    public void Blesser(Creature c){
        c.setPtVie(c.getPtVie()-dommage);
    }

    public int getDommage() {
        return dommage;
    }

    public void setDommage(int dommage) {
        this.dommage = dommage;
    }
    
}

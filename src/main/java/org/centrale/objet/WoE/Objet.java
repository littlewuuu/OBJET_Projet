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
public class Objet {
    
    /**
     * Durée d'utilisation
     */
    private int life; 
    
    /**
     * combien cela coûte-t-il d'acheter
     */
    private int valeur; 
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
    

    public Objet(int life, int valeur) {
        this.life = life;
        this.valeur = valeur;
    }
    public Objet(){}
    
}

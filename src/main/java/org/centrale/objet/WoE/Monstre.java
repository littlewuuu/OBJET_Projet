/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.centrale.objet.WoE;

import java.util.Random;

/**
 * The class Monstre represents the monsters of the world, a class that inherits
 * from the Creature class.
 *
 * @author WuZilong
 * @author ZouKang
 * @version 1.0
 */
public class Monstre extends Creature {

    /**
     * Initializes a newly created Monstre object, so that it represents
     * the same values of characters as the argument.
     * @param ptVie represent 
     * @param degAtt
     * @param ptPar            
     * @param pageAtt
     * @param pagePar
     * @param pos 
     */
    public Monstre(int ptVie, int degAtt, int ptPar, int pageAtt, int pagePar, Point2D pos) {
        super(ptVie, degAtt, ptPar, pageAtt, pagePar, pos);
    }
    /**
     * Initializes a newly created Monstre object by another Monstre objet m
     * @param m 
     */
    public Monstre(Creature m) {
        super(m);
    }

    /**
     * Initializes a newly created Monstre object so that all the attributes are 0 or null.
     */
    public Monstre() {
        super();
    }
    
    /**
     * To display all information of a Monstre objet.
     */
    public void affiche() {
        super.affiche();
    }


}

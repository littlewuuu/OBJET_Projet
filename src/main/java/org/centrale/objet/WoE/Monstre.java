/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.centrale.objet.WoE;

/**
 * The class Monstre represents the monsters of the world, a class that inherits
 * from thhe Creature class.
 *
 * @author WuZilong
 * @author ZouKang
 * @version 1.0
 */
public class Monstre extends Creature {

    /**
     * Initializes a newly created Monstre object, so that it represents
     * the same values of characters as the argument.
     *
     * @param ptVie   represents life value
     * @param degAtt  Valeur des dommages
     * @param ptPar   points de parade du défenseur
     * @param pageAtt pourcentages d’attaque
     * @param pagePar Probabilité de défense
     * @param pos     Position de Monstre
     */
    public Monstre(int ptVie, int degAtt, int ptPar, int pageAtt, int pagePar, Point2D pos) {
        super(ptVie, degAtt, ptPar, pageAtt, pagePar, pos);
    }

    /**
     * Initializes a newly created Monstre object by another Monstre objet m
     *
     * @param m Another Monstre type for initialization
     */
    public Monstre(Creature m) {
        super(m);
    }


    public Monstre(Point2D p) {
        super(p);
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

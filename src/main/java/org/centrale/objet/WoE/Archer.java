/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.centrale.objet.WoE;

import java.util.Random;

/**
 *
 * @author wuzilong et zoukang
 */
public class Archer extends Personnage {

    private Fleche fleche = new Fleche(10);
    private int nbFleche = 10;

    public Archer(String nom, int ptVie, int degAtt, int ptPar, int pageAtt, int pagePar, int distAttMax, Point2D pos) {
        super(nom, ptVie, degAtt, ptPar, pageAtt, pagePar, distAttMax, pos);
    }

    public Archer(Archer a) {
        super(a);
    }

    public Archer() {
        super();
    }

    public Archer(String name) {
        super(name);
    }

    
    /**
     * Il s'agit d'un système de combat pour combattre une autre créature. 
     * Il reconnaît les attaques à distance ou les combats de mêlée et simule la 
     * probabilité de toucher et de se défendre au moyen de nombres aléatoires.
     * @param c Creature
     */
    void combattre(Creature c) {
        Random generateRandom = new Random();
        int randatt = generateRandom.nextInt(100) + 1;
        int randdef = generateRandom.nextInt(100) + 1;
        double distance = Point2D.distance(this.getPos().getX(), c.getPos().getX(), this.getPos().getY(), c.getPos().getY());
        if (distance == 1) { // combat contact
            if (randatt > c.getPageAtt()) {//rate
            } else {//reussie
                if (randdef > c.getPagePar()) { //防御失败
                    c.setPtVie(c.getPtVie() - this.getDegAtt());
                } else {//防御成功
                    c.setPtVie(c.getPtVie() - this.getDegAtt() + c.getPtPar());
                }
            }
        } else if (distance > 1 && distance < this.getDistAttMax()) { //combat a distance
            if (nbFleche > 0) {
                int randdis = generateRandom.nextInt(100) + 1;
                if (randdis > this.getPageAtt()) {

                } else {
                    c.setPtVie(c.getPtVie() - fleche.getDommage());
                    nbFleche--;
                }
            } else {
                System.out.println("pas de Fleche");
            }
        }
    }

    public void affiche() {
        System.out.println("Archer: ");
        System.out.println("nbFleche=" + nbFleche+" ");

        super.affiche();
    }

}

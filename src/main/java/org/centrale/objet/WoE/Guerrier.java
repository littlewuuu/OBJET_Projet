/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.centrale.objet.WoE;

import java.util.Random;

/**
 *
 * @author wuzilong
 * @author Zou Kang
 */
public class Guerrier extends Personnage {

    private Epee epee = new Epee(8);
    public Guerrier(String nom, int ptVie, int degAtt, int ptPar, int pageAtt, int pagePar, int distAttMax, Point2D pos) {
        super(nom, ptVie, degAtt, ptPar, pageAtt, pagePar, distAttMax, pos);
    }

    public Guerrier(Personnage perso) {
        super(perso);
    }
    
    public Guerrier(String name){
        super(name);
    }
    
    public Guerrier() {
    }
    
    public Guerrier(Point2D p){
        super(p);
    }
    
    /**
     * Attack a creature according to the position of the target.
     * If the distance is 1, then it is melee, otherwise it is long range.
     * For each type of combat, we have a certain probability that the attack will fail, 
     * and if it fails, there is no damage. After a successful attack. The target object has 
     * a certain chance to succeed in defense, and if it succeeds, the damage is cut.
     * @param c Target creature of the attack.
     */  
    void combattre(Creature c) {
        Random generateRandom = new Random();
        int randatt = generateRandom.nextInt(100) + 1;
        int randdef = generateRandom.nextInt(100) + 1;
        double distance = Point2D.distance(this.getPos().getX(), this.getPos().getY(), c.getPos().getX(), c.getPos().getY());
        if (distance == 1) {
            if (randatt > c.getPageAtt()) {//rate
            } else {//reussie
                if (randdef > c.getPagePar()) {  
                    c.setPtVie(c.getPtVie() - this.epee.getDommage()); //on utilise le dommage de epee 
                } else { 
                    c.setPtVie(c.getPtVie() - this.epee.getDommage()+ c.getPtPar());
                }
            }
        } else if (distance > 1 && distance < this.getDistAttMax()) { //conbattre a distance

            int randdis = generateRandom.nextInt(100) + 1;
            if (randdis > this.getPageAtt()) {

            } else {
                c.setPtVie(c.getPtVie() - epee.getDommage());
            }
        }
    }

    @Override
    public String toString() {
        return "Guerrier{" + '}';
    }

    public void affiche() {
        System.out.println("Guerrier: ");
        super.affiche();
    }

}

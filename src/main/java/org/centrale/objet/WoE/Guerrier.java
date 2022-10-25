/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.centrale.objet.WoE;

import java.util.Random;
import java.util.Vector;

/**
 * It is an element of the world that can be selected by the player
 * and will also be automatically generated by the system.
 * Random movement at regular intervals.
 * @author wuzilong
 * @author Zou Kang
 */
public class Guerrier extends Personnage implements Combattant {

    /**
     * marker on the map OCCUPIED[][]
     */
    final private int type = 6;

    /**
     * Store Epees owned by the Guerrier
     */
    private Vector<Epee> epees = new Vector<>();

    public Guerrier(String nom, int ptVie, int degAtt, int ptPar, int pageAtt, int pagePar, int distAttMax, Point2D pos) {
        super(nom, ptVie, degAtt, ptPar, pageAtt, pagePar, distAttMax, pos);
    }

    public Guerrier(Personnage perso) {
        super(perso);
    }

    public Guerrier(String name) {
        super(name);
    }

    public Guerrier() {
        super();
        setType(6);
    }

    public Guerrier(Point2D p) {
        super(p);
    }

    public Guerrier(int type10, String name10, int distattmax10, int vie10, int degatt10, int ptpar10, int pageatt10, int pageapr10, int direction10, int x10, int y10) {
        setType(type10);
        setNom(name10);
        setDistAttMax(distattmax10);
        setPtVie(vie10);
        setDegAtt(degatt10);
        setPtPar(ptpar10);
        setPageAtt(pageatt10);
        setPagePar(pageapr10);
        setDirection(direction10);
        setPos(new Point2D(x10, y10));
    }

    /**
     * When a Personnage find an Epee, it can put into its Vector.
     *
     * @param e
     */
    public void trouEpee(Epee e) {
        epees.add(e);
    }

    /**
     * This function is called when the epee has run out of life and is removed from the bag.
     */
    public void useEpee() {
        if (epees.size() == 0) {
            System.out.print("no epee for use");
        }
        epees.removeElementAt(epees.size() - 1);
    }

    @Override
    public int getType() {
        return type;
    }


    /**
     * Attack a creature according to the position of the target.
     * If the distance is 1, then it is melee, otherwise it is long range.
     * For each type of combat, we have a certain probability that the attack will fail,
     * and if it fails, there is no damage. After a successful attack. The target object has
     * a certain chance to succeed in defense, and if it succeeds, the damage is cut.
     *
     * @param c Target creature of the attack.
     */
    public void combattre(Creature c) {
        Random generateRandom = new Random();
        int randatt = generateRandom.nextInt(100) + 1;
        int randdef = generateRandom.nextInt(100) + 1;
        double distance = Point2D.distance(this.getPos().getX(), this.getPos().getY(), c.getPos().getX(), c.getPos().getY());
        if (distance == 1) {
            if (randatt > c.getPageAtt()) {//rate
            } else {//reussie
                if (randdef > c.getPagePar()) {
                    c.setPtVie(c.getPtVie() - this.getDegAtt()); //on utilise le dommage de epee
                } else {
                    c.setPtVie(c.getPtVie() - this.getDegAtt() + c.getPtPar());
                }
            }
        } else if (distance > 1 && distance < this.getDistAttMax()) { //conbattre a distance
            int randdis = generateRandom.nextInt(100) + 1;
            if (randdis > c.getPageAtt()) {
            } else { //success
                if (epees.size() == 0) {
                    System.out.println("no epee to use");
                } else if (epees.lastElement().getLife() == 0) {
                    useEpee();
                    Joueur.setNbEpee(Joueur.getNbEpee() - 1);
                    System.out.println("use another Epee");
                } else {
                    c.setPtVie(c.getPtVie() - epees.get(epees.size() - 1).getDommage());
                    epees.lastElement().setLife(epees.lastElement().getLife() - 5);//Each attack loses 5 points of durability
                }


            }
        }
    }

    @Override
    public String toString() {
        return "Guerrier " + " " + type + " " + getNom() + " " + getDistAttMax() + " " + getPtVie() + " " + getDegAtt() + " " + getPtPar() + " " + getPageAtt() + " " + getPagePar() + " " + getDirection() + " " + getPos().getX() + " " + getPos().getY() + '\n';
    }

    public void affiche() {
        System.out.println("Guerrier: ");
        super.affiche();
    }

    public Vector<Epee> getEpees() {
        return epees;
    }


}

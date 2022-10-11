/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.centrale.objet.WoE;

import java.util.Random;
import java.util.Vector;

/**
 * @author wuzilong
 * @author Zou Kang
 */
public class Archer extends Personnage implements Combattant {

    final private int type = 5;
    private Vector<Fleche> fleches = new Vector<>();
    private Vector<Epee> epees = new Vector<>();

    /**
     * initialize Archer
     *
     * @param nom        name
     * @param ptVie      point of life
     * @param degAtt     points of attack
     * @param ptPar      points of defence
     * @param pageAtt    percentage of att
     * @param pagePar    percentage of par
     * @param distAttMax max att distance
     * @param pos        position
     */
    public Archer(String nom, int ptVie, int degAtt, int ptPar, int pageAtt, int pagePar, int distAttMax, Point2D pos) {
        super(nom, ptVie, degAtt, ptPar, pageAtt, pagePar, distAttMax, pos);

    }

    /**
     * Use an Archer to initialize an Archer
     *
     * @param a an instance of Archer
     */
    public Archer(Archer a) {
        super(a);
    }

    /**
     * Initial an Archer without parameter
     */
    public Archer() {
        super();
        setType(5);
        for (int i = 0; i < 10; i++) {
            fleches.add(new Fleche(true));
        }
    }

    @Override
    public int getType() {
        return type;
    }

    /**
     * Initialize Archer with name
     *
     * @param name name of Archer
     */
    public Archer(String name) {
        super(name);
        setType(5);
    }

    /**
     * initialize with position
     *
     * @param p position, Class of Point2D
     */
    public Archer(Point2D p) {
        super(p);
    }

    public void trouFleche(Fleche f) {
        fleches.add(f);
    }

    /**
     * 返回用掉的箭
     * @return
     */
    public Fleche useFleche() {
        if (fleches.size() == 0) {
            System.out.print("you have no fleche");
            return null;
        }
        Fleche fleche = fleches.get(fleches.size() - 1);
        fleches.removeElementAt(fleches.size() - 1);
        return fleche;
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
        double distance = Point2D.distance(this.getPos().getX(),  this.getPos().getY(),c.getPos().getX(), c.getPos().getY());
        if (distance == 1) { // combat contact
            if (randatt > c.getPageAtt()) {//rate
            } else {//reussie
                if (randdef > c.getPagePar()) { //防御失败
                    c.setPtVie(c.getPtVie() - this.getDegAtt());
                } else {//防御成功
                    c.setPtVie(c.getPtVie() - this.getDegAtt() + c.getPtPar());
                }
            }
        } else if (distance > 1 && distance < this.getDistAttMax()) { //combat a distance through Fleche
            if (fleches.size() > 0) {
                int randdis = generateRandom.nextInt(100) + 1;
                if (randdis > c.getPageAtt()) {
                    Joueur.setNbFleche(Joueur.getNbFleche()-1); //没击中也会损耗 Fleche
                    //************为了显示箭的移动轨迹，没写完
                    Fleche fleche = useFleche();
                    fleche.setDirection(super.getDirection());
                    fleche.setPos(new Point2D(getPos().getX(),getPos().getY()));
                    new Thread(fleche).start();
                } else {
                    c.setPtVie(c.getPtVie() - fleches.lastElement().getDommage());
                    //***********为了显示箭的移动轨迹，没写完
                    Fleche fleche = useFleche();
                    fleche.setDirection(super.getDirection());
                    fleche.setPos(new Point2D(getPos().getX(),getPos().getY()));
                    new Thread(fleche).start();
                    Joueur.setNbFleche(Joueur.getNbFleche()-1);
                }
            } else {
                System.out.println("There is no Fleche in your bag!");
            }
        }
    }

    /**
     * Print information of archer.
     */
    public void affiche() {
        System.out.println("Archer: ");
        System.out.print("nbFleche=" + fleches.size() + " ");
        super.affiche();
    }

    public Vector<Fleche> getFleches() {
        return fleches;
    }

    public Vector<Epee> getEpees() {
        return epees;
    }
}

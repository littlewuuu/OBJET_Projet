/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.centrale.objet.WoE;

import java.util.Random;

/**
 * @author wuzilong
 * @author Zou Kang
 */
public class Loup extends Monstre implements Combattant {

    private final int type = 9;

    /**
     * The longest distance a Loup can attack.
     */
    private int distAttMax = 5;
    /**
     * The damage that wolves can cause.
     */
    private final int degAtt = 5;

    public Loup(int ptVie, int degAtt, int ptPar, int pageAtt, int pagePar, Point2D pos) {
        super(ptVie, degAtt, ptPar, pageAtt, pagePar, pos);
    }

    public Loup(Lapin m) {
        super(m);
    }

    public Loup() {
        super();
        setType(9);
    }

    public Loup(Point2D p) {
        super(p);
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
        if (distance == 1) {//combat contact
            if (randatt > c.getPageAtt()) {//rate 没击中
            } else {//success
                if (randdef > c.getPagePar()) { //防御失败
                    c.setPtVie(c.getPtVie() - this.getDegAtt());
                } else {//防御成功
                    c.setPtVie(c.getPtVie() - this.getDegAtt() + c.getPtPar());
                }
            }
        } else if (distance > 1 && distance < this.distAttMax) { //combat a distance

            int randdis = generateRandom.nextInt(100) + 1;
            if (randdis > c.getPageAtt()) {
            } else {
                c.setPtVie(c.getPtVie() - degAtt);
            }
        }
    }

    public int getGetDistAttMax() {
        return distAttMax;
    }

    public void setGetDistAttMax(int getDistAttMax) {
        this.distAttMax = getDistAttMax;
    }


    public void affiche() {
        System.out.println("Loup: ");
        super.affiche();
    }

}

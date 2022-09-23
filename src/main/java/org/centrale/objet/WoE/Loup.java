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
 */
public class Loup extends Monstre{
    
    //狼的最远攻击距离，新增
    private int distAttMax = 5;
    //狼的伤害
    private int Att = 5;

    public Loup(int ptVie, int degAtt, int ptPar, int pageAtt, int pagePar, Point2D pos) {
        super(ptVie, degAtt, ptPar, pageAtt, pagePar, pos);
    }

    public Loup(Lapin m) {
        super(m);
    }

    public Loup() {
    }
    
    
    public Loup(Point2D p){
        super(p);
    }
    
        
 void combattre(Creature c) {
        Random generateRandom = new Random();
        int randatt = generateRandom.nextInt(100) + 1;
        int randdef = generateRandom.nextInt(100) + 1;
        double distance = Point2D.distance(this.getPos().getX(), this.getPos().getY(), c.getPos().getX(), c.getPos().getY());
        if (distance == 1) {//近战
            if (randatt > c.getPageAtt()) {//rate 没集击中
            } else {//击中
                if (randdef > c.getPagePar()) { //防御失败
                    c.setPtVie(c.getPtVie() - this.getDegAtt());
                } else {//防御成功
                    c.setPtVie(c.getPtVie() - this.getDegAtt() + c.getPtPar());
                }
            }
        } else if (distance > 1 && distance < this.distAttMax) { //远距离攻击

            int randdis = generateRandom.nextInt(100) + 1;
            if (randdis > this.getPageAtt()) {

            } else {
                c.setPtVie(c.getPtVie() - Att);
            }
        }
    }

    public int getGetDistAttMax() {
        return distAttMax;
    }

    public void setGetDistAttMax(int getDistAttMax) {
        this.distAttMax = getDistAttMax;
    }
    
    
    
    public void affiche(){
        System.out.println("Loup: ");
        super.affiche();
    }
    
}

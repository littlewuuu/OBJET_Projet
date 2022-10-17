/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.centrale.objet.WoE;

import java.util.Random;

/**
 * @author wuzilong
 * @author zoukang
 */
public abstract class Creature extends ElementDeJeu implements Deplacable,Runnable {

    private int ptVie = 100;
    private int degAtt = 20;
    private int ptPar = 10;
    private int pageAtt = 70;
    private int pagePar = 60;


    private int direction = 1;

    public int getDirection() {
        return direction;
    }

    public void setDirection(int direction) {
        this.direction = direction;
    }

    /**
     * 1:Joueur
     * 2:Fleche
     * 3:Epee
     * 4:PotionSoin
     * 5:Archer
     * 6:Guerrier
     * 7:Paysan
     * 8:Lapin
     * 9:Loup
     */


    /**
     * initialize
     *
     * @param ptVie   point of life
     * @param degAtt  points of attack
     * @param ptPar   points of defence
     * @param pageAtt percentage of attack
     * @param pagePar percentage of denfence
     * @param pos     position
     */
    public Creature(int ptVie, int degAtt, int ptPar, int pageAtt, int pagePar, Point2D pos) {
        super(pos);
        this.ptVie = ptVie;
        this.degAtt = degAtt;
        this.ptPar = ptPar;
        this.pageAtt = pageAtt;
        this.pagePar = pagePar;
    }

    /**
     * initialize
     *
     * @param m creature
     */
    public Creature(Creature m) {
        super(m.getPos());
        this.degAtt = m.degAtt;
        this.pageAtt = m.pageAtt;
        this.pagePar = m.pagePar;

        //this.pos = m.pos;
        this.ptPar = m.ptPar;
        this.ptVie = m.ptVie;
    }

    /**
     * initialize
     *
     * @param p position
     */
    public Creature(Point2D p) {
        super(p);
    }

    /**
     * initialize by random.
     */
    public Creature() {
        super();
        Point2D pos = new Point2D(World.createPoints(getType()));
        setPos( new Point2D(pos));
    }

    public int getType() {
        return super.getType();
    }

    public void setType(int type) {
        super.setType(type);
    }

    /**
     * get ptvie
     *
     * @return int ptvie
     */
    public int getPtVie() {
        return ptVie;
    }

    /**
     * set ptvie
     *
     * @param ptVie point of life
     */
    public void setPtVie(int ptVie) {
        this.ptVie = ptVie;
    }

    /**
     * get points of att
     *
     * @return int degatt
     */
    public int getDegAtt() {
        return degAtt;
    }

    /**
     * Set points of att.
     *
     * @param degAtt points of attack
     */

    public void setDegAtt(int degAtt) {
        this.degAtt = degAtt;
    }

    /**
     * Get points of defense.
     *
     * @return int ptpar
     */
    public int getPtPar() {
        return ptPar;
    }

    /**
     * Set points of defense
     *
     * @param ptPar points of defence
     */
    public void setPtPar(int ptPar) {
        this.ptPar = ptPar;
    }


    /**
     * Get percentage of attack.
     *
     * @return int, percentage of attack
     */

    public int getPageAtt() {
        return pageAtt;
    }

    /**
     * Set percentage of att
     *
     * @param pageAtt percentage of att
     */
    public void setPageAtt(int pageAtt) {
        this.pageAtt = pageAtt;
    }

    /**
     * get percentage of defence
     *
     * @return int, percentage of defence
     */
    public int getPagePar() {
        return pagePar;
    }

    /**
     * Set percentage of defence
     *
     * @param pagePar percentage of defence
     */
    public void setPagePar(int pagePar) {
        this.pagePar = pagePar;
    }

//    /**
//     * Get position
//     *
//     * @return Point2D, position
//     */
//    public Point2D getPos() {
//        return super.getPos();
//    }
//
//    /**
//     * Set position
//     *
//     * @param pos position
//     */
//    public void setPos(Point2D pos) {
//        super.setPos(new Point2D(pos));
//    }

    /**
     * Il s'agit d'une fonction qui se déplace de manière aléatoire dans
     * huit directions autour de la zone, en utilisant un nombre aléatoire
     * pour déterminer la direction du mouvement.
     */
    public void deplacer() {
        Random generateRandom = new Random();
        int x, y;
        x = y = 0;
        do {
            x = generateRandom.nextInt(3)-1;
            y = generateRandom.nextInt(3)-1;
            if ((x != 0 || y != 0)
                    &&(this.getPos().getX() + x >=0)
                    && (this.getPos().getY() + y >=0)
                    &&(this.getPos().getX() + x <= World.TAILLE-1)
                    &&(this.getPos().getY() + y <= World.TAILLE - 1)
                    && World.getOCCUPIED(this.getPos().getX() + x, this.getPos().getY() + y) == 0) {
                break;
            }
        } while (true);
        World.setOCCUPIED(this.getPos().getX(), this.getPos().getY(), 0);
        getPos().translate(x, y);
        World.setOCCUPIED(this.getPos().getX(), this.getPos().getY(), getType());
    }

    /**
     * print out all information.
     */
    public void affiche() {
        System.out.println("ptVie=" + ptVie + ", degAtt=" + degAtt + ", ptPar=" + ptPar + ", pageAtt=" + pageAtt + ", pagePar=" + pagePar + ", pos=" + super.getPos() + "\n");
    }

    @Override
    public void run() {
        while (true) {
            //玩家死亡后就暂停不移动
            if(World.GAMESTATUESTATUS == 0){
                continue;
            }
            System.out.println("creature moving");
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            deplacer();
            if(this.getPtVie()<=0)
                break;
        }
    }
}

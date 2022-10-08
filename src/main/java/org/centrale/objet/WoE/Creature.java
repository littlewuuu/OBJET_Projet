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
public class Creature extends ElementDeJeu implements Deplacable,Runnable {

    private int ptVie = 100;
    private int degAtt = 20;
    private int ptPar = 10;
    private int pageAtt = 70;
    private int pagePar = 60;
    private Point2D pos;

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
    private int type;


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
        this.ptVie = ptVie;
        this.degAtt = degAtt;
        this.ptPar = ptPar;
        this.pageAtt = pageAtt;
        this.pagePar = pagePar;
        this.pos = new Point2D(pos);
    }

    /**
     * initialize
     *
     * @param m creature
     */
    public Creature(Creature m) {
        this.degAtt = m.degAtt;
        this.pageAtt = m.pageAtt;
        this.pagePar = m.pagePar;
        this.pos = new Point2D(m.pos);
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
        this.pos = new Point2D(p);
    }

    /**
     * initialize by random.
     */
    public Creature() {
        Point2D pos = new Point2D(World.createPoints(getType()));
        this.pos = new Point2D(pos);
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
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

    /**
     * Get position
     *
     * @return Point2D, position
     */
    public Point2D getPos() {
        return pos;
    }

    /**
     * Set position
     *
     * @param pos position
     */
    public void setPos(Point2D pos) {
        this.pos = new Point2D(pos);
    }

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
        pos.translate(x, y);
        World.setOCCUPIED(this.getPos().getX(), this.getPos().getY(), getType());
    }

    /**
     * print out all information.
     */
    public void affiche() {
        System.out.println("ptVie=" + ptVie + ", degAtt=" + degAtt + ", ptPar=" + ptPar + ", pageAtt=" + pageAtt + ", pagePar=" + pagePar + ", pos=" + pos + "\n");
    }

    @Override
    public void run() {
        while (true) {
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

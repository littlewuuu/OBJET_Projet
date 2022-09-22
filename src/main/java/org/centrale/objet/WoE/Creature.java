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
 * @author zoukang
 */
public class Creature {

    private int ptVie = 100;
    private int degAtt = 20;
    private int ptPar = 10;
    private int pageAtt = 70;
    private int pagePar = 60;
    private Point2D pos;

    public Creature(int ptVie, int degAtt, int ptPar, int pageAtt, int pagePar, Point2D pos) {
        this.ptVie = ptVie;
        this.degAtt = degAtt;
        this.ptPar = ptPar;
        this.pageAtt = pageAtt;
        this.pagePar = pagePar;
        this.pos = new Point2D(pos);
    }

    public Creature(Creature m) {
        this.degAtt = m.degAtt;
        this.pageAtt = m.pageAtt;
        this.pagePar = m.pagePar;
        this.pos = new Point2D(m.pos);
        //this.pos = m.pos;
        this.ptPar = m.ptPar;
        this.ptVie = m.ptVie;
    }
    
 
    public Creature() {

    }

    public int getPtVie() {
        return ptVie;
    }

    public void setPtVie(int ptVie) {
        this.ptVie = ptVie;
    }

    public int getDegAtt() {
        return degAtt;
    }

    public void setDegAtt(int degAtt) {
        this.degAtt = degAtt;
    }

    public int getPtPar() {
        return ptPar;
    }

    public void setPtPar(int ptPar) {
        this.ptPar = ptPar;
    }

    public int getPageAtt() {
        return pageAtt;
    }

    public void setPageAtt(int pageAtt) {
        this.pageAtt = pageAtt;
    }

    public int getPagePar() {
        return pagePar;
    }

    public void setPagePar(int pagePar) {
        this.pagePar = pagePar;
    }

    public Point2D getPos() {
        return pos;
    }

    public void setPos(Point2D pos) {
        this.pos = new Point2D(pos);
    }

    /**
     * Il s'agit d'une fonction qui se déplace de manière aléatoire dans 
     * huit directions autour de la zone, en utilisant un nombre aléatoire 
     * pour déterminer la direction du mouvement.
     */
    public void deplace() {
        Random generateRandom = new Random();
        int x, y;
        x = y = 0;
        do {
            x = generateRandom.nextInt(2);
            y = generateRandom.nextInt(2);
            if (x != 0 || y != 0) {
                break;
            }
        } while (true);

        pos.translate(x, y);
    }


    public void affiche() {
        System.out.println( "ptVie=" + ptVie + ", degAtt=" + degAtt + ", ptPar=" + ptPar + ", pageAtt=" + pageAtt + ", pagePar=" + pagePar + ", pos=" + pos + "\n");
    }


    

}

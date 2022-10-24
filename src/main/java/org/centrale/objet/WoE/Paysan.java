/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.centrale.objet.WoE;

/**
 * @author wuzilong
 * @author Zou Kang
 */
public class Paysan extends Personnage {

    private final int type = 7;

    public Paysan(String nom, int ptVie, int degAtt, int ptPar, int pageAtt, int pagePar, int distAttMax, Point2D pos) {
        super(nom, ptVie, degAtt, ptPar, pageAtt, pagePar, distAttMax, pos);
    }

    public Paysan(Paysan p) {
        super(p);
    }

    public Paysan(Point2D p) {
        super(p);
    }

    public Paysan() {
        super();
        setType(7);
    }

    public Paysan(String name) {
        super(name);
    }

    public Paysan(int type6, int vie6, int degatt6, int ptpar, int pageatt, int pageapr, int direction, int x, int y) {
        setType(type6);
        setPtVie(vie6);
        setDegAtt(degatt6);
        setPtPar(ptpar);
        setPageAtt(pageatt);
        setPtPar(ptpar);
        setPagePar(pageapr);
        setDirection(direction);
        setPos(new Point2D(x, y));
    }

    public Paysan(int type, String name, int distattmax, int vie7, int degatt7, int ptpar7, int pageatt7, int pageapr7, int direction7, int x7, int y7) {
        setType(type);
        setNom(name);
        setDistAttMax(distattmax);
        setPtVie(vie7);
        setDegAtt(degatt7);
        setPtPar(ptpar7);
        setPageAtt(pageatt7);
        setPagePar(pageapr7);
        setDirection(direction7);
        setPos(new Point2D(x7, y7));

    }

    public void affiche() {
        System.out.println("Paysan: ");
        super.affiche();
    }

    @Override
    public int getType() {
        return type;
    }

    @Override
    public String toString() {
        return "Paysan " + " " + type + " " + getNom() + " " + getDistAttMax() + " " + getPtVie() + " " + getDegAtt() + " " + getPtPar() + " " + getPageAtt() + " " + getPagePar() + " " + getDirection() + " " + getPos().getX() + " " + getPos().getY() + '\n';
    }
}

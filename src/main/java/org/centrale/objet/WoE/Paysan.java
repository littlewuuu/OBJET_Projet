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

    public void affiche() {
        System.out.println("Paysan: ");
        super.affiche();
    }

    @Override
    public int getType() {
        return type;
    }
}

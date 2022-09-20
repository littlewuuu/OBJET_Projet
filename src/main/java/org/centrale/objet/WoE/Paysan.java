/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.centrale.objet.WoE;

/**
 *
 * @author wuzilong et zoukang
 */
public class Paysan extends Personnage{

    public Paysan(String nom, int ptVie, int degAtt, int ptPar, int pageAtt, int pagePar, int distAttMax, Point2D pos) {
        super(nom, ptVie, degAtt, ptPar, pageAtt, pagePar, distAttMax, pos);
    }
    
    public Paysan(Paysan p){
        super(p);
    }
    
    public Paysan() {
        super();
    }
    public void affiche(){
        System.out.println("Paysan: ");
        super.affiche();
    }
    public Paysan(String name){
        super(name);
    }
    
    
}

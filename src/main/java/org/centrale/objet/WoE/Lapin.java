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
public class Lapin extends Monstre {

    public Lapin(int ptVie, int degAtt, int ptPar, int pageAtt, int pagePar, Point2D pos) {
        super(ptVie, degAtt, ptPar, pageAtt, pagePar, pos);
    }

    public Lapin(Lapin m) {
        super(m);
    }

    public Lapin() {
    }
    
    public Lapin(Point2D p){
        super(p);
    }
    
     public void affiche(){
         System.out.println("Lapin:");
        super.affiche();
    }
    
 
    
    
    
  

}

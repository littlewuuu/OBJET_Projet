/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.centrale.objet.WoE;

/**
 *
 * @author wuzilong
 */
public class Loup extends Monstre{

    public Loup(int ptVie, int degAtt, int ptPar, int pageAtt, int pagePar, Point2D pos) {
        super(ptVie, degAtt, ptPar, pageAtt, pagePar, pos);
    }

    public Loup(Lapin m) {
        super(m);
    }

    public Loup() {
    }
    
        
   //没写完
    void combattre(Creature c){
        
            
        }
    
    
    
    public void affiche(){
        super.affiche();
    }
    
}

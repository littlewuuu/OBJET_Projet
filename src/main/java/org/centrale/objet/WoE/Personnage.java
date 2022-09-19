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
public class Personnage extends Creature {

    private String nom;

    private int distAttMax = 3;

    public Personnage(String nom, int ptVie, int degAtt, int ptPar, int pageAtt, int pagePar, int distAttMax, Point2D pos) {
        super(ptVie, degAtt, ptPar, pageAtt, pagePar, pos);
        this.nom = nom;
        this.distAttMax = distAttMax;

    }

    public Personnage(String nom) {
        this.nom = nom;
    }
    
    

    
/**
 * 
 * @param perso
 */
    public Personnage(Personnage perso) {
        this.nom = perso.nom;

        this.distAttMax = perso.distAttMax;
        //this.pos = new Point2D(perso.pos);//注意 new
    }

    public Personnage() {
        super();
        this.nom = null;
        this.distAttMax = 0;

    }

//    public void affiche() {
//        System.out.println("================================");
//        System.out.printf("les information du Personnage: \n");
//        System.out.printf("nom: " + nom + "\ndistAttMax" + distAttMax + "ptVie: " + ptVie + "\ndegAtt: " + degAtt + "\nptPar: "
//                + ptPar + "\npageAtt: " + pageAtt + "\npagePar: " + pagePar + "\npos: " + pos + "\n");
//
//    }
        
/**
 * @return String
 * @param perso
 */
    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public int getDistAttMax() {
        return distAttMax;
    }

    public void setDistAttMax(int distAttMax) {
        this.distAttMax = distAttMax;
    }
    
    
    public void affiche(){
        System.out.printf("nom=" + nom + ",distAttMax=" + distAttMax);
        super.affiche();
    }

}

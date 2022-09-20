/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.centrale.objet.WoE;
import java.util.*;


/**
 * The class {Personnage} represents a human being in the world, 
 * 
 * 
 * @author Wu Zilong 
 * @author Zou Kang
 */
public class Personnage extends Creature {
    
    /**
     * Le nom de Personnnage
     */
    private String nom;
    /**
     * 
     */
    private int distAttMax = 10;
    
    private Vector<PotionSoin> potionsoin = new Vector();//存放药水
    
    //找到药水加入背包
    public void TrouPotion(PotionSoin p){
        if(p instanceof PotionSoin){
            potionsoin.add(p);
        }
    }
       
    //使用药水
    public void usagePotion(PotionSoin p){
        if(potionsoin.size() == 0){
            System.out.println("no potion for use!");
            return;
        }
        potionsoin.remove(p);
        p=null;
    }

    public Personnage(String nom, int ptVie, int degAtt, int ptPar, int pageAtt, int pagePar, int distAttMax, Point2D pos) {
        super(ptVie, degAtt, ptPar, pageAtt, pagePar, pos);
        this.nom = nom;
        this.distAttMax = distAttMax;

    }

    public Personnage(String nom) {
        this.nom = nom;
    }

    
 

    public Vector<PotionSoin> getPotionsoin() {
        return potionsoin;
    }

    public void setPotionsoin(Vector<PotionSoin> potionsoin) {
        this.potionsoin = potionsoin;
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
        System.out.print("nom=" + nom + ",distAttMax=" + distAttMax);
        super.affiche();
    }

}

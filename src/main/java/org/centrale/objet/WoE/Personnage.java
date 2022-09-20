/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.centrale.objet.WoE;

import java.util.*;

/**
 * The class {Personnage} represents a human being in the world Under this
 * Superclass, we can find subclasses such as warrior, archer, etc. 
 * This class has features such as the ability to move, attack and use items.
 *
 * @author Wu Zilong
 * @author Zou Kang
 * @version 1.0
 */
public class Personnage extends Creature {

    /**
     * The name of a  person
     */
    private String nom;
    /**
     * Longest attack distance， default as 10
     */
    private int distAttMax = 10;
    /**
     * A vector of PotionSoin, 
     * Considering that a person can carry several bottles of potion, 
     * we set it as a vector type
     */
    private Vector<PotionSoin> potionsoin = new Vector();//存放药水

    //find potion, put into packsack
   /**
     * when find a potion type, put into knapsack
     * We will first check if the input parameter is a potion class,
     * if yes, put into knapsack
     * @param p 
     */
    public void TrouPotion(PotionSoin p) {
        if (p instanceof PotionSoin) {
            potionsoin.add(p);
        }
    }

    //使用药水
    /**
     * The process of using potion
     * first, we will check whether there is potion in Knapsack,
     * if no, program will printout "no potion"
     * if yes, potion will be moved out of knapsack, and set as null to be collect as garbage
     * @param p 
     */
    public void usagePotion(PotionSoin p) {
        if (potionsoin.size() == 0) {
            System.out.println("no potion for use!");
            return;
        }
        potionsoin.remove(p);
        p = null;
    }

    /**
     * Initializes a newly created Personnage object, so that it represents
     * the same values of characters as the argument.
     * @param nom
     * @param ptVie
     * @param degAtt
     * points of attack
     * @param ptPar 
     * points of defence
     * @param pageAtt
     * pourcentageAtt
     * @param pagePar
     * pourcentagePar
     * @param distAttMax
     * longest distance it can attack
     * @param pos 
     */
    public Personnage(String nom, int ptVie, int degAtt, int ptPar, int pageAtt, int pagePar, int distAttMax, Point2D pos) {
        super(ptVie, degAtt, ptPar, pageAtt, pagePar, pos);
        this.nom = nom;
        this.distAttMax = distAttMax;

    }

    /**
     * Initializes a newly created Personnage object by one name
     * So we just set the name of the person
     * @param nom 
     */
    public Personnage(String nom) {
        this.nom = nom;
    }
    
    /**
     * get potionsoin of a class of person
     * @return a vector of PotionSoin, PotionSoin is a class of potion, potion were stored in it
     */
    public Vector<PotionSoin> getPotionsoin() {
        return potionsoin;
    }
    
    /**
     * set a person's knapsack of potion with a knapsack's potion. So we can see it's a vector
     * @param potionsoin
     */
    public void setPotionsoin(Vector<PotionSoin> potionsoin) {
        this.potionsoin = potionsoin;
    }

    /**
     *Initializes a person with a person object
     * we use the object's name and disAttMax to initialize the new object
     * @param perso
     */
    public Personnage(Personnage perso) {
        this.nom = perso.nom;

        this.distAttMax = perso.distAttMax;
        //this.pos = new Point2D(perso.pos);//注意 new
    }
    
    /**
     * Initialize a person without parameter
     * 
     */
    public Personnage() {
        super();
        this.nom = null;
        this.distAttMax = 0;

    }

    /** get name 
     * @return the name of the person 
     */
    public String getNom() {
        return nom;
    }
    
    /** Set name of a person
     * @param nom 
     */
    public void setNom(String nom) {
        this.nom = nom;
    }

    /**
     * get disAttMax
     * @return the length of the person can attack
     */
    public int getDistAttMax() {
        return distAttMax;
    }
    
    /**
     * Set disAttMax
     * @param distAttMax 
     */
    public void setDistAttMax(int distAttMax) {
        this.distAttMax = distAttMax;
    }

    /**
     * print out the attribute of one person, name, disAttMax and so on
     */
    public void affiche() {
        System.out.print("nom=" + nom + ",distAttMax=" + distAttMax);
        super.affiche();
    }

}

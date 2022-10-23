/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.centrale.objet.WoE;

import java.util.Random;
import java.util.Vector;

/**
 * The class Personnage represents a human being in the world Under this
 * Superclass, we can find subclasses such as warrior, archer, etc. This class
 * has features such as the ability to move, attack and use items.
 *
 * @author Wu Zilong
 * @author Zou Kang
 * @version 1.0
 */
public abstract class Personnage extends Creature {

    /**
     * The name of a person.
     */
    private String nom;

    /**
     * Longest attack distance， default random.
     */
    private int distAttMax = 5;

    /**
     * A vector of PotionSoin, Considering that a person can carry several
     * bottles of potion, we set it as a vector type.
     */
    private Vector<PotionSoin> potionsoins = new Vector();

    private static Vector<Nourriture> nourritureInUse = new Vector();

    private  Vector<Epinard> epinards = new Vector<>();

    public static Vector<Nourriture> getNourritureInUse() {
        return nourritureInUse;
    }



    /**
     * Initializes a newly created Personnage object, so that it represents the
     * same values of characters as the argument.
     *
     * @param nom        name of person
     * @param ptVie      points of life
     * @param degAtt     points of attack
     * @param ptPar      points of defense
     * @param pageAtt    pourcentageAtt
     * @param pagePar    pourcentagePar
     * @param distAttMax longest distance it can attack
     * @param pos        position, class of class 2D
     */
    public Personnage(String nom, int ptVie, int degAtt, int ptPar, int pageAtt, int pagePar, int distAttMax, Point2D pos) {
        super(ptVie, degAtt, ptPar, pageAtt, pagePar, pos);
        this.nom = nom;
        this.distAttMax = distAttMax;

    }



    public Personnage(Point2D p) {
        super(p);
    }

    /**
     * Initializes a newly created Personnage object by one name So we just set
     * the name of the person.
     *
     * @param nom name of person
     */
    public Personnage(String nom) {
        this.nom = nom;
    }

    /**
     * Initializes a person with a person object we use the object's name and
     * disAttMax to initialize the new object.
     *
     * @param perso an instance of person
     */
    public Personnage(Personnage perso) {
        super(perso);
        this.nom = perso.getNom();
        this.distAttMax = perso.distAttMax;
    }

    /**
     * Initialize a person without parameter.
     */
    public Personnage() {
        super();
        this.nom = null;
        Random random = new Random();
        int att = random.nextInt(20);
        this.distAttMax = att;

    }

    /**
     * When find a potion type, put into knapsack We will first check if the
     * input parameter is a potion class, if yes, put into knapsack.
     *
     * @param p instance of Potion Soin
     */
    public void TrouPotion(PotionSoin p) {
        if (p instanceof PotionSoin) {
            potionsoins.add(p);
        }
    }

    /**
     * The process of using potion first, we will check whether there is potion
     * in Knapsack, if no, program will print "no potion" if yes, potion will
     * be moved out of knapsack, and set as null to be collect as garbage.
     */
    public void usePotion() {
        if (potionsoins.size() == 0) {
            System.out.println("no potion for use!");
            return;
        }
        if(this.getPtVie() == 100){
            System.out.println("there is no need to use PotionSoin!");
            return;
        }
        if(100-this.getPtVie() < potionsoins.lastElement().getRecover()) {
            potionsoins.removeElementAt(potionsoins.size() - 1);
            Joueur.setNbPotionSoin(Joueur.getNbPotionSoin()-1);
            this.setPtVie(100);
            this.affiche();
        }else {
            this.setPtVie(this.getPtVie() + potionsoins.lastElement().getRecover());
            potionsoins.removeElementAt(potionsoins.size() - 1);
            Joueur.setNbPotionSoin(Joueur.getNbPotionSoin()-1);
            this.affiche();
        }

    }


    public void useEpinard(){
        if(epinards.size() == 0){
            System.out.println("no Epinard for use!");
            return;
        }
        this.setDegAtt(this.getDegAtt() + epinards.lastElement().getBonus());
        nourritureInUse.add(epinards.lastElement());
        epinards.removeElementAt(epinards.size() - 1);
        Joueur.setNbEpinard(Joueur.getNbEpinard() - 1);
        System.out.println("degATT:"+MyPanel.joueur.perso.getDegAtt());
        this.affiche();
    }

    /**
     * Cancels the nourriture effect and restores the player's degAtt
     */
    public static void cancelEpinard(Epinard epinard){
        MyPanel.joueur.perso.setDegAtt(MyPanel.joueur.perso.getDegAtt() - epinard.getBonus());
    }


    /**
     * Get PotionSoin of a class of person
     *
     * @return a vector of PotionSoin, PotionSoin is a class of potion, potion were stored in it.
     */
    public Vector<PotionSoin> getPotionsoins() {
        return potionsoins;
    }

    public Vector<Epinard> getEpinard(){
        return epinards;
    }

    /**
     * Set a person's knapsack of potion with a knapsack's potion.
     * So we can see it's a vector
     *
     * @param potionsoins a list of PotionSoin(vector)
     */
    public void setPotionsoins(Vector<PotionSoin> potionsoins) {
        this.potionsoins = potionsoins;
    }

    /**
     * get name
     *
     * @return the name of the person
     */
    public String getNom() {
        return nom;
    }

    /**
     * Set name of a person
     *
     * @param nom name of person
     */
    public void setNom(String nom) {
        this.nom = nom;
    }

    /**
     * get disAttMax
     *
     * @return the length of the person can attack
     */
    public int getDistAttMax() {
        return distAttMax;
    }

    /**
     * Set disAttMax
     *
     * @param distAttMax int, max attack distance
     */
    public void setDistAttMax(int distAttMax) {
        this.distAttMax = distAttMax;
    }

    /**
     * Print out the attribute of one person, name, disAttMax and so on.
     */
    public void affiche() {
        System.out.print("nom=" + nom + ",distAttMax=" + distAttMax + " ");
        super.affiche();
    }

}

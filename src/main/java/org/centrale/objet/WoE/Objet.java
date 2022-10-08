/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.centrale.objet.WoE;

/**
 * @author wuzilong
 */
public class Objet extends ElementDeJeu{

    /**
     * Durée d'utilisation
     */
    private int life = 100;

    private Point2D pos;
    /**
     * combien cela coûte-t-il d'acheter
     */
    private int valeur;
    private String name;

    private int type;

    // 0:showed on the map,1:not showed on the map
    private int state = 0;

    public Objet(int life, Point2D pos, int valeur, String name) {
        this.life = life;
        this.pos = new Point2D(pos);
        this.valeur = valeur;
        this.name = name;
    }

    public Objet(int life, int valeur) {
        this.life = life;
        this.valeur = valeur;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public int getType() {
        return type;
    }

    public Objet() {

    }

    public Point2D getPos() {
        return pos;
    }

    public void setPos(Point2D pos) {
        this.pos = pos;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getLife() {
        return life;
    }

    public void setLife(int life) {
        this.life = life;
    }

    public int getValeur() {
        return valeur;
    }

    public void setValeur(int valeur) {
        this.valeur = valeur;
    }

    public void affiche() {
        System.out.println("life : " + life + " postion:" + pos);
    }

    public Objet(Objet o){
        this.life = o.getLife();
        this.pos = new Point2D(o.getPos());
        this.valeur =o.getValeur();
        this.name = o.getName();
        this.state=o.getState();
        this.type = o.getType();
    }
}

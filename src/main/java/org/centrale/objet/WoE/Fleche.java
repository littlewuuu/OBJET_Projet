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
public class Fleche extends Objet {
    /**
     * Used to indicate on the OOCUPIED table that the position is occupied by Fleche
     */
    private final int type = 2;
    /**
     * Injuries caused by Fleche
     */
    private int dommage = 4;


    public Fleche(int dommage, int life, int valeur) {
        super(life, valeur);
        this.dommage = dommage;
        setPos(World.createPoints(type));
    }

    public Fleche(int dommage) {
        this.dommage = dommage;
        setPos(World.createPoints(type));
    }

    public Fleche(Fleche f) {
        super(f);
        dommage = f.getDommage();
    }

    public Fleche() {
        setPos(World.createPoints(type));
    }

    //在最开始初始化 Archer 的时候有 10 只 Fleche，防止其在地图上占位置
    public Fleche(Boolean i) {
    }

    /**
     * used for charge the world.
     *
     * @param type
     * @param dommage
     * @param life
     * @param state
     * @param x
     * @param y
     */
    public Fleche(int type, int dommage, int life, int state, int x, int y) {
        setType(type);
        setDommage(dommage);
        setLife(life);
        setState(state);
        setPos(new Point2D(x, y));
    }


    public int getDommage() {
        return dommage;
    }

    public void setDommage(int dommage) {
        this.dommage = dommage;
    }

    public int getType() {
        return type;
    }

    public void affiche() {
        System.out.printf("Fleche : ");
        super.affiche();
    }


    //********为了实现箭的移动，没写完
    //   @Override
//    public void run() {
//        while (true) {
//            try {
//                Thread.sleep(100);
//            } catch (InterruptedException e) {
//                throw new RuntimeException(e);
//            }
//            if (!isLive || this.getPos().getX() <= 0
//                    || this.getPos().getX() >= World.TAILLE
//                    || this.getPos().getY() <= 0
//                    || this.getPos().getY() >= World.TAILLE) {
//                break;
//            }
//
//            switch (direction) {
//                case 1:
//                    setPos(new Point2D(getPos().getX(), getPos().getY() - speed));
//                    break;
//                case 2:
//                    setPos(new Point2D(getPos().getX() + speed, getPos().getY()));
//                    break;
//                case 3:
//                    setPos(new Point2D(getPos().getX(), getPos().getY() + speed));
//                    break;
//                case 4:
//                    setPos(new Point2D(getPos().getX() - speed, getPos().getY()));
//                    break;
//            }
//
//        }
//    }

    @Override
    public String toString() {
        return "Fleche " + type + " " + dommage + " " + getLife() + " " + getState() + " " + getPos().getX() + " " + getPos().getY() + '\n';
    }
}

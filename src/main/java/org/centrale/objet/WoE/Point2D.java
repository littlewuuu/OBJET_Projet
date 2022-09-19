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
public class Point2D {

    private int x;
    private int y;

    public Point2D(int x, int y) {
        setX(x);
        setY(y);
    }

    public Point2D(Point2D p) {
        setX(p.x);
        setY(p.y);
    }

    public Point2D() {
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void affiche() {
        System.out.println("coordX: " + x + "; coordY: " + y);
    }

    public void translate(int dx, int dy) {
        this.x += dx;
        this.y += dy;
    }

    public void setPosition(int x, int y) {
        this.x = x;
        this.y = y;
    }
    
    public float distance(Point2D p){
        return (float)Math.sqrt((this.x - p.x)*(this.x - p.x) + (this.y - p.y)*(this.y - p.y));
    }

    @Override
    public String toString() {
        return "Point2D{" + "x=" + x + ", y=" + y + '}';
    }
    
    public static double distance(int x1, int y1, int x2, int y2){
        return (double)Math.sqrt((x1 - x2)*(x1 - x2) + (y1 - y2)*(y1 - y2));
    }
 
}


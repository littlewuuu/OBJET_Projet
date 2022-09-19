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
public class TestPoint2D {
    public static void main(String[] args){
        Point2D p1 = new Point2D(1,2);
        Point2D p2 = new Point2D(p1);
        Point2D p3 = new Point2D();
        System.out.println("=========== test de affiche() et setX(),setY() ============");
        System.out.println("coords de p1: ");
        p1.affiche();
        p1.setX(3);
        System.out.println("coords de p1 apres setX(3) ");
        p1.affiche();
        p1.setY(4);
        System.out.println("coords de p1 apres setY(4) ");
        p1.affiche();
        System.out.println("============ test de getX() et getY() ===========");
        System.out.println("coordX de p1: " + p1.getX());
        System.out.println("coordY de p1: " + p1.getY());
        
        System.out.println("============ test de translate() ===========");
        System.out.println("coords de p2 avant la translation: " );
        p2.affiche();
        p2.translate(2, 3);
        System.out.println("translation:[2,3] " );
        System.out.println("coords de p2 apres la translation: " );
        p2.affiche();
        
        System.out.println("============ test de setPosition() ===========");
        System.out.println("coords de p2 avant setPosition()" );
        p2.affiche();
        System.out.println("coords de p2 apres setPosition(-1,-1) " );
        p2.setPosition(-1, -1);
        p2.affiche();
        
        
        System.out.println("============ test de distance() ===========");
        System.out.println("coords de p1: ");
        p1.affiche();
        System.out.println("coords de p3: ");
        p3.affiche();
        System.out.println("distance entre p1 et p3 " + p1.distance(p3) );
        
    }
    
}
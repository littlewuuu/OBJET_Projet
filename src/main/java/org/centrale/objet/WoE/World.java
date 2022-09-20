/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.centrale.objet.WoE;

import java.util.Random;
import java.util.ArrayList;

/**
 *
 * @author wuzilong et zoukang
 */
public class World {

    public Archer robin;
    public Paysan peon;
    public Lapin bugs1;
    public Lapin bugs2; //new 
    public Archer guillaumeT;
    public Guerrier grosBill;//new
    public Loup wolfie;//new

    public void tourDeJeu() {

    }

    public void aficheWorld() {

    }

    public World() {
    }

    public void creerMondeAlea() {

        bugs1 = new Lapin();
        bugs2 = new Lapin();

        robin = new Archer("robin");
        peon = new Paysan("peon");

        grosBill = new Guerrier("grosBill");

        wolfie = new Loup();

        Random generateurAleatoire = new Random();
        Point2D points[] = new Point2D[6];

        int i = 1;
        int X = generateurAleatoire.nextInt(100) - 50;
        int Y = generateurAleatoire.nextInt(100) - 50;
        points[0] = new Point2D();
        points[0].setPosition(X, Y);

        while (i < 6) {
            X = generateurAleatoire.nextInt(100) - 50;
            Y = generateurAleatoire.nextInt(100) - 50;
            for (int j = 0; j < i; j++) {
                if (X == points[j].getX() && Y == points[j].getX()) {
                    break;
                }
                if (j == i - 1) {
                    points[i++] = new Point2D(X, Y);
                    break;
                }
            }
        }

        robin.setPos(points[0]);
        peon.setPos(points[1]);
        bugs1.setPos(points[2]);
        bugs2.setPos(points[3]);
        grosBill.setPos(points[4]);
        wolfie.setPos(points[5]);

        guillaumeT = new Archer(robin);
        guillaumeT.setNom("guillaumeT");

        robin.deplace();

        //测试药水
        System.out.println("================test potion================");
        PotionSoin potion1 = new PotionSoin();
        PotionSoin potion2 = new PotionSoin();
        robin.getPotionsoin().add(potion1);
        robin.getPotionsoin().add(potion2);
        System.out.println("nombre de potion de robin: " + robin.getPotionsoin().size());
        System.out.println("consumer 1 potion");
        robin.usagePotion(potion2);
        System.out.println("nombre de potion de robin: " + robin.getPotionsoin().size());
        System.out.println("consumer 1 potion");
        robin.usagePotion(potion1);
        System.out.println("nombre de potion de robin: " + robin.getPotionsoin().size());
        robin.usagePotion(potion1);
        System.out.println("================fin de test potion================");
        
    }

}

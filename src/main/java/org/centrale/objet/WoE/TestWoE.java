/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.centrale.objet.WoE;

import java.util.Random;

/**
 *
 * @author wuzilong et zoukang
 */
public class TestWoE {

    public static void main(String[] args) {

        World myworld = new World();
        myworld.creerMondeAlea();

        myworld.robin.affiche();
        myworld.peon.affiche();
        myworld.bugs1.affiche();
        myworld.bugs2.affiche();
        myworld.grosBill.affiche();
        myworld.wolfie.affiche();

        myworld.guillaumeT.affiche();
        myworld.robin.affiche();

        //test combat contact
        System.out.println("================test combat contact================");
        myworld.bugs1.setPos(new Point2D(myworld.grosBill.getPos().getX() + 1, myworld.grosBill.getPos().getY()));
        System.out.println("----------------avant----------------");
        myworld.bugs1.affiche();
        myworld.grosBill.affiche();
        myworld.grosBill.combattre(myworld.bugs1);
        System.out.println("----------------apres----------------");
        myworld.bugs1.affiche();
        System.out.println("================fin de combat contact================");

        //test combat a distance
        System.out.println("================test combat a distance================");
        myworld.bugs2.setPos(new Point2D(myworld.robin.getPos().getX() + 5, myworld.robin.getPos().getY() + 5));
        System.out.println("----------------avant----------------");
        myworld.bugs2.affiche();
        myworld.robin.affiche();
        myworld.robin.combattre(myworld.bugs2);
        System.out.println("----------------apres----------------");
        myworld.bugs2.affiche();
        myworld.robin.affiche();
        System.out.println("================fin test combat a distance================");

    }
}

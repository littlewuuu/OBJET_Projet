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
    public static void main(String[] args){
        
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

    }
}

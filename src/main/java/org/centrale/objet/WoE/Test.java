/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.centrale.objet.WoE;

import java.util.Random;

/**
 *
 * @author wuzilong
 * @author Zou Kang
 */
public class Test {
    public static void main(String[] args){
        Random generateRandom = new Random();
        int x, y;
        x = y = 0;
        do {
            x = generateRandom.nextInt(2);
            y = generateRandom.nextInt(2);
            if (x != 0 || y != 0) {
                break;
            }
        } while (true);

        System.out.println("x="+x+"y="+y);
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.centrale.objet.WoE;

import javax.swing.*;
import java.util.Iterator;
import java.util.Scanner;

/**
 * Test if the World Me function is working properly.
 * @author wuzilong
 * @author Zou Kang
 */
public class TestWoE extends JFrame {
    static MyPanel mp = null;

    public TestWoE() {
        initWord();
    }

    public static void main(String[] args) {
        TestWoE testWoE = new TestWoE();
    }

    /**
     * Called when the game is over.
     */
    public static void endOfGame() {
        System.out.println("Game over :(");
        World.setGameCount(World.getGameCount() + 1); //Rounds plus one
        //Add one to the number of rounds in the nourriture used
        for (Nourriture nou : Personnage.getNourritureInUse()) {
            nou.setCount(nou.getCount() + 1);
        }

        //If the Nourriture effect in NourritureInUse is over, restore the properties of the jouer
        Iterator<Nourriture> iterator = Personnage.getNourritureInUse().iterator();
        while (iterator.hasNext()) {
            Epinard epinard = (Epinard) iterator.next();
            if (epinard.getCount() == 3) {
                Personnage.cancelEpinard(epinard);
                iterator.remove();
                System.out.println("degAtt:" + MyPanel.joueur.perso.getDegAtt());
            }
        }


        World.setOCCUPIED(MyPanel.joueur.perso.getPos().getX(), MyPanel.joueur.perso.getPos().getX(), 0);//地图上抹掉玩家的位置信息
        Scanner scanner = new Scanner(System.in);
        System.out.println("open a new game or continue? (enter 1 restart)");
        int i = scanner.nextInt();
        if (i == 1) { //restart
            reStart();
        }

    }

    /**
     * Restart the game.
     */
    public static void reStart() {
        MyPanel.resetJoueurVie();
        World.setGAMESTATUESTATUS(1);
        MyPanel.joueur.perso.setPos(new Point2D(World.createPoints(1)));//Reset the player's position
        new Thread(mp).start();
    }

    /**
     * Initialize the world.
     */
    public void initWord() {
        JFrame frame = new JFrame();
        mp = new MyPanel();

        frame.setContentPane(mp);

        Thread thread = new Thread(mp);
        thread.start();
        add(mp);
        setSize(World.TAILLE * 8 + 250, World.TAILLE * 8 + 30);
        addKeyListener(mp);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }
}

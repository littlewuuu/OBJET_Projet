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
 * @author wuzilong
 * @author Zou Kang
 */
public class TestWoE extends JFrame {
    static MyPanel mp = null;

    public static void main(String[] args) {
        TestWoE testWoE = new TestWoE();
    }


    /**
     * 结束游戏出现的界面
     */
    public static void endOfGame(){
        System.out.println("Game over :(");
        World.setGameCount(World.getGameCount() + 1); //游戏轮数加一
        //在使用的 nourriture 的轮数加一
        for (Nourriture nou: Personnage.getNourritureInUse()) {
            nou.setCount(nou.getCount()+1);
        }

        //如果 NourritureInUse 里面的 Epinard 效果完了，就恢复 jouer 的属性
        Iterator<Nourriture> iterator = Personnage.getNourritureInUse().iterator();
        while (iterator.hasNext()){
            Epinard epinard = (Epinard) iterator.next();
            if(epinard.getCount() == 3){
                Personnage.cancelEpinard(epinard);
                iterator.remove();
                System.out.println("degAtt:" + MyPanel.joueur.perso.getDegAtt());
            }
        }



        World.setOCCUPIED(MyPanel.joueur.perso.getPos().getX(),MyPanel.joueur.perso.getPos().getX(),0);//地图上抹掉玩家的位置信息
        Scanner scanner= new Scanner(System.in);
        System.out.println("open a new game? (enter 1 restart)");
        int i = scanner.nextInt();
        if(i == 1){ //restart
          reStart();
        }



    }

    public static void reStart(){
        MyPanel.resetJoueurVie();
        World.setGAMESTATUESTATUS(1);
        MyPanel.joueur.perso.setPos(new Point2D(World.createPoints(1)));//重新设置玩家的位置
        Thread thread = new Thread(mp);
    }
    public TestWoE(){
        initWord();
    }

    public void initWord(){
        JFrame frame = new JFrame();
        mp = new MyPanel();

        frame.setContentPane(mp);

        Thread thread = new Thread(mp);
        thread.start();
        add(mp);//把面板(就是游戏的绘图区域)
        setSize(World.TAILLE*8 + 250, World.TAILLE*8+30);
        addKeyListener(mp);//让JFrame 监听mp的键盘事件
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }
}


/*

        myworld.robin.affiche();
        myworld.peon.affiche();
        myworld.bugs1.affiche();
        myworld.bugs2.affiche();
        myworld.grosBill.affiche();
        myworld.wolfie.affiche();

        myworld.guillaumeT.affiche();
        myworld.robin.affiche();

        System.out.println("================test deplace================");
        System.out.println("----------------avant deplace:----------------");
        myworld.robin.affiche();
        myworld.peon.affiche();
        myworld.bugs1.affiche();
        System.out.println("----------------apres deplace:----------------");
        myworld.robin.deplace();
        myworld.peon.deplace();
        myworld.bugs1.deplace();
        myworld.robin.affiche();
        myworld.peon.affiche();
        myworld.bugs1.affiche();
        System.out.println("================fin test deplace================");

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
        */

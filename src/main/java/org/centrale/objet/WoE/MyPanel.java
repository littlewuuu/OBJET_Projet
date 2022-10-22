/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.centrale.objet.WoE;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;
import java.util.Vector;
import java.util.concurrent.atomic.AtomicReference;

/**
 * @author wuzilong
 */
public class MyPanel extends JPanel implements KeyListener, Runnable {

    static Joueur joueur;
    World world;

    /**
     * 显示的地图的放大倍数
     */
    int zoom = 8;


    MyPanel() {
        joueur = new Joueur();
        joueur.affiche();
        world = new World();
        world.creerMondeAlea();

        world.afficheWorld();
    }



    public static void resetJoueurVie() {
        joueur.perso.setPtVie(100);
    }

    /**
     * 显示图例信息
     * @param g
     */
    public void showInfo(Graphics g){


        //显示操作信息背景
        g.setColor(Color.pink);
        g.fillRect(World.TAILLE*zoom + 10,240,200,50);
        //显示操作信息
        g.setColor(Color.black);
        g.drawString("Press \" P \" to pick up an objet",World.TAILLE*zoom + 10,250);
        g.drawString("Press \" C \" to fight",World.TAILLE*zoom + 10,270);
        g.drawString("Press \" G \" to use potionsoin",World.TAILLE*zoom + 10,290);

        //显示地图物品的对应关系
        g.setColor(new Color(0,228,114));
        g.fillOval(World.TAILLE*zoom + 10,310,zoom,zoom+3);
        g.drawString("-1:NuageToxique",World.TAILLE*zoom + 20,320);

        g.setColor(Color.CYAN);
        g.fillOval(World.TAILLE*zoom + 10,330,zoom,zoom+3);
        g.drawString("1:Joueur",World.TAILLE*zoom + 20,340);

        g.setColor(new Color(250,128,114));
        g.fillRect(World.TAILLE*zoom + 10,350,zoom,zoom);
        g.drawString("2:Fleche",World.TAILLE*zoom + 20,360);

        g.setColor(new Color(205 ,201 ,201));
        g.fillRect(World.TAILLE*zoom + 10,370,zoom,zoom);
        g.drawString("3:Epee",World.TAILLE*zoom + 20,380);

        g.setColor(new Color(0 ,139 ,139));
        g.fillRect(World.TAILLE*zoom + 10,390,zoom,zoom);
        g.drawString("4:PotionSoin",World.TAILLE*zoom + 20,400);

        g.setColor(new Color(155 ,123 ,200));
        g.fillOval(World.TAILLE*zoom + 10,410,zoom,zoom);
        g.drawString("5:Archer",World.TAILLE*zoom + 20,420);

        g.setColor(new Color(155 ,223 ,200));
        g.fillOval(World.TAILLE*zoom + 10,430,zoom,zoom);
        g.drawString("6:Guerrier",World.TAILLE*zoom + 20,440);

        g.setColor( new Color(155 ,223 ,00));
        g.fillOval(World.TAILLE*zoom + 10,450,zoom,zoom);
        g.drawString("7:Paysan",World.TAILLE*zoom + 20,460);

        g.setColor(new Color(255, 0, 255) );
        g.fillOval(World.TAILLE*zoom + 10,470,zoom,zoom);
        g.drawString("8:Lapin",World.TAILLE*zoom + 20,480);

        g.setColor( new Color(155, 50, 55));
        g.fillOval(World.TAILLE*zoom + 10,490,zoom,zoom);
        g.drawString("9:Loup",World.TAILLE*zoom + 20,500);




        //显示玩家信息背景
        g.setColor(Color.pink);
        g.fillRect(World.TAILLE*zoom + 10,550,250,80);
        //显示玩家信息
        g.setColor(Color.black);
        g.drawString("Informations of Joueur" ,World.TAILLE*zoom + 10,560);

        g.drawString("PtVie: ",World.TAILLE*zoom + 10,580);
        g.drawString(joueur.perso.getPtVie()+"",World.TAILLE*zoom +100,580);

        g.drawString("Pos: ",World.TAILLE*zoom + 10,600);
        g.drawString(joueur.perso.getPos()+"",World.TAILLE*zoom + 100,600);

        g.drawString("direction: ",World.TAILLE*zoom + 10,620);
        int direction = joueur.perso.getDirection();
        switch (direction){
            case 1:
                g.drawString("up",World.TAILLE*zoom + 100,620);
                break;
            case 2:
                g.drawString("right",World.TAILLE*zoom + 100,620);
                break;
            case 3:
                g.drawString("down",World.TAILLE*zoom + 100,620);
                break;
            case 4:
                g.drawString("left",World.TAILLE*zoom + 100,620);
                break;
        }

        g.drawString("DegAtt: ",World.TAILLE*zoom + 10,640);
        g.drawString(joueur.perso.getDegAtt()+"",World.TAILLE*zoom + 100,640);

    }

    /**
     * Joueur 拥有的物品信息
     * @param g
     */
    public void showInfoObjet(Graphics g){
        g.drawString("What you have",World.TAILLE*zoom + 10,10);

        g.setColor(new Color(250,128,114));
        g.fillRect(World.TAILLE*zoom + 10,20, zoom,zoom);
        g.drawString("Fleche : ",World.TAILLE*zoom + 30,30);
        String numFleche = joueur.nbFleche+"";
        g.drawString(numFleche,World.TAILLE*zoom + 120,30);

        g.setColor(new Color(205 ,201 ,201));
        g.fillRect(World.TAILLE*zoom + 10,40,zoom,zoom);
        g.drawString("Epee ",World.TAILLE*zoom + 30,50);
        String numEpee = joueur.nbEpee+"";
        g.drawString(numEpee,World.TAILLE*zoom + 120,50);

        g.setColor(new Color(0 ,139 ,139));
        g.fillRect(World.TAILLE*zoom +10,60 ,zoom,zoom);
        g.drawString("PotionSoin ",World.TAILLE*zoom + 30,70);
        String numPotionSoin = joueur.nbPotionSoin+"";
        g.drawString(numPotionSoin,World.TAILLE*zoom + 120,70);

        g.setColor(new Color(255 ,19 ,19));
        g.fillRect(World.TAILLE*zoom +10,80 ,zoom,zoom);
        g.drawString("Epinard ",World.TAILLE*zoom + 30,90);
        String numEpinard = joueur.nbEpinard+"";
        g.drawString(numEpinard,World.TAILLE*zoom + 120,90);

    }

    /**
     * 画出图形
     * @param g  the <code>Graphics</code> context in which to paint
     */
    public void paint(Graphics g) {
        super.paint(g);

        g.fillRect(0, 0, World.TAILLE*zoom - 1 , World.TAILLE*zoom - 1);//background
        showInfoObjet(g);
        showInfo(g);
        drawJoueur(g);
        drawWorldCreature(g);
        drawWorldObjet(g);
        drawNuageToxique(g);
        //drawFleche(g);


    }



    /**
     * Draw all the objet in th world.
     * @param g
     */
    public void drawWorldObjet(Graphics g){
        Iterator<Objet> iterator = world.objets.iterator();
        while (iterator.hasNext()){
            Objet objet = iterator.next();
            if(objet.getState() == 1){continue;}
            int type = objet.getType();
            switch (type){
                case 2:
                    g.setColor(new Color(250,128,114));
                    g.fillRect(objet.getPos().getX()*zoom-zoom/2,objet.getPos().getY()*zoom-zoom/2,zoom,zoom);
                    break;
                case 3:
                    g.setColor(new Color(205 ,201 ,201));
                    g.fillRect(objet.getPos().getX()*zoom-zoom/2,objet.getPos().getY()*zoom-zoom/2,zoom,zoom);
                    break;
                case 4:
                    g.setColor(new Color(0 ,139 ,139));
                    g.fillRect(objet.getPos().getX()*zoom-zoom/2,objet.getPos().getY()*zoom-zoom/2,zoom,zoom);
                    break;
                case 10:
                    g.setColor(new Color(255 ,19 ,19));
                    g.fillRect(objet.getPos().getX()*zoom-zoom/2,objet.getPos().getY()*zoom-zoom/2,zoom,zoom);
                    break;
            }
        }
    }

    public void drawNuageToxique(Graphics g){
        Iterator<NuageToxique> nuageToxiqueIterator = World.nuageToxiques.iterator();
        while (nuageToxiqueIterator.hasNext()){
            Objet nuage = nuageToxiqueIterator.next();
            g.setColor(new Color(0,228,114));
            g.fillOval(nuage.getPos().getX()*zoom-zoom/2,nuage.getPos().getY()*zoom-zoom/2,zoom,zoom+3);
        }
    }


    /**
     * Draw Joueur
     * @param g
     */
    public void drawJoueur(Graphics g){
        if(joueur.perso.getPtVie() <= 0){ //玩家生命值为 0 就不画
            return;
        }
        g.setColor(Color.CYAN);
        g.fillOval(joueur.perso.getPos().getX()*zoom-zoom/2,joueur.perso.getPos().getY()*zoom-zoom/2,zoom,zoom);
    }

    /**
     * Draw all the Creture in the world.
     * @param g
     */
    public void drawWorldCreature(Graphics g){
        Iterator<Creature> iterator = world.creatures.iterator();
        while (iterator.hasNext()){
            Creature c = iterator.next();
            if(c.getPtVie() <=0){continue;} //单纯不在地图上显示，并没有在地图上把该 Creature 的位置置为 0
            int type = c.getType();
            switch (type){
                case 5:
                    g.setColor(new Color(155 ,123 ,200));
                    g.fillOval(c.getPos().getX()*zoom-zoom/2,c.getPos().getY()*zoom-zoom/2,zoom,zoom);
                    break;
                case 6:
                    g.setColor(new Color(155 ,223 ,200));
                    g.fillOval(c.getPos().getX()*zoom-zoom/2,c.getPos().getY()*zoom-zoom/2,zoom,zoom);
                    break;
                case 7:
                    g.setColor(new Color(155 ,223 ,00));
                    g.fillOval(c.getPos().getX()*zoom-zoom/2,c.getPos().getY()*zoom-zoom/2,zoom,zoom);
                    break;
                case 8:
                    g.setColor(new Color(255, 0, 255));
                    g.fillOval(c.getPos().getX()*zoom-zoom/2,c.getPos().getY()*zoom-zoom/2,zoom,zoom);
                    break;
                case 9:
                    g.setColor(new Color(155, 50, 55));
                    g.fillOval(c.getPos().getX()*zoom-zoom/2,c.getPos().getY()*zoom-zoom/2,zoom,zoom);
                    break;
            }
        }
    }




    public void run() {
        while (true) {

            //玩家死亡后地图不刷新
            if(World.GAMESTATUESTATUS == 0){
                continue;
            }
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            //判断玩家是否在 nuageToxiques 周围，在周围就会收到攻击
            try {
                nuageAttack(World.getNuageToxiques());
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

            try {
                loupAttack(World.getCreatures());
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

            this.repaint();

            //玩家生命值 <= 0 就结束游戏
            if(joueur.perso.getPtVie() <= 0){
                World.setGAMESTATUESTATUS(0);
                save();
                TestWoE.endOfGame();//结束游戏
            }

        }
    }

    public void save(){
        String fileName = "";
        Scanner scanner = new Scanner(System.in);
        System.out.println("please enter filename: (or by default press \"n\")");
        String s = scanner.next();
        if(s.equals("n"))
        {
            fileName = "sauvegarde" + World.gameCount;
        }else {
            fileName= s;
        }
        sauvegardePartie(fileName);
    }


    @Override
    public void keyTyped(KeyEvent e) {

    }


    /**
     * Detect whether a button is pressed
     * @param e the event to be processed
     */
    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_DOWN) {
            joueur.moveDown();
        } else if (e.getKeyCode() == KeyEvent.VK_UP) {
           joueur.moveUp();
        } else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            joueur.moveRight();
        } else if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            joueur.moveLeft();
        } else {
        }
        if(e.getKeyCode() == KeyEvent.VK_P){ //pick an objet
            joueur.pickObjet();
        }
        if(e.getKeyCode() == KeyEvent.VK_C){ //combattre
            joueur.combattre();
        }
        if(e.getKeyCode() == KeyEvent.VK_G){ //use PotionSoin
            joueur.guerir();
        }
        if(e.getKeyCode() == KeyEvent.VK_K)//to test use PotionSoin
        {
            joueur.perso.setPtVie(joueur.perso.getPtVie() - 10);
            joueur.perso.affiche();
        }
        if(e.getKeyCode() == KeyEvent.VK_L){
            joueur.perso.useEpinard();
        }
//        if(e.getKeyCode() == KeyEvent.VK_S)
//        {
//            sauvegardePartie("tset.txt");
//        }
        this.repaint();
    }


    /**
     * 判断玩家是否在 huageToxique 的攻击范围内，如果是，则攻击
     * @param nuageToxiques
     */
    public void nuageAttack(Vector<NuageToxique> nuageToxiques) throws InterruptedException {
        Thread.sleep(1000);
        Iterator<NuageToxique> nuageToxiqueIterator = nuageToxiques.iterator();
        while (nuageToxiqueIterator.hasNext()){
            NuageToxique nuageToxique1 = nuageToxiqueIterator.next();

            if(nuageToxique1.getLife() <=0) { //若生命为0，则把该 nuageToxique 移除
                nuageToxiques.remove(nuageToxique1);
                continue;
            }
            if(Point2D.distance(nuageToxique1.getPos().getX(),nuageToxique1.getPos().getY(),joueur.perso.getPos().getX(), joueur.perso.getPos().getY()) <= nuageToxique1.getAttackRange())//玩家在攻击范围内
            {
                joueur.perso.setPtVie(joueur.perso.getPtVie() - nuageToxique1.getDamage());
            }
        }
    }

    /**
     * 世界里的狼会随机攻击人类
     */
    public void loupAttack(ArrayList<Creature> creatures) throws InterruptedException {
        Thread.sleep(1000);
        Iterator<Creature> iterator = creatures.iterator();
        while (iterator.hasNext()){
            Creature creature = iterator.next();
            if(World.getOCCUPIED(creature.getPos().getX(),creature.getPos().getY()) == 9 && creature.getPtVie() > 0) // is loup and is alive
            {
                Loup loup = (Loup) creature; //转成 Loup，因为 disAttMax 在 Loup 类里面
                if(Point2D.distance(loup.getPos().getX(),loup.getPos().getY(),joueur.perso.getPos().getX(), joueur.perso.getPos().getY()) <= (loup.getDistAttMax()))//玩家在Loup攻击范围内
                {
                    loup.combattre(joueur.perso);
                }
            }
        }
    }

    /**
     * 保存世界的信息到文件里
     * 因为我们的 joueur world 等都在 mypanel 里面，所以这个也放在 mypanel 里面
     * 实现的功能：
     *      保存信息，注意格式
     *      能够询问使用默认名或者自定义文件名
     *      每轮游戏提醒玩家保存游戏
     * @param fileName 保存文件的名字
     */
    public void sauvegardePartie(String fileName){
        try {
            //创建 FileWriter
            FileWriter file = new FileWriter(fileName);

            //创建 BufferedWriter
            BufferedWriter output = new BufferedWriter(file);


            //存放世界的 OCCUPIED[][]
            output.write("OCCUPIED");
            output.newLine();
            for (int i = 0; i < World.TAILLE; i++) {
                for (int j = 0; j < World.TAILLE; j++) {
                    int a = World.getOCCUPIED(i,j);
                    output.write(a + " ");
                  //  System.out.print(World.getOCCUPIED(i,j));
                }
                output.newLine();
                System.out.println();
            }

            //存放玩家类型
            output.write("joueur ");
            String s = joueur.perso.getClass().toString();
            output.write(s.substring(s.length()-5)+" ");

            //存放玩家的物品信息 fleche epee epinard potionsoin
            int nb1 = joueur.getNbEpinard();
            int nb2 =joueur.getNbPotionSoin();
            int nb3=joueur.getNbEpee();
            int nb4 = joueur.getNbFleche();
            output.write(nb1+" ");
            output.write(nb2+" ");
            output.write(nb3+" ");
            output.write(nb4+" ");
            //存放玩家的生命值等信息
            output.write(joueur.perso.getNom() + " " + joueur.perso.getDistAttMax() + " " + joueur.perso.getPtVie() + " " + joueur.perso.getDegAtt()+" " + joueur.perso.getPtPar() + " " + joueur.perso.getPageAtt()+" " + joueur.perso.getPagePar() + " " + joueur.perso.getDirection() + " " + joueur.perso.getPos().getX()+" " + joueur.perso.getPos().getY());
            output.newLine();

            //存放世界中的物品信息
            Iterator<Objet> iterator = World.getObjets().iterator();
            while (iterator.hasNext()){
                Objet o  = iterator.next();
                String ss = o.toString();
                output.write(ss);
            }

            //存放 nuageToxique
            Iterator<NuageToxique> iterator1 = World.getNuageToxiques().iterator();
            while (iterator1.hasNext()){
                NuageToxique n = iterator1.next();
                String ss = n.toString();
                output.write(ss);
            }

            //存放世界中的 creature 信息
            Iterator<Creature> iterator2 = World.getCreatures().iterator();
            while (iterator2.hasNext()){
                Creature c = iterator2.next();
                String ss = c.toString();
                output.write(ss);
            }

            output.flush();
            output.close();


        } catch (IOException e) {
            throw new RuntimeException(e);
        }finally {

        }

    }



    @Override
    public void keyReleased(KeyEvent e) {
    }
}

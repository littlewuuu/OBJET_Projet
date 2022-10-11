/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.centrale.objet.WoE;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.Vector;

/**
 * @author wuzilong
 */
public class MyPanel extends JPanel implements KeyListener, Runnable {

    Joueur joueur;
    World world;

    Boolean buttonPressed = false;
    int zoom = 8;
    JButton button = new JButton();


    MyPanel() {
        joueur = new Joueur();

        joueur.affiche();
        world = new World();
        world.creerMondeAlea();

        world.afficheWorld();
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
        drawFleche(g);
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
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            //判断玩家是否在 nuageToxiques 周围，在周围就会收到攻击
            try {
                nuageAttack(World.nuageToxiques);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            this.repaint();
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    //**************显示 Fleche 的移动轨迹，没写完
    public void drawFleche(Graphics g){
        if(joueur.perso.getClass().toString().equals("class org.centrale.objet.WoE.Archer")){
            Archer a = (Archer) joueur.perso;
            Vector<Fleche> fleches = a.getFleches();

                Fleche fleche = fleches.lastElement();

                    g.setColor(new Color(255 ,255 ,255));
                    g.fillOval(fleche.getPos().getX()*zoom-zoom/2,fleche.getPos().getY()*zoom-zoom/2,zoom,zoom);

        }
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

    @Override
    public void keyReleased(KeyEvent e) {

    }
}

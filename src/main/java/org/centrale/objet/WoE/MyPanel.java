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
import java.io.*;
import java.util.*;

/**
 * The interface used to display the game, which is convenient for the user to play.
 * This class inherits from JPanel.
 * Since the creatures in the world are to be displayed on the panel, we instantiate the player and the lineage here.
 * @author wuzilong
 */
public class MyPanel extends JPanel implements KeyListener, Runnable {

    /**
     * Players in the world.
     */
    static Joueur joueur;
    /**
     * The whole world.
     */
    World world;

    /**
     * The magnification of the displayed map.
     * For better display, we will enlarge the actual size of the map according to this multiple.
     */
    int zoom = 8;


    MyPanel() {

        //At the beginning of the game we give the player the option to start a new game or load from a file.
        while (true) {
            System.out.println("new game(1), old game(2)");
            Scanner scanner = new Scanner(System.in);
            String choose = scanner.next();
            if ("1".equals(choose)) {
                joueur = new Joueur();
                joueur.affiche();
                world = new World();
                world.creerMondeAlea();

                world.afficheWorld();
                break;
            } else if ("2".equals(choose)) {
                System.out.println("Please enter the name of file");
                String fileName = scanner.next();
                chargementPartie(fileName);
                break;
            } else {
                System.out.println("wrong input, try again");
            }
        }

    }


    /**
     * when player chose to restart a world, set ptVie of player = 100
     */
    public static void resetJoueurVie() {
        joueur.perso.setPtVie(100);
    }

    /**
     * Show legend information on the game panel.
     * @param g
     */
    public void showInfo(Graphics g) {


        //Background of Operational Information
        g.setColor(Color.pink);
        g.fillRect(World.TAILLE * zoom + 10, 220, 220, 70);
        //Operational information
        g.setColor(Color.black);
        g.drawString("Press \" L \" to use Epinard", World.TAILLE * zoom + 10, 230);
        g.drawString("Press \" P \" to pick up an objet", World.TAILLE * zoom + 10, 250);
        g.drawString("Press \" C \" to fight", World.TAILLE * zoom + 10, 270);
        g.drawString("Press \" G \" to use potionsoin", World.TAILLE * zoom + 10, 290);

        g.setColor(new Color(0, 228, 114));
        g.fillOval(World.TAILLE * zoom + 10, 310, zoom, zoom + 3);
        g.drawString("-1:NuageToxique", World.TAILLE * zoom + 20, 320);

        g.setColor(Color.CYAN);
        g.fillOval(World.TAILLE * zoom + 10, 330, zoom, zoom );
        g.drawString("1:Joueur", World.TAILLE * zoom + 20, 340);

        g.setColor(new Color(250, 128, 114));
        g.fillRect(World.TAILLE * zoom + 10, 350, zoom, zoom);
        g.drawString("2:Fleche", World.TAILLE * zoom + 20, 360);

        g.setColor(new Color(205, 201, 201));
        g.fillRect(World.TAILLE * zoom + 10, 370, zoom, zoom);
        g.drawString("3:Epee", World.TAILLE * zoom + 20, 380);

        g.setColor(new Color(0, 139, 139));
        g.fillRect(World.TAILLE * zoom + 10, 390, zoom, zoom);
        g.drawString("4:PotionSoin", World.TAILLE * zoom + 20, 400);

        g.setColor(new Color(155, 123, 200));
        g.fillOval(World.TAILLE * zoom + 10, 410, zoom, zoom);
        g.drawString("5:Archer", World.TAILLE * zoom + 20, 420);

        g.setColor(new Color(155, 223, 200));
        g.fillOval(World.TAILLE * zoom + 10, 430, zoom, zoom);
        g.drawString("6:Guerrier", World.TAILLE * zoom + 20, 440);

        g.setColor(new Color(155, 223, 00));
        g.fillOval(World.TAILLE * zoom + 10, 450, zoom, zoom);
        g.drawString("7:Paysan", World.TAILLE * zoom + 20, 460);

        g.setColor(new Color(255, 0, 255));
        g.fillOval(World.TAILLE * zoom + 10, 470, zoom, zoom);
        g.drawString("8:Lapin", World.TAILLE * zoom + 20, 480);

        g.setColor(new Color(155, 50, 55));
        g.fillOval(World.TAILLE * zoom + 10, 490, zoom, zoom);
        g.drawString("9:Loup", World.TAILLE * zoom + 20, 500);

        g.setColor(new Color(255, 19, 19));
        g.fillOval(World.TAILLE * zoom + 10, 510, zoom, zoom);
        g.drawString("10:Epinard", World.TAILLE * zoom + 20, 520);

        g.setColor(Color.black);
        g.drawString("move up:    ↑", World.TAILLE * zoom + 10, 680);
        g.drawString("move down:  ↓", World.TAILLE * zoom + 10, 700);
        g.drawString("move right: →", World.TAILLE * zoom + 10, 720);
        g.drawString("move left:  ←", World.TAILLE * zoom + 10, 740);

        g.setColor(Color.pink);
        g.fillRect(World.TAILLE * zoom + 10, 550, 250, 100);
        //Show player info
        g.setColor(Color.black);
        g.drawString("Informations of Joueur", World.TAILLE * zoom + 10, 560);

        g.drawString("PtVie: ", World.TAILLE * zoom + 10, 580);
        g.drawString(joueur.perso.getPtVie() + "", World.TAILLE * zoom + 100, 580);

        g.drawString("Pos: ", World.TAILLE * zoom + 10, 600);
        g.drawString(joueur.perso.getPos() + "", World.TAILLE * zoom + 100, 600);

        g.drawString("direction: ", World.TAILLE * zoom + 10, 620);
        int direction = joueur.perso.getDirection();
        switch (direction) {
            case 1:
                g.drawString("up", World.TAILLE * zoom + 100, 620);
                break;
            case 2:
                g.drawString("right", World.TAILLE * zoom + 100, 620);
                break;
            case 3:
                g.drawString("down", World.TAILLE * zoom + 100, 620);
                break;
            case 4:
                g.drawString("left", World.TAILLE * zoom + 100, 620);
                break;
        }

        g.drawString("DegAtt: ", World.TAILLE * zoom + 10, 640);
        g.drawString(joueur.perso.getDegAtt() + "", World.TAILLE * zoom + 100, 640);

    }

    /**
     * Displays information about items owned by Joueur.
     * @param g
     */
    public void showInfoObjet(Graphics g) {
        g.drawString("What you have", World.TAILLE * zoom + 10, 10);

        g.setColor(new Color(250, 128, 114));
        g.fillRect(World.TAILLE * zoom + 10, 20, zoom, zoom);
        g.drawString("Fleche : ", World.TAILLE * zoom + 30, 30);
        String numFleche = Joueur.nbFleche + "";
        g.drawString(numFleche, World.TAILLE * zoom + 120, 30);

        g.setColor(new Color(205, 201, 201));
        g.fillRect(World.TAILLE * zoom + 10, 40, zoom, zoom);
        g.drawString("Epee ", World.TAILLE * zoom + 30, 50);
        String numEpee = Joueur.nbEpee + "";
        g.drawString(numEpee, World.TAILLE * zoom + 120, 50);

        g.setColor(new Color(0, 139, 139));
        g.fillRect(World.TAILLE * zoom + 10, 60, zoom, zoom);
        g.drawString("PotionSoin ", World.TAILLE * zoom + 30, 70);
        String numPotionSoin = Joueur.nbPotionSoin + "";
        g.drawString(numPotionSoin, World.TAILLE * zoom + 120, 70);

        g.setColor(new Color(255, 19, 19));
        g.fillRect(World.TAILLE * zoom + 10, 80, zoom, zoom);
        g.drawString("Epinard ", World.TAILLE * zoom + 30, 90);
        String numEpinard = Joueur.nbEpinard + "";
        g.drawString(numEpinard, World.TAILLE * zoom + 120, 90);

    }

    /**
     * @param g the <code>Graphics</code> context in which to paint
     */
    public void paint(Graphics g) {
        super.paint(g);

        g.fillRect(0, 0, World.TAILLE * zoom - 1, World.TAILLE * zoom - 1);//background
        showInfoObjet(g);
        showInfo(g);
        drawJoueur(g);
        drawWorldCreature(g);
        drawWorldObjet(g);
        drawNuageToxique(g);

    }


    /**
     * Draw all the objet in th world.
     * @param g
     */
    public void drawWorldObjet(Graphics g) {
        Iterator<Objet> iterator = World.objets.iterator();
        while (iterator.hasNext()) {
            Objet objet = iterator.next();
            if (objet.getState() == 1) {
                continue;
            }
            int type = objet.getType();
            switch (type) {
                case 2:
                    g.setColor(new Color(250, 128, 114));
                    g.fillRect(objet.getPos().getX() * zoom - zoom / 2, objet.getPos().getY() * zoom - zoom / 2, zoom, zoom);
                    break;
                case 3:
                    g.setColor(new Color(205, 201, 201));
                    g.fillRect(objet.getPos().getX() * zoom - zoom / 2, objet.getPos().getY() * zoom - zoom / 2, zoom, zoom);
                    break;
                case 4:
                    g.setColor(new Color(0, 139, 139));
                    g.fillRect(objet.getPos().getX() * zoom - zoom / 2, objet.getPos().getY() * zoom - zoom / 2, zoom, zoom);
                    break;
                case 10:
                    g.setColor(new Color(255, 19, 19));
                    g.fillRect(objet.getPos().getX() * zoom - zoom / 2, objet.getPos().getY() * zoom - zoom / 2, zoom, zoom);
                    break;
            }
        }
    }

    /**
     * Draw all the NuageToxique in the world.
     * @param g
     */
    public void drawNuageToxique(Graphics g) {
        Iterator<NuageToxique> nuageToxiqueIterator = World.nuageToxiques.iterator();
        while (nuageToxiqueIterator.hasNext()) {
            Objet nuage = nuageToxiqueIterator.next();
            g.setColor(new Color(0, 228, 114));
            g.fillOval(nuage.getPos().getX() * zoom - zoom / 2, nuage.getPos().getY() * zoom - zoom / 2, zoom, zoom + 3);
        }
    }


    /**
     * Draw Joueur
     * @param g
     */
    public void drawJoueur(Graphics g) {
        if (joueur.perso.getPtVie() <= 0) { //do not draw when ptVie of joueur <= 0
            return;
        }
        g.setColor(Color.CYAN);
        g.fillOval(joueur.perso.getPos().getX() * zoom - zoom / 2, joueur.perso.getPos().getY() * zoom - zoom / 2, zoom, zoom);
    }

    /**
     * Draw all the Creture in the world.
     *
     * @param g
     */
    public void drawWorldCreature(Graphics g) {
        Iterator<Creature> iterator = World.creatures.iterator();
        while (iterator.hasNext()) {
            Creature c = iterator.next();
            if (c.getPtVie() <= 0) {

                continue;
            } //单纯不在地图上显示，并没有在地图上把该 Creature 的位置置为 0
            int type = c.getType();
            switch (type) {
                case 5:
                    g.setColor(new Color(155, 123, 200));
                    g.fillOval(c.getPos().getX() * zoom - zoom / 2, c.getPos().getY() * zoom - zoom / 2, zoom, zoom);
                    break;
                case 6:
                    g.setColor(new Color(155, 223, 200));
                    g.fillOval(c.getPos().getX() * zoom - zoom / 2, c.getPos().getY() * zoom - zoom / 2, zoom, zoom);
                    break;
                case 7:
                    g.setColor(new Color(155, 223, 00));
                    g.fillOval(c.getPos().getX() * zoom - zoom / 2, c.getPos().getY() * zoom - zoom / 2, zoom, zoom);
                    break;
                case 8:
                    g.setColor(new Color(255, 0, 255));
                    g.fillOval(c.getPos().getX() * zoom - zoom / 2, c.getPos().getY() * zoom - zoom / 2, zoom, zoom);
                    break;
                case 9:
                    g.setColor(new Color(155, 50, 55));
                    g.fillOval(c.getPos().getX() * zoom - zoom / 2, c.getPos().getY() * zoom - zoom / 2, zoom, zoom);
                    break;
            }
        }
    }


    /**
     * Implement the NuageToxique and Loup attacks.
     */
    public void run() {
        while (true) {

            //Map does not refresh after player dies
            if (World.GAMESTATUESTATUS == 0) {
                continue;
            }
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            //Determines if the player is around nuageToxiques and will be attacked if is around
            try {
                nuageAttack(World.getNuageToxiques());
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

            //Determine whether the player is around the Loup, if so there is a probability of being attacked (by using Loup's combattre method)
            try {
                loupAttack(World.getCreatures());
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

            //Redrawing the game interface
            this.repaint();

            //if player's life <= 0 then ends the game
            if (joueur.perso.getPtVie() <= 0) {
                World.setGAMESTATUESTATUS(0);
                save();
                TestWoE.endOfGame();
            }

        }
    }


    /**
     * Create file: based on user input or by default.
     */
    public void save() {
        String fileName = "";
        Scanner scanner = new Scanner(System.in);
        System.out.println("please enter filename to save: (or by default press \"n\")");
        String s = scanner.next();
        if (s.equals("n")) {
            fileName = "sauvegarde" + World.gameCount + ".txt";
        } else {
            fileName = s;
        }
        sauvegardePartie(fileName);
    }


    @Override
    public void keyTyped(KeyEvent e) {

    }

    /**
     * Record the number of game saves
     */
    int countFile = 0;
    /**
     * The corresponding function is called according to the key pressed to achieve the corresponding function.
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
        if (e.getKeyCode() == KeyEvent.VK_P) { //pick an objet
            joueur.pickObjet();
        }
        if (e.getKeyCode() == KeyEvent.VK_C) { //combattre
            joueur.combattre();
        }
        if (e.getKeyCode() == KeyEvent.VK_G) { //use PotionSoin
            joueur.guerir();
        }
        if (e.getKeyCode() == KeyEvent.VK_K) //to test use PotionSoin
        {
            joueur.perso.setPtVie(joueur.perso.getPtVie() - 10);
            joueur.perso.affiche();
        }
        if (e.getKeyCode() == KeyEvent.VK_L) { //use Epinard
            joueur.perso.useEpinard();
        }
        if (e.getKeyCode() == KeyEvent.VK_S) { //save the world to txt file
            sauvegardePartie("sauvegarde_automatique" + countFile +".txt");
            countFile = countFile + 1;
        }

        this.repaint();
    }


    /**
     * Implement to determine whether the player is within the attack range of huageToxique, if so, attack.
     *
     * @param nuageToxiques All nuageToxique in the world, stored via Vector.
     */
    public void nuageAttack(Vector<NuageToxique> nuageToxiques) throws InterruptedException {
        Thread.sleep(1000);
        Iterator<NuageToxique> nuageToxiqueIterator = nuageToxiques.iterator();
        while (nuageToxiqueIterator.hasNext()) {
            NuageToxique nuageToxique1 = nuageToxiqueIterator.next();

            if (nuageToxique1.getLife() <= 0) { //若生命为0，则把该 nuageToxique 移除
                nuageToxiques.remove(nuageToxique1);
                continue;
            }
            if (Point2D.distance(nuageToxique1.getPos().getX(), nuageToxique1.getPos().getY(), joueur.perso.getPos().getX(), joueur.perso.getPos().getY()) <= nuageToxique1.getAttackRange())//玩家在攻击范围内
            {
                joueur.perso.setPtVie(joueur.perso.getPtVie() - nuageToxique1.getDamage());
            }
        }
    }

    /**
     * mplement to determine whether the player is within the attack range of Loup, if so, attack by using Loup's combattre method.
     * @param creatures All creatures that may be attacked.
     * @throws InterruptedException
     */
    public void loupAttack(ArrayList<Creature> creatures) throws InterruptedException {
        Thread.sleep(1000);
        Iterator<Creature> iterator = creatures.iterator();
        while (iterator.hasNext()) {
            Creature creature = iterator.next();
            if (World.getOCCUPIED(creature.getPos().getX(), creature.getPos().getY()) == 9 && creature.getPtVie() > 0) // is loup and is alive
            {
                Loup loup = (Loup) creature; //转成 Loup，因为 disAttMax 在 Loup 类里面
                if (Point2D.distance(loup.getPos().getX(), loup.getPos().getY(), joueur.perso.getPos().getX(), joueur.perso.getPos().getY()) <= (loup.getDistAttMax()))//玩家在Loup攻击范围内
                {
                    loup.combattre(joueur.perso);
                }
            }
        }
    }


    /**
     * Save world information to txt file.
     *
     * @param fileName name of file.
     */
    public void sauvegardePartie(String fileName) {
        try {
            //create FileWriter
            FileWriter file = new FileWriter(fileName);

            //create BufferedWriter
            BufferedWriter output = new BufferedWriter(file);


            //save OCCUPIED[][]
            for (int i = 0; i < World.TAILLE; i++) {
                for (int j = 0; j < World.TAILLE; j++) {
                    int a = World.getOCCUPIED(i, j);
                    output.write(a + " ");
                    //  System.out.print(World.getOCCUPIED(i,j));
                }
                output.newLine();
                System.out.println();
            }

            //Stores the type selected by the player(Archer or Guerrier)
            String s = joueur.perso.getClass().toString();
            output.write(s.substring(s.length() - 5) + " ");

            //Store the player's ptVie and other information
            output.write(joueur.perso.getPos().getX() + " " + joueur.perso.getPos().getY() + " " + joueur.perso.getNom() + " " + joueur.perso.getDistAttMax() + " " + joueur.perso.getPtVie() + " " + joueur.perso.getDegAtt() + " " + joueur.perso.getPtPar() + " " + joueur.perso.getPageAtt() + " " + joueur.perso.getPagePar() + " " + joueur.perso.getDirection() + " ");

            //Store player's item information: fleche, epee, epinard, potionsoin
            int nb1 = Joueur.getNbEpinard();
            int nb2 = Joueur.getNbPotionSoin();
            int nb3 = Joueur.getNbEpee();
            int nb4 = Joueur.getNbFleche();
            output.write(nb1 + " ");
            output.write(nb2 + " ");
            output.write(nb3 + " ");
            output.write(nb4 + " ");
            output.newLine();

            //Stores item information in the world
            Iterator<Objet> iterator = World.getObjets().iterator();
            while (iterator.hasNext()) {
                Objet o = iterator.next();
                String ss = o.toString();
                output.write(ss);
            }

            //store nuageToxique
            Iterator<NuageToxique> iterator1 = World.getNuageToxiques().iterator();
            while (iterator1.hasNext()) {
                NuageToxique n = iterator1.next();
                String ss = n.toString();
                output.write(ss);
            }

            //store all creatures in the world
            Iterator<Creature> iterator2 = World.getCreatures().iterator();
            while (iterator2.hasNext()) {
                Creature c = iterator2.next();
                String ss = c.toString();
                output.write(ss);
            }
            output.flush();
            output.close();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }


    /**
     * Reading the world from a file
     * @param fileName the name of file to charge. Default storage is in the current project's folder.
     */
    public static boolean  chargementPartie(String fileName){
        String source = fileName;
        BufferedReader buf = null;

        try {
            try {
                buf = new BufferedReader(new FileReader(source));
            }catch (Exception e){
                System.out.println("no such file, try again: " + fileName);
                return false;
            }


            String line = null;

            //charge OCCUPIED[][]
            for (int i = 0; i < World.TAILLE; i++) {
                line = buf.readLine();
                StringTokenizer st = new StringTokenizer(line, " ");
                for (int j = 0; j < World.TAILLE; j++) {
                    String state = st.nextToken();
                    World.setOCCUPIED(i, j, Integer.parseInt(state));
                }
                System.out.println();
            }

            //charge Joueur
            int joueurType = 0; //1 for Archer, 2 for Guerrier
            line = buf.readLine();
            StringTokenizer st = new StringTokenizer(line, " ");
            String s = st.nextToken();//get type
            int x = Integer.parseInt(st.nextToken());//get x position
            int y = Integer.parseInt((st.nextToken()));//get y position
            String name = st.nextToken();//get name
            if ("rcher".equals(s)) {//is Archer
                joueur = new Joueur("rcher", x, y, name);
                joueurType = 1;
            } else {
                joueur = new Joueur("rrier", x, y, name);
                joueurType = 2;
            }
            int disAtMax = Integer.parseInt(st.nextToken());
            int PtVie = Integer.parseInt(st.nextToken());
            if(World.gameCount == 0){
                PtVie = 100;
            }
            int DegAtt = Integer.parseInt(st.nextToken());
            int PtPar = Integer.parseInt(st.nextToken());
            int PageAtt = Integer.parseInt(st.nextToken());
            int PagePar = Integer.parseInt(st.nextToken());
            int Direction = Integer.parseInt(st.nextToken());
            joueur.perso.setDistAttMax(disAtMax);
            joueur.perso.setPtVie(PtVie);
            joueur.perso.setDegAtt(DegAtt);
            joueur.perso.setPagePar(PtPar);
            joueur.perso.setPageAtt(PageAtt);
            joueur.perso.setPagePar(PagePar);
            joueur.perso.setDirection(Direction);
            int nbEpinard = Integer.parseInt(st.nextToken());
            int nbPotionSoin = Integer.parseInt(st.nextToken());
            int nbEpee = Integer.parseInt(st.nextToken());
            int nbFleche = Integer.parseInt(st.nextToken());
            Joueur.setNbEpinard(nbEpinard);
            Joueur.setNbFleche(nbFleche);
            Joueur.setNbPotionSoin(nbPotionSoin);
            Joueur.setNbEpee(nbEpee);
            addObjets(nbEpinard, nbFleche, nbPotionSoin, nbEpee, joueurType);

            //charge others
            while ((line = buf.readLine()) != null) {
                st = new StringTokenizer(line, " ");
                String typeName = st.nextToken();
                chargeElem(typeName, st);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return true;
    }

    /**
     * Load player-owned items based on the information about the player-owned items in the file and the player's type.
     * @param nbEpinard Number of Epinard owned by the player.
     * @param nbFleche Number of Flech owned by the player.
     * @param nbPotionSoin Number of PotionSoin owned by the player.
     * @param nbEpee Number of Epee owned by the player.
     * @param joueurType The type chosen by the player: Archer or Guerrier
     */
    private static void addObjets(int nbEpinard, int nbFleche, int nbPotionSoin, int nbEpee, int joueurType) {
        for (int i = 0; i < nbEpinard; i++) {
            Epinard epinard = new Epinard();
            joueur.perso.getEpinard().add(epinard);
        }

        if (joueurType == 1) { // is Archer
            for (int i = 0; i < nbFleche; i++) {
                Fleche fleche = new Fleche();
                ((Archer) joueur.perso).getFleches().add(fleche);
            }
        } else {
            for (int i = 0; i < nbEpee; i++) {
                Epee epee = new Epee();
                ((Guerrier) joueur.perso).getEpees().add(epee);
            }
        }

        for (int i = 0; i < nbPotionSoin; i++) {
            PotionSoin potionSoin = new PotionSoin();
            joueur.perso.getPotionsoins().add(potionSoin);
        }
    }

    /**
     * Read the information from the file to store in the world.
     * @param typeName
     * @param st
     */
    public static void chargeElem(String typeName, StringTokenizer st) {
        switch (typeName) {
            case "Fleche":
                int type1 = Integer.parseInt(st.nextToken());
                int dommage1 = Integer.parseInt(st.nextToken());
                int life1 = Integer.parseInt(st.nextToken());
                int state1 = Integer.parseInt(st.nextToken());
                int x1 = Integer.parseInt(st.nextToken());
                int y1 = Integer.parseInt(st.nextToken());
                World.getObjets().add(new Fleche(type1, dommage1, life1, state1, x1, y1));
                break;

            case "Epee":
                int type2 = Integer.parseInt(st.nextToken());
                int dommage2 = Integer.parseInt(st.nextToken());
                int life2 = Integer.parseInt(st.nextToken());
                int state2 = Integer.parseInt(st.nextToken());
                int x2 = Integer.parseInt(st.nextToken());
                int y2 = Integer.parseInt(st.nextToken());
                Epee e = new Epee(type2, dommage2, life2, state2, x2, y2);
                World.getObjets().add(e);
                break;
            case "Epinard":
                int bonus3 = Integer.parseInt(st.nextToken());
                int type3 = Integer.parseInt(st.nextToken());
                int count3 = Integer.parseInt(st.nextToken());
                int state3 = Integer.parseInt(st.nextToken());
                int x3 = Integer.parseInt(st.nextToken());
                int y3 = Integer.parseInt(st.nextToken());
                World.getObjets().add(new Epinard(bonus3, type3, count3, state3, x3, y3));
                break;
            case "PotionSoin":
                int type4 = Integer.parseInt(st.nextToken());
                int recover4 = Integer.parseInt(st.nextToken());
                int life4 = Integer.parseInt(st.nextToken());
                int state4 = Integer.parseInt(st.nextToken());
                int x4 = Integer.parseInt(st.nextToken());
                int y4 = Integer.parseInt(st.nextToken());
                World.getObjets().add(new PotionSoin(type4, recover4, life4, state4, x4, y4));
                break;
            case "NuageToxique":
                int attackrange = Integer.parseInt(st.nextToken());
                int damage = Integer.parseInt(st.nextToken());
                int type5 = Integer.parseInt(st.nextToken());
                int state5 = Integer.parseInt(st.nextToken());
                int x5 = Integer.parseInt(st.nextToken());
                int y5 = Integer.parseInt(st.nextToken());
                NuageToxique nuage = new NuageToxique(attackrange, damage, type5, state5, x5, y5);
                new Thread(nuage).start();
                World.getNuageToxiques().add(new NuageToxique(attackrange, damage, type5, state5, x5, y5));
                break;
            case "Lapin":
                int type6 = Integer.parseInt(st.nextToken());
                int vie6 = Integer.parseInt(st.nextToken());
                int degatt6 = Integer.parseInt(st.nextToken());
                int ptpar = Integer.parseInt(st.nextToken());
                int pageatt = Integer.parseInt(st.nextToken());
                int pageapr = Integer.parseInt(st.nextToken());
                int direction = Integer.parseInt(st.nextToken());
                int x = Integer.parseInt(st.nextToken());
                int y = Integer.parseInt(st.nextToken());
                Lapin lapin = new Lapin(type6, vie6, degatt6, ptpar, pageatt, pageapr, direction, x, y);
                new Thread(lapin).start();
                World.getCreatures().add(lapin);

                break;
            case "Paysan":
                int type = Integer.parseInt(st.nextToken());
                String name = st.nextToken();
                int distattmax = Integer.parseInt(st.nextToken());
                int vie7 = Integer.parseInt(st.nextToken());
                int degatt7 = Integer.parseInt(st.nextToken());
                int ptpar7 = Integer.parseInt(st.nextToken());
                int pageatt7 = Integer.parseInt(st.nextToken());
                int pageapr7 = Integer.parseInt(st.nextToken());
                int direction7 = Integer.parseInt(st.nextToken());
                int x7 = Integer.parseInt(st.nextToken());
                int y7 = Integer.parseInt(st.nextToken());
                Paysan paysan = new Paysan(type, name, distattmax, vie7, degatt7, ptpar7, pageatt7, pageapr7, direction7, x7, y7);
                new Thread(paysan);
                World.getCreatures().add(paysan);
                break;
            case "Loup":
                int type8 = Integer.parseInt(st.nextToken());
                int distattmax8 = Integer.parseInt(st.nextToken());
                int degatt8 = Integer.parseInt(st.nextToken());
                int vie8 = Integer.parseInt(st.nextToken());
                int ptpar8 = Integer.parseInt(st.nextToken());
                int pageatt8 = Integer.parseInt(st.nextToken());
                int pageapr8 = Integer.parseInt(st.nextToken());
                int direction8 = Integer.parseInt(st.nextToken());
                int x8 = Integer.parseInt(st.nextToken());
                int y8 = Integer.parseInt(st.nextToken());
                Loup loup = new Loup(type8, distattmax8, degatt8, vie8, ptpar8, pageatt8, pageapr8, direction8, x8, y8);
                new Thread(loup).start();
                World.getCreatures().add(loup);
                break;
            case "Archer":
                int type9 = Integer.parseInt(st.nextToken());
                String name9 = st.nextToken();

                int distattmax9 = Integer.parseInt(st.nextToken());
                int vie9 = Integer.parseInt(st.nextToken());
                int degatt9 = Integer.parseInt(st.nextToken());
                int ptpar9 = Integer.parseInt(st.nextToken());
                int pageatt9 = Integer.parseInt(st.nextToken());
                int pageapr9 = Integer.parseInt(st.nextToken());
                int direction9 = Integer.parseInt(st.nextToken());
                int x9 = Integer.parseInt(st.nextToken());
                int y9 = Integer.parseInt(st.nextToken());
                Archer archer = new Archer(type9, name9, distattmax9, vie9, degatt9, ptpar9, pageatt9, pageapr9, direction9, x9, y9);
                new Thread(archer).start();
                World.getCreatures().add(archer);
                break;
            case "Guerrier":
                int type10 = Integer.parseInt(st.nextToken());
                String name10 = st.nextToken();

                int distattmax10 = Integer.parseInt(st.nextToken());
                int vie10 = Integer.parseInt(st.nextToken());
                int degatt10 = Integer.parseInt(st.nextToken());
                int ptpar10 = Integer.parseInt(st.nextToken());
                int pageatt10 = Integer.parseInt(st.nextToken());
                int pageapr10 = Integer.parseInt(st.nextToken());
                int direction10 = Integer.parseInt(st.nextToken());
                int x10 = Integer.parseInt(st.nextToken());
                int y10 = Integer.parseInt(st.nextToken());
                Guerrier guerrier = new Guerrier(type10, name10, distattmax10, vie10, degatt10, ptpar10, pageatt10, pageapr10, direction10, x10, y10);
                new Thread(guerrier).start();
                World.creatures.add(guerrier);
                break;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }
}

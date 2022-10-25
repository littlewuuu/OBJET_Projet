/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.centrale.objet.WoE;

import java.util.*;

/**
 * The class {@link org.centrale.objet.WoE.World} represents a world that
 * contains different roles like
 * {@code Personnage}({@code Archer},{@code Paysan}, {@code Guerrier}) and
 * {@code Monstre}({@code Lapin}, {@code Loup}), the role {@link Personnage} can
 * attack {@link Monstre} through the method
 * {@link org.centrale.objet.WoE.Guerrier#combattre(org.centrale.objet.WoE.Creature) combattre},
 * it can also restore vitality through the use of {@code PationSoin}.
 *
 * @author wuzilong
 * @author ZouKang
 * @version 1.0
 */
public class World {

    /**
     * The size of the World : TAILLE x TAILLE.
     */
    public final static int TAILLE = 100;
    /**
     * To identify if a coordinate is occupied, 0 stands for non-occupied and 1
     * stands for occupied by creatures, 2 stands for occupied by Fleche, 3 stands for occupied by Epee,
     * 4 stands for occupied by PotionSoin . original point is [0,0],the range of X coordinate is
     * [0,TAILLE-1] and the range for Y coordinate is [0,TAILLE-1]
     */
    private static final int[][] OCCUPIED = new int[TAILLE][TAILLE];
    /**
     * 1 Indicates that the game is in progress
     * 0 Indicates that the game is over
     */
    public static int GAMESTATUESTATUS = 1;
    /**
     * Record the number of rounds of the game.
     */
    public static int gameCount = 0;
    /**
     * To store different creatures: Archer, Paysan, Lapin, Guerrier, Loup
     */
    public static ArrayList<Creature> creatures = new ArrayList<>();
    /**
     * To store different objets:epinard,fleche,epee,potionSoin
     */
    public static ArrayList<Objet> objets = new ArrayList<>();
    /**
     * To store nuageToxiques.
     */
    public static Vector<NuageToxique> nuageToxiques = new Vector<>();

    /**
     * Default parameterless constructor
     */
    public World() {
    }

    public static int getGameCount() {
        return gameCount;
    }

    public static void setGameCount(int gameCount) {
        World.gameCount = gameCount;
    }

    public static int getGAMESTATUESTATUS() {
        return GAMESTATUESTATUS;
    }

    public static void setGAMESTATUESTATUS(int GAMESTATUESTATUS) {
        World.GAMESTATUESTATUS = GAMESTATUESTATUS;
    }

    public static ArrayList<Creature> getCreatures() {
        return creatures;
    }

    public static ArrayList<Objet> getObjets() {
        return objets;
    }

    public static Vector<NuageToxique> getNuageToxiques() {
        return nuageToxiques;
    }

    /**
     * @param x x-coordinate of the World
     * @param y y-coordinate of the World
     * @return the state of this position[x,y]
     */
    public static int getOCCUPIED(int x, int y) {
        return OCCUPIED[x][y];
    }

    /**
     * To declare a position is occupied
     *
     * @param x     x-coordinate of the World
     * @param y     y-coordinate of the World
     * @param state 0 or 1; 0:free 1:occupied
     */
    public static void setOCCUPIED(int x, int y, int state) {
        OCCUPIED[x][y] = state;
    }

    /**
     * Generate a random Point2D; it can not be a position which was already
     * occupied.
     *
     * @return a instance of Class Point2D which generated by random
     */
    public static Point2D createPoints() {
        Random generateurAleatoire = new Random();
        int X;
        int Y;
        while (true) {
            X = generateurAleatoire.nextInt(TAILLE);
            Y = generateurAleatoire.nextInt(TAILLE);
            if (OCCUPIED[X][Y] == 0) {
                OCCUPIED[X][Y] = 1;
                break;
            }
        }
        return new Point2D(X, Y);
    }

    /**
     * To mark different objects in the OCCUPIED.
     *
     * @param type 2:fleche; 3:epee; 4:PotionSoin
     * @return
     */
    public static Point2D createPoints(int type) {
        Random generateurAleatoire = new Random();
        int X;
        int Y;
        while (true) {
            X = generateurAleatoire.nextInt(TAILLE);
            Y = generateurAleatoire.nextInt(TAILLE);
            if (OCCUPIED[X][Y] == 0) {
                OCCUPIED[X][Y] = type;
                break;
            }
        }
        return new Point2D(X, Y);
    }

    /**
     * Returns the item information at this coordinate in the world, which is used to judge whether it is an attackable object when the player attacks.
     *
     * @param x
     * @param y
     * @return
     */
    public static Objet getObjet(int x, int y) {
        Iterator<Objet> iterator = objets.iterator();
        while (iterator.hasNext()) {
            Objet o = iterator.next();
            if (o.getPos().getX() == x && o.getPos().getY() == y) {
                return o;
            }
        }
        return null; //can not find the objet
    }

    /**
     * Returns the creatures at the coordinates according to the coordinates.
     * @param x Horizontal coordinates
     * @param y Longitudinal coordinates
     * @return  The creature in this position
     */
    public static Creature getCreature(int x, int y) {
        Iterator<Creature> iterator = creatures.iterator();
        while (iterator.hasNext()) {
            Creature c = iterator.next();
            if (c.getPos().getX() == x && c.getPos().getY() == y) {
                return c;
            }
        }
        return null;
    }

    /**
     * Print basic information about the world of printouts.
     */
    public void afficheWorld() {
//        Iterator<Creature> iterator = creatures.iterator();
//        while (iterator.hasNext()) {
//            iterator.next().affiche();
//        }
//
//        Iterator<Objet> i = objets.iterator();
//        while (i.hasNext()) {
//            i.next().affiche();
//        }
//
//
//        for (int j = TAILLE - 1; j >= 0; j--) {
//            for (int k = 0; k < TAILLE; k++) {
//                System.out.print(OCCUPIED[j][k]);
//            }
//            System.out.println();
//        }
    }

    /**
     * Generate a random world with the coordinates of the Creature randomly.
     * generated by {@link java.util.Random}
     */
    public void creerMondeAlea() {
        geneCreature(50);
        geneObjet(20);
        geneNuageToxique(10);
        geneEpinard(20);
    }

    /**
     * Generate a certain number objets.
     *
     * @param num
     */
    public void geneObjet(int num) {
        int count = 0;
        while (true) {
            int type = numberRandom3();
            switch (type) {
                case 1:
                    Fleche fleche = new Fleche();
                    objets.add(fleche);
                    break;
                case 2:
                    Epee epee = new Epee();
                    objets.add(epee);
                    break;
                case 3:
                    PotionSoin potionSoin = new PotionSoin();
                    objets.add(potionSoin);
                    break;
            }
            count++;
            if (count == num) {
                break;
            }
        }
    }

    public void geneEpinard(int num) {
        for (int i = 0; i < num; i++) {
            Epinard epinard = new Epinard();
            objets.add(epinard);
        }
    }

    /**
     * Randomly generate a specified number of NuageToxique.
     * @param num  Number of NuageToxique.
     */
    public void geneNuageToxique(int num) {
        for (int i = 0; i < num; i++) {
            NuageToxique nuageToxique = new NuageToxique();
            new Thread(nuageToxique).start();
            nuageToxiques.add(nuageToxique);
        }
    }




    /**
     * Generate a certain number creatures.
     *
     * @param num The number of creature to generate.
     */
    public void geneCreature(int num) {
        int count = 0;
        while (true) {
            int type = numberRandom5();
            switch (type) {
                case 1:
                    Archer archer = new Archer();
                    new Thread(archer).start();
                    creatures.add(archer); //creatures store all creatures in the world
                    break;
                case 2:
                    Paysan p = new Paysan();
                    new Thread(p).start();
                    creatures.add(p);
                    break;
                case 3:
                    Lapin l = new Lapin();
                    new Thread(l).start();
                    creatures.add(l);
                    break;
                case 4:
                    Guerrier g = new Guerrier();
                    new Thread(g).start();
                    creatures.add(g);
                    break;
                case 5:
                    Loup lo = new Loup();
                    new Thread(lo).start();
                    creatures.add(lo);
                    break;
            }
            count++;
            if (count == num) {
                break;
            }
        }
    }

    /**
     * Generate some creatures by random
     */
    public void geneCreature() {
        Random generateurAleatoire = new Random(100);
        int nbArchers = 50000;
        for (int i = 0; i < nbArchers; i++) {
            Archer a = new Archer(createPoints());
            creatures.add(a);
        }

        int nbPaysan = generateurAleatoire.nextInt(100);
        for (int i = 0; i < nbPaysan; i++) {
            Paysan a = new Paysan(createPoints());
            creatures.add(a);
        }

        int nbLapin = generateurAleatoire.nextInt(100);
        for (int i = 0; i < nbLapin; i++) {
            Lapin a = new Lapin(createPoints());
            creatures.add(a);
        }


        int nbGuerrier = generateurAleatoire.nextInt(100);
        for (int i = 0; i < nbGuerrier; i++) {
            Guerrier a = new Guerrier(createPoints());
            creatures.add(a);
        }

        int nbLoup = generateurAleatoire.nextInt(100);
        for (int i = 0; i < nbLoup; i++) {
            Loup a = new Loup(createPoints());
            creatures.add(a);
        }

        System.out.println("the number of creature in creatures: " + creatures.size());

        long debut = System.nanoTime();

        int sumPtVie = 0;
        for (int i = 0; i < creatures.size(); i++) {
            sumPtVie += creatures.get(i).getPtVie();
        }
        long fin = System.nanoTime();
        System.out.println("temps ecoule en ns : " + (fin - debut));

        System.out.println("sumPtVie: " + sumPtVie);

    }

    /**
     * To test if the function {@code Creature#deplace} of the class Creature
     * works well.
     */
    public void testDeplacement() {
        System.out.println("=============test deplacement: =============");
        System.out.println("persent position:");
        creatures.get(0).affiche();
        System.out.print("state of persent table OCCUPIED [" + creatures.get(0).getPos().getX() + " " + creatures.get(0).getPos().getY() + "]: ");
        System.out.println(OCCUPIED[creatures.get(0).getPos().getX()][creatures.get(0).getPos().getY()]);
        System.out.print("state of table OCCUPIED to move: ");
        System.out.println(OCCUPIED[creatures.get(0).getPos().getX() + 1][creatures.get(0).getPos().getY() + 1]);

        //We surround the seven directions of A, leaving only one exit
        creatures.get(1).setPos(new Point2D(creatures.get(0).getPos().getX() + 1, creatures.get(0).getPos().getY()));
        setOCCUPIED(creatures.get(0).getPos().getX() + 1, creatures.get(0).getPos().getY(), 1);
        creatures.get(2).setPos(new Point2D(creatures.get(0).getPos().getX() + 1, creatures.get(0).getPos().getY() - 1));
        setOCCUPIED(creatures.get(0).getPos().getX() + 1, creatures.get(0).getPos().getY() - 1, 1);
        creatures.get(3).setPos(new Point2D(creatures.get(0).getPos().getX(), creatures.get(0).getPos().getY() - 1));
        setOCCUPIED(creatures.get(0).getPos().getX(), creatures.get(0).getPos().getY() - 1, 1);
        creatures.get(4).setPos(new Point2D(creatures.get(0).getPos().getX() - 1, creatures.get(0).getPos().getY() - 1));
        setOCCUPIED(creatures.get(0).getPos().getX() - 1, creatures.get(0).getPos().getY() - 1, 1);
        creatures.get(5).setPos(new Point2D(creatures.get(0).getPos().getX() - 1, creatures.get(0).getPos().getY()));
        setOCCUPIED(creatures.get(0).getPos().getX() - 1, creatures.get(0).getPos().getY(), 1);
        creatures.get(6).setPos(new Point2D(creatures.get(0).getPos().getX() - 1, creatures.get(0).getPos().getY() + 1));
        setOCCUPIED(creatures.get(0).getPos().getX() - 1, creatures.get(0).getPos().getY() + 1, 1);
        creatures.get(7).setPos(new Point2D(creatures.get(0).getPos().getX(), creatures.get(0).getPos().getY() + 1));
        setOCCUPIED(creatures.get(0).getPos().getX(), creatures.get(0).getPos().getY() + 1, 1);

        creatures.get(0).deplacer();

        System.out.print("state of table OCCUPIED original: ");
        System.out.println(OCCUPIED[creatures.get(0).getPos().getX() - 1][creatures.get(0).getPos().getY() - 1]);
        System.out.print("state of table OCCUPIED now: ");
        System.out.println(OCCUPIED[creatures.get(0).getPos().getX()][creatures.get(0).getPos().getY()]);
        System.out.println("position after deplacement:");
        creatures.get(0).affiche();

    }

    /**
     * Generate a random number between 1-5. 1 for Archer, 2 for Paysan, 3 for
     * Lapin, 4 for Guerrier, 5 for Loup.
     *
     * @return a random number
     */
    public int numberRandom5() {
        Random generateurAleatoire = new Random();
        return generateurAleatoire.nextInt(5) + 1;
    }

    public int numberRandom3() {
        Random generateurAleatoire = new Random();
        return generateurAleatoire.nextInt(3) + 1;
    }

    /**
     * Calculate the time taken to generate ArrayLists of different sizes.
     */
    public void testTimeArrayList() {

        ArrayList<Creature> creatures = new ArrayList<>();

        //test ArrayList with the size 100
        geneArrayList(100, creatures);
        int sumPtVie = 0;
        long debut = System.nanoTime();
        for (int i = 0; i < creatures.size(); i++) {
            sumPtVie += creatures.get(i).getPtVie();
        }
        long fin = System.nanoTime();
        System.out.println("temps pour[size: 100; \t\tArraryList; \tthrough size] :" + (fin - debut) + "ns");
        creatures.clear();

        //test ArrayList with size 1000
        geneArrayList(1000, creatures);
        sumPtVie = 0;
        debut = System.nanoTime();
        for (int i = 0; i < creatures.size(); i++) {
            sumPtVie += creatures.get(i).getPtVie();
        }
        fin = System.nanoTime();
        System.out.println("temps pour[size: 1000; \t\tArraryList; \tthrough size] :" + (fin - debut) + "ns");
        creatures.clear();

        //test ArrayList with size 10000
        geneArrayList(10000, creatures);
        sumPtVie = 0;
        debut = System.nanoTime();
        for (int i = 0; i < creatures.size(); i++) {
            sumPtVie += creatures.get(i).getPtVie();
        }
        fin = System.nanoTime();
        System.out.println("temps pour[size: 10000; \tArraryList; \tthrough size] :" + (fin - debut) + "ns");
        creatures.clear();

        //test ArrayList with size 100000
        geneArrayList(100000, creatures);
        sumPtVie = 0;
        debut = System.nanoTime();
        for (int i = 0; i < creatures.size(); i++) {
            sumPtVie += creatures.get(i).getPtVie();
        }
        fin = System.nanoTime();
        System.out.println("temps pour[size: 100000; \tArraryList; \tthrough size] :" + (fin - debut) + "ns");
        creatures.clear();

        //test ArrayList with size 1000000
        geneArrayList(1000000, creatures);
        sumPtVie = 0;
        debut = System.nanoTime();
        for (int i = 0; i < creatures.size(); i++) {
            sumPtVie += creatures.get(i).getPtVie();
        }
        fin = System.nanoTime();
        System.out.println("temps pour[size: 1000000; \tArraryList; \tthrough size] :" + (fin - debut) + "ns");
        creatures.clear();

        //test ArrayList with size 100 through iterator
        geneArrayList(100, creatures);
        sumPtVie = 0;
        Iterator<Creature> it = creatures.iterator();
        debut = System.nanoTime();
        while (it.hasNext()) {
            Creature c = it.next();
            sumPtVie += c.getPtVie();
        }
        fin = System.nanoTime();
        System.out.println("temps pour[size: 100; \t\tArraryList; \tthrough iterator] :" + (fin - debut) + "ns");
        creatures.clear();

        //test ArrayList with size 1000 through iterator
        geneArrayList(1000, creatures);
        sumPtVie = 0;
        it = creatures.iterator();
        debut = System.nanoTime();
        while (it.hasNext()) {
            Creature c = it.next();
            sumPtVie += c.getPtVie();
        }
        fin = System.nanoTime();
        System.out.println("temps pour[size: 1000; \t\tArraryList; \tthrough iterator] :" + (fin - debut) + "ns");
        creatures.clear();

        //test ArrayList with size 10000 through iterator
        geneArrayList(10000, creatures);
        sumPtVie = 0;
        it = creatures.iterator();
        debut = System.nanoTime();
        while (it.hasNext()) {
            Creature c = it.next();
            sumPtVie += c.getPtVie();
        }
        fin = System.nanoTime();
        System.out.println("temps pour[size: 10000; \tArraryList; \tthrough iterator] :" + (fin - debut) + "ns");
        creatures.clear();

        //test ArrayList with size 100000 through iterator
        geneArrayList(100000, creatures);
        sumPtVie = 0;
        it = creatures.iterator();
        debut = System.nanoTime();
        while (it.hasNext()) {
            Creature c = it.next();
            sumPtVie += c.getPtVie();
        }
        fin = System.nanoTime();
        System.out.println("temps pour[size: 100000; \tArraryList; \tthrough iterator] :" + (fin - debut) + "ns");
        creatures.clear();

        //test ArrayList with size 1000000 through iterator
        geneArrayList(1000000, creatures);
        sumPtVie = 0;
        it = creatures.iterator();
        debut = System.nanoTime();
        while (it.hasNext()) {
            Creature c = it.next();
            sumPtVie += c.getPtVie();
        }
        fin = System.nanoTime();
        System.out.println("temps pour[size: 1000000; \tArraryList; \tthrough iterator] :" + (fin - debut) + "ns");
        creatures.clear();

    }

    /**
     * Generate an ArrayList of type Creature. The type of elements it contains
     * is generated by random.
     *
     * @param nbCreature The number of elements we want to generate.
     * @param creatures  Save elements to this ArrayList
     */
    public void geneArrayList(int nbCreature, ArrayList<Creature> creatures) {
        int type = numberRandom5();
        for (int i = 0; i < nbCreature; i++) {
            switch (type) {
                case 1:
                    Archer archer = new Archer();
                    creatures.add(archer);
                    break;
                case 2:
                    Paysan p = new Paysan();
                    creatures.add(p);
                    break;
                case 3:
                    Lapin l = new Lapin();
                    creatures.add(l);
                    break;
                case 4:
                    Guerrier g = new Guerrier();
                    creatures.add(g);
                    break;
                case 5:
                    Loup lo = new Loup();
                    creatures.add(lo);
                    break;
            }

        }

    }

    /**
     * To test if the class PotionSoin works correctly.
     */
//    public void testPotion() {
//        //Test potion function
//        System.out.println("================test potion================");
//        robin.setPtVie(70);
//        PotionSoin potion1 = new PotionSoin();
//        PotionSoin potion2 = new PotionSoin();
//        robin.getPotionsoins().add(potion1);
//        robin.getPotionsoins().add(potion2);
//        robin.affiche();
//        System.out.println("nombre de potion de robin: " + robin.getPotionsoins().size());
//        System.out.println("consumer 1 potion");
//        robin.usagePotion(potion2);
//        robin.affiche();
//        System.out.println("nombre de potion de robin: " + robin.getPotionsoins().size());
//        System.out.println("consumer 1 potion");
//        robin.usagePotion(potion1);
//        System.out.println("nombre de potion de robin: " + robin.getPotionsoins().size());
//        robin.usagePotion(potion1);
//        robin.affiche();
//        System.out.println("================fin de test potion================");
//
//    }

    /**
     * Calculate the time taken to generate LinkedLists of different sizes.
     */
    public void testTimeLinkedList() {

        LinkedList<Creature> creatures = new LinkedList<>();

        //test ArrayList with the size 100
        geneLinkedList(100, creatures);
        int sumPtVie = 0;
        long debut = System.nanoTime();
        for (int i = 0; i < creatures.size(); i++) {
            sumPtVie += creatures.get(i).getPtVie();
        }
        long fin = System.nanoTime();
        System.out.println("temps pour[size: 100; \t\tLinkedList; \tthrough size] :" + (fin - debut) + "ns");
        creatures.clear();

        //test ArrayList with size 1000
        geneLinkedList(1000, creatures);
        sumPtVie = 0;
        debut = System.nanoTime();
        for (int i = 0; i < creatures.size(); i++) {
            sumPtVie += creatures.get(i).getPtVie();
        }
        fin = System.nanoTime();
        System.out.println("temps pour[size: 1000; \t\tLinkedList; \tthrough size] :" + (fin - debut) + "ns");
        creatures.clear();

        //test ArrayList with size 10000
        geneLinkedList(10000, creatures);
        sumPtVie = 0;
        debut = System.nanoTime();
        for (int i = 0; i < creatures.size(); i++) {
            sumPtVie += creatures.get(i).getPtVie();
        }
        fin = System.nanoTime();
        System.out.println("temps pour[size: 10000; \tLinkedList; \tthrough size] :" + (fin - debut) + "ns");
        creatures.clear();

        //test ArrayList with size 100000
        geneLinkedList(100000, creatures);
        sumPtVie = 0;
        debut = System.nanoTime();
        for (int i = 0; i < creatures.size(); i++) {
            sumPtVie += creatures.get(i).getPtVie();
        }
        fin = System.nanoTime();
        System.out.println("temps pour[size: 100000; \tLinkedList; \tthrough size] :" + (fin - debut) + "ns");
        creatures.clear();

        //test ArrayList with size 1000000
        geneLinkedList(1000000, creatures);
        sumPtVie = 0;
        debut = System.nanoTime();
        for (int i = 0; i < creatures.size(); i++) {
            sumPtVie += creatures.get(i).getPtVie();
        }
        fin = System.nanoTime();
        System.out.println("temps pour[size: 1000000; \tLinkedList; \tthrough size] :" + (fin - debut) + "ns");
        creatures.clear();

        //test ArrayList with size 100 through iterator
        geneLinkedList(100, creatures);
        sumPtVie = 0;
        Iterator<Creature> it = creatures.iterator();
        debut = System.nanoTime();
        while (it.hasNext()) {
            Creature c = it.next();
            sumPtVie += c.getPtVie();
        }
        fin = System.nanoTime();
        System.out.println("temps pour[size: 100; \t\tLinkedList; \tthrough iterator] :" + (fin - debut) + "ns");
        creatures.clear();

        //test ArrayList with size 1000 through iterator
        geneLinkedList(1000, creatures);
        sumPtVie = 0;
        it = creatures.iterator();
        debut = System.nanoTime();
        while (it.hasNext()) {
            Creature c = it.next();
            sumPtVie += c.getPtVie();
        }
        fin = System.nanoTime();
        System.out.println("temps pour[size: 1000; \t\tLinkedList; \tthrough iterator] :" + (fin - debut) + "ns");
        creatures.clear();

        //test ArrayList with size 10000 through iterator
        geneLinkedList(10000, creatures);
        sumPtVie = 0;
        it = creatures.iterator();
        debut = System.nanoTime();
        while (it.hasNext()) {
            Creature c = it.next();
            sumPtVie += c.getPtVie();
        }
        fin = System.nanoTime();
        System.out.println("temps pour[size: 10000; \tLinkedList; \tthrough iterator] :" + (fin - debut) + "ns");
        creatures.clear();

        //test ArrayList with size 100000 through iterator
        geneLinkedList(100000, creatures);
        sumPtVie = 0;
        it = creatures.iterator();
        debut = System.nanoTime();
        while (it.hasNext()) {
            Creature c = it.next();
            sumPtVie += c.getPtVie();
        }
        fin = System.nanoTime();
        System.out.println("temps pour[size: 100000; \tLinkedList; \tthrough iterator] :" + (fin - debut) + "ns");
        creatures.clear();

        //test ArrayList with size 1000000 through iterator
        geneLinkedList(1000000, creatures);
        sumPtVie = 0;
        it = creatures.iterator();
        debut = System.nanoTime();
        while (it.hasNext()) {
            Creature c = it.next();
            sumPtVie += c.getPtVie();
        }
        fin = System.nanoTime();
        System.out.println("temps pour[size: 1000000; \tLinkedList; \tthrough iterator] :" + (fin - debut) + "ns");
        creatures.clear();

    }

    /**
     * Generate a LinkedList of type Creature
     *
     * @param nbCreature The number of elements we want to generate.
     * @param creatures  Save elements to this LinkedList.
     */
    public void geneLinkedList(int nbCreature, LinkedList<Creature> creatures) {
        int type = numberRandom5();
        for (int i = 0; i < nbCreature; i++) {
            switch (type) {
                case 1:
                    Archer archer = new Archer();
                    creatures.add(archer);
                    break;
                case 2:
                    Paysan p = new Paysan();
                    creatures.add(p);
                    break;
                case 3:
                    Lapin l = new Lapin();
                    creatures.add(l);
                    break;
                case 4:
                    Guerrier g = new Guerrier();
                    creatures.add(g);
                    break;
                case 5:
                    Loup lo = new Loup();
                    creatures.add(lo);
                    break;
            }

        }

    }


}

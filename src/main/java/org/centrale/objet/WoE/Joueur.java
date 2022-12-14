package org.centrale.objet.WoE;

import java.util.Scanner;


/**
 * Represents the player in the world, able to move, attack, use medicine, etc.
 */
public class Joueur {

    /**
     * Number of fleche owned by the player
     */
    public static int nbFleche = 10;

    /**
     * Number of epee owned by the player
     */
    public static int nbEpee = 0;

    /**
     * Number of potionSoin owned by the player
     */
    public static int nbPotionSoin = 0;

    /**
     * Number of Epinard owned by the player
     */
    public static int nbEpinard = 0;

    /**
     * Used to indicate on the OOCUPIED table that the position is occupied by Joueur
     */
    private final int type = 1;
    /**
     * The role Joueur can choose: Archer, Guerrier
     */
    public Personnage perso;


    /**
     * used to charge world
     *
     * @param s
     * @param x
     * @param y
     * @param name
     */
    Joueur(String s, int x, int y, String name) {
        if ("rcher".equals(s)) {
            perso = new Archer(new Point2D(x, y));
            perso.setNom(name);
        } else {
            perso = new Guerrier(new Point2D(x, y));
            perso.setNom(name);
        }

    }

    Joueur() {
        choosePersonnage();
    }

    public static int getNbEpinard() {
        return nbEpinard;
    }

    public static void setNbEpinard(int nbEpinard) {
        Joueur.nbEpinard = nbEpinard;
    }

    public static int getNbFleche() {
        return nbFleche;
    }

    public static void setNbFleche(int nb) {
        nbFleche = nb;
    }

    public static int getNbEpee() {
        return nbEpee;
    }

    public static void setNbEpee(int nb) {
        nbEpee = nb;
    }

    public static int getNbPotionSoin() {
        return nbPotionSoin;
    }

    public static void setNbPotionSoin(int nb) {
        nbPotionSoin = nb;
    }

    public Personnage getPerso() {
        return perso;
    }

    public void setPerso(Personnage perso) {
        this.perso = perso;
    }


    public int getType() {
        return type;
    }

    /**
     * A method provided for Joueur to choose his Personnage and name.
     * Set the position on the correspondent OCCUPIED table to 1, which means
     * this position is occupied by the Joueur
     */
    public void choosePersonnage() {
        System.out.println("Please choose a role:(Archer or Guerrier)");
        Scanner scanner = new Scanner(System.in);
        String chose = scanner.nextLine();
        String name = "";
        while (true) {
            if (chose.equals("Archer")) {
                perso = new Archer();
                World.setOCCUPIED(perso.getPos().getX(), perso.getPos().getY(), 1);
                System.out.println("name your Archer: ");
                name = scanner.nextLine();
                perso.setNom(name);
                break;
            } else if (chose.equals("Guerrier")) {
                perso = new Guerrier();
                World.setOCCUPIED(perso.getPos().getX(), perso.getPos().getY(), 1);
                System.out.print("name your Guerrier: ");
                name = scanner.nextLine();
                perso.setNom(name);
                break;
            } else {
                System.out.println("incorrect input! ");
                System.out.println("input again:");
                chose = scanner.nextLine();
            }
        }
    }

    public void affiche() {
        perso.affiche();
    }

    /**
     * This method enables the player to move up around the world, and will detect if the target position can be moved to.
     */
    public void moveUp() {
        if (perso.getPos().getY() <= 0) {
            System.out.println("border!");
            return;
        }
        if (World.getOCCUPIED(perso.getPos().getX(), perso.getPos().getY() - 1) != 0) { //can't move up
            System.out.println("Position is already occupied by : " + World.getOCCUPIED(perso.getPos().getX(), perso.getPos().getY() - 1));
            return;
        }

        perso.setPos(new Point2D(perso.getPos().getX(), perso.getPos().getY() - 1));
        World.setOCCUPIED(perso.getPos().getX(), perso.getPos().getY() + 1, 0);
        World.setOCCUPIED(perso.getPos().getX(), perso.getPos().getY(), 1);
        perso.setDirection(1);
        System.out.println("joueur position : " + perso.getPos() + "; direction : " + perso.getDirection());
    }

    /**
     *     This method enables the player to move down around the world, and will detect if the target position can be moved to.
     */
    public void moveDown() {
        if (perso.getPos().getY() >= 99) {
            System.out.println("border!");
            return;
        }
        if (World.getOCCUPIED(perso.getPos().getX(), perso.getPos().getY() + 1) != 0) { //can't move down
            System.out.println("Position is already occupied by : " + World.getOCCUPIED(perso.getPos().getX(), perso.getPos().getY() + 1));
            return;
        }

        perso.setPos(new Point2D(perso.getPos().getX(), perso.getPos().getY() + 1));
        World.setOCCUPIED(perso.getPos().getX(), perso.getPos().getY() - 1, 0);
        World.setOCCUPIED(perso.getPos().getX(), perso.getPos().getY(), 1);
        perso.setDirection(3);
        System.out.println("joueur position : " + perso.getPos() + "; direction : " + perso.getDirection());

    }

    /**
     * This method enables the player to move left around the world, and will detect if the target position can be moved to.
     */
    public void moveLeft() {
        if (perso.getPos().getX() <= 0) {
            System.out.println("border!");
            return;
        }
        if (World.getOCCUPIED(perso.getPos().getX() - 1, perso.getPos().getY()) != 0) { //can't move left
            System.out.println("Position is already occupied by : " + World.getOCCUPIED(perso.getPos().getX() - 1, perso.getPos().getY()));
            return;
        }

        perso.setPos(new Point2D(perso.getPos().getX() - 1, perso.getPos().getY()));
        World.setOCCUPIED(perso.getPos().getX() + 1, perso.getPos().getY(), 0);
        World.setOCCUPIED(perso.getPos().getX(), perso.getPos().getY(), 1);
        perso.setDirection(4);
        System.out.println("joueur position : " + perso.getPos() + "; direction : " + perso.getDirection());
    }

    //This method enables the player to move right around the world, and will detect if the target position can be moved to.
    public void moveRight() {
        if (perso.getPos().getX() >= 99) {
            System.out.println("border!");
            return;
        }
        if (World.getOCCUPIED(perso.getPos().getX() + 1, perso.getPos().getY()) != 0) { //can't move right
            System.out.println("Position is already occupied by : " + World.getOCCUPIED(perso.getPos().getX() + 1, perso.getPos().getY()));
            return;
        }

        perso.setPos(new Point2D(perso.getPos().getX() + 1, perso.getPos().getY()));
        World.setOCCUPIED(perso.getPos().getX() - 1, perso.getPos().getY(), 0);
        World.setOCCUPIED(perso.getPos().getX(), perso.getPos().getY(), 1);
        perso.setDirection(2);
        System.out.println("joueur position : " + perso.getPos() + "; direction : " + perso.getDirection());
    }

    /**
     * This method enables the player to pick up items, but only if they are directly in front of the player.
     * Will determine the current player's type, as some items cannot be picked up by a particular player.
     */
    public void pickObjet() {
        Point2D p = new Point2D(perso.getPos()); //joueur's coordinate
        if (perso.getClass().toString().equals("class org.centrale.objet.WoE.Archer")) { //is Archer
            Archer a = (Archer) perso;
            switch (perso.getDirection()) {
                case 1:
                    Point2D p1 = new Point2D(p.getX(), p.getY() - 1);//objet's coordinate
                    int type = World.getOCCUPIED(p.getX(), p.getY() - 1);
                    if (type == 2 || type == 4 || type == 10) {
                        Objet o = World.getObjet(p1.getX(), p1.getY());
                        switch (type) {
                            case 2:
                                Fleche f = (Fleche) o;
                                f.setState(1);
                                //fleches.add(new Fleche(f));
                                a.getFleches().add(new Fleche(f));
                                nbFleche = a.getFleches().size();
                                World.setOCCUPIED(p1.getX(), p1.getY(), 0);
                                System.out.println("pick up Fleche");
                                break;
                            case 4:
                                PotionSoin po = (PotionSoin) o;
                                po.setState(1);
                                //potionSoins.add(new PotionSoin(po));
                                a.getPotionsoins().add(po);
                                nbPotionSoin = a.getPotionsoins().size();
                                World.setOCCUPIED(p1.getX(), p1.getY(), 0);
                                System.out.println("pick up PotionSoin");
                                break;
                            case 10:
                                Epinard epinard = (Epinard) o;
                                epinard.setState(1);
                                a.getEpinard().add(epinard);
                                nbEpinard = a.getEpinard().size();
                                World.setOCCUPIED(p1.getX(), p1.getY(), 0);
                                System.out.println("pick up Epianrd");
                                break;
                        }
                    }
                    break;
                case 2:
                    Point2D p2 = new Point2D(p.getX() + 1, p.getY());//object's coordinate
                    int type2 = World.getOCCUPIED(p.getX() + 1, p.getY());
                    if (type2 == 2 || type2 == 4 || type2 == 10) {
                        Objet o = World.getObjet(p2.getX(), p2.getY());
                        switch (type2) {
                            case 2:
                                Fleche f = (Fleche) o;
                                f.setState(1);
                                //fleches.add(new Fleche(f));
                                a.getFleches().add(new Fleche(f));
                                nbFleche = a.getFleches().size();
                                World.setOCCUPIED(p2.getX(), p2.getY(), 0);
                                System.out.println("pick up Fleche");
                                break;

                            case 4:
                                PotionSoin po = (PotionSoin) o;
                                po.setState(1);
                                //potionSoins.add(new PotionSoin(po));
                                a.getPotionsoins().add(po);
                                nbPotionSoin = a.getPotionsoins().size();
                                World.setOCCUPIED(p2.getX(), p2.getY(), 0);
                                System.out.println("pick up PotionSoin");
                                break;
                            case 10:
                                Epinard epinard = (Epinard) o;
                                epinard.setState(1);
                                a.getEpinard().add(epinard);
                                nbEpinard = a.getEpinard().size();
                                World.setOCCUPIED(p2.getX(), p2.getY(), 0);
                                System.out.println("pick up Epianrd");
                                break;
                        }
                    }
                    break;
                case 3:
                    Point2D p3 = new Point2D(p.getX(), p.getY() + 1);//object's coordinate
                    int type3 = World.getOCCUPIED(p.getX(), p.getY() + 1);
                    if (type3 == 2 || type3 == 4 || type3 == 10) {
                        Objet o = World.getObjet(p3.getX(), p3.getY());
                        switch (type3) {
                            case 2:
                                Fleche f = (Fleche) o;
                                f.setState(1);
                                //fleches.add(new Fleche(f));
                                a.getFleches().add(new Fleche(f));
                                nbFleche = a.getFleches().size();
                                World.setOCCUPIED(p3.getX(), p3.getY(), 0);
                                System.out.println("pick up Fleche");
                                break;

                            case 4:
                                PotionSoin po = (PotionSoin) o;
                                po.setState(1);
                                // potionSoins.add(new PotionSoin(po));
                                a.getPotionsoins().add(po);
                                nbPotionSoin = a.getPotionsoins().size();
                                World.setOCCUPIED(p3.getX(), p3.getY(), 0);
                                System.out.println("pick up PotionSoin");
                                break;
                            case 10:
                                Epinard epinard = (Epinard) o;
                                epinard.setState(1);
                                a.getEpinard().add(epinard);
                                nbEpinard = a.getEpinard().size();
                                World.setOCCUPIED(p3.getX(), p3.getY(), 0);
                                System.out.println("pick up Epianrd");
                                break;
                        }
                    }
                    break;
                case 4:
                    Point2D p4 = new Point2D(p.getX() - 1, p.getY());//object's coordinate
                    int type4 = World.getOCCUPIED(p.getX() - 1, p.getY());
                    if (type4 == 2 || type4 == 4 || type4 == 10) {
                        Objet o = World.getObjet(p4.getX(), p4.getY());
                        switch (type4) {
                            case 2:
                                Fleche f = (Fleche) o;
                                f.setState(1);
                                //fleches.add(new Fleche(f));
                                a.getFleches().add(new Fleche(f));
                                nbFleche = a.getFleches().size();
                                World.setOCCUPIED(p4.getX(), p4.getY(), 0);
                                System.out.println("pick up Fleche");
                                break;

                            case 4:
                                PotionSoin po = (PotionSoin) o;
                                po.setState(1);
                                //potionSoins.add(new PotionSoin(po));
                                a.getPotionsoins().add(po);
                                nbPotionSoin = a.getPotionsoins().size();
                                World.setOCCUPIED(p4.getX(), p4.getY(), 0);
                                System.out.println("pick up PotionSoin");
                                break;
                            case 10:
                                Epinard epinard = (Epinard) o;
                                epinard.setState(1);
                                a.getEpinard().add(epinard);
                                nbEpinard = a.getEpinard().size();
                                World.setOCCUPIED(p4.getX(), p4.getY(), 0);
                                System.out.println("pick up Epianrd");
                                break;
                        }
                    }
                    break;
            }
        } else { // is Guerrier
            Guerrier a = (Guerrier) perso;
            switch (perso.getDirection()) {
                case 1:
                    Point2D p1 = new Point2D(p.getX(), p.getY() - 1);//object's coordinate
                    int type = World.getOCCUPIED(p.getX(), p.getY() - 1);
                    if (type == 3 || type == 4) {
                        Objet o = World.getObjet(p1.getX(), p1.getY());
                        switch (type) {
                            case 3:
                                Epee e = (Epee) o;
                                e.setState(1);
                                //epees.add(new Epee(e));
                                a.getEpees().add(e);
                                nbEpee = a.getEpees().size();
                                World.setOCCUPIED(p1.getX(), p1.getY(), 0);
                                System.out.println("pick up  Epee");
                                break;
                            case 4:
                                PotionSoin po = (PotionSoin) o;
                                po.setState(1);
                                //potionSoins.add(new PotionSoin(po));
                                a.getPotionsoins().add(po);
                                nbPotionSoin = a.getPotionsoins().size();
                                World.setOCCUPIED(p1.getX(), p1.getY(), 0);
                                System.out.println("pick up PotionSoin");
                                break;
                        }
                    }
                    break;
                case 2:
                    Point2D p2 = new Point2D(p.getX() + 1, p.getY());//object's coordinate
                    int type2 = World.getOCCUPIED(p.getX() + 1, p.getY());
                    if (type2 == 3 || type2 == 4) {
                        Objet o = World.getObjet(p2.getX(), p2.getY());
                        switch (type2) {

                            case 3:
                                Epee e = (Epee) o;
                                e.setState(1);
                                //epees.add(new Epee(e));
                                a.getEpees().add(e);
                                nbEpee = a.getEpees().size();
                                World.setOCCUPIED(p2.getX(), p2.getY(), 0);
                                System.out.println("pick up Epee");
                                break;
                            case 4:
                                PotionSoin po = (PotionSoin) o;
                                po.setState(1);
                                //potionSoins.add(new PotionSoin(po));
                                a.getPotionsoins().add(po);
                                nbPotionSoin = a.getPotionsoins().size();
                                World.setOCCUPIED(p2.getX(), p2.getY(), 0);
                                System.out.println("pick up PotionSoin");
                                break;
                        }
                    }
                    break;
                case 3:
                    Point2D p3 = new Point2D(p.getX(), p.getY() + 1);//object's coordinate
                    int type3 = World.getOCCUPIED(p.getX(), p.getY() + 1);
                    if (type3 == 3 || type3 == 4) {
                        Objet o = World.getObjet(p3.getX(), p3.getY());
                        switch (type3) {
                            case 3:
                                Epee e = (Epee) o;
                                e.setState(1);
                                //epees.add(new Epee(e));
                                a.getEpees().add(e);
                                nbEpee = a.getEpees().size();
                                World.setOCCUPIED(p3.getX(), p3.getY(), 0);
                                System.out.println("pick up Epee");
                                break;
                            case 4:
                                PotionSoin po = (PotionSoin) o;
                                po.setState(1);
                                // potionSoins.add(new PotionSoin(po));
                                a.getPotionsoins().add(po);
                                nbPotionSoin = a.getPotionsoins().size();
                                World.setOCCUPIED(p3.getX(), p3.getY(), 0);
                                System.out.println("pick up PotionSoin");
                                break;
                        }
                    }
                    break;
                case 4:
                    Point2D p4 = new Point2D(p.getX() - 1, p.getY());//object's coordinate
                    int type4 = World.getOCCUPIED(p.getX() - 1, p.getY());
                    if (type4 == 3 || type4 == 4) {
                        Objet o = World.getObjet(p4.getX(), p4.getY());
                        switch (type4) {

                            case 3:
                                Epee e = (Epee) o;
                                e.setState(1);
                                //epees.add(new Epee(e));
                                a.getEpees().add(e);
                                nbEpee = a.getEpees().size();
                                World.setOCCUPIED(p4.getX(), p4.getY(), 0);
                                System.out.println("pick up Epee");
                                break;
                            case 4:
                                PotionSoin po = (PotionSoin) o;
                                po.setState(1);
                                //potionSoins.add(new PotionSoin(po));
                                a.getPotionsoins().add(po);
                                nbPotionSoin = a.getPotionsoins().size();
                                World.setOCCUPIED(p4.getX(), p4.getY(), 0);
                                System.out.println("pick up PotionSoin");
                                break;
                        }
                    }
                    break;
            }

        }

    }

    /**
     * The attack is carried out by calling the combattre method of Archer or Guerrier.
     * Can only attack creatures directly in front of it.
     */
    public void combattre() {
        if (perso.getClass().toString().equals("class org.centrale.objet.WoE.Archer")) { //Joueur's perso is Archer
            Archer a = (Archer) perso;
            switch (perso.getDirection()) {
                case 1:
                    Creature c1 = getTarget();
                    if (c1 == null) {
                        System.out.println("there is no target to attack!");
                        break;
                    }
                    a.combattre(c1);
                    c1.affiche();
                    if (c1.getPtVie() <= 0) {
                        World.setOCCUPIED(c1.getPos().getX(), c1.getPos().getY(), 0);
                        World.creatures.remove(c1);
                        new Thread(c1).interrupt();

                    }

                    break;
                case 2:
                    Creature c2 = getTarget();
                    if (c2 == null) {
                        System.out.println("there is no target to attack!");
                        break;
                    }

                    if (c2.getPtVie() <= 0) {
                        World.setOCCUPIED(c2.getPos().getX(), c2.getPos().getY(), 0);
                        World.creatures.remove(c2);
                        new Thread(c2).interrupt();
                    }
                    a.combattre(c2);
                    c2.affiche();
                    break;
                case 3:
                    Creature c3 = getTarget();
                    if (c3 == null) {
                        System.out.println("there is no target to attack!");
                        break;
                    }

                    if (c3.getPtVie() <= 0) {
                        World.setOCCUPIED(c3.getPos().getX(), c3.getPos().getY(), 0);
                        World.creatures.remove(c3);
                        new Thread(c3).interrupt();
                    }
                    a.combattre(c3);
                    c3.affiche();
                    break;
                case 4:
                    Creature c4 = getTarget();
                    if (c4 == null) {
                        System.out.println("there is no target to attack!");
                        break;
                    }

                    if (c4.getPtVie() <= 0) {
                        World.setOCCUPIED(c4.getPos().getX(), c4.getPos().getY(), 0);
                        World.creatures.remove(c4);
                        new Thread(c4).interrupt();
                    }
                    a.combattre(c4);
                    c4.affiche();
                    break;
            }
        } else { // is Guerrier
            Guerrier g = (Guerrier) perso;
            switch (perso.getDirection()) {
                case 1:
                    Creature c1 = getTarget();
                    if (c1 == null) {
                        System.out.println("there is no target to attack!");
                        break;
                    }

                    if (c1.getPtVie() <= 0) {
                        World.setOCCUPIED(c1.getPos().getX(), c1.getPos().getY(), 0);
                        World.creatures.remove(c1);

                    }
                    g.combattre(c1);
                    c1.affiche();
                    break;
                case 2:
                    Creature c2 = getTarget();
                    if (c2 == null) {
                        System.out.println("there is no target to attack!");
                        break;
                    }

                    if (c2.getPtVie() <= 0) {
                        World.setOCCUPIED(c2.getPos().getX(), c2.getPos().getY(), 0);
                        World.creatures.remove(c2);

                    }
                    g.combattre(c2);
                    c2.affiche();
                    break;
                case 3:
                    Creature c3 = getTarget();
                    if (c3 == null) {
                        System.out.println("there is no target to attack!");
                        break;
                    }

                    if (c3.getPtVie() <= 0) {
                        World.setOCCUPIED(c3.getPos().getX(), c3.getPos().getY(), 0);
                        World.creatures.remove(c3);

                    }
                    g.combattre(c3);
                    c3.affiche();
                    break;
                case 4:
                    Creature c4 = getTarget();
                    if (c4 == null) {
                        System.out.println("there is no target to attack!");
                        break;
                    }

                    if (c4.getPtVie() <= 0) {
                        World.setOCCUPIED(c4.getPos().getX(), c4.getPos().getY(), 0);
                        World.creatures.remove(c4);

                    }
                    g.combattre(c4);
                    c4.affiche();
                    break;
            }

        }
    }

    /**
     * Get a Creature within direct attack range of the joueur.
     * Only the nearest one is returned.
     *
     * @return
     */
    public Creature getTarget() {
        Creature c = null;
        int distance = 0;
        switch (perso.getDirection()) {
            case 1:
                if (perso.getPos().getY() < perso.getDistAttMax()) {
                    distance = perso.getPos().getY();
                } else {
                    distance = perso.getDistAttMax();
                } //If the distance to the boundary is less than perso.getDistAttMax(), the search range is the distance to the boundary
                for (int i = 1; i <= distance; i++) {
                    if (World.getOCCUPIED(perso.getPos().getX(), perso.getPos().getY() - i) >= 5 && World.getOCCUPIED(perso.getPos().getX(), perso.getPos().getY() - i) != 10) { //Represents a creature that can attack
                        c = World.getCreature(perso.getPos().getX(), perso.getPos().getY() - i);
                        break;
                    }
                }
                break;
            case 2:
                if (perso.getPos().getX() > World.TAILLE - 1 - perso.getDistAttMax()) {
                    distance = World.TAILLE - 1 - perso.getPos().getX();
                } else {
                    distance = perso.getDistAttMax();
                } //If the distance to the boundary is less than perso.getDistAttMax(), the search range is the distance to the boundary
                for (int i = 1; i <= distance; i++) {
                    if (World.getOCCUPIED(perso.getPos().getX() + i, perso.getPos().getY()) >= 5 && World.getOCCUPIED(perso.getPos().getX(), perso.getPos().getY() - i) != 10) { //Represents a creature that can attack
                        c = World.getCreature(perso.getPos().getX() + i, perso.getPos().getY());
                        break;
                    }
                }
                break;
            case 3:
                if (perso.getPos().getY() > World.TAILLE - 1 - perso.getDistAttMax()) {
                    distance = World.TAILLE - 1 - perso.getPos().getY();
                } else {
                    distance = perso.getDistAttMax();
                }
                for (int i = 1; i <= distance; i++) {
                    if (World.getOCCUPIED(perso.getPos().getX(), perso.getPos().getY() + i) >= 5 && World.getOCCUPIED(perso.getPos().getX(), perso.getPos().getY() - i) != 10) { //Represents a creature that can attack
                        c = World.getCreature(perso.getPos().getX(), perso.getPos().getY() + i);
                        break;
                    }
                }
                break;
            case 4:
                if (perso.getPos().getX() < perso.getDistAttMax()) {
                    distance = perso.getPos().getX();
                } else {
                    distance = perso.getDistAttMax();
                }
                for (int i = 1; i <= distance; i++) {
                    if (World.getOCCUPIED(perso.getPos().getX() - i, perso.getPos().getY()) >= 5 && World.getOCCUPIED(perso.getPos().getX(), perso.getPos().getY() - i) != 10) { //Represents a creature that can attack
                        c = World.getCreature(perso.getPos().getX() - i, perso.getPos().getY());
                        break;
                    }
                }
                break;
        }

        return c;
    }

    public void guerir() {
        perso.usePotion();
    }

}

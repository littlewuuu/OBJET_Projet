package org.centrale.objet.WoE;

import java.util.Random;

public class NuageToxique extends Objet implements Combattant, Deplacable, Runnable {

    /**
     * Maker on the map OCCUPIED[][].
     */
    private final int type = -1;
    private int attackRange = 2;
    private int damage = 30;

    public NuageToxique(int life, Point2D pos, int valeur, String name, int attackRange, int damage) {
        super(life, pos, valeur, name);
        this.attackRange = attackRange;
        this.damage = damage;
        setPos(World.createPoints(10));
    }

    public NuageToxique() {
        setPos(World.createPoints(type));
    }

    public NuageToxique(int life, int valeur, int attackRange, int damage) {
        super(life, valeur);
        this.attackRange = attackRange;
        this.damage = damage;
        setPos(World.createPoints(10));
    }

    public NuageToxique(int attackRange, int damage) {
        this.attackRange = attackRange;
        this.damage = damage;
        setPos(World.createPoints(10));
    }

    public NuageToxique(int attackrange, int damage, int type5, int state5, int x5, int y5) {
        setAttackRange(attackrange);
        setDamage(damage);
        setType(type5);
        setState(state5);
        setPos(new Point2D(x5, y5));
    }

    @Override
    public int getType() {
        return type;
    }

    public int getDamage() {
        return damage;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

    public int getAttackRange() {
        return attackRange;
    }

    public void setAttackRange(int attackRange) {
        this.attackRange = attackRange;
    }

    /**
     * Damage is taken when the player is in range of the attack.
     *
     * @param c
     */
    @Override
    public void combattre(Creature c) {
        if (c == null) {
            return;
        }
        if (Point2D.distance(c.getPos().getX(), c.getPos().getY(), this.getPos().getX(), this.getPos().getY()) <= this.attackRange) {
            c.setPtVie(c.getPtVie() - damage);
        }
    }

    /**
     * Random movement to surrounding unoccupied positions.
     */
    @Override
    public void deplacer() {
        Random generateRandom = new Random();
        int x, y;
        x = y = 0;
        do {
            x = generateRandom.nextInt(3) - 1;
            y = generateRandom.nextInt(3) - 1;
            if ((x != 0 || y != 0)
                    && (this.getPos().getX() + x >= 0)
                    && (this.getPos().getY() + y >= 0)
                    && (this.getPos().getX() + x <= World.TAILLE - 1)
                    && (this.getPos().getY() + y <= World.TAILLE - 1)
                    && World.getOCCUPIED(this.getPos().getX() + x, this.getPos().getY() + y) == 0) {
                break;
            }
        } while (true);
        World.setOCCUPIED(this.getPos().getX(), this.getPos().getY(), 0);
        setPos(new Point2D(this.getPos().getX() + x, this.getPos().getY() + y));
        World.setOCCUPIED(this.getPos().getX(), this.getPos().getY(), type);
    }

    /**
     * Implement random movement of NuageToxique at regular intervals.
     */
    @Override
    public void run() {
        while (true) {

//            //stop moving when game is over
//            if (World.GAMESTATUESTATUS == 0) {
//                continue;
//            }

            try {
                Thread.sleep(10000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            deplacer();

            if (this.getLife() <= 0)
                break;
        }
    }


    public void affiche() {
        System.out.print("NuageToxique : ");
        super.affiche();
    }

    @Override
    public String toString() {
        return "NuageToxique " + attackRange + " " + damage + " " + type + " " + getState() + " " + getPos().getX() + " " + getPos().getY() + '\n';
    }
}

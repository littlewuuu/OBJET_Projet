package org.centrale.objet.WoE;

import jdk.jshell.UnresolvedReferenceException;

import java.util.Random;

public class NuageToxique extends Objet implements Combattant,Deplacable,Runnable{

    private int attackRange = 2;
    private int damage = 6;

    private final int type = -1;

    public NuageToxique(int life, Point2D pos, int valeur, String name, int attackRange, int damage) {
        super(life, pos, valeur, name);
        this.attackRange = attackRange;
        this.damage = damage;
        setPos(World.createPoints(10));
    }

    public NuageToxique(){
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

    @Override
    public int getType() {
        return type;
    }

    public int getDamage() {
        return damage;
    }

    public int getAttackRange() {
        return attackRange;
    }

    public void setAttackRange(int attackRange) {
        this.attackRange = attackRange;
    }

    /**
     * Damage is taken when the player is in range of the attack.
     * @param c
     */
    @Override
    public void combattre(Creature c) {
        if(c == null){
            return;
        }
        if(Point2D.distance(c.getPos().getX(),c.getPos().getY(),this.getPos().getX(),this.getPos().getY()) <= this.attackRange){
            c.setPtVie(c.getPtVie() - damage);
        }
    }

    @Override
    public void deplacer() {
        Random generateRandom = new Random();
        int x, y;
        x = y = 0;
        do {
            x = generateRandom.nextInt(3) -1;
            y = generateRandom.nextInt(3) -1;
            if ((x != 0 || y != 0)
                    && (this.getPos().getX() + x >=0)
                    && (this.getPos().getY() + y >=0)
                    &&(this.getPos().getX() + x <= World.TAILLE-1)
                    &&(this.getPos().getY() + y <= World.TAILLE - 1)
                    && World.getOCCUPIED(this.getPos().getX() + x, this.getPos().getY() + y) == 0) {
                break;
            }
        } while (true);
        World.setOCCUPIED(this.getPos().getX(), this.getPos().getY(), 0);
        setPos(new Point2D(this.getPos().getX()+x,this.getPos().getY()+y));
        World.setOCCUPIED(this.getPos().getX(), this.getPos().getY(), type);
    }

    @Override
    public void run() {
        while (true) {
            try {
                Thread.sleep(10000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            deplacer();

            //************还没写完***********
            //判断 attackRange 里面有没有可攻击的生物
            for(int i = - attackRange; i <= attackRange;i++)
            {
                for (int j = - attackRange; j <= attackRange; j++) {
                    Creature c = World.getCreature(this.getPos().getX()+i,this.getPos().getY()+j);
                    combattre(c);
                }
            }

            if(this.getLife()<=0)
                break;

        }
    }

    public void affiche(){
        System.out.print("NuageToxique : ");
        super.affiche();
    }
}

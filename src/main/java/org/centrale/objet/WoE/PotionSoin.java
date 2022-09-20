/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.centrale.objet.WoE;

/**
 *
 * @author wuzilong
 */
public class PotionSoin extends Objet{
    private int recover = 5;
    private int state = 1; //标记是否被消耗 0-使用过消失 1-未使用

        
    public PotionSoin(){}
    public PotionSoin(int recouver, int life, int valeur) {
        super(life, valeur);
        this.recover = recouver;
    }
    
    //补充生命值
    public void Guerir(Creature c){
        c.setPtVie(c.getPtVie()+recover);
    }

    public int getRecover() {
        return recover;
    }

    public void setRecover(int recover) {
        this.recover = recover;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }
    
    
}

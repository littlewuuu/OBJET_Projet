package org.centrale.objet.WoE;

import java.util.Vector;

public class TestException {
    public static void main(String[] args){

        //NullPointerException
        Lapin lapin1 = new Lapin();
        lapin1 = null;
        lapin1.setPos(null);
        int coorX = lapin1.getPos().getX();




        // ArrayIndexOutOfBoundsException
//        Vector <Lapin> Lapins = new Vector<>();
//        Lapins.add(new Lapin());
//        Lapins.get(1).affiche();


        // ArithmeticException
//        Random generateurAleatoire = new Random();
//        int random1,random2, NumberLapin;
//        random1 = generateurAleatoire.nextInt(3) + 1;
//        random2 = generateurAleatoire.nextInt(2) - 1;
//        NumberLapin = random1/random2;
//        System.out.println("nombre de lapin est "+NumberLapin);



        // ClassCastException
//        Monstre lapin1 = new Lapin();
//        lapin1 = (Loup) lapin1;



        // NumberFormatException
//        int n =Integer.parseInt("100E");
//        System.out.println(n);

        //StackOverflowError
//        Lapin lapin1 = new Lapin();
//        generateLapin(lapin1);

        //ConcurrentModificationException
//        Vector<Lapin> lapins = new Vector<>();
//        lapins.add(new Lapin());
//        lapins.add(new Lapin());
//        lapins.add(new Lapin());
//        for(Lapin la : lapins){
//            lapins.remove(la);
////            lapins.add(new Lapin());
//        }

    }
    public static void generateLapin(Lapin lapin){
        if (lapin.getDegAtt() == 15){
            generateLapin(lapin);
        }
    }
}

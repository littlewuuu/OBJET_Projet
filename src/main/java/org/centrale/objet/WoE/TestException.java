package org.centrale.objet.WoE;

import java.util.Vector;
import java.util.Random;
import java.util.Scanner;

public class TestException {
    public static void main(String[] args){

        //NullPointerException
//        Lapin lapin1 = new Lapin();
//        lapin1 = null;
//        lapin1.setPos(null);
//        int coorX = lapin1.getPos().getX();




        // ArrayIndexOutOfBoundsException
//        Vector <Lapin> Lapins = new Vector<>();
//        Lapins.add(new Lapin());
//        for(int i=0; i <2; i++){
//            System.out.println(Lapins.get(i).getPos().getX());
//        }



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

        // NumberFormatException with input
        System.out.println("Please give the number of Lapin you want in your world!");
        try{
            inputLapin();
        }catch (NumberFormatException e){
            System.out.println("There is error in input");
        }


//        Scanner scan = new Scanner(System.in);
//        if (scan.hasNext()){
//            try{
//                int numberLapin = Integer.parseInt(scan.next());
//                Vector<Lapin> lapins = new Vector<>();
//                for(int i=0; i<numberLapin;i++ ){
//                    lapins.add(new Lapin());
//                }
//                System.out.println("There are "+lapins.size()+" Lapin");
//            } catch (NumberFormatException e){
//                e.printStackTrace();
//            }finally {
//                System.out.println("This is the end");
//            }
//
//        }

    }

    public static void inputLapin() throws NumberFormatException{
        Scanner scan = new Scanner(System.in);
        if (scan.hasNext()){
            int numberLapin = Integer.parseInt(scan.next());
                Vector<Lapin> lapins = new Vector<>();
                for(int i=0; i<numberLapin;i++ ){
                    lapins.add(new Lapin());
                }
                System.out.println("There are "+lapins.size()+" Lapin");
        }
    }
    public static void generateLapin(Lapin lapin){
        if (lapin.getDegAtt() == 15){
            generateLapin(lapin);
        }
    }
}

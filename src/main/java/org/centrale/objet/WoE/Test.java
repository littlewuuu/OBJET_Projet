package org.centrale.objet.WoE;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class Test {

    public static void main(String[] args) throws IOException {
        FileWriter file = new FileWriter("dss.txt");

        //创建 BufferedWriter
        BufferedWriter output = new BufferedWriter(file);


        output.write("asdf");
        output.flush();
        output.close();

    }

}
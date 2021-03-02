package com.example.LiuMod;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class HarmonGUI extends JFrame {

    JTable jt, harmonT;
    JLabel l1, l2, l3;

    public HarmonGUI(ArrayList<Event> events, ArrayList<String> harmon, int t, int lMax){
        setSize(400, 300);
        setName("Harmonogram");
        setTitle("Harmonogram");
        int szer = Toolkit.getDefaultToolkit().getScreenSize().width;
        int wys = Toolkit.getDefaultToolkit().getScreenSize().height;
        int szer_okna = getSize().width;
        int wys_okna = getSize().height;
        setLocation((szer-szer_okna)/2,(wys-wys_okna)/2);
        setResizable(false);
        setLayout(null);

        String column[] = new String[events.size()+1];
        int i = 1;
        column[0] = "";
        for(Event event: events){
            column[i] = event.name;
            i++;
        }

        String data[][] = new String[5][events.size()+1];
        i = 1;
        data[0][0] = "p(i)";
        data[1][0] = "d(j)";
        data[2][0] = "r(j)";
        data[3][0] = "d(i)";
        data[4][0] = "L(i)";
        for(Event event: events){
            data[0][i] = String.valueOf(event.p);
            data[1][i] = String.valueOf(event.dj);
            data[2][i] = String.valueOf(event.r);
            data[3][i] = String.valueOf(event.di);
            data[4][i] = String.valueOf(event.li);
            i++;
        }

        jt = new JTable(data, column);
        JScrollPane sp = new JScrollPane(jt);
        sp.setBounds(10, 10, 360, 103);
        add(sp);

        l1 = new JLabel("T = " + t);
        l1.setBounds(10, 130, 100, 10);
        add(l1);

        l2 = new JLabel("L max = " + lMax);
        l2.setBounds(130, 130, 100, 10);
        add(l2);

        l3 = new JLabel("Harmonogram:");
        l3.setBounds(10, 160, 100, 10);
        add(l3);

        String[] columnH = new String[t];
        for(int j=0; j < t; j++){
            columnH[j] = String.valueOf(j+1);
        }

        i = 0;
        String[][] dataH = new String[1][t];
        for(String s: harmon){
            dataH[0][i] = s;
            i++;
        }

        harmonT = new JTable(dataH, columnH);
        harmonT.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        JScrollPane sp2 = new JScrollPane(harmonT,JScrollPane.VERTICAL_SCROLLBAR_NEVER ,JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        sp2.setBounds(10, 190, 360, 55);
        add(sp2);


    }
}

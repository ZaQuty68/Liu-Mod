package com.example.LiuMod;

import org.graphstream.graph.Node;
import org.graphstream.graph.implementations.SingleGraph;

import javax.swing.*;
import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Scanner;

public class Liu {

    public Liu(String filename){

        /////////////////////////////////////reading file


        File file = new File("src/main/java/com/example/LiuMod/" + filename);
        ArrayList<Event> events = new ArrayList<>();
        try(Scanner sc = new Scanner(file, StandardCharsets.UTF_8.name())){
            while(sc.hasNextLine()){
                String line = sc.nextLine();
                String[] s = line.split(";");
                Event e = new Event(s[0], Integer.parseInt(s[1].trim()), Integer.parseInt(s[2].trim()), Integer.parseInt(s[3].trim()));
                events.add(e);
                if(s.length == 5){
                    e.free = false;
                    String[] pe = s[4].split(",");
                    for(String pes: pe){
                        for(Event event: events){
                            if(event.name.matches("^" + pes.trim() + "$")){
                                event.next.add(e);
                                e.beforeNeeded ++;
                            }
                        }
                    }
                }
            }
        }
        catch (IOException e){
            e.printStackTrace();
        }

        /////////////////////////////////////////////// di

        for(Event event: events){
            int min = event.dj;
            event.di = DiRecur(event, min);
        }

        ///////////////////////////////////////////////// Liu

        boolean allDone = false;
        int t = 0;
        ArrayList<String> harmon = new ArrayList<>();
        while(!allDone){
            ArrayList<Event> freeEvents = new ArrayList<>();
            for(Event event: events){
                if(t >= event.r && event.free && event.alreadyDone < event.p){
                    freeEvents.add(event);
                }
            }
            if(freeEvents.isEmpty()){
                harmon.add("NULL");
            }
            else{
                Event eventMin = freeEvents.get(0);
                for(Event event: freeEvents){
                    if(eventMin.di > event.di){
                        eventMin = event;
                    }
                }
                harmon.add(eventMin.name);
                eventMin.alreadyDone ++;
                if(eventMin.alreadyDone == eventMin.p){
                    eventMin.li = t+1 - eventMin.dj;
                    for(Event event: eventMin.next){
                        event.before ++;
                        if(event.before == event.beforeNeeded){
                            event.free = true;
                        }
                    }
                }
            }

            t++;
            allDone = true;
            for(Event event: events){
                if(event.alreadyDone < event.p){
                    allDone = false;
                }
            }
        }

        int lMax = events.get(0).li;
        for(Event event: events){
            if(lMax < event.li){
                lMax = event.li;
            }
        }


        ////////////////////////////////////////////////// Showing

        System.setProperty("org.graphstream.ui", "swing");
        SingleGraph graph = new SingleGraph("Graph1");
        graph.setStrict(false);
        graph.setAutoCreate(true);
        graph.setAttribute("ui.stylesheet", styleSheet);
        for(Event event: events){
            for(Event e: event.next){
                graph.addEdge(event.name + "-" + e.name, event.name, e.name, true);
            }
        }
        graph.display();
        for(Node node: graph){
            node.setAttribute("ui.label", node.getId());
        }

        HarmonGUI harmonGUI = new HarmonGUI(events, harmon, t, lMax);
        harmonGUI.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        harmonGUI.setVisible(true);
    }

    public int DiRecur(Event event, int min){
        if(min >= event.dj){
            min = event.dj;
        }
        if(!event.next.isEmpty()){
            for(Event e: event.next){
                int nextMin = DiRecur(e, min);
                if(min >= nextMin){
                    min = nextMin;
                }
            }
        }
        return min;
    }

    protected String styleSheet =
            "node {" +
                    "   fill-color: green;" +
                    "   size: 30px, 30px;" +
                    "}";
}

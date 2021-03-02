package com.example.LiuMod;

import java.util.ArrayList;

public class Event {
    public String name;
    public int p, dj, r, di, li, alreadyDone, before, beforeNeeded;
    public ArrayList<Event> next;
    boolean free, done;

    public Event(String name, int p, int dj, int r){
        this.name = name;
        this.p = p;
        this.dj = dj;
        this.r = r;
        next = new ArrayList<>();
        alreadyDone = 0;
        free = true;
        done = false;
        before = 0;
        beforeNeeded = 0;
    }
}

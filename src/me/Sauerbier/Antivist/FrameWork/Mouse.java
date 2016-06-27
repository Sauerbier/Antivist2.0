package me.Sauerbier.Antivist.FrameWork;

import me.Sauerbier.Antivist.Antivist;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.Map;
import java.util.TreeMap;

/**
 * @Author Sauerbier | Jan
 * @Copyright 2016 by Jan Hof
 * All rights reserved.
 **/
public class Mouse implements MouseListener, MouseMotionListener{
    private TreeMap<Integer,MouseCallBack> listeners = new TreeMap<>();
    private int down = 0,x,y;
    private GameTickAdapter adapter;

    public void addCallBack(int priority,MouseCallBack call){
        listeners.put(priority,call);
    }


    @Override
    public void mouseClicked(MouseEvent e) {

    }
    @Override
    public void mousePressed(MouseEvent e) {
        down = e.getButton();

      adapter = new GameTickAdapter() {
            @Override
            public void onTick() {
                if(down == e.getButton()) {
                    for (Map.Entry<Integer, MouseCallBack> entry : listeners.entrySet()) {
                        entry.getValue().call(Mouse.this);
                        if(entry.getValue().isCanceled()) return;
                    }
                }else{
                    Antivist.getInstance().removeTickAdapter(this);
                }
            }
        };

        Antivist.getInstance().addTickAdapter(adapter);

    }

    @Override
    public void mouseReleased(MouseEvent e) {
        down = 0;
        Antivist.getInstance().removeTickAdapter(adapter);
        for(Map.Entry<Integer,MouseCallBack> entry : listeners.entrySet()){
            entry.getValue().call(Mouse.this);
            if(entry.getValue().isCanceled()) return;
        }
    }

    @Override
    public void mouseEntered(MouseEvent e) {



    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void mouseDragged(MouseEvent e) {
        this.y = e.getY();
        this.x = e.getX();
        for(Map.Entry<Integer,MouseCallBack> entry : listeners.entrySet()){
            if(entry.getValue().isCallOnMove())
                entry.getValue().call(Mouse.this);
        }
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        this.y = e.getY();
        this.x = e.getX();
        for(Map.Entry<Integer,MouseCallBack> entry : listeners.entrySet()){
            if(entry.getValue().isCallOnMove())
                entry.getValue().call(Mouse.this);
        }
    }

    public int getY() {
        return y;
    }

    public int getX() {
        return x;
    }

    public int getDown() {
        return down;
    }
}

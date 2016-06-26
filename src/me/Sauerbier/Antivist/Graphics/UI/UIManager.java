package me.Sauerbier.Antivist.Graphics.UI;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author Sauerbier | Jan
 * @Copyright 2016 by Jan Hof
 * All rights reserved.
 **/
public class UIManager {
    public static final int TRANSPARENT = 0xffff00ff;
    public static final Color BACKGROUD_COLOR = new Color(187, 187, 187, 255);
    public static final Color BACKGROUD_COLOR_DARK = new Color(33, 33, 33, 233);
    private List<UIPanel> panels = new ArrayList<>();


    public void update(){
        for (int i = 0; i < panels.size(); i++) {
            panels.get(i).update();
        }
    }


    public void render(Graphics g){
        for (int i = 0; i < panels.size(); i++) {
            panels.get(i).render(g);
        }
    }

    public void addPanel(UIPanel panel){
        panels.add(panel);
    }

}

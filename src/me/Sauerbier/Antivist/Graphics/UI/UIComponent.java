package me.Sauerbier.Antivist.Graphics.UI;

import me.Sauerbier.Antivist.FrameWork.Vector2i;

import java.awt.*;

/**
 * @Author Sauerbier | Jan
 * @Copyright 2016 by Jan Hof
 * All rights reserved.
 **/
public interface UIComponent {


     void update();
    void render(Graphics g);
    void add(UIComponent component);
    UIComponent getParent();
    void setParent(UIComponent component);
    Vector2i getPosition();
    void setPosition(Vector2i position);
    Color getBackgroundColor();


}

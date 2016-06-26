package me.Sauerbier.Antivist.Graphics.UI;

import me.Sauerbier.Antivist.FrameWork.Mouse;
import me.Sauerbier.Antivist.FrameWork.Vector2i;

import java.awt.*;

/**
 * @Author Sauerbier | Jan
 * @Copyright 2016 by Jan Hof
 * All rights reserved.
 **/
public class UIButton implements UIComponent {

    private String text;
    private Vector2i position;
    private Vector2i size;
    private Color color,textColor;
    private UIComponent parent;
    private UIComponent child;
    private UIButtonEvent event;
    private UIActionListener actionEvent;
    private Rectangle bounds;
    private boolean inside;

    public UIButton(String text,Vector2i position, Vector2i size, Color color, Color textColor) {
        this.text = text;
        this.position = position;
        this.size = size;
        this.color = color;
        this.textColor = textColor;
        bounds = new Rectangle(position.getX(),position.getY(),size.getX() + 5,size.getY());
    }

    @Override
    public void update() {
        if(event != null) {
            if (bounds.contains(Mouse.getMouseX(), Mouse.getMouseY())) {
                if(!inside)
                    event.mouseEnter(this);
                inside = true;
            }else{
                if(inside)
                    event.mouseLeave(this);
                inside = false;
            }
        }


        if(child != null)
            child.update();
    }

    @Override
    public void render(Graphics g) {
        g.setColor(color);
        //g.fillRect(position.getX(),position.getY(),size.getX() + 5,size.getY());
       g.fillRoundRect(position.getX(),position.getY(),size.getX() + 5,size.getY(),5,5);
        if(!text.equals("")) {
            g.setColor(textColor);
            g.drawString(text, position.getX() + 5, position.getY() + 15);
        }
        if(child != null)
            child.render(g);
    }

    @Override
    public void add(UIComponent component) {
        component.setParent(this);
        Vector2i rel = position.clone();
        rel.add(component.getPosition());
        component.setPosition(rel);
        this.child = component;
    }

    @Override
    public UIComponent getParent() {
        return parent;
    }

    @Override
    public void setParent(UIComponent component) {
        this.parent = component;
        bounds = new Rectangle(component.getPosition().getX() + position.getX(), component.getPosition().getY() + position.getY(),size.getX() + 5,size.getY());
    }

    @Override
    public Vector2i getPosition() {
        return position;
    }

    @Override
    public void setPosition(Vector2i position) {
        this.position = position;
    }

    @Override
    public Color getBackgroundColor() {
        return color;
    }

    public UIActionListener getActionEvent() {
        return actionEvent;
    }

    public void setActionEvent(UIActionListener actionEvent) {
        this.actionEvent = actionEvent;
    }

    public UIButtonEvent getEvent() {
        return event;
    }

    public void setEvent(UIButtonEvent event) {
        this.event = event;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Vector2i getSize() {
        return size;
    }

    public void setSize(Vector2i size) {
        this.size = size;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public Color getTextColor() {
        return textColor;
    }

    public void setTextColor(Color textColor) {
        this.textColor = textColor;
    }

    public UIComponent getChild() {
        return child;
    }

    public void setChild(UIComponent child) {
        this.child = child;
    }

    public Rectangle getBounds() {
        return bounds;
    }

    public void setBounds(Rectangle bounds) {
        this.bounds = bounds;
    }
}

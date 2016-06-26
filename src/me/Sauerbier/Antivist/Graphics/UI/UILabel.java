package me.Sauerbier.Antivist.Graphics.UI;

import me.Sauerbier.Antivist.FrameWork.Vector2i;

import java.awt.*;

/**
 * @Author Sauerbier | Jan
 * @Copyright 2016 by Jan Hof
 * All rights reserved.
 **/
public class UILabel implements UIComponent {

    private String text;
    private Font font;
    private Vector2i position;
    private Color color;
    private UIComponent parent;
    public UILabel(String text, Font font, Vector2i position, Color color) {
        this.text = text;
        this.font = font;
        this.position = position;
        this.color = color;
    }

    @Override
    public void update() {

    }

    @Override
    public void render(Graphics g) {
        g.setFont(font);
        g.setColor(color);
        g.drawString(text,position.getX(),position.getY());
    }

    @Override
    public void add(UIComponent component) {

    }

    @Override
    public UIComponent getParent() {
        return parent;
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
        return new Color(UIManager.TRANSPARENT);
    }

    @Override
    public void setParent(UIComponent parent) {
        this.parent = parent;
    }


    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Font getFont() {
        return font;
    }

    public void setFont(Font font) {
        this.font = font;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }
}

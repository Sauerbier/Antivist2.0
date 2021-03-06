package me.Sauerbier.Antivist;

import me.Sauerbier.Antivist.FrameWork.GameTickAdapter;
import me.Sauerbier.Antivist.Graphics.PlayerUI;
import me.Sauerbier.Antivist.Graphics.UI.UIManager;
import me.Sauerbier.Antivist.Level.Level;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;
import java.io.File;
import java.util.ArrayList;

/**
 * @Author Sauerbier | Jan
 * @Copyright 2016 by Jan Hof
 * All rights reserved.
 **/
public class Antivist extends Canvas implements Runnable{

    private static Antivist instance;
    private static Level current;
    public static int WIDTH = 384, HEIGHT = WIDTH / 16*9, SCALE = 4;

    private Thread thread;
    private boolean running;
    private JFrame frame;

    private Level level;
    private PlayerUI ui;
    private UIManager uiManager;
    private BufferedImage finalView = new BufferedImage(WIDTH ,HEIGHT, BufferedImage.TYPE_INT_RGB);
    private int[] viewPixels = ((DataBufferInt)finalView.getRaster().getDataBuffer()).getData();
    private ArrayList<GameTickAdapter> tickAdapters = new ArrayList<>();


    public static void main(String[] args){
        instance = new Antivist();
        instance.getFrame().setResizable(false);
        instance.getFrame().setTitle("Antivist 2.0");
        instance.getFrame().add(instance);
        instance.getFrame().pack();
        instance.getFrame().setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        instance.getFrame().setLocationRelativeTo(null);
        instance.getFrame().setVisible(true);
        instance.start();
    }

    public Antivist(){
        Dimension dimension = new Dimension(WIDTH * SCALE, HEIGHT * SCALE);
        setPreferredSize(dimension);




        frame = new JFrame();
        level = new Level(new File(Antivist.class.getResource("/levels/the_begin/").getPath()));
        current = level;
        uiManager = new UIManager();
        ui = new PlayerUI(level.getClientPlayer());
        addKeyListener(level.getKeyboard());
        addMouseListener(level.getMouse());
        addMouseMotionListener(level.getMouse());

        uiManager.addPanel(ui.panel);
    }



    public synchronized void start(){
        running = true;
        thread = new Thread(this, "Main Game Thread");
        thread.start();
    }

    public synchronized void stop(){
        running = false;
        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static Antivist getInstance() {
        return instance;
    }

    @Override
    public void run() {
        long lastTime = System.nanoTime();
        long timer = System.currentTimeMillis();
        double ns = 1.0E09 / 60;
        double delta = 0;
        int frames = 0;
        int updates = 0;
        requestFocus();
        while (running){
            long now = System.nanoTime();
            delta += (now -lastTime)/ns;
            lastTime = now;
            while(delta >= 1){
                tick();
                updates++;
                delta --;
            }
            render();
            frames++;

            if(System.currentTimeMillis() - timer > 1000){
                timer += 1000;
                frame.setTitle("Antivist 2.0 | FPS: " + frames+ " ticks: " + updates);
                updates = 0;
                frames = 0;
            }
        }
        stop();
    }

    private void render() {
        BufferStrategy bs = getBufferStrategy();
        if(bs == null){
            createBufferStrategy(2);
            return;
        }
        level.getScreen().clear();
        level.render((int)level.getClientPlayer().getPosition().getX() - (WIDTH >> 1) + (level.getClientPlayer().getSprite().getSizeX() >> 1),
                (int)level.getClientPlayer().getPosition().getY() -(HEIGHT >> 1) + (level.getClientPlayer().getSprite().getSizeY() >> 1));



        for (int i = 0; i < viewPixels.length; i++) {
            viewPixels[i] = level.getScreen().getPixels()[i];
        }
        Graphics g = bs.getDrawGraphics();

        {
            //Graphics here :3
            g.drawImage(finalView,0,0,getWidth(),getHeight(),null);
            uiManager.render(g);
        }

        g.dispose();
        bs.show();
    }

    private void tick() {
        level.update();
        ui.update();
        uiManager.update();

        for (int i = 0; i < tickAdapters.size(); i++) {
            tickAdapters.get(i).onTick();
        }
    }


    public void addTickAdapter(GameTickAdapter adapter){
        tickAdapters.add(adapter);
    }

    public void removeTickAdapter(GameTickAdapter adapter){
        tickAdapters.remove(adapter);
    }
    public JFrame getFrame() {
        return frame;
    }

    public void setFrame(JFrame frame) {
        this.frame = frame;
    }

    public Thread getThread() {
        return thread;
    }

    public void setThread(Thread thread) {
        this.thread = thread;
    }

    public boolean isRunning() {
        return running;
    }

    public void setRunning(boolean running) {
        this.running = running;
    }


    public BufferedImage getFinalView() {
        return finalView;
    }

    public void setFinalView(BufferedImage finalView) {
        this.finalView = finalView;
    }

    public int[] getViewPixels() {
        return viewPixels;
    }

    public void setViewPixels(int[] viewPixels) {
        this.viewPixels = viewPixels;
    }
    public static Level getCurrent() {
        return current;
    }

    public static void setCurrent(Level current) {
        Antivist.current = current;
    }

    public Level getLevel() {
        return level;
    }
}

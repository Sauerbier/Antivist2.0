package me.Sauerbier.Antivist.Level;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import me.Sauerbier.Antivist.Antivist;
import me.Sauerbier.Antivist.Entity.Mobs.Player;
import me.Sauerbier.Antivist.FrameWork.Keyboard;
import me.Sauerbier.Antivist.Graphics.Screen;
import me.Sauerbier.Antivist.ResourceManagement.Resources;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;

/**
 * @Author Sauerbier | Jan
 * @Copyright 2016 by Jan Hof
 * All rights reserved.
 **/
public class Level {

    private int width, height;
    private String name;
    private File levelDir;
    private JsonObject metadata;
    private BufferedImage level, background;
    private int[] backgroundPixels;
    private String[] tiles;
    private Resources resources;
    private Screen screen;
    private Keyboard keyboard;
    private Player clientPlayer;

    public Level(File levelDir) {
        this.levelDir = levelDir;
        this.name = levelDir.getName();
        resources = new Resources();
        keyboard = new Keyboard();
        try {
            resources.loadSprites(Antivist.class.getResource("/levels/"+name+ "/Sprites.json").getPath());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        screen = new Screen(resources, Antivist.WIDTH, Antivist.HEIGHT, 128, 32, 5);
        loadLevel();
        clientPlayer = new Player(this,keyboard,metadata.get("playerX").getAsInt(),metadata.get("playerY").getAsInt());
    }


    private void loadLevel() {
        try {
            level = ImageIO.read(new File(Level.class.getResource("/levels/" + name + "/level.png").getPath()));
            background = ImageIO.read(new File(Level.class.getResource("/levels/" + name + "/background.png").getPath()));
            background = screen.resizeImage(background,screen.getWidth(),screen.getHeight());
            int w = background.getWidth();
            int h = background.getHeight();
            backgroundPixels = new int[w*h];
            background.getRGB(0 ,0,w,h,backgroundPixels,0,w);
            Gson gson = new Gson();
            BufferedReader r = new BufferedReader(new FileReader(new File((Level.class.getResource("/levels/" + name + "/MetaData.json").getPath()))));
            metadata = gson.fromJson(r, JsonObject.class);
        } catch (Exception e) {
            e.printStackTrace();
        }

        this.width = level.getWidth();
        this.height = level.getHeight();
        tiles = new String[width*height];
        int[] tmp = new int[width*height];
        level.getRGB(0,0,width,height,tmp,0,width);
        for (int i = 0; i < tmp.length; i++) {
            tiles[i] = Integer.toHexString(tmp[i]);
        }
    }

    public void update() {
        keyboard.update();
        clientPlayer.update();
    }

    public void render(int xScroll, int yScroll) {
        screen.setOffset(xScroll, yScroll);
        screen.renderBackground(backgroundPixels,background.getWidth(),background.getHeight());
        int x0 = xScroll >> screen.getTileSizeMask();
        int x1 = (xScroll + screen.getWidth() + screen.getTileSize()) >> screen.getTileSizeMask();
        int y0 = yScroll >> screen.getTileSizeMask();
        int y1 = (yScroll + screen.getHeight()+ screen.getTileSize()) >> screen.getTileSizeMask();

        for (int y = y0; y < y1; y++) {
            for (int x = x0; x < x1; x++) {
                 getBlock(x, y).render(screen);
            }
        }
    }

    public Block getBlock(int x, int y) {
        if(x < 0 || y < 0 || x >= width || y >= height)  return Resources.AIR;
        Block block = resources.getBlockByID(tiles[x + y * width]);
        if(block == null){
           block = Resources.AIR;
        }
        block.setY(y);
        block.setX(x);
        return block;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public String[] getTiles() {
        return tiles;
    }

    public void setTiles(String[] tiles) {
        this.tiles = tiles;
    }

    public Resources getResources() {
        return resources;
    }

    public void setResources(Resources resources) {
        this.resources = resources;
    }

    public Screen getScreen() {
        return screen;
    }

    public void setScreen(Screen screen) {
        this.screen = screen;
    }

    public Keyboard getKeyboard() {
        return keyboard;
    }

    public void setKeyboard(Keyboard keyboard) {
        this.keyboard = keyboard;
    }

    public Player getClientPlayer() {
        return clientPlayer;
    }

    public void setClientPlayer(Player clientPlayer) {
        this.clientPlayer = clientPlayer;
    }

    public BufferedImage getBackground() {
        return background;
    }

    public void setBackground(BufferedImage background) {
        this.background = background;
    }
}

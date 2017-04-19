package edu.virginia.lab1test;

import edu.virginia.engine.controller.GamePad;
import edu.virginia.engine.display.Game;
import edu.virginia.engine.display.Sprite;
import edu.virginia.engine.util.GameClock;
import edu.virginia.engine.util.MusicPlayer;
import edu.virginia.engine.util.Position;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;


public class SmallGame extends Game implements MouseListener{
    static int gameWidth = 1040;
    static int gameHeight = 920;
    private boolean isstart = false;
    private boolean isPause = false;
    private boolean isend = false;
    private Sprite floor = new Sprite("Floor", "Brick.png");
    private Sprite background = new Sprite("background", "background.png");
    private Sprite platform = new Sprite("Platform", "Brick.png");
    private Sprite player = new Sprite("Player", "Protagonist.png");
    private Sprite bullet = new Sprite("Bullet", "Protagonist.png");
    private Sprite target = new Sprite("Target", "Protagonist.png");

    private ArrayList<Sprite> particle = new ArrayList<Sprite>();
    private ArrayList<Sprite> objects = new ArrayList<Sprite>();
    private ArrayList<Sprite> enemies = new ArrayList<Sprite>();
    private ArrayList<Sprite> moving = new ArrayList<Sprite>();
    private ArrayList<Sprite> moving2 = new ArrayList<Sprite>();

    public SmallGame() {
        super("SmallGame", gameWidth, gameHeight);
        player.setPosition(500, gameHeight - floor.getUnscaledHeight() - player.getUnscaledHeight());
        target.setPosition(700,gameHeight - floor.getUnscaledHeight() - target.getUnscaledHeight());
        setFloor(8, floor);
        setHorizontalPlatform(0, 0, 8, platform);
        setVerticalPlatformOnFloor(gameWidth - platform.getUnscaledWidth(), 8, platform);
        setVerticalPlatformOnFloor(0, 8, platform);
        setHorizontalPlatform(500,500,2,platform);
        setVerticalPlatform(200,600,3,platform);
    }

    public void setBackground(int number, Sprite background) {
        for (int i = 0; i < number; i++) {
            Sprite temp = new Sprite(background.getId(), background.getImageName());
            temp.setPosition(temp.getUnscaledWidth() * i, 0);
            moving2.add(temp);
        }
    }

    public void setFloor(int number, Sprite floor) {
        for (int i = 0; i < number; i++) {
            Sprite temp = new Sprite(floor.getId(), floor.getImageName());
            temp.setPosition(i * floor.getUnscaledWidth(), gameHeight - floor.getUnscaledHeight());
            objects.add(temp);
            moving.add(temp);
        }
    }

    public void setHorizontalPlatform(int x, int y, int number, Sprite Platform) {
        for (int i = 0; i < number; i++) {
            Sprite temp = new Sprite(Platform.getId(), Platform.getImageName());
            temp.setPosition(x + Platform.getUnscaledWidth() * i, y);
            objects.add(temp);
            moving.add(temp);
        }
    }

    public void setHorizontalPlaformOnFloor(int x, int number, Sprite Platform) {
        for (int i = 0; i < number; i++) {
            Sprite temp = new Sprite(Platform.getId(), Platform.getImageName());
            temp.setPosition(x + Platform.getUnscaledWidth() * i, gameHeight - floor.getUnscaledHeight() - platform.getUnscaledHeight());
            objects.add(temp);
            moving.add(temp);
        }
    }

    public void setVerticalPlatform(int x, int y, int number, Sprite Platform) {
        for (int i = 0; i < number; i++) {
            Sprite temp = new Sprite(Platform.getId(), Platform.getImageName());
            temp.setPosition(x, y - Platform.getUnscaledHeight() * (i + 1));
            objects.add(temp);
            moving.add(temp);
        }
    }

    public void setVerticalPlatformOnFloor(int x, int number, Sprite Platform) {
        for (int i = 0; i < number; i++) {
            Sprite temp = new Sprite(Platform.getId(), Platform.getImageName());
            temp.setPosition(x, gameHeight - floor.getUnscaledHeight() - Platform.getUnscaledHeight() * (i + 1));
            objects.add(temp);
            moving.add(temp);
        }
    }

    public void setDiagonalPlatform(int x, int y, int number, boolean isRight, Sprite Platform) {
        int tempnum = -1;
        if (isRight) {
            tempnum = 1;
        }
        for (int i = 0; i < number; i++) {
            Sprite temp = new Sprite(Platform.getId(), Platform.getImageName());
            temp.setPosition(x + tempnum * i * temp.getUnscaledWidth(), y - Platform.getUnscaledHeight() * (i + 1));
            objects.add(temp);
            moving.add(temp);
        }
    }

    public void setDiagonalPlatformOnFloor(int x, int number, boolean isRight, Sprite Platform) {
        int tempnum = -1;
        if (isRight) {
            tempnum = 1;
        }
        for (int i = 0; i < number; i++) {
            Sprite temp = new Sprite(Platform.getId(), Platform.getImageName());
            temp.setPosition(x + tempnum * i * temp.getUnscaledWidth(), gameHeight - floor.getUnscaledHeight() - Platform.getUnscaledHeight() * (i + 1));
            objects.add(temp);
            moving.add(temp);
        }
    }

    @Override
    public void update(ArrayList<String> pressedKeys, ArrayList<GamePad> gamePads) {
        if (!isend) {
            if (isstart) {
                super.update(pressedKeys, gamePads);
                player.update(pressedKeys, gamePads);
                player.rePosition(player.getVelX(), player.getVelY());
                bullet.rePosition(bullet.getVelX(), bullet.getVelY());


                for (Sprite s : objects) {
                    if (s.isVisible()) {
                        if (bullet.collidesWith(s, bullet.getVelX(), bullet.getVelY())) {
                            int xscenter = s.getPosX() + s.getUnscaledWidth() / 2;
                            int yscenter = s.getPosY() + s.getUnscaledHeight() / 2;
                            int xbulletcenter = bullet.getPosX() + bullet.getUnscaledWidth() / 2;
                            int ybulletcenter = bullet.getPosY() + bullet.getUnscaledHeight() / 2;
                            int xdif = xbulletcenter - xscenter;
                            int ydif = ybulletcenter - yscenter;
                            // top
                            if (ydif < 0 && Math.abs(ydif) > Math.abs(xdif)) {
                                bullet.setVelY(-(bullet.getVelY()));
                                System.out.println("top");
                                break;
                            }
                            // Left side
                            if (xdif < 0 && Math.abs(ydif) < Math.abs(xdif)) {
                                bullet.setVelX(-(bullet.getVelX()));
                                System.out.println("left");
                                break;
                            }
                            // Right side
                            if (xdif > 0 && Math.abs(ydif) < Math.abs(xdif)) {
                                bullet.setVelX(-(bullet.getVelX()));
                                System.out.println("right");
                                break;

                            }
                            // Below
                            if (ydif > 0 && Math.abs(ydif) > Math.abs(xdif)) {
                                bullet.setVelY(-(bullet.getVelY()));
                                System.out.println("below");
                                break;

                            }
                        }

                    }
                }

                if (bullet.collidesWith(target, bullet.getVelX(), bullet.getVelY())) {
                    isend = true;
                }
            }
        }


    }

    public void keyPressed(KeyEvent e) {

        if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            player.setVelX(-15);

        }
        if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            player.setVelX(15);

        }
        if (e.getKeyCode() == KeyEvent.VK_SPACE){
            if(isstart) {
                if (!isPause) {
                    this.pause();
                    isPause = true;
                } else {
                    this.start();
                    isPause = false;
                }
            }else{
                isstart = true;
            }
        }
        if (e.getKeyCode() == KeyEvent.VK_ESCAPE){
            this.exitGame();
        }

    }

    public void keyReleased(KeyEvent e) {
        player.setVelX(0);
    }


    private boolean finishshooting = false;

    @Override
    public void mouseClicked(MouseEvent e){
            bullet.setPosition(player.getPosition());
            int xdif = e.getX() - (player.getPosX()+player.getUnscaledWidth()/2);
            int ydif = e.getY() - (player.getPosY()+player.getUnscaledHeight()/2);
            System.out.println(xdif);
            System.out.println(ydif);
            double distance = Math.sqrt(xdif*xdif+ydif*ydif);
            double x = xdif / distance;
            double y = ydif / distance;
            int truex = (int)(x*15);
            int truey = (int)(y*15);
            bullet.setVelX(truex);
            bullet.setVelY(truey);
            finishshooting = true;

    }

    @Override
    public void draw(Graphics g) {
        if (isend){
            g.drawString("game ends", gameWidth/2,gameHeight/2);
        }
        else {
            if (isstart) {
                super.draw(g);

                bullet.draw(g);
                target.draw(g);
                for (Sprite p : objects) {
                    if (p.isVisible()) {
                        p.draw(g);
                    }
                }
                player.draw(g);

            } else {
                g.drawString("press space to start", gameWidth / 2, gameHeight / 2);
            }
        }
    }

    public static void main(String[] args) {
        SmallGame game = new SmallGame();
        game.start();

    }
}

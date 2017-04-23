package edu.virginia.lab1test;

import edu.virginia.engine.controller.GamePad;
import edu.virginia.engine.display.Game;
import edu.virginia.engine.display.Sprite;
import edu.virginia.engine.util.Position;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

/**
 * serve ball using key
 */
public class SmallGame extends Game implements MouseListener {
    static int gameWidth = 1040;
    static int gameHeight = 920;
    private boolean isstart = false;
    private boolean isPause = false;
    private boolean isend = false;
    private int p1score;
    private int p2score;
    private Sprite floor = new Sprite("Floor", "Brick.png");
    private Sprite platform = new Sprite("Platform", "Brick.png");
    private Sprite player1 = new Sprite("Player1", "pong.png");
    private Sprite player2 = new Sprite("Player2", "pong.png");

    private Sprite bullet = new Sprite("Bullet", "sphere.jpeg");
    private Sprite p1plat = new Sprite("p1plat", "Brick.png");
    private Sprite p2plat = new Sprite("p2plat", "Brick.png");

    private ArrayList<Sprite> objects = new ArrayList<Sprite>();
    private ArrayList<Sprite> moving = new ArrayList<Sprite>();
    private ArrayList<Sprite> moving2 = new ArrayList<Sprite>();
    private ArrayList<Sprite> player = new ArrayList<Sprite>();

    public SmallGame() {
        super("SmallGame", gameWidth, gameHeight);
        player1.setPosition(500, gameHeight - floor.getUnscaledHeight() - player1.getUnscaledHeight());
        player2.setPosition(500, floor.getUnscaledHeight());
        //bullet.setPosition(player1.getPosition());
        setMid(bullet, player1, false);
        objects.add(player1);
        objects.add(player2);
        //setFloor(8, p2plat);
        setHorizontalPlatform(0, gameHeight - p2plat.getUnscaledHeight() / 2, 8, p2plat);
        setHorizontalPlatform(0, -(p1plat.getUnscaledHeight() / 2), 8, p1plat);
        setVerticalPlatformOnFloor(gameWidth, 8, platform);
        setVerticalPlatformOnFloor(0 - platform.getUnscaledWidth(), 8, platform);
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


    public void gamePadUpdate(ArrayList<GamePad> gamePads) {
        for (GamePad pad : gamePads) {

            if (pad.getLeftStickXAxis() < -0.5) {
                player2.setVelX(-15);
            } else if (pad.getLeftStickXAxis() > 0.5) {
                player2.setVelX(15);
            } else {
                player2.setVelX(0);
            }

            if (pad.isButtonPressed(GamePad.BUTTON_CROSS)) {
                if (serving) {
                    if (player1serve) {
                    } else {
                        bullet.setVelX(player2.getVelX());
                        bullet.setVelY(20);
                        serving = false;
                        player1serve = true;
                    }
                }
            }
        }
    }

    public void checkbound(Sprite s) {
        Position p1 = s.getPosition();
        s.setBounce(true);
        if (p1.getX() <= 0) s.setPosition(0, p1.getY());
        else if (p1.getX() >= gameWidth - s.getScaledWidth())
            s.setPosition(gameWidth - s.getScaledWidth() - 1, p1.getY());
        else if (p1.getY() <= 0) s.setPosition(p1.getX(), 0);
        else if (p1.getY() >= gameHeight - s.getScaledHeight() - 1) {
            s.setPosition(p1.getX(), gameHeight - s.getScaledHeight());
        } else {
            s.setBounce(false);
        }
    }

    public void collision() {
        for (Sprite s : objects) {
            if (s.isVisible()) {
                if (bullet.collidesWith(s, bullet.getVelX(), bullet.getVelY())) {

                    if (s.getId() == "Player1") {
                        bullet.setVelY(-(bullet.getVelY()));
                        bullet.setVelX(bullet.getVelX() + s.getVelX());
                        System.out.println("hit p1");
                        p1ishit = true;

                        break;

                    }
                    if (s.getId() == "Player2") {
                        bullet.setVelY(-(bullet.getVelY()));
                        bullet.setVelX(bullet.getVelX() + s.getVelX());
                        System.out.println("hit p2");
                        break;

                    }

                    if (s.getId() == "p1plat") {
                        p2score++;
                        //bullet.setPosition(player2.getPosX(), player2.getPosY() + player2.getUnscaledHeight() + 5);
                        setMid(bullet, player2, true);
                        player1serve = false;
                        bullet.setVelX(0);
                        bullet.setVelY(0);
                        p1follow = false;
                        System.out.println("p2 score");
                        serving = true;
                        break;
                    }
                    if (s.getId() == "p2plat") {
                        p1score++;
                        // bullet.setPosition(player1.getPosX(), player1.getPosY() - player1.getUnscaledWidth() - 5);
                        setMid(bullet, player1, false);
                        player1serve = true;
                        bullet.setVelX(0);
                        bullet.setVelY(0);
                        p1follow = true;
                        serving = true;
                        System.out.println("p1 score");

                        break;
                    }


                    int xscenter = s.getPosX() + s.getUnscaledWidth() / 2;
                    int yscenter = s.getPosY() + s.getUnscaledHeight() / 2;
                    int xbulletcenter = bullet.getPosX() + bullet.getUnscaledWidth() / 2;
                    int ybulletcenter = bullet.getPosY() + bullet.getUnscaledHeight() / 2;
                    int xdif = xbulletcenter - xscenter;
                    int ydif = ybulletcenter - yscenter;

                    // Left side
                    if (xdif < 0 && Math.abs(ydif) < Math.abs(xdif)) {
                        bullet.setVelX(-(bullet.getVelX()));
                        break;
                    }
                    // Right side
                    if (xdif > 0 && Math.abs(ydif) < Math.abs(xdif)) {
                        bullet.setVelX(-(bullet.getVelX()));
                        break;

                    }

                }

            }
        }
    }

    private boolean p1ishit = false;
    private boolean p2ishit = false;
    private boolean p1follow = true;
    private boolean serving = true;

    @Override
    public void update(ArrayList<String> pressedKeys, ArrayList<GamePad> gamePads) {
        if (!isend) {
            if (isstart) {
                player1.rePosition(player1.getVelX(), player1.getVelY());
                player2.rePosition(player2.getVelX(), player2.getVelY());
                bullet.rePosition(bullet.getVelX(), bullet.getVelY());
                checkbound(player1);
                checkbound(player2);
                if (serving) {
                    if (p1follow && !player1.isBounce()) {
                        bullet.rePosition(bullet.getVelX() + player1.getVelX(), bullet.getVelY() + player1.getVelY());
                    } else if (!p1follow && !player2.isBounce()) {
                        bullet.rePosition(bullet.getVelX() + player2.getVelX(), bullet.getVelY() + player2.getVelY());
                    } else {

                    }
                }

                gamePadUpdate(gamePads);
                collision();

            }
        }



    }


    public void keyPressed(KeyEvent e) {


        if (isstart) {
            if (e.getKeyCode() == KeyEvent.VK_LEFT) {
                player1.setVelX(-15);
            } else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
                player1.setVelX(15);

            } else {
                player1.setVelX(0);
            }

            if (e.getKeyCode() == KeyEvent.VK_UP) {

                if (player1serve) {

                    bullet.setVelX(player1.getVelX());
                    bullet.setVelY(-20);
                    serving = false;
                    player1serve = false;

                } else {
                }


            }

        }

        if (e.getKeyCode() == KeyEvent.VK_SPACE) {
            if (isstart) {
                if (!isPause) {
                    this.pause();
                    isPause = true;
                } else {
                    this.start();
                    isPause = false;
                }

            } else {
                isstart = true;
            }
        }

        if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
            this.exitGame();
        }

    }


    public void keyReleased(KeyEvent e) {
        player1.setVelX(0);
    }

    private boolean player1serve = true;

    public void setMid(Sprite a, Sprite b, boolean isbelow) {
        if (isbelow) {
            a.setPosition(b.getPosX() + b.getUnscaledWidth() / 2 - a.getUnscaledWidth() / 2, b.getPosY() + a.getUnscaledHeight() + 10);
        } else {
            a.setPosition(b.getPosX() + b.getUnscaledWidth() / 2 - a.getUnscaledWidth() / 2, b.getPosY() - a.getUnscaledHeight() - 10);
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (player1serve) {
            bullet.setPosition(player1.getPosX(), player1.getPosY() - 5 - player1.getUnscaledHeight());
            int xdif = e.getX() - (player1.getPosX() + player1.getUnscaledWidth() / 2);
            int ydif = e.getY() - (player1.getPosY() + player1.getUnscaledHeight() / 2);
            double distance = Math.sqrt(xdif * xdif + ydif * ydif);
            double x = xdif / distance;
            double y = ydif / distance;
            int truex = (int) (x * 15);
            int truey = (int) (y * 15);
            bullet.setVelX(truex);
            bullet.setVelY(truey);
            serving = false;
        } else {
            bullet.setPosition(player2.getPosX(), player2.getPosY() + 5 + player2.getUnscaledHeight());
            int xdif = e.getX() - (player2.getPosX() + player2.getUnscaledWidth() / 2);
            int ydif = e.getY() - (player2.getPosY() + player2.getUnscaledHeight() / 2);
            double distance = Math.sqrt(xdif * xdif + ydif * ydif);
            double x = xdif / distance;
            double y = ydif / distance;
            int truex = (int) (x * 15);
            int truey = (int) (y * 15);
            bullet.setVelX(truex);
            bullet.setVelY(truey);
            serving = false;
        }

    }

    @Override
    public void draw(Graphics g) {
        if (p1score == 11 || p2score == 11) {
            if (p1score == 11) {
                g.drawString("player 1 wins, press ESC to exit game", gameWidth / 2 - 150, gameHeight / 2);

            } else if (p2score == 11) {
                g.drawString("player 2 wins press ESC to exit game", gameWidth / 2 - 150, gameHeight / 2);
            }
        } else {
            if (isstart) {
                super.draw(g);

                bullet.draw(g);
                for (Sprite p : objects) {
                    if (p.isVisible()) {
                        p.draw(g);
                    }
                }
                player1.draw(g);
                player2.draw(g);
                g.drawString("player 1 score " + p1score, gameWidth / 2 - 50, gameHeight / 2);
                g.drawString("player 2 score " + p2score, gameWidth / 2 - 50, gameHeight / 2 + 20);

                if (isPause) {
                    g.drawString("pasued ", gameWidth / 2, gameHeight / 2 + 40);
                }
            } else {
                g.drawString("press space to start", gameWidth / 2 - 50, gameHeight / 2);
            }
        }
    }

    public static void main(String[] args) {
        SmallGame game = new SmallGame();
        game.start();

    }
}

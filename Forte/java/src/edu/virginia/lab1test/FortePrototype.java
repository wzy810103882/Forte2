package edu.virginia.lab1test;

import edu.virginia.engine.controller.GamePad;
import edu.virginia.engine.display.DisplayObject;
import edu.virginia.engine.display.Game;
import edu.virginia.engine.display.Sprite;
import edu.virginia.engine.events.Event;
import edu.virginia.engine.events.IEventListener;
import edu.virginia.engine.util.GameClock;
import edu.virginia.engine.util.MusicPlayer;
import edu.virginia.engine.util.Position;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

//Gannon was here.
public class FortePrototype extends Game implements IEventListener {
    static int gravity = 2;
    boolean falling = false;
    static int gameWidth = 1000;
    static int gameHeight = 900;
    boolean collision = false;
    boolean plat_top = false;
    boolean plat_down = false;
    boolean bullet_ready = true;
    private Sprite floor = new Sprite("Floor", "matt3.png");
    private Sprite background = new Sprite("background", "bg.png");
    private Sprite bossBackground = new Sprite("bossBackground", "bg.png");
    private Sprite boss = new Sprite("boss", "Protagonist.png");
    private Sprite platform = new Sprite("Platform", "matt3.png");
    private Sprite player = new Sprite("Player", "Protagonist.png");
    private Sprite door = new Sprite("door", "door.png");
    private Sprite bossFloor = new Sprite("Floor", "matt3.png");
    private boolean isStart = false;
    private boolean isEnd = false;
    private boolean BossEncountered = false;

    private ArrayList<Sprite> particle = new ArrayList<Sprite>();
    private ArrayList<Sprite> bossObject = new ArrayList<Sprite>();
    private ArrayList<Sprite> objects = new ArrayList<Sprite>();
    private ArrayList<Sprite> enemies = new ArrayList<Sprite>();
    private ArrayList<Sprite> moving = new ArrayList<Sprite>();
    private ArrayList<Sprite> moving2 = new ArrayList<Sprite>();

    Sprite C = new Sprite("C", "C.png");
    Sprite D = new Sprite("D", "D.png");
    Sprite E = new Sprite("E", "E.png");
    Sprite F = new Sprite("F", "F.png");

    Cevent cevent = new Cevent(Cevent.Cevent, C);
    Devent devent = new Devent(Devent.Devent, D);
    Eevent eevent = new Eevent(Eevent.Eevent, E);
    Fevent fevent = new Fevent(Fevent.Fevent, F);

    BossEvent bevent = new BossEvent(BossEvent.BossEvent, door);
    BossEvent bossCompleteEvent = new BossEvent(BossEvent.BossEvent, boss);
    MusicPlayer sfx = new MusicPlayer();
    MusicPlayer pC = new MusicPlayer();
    MusicPlayer pG = new MusicPlayer();
    MusicPlayer pA = new MusicPlayer();
    MusicPlayer pD = new MusicPlayer();

    boolean c = false;
    boolean g = false;
    boolean a = false;
    boolean d = false;

    private GameClock mainClock = new GameClock();
    private QuestManager QuestManager = new QuestManager(C, D, E, F, sfx, mainClock);

    public FortePrototype() {
        super("Forte Prototype", gameWidth, gameHeight);
        // player.setPosition(500, gameHeight - floor.getUnscaledHeight() - player.getUnscaledHeight());
        player.setPosition(50, 700);
        C.setPosition(260, gameHeight - floor.getUnscaledHeight() - C.getUnscaledHeight());
        D.setPosition(700, gameHeight - floor.getUnscaledHeight() - D.getUnscaledHeight());
        E.setPosition(780, gameHeight - floor.getUnscaledHeight() - E.getUnscaledHeight());
        F.setPosition(910, gameHeight - floor.getUnscaledHeight() - F.getUnscaledHeight());

        C.addEventListener(QuestManager, Cevent.Cevent);
        D.addEventListener(QuestManager, Devent.Devent);
        E.addEventListener(QuestManager, Eevent.Eevent);
        F.addEventListener(QuestManager, Fevent.Fevent);

        door.addEventListener(this, BossEvent.BossEvent);
        boss.addEventListener(this, BossEvent.BossEvent);
        //  ArrayList<Integer> CxArray = Arrays.asList(500,2000,4000);

        int[] CxArray = {1500, 1980};
        int[] CyArray = {gameHeight - 175, 410};

        int[] DxArray = {1600, 2100};
        int[] DyArray = {gameHeight - 175, gameHeight - 175};

        int[] ExArray = {1700, 2200};
        int[] EyArray = {gameHeight - 175, gameHeight - 175};

        int[] FxArray = {1800, 2300};
        int[] FyArray = {gameHeight - 175, gameHeight - 175};

        C.setxArray(CxArray);
        C.setyArray(CyArray);
        D.setxArray(DxArray);
        D.setyArray(DyArray);
        E.setxArray(ExArray);
        E.setyArray(EyArray);
        F.setxArray(FxArray);
        F.setyArray(FyArray);

        enemies.add(C);
        enemies.add(D);
        enemies.add(E);
        enemies.add(F);
        moving.add(player);

        /**
         * platform positions
         */

        platformSetup();
        mainClock.resetGameClock();


    }

    public void platformSetup() {
        /**
         * -200 is reference floor
         * -300 first tier
         * -900 is the top
         */

        setFloor(110, floor);
        setBackground(20, background);

        setHorizontalPlaformOnFloor(800, 3, platform);
        setHorizontalPlatform(900, gameHeight - 300, 2, platform);
        setHorizontalPlatform(1000, gameHeight - 400, 1, platform);

        setHorizontalPlatform(1500, gameHeight - 400, 4, platform);

        setHorizontalPlatform(1900, gameHeight - 600, 3, platform);

        setVerticalPlatformOnFloor(2000, 2, platform);

        setVerticalPlatformOnFloor(2300, 2, platform);
        setVerticalPlatformOnFloor(2400, 2, platform);
        setVerticalPlatformOnFloor(2500, 2, platform);
        setVerticalPlatformOnFloor(2600, 2, platform);

        setHorizontalPlaformOnFloor(3000, 10, platform);
        setHorizontalPlatform(3000, gameHeight - 300, 8, platform);
        setHorizontalPlatform(3000, gameHeight - 400, 6, platform);
        setHorizontalPlatform(3300, gameHeight - 500, 3, platform);
        setHorizontalPlatform(3400, gameHeight - 600, 2, platform);
        setHorizontalPlatform(3400, gameHeight - 700, 2, platform);

        setVerticalPlatformOnFloor(4400, 1, platform);
        setVerticalPlatformOnFloor(4600, 2, platform);
        setVerticalPlatformOnFloor(4800, 3, platform);
        setVerticalPlatformOnFloor(5000, 4, platform);
        setVerticalPlatformOnFloor(5200, 5, platform);
        setVerticalPlatformOnFloor(5400, 2, platform);


        setHorizontalPlatform(5700, gameHeight - 500, 4, platform);
        setVerticalPlatform(6000, gameHeight - 600, 2, platform);

        setHorizontalPlatform(6400, gameHeight - 700, 2, platform);
        setVerticalPlatformOnFloor(6700, 3, platform);
        setHorizontalPlatform(6800, gameHeight - 700, 2, platform);

        setHorizontalPlatform(7200, gameHeight - 700, 1, platform);
        setHorizontalPlatform(7500, gameHeight - 700, 1, platform);

        setVerticalPlatformOnFloor(7800, 3, platform);
        setVerticalPlatform(7800, gameHeight - 600, 3, platform);


        setVerticalPlatform(8100, gameHeight - 500, 1, platform);
        setVerticalPlatform(8300, gameHeight - 500, 1, platform);
        setVerticalPlatform(8500, gameHeight - 500, 1, platform);
        setVerticalPlatform(8700, gameHeight - 500, 1, platform);

        setVerticalPlatformOnFloor(8800, 3, platform);
        setVerticalPlatformOnFloor(8900, 5, platform);
        setVerticalPlatformOnFloor(9100, 3, platform);
        setVerticalPlatformOnFloor(9200, 2, platform);
        setVerticalPlatformOnFloor(9300, 2, platform);
        setVerticalPlatformOnFloor(9400, 1, platform);
        setVerticalPlatformOnFloor(9500, 1, platform);

        setVerticalPlatformOnFloor(10000, 10, platform);
        setVerticalPlatformOnFloor(10100, 10, platform);
        setVerticalPlatformOnFloor(10200, 10, platform);
        setVerticalPlatformOnFloor(10300, 10, platform);
        setVerticalPlatformOnFloor(10400, 10, platform);

        door.setPosition(9800, gameHeight - 300);
        moving2.add(door);
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

    public void setBoosFloor(int number, Sprite floor) {
        for (int i = 0; i < number; i++) {
            Sprite temp = new Sprite(floor.getId(), floor.getImageName());
            temp.setPosition(i * floor.getUnscaledWidth(), gameHeight - floor.getUnscaledHeight());
            bossObject.add(temp);
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

    public void timing(int start, int finish, Sprite temp) {

        //  BufferedImage image = new BufferedImage(2,2,1);
        if (temp.isPrevChange()) {
            temp.setPrev(temp.getDisplayImage());
            temp.setPrevChange(false);
        }
        //  if (bol) {
        //      image = temp.getDisplayImage();
        //  }
        if (mainClock.getElapsedTime() > start && mainClock.getElapsedTime() < start + 500) {
            temp.setImage("Flash.png");
            temp.setStart(start);
            temp.setFinish(finish);
            //bol = false;
        }

        if (mainClock.getElapsedTime() > finish && mainClock.getElapsedTime() < finish + 500) {
            temp.setImage(temp.getPrev());
        }

    }

    public void timingMode(int start, int finish, Sprite temp) {

        //  BufferedImage image = new BufferedImage(2,2,1);
        if (temp.isPrevChange()) {
            temp.setPrev(temp.getDisplayImage());
            temp.setPrevChange(false);
        }
        //  if (bol) {
        //      image = temp.getDisplayImage();
        //  }
        if (mainClock.getElapsedTime() % 2000 > start && (mainClock.getElapsedTime() % 2000) < start + 100) {
            temp.setImage("Flash.png");
            temp.setStart(start);
            temp.setFinish(finish);
            //bol = false;
        }

        if (mainClock.getElapsedTime() % 2000 > finish && (mainClock.getElapsedTime() % 2000) < finish + 100) {
            temp.setImage(temp.getPrev());
        }

    }

    public void gamePadUpdate(ArrayList<GamePad> gamePads) {
        for (GamePad pad : gamePads) {

            if (pad.getLeftStickXAxis() > 0.9) {
                player.setVelX(8);
            } else if (pad.getLeftStickXAxis() < -0.9) {
                player.setVelX(-8);
            } else {
                player.setVelX(0);

            }


            if (pad.isButtonPressed(GamePad.BUTTON_CROSS)) {
                if (!falling) {
                    player.setVelY(-37);
                    falling = true;
                    //  sfx.playSong("bass1.wav", 0);

                }
            }

            if (pad.isButtonPressed(GamePad.BUTTON_SQUARE)) {
                if (bullet_ready) {
                    //  sfx.playSong("bass2.wav", -15);
                    Sprite bullet = new Sprite("bullet", "music_note2.png");
                    bullet.setPosition(player.getPosition());
                    bullet.setVelY(-20);
                    bullet.setVelX(-30);
                    particle.add(bullet);
                    falling = true;
                    bullet_ready = false;
                }
            } else if (pad.isButtonPressed(GamePad.BUTTON_CIRCLE)) {
                if (bullet_ready) {
                    // player.setVelY(-30);
                    // player.setVelX(40);
                    //  sfx.playSong("bass2.wav", -15);
                    Sprite bullet = new Sprite("bullet", "music_note2.png");
                    bullet.setPosition(player.getPosition());
                    bullet.setVelY(-20);
                    bullet.setVelX(30);
                    particle.add(bullet);
                    falling = true;
                    bullet_ready = false;
                }
            } else {
                bullet_ready = true;
            }
        }

    }

    public void mapBound() {
        Position p1 = player.getPosition();
        if (p1.getX() <= 0) player.setPosition(0, p1.getY());
        if (p1.getX() >= gameWidth - player.getScaledWidth())
            player.setPosition(gameWidth - player.getScaledWidth() - 1, p1.getY());
        if (p1.getY() <= 0) player.setPosition(p1.getX(), 0);
        if (p1.getY() >= gameHeight - player.getScaledHeight() - 1) {
            player.setPosition(p1.getX(), gameHeight - player.getScaledHeight());
            falling = false;
        }
    }

    public void platformCollision(ArrayList<Sprite> objects) {
        for (Sprite s : objects) {
            if (s.isVisible()) {
                if (player.collidesWith(s, player.getVelX(), player.getVelY())) {
                    collision = true;
                    int xscenter = s.getPosX() + s.getUnscaledWidth() / 2;
                    int yscenter = s.getPosY() + s.getUnscaledHeight() / 2;
                    int xbulletcenter = player.getPosX() + player.getUnscaledWidth() / 2;
                    int ybulletcenter = player.getPosY() + player.getUnscaledHeight() / 2;
                    int xdif = xbulletcenter - xscenter;
                    int ydif = ybulletcenter - yscenter;



                    // top
                    if (ydif < 0 && Math.abs(ydif) > Math.abs(xdif)) {
                        //player.setVelY(-(player.getVelY()));
                        falling = false;
                        plat_top = true;
                        plat_down = false;
                        player.setPosition(player.getPosX(), s.getPosY() - player.getScaledHeight() - 1);
                        player.setVelY(0);
                        //  System.out.println("top");
                        break;
                    }


                    if (s.getId() == "Floor"){
                        falling = false;
                        plat_top = true;
                        plat_down = false;
                        player.setPosition(player.getPosX(), s.getPosY() - player.getScaledHeight() - 1);
                        break;
                    }


                    // Right side
                    if (xdif > 0 && Math.abs(ydif) < Math.abs(xdif)) {
                        player.setPosition(s.getPosX() + s.getUnscaledWidth() + 1, player.getPosY());
                        falling = true;
                        plat_top = false;
                        plat_down = false;
                        setVelY(0);
                        break;
                        //  System.out.println("right");

                    }

                    // Left side
                    if (xdif < 0 && Math.abs(ydif) < Math.abs(xdif)) {
                        player.setPosition(s.getPosX() - player.getScaledWidth() - 1, player.getPosY());
                        falling = true;
                        plat_top = false;
                        plat_down = false;
                        setVelY(0);
                        break;
                        // System.out.println("left");
                    }


                    // Below
                    if (ydif > 0 && Math.abs(ydif) > Math.abs(xdif)) {
                        player.setPosition(player.getPosX(), s.getPosY() + s.getScaledHeight() + 1);
                        falling = true;
                        plat_top = false;
                        plat_down = true;
                        setVelY(0);
                        //  System.out.println("below");
                        break;

                    }



                } else {
                    collision = false;
                    falling = true;
                }
            }
        }
    }

    public void enemyCollision() {
        for (Sprite e : enemies) {
            if (e.isVisible()) {
                if (player.nearby(e)) {
                    if (player.collidesWith(e, player.getVelX(), player.getVelY())) {

                        if (e.getId() == "C") {
                            C.dispatchEvent(cevent);
                        } else if (e.getId() == "D") {
                            D.dispatchEvent(devent);

                        } else if (e.getId() == "E") {
                            E.dispatchEvent(eevent);

                        } else if (e.getId() == "F") {
                            F.dispatchEvent(fevent);
                        } else {
                            ;
                        }
                    }

                }
            }
        }
    }



    public void scrolling() {

        if (!collision) {
            if (!(player.getPosX() < gameWidth / 2)) {
                if (player.getVelX() > 0) {
                    for (Sprite p : moving) {
                        p.setPosition(p.getPosX() - player.getVelX(), p.getPosY());
                    }
                    for (Sprite p : moving2) {
                        p.setPosition(p.getPosX() - player.getVelX(), p.getPosY());
                    }
                    for (Sprite p : enemies) {
                        p.setPosition(p.getPosX() - player.getVelX(), p.getPosY());
                    }
                }
            }
        } else {
            if (plat_top || plat_down) {
                if (!(player.getPosX() < gameWidth / 2)) {
                    if (player.getVelX() > 0) {
                        for (Sprite p : moving) {
                            p.setPosition(p.getPosX() - player.getVelX(), p.getPosY());
                        }
                        for (Sprite p : moving2) {
                            p.setPosition(p.getPosX() - player.getVelX(), p.getPosY());
                        }
                        for (Sprite p : enemies) {
                            p.setPosition(p.getPosX() - player.getVelX(), p.getPosY());
                        }
                    }
                }
            }
        }
    }

    public void attack() {
        ArrayList<Sprite> toRemove = new ArrayList<Sprite>();
        for (Sprite p : particle) {
            p.rePosition(p.getVelX(), p.getVelY());
            p.setVelY(p.getVelY() + (int) gravity);

            for (Sprite o : objects){
                if (o.isVisible()){
                    if (o.nearby(p)){
                        if (o.collidesWith(p,o.getVelX(),o.getVelY())){
                            toRemove.add(p);
                            p.setVisible(false);
                          //  sfx.playSong("Error.wav",-20);
                        }
                    }
                }
            }

            for (Sprite e : enemies) {
                if (e.isVisible()) {
                    if (e.nearby(p)) {
                        if (e.collidesWith(p, e.getVelX(), e.getVelY())) {
                            toRemove.add(p);
                            falling = true;
                            if (e.getId() == "C") {
                                C.dispatchEvent(cevent);
                            }
                            if (e.getId() == "D") {
                                D.dispatchEvent(devent);
                            }

                            if (e.getId() == "E") {
                                E.dispatchEvent(eevent);
                            }
                            if (e.getId() == "F") {
                                F.dispatchEvent(fevent);
                            }

                        }
                    }
                }
            }
        }
        particle.removeAll(toRemove);
    }

    @Override
    public void update(ArrayList<String> pressedKeys, ArrayList<GamePad> gamePads) {


        if (isStart) {
            if (!isEnd) {
                if (!BossEncountered) {

/**
 * controller
 */
                    super.update(pressedKeys, gamePads);
                    /**
                     * speed and gravity reposition for the player
                     */
                    player.rePosition(player.getVelX(), player.getVelY());
                    if (falling) player.setVelY(player.getVelY() + (int) gravity);

                    gamePadUpdate(gamePads);

                    mapBound();

                    platformCollision(objects);

                    enemyCollision();
                    timingMode(0, 1000, C);
                    timingMode(0, 1000, D);
                    timingMode(0, 1000, E);
                    timingMode(0, 1000, F);
                    attack();
                    scrolling();

                    if (player.collidesWith(door, player.getVelX(), player.getVelY())) {
                        door.dispatchEvent(bevent);
                    }
                } else {

                    super.update(pressedKeys, gamePads);
                    player.rePosition(player.getVelX(), player.getVelY());
                    if (falling) player.setVelY(player.getVelY() + (int) gravity);

                    gamePadUpdate(gamePads);
                    mapBound();
                    attack();
                    platformCollision(bossObject);
                    if (player.collidesWith(boss, player.getVelX(), player.getVelY())) {
                        boss.dispatchEvent(bossCompleteEvent);
                    }

                }



            }
            else {
                for (GamePad pad : gamePads) {
                    if (pad.isButtonPressed(GamePad.BUTTON_TRIANGLE)){
                        exitGame();
                    }
                }
            }
        } else {
            for (GamePad pad : gamePads) {
                if (pad.isButtonPressed(GamePad.BUTTON_TRIANGLE)) {
                    this.start();
                    isStart = true;
                    isEnd = false;
                    BossEncountered = false;
                }
            }
        }
    }


    public void keyPressed(KeyEvent e) {
        if (!falling) {
            if (e.getKeyCode() == KeyEvent.VK_UP) {
                player.setVelY(-37);
                falling = true;
                sfx.playSong("bass1.wav", 0);

            }
        }

        if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            player.setVelX(-15);

        }
        if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            player.setVelX(15);

        }

        if (e.getKeyCode() == KeyEvent.VK_A) {
            //player.setVelY(-30);
            //player.setVelX(-40);
            sfx.playSong("bass2.wav", -15);
            Sprite bullet = new Sprite("bullet", "music_note.png");
            bullet.setPosition(player.getPosition());
            bullet.setVelY(-20);
            bullet.setVelX(-30);
            particle.add(bullet);
            falling = true;
        }

        if (e.getKeyCode() == KeyEvent.VK_S) {
            // player.setVelY(-30);
            // player.setVelX(40);
            sfx.playSong("bass2.wav", -15);
            Sprite bullet = new Sprite("bullet", "music_note.png");
            bullet.setPosition(player.getPosition());
            bullet.setVelY(-20);
            bullet.setVelX(30);
            particle.add(bullet);
            falling = true;
        }

    }

    public void keyReleased(KeyEvent e) {
        player.setVelX(0);
    }

    public boolean inRange(Sprite p) {
        if (p.getPosY() > gameHeight) {
            return false;
        }
        if (p.getPosX() > gameWidth) {
            return false;
        }
        if (p.getPosX() + p.getUnscaledWidth() < 0) {
            return false;
        }
        if (p.getPosY() + p.getUnscaledHeight() < 0) {
            return false;
        }
        return true;
    }

    @Override
    public void draw(Graphics g) {
        if (isStart) {
            if (!isEnd) {
                if (!BossEncountered) {
                    super.draw(g);
                    for (Sprite p : moving2) {
                        if (p.isVisible()) {
                            if (inRange(p)) {
                                p.draw(g);
                            }
                        }
                    }
                    for (Sprite p : objects) {
                        if (p.isVisible()) {
                            if (inRange(p)) {
                                p.draw(g);
                            }
                        }
                    }

                    if (door != null) door.draw(g);
                    if (C != null) C.draw(g);
                    if (D != null) D.draw(g);
                    if (E != null) E.draw(g);
                    if (F != null) F.draw(g);

                    if (player != null) player.draw(g);
                    for (Sprite temp : particle) {
                        if (temp.isVisible()) {
                            if (inRange(temp)) {
                                temp.draw(g);
                            }
                        }
                    }
                } else {
                    bossBackground.draw(g);
                    for (Sprite temp : bossObject) {
                        if (temp.isVisible()) {
                            if (inRange(temp)) {
                                temp.draw(g);
                            }
                        }
                    }
                    boss.draw(g);
                    player.draw(g);
                    for (Sprite p: particle){
                        if (p.isVisible()) {
                            if (inRange(p)) {
                                p.draw(g);
                            }
                        }
                    }

                }
            } else {
                g.drawString("GAME OVER", gameWidth / 2, gameHeight / 2);
            }
        } else {

            g.drawString("Forte", gameWidth / 2, gameHeight / 2);
            g.drawString("press Y to start", gameWidth / 2, gameHeight / 2 + 50);
        }

    }

    public boolean getmute(int number) {
        if (number == 1) {
            return QuestManager.isCismute();
        } else if (number == 2) {
            return QuestManager.isDismute();
        } else if (number == 3) {
            return QuestManager.isEismute();
        } else {
            return QuestManager.isFismute();
        }

    }

    public void setpC(MusicPlayer pC) {
        this.pC = pC;
    }

    public void setpG(MusicPlayer pG) {
        this.pG = pG;
    }

    public void setpA(MusicPlayer pA) {
        this.pA = pA;
    }

    public void setpD(MusicPlayer pD) {
        this.pD = pD;
    }

    public void setC(boolean c) {
        this.c = c;
    }

    public void setG(boolean g) {
        this.g = g;
    }

    public void setA(boolean a) {
        this.a = a;
    }

    public void setD(boolean d) {
        this.d = d;
    }

    public static void main(String[] args) {
        FortePrototype game = new FortePrototype();
        MusicPlayer bgm = new MusicPlayer();
        MusicPlayer C = new MusicPlayer();
        MusicPlayer G = new MusicPlayer();
        MusicPlayer A = new MusicPlayer();
        MusicPlayer D = new MusicPlayer();
        game.setpC(C);
        game.setpG(G);
        game.setpA(A);
        game.setpD(D);
        game.start();

        GameClock clock2 = new GameClock();
        C.playSong("piano_c5.wav", -80);
        G.playSong("piano_g4.wav", -80);
        A.playSong("piano_a4.wav", -80);
        D.playSong("piano_d4.wav", -80);
        while (true) {

            if (clock2.getElapsedTime() > 2000) {
                if (!game.getmute(1)) {
                    C.playSong("piano_c5.wav", -10);
                }
                if (!game.getmute(2)) {
                    //   System.out.println("lll");
                    G.playSong("piano_g4.wav", -10);
                }
                if (!game.getmute(3)) {
                    A.playSong("piano_a4.wav", -10);
                }
                if (!game.getmute(4)) {
                    D.playSong("piano_d4.wav", -10);
                }
                clock2.resetGameClock();
            }
        }


    }

    @Override
    public void handleEvent(Event event) {
        DisplayObject source = (DisplayObject) event.getSource();
        if (source == door) {
            BossEncountered = true;
            player.setPosition(50, 50);
            boss.setPosition(500, 500);
            bossBackground.setPosition(0, 0);
            setBoosFloor(20, bossFloor);
        }

        if (source == boss) {
            isEnd = true;
        }
    }
}

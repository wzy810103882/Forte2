package edu.virginia.lab1test;

import edu.virginia.engine.controller.GamePad;
import edu.virginia.engine.display.Game;
import edu.virginia.engine.display.Sprite;
import edu.virginia.engine.util.GameClock;
import edu.virginia.engine.util.MusicPlayer;
import edu.virginia.engine.util.Position;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

//Gannon was here.
public class FortePrototype extends Game {
    static int gravity = 2;
    boolean falling = false;
    static int gameWidth = 1040;
    static int gameHeight = 920;
    boolean collision = false;
    boolean plat_top = false;
    boolean plat_down = false;
    boolean bullet_ready = true;
    private Sprite floor = new Sprite("Floor", "Brick.png");
    private Sprite background = new Sprite("background", "background.png");
    private Sprite platform = new Sprite("Platform", "Brick.png");
    private Sprite player = new Sprite("Player", "Protagonist.png");

    private ArrayList<Sprite> particle = new ArrayList<Sprite>();
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
        player.setPosition(500, gameHeight - floor.getUnscaledHeight() - player.getUnscaledHeight());
        C.setPosition(260, gameHeight - floor.getUnscaledHeight() - C.getUnscaledHeight());
        D.setPosition(700, gameHeight - floor.getUnscaledHeight() - D.getUnscaledHeight());
        E.setPosition(780, gameHeight - floor.getUnscaledHeight() - E.getUnscaledHeight());
        F.setPosition(910, gameHeight - floor.getUnscaledHeight() - F.getUnscaledHeight());

        C.addEventListener(QuestManager, Cevent.Cevent);
        D.addEventListener(QuestManager, Devent.Devent);
        E.addEventListener(QuestManager, Eevent.Eevent);
        F.addEventListener(QuestManager, Fevent.Fevent);


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
        setFloor(100, floor);
        setBackground(10, background);
        setHorizontalPlatform(500, gameHeight - 450, 3, platform);
        setHorizontalPlatform(2000, gameHeight - 500, 4, platform);
        setHorizontalPlatform(3000, gameHeight - 400, 7, platform);
        setVerticalPlatformOnFloor(1000, 3, platform);
        setVerticalPlatformOnFloor(1500, 2, platform);
        setVerticalPlatformOnFloor(0, 8, platform);
        setDiagonalPlatformOnFloor(2500, 3, true, platform);
        mainClock.resetGameClock();

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

    public void gamePadUpdate(ArrayList<GamePad> gamePads){
        for (GamePad pad : gamePads) {

            if (pad.getLeftStickXAxis() < -0.5) {
                player.setVelX(-15);
            }


            else if (pad.getLeftStickXAxis() > 0.5)
            {
                player.setVelX(15);
            }
            else {
                player.setVelX(0);

            }


            if (pad.isButtonPressed(GamePad.BUTTON_CROSS)) {
                if (!falling) {
                    player.setVelY(-37);
                    falling = true;
                    sfx.playSong("bass1.wav", 0);

                }
            }

            if (pad.isButtonPressed(GamePad.BUTTON_SQUARE)) {
                if (bullet_ready) {
                    //player.setVelY(-30);
                    //player.setVelX(-40);
                    sfx.playSong("bass2.wav", -15);
                    Sprite bullet = new Sprite("bullet", "music_note.png");
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
                    sfx.playSong("bass2.wav", -15);
                    Sprite bullet = new Sprite("bullet", "music_note.png");
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
    @Override
    public void update(ArrayList<String> pressedKeys, ArrayList<GamePad> gamePads) {
        super.update(pressedKeys, gamePads);
        player.update(pressedKeys, gamePads);
        player.rePosition(player.getVelX(), player.getVelY());
        if (falling) player.setVelY(player.getVelY() + (int) gravity);

/**
 * controller
 */
        gamePadUpdate(gamePads);



/**
 * map bound check
 */
        Position p1 = player.getPosition();
        if (p1.getX() <= 0) player.setPosition(0, p1.getY());
        if (p1.getX() >= gameWidth - player.getScaledWidth())
            player.setPosition(gameWidth - player.getScaledWidth() - 1, p1.getY());
        if (p1.getY() <= 0) player.setPosition(p1.getX(), 0);
        if (p1.getY() >= gameHeight - player.getScaledHeight() - 1) {
            player.setPosition(p1.getX(), gameHeight - player.getScaledHeight());
            falling = false;
        }


        /**
         * collision with platform
         */
        for (Sprite s : objects) {
            if (s.isVisible()) {
                if (player.nearby(s)) {
                    if (player.collidesWith(s, player.getVelX(), player.getVelY())) {
                        collision = true;
                        if (s.getId() == "Floor") {
                            player.setPosition(player.getPosX(), s.getPosY() - player.getScaledHeight() - 1);
                            player.setVelY(0);
                            falling = false;
                            break;
                        }

                        if (s.getId() == "Platform") {
                            // Landing on top
                            if (player.getPosY() + player.getScaledHeight() - player.getVelY() <= s.getPosY()) {
                                falling = false;
                                plat_top = true;
                                plat_down = false;
                                player.setPosition(player.getPosX(), s.getPosY() - player.getScaledHeight() - 1);
                                break;
                            } else {
                                // Left side
                                if (player.getPosX() + player.getScaledWidth() >= s.getPosX()
                                        && player.getPosX() + player.getScaledWidth() < s.getPosX() + s.getScaledWidth() / 2) {
                                    player.setPosition(s.getPosX() - player.getScaledWidth() - 1, player.getPosY());
                                    falling = true;
                                    plat_top = false;
                                    plat_down = false;
                                    break;
                                }
                                // Right side
                                if (player.getPosX() <= s.getPosX() + s.getScaledWidth() && player.getPosX() > s.getPosX() + s.getScaledWidth() * 0.5) {
                                    player.setPosition(s.getPosX() + s.getScaledWidth() + 1, player.getPosY());
                                    falling = true;
                                    plat_top = false;
                                    plat_down = false;
                                    break;
                                }
                                // Below
                                if (player.getPosY() <= s.getPosY() + s.getScaledHeight() && player.getPosY() > s.getPosY()) {
                                    player.setPosition(player.getPosX(), s.getPosY() + s.getScaledHeight() + 1);
                                    falling = true;
                                    plat_top = false;
                                    plat_down = true;
                                    break;

                                }
                            }
                        }

                    } else {
                        collision = false;
                    }
                }
            }
        }


        /**
         * collision with player and enemy
         */
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

        /**
         * blinking feature
         */
        timingMode(0, 1000, C);
        timingMode(0, 1000, D);
        timingMode(0, 1000, E);
        timingMode(0, 1000, F);


        ArrayList<Sprite> toRemove = new ArrayList<Sprite>();

        /**
         * attack feature
         */
        for (Sprite p : particle) {
            p.rePosition(p.getVelX(), p.getVelY());
            p.setVelY(p.getVelY() + (int) gravity);
            for (Sprite e : enemies) {
                if (e.isVisible()) {
                    if (e.collidesWith(p, e.getVelX(), e.getVelY())) {
                        toRemove.add(p);
                        falling = true;
                        // player.setPosition(p.getPosition());
                        //p.setVisible(false);
                        // particle.remove(p);
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
        particle.removeAll(toRemove);

        /**
         * background and object scrolling
         */

        if (!collision) {
            if (!(player.getPosX() < gameWidth / 2)) {
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
        } else {
            if (plat_top || plat_down) {
                if (!(player.getPosX() < gameWidth / 2)) {

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
}

package edu.virginia.lab1test;

import edu.virginia.Music.SecondSongMixer;
import edu.virginia.Music.SilenceTest;
import edu.virginia.Music.ThirdSongMixer;
import edu.virginia.engine.controller.GamePad;
import edu.virginia.engine.display.DisplayObject;
import edu.virginia.engine.display.Game;
import edu.virginia.engine.display.Sprite;
import edu.virginia.engine.events.Event;
import edu.virginia.engine.events.IEventListener;
import edu.virginia.engine.util.GameClock;
import edu.virginia.engine.util.Position;
import jm.JMC;
import jm.audio.RTMixer;
import jm.music.data.Score;
import jm.util.Play;
import jm.util.Read;

import java.awt.*;
import java.util.ArrayList;

//Gannon was here.
public class FortePrototype extends Game implements IEventListener, JMC {
    static int gravity = 2;
    private double health = 100;
    boolean falling = false;
    static int gameWidth = 1000;
    static int gameHeight = 900;
    boolean collision = false;
    boolean plat_top = false;
    boolean plat_down = false;
    boolean bullet_ready = true;

    private RTMixer mixer;

    private Sprite healthbar = new Sprite("health", "health.png");
    private Sprite floor = new Sprite("Floor", "blue.png");
    private Sprite background = new Sprite("background", "download.png");
    private Sprite bossBackground = new Sprite("bossBackground", "download.png");
    private Sprite boss = new Sprite("boss", "Protagonist.png");
    private Sprite platform = new Sprite("Platform", "blue.png");
    private Sprite player = new Sprite("Player", "Protagonist.png");

    private Sprite platformleveltwo = new Sprite("Platform", "matt3.png");
    private Sprite floorleveltwo = new Sprite("Floor", "matt3.png");
    private Sprite backgroundleveltwo = new Sprite("background", "bg.png");


    private Sprite platformlevelthree = new Sprite("Platform", "sunburstround.png");
    private Sprite floorlevelthree = new Sprite("Floor", "sunburstround.png");
    private Sprite backgroundlevelthree = new Sprite("background", "background.png");

    private Sprite levelonedoor = new Sprite("door", "door.png");
    private Sprite leveltwodoor = new Sprite("door", "door.png");
    private Sprite levelthreedoor = new Sprite("door", "door.png");

    private Sprite door = new Sprite("door", "door.png");
    private Sprite bossFloor = new Sprite("Floor", "blue.png");
    private Sprite startSreen = new Sprite("start", "pinkbg.png");
    private boolean isStart = false;
    private boolean isEnd = false;
    private boolean BossEncountered = false;
    private boolean isVictoryEnd = false;
    private SilenceTest silenceTest = new SilenceTest();
    private SecondSongMixer secondSongMixer = new SecondSongMixer();
    private ThirdSongMixer thirdSongMixer = new ThirdSongMixer();
    private int score = 0;

    private double[] trumpetRhythmArray = new double[]{DOTTED_QUARTER_NOTE, DOTTED_QUARTER_NOTE, DOTTED_QUARTER_NOTE, DOTTED_QUARTER_NOTE,
            QN, QN, DOTTED_QUARTER_NOTE, DOTTED_QUARTER_NOTE, DOTTED_QUARTER_NOTE, DOTTED_QUARTER_NOTE,
            EN, EN, QN};

    private int[] trumpetPitchArray = new int[]{A4, FS4, G4, FS4, FS4, FS4, E4, FS4, E4, D4, D4, E4, REST};

    private double[] bassRhythmArray = new double[]{DOTTED_QUARTER_NOTE, EN, EN, EN, DOTTED_QUARTER_NOTE, DOTTED_QUARTER_NOTE,
            QN, QN, DOTTED_QUARTER_NOTE, EN, EN, EN, DOTTED_QUARTER_NOTE, DOTTED_QUARTER_NOTE,
            QN, QN};

    private int[] bassPitchArray = new int[]{D3, A2, D3, REST, B2, B2, B2, B2, A2, A2, A2, REST, G2, G2, G2, G2};

    private double[] whistleRhythmArray = new double[]{QN, QN, QN, QN, QN, EN, EN, QN, EN, EN, QN, QN, QN, QN, QN, EN, EN, QN, EN, EN};

    private int[] whistlePitchArray = new int[]{REST, REST, REST, REST, REST, D4, D4, REST, D4, REST, REST, REST, REST, REST, REST, D4, D4, REST, D4, REST};


    private ArrayList<Sprite> particle = new ArrayList<Sprite>();
    private ArrayList<Sprite> bossObject = new ArrayList<Sprite>();
    private ArrayList<Sprite> objects = new ArrayList<Sprite>();
    private ArrayList<Sprite> enemies = new ArrayList<Sprite>();
    private ArrayList<Sprite> moving = new ArrayList<Sprite>();
    private ArrayList<Sprite> moving2 = new ArrayList<Sprite>();


    int ground = gameHeight - 175;
    int[] CxArray = {500, 1300, 2200, 2900, 4200, 5600, 6600, 7500, 8300, 8600, 9700, 15000};
    int[] CyArray = {ground, ground, ground, ground, ground, ground, ground, ground, ground, ground, ground, ground};

    int[] DxArray = {1000, 2000, 3000, 4000, 5000, 6000, 7000, 7800, 8100, 8300, 15000, 15000};
    int[] DyArray = {ground - 300, ground - 600, ground - 600, ground - 450, ground - 700, ground, ground, ground - 300, ground - 600, ground - 500, ground - 500, ground};

    int[] ExArray = {1700, 2200, 3000, 4000, 15000};
    int[] EyArray = {ground, ground, ground, ground, ground};

    int[] FxArray = {1700, 2200, 3000, 4000, 15000};
    int[] FyArray = {1700, 2200, 3000, 4000, 15000};

    int[] CxArrayleveltwo = {500, 1300, 2200, 2900, 4200, 5600, 6600, 7500, 8300, 8600, 9700, 15000};
    int[] CyArrayleveltwo = {ground, ground, ground, ground, ground, ground, ground, ground, ground, ground, ground, ground};

    int[] DxArrayleveltwo = {1000, 2000, 3000, 4000, 5000, 6000, 7000, 7800, 8100, 8300, 15000, 15000};
    int[] DyArrayleveltwo = {ground - 300, ground - 600, ground - 600, ground - 450, ground - 700, ground, ground, ground - 300, ground - 600, ground - 500, ground - 500, ground};

    int[] ExArrayleveltwo = {1700, 2200, 3000, 4000, 15000};
    int[] EyArrayleveltwo = {ground, ground, ground, ground, ground};

    int[] FxArrayleveltwo = {1700, 2200, 3000, 4000, 15000};
    int[] FyArrayleveltwo = {ground, ground, ground, ground, ground};

    int[] CxArraylevelthree = {500, 1300, 2200, 2900, 4200, 5600, 6600, 7500, 8300, 8600, 9700, 15000};
    int[] CyArraylevelthree = {ground, ground, ground, ground, ground, ground, ground, ground, ground, ground, ground, ground};

    int[] DxArraylevelthree = {1000, 2000, 3000, 4000, 5000, 6000, 7000, 7800, 8100, 8300, 15000, 15000};
    int[] DyArraylevelthree = {ground - 300, ground - 600, ground - 600, ground - 450, ground - 700, ground, ground, ground - 300, ground - 600, ground - 500, ground - 500, ground};

    int[] ExArraylevelthree = {1700, 2200, 3000, 4000, 15000};
    int[] EyArraylevelthree = {ground, ground, ground, ground, ground};

    int[] FxArraylevelthree = {1700, 2200, 3000, 4000, 15000};
    int[] FyArraylevelthree = {ground, ground, ground, ground, ground};

    int[] CxArraybuffer = CxArray;
    int[] CyArraybuffer = CyArray;
    int[] DxArraybuffer = DxArray;
    int[] DyArraybuffer = DyArray;
    int[] ExArraybuffer = ExArray;
    int[] EyArraybuffer = EyArray;
    int[] FxArraybuffer = FxArray;
    int[] FyArraybuffer = FyArray;

    int[] CxArraybufferleveltwo = CxArrayleveltwo;
    int[] CyArraybufferleveltwo = CyArrayleveltwo;
    int[] DxArraybufferleveltwo = DxArrayleveltwo;
    int[] DyArraybufferleveltwo = DyArrayleveltwo;
    int[] ExArraybufferleveltwo = ExArrayleveltwo;
    int[] EyArraybufferleveltwo = EyArrayleveltwo;
    int[] FxArraybufferleveltwo = FxArrayleveltwo;
    int[] FyArraybufferleveltwo = FyArrayleveltwo;

    int[] CxArraybufferlevelthree = CxArraylevelthree;
    int[] CyArraybufferlevelthree = CyArraylevelthree;
    int[] DxArraybufferlevelthree = DxArraylevelthree;
    int[] DyArraybufferlevelthree = DyArraylevelthree;
    int[] ExArraybufferlevelthree = ExArraylevelthree;
    int[] EyArraybufferlevelthree = EyArraylevelthree;
    int[] FxArraybufferlevelthree = FxArraylevelthree;
    int[] FyArraybufferlevelthree = FyArraylevelthree;

    Sprite C = new Sprite("C", "C.png");
    Sprite D = new Sprite("D", "D.png");
    Sprite E = new Sprite("E", "E.png");
    //Sprite F = new Sprite("F", "F.png");

    Cevent cevent = new Cevent(Cevent.Cevent, C);
    Devent devent = new Devent(Devent.Devent, D);
    Eevent eevent = new Eevent(Eevent.Eevent, E);
    //Fevent fevent = new Fevent(Fevent.Fevent, F);

    BossDamageEvent bossDamageEvent = new BossDamageEvent(BossDamageEvent.BossDamageEvent, boss);
    BossEvent bossCompleteEvent = new BossEvent(BossEvent.BossEvent, boss);
    BossKillsPlayerEvent bossKillsPlayerEvent = new BossKillsPlayerEvent(BossKillsPlayerEvent.BossKillsPlayerEvent, boss);
    SongStartEvent songStartEvent = new SongStartEvent(SongStartEvent.SongStartEvent, player);
    LevelTwoEvent levelTwoEvent = new LevelTwoEvent(LevelTwoEvent.LevelTwoEvent, levelonedoor);
    LevelThreeEvent levelThreeEvent = new LevelThreeEvent(LevelThreeEvent.LevelThreeEvent, leveltwodoor);
    BossEvent bevent = new BossEvent(BossEvent.BossEvent, levelthreedoor);

    /**
     * BlinkOnEvent CblinkOnEvent = new BlinkOnEvent(BlinkOnEvent.BlinkOnEvent,C);
     * BlinkOnEvent DblinkOnEvent = new BlinkOnEvent(BlinkOnEvent.BlinkOnEvent,D);
     * BlinkOnEvent EblinkOnEvent = new BlinkOnEvent(BlinkOnEvent.BlinkOnEvent,E);
     * BlinkOnEvent FblinkOnEvent = new BlinkOnEvent(BlinkOnEvent.BlinkOnEvent,F);
     * BlinkOffEvent CblinkOffEvent = new BlinkOffEvent(BlinkOffEvent.BlinkOffEvent,C);
     * BlinkOffEvent DblinkOffEvent = new BlinkOffEvent(BlinkOffEvent.BlinkOffEvent,D);
     * BlinkOffEvent EblinkOffEvent = new BlinkOffEvent(BlinkOffEvent.BlinkOffEvent,E);
     * BlinkOffEvent FblinkOffEvent = new BlinkOffEvent(BlinkOffEvent.BlinkOffEvent,F);
     */

    private GameClock mainClock = new GameClock();

    public FortePrototype() {
        super("Forte Prototype", gameWidth, gameHeight);

        C.addEventListener(this, Cevent.Cevent);
        D.addEventListener(this, Devent.Devent);
        E.addEventListener(this, Eevent.Eevent);
        //F.addEventListener(this, Fevent.Fevent);
        door.addEventListener(this, BossEvent.BossEvent);
        boss.addEventListener(this, BossEvent.BossEvent);
        boss.addEventListener(this, BossDamageEvent.BossDamageEvent);
        boss.addEventListener(this, BossKillsPlayerEvent.BossKillsPlayerEvent);
        player.addEventListener(this, SongStartEvent.SongStartEvent);
        levelonedoor.addEventListener(this, LevelTwoEvent.LevelTwoEvent);
        leveltwodoor.addEventListener(this, LevelThreeEvent.LevelThreeEvent);
        levelthreedoor.addEventListener(this, BossEvent.BossEvent);
        enemies.add(C);
        enemies.add(D);
        enemies.add(E);
        //enemies.add(F);
        bossObject.add(boss);
    }

    public void randomSpawn(Sprite s){
        int xlowerbound = player.getPosX();
        int xupperbound = player.getPosX() + 1000;
        int ylowerbound = 0;
        int yupperbound = 700;
        s.setPosition(xlowerbound + (int)(Math.random() * ((xupperbound - xlowerbound) + 1)),ylowerbound + (int)(Math.random() * ((yupperbound - ylowerbound) + 1)));
    }
    
    public void levelonesetup() {
        player.setPosition(150, 50);

        CxArray = CxArraybuffer;
        CyArray = CyArraybuffer;
        DxArray = DxArraybuffer;
        DyArray = DyArraybuffer;
        ExArray = ExArraybuffer;
        EyArray = EyArraybuffer;
        FxArray = FxArraybuffer;
        FyArray = FyArraybuffer;

        C.setxArray(CxArray);
        C.setyArray(CyArray);
        D.setxArray(DxArray);
        D.setyArray(DyArray);
        E.setxArray(ExArray);
        E.setyArray(EyArray);
        //F.setxArray(FxArray);
        //F.setyArray(FyArray);
        C.setCurrentIndex(0);
        D.setCurrentIndex(0);
        E.setCurrentIndex(0);
        //F.setCurrentIndex(0);

        C.setCurrentFlashingIndex(0);
        D.setCurrentFlashingIndex(0);
        E.setCurrentFlashingIndex(0);
        //F.setCurrentFlashingIndex(0);

        //  F.setxArray(FxArray);
        //  F.setyArray(FyArray);

        C.setPosition(260, gameHeight - floor.getUnscaledHeight() - C.getUnscaledHeight());
        D.setPosition(700, gameHeight - floor.getUnscaledHeight() - D.getUnscaledHeight());
        E.setPosition(780, gameHeight - floor.getUnscaledHeight() - E.getUnscaledHeight());
        //F.setPosition(900, gameHeight - floor.getUnscaledHeight() - E.getUnscaledHeight());

        if (enemies.contains(F)) {
            enemies.remove(F);
        }
        levelonedoor.setPosition(9800, gameHeight - 300);
        platformSetup();
        moving2.add(levelonedoor);
        moving.add(player);


    }

    public void leveltwosetup() {
        player.setPosition(150, 50);

        CxArrayleveltwo = CxArraybufferleveltwo;
        CyArrayleveltwo = CyArraybufferleveltwo;
        DxArrayleveltwo = DxArraybufferleveltwo;
        DyArrayleveltwo = DyArraybufferleveltwo;
        ExArrayleveltwo = ExArraybufferleveltwo;
        EyArrayleveltwo = EyArraybufferleveltwo;
        FxArrayleveltwo = FxArraybufferleveltwo;
        FyArrayleveltwo = FyArraybufferleveltwo;

        C.setxArray(CxArrayleveltwo);
        C.setyArray(CyArrayleveltwo);
        D.setxArray(DxArrayleveltwo);
        D.setyArray(DyArrayleveltwo);
        E.setxArray(ExArrayleveltwo);
        E.setyArray(EyArrayleveltwo);
        //F.setxArray(FxArrayleveltwo);
        //F.setyArray(FyArrayleveltwo);

        C.setCurrentIndex(0);
        D.setCurrentIndex(0);
        E.setCurrentIndex(0);
        //F.setCurrentIndex(0);
        //  F.setxArray(FxArray);
        //  F.setyArray(FyArray);

        C.setCurrentFlashingIndex(0);
        D.setCurrentFlashingIndex(0);
        E.setCurrentFlashingIndex(0);
        //F.setCurrentFlashingIndex(0);

        C.setPosition(260, gameHeight - floor.getUnscaledHeight() - C.getUnscaledHeight());
        D.setPosition(700, gameHeight - floor.getUnscaledHeight() - D.getUnscaledHeight());
        E.setPosition(780, gameHeight - floor.getUnscaledHeight() - E.getUnscaledHeight());
        //F.setPosition(900, gameHeight - floor.getUnscaledHeight() - E.getUnscaledHeight());
        leveltwodoor.setPosition(9800, gameHeight - 300);
        //if (!enemies.contains(F)) {
          //  enemies.add(F);
        //}
        platformSetupleveltwo();
        moving2.add(leveltwodoor);
        moving.add(player);


    }

    public void levelthreeup() {
        player.setPosition(150, 50);

        CxArraylevelthree = CxArraybufferlevelthree;
        CyArraylevelthree = CyArraybufferlevelthree;
        DxArraylevelthree = DxArraybufferlevelthree;
        DyArraylevelthree = DyArraybufferlevelthree;
        ExArraylevelthree = ExArraybufferlevelthree;
        EyArraylevelthree = EyArraybufferlevelthree;
        FxArraylevelthree = FxArraybufferlevelthree;
        FyArraylevelthree = FyArraybufferlevelthree;

        C.setxArray(CxArraylevelthree);
        C.setyArray(CyArraylevelthree);
        D.setxArray(DxArraylevelthree);
        D.setyArray(DyArraylevelthree);
        E.setxArray(ExArraylevelthree);
        E.setyArray(EyArraylevelthree);
        //F.setxArray(FxArraylevelthree);
        //F.setyArray(FyArraylevelthree);

        C.setCurrentIndex(0);
        D.setCurrentIndex(0);
        E.setCurrentIndex(0);
        //F.setCurrentIndex(0);
        //  F.setxArray(FxArray);
        //  F.setyArray(FyArray);

        C.setCurrentFlashingIndex(0);
        D.setCurrentFlashingIndex(0);
        E.setCurrentFlashingIndex(0);
        //F.setCurrentFlashingIndex(0);

        C.setPosition(260, gameHeight - floor.getUnscaledHeight() - C.getUnscaledHeight());
        D.setPosition(700, gameHeight - floor.getUnscaledHeight() - D.getUnscaledHeight());
        E.setPosition(780, gameHeight - floor.getUnscaledHeight() - E.getUnscaledHeight());
        //F.setPosition(900, gameHeight - floor.getUnscaledHeight() - E.getUnscaledHeight());
        levelthreedoor.setPosition(9800, gameHeight - 300);

        //if (!enemies.contains(F)) {
        //    enemies.add(F);
        //}

        platformSetuplevelthree();
        moving2.add(levelthreedoor);
        moving.add(player);

    }

    public void platformSetup() {
        /**
         * -200 is reference floor
         * -300 first tier
         * -900 is the top
         */
        particle.clear();
        objects.clear();
        moving.clear();
        moving2.clear();
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


    }

    public void platformSetupleveltwo() {
        /**
         * -200 is reference floor
         * -300 first tier
         * -900 is the top
         */
        particle.clear();
        objects.clear();
        moving.clear();
        moving2.clear();
        setFloor(110, floorleveltwo);
        setBackground(20, backgroundleveltwo);

        setHorizontalPlaformOnFloor(800, 3, platformleveltwo);
        setHorizontalPlatform(900, gameHeight - 300, 2, platformleveltwo);
        setHorizontalPlatform(1000, gameHeight - 400, 1, platformleveltwo);

        setHorizontalPlatform(1500, gameHeight - 400, 4, platformleveltwo);

        setHorizontalPlatform(1900, gameHeight - 600, 3, platformleveltwo);

        setVerticalPlatformOnFloor(2000, 2, platformleveltwo);

        setVerticalPlatformOnFloor(2300, 2, platformleveltwo);
        setVerticalPlatformOnFloor(2400, 2, platformleveltwo);
        setVerticalPlatformOnFloor(2500, 2, platformleveltwo);
        setVerticalPlatformOnFloor(2600, 2, platformleveltwo);

        setHorizontalPlaformOnFloor(3000, 10, platformleveltwo);
        setHorizontalPlatform(3000, gameHeight - 300, 8, platformleveltwo);
        setHorizontalPlatform(3000, gameHeight - 400, 6, platformleveltwo);
        setHorizontalPlatform(3300, gameHeight - 500, 3, platformleveltwo);
        setHorizontalPlatform(3400, gameHeight - 600, 2, platformleveltwo);
        setHorizontalPlatform(3400, gameHeight - 700, 2, platformleveltwo);

        setVerticalPlatformOnFloor(4400, 1, platformleveltwo);
        setVerticalPlatformOnFloor(4600, 2, platformleveltwo);
        setVerticalPlatformOnFloor(4800, 3, platformleveltwo);
        setVerticalPlatformOnFloor(5000, 4, platformleveltwo);
        setVerticalPlatformOnFloor(5200, 5, platformleveltwo);
        setVerticalPlatformOnFloor(5400, 2, platformleveltwo);


        setHorizontalPlatform(5700, gameHeight - 500, 4, platformleveltwo);
        setVerticalPlatform(6000, gameHeight - 600, 2, platformleveltwo);

        setHorizontalPlatform(6400, gameHeight - 700, 2, platformleveltwo);
        setVerticalPlatformOnFloor(6700, 3, platformleveltwo);
        setHorizontalPlatform(6800, gameHeight - 700, 2, platformleveltwo);

        setHorizontalPlatform(7200, gameHeight - 700, 1, platformleveltwo);
        setHorizontalPlatform(7500, gameHeight - 700, 1, platformleveltwo);

        setVerticalPlatformOnFloor(7800, 3, platformleveltwo);
        setVerticalPlatform(7800, gameHeight - 600, 3, platformleveltwo);


        setVerticalPlatform(8100, gameHeight - 500, 1, platformleveltwo);
        setVerticalPlatform(8300, gameHeight - 500, 1, platformleveltwo);
        setVerticalPlatform(8500, gameHeight - 500, 1, platformleveltwo);
        setVerticalPlatform(8700, gameHeight - 500, 1, platformleveltwo);

        setVerticalPlatformOnFloor(8800, 3, platformleveltwo);
        setVerticalPlatformOnFloor(8900, 5, platformleveltwo);
        setVerticalPlatformOnFloor(9100, 3, platformleveltwo);
        setVerticalPlatformOnFloor(9200, 2, platformleveltwo);
        setVerticalPlatformOnFloor(9300, 2, platformleveltwo);
        setVerticalPlatformOnFloor(9400, 1, platformleveltwo);
        setVerticalPlatformOnFloor(9500, 1, platformleveltwo);

        setVerticalPlatformOnFloor(10000, 10, platformleveltwo);
        setVerticalPlatformOnFloor(10100, 10, platformleveltwo);
        setVerticalPlatformOnFloor(10200, 10, platformleveltwo);
        setVerticalPlatformOnFloor(10300, 10, platformleveltwo);
        setVerticalPlatformOnFloor(10400, 10, platformleveltwo);


    }

    public void platformSetuplevelthree() {
        /**
         * -200 is reference floor
         * -300 first tier
         * -900 is the top
         */
        particle.clear();
        objects.clear();
        moving.clear();
        moving2.clear();
        setFloor(110, floorlevelthree);
        setBackground(20, backgroundlevelthree);

        setHorizontalPlaformOnFloor(800, 3, platformlevelthree);
        setHorizontalPlatform(900, gameHeight - 300, 2, platformlevelthree);
        setHorizontalPlatform(1000, gameHeight - 400, 1, platformlevelthree);

        setHorizontalPlatform(1500, gameHeight - 400, 4, platformlevelthree);

        setHorizontalPlatform(1900, gameHeight - 600, 3, platformlevelthree);

        setVerticalPlatformOnFloor(2000, 2, platformlevelthree);

        setVerticalPlatformOnFloor(2300, 2, platformlevelthree);
        setVerticalPlatformOnFloor(2400, 2, platformlevelthree);
        setVerticalPlatformOnFloor(2500, 2, platformlevelthree);
        setVerticalPlatformOnFloor(2600, 2, platformlevelthree);

        setHorizontalPlaformOnFloor(3000, 10, platformlevelthree);
        setHorizontalPlatform(3000, gameHeight - 300, 8, platformlevelthree);
        setHorizontalPlatform(3000, gameHeight - 400, 6, platformlevelthree);
        setHorizontalPlatform(3300, gameHeight - 500, 3, platformlevelthree);
        setHorizontalPlatform(3400, gameHeight - 600, 2, platformlevelthree);
        setHorizontalPlatform(3400, gameHeight - 700, 2, platformlevelthree);

        setVerticalPlatformOnFloor(4400, 1, platformlevelthree);
        setVerticalPlatformOnFloor(4600, 2, platformlevelthree);
        setVerticalPlatformOnFloor(4800, 3, platformlevelthree);
        setVerticalPlatformOnFloor(5000, 4, platformlevelthree);
        setVerticalPlatformOnFloor(5200, 5, platformlevelthree);
        setVerticalPlatformOnFloor(5400, 2, platformlevelthree);


        setHorizontalPlatform(5700, gameHeight - 500, 4, platformlevelthree);
        setVerticalPlatform(6000, gameHeight - 600, 2, platformlevelthree);

        setHorizontalPlatform(6400, gameHeight - 700, 2, platformlevelthree);
        setVerticalPlatformOnFloor(6700, 3, platformlevelthree);
        setHorizontalPlatform(6800, gameHeight - 700, 2, platformlevelthree);

        setHorizontalPlatform(7200, gameHeight - 700, 1, platformlevelthree);
        setHorizontalPlatform(7500, gameHeight - 700, 1, platformlevelthree);

        setVerticalPlatformOnFloor(7800, 3, platformlevelthree);
        setVerticalPlatform(7800, gameHeight - 600, 3, platformlevelthree);


        setVerticalPlatform(8100, gameHeight - 500, 1, platformlevelthree);
        setVerticalPlatform(8300, gameHeight - 500, 1, platformlevelthree);
        setVerticalPlatform(8500, gameHeight - 500, 1, platformlevelthree);
        setVerticalPlatform(8700, gameHeight - 500, 1, platformlevelthree);

        setVerticalPlatformOnFloor(8800, 3, platformlevelthree);
        setVerticalPlatformOnFloor(8900, 5, platformlevelthree);
        setVerticalPlatformOnFloor(9100, 3, platformlevelthree);
        setVerticalPlatformOnFloor(9200, 2, platformlevelthree);
        setVerticalPlatformOnFloor(9300, 2, platformlevelthree);
        setVerticalPlatformOnFloor(9400, 1, platformlevelthree);
        setVerticalPlatformOnFloor(9500, 1, platformlevelthree);

        setVerticalPlatformOnFloor(10000, 10, platformlevelthree);
        setVerticalPlatformOnFloor(10100, 10, platformlevelthree);
        setVerticalPlatformOnFloor(10200, 10, platformlevelthree);
        setVerticalPlatformOnFloor(10300, 10, platformlevelthree);
        setVerticalPlatformOnFloor(10400, 10, platformlevelthree);


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

    /**
     * platform placement
     *
     * @param x
     * @param y
     * @param number
     * @param Platform
     */
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


    /**
     * blinking feature
     *
     * @param start
     * @param finish
     * @param temp
     */
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

    public void blink(SilenceTest mixer, int songLength, Sprite temp, int index) {

        if (index == 0) {
            int globaltime = 0;
            for (int a = 0; a < mixer.getTrumpet().getTrumpetRhythmArray().length; a++) {
                if (trumpetPitchArray[a] > 0) {
                    if (!mixer.getTrumpet().getMuteArray()[a]) {
                        int start = globaltime;
                        int finish = globaltime + (int) (0.5 * 1000 * (mixer.getTrumpet().getTrumpetRhythmArray()[a] / 3));
                        globaltime = globaltime + (int) (1000 * (mixer.getTrumpet().getTrumpetRhythmArray()[a] / 3));
                        timingMode(start, finish, songLength, temp, a);
                    }
                }
            }
        } else if (index == 1) {
            int globaltime = 0;
            for (int a = 0; a < mixer.getBass().getBassRhythmArray().length; a++) {
                if (bassPitchArray[a] > 0) {
                    if (!mixer.getBass().getMuteArray()[a]) {
                        int start = globaltime;
                        int finish = globaltime + (int) (0.5 * 1000 * (mixer.getBass().getBassRhythmArray()[a] / 3));
                        globaltime = globaltime + (int) (1000 * (mixer.getBass().getBassRhythmArray()[a] / 3));
                        timingMode(start, finish, songLength, temp, a);
                    }
                }
            }
        } else if (index == 2) {
            int globaltime = 0;
            for (int a = 0; a < mixer.getTrumpet().getTrumpetRhythmArray().length; a++) {
                if (trumpetPitchArray[a] > 0) {
                    if (!mixer.getTrumpet().getMuteArray()[a]) {
                        int start = globaltime;
                        int finish = globaltime + (int) (0.5 * 1000 * (mixer.getTrumpet().getTrumpetRhythmArray()[a] / 3));
                        globaltime = globaltime + (int) (1000 * (mixer.getTrumpet().getTrumpetRhythmArray()[a] / 3));
                        bgtiming(start, finish, songLength, temp);
                    }
                }
            }
        }
    }

    public void blinksecondsong(SecondSongMixer mixer, int songLength, Sprite temp, int index) {

        if (index == 0) {
            int globaltime = 0;
            for (int a = 0; a < mixer.getTrumpet().getMelodyRhythmArray().length; a++) {
                if (mixer.getTrumpet().getMelodyPitchArray()[a] > 0) {
                    if (!mixer.getTrumpet().getMuteArray()[a]) {
                        int start = globaltime;
                        int finish = globaltime + (int) (0.5 * 1000 * (mixer.getTrumpet().getMelodyRhythmArray()[a] / 3));
                        globaltime = globaltime + (int) (1000 * (mixer.getTrumpet().getMelodyRhythmArray()[a] / 3));
                        timingMode(start, finish, songLength, temp, a);
                    }
                }
            }
        } else if (index == 1) {
            int globaltime = 0;
            for (int a = 0; a < mixer.getBass().getMelodyRhythmArray().length; a++) {
                if (mixer.getBass().getMelodyRhythmArray()[a]> 0) {
                    if (!mixer.getBass().getMuteArray()[a]) {
                        int start = globaltime;
                        int finish = globaltime + (int) (0.5 * 1000 * (mixer.getBass().getMelodyRhythmArray()[a] / 3));
                        globaltime = globaltime + (int) (1000 * (mixer.getBass().getMelodyRhythmArray()[a] / 3));
                        timingMode(start, finish, songLength, temp, a);
                    }
                }
            }

        } else if (index == 2) {
            int globaltime = 0;
            for (int a = 0; a < mixer.getWhistle().getMelodyRhythmArray().length; a++) {
                if (mixer.getWhistle().getMelodyRhythmArray()[a] > 0) {
                    if (!mixer.getWhistle().getMuteArray()[a]) {
                        int start = globaltime;
                        int finish = globaltime + (int) (0.5 * 1000 * (mixer.getWhistle().getMelodyRhythmArray()[a] / 3));
                        globaltime = globaltime + (int) (1000 * (mixer.getWhistle().getMelodyRhythmArray()[a] / 3));
                        timingMode(start, finish, songLength, temp, a);
                    }
                }
            }

        }
        else if (index == 3){
            int globaltime = 0;
            for (int a = 0; a < mixer.getAcc().getMelodyRhythmArray().length; a++) {
                if (mixer.getAcc().getMelodyRhythmArray()[a] > 0) {
                    if (!mixer.getAcc().getMuteArray()[a]) {
                        int start = globaltime;
                        int finish = globaltime + (int) (0.5 * 1000 * (mixer.getAcc().getMelodyRhythmArray()[a] / 3));
                        globaltime = globaltime + (int) (1000 * (mixer.getAcc().getMelodyRhythmArray()[a] / 3));
                        timingMode(start, finish, songLength, temp, a);
                    }
                }
            }

        }
        else {

        }
    }

    public void blinkthirdsong(ThirdSongMixer mixer, int songLength, Sprite temp, int index) {

        if (index == 0) {
            int globaltime = 0;
            for (int a = 0; a < mixer.getTrumpet().getMelodyRhythmArray().length; a++) {
                if (mixer.getTrumpet().getMelodyPitchArray()[a] > 0) {
                    if (!mixer.getTrumpet().getMuteArray()[a]) {
                        int start = globaltime;
                        int finish = globaltime + (int) (0.5 * 1000 * (mixer.getTrumpet().getMelodyRhythmArray()[a] / 3));
                        timingMode(start, finish, songLength, temp, a);
                    }
                }
                globaltime = globaltime + (int) (1000 * (mixer.getTrumpet().getMelodyRhythmArray()[a] / 3));
            }


        } else if (index == 1) {
            int globaltime = 0;
            for (int a = 0; a < mixer.getBass().getMelodyRhythmArray().length; a++) {
                if (mixer.getBass().getMelodyRhythmArray()[a]> 0) {
                    if (!mixer.getBass().getMuteArray()[a]) {
                        int start = globaltime;
                        int finish = globaltime + (int) (0.5 * 1000 * (mixer.getBass().getMelodyRhythmArray()[a] / 3));
                        timingMode(start, finish, songLength, temp, a);
                    }
                }
                globaltime = globaltime + (int) (1000 * (mixer.getBass().getMelodyRhythmArray()[a] / 3));

            }


        } else if (index == 2) {
            int globaltime = 0;
            for (int a = 0; a < mixer.getWhistle().getMelodyRhythmArray().length; a++) {
                if (mixer.getWhistle().getMelodyRhythmArray()[a] > 0) {
                    if (!mixer.getWhistle().getMuteArray()[a]) {
                        int start = globaltime;
                        int finish = globaltime + (int) (0.5 * 1000 * (mixer.getWhistle().getMelodyRhythmArray()[a] / 3));
                        timingMode(start, finish, songLength, temp, a);
                    }
                }
                globaltime = globaltime + (int) (1000 * (mixer.getWhistle().getMelodyRhythmArray()[a] / 3));

            }


        }

        else {

        }
    }

    public void blinkwithrest(double[] musicarray, int songLength, Sprite temp) {
        int globaltime = 0;
        for (int a = 0; a < musicarray.length; a++) {
            int start = globaltime;

            if (whistlePitchArray[a] > 0) {
                if (!silenceTest.getWhistle().getMuteArray()[a]) {
                    int finish = globaltime + (int) (0.5 * 1000 * (musicarray[a] / 3));
                    timingMode(start, finish, songLength, temp, a);
                }
            }
            globaltime = globaltime + (int) (1000 * (musicarray[a] / 3));
        }
    }

    public void bgtiming(int start, int finish, int songLength, Sprite temp) {
        if (temp.isPrevChange()) {
            temp.setPrev(temp.getDisplayImage());
            temp.setPrevChange(false);
        }
        //  if (bol) {
        //      image = temp.getDisplayImage();
        //  }
        if (mainClock.getElapsedTime() % songLength > start && (mainClock.getElapsedTime() % songLength) < start + 18) {
            temp.setImage("flashbg.jpg");
            temp.setFlashing(true);
            temp.setStart(start);
            temp.setFinish(finish);
            // System.out.print(temp.getCurrentFlashingIndex());
            //bol = false;
        }

        if (mainClock.getElapsedTime() % songLength > finish && (mainClock.getElapsedTime() % songLength) < finish + 18) {
            temp.setImage(temp.getPrev());
            temp.setFlashing(false);
        }
    }

    public void timingMode(int start, int finish, int songLength, Sprite temp, int index) {

        //  BufferedImage image = new BufferedImage(2,2,1);
        if (temp.isPrevChange()) {
            temp.setPrev(temp.getDisplayImage());
            temp.setPrevChange(false);
        }
        //  if (bol) {
        //      image = temp.getDisplayImage();
        //  }
        if (mainClock.getElapsedTime() % songLength > start && (mainClock.getElapsedTime() % songLength) < start + 18) {
            temp.setImage("Flash.png");
            temp.setFlashing(true);
            temp.setStart(start);
            temp.setFinish(finish);
            temp.setCurrentFlashingIndex(index);
            // System.out.print(temp.getCurrentFlashingIndex());
            //bol = false;
        }

        if (mainClock.getElapsedTime() % songLength > finish && (mainClock.getElapsedTime() % songLength) < finish + 18) {
            temp.setImage(temp.getPrev());
            temp.setFlashing(false);

        }

    }


    /**
     * update methods
     *
     * @param gamePads
     */

    public void gamePadUpdate(ArrayList<GamePad> gamePads) {
        for (GamePad pad : gamePads) {
            // pad.printButtonSummary();

            if (pad.getLeftStickXAxis() > 0.9) {
                player.setVelX(8);
            } else if (pad.getLeftStickXAxis() < -0.9) {
                player.setVelX(-8);
            } else {
                player.setVelX(0);

            }


            if (pad.isButtonPressed(GamePad.BUTTON_TRIANGLE)) {
                if (isStart && isEnd && isAtlevelOne) {
                    player.dispatchEvent(songStartEvent);
                } else if (isStart && isEnd && isAtlevelTwo) {
                    levelonedoor.dispatchEvent(levelTwoEvent);
                } else if (isStart && isEnd && isAtlevelThree) {
                    leveltwodoor.dispatchEvent(levelThreeEvent);
                } else if (isStart && isEnd && BossEncountered) {
                    levelthreedoor.dispatchEvent(bevent);
                }
                else {

                }
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
                    bullet.setVelY(-10);
                    bullet.setVelX(-40);
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
                    bullet.setVelY(-10);
                    bullet.setVelX(40);
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
        if (p1.getX() <= 0) {
            player.setPosition(0, p1.getY());
            if (!BossEncountered) {
                boss.dispatchEvent(bossKillsPlayerEvent);
            }
        }
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


                    if (s.getId() == "Floor") {
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
                            // System.out.print("sss");

                        } else if (e.getId() == "E") {
                            E.dispatchEvent(eevent);

                        } else {

                            ;
                        }
                    }

                }
            }
        }
    }

    private int scrollingspeed = 6;

    public void scrolling() {

        for (Sprite p : moving) {
            p.setPosition(p.getPosX() - scrollingspeed, p.getPosY());
        }
        for (Sprite p : moving2) {
            p.setPosition(p.getPosX() - scrollingspeed, p.getPosY());
        }
        for (Sprite p : enemies) {
            p.setPosition(p.getPosX() - scrollingspeed, p.getPosY());
        }
        if (isAtlevelOne) {
            for (int a = 0; a < CxArray.length; a++) {
                CxArray[a] = CxArray[a] - scrollingspeed;
            }
            for (int a = 0; a < DxArray.length; a++) {
                DxArray[a] = DxArray[a] - scrollingspeed;
            }
            for (int a = 0; a < ExArray.length; a++) {
                ExArray[a] = ExArray[a] - scrollingspeed;
            }
        }
        if (isAtlevelTwo) {
            for (int a = 0; a < CxArrayleveltwo.length; a++) {
                CxArrayleveltwo[a] = CxArrayleveltwo[a] - scrollingspeed;
            }
            for (int a = 0; a < DxArrayleveltwo.length; a++) {
                DxArrayleveltwo[a] = DxArrayleveltwo[a] - scrollingspeed;
            }
            for (int a = 0; a < ExArrayleveltwo.length; a++) {
                ExArrayleveltwo[a] = ExArrayleveltwo[a] - scrollingspeed;
            }
            for (int a = 0; a < FxArrayleveltwo.length; a++) {
                FxArrayleveltwo[a] = ExArrayleveltwo[a] - scrollingspeed;
            }
        }
        if (isAtlevelThree) {
            for (int a = 0; a < CxArraylevelthree.length; a++) {
                CxArraylevelthree[a] = CxArraylevelthree[a] - scrollingspeed;
            }
            for (int a = 0; a < DxArraylevelthree.length; a++) {
                DxArraylevelthree[a] = DxArraylevelthree[a] - scrollingspeed;
            }
            for (int a = 0; a < ExArraylevelthree.length; a++) {
                ExArraylevelthree[a] = ExArraylevelthree[a] - scrollingspeed;
            }
            for (int a = 0; a < FxArrayleveltwo.length; a++) {
                FxArrayleveltwo[a] = ExArrayleveltwo[a] - scrollingspeed;
            }
        }
    }

    public void attack(ArrayList<Sprite> stuff) {
        ArrayList<Sprite> toRemove = new ArrayList<Sprite>();
        for (Sprite p : particle) {
            p.rePosition(p.getVelX(), p.getVelY());
            p.setVelY(p.getVelY() + (int) gravity);

            for (Sprite o : stuff) {
                if (o.isVisible()) {
                    if (o.nearby(p)) {
                        if (o.collidesWith(p, o.getVelX(), o.getVelY())) {
                            toRemove.add(p);
                            p.setVisible(false);
                            //  sfx.playSong("Error.wav",-20);
                        }
                    }
                }
            }

            if (!BossEncountered) {
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
                                    //            F.dispatchEvent(fevent);
                                }

                            }
                        }
                    }
                }
            }
        }
        particle.removeAll(toRemove);
    }


    private boolean islevelone = true;
    private boolean isleveltwo = false;
    private boolean islevelthree = false;

    /**
     * public void levelcheck() {
     * if (islevelone) {
     * levelonesetup();
     * islevelone = false;
     * }
     * if (isleveltwo) {
     * leveltwosetup();
     * isleveltwo = false;
     * }
     * if (islevelthree){
     * levelthreeup();
     * islevelthree = false;
     * }
     * }
     */

    @Override
    public void update(ArrayList<String> pressedKeys, ArrayList<GamePad> gamePads) {

        if (isStart) {
            if (!isEnd) {
                if (!BossEncountered) {

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


                    attack(objects);
                    scrolling();

                    for (Sprite e : enemies) {
                        if (e.getPosX() < -e.getUnscaledWidth()) {
                            e.nextPosition();
                        }
                    }
                    if (isAtlevelOne) {
                        if (player.collidesWith(levelonedoor, player.getVelX(), player.getVelY())) {
                            levelonedoor.dispatchEvent(levelTwoEvent);
                        }
                        blink(silenceTest, 5331, C, 0);
                        blink(silenceTest, 5328, D, 1);
                        blinkwithrest(whistleRhythmArray, 2662, E);

                    }
                    if (isAtlevelTwo) {
                        if (player.collidesWith(leveltwodoor, player.getVelX(), player.getVelY())) {
                            leveltwodoor.dispatchEvent(levelThreeEvent);
                        }
                        blinksecondsong(secondSongMixer,10656,C,0);
                        blinksecondsong(secondSongMixer,10656,D,1);
                        blinksecondsong(secondSongMixer,10656,E,2);
                        //blinksecondsong(secondSongMixer,10656,E,3);
                        //blinksecondsong(secondSongMixer,10656,F,3);


                    }
                    if (isAtlevelThree) {
                        if (player.collidesWith(levelthreedoor, player.getVelX(), player.getVelY())) {
                            levelthreedoor.dispatchEvent(bevent);
                        }
                        blinkthirdsong(thirdSongMixer,18330,C,0);
                        blinkthirdsong(thirdSongMixer,18330,D,1);
                        blinkthirdsong(thirdSongMixer,18330,E,2);
                        //blinkthirdsong(thirdSongMixer,18330,F,3);

                    }
                } else {

                    super.update(pressedKeys, gamePads);
                    player.rePosition(player.getVelX(), player.getVelY());
                    if (falling) player.setVelY(player.getVelY() + (int) gravity);

                    gamePadUpdate(gamePads);
                    mapBound();
                    attack(bossObject);
                    platformCollision(bossObject);

                    for (Sprite p : particle) {
                        if (p.nearby(boss)) {
                            if (p.collidesWith(boss, p.getVelX(), p.getVelY())) {
                                boss.dispatchEvent(bossDamageEvent);
                                break;
                            }
                        }
                    }

                    if (player.collidesWith(boss, player.getVelX(), player.getVelY())) {
                        boss.dispatchEvent(bossKillsPlayerEvent);
                    }

                    healthbar.setPosition(-(int) (1000 * (1 - (health / 100))), 0);

                    if (health < 0) {
                        boss.dispatchEvent(bossCompleteEvent);
                    }

                    if (mainClock.getElapsedTime() % 2000 > 0 && mainClock.getElapsedTime() % 2000 < 20) {
                        int xBossPos = (int) (Math.random() * (gameWidth - boss.getScaledWidth()));
                        int yBossPos = (int) (Math.random() * (gameHeight - boss.getScaledHeight()) - 100) + 100;
                        boss.setPosition(xBossPos, yBossPos);
                    }

                }


            } else {
                gamePadUpdate(gamePads);
            }
        } else {
            blink(silenceTest, 5331, startSreen, 2);
            for (GamePad pad : gamePads) {
                if (pad.isButtonPressed(GamePad.BUTTON_TRIANGLE)) {
                    this.start();
                    mainClock.resetGameClock();
                    player.dispatchEvent(songStartEvent);
                    isStart = true;
                    isEnd = false;
                    BossEncountered = false;
                }
            }
        }
    }

    /**
     * public void keyPressed(KeyEvent e) {
     * if (!falling) {
     * if (e.getKeyCode() == KeyEvent.VK_UP) {
     * player.setVelY(-37);
     * falling = true;
     * sfx.playSong("bass1.wav", 0);
     * <p>
     * }
     * }
     * <p>
     * if (e.getKeyCode() == KeyEvent.VK_LEFT) {
     * player.setVelX(-15);
     * <p>
     * }
     * if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
     * player.setVelX(15);
     * <p>
     * }
     * <p>
     * if (e.getKeyCode() == KeyEvent.VK_A) {
     * //player.setVelY(-30);
     * //player.setVelX(-40);
     * sfx.playSong("bass2.wav", -15);
     * Sprite bullet = new Sprite("bullet", "music_note.png");
     * bullet.setPosition(player.getPosition());
     * bullet.setVelY(-20);
     * bullet.setVelX(-30);
     * particle.add(bullet);
     * falling = true;
     * }
     * <p>
     * if (e.getKeyCode() == KeyEvent.VK_S) {
     * // player.setVelY(-30);
     * // player.setVelX(40);
     * sfx.playSong("bass2.wav", -15);
     * Sprite bullet = new Sprite("bullet", "music_note.png");
     * bullet.setPosition(player.getPosition());
     * bullet.setVelY(-20);
     * bullet.setVelX(30);
     * particle.add(bullet);
     * falling = true;
     * }
     * <p>
     * }
     * <p>
     * public void keyReleased(KeyEvent e) {
     * player.setVelX(0);
     * }
     */

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

    private boolean isAtlevelOne = true;
    private boolean isAtlevelTwo = false;
    private boolean isAtlevelThree = false;

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


                    //       if (F != null) F.draw(g);

                    if (player != null) player.draw(g);
                    for (Sprite temp : particle) {
                        if (temp.isVisible()) {
                            if (inRange(temp)) {
                                temp.draw(g);
                            }
                        }
                    }
                    if (isAtlevelOne) {
                        if (levelonedoor != null) levelonedoor.draw(g);
                    }
                    if (isAtlevelTwo) {
                        if (leveltwodoor != null) leveltwodoor.draw(g);
                    }
                    if (isAtlevelThree) {
                        if (levelthreedoor != null) levelthreedoor.draw(g);
                    }

                    g.drawString("Score: 0%", 360, 40);
                    if (C != null) C.draw(g);
                    if (D != null) D.draw(g);
                    if (E != null) E.draw(g);
                   // if (isAtlevelTwo || isAtlevelThree) {
                   //     if (F != null) F.draw(g);
                    //}

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
                    for (Sprite p : particle) {
                        if (p.isVisible()) {
                            if (inRange(p)) {
                                p.draw(g);
                            }
                        }
                    }
                    healthbar.draw(g);

                }
            } else {
                if (isVictoryEnd) {
                    g.drawString("Congratulations!", gameWidth / 2, gameHeight / 2);
                } else {
                    g.drawString("GAME OVER", gameWidth / 2, gameHeight / 2);
                }

            }
        } else {
            startSreen.draw(g);
            g.drawString("Forte", gameWidth / 2, gameHeight / 2);
            g.drawString("press 'start' to start", gameWidth / 2, gameHeight / 2 + 50);
        }

    }

    public static void main(String[] args) {
        FortePrototype game = new FortePrototype();
        game.start();

    }

    @Override
    public void handleEvent(Event event) {
        DisplayObject source = (DisplayObject) event.getSource();

        if (source == levelthreedoor) {
            if (silenceTest.isHasStarted()) {
                silenceTest.stop();
            }
            if (secondSongMixer.isHasStarted()) {
                secondSongMixer.stop();
            }
            if (thirdSongMixer.isHasStarted()) {
                thirdSongMixer.stop();
            }
            BossEncountered = true;
            player.setPosition(50, 50);
            boss.setPosition(500, 500);
            bossBackground.setPosition(0, 0);
            setBoosFloor(20, bossFloor);
            Score s = new Score("bossmusic.mid");
            Read.midi(s, "bossmusic.mid");
            Play.midi(s);
            isEnd = false;
        }

        if (source == levelonedoor) {
            isleveltwo = true;
            isAtlevelTwo = true;
            isAtlevelOne = false;
            isAtlevelThree = false;
            leveltwosetup();
            silenceTest.clearallmuteArray();

            if (silenceTest.isHasStarted()) {
                silenceTest.stop();
            }
            if (secondSongMixer.isHasStarted()) {
                secondSongMixer.stop();
            }
            if (thirdSongMixer.isHasStarted()) {
                thirdSongMixer.stop();
            }
            secondSongMixer = new SecondSongMixer();
            secondSongMixer.start();
            isEnd = false;
        }

        if (source == leveltwodoor) {
            islevelthree = true;
            isAtlevelTwo = false;
            isAtlevelOne = false;
            isAtlevelThree = true;
            levelthreeup();
            silenceTest.clearallmuteArray();
            if (silenceTest.isHasStarted()) {
                silenceTest.stop();
            }
            if (secondSongMixer.isHasStarted()) {
                secondSongMixer.stop();
            }
            if (thirdSongMixer.isHasStarted()) {
                thirdSongMixer.stop();
            }
            thirdSongMixer = new ThirdSongMixer();
            thirdSongMixer.start();
            isEnd = false;
        }

        if (source == player) {
            islevelone = true;
            isAtlevelTwo = false;
            isAtlevelOne = true;
            isAtlevelThree = false;
            levelonesetup();
            silenceTest.clearallmuteArray();

            if (silenceTest.isHasStarted()) {
                silenceTest.stop();
            }
            if (secondSongMixer.isHasStarted()) {
                secondSongMixer.stop();
            }
            if (thirdSongMixer.isHasStarted()) {
                thirdSongMixer.stop();
            }
            silenceTest = new SilenceTest();
            silenceTest.start();
            isEnd = false;
            System.out.println("sss");
        }

        if (source == boss) {
            if (event.getEventType().equals(BossEvent.BossEvent)) {
                isEnd = true;
                isVictoryEnd = true;
                Play.stopMidi();
                if (silenceTest.isHasStarted()) {
                    silenceTest.stop();
                }
                if (secondSongMixer.isHasStarted()) {
                    secondSongMixer.stop();
                }
                if (thirdSongMixer.isHasStarted()) {
                    thirdSongMixer.stop();
                }

            }

            if (event.getEventType().equals(BossKillsPlayerEvent.BossKillsPlayerEvent)) {
                isEnd = true;
                isVictoryEnd = false;
                Play.stopMidi();
                if (silenceTest.isHasStarted()) {
                    silenceTest.stop();
                }
                if (secondSongMixer.isHasStarted()) {
                    secondSongMixer.stop();
                }
                if (thirdSongMixer.isHasStarted()) {
                    thirdSongMixer.stop();
                }
            }

            if (event.getEventType().equals(BossDamageEvent.BossDamageEvent)) {
                health = health - 10;
            } else {

            }
        }

        if (source == C) {
            if (C.isFlashing()) {
                C.nextPosition();
                if (isAtlevelOne) {
                    silenceTest.getTrumpet().setMuteArray(C.getCurrentFlashingIndex(), true);
                }
                if (isAtlevelTwo) {
                    secondSongMixer.getTrumpet().setMuteArray(C.getCurrentFlashingIndex(),true);
                }
                if (isAtlevelThree) {
                    thirdSongMixer.getTrumpet().setMuteArray(C.getCurrentFlashingIndex(),true);
                }
            } else {
            }
        }

        if (source == D) {

            if (D.isFlashing()) {
                D.nextPosition();
                if (isAtlevelOne) {
                    silenceTest.getBass().setMuteArray(D.getCurrentFlashingIndex(), true);

                }
                if (isAtlevelTwo) {
                    secondSongMixer.getBass().setMuteArray(D.getCurrentFlashingIndex(),true);

                }

                if (isAtlevelThree) {
                thirdSongMixer.getBass().setMuteArray(D.getCurrentFlashingIndex(),true);
                }
            } else {

            }

        }

        if (source == E) {
            if (E.isFlashing()) {
                E.nextPosition();
                if (isAtlevelOne) {
                    silenceTest.getWhistle().setMuteArray(E.getCurrentFlashingIndex(), true);

                }
                if (isAtlevelTwo) {
                    secondSongMixer.getWhistle().setMuteArray(E.getCurrentFlashingIndex(),true);
                    secondSongMixer.getAcc().setMuteArray(E.getCurrentFlashingIndex(),true);

                }
                if (isAtlevelThree) {
                    thirdSongMixer.getWhistle().setMuteArray(E.getCurrentFlashingIndex(),true);


                }
            } else {

            }

        }




    }

    /**
     public void keyPressed(KeyEvent e) {
     if (e.getKeyCode() == KeyEvent.VK_UP) {
     player.setVelY(-37);
     falling = true;

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
     Sprite bullet = new Sprite("bullet", "music_note.png");
     bullet.setPosition(player.getPosition());
     bullet.setVelY(-20);
     bullet.setVelX(30);
     particle.add(bullet);
     falling = true;
     }

     if (!isStart) {
     if (e.getKeyCode() == KeyEvent.VK_SPACE) {
     isStart = true;
     }
     }


     }

     public void keyReleased(KeyEvent e) {
     player.setVelX(0);
     }
     */


}

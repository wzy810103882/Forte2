package edu.virginia.Music;

import jm.JMC;
import jm.music.data.*;
import jm.audio.RTMixer;
import jm.audio.Instrument;
import jm.music.rt.RTLine;

import java.awt.*;
import java.awt.event.*;
import javax.swing.event.*;
import javax.swing.*;

public class ThirdSongMixer implements JMC, ChangeListener, ActionListener {
    private JSlider dyno;
    private RTMixer mixer;
    private JLabel val;
    //private GBassLine bass;

    private boolean hasStarted = false;

    public boolean isHasStarted() {
        return hasStarted;
    }

    public void setHasStarted(boolean hasStarted) {
        this.hasStarted = hasStarted;
    }

    public ForteSong7 getTrumpet() {
        return trumpet;
    }

    public int[] getScore()
    {
        int a[] = trumpet.getScore();
        int b[] = bass.getScore();
        int c[] = whistle.getScore();


        int num = a[0] + b[0] + c[0];
        int denom = a[1] + b[1] + c[1];
        int ret[] = new int[] {num, denom};


        //combine numerators, denominators
        //return this percentage.
        return ret;
    }

    public void setTrumpet(ForteSong7 trumpet) {
        this.trumpet = trumpet;
    }

    public ForteSong8 getBass() {
        return bass;
    }

    public void setBass(ForteSong8 bass) {
        this.bass = bass;
    }

    public ForteSong9 getWhistle() {
        return whistle;
    }

    public void setWhistle(ForteSong9 whistle) {
        this.whistle = whistle;
    }

    public ForteSong10 getMelody() {
        return melody;
    }

    public void setMelody(ForteSong10 melody) {
        this.melody = melody;
    }

    private ForteSong7 trumpet;
    private ForteSong8 bass;
    private ForteSong9 whistle;
    private ForteSong10 melody;

    private JButton goBtn;
    private RTLine[] lineArray = new RTLine[4];

    public static void main(String[] args) {
        new ThirdSongMixer();
    }

    public ThirdSongMixer() {
        int sampleRate = 44100;
        int channels = 2;

        Instrument[] instArray = new Instrument[1];
        for(int i=0;i<instArray.length;i++){
            instArray[i] = new SawLPFInstRT2(sampleRate, 1000, channels);
        }

        Instrument[] instArray2 = new Instrument[1];
        for(int i=0;i<instArray2.length;i++){
            instArray2[i] = new SawLPFInstRT2(sampleRate, 1000, channels);
        }

        Instrument[] instArray3 = new Instrument[1];
        for(int i=0;i<instArray3.length;i++){
            instArray3[i] = new SawLPFInstRT2(sampleRate, 1000, channels);
        }

        Instrument[] instArray4 = new Instrument[1];
        for(int i=0;i<instArray4.length;i++){
            instArray4[i] = new SquareLPFInstRT2(sampleRate, 1000, channels);
        }

        trumpet = new ForteSong7(instArray);
        trumpet.setTempo(200);
        lineArray[0] = trumpet;

        bass = new ForteSong8(instArray2);
        bass.setTempo(200);
        lineArray[1] = bass;

        whistle = new ForteSong9(instArray3);
        whistle.setTempo(200);
        lineArray[2] = whistle;

        melody = new ForteSong10(instArray4);
        melody.setTempo(200);
        lineArray[3] = melody;

        // show slider panel
    }

    private void makeGUI() {
        JFrame f = new JFrame("Title");
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setSize(1000, 1000);
        JPanel panel = new JPanel(new BorderLayout());
        f.getContentPane().add(panel);


        //my dynamic slider
        dyno = new JSlider(1, 0, 120, 50);
        dyno.setEnabled(false);
        dyno.addChangeListener(this);

        panel.add(dyno, "West");

        // filter value display
        val = new JLabel("1000");
        panel.add(val, "East");

        goBtn = new JButton("Start");
        goBtn.addActionListener(this);
        panel.add(goBtn, "North");

        //
        f.pack();
        f.setVisible(true);
    }

    public void stateChanged(ChangeEvent e){
        // my dynamic slider moved
        if (e.getSource() == dyno) {
            //val.setText("" + (dyno.getValue() * 100));
            bass.setDynoValue(dyno.getValue()); //does this need a coefficient?
            //mixer.actionLines(dyno, 2);
        }

    }

    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == goBtn) {
            mixer = new RTMixer(lineArray);
            mixer.begin();
            dyno.setEnabled(true);
            goBtn.setEnabled(false);
        }
    }


    public void start(){
        mixer = new RTMixer(lineArray);
        mixer.begin();
        hasStarted = true;
    }

    public void stop(){
        mixer.stop();
        hasStarted = false;
    }

}

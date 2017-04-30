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

public class RTCompMute implements JMC, ChangeListener, ActionListener {
    private JSlider cutoff, panner, dyno;
    private RTMixer mixer;
    private JLabel val;
    private BassLine bass;
    private JButton goBtn;
    private RTLine[] lineArray = new RTLine[1];

    public static void main(String[] args) {
        new RTComposition();
    }

    public RTCompMute() {
        int sampleRate = 44100;
        int channels = 2;

        Instrument[] instArray = new Instrument[1];
        for(int i=0;i<instArray.length;i++){
            instArray[i] = new SawLPFInstRT2(sampleRate, 1000, channels);
        }

        bass = new BassLine(instArray);
        bass.setTempo(104);
        lineArray[0] = bass;
        //mixer = new RTMixer(lineArray);

        // show slider panel
        makeGUI();
    }

    private void makeGUI() {
        JFrame f = new JFrame("Title");
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setSize(300, 500);
        JPanel panel = new JPanel(new BorderLayout());
        f.getContentPane().add(panel);
        // pan
        panner = new JSlider(1, 1, 100, 50);
        panner.setEnabled(false);
        panner.addChangeListener(this);
        panel.add(panner, "West");
        // filter
        cutoff = new JSlider(1, 1, 100, 10);
        cutoff.setEnabled(false);
        cutoff.addChangeListener(this);
        panel.add(cutoff, "Center");

        //my dynamic slider
        dyno = new JSlider(1, 0, 120, 50);
        dyno.setEnabled(false);
        dyno.addChangeListener(this);
        panel.add(dyno, "East");

        // filter value display
        //val = new JLabel("1000");
        //panel.add(val, "East");

        // start stop
        goBtn = new JButton("Start");
        goBtn.addActionListener(this);
        panel.add(goBtn, "North");

        //
        f.pack();
        f.setVisible(true);
    }

    public void stateChanged(ChangeEvent e){
        // panner slider moved
        if (e.getSource() == panner) {
            bass.setPanValue((double)panner.getValue() / 100.0);
        }
        // filter slider moved
        if (e.getSource() == cutoff) {
            //val.setText("" + (cutoff.getValue() * 100));
            mixer.actionLines(cutoff, 1);
        }

        // my dynamic slider moved
        if (e.getSource() == dyno) {
            //val.setText("" + (dyno.getValue() * 100));
            bass.setDynoValue(dyno.getValue()); //does this need a coefficient?
            //mixer.actionLines(dyno, 2);
        }
        //revisit this. Idk what I'm writing.

    }

    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == goBtn) {
            mixer = new RTMixer(lineArray);
            mixer.begin();
            cutoff.setEnabled(true);
            panner.setEnabled(true);
            dyno.setEnabled(true);
            goBtn.setEnabled(false);
        }
    }


}

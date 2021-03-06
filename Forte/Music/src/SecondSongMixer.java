
import jm.JMC;
import jm.music.data.*;
import jm.audio.RTMixer;
import jm.audio.Instrument;
import jm.music.rt.RTLine;

import java.awt.*;
import java.awt.event.*;
import javax.swing.event.*;
import javax.swing.*;

public class SecondSongMixer implements JMC, ChangeListener, ActionListener {
    private JSlider dyno;
    private RTMixer mixer;
    private JLabel val;
    //private GBassLine bass;

    private ForteSong4 trumpet;
    private ForteSong5 bass;
    private ForteSong6 whistle;
    private ForteSong6b acc;

    private JButton goBtn;
    private RTLine[] lineArray = new RTLine[4];

    public static void main(String[] args) {
        new SecondSongMixer();
    }

    public SecondSongMixer() {
        int sampleRate = 44100;
        int channels = 2;

        Instrument[] instArray = new Instrument[1];
        for(int i=0;i<instArray.length;i++){
            instArray[i] = new SquareLPFInstRT2(sampleRate, 1000, channels);
        }

        Instrument[] instArray2 = new Instrument[1];
        for(int i=0;i<instArray2.length;i++){
            instArray2[i] = new SquareLPFInstRT2(sampleRate, 1000, channels);
        }

        Instrument[] instArray3 = new Instrument[1];
        for(int i=0;i<instArray3.length;i++){
            instArray3[i] = new SquareLPFInstRT2(sampleRate, 1000, channels);
        }

        Instrument[] instArray4 = new Instrument[1];
        for(int i=0;i<instArray4.length;i++){
            instArray4[i] = new SquareLPFInstRT2(sampleRate, 1000, channels);
        }

        trumpet = new ForteSong4(instArray);
        trumpet.setTempo(200);
        lineArray[0] = trumpet;

        bass = new ForteSong5(instArray2);
        bass.setTempo(200);
        lineArray[1] = bass;

        whistle = new ForteSong6(instArray3);
        whistle.setTempo(200);
        lineArray[2] = whistle;

        acc = new ForteSong6b(instArray4);
        acc.setTempo(200);
        lineArray[3] = acc;


        // show slider panel
        makeGUI();
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


}

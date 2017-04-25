
import jm.JMC;
import jm.music.data.*;
import jm.audio.RTMixer;
import jm.audio.Instrument;
import jm.music.rt.RTLine;

import java.awt.*;
import java.awt.event.*;
import javax.swing.event.*;
import javax.swing.*;

public class SilenceTest implements JMC, ChangeListener, ActionListener {
    private JSlider dyno;
    private RTMixer mixer;
    private JLabel val;
    //private GBassLine bass;

    private ForteSong trumpet;
    private ForteSong2 bass;
    private ForteSong3 whistle;

    private JButton goBtn;
    private RTLine[] lineArray = new RTLine[3];

    public static void main(String[] args) {
        new SilenceTest();
    }

    public SilenceTest() {
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

        trumpet = new ForteSong(instArray);
        trumpet.setTempo(180);
        lineArray[0] = trumpet;

        bass = new ForteSong2(instArray2);
        bass.setTempo(180);
        lineArray[1] = bass;

        whistle = new ForteSong3(instArray3);
        whistle.setTempo(180);
        lineArray[2] = whistle;
        //mixer = new RTMixer(lineArray);

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

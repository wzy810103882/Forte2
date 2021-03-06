import jm.music.rt.RTLine;
import jm.audio.Instrument;
import jm.music.data.Note;
import javax.swing.*;
import jm.util.*;

import jm.JMC;

public class ForteSong10 extends RTLine implements JMC{
    private Note n = new Note(36, 0.5);
    private int dynoPosition = 50;

    private int[] trumpetPitchArray = new int[] {B3, CS4, FS3, B3, CS4, D4, CS4, B3, A3, E3, FS3, GS3, A3};
    private double[] trumpetRhythmArray = new double[] {QN, 5, 5, 1, 8, 1, 1, 6, 5, 11, 6, 3, 2};


    int arrayIndex = 0;

    /**
     * Constructor
     */
    public ForteSong10(Instrument[] instArray) {
        super(instArray);
    }

    /**
     * Generate the next note when requested.
     */
    public synchronized Note getNextNote() {
        /*n.setPitch(pitch + intervals[(int) (Math.random() * intervals.length)]);
        //n.setDynamic((int) (Math.random() * 80 + 45));
        n.setDynamic(dynoPosition);
        n.setPan(panPosition);
        n.setRhythmValue((int) (Math.random() * 2 + 1) * 0.25);
        n.setDuration(n.getRhythmValue() * 0.9);
        return n;*/

        n.setPitch(trumpetPitchArray[arrayIndex]);
        n.setRhythmValue(trumpetRhythmArray[arrayIndex]);
        n.setDynamic(dynoPosition);
        n.setDuration(n.getRhythmValue());


        /*if(arrayIndex % 2 == 0)
        {
            n.setDynamic(dynoPosition);
        }
        else
        {
            setDynoValue(0);
            //this.dynoPosition = 0;
        }*/
        System.out.println("ArrayIndex is: " + arrayIndex);
        //n.setDynamic(dynoPosition);

        arrayIndex++;
        if(arrayIndex == trumpetRhythmArray.length)
        {
            arrayIndex = 0;
        }
        return n;
    }

    /**
     * Allow other classes to set the notes pan value
     */
    public void setDynoValue(int p) {
        this.dynoPosition = p;

    }

    // added for control change
    public synchronized void externalAction(Object obj, int actionNumber) {
        if (actionNumber == 0) {
            JSlider slider = (JSlider) obj;
            double filterCutoff = (double) (slider.getValue() * 100);
            for (int i = 0; i < inst.length; i++) {
                double[] values = {filterCutoff};
                inst[i].setController(values);
            }
        }
    }
}
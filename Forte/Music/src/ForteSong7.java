import jm.music.rt.RTLine;
import jm.audio.Instrument;
import jm.music.data.Note;
import javax.swing.*;
import jm.util.*;

import jm.JMC;

public class ForteSong7 extends RTLine implements JMC{
    private Note n = new Note(36, 0.5);
    private int dynoPosition = 50;

    //i added these. currently not using.
    //private int[] pitchArray = new int[] {C4,C4,C4,D4,E4,E4,D4,E4,F4,G4,C5,C5,C5,G4,G4,G4,E4,E4,E4,C4,C4,C4,G4,F4,E4,D4,C4};
    //private double[] rhythmArray = new double[] {QN,QN,QNT,ENT,QN,QNT,ENT,QNT,QT,HN,
    //      ENT,ENT,ENT,ENT,ENT,ENT,ENT,ENT,ENT,ENT,ENT,ENT,QNT,ENT,QNT,ENT,HN};
    private int[] melodyPitchArray = new int[] {A4, A4, A4, A4, REST, AF4, AF4, AF4, A4, AF4, FS4, FS4, FS4, FS4, REST,
            E4, E4, E4, E4, REST, E4, E4, E4, FS4, REST};
    private double[] melodyRhythmArray = new double[] {DOTTED_HALF_NOTE, DOTTED_HALF_NOTE, DOTTED_HALF_NOTE, QN, QN,
            DOTTED_HALF_NOTE, DOTTED_HALF_NOTE, DOTTED_HALF_NOTE, QN, QN, DOTTED_HALF_NOTE, DOTTED_HALF_NOTE,
            DOTTED_HALF_NOTE, QN, QN, DOTTED_HALF_NOTE, DOTTED_HALF_NOTE, DOTTED_HALF_NOTE, QN, QN, DOTTED_HALF_NOTE,
            DOTTED_HALF_NOTE, DOTTED_HALF_NOTE, QN, QN};


    int arrayIndex = 0;

    /**
     * Constructor
     */
    public ForteSong7(Instrument[] instArray) {
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

        n.setPitch(melodyPitchArray[arrayIndex]);
        n.setRhythmValue(melodyRhythmArray[arrayIndex]);
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
        //System.out.println("ArrayIndex is: " + arrayIndex);
        //n.setDynamic(dynoPosition);

        arrayIndex++;
        if(arrayIndex == melodyRhythmArray.length)
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
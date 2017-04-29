import jm.music.rt.RTLine;
import jm.audio.Instrument;
import jm.music.data.Note;
import javax.swing.*;
import jm.util.*;

import jm.JMC;

public class ForteSong5 extends RTLine implements JMC{
    private Note n = new Note(36, 0.5);
    private int dynoPosition = 50;

    private int[] melodyPitchArray = new int[] { G2, G2, REST, G2, REST, G2, REST, FS2, FS2, REST, FS2, REST, FS2, REST,  BF2, BF2, REST, BF2, REST, BF2, REST, C3, C3, REST, C3, REST, C3, REST,};
    private double[] melodyRhythmArray = new double[] {1, 1, 2.5, .5, 1.5, .5, 1  , 1, 1, 2.5, .5, 1.5, .5, 1, 1, 1, 2.5, .5, 1.5, .5, 1, 1, 1, 2.5, .5, 1.5, .5, 1};


    int arrayIndex = 0;

    /**
     * Constructor
     */
    public ForteSong5(Instrument[] instArray) {
        super(instArray);
    }

    /**
     * Generate the next note when requested.
     */
    public synchronized Note getNextNote() {

        n.setPitch(melodyPitchArray[arrayIndex]);
        n.setRhythmValue(melodyRhythmArray[arrayIndex]);
        n.setDynamic(dynoPosition);
        n.setDuration(n.getRhythmValue());


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
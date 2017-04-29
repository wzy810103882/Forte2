import jm.music.rt.RTLine;
import jm.audio.Instrument;
import jm.music.data.Note;
import javax.swing.*;
import jm.util.*;

import jm.JMC;

public class ForteSong4 extends RTLine implements JMC{
    private Note n = new Note(36, 0.5);
    private int dynoPosition = 50;

    private int[] melodyPitchArray = new int[] {REST, D4, BF3, G3, G3, FS3, REST, FS3, BF3, C4, C4, BF3, G3, BF3, REST,
            G3, G3, BF3, C4, REST, C4, G3, G3, G3, BF3, REST, C4, C4, G3, G3, REST};
    private double[] melodyRhythmArray = new double[] {1, 1.5, .5, .5, 3.5, 1, 3, 1, .5, 1, 1, .5, .5, 3.5, 3.5, .5, 1,
            .5, .5, .5, 1, .5, .5, .5, .5, .5, .5, 1, .5, .5, .5};


    int arrayIndex = 0;

    /**
     * Constructor
     */
    public ForteSong4(Instrument[] instArray) {
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
        //System.out.println("PitchArray.length = " + melodyPitchArray.length);
        //System.out.println("RhythmArray.length = " + melodyRhythmArray.length);

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

import jm.music.data.Phrase;
import jm.music.rt.RTLine;
import jm.audio.Instrument;
import jm.music.data.Note;
import javax.swing.*;

import jm.music.rt.RTPhrase;
import jm.util.*;

import jm.JMC;

public class BirdLine extends RTPhrase implements JMC{
    private final Phrase phrase = new Phrase("stuff", 0.0, JMC.BIRD);
    private Note n = new Note(36, 0.5);

    private int dynoPosition = 50;

    int arrayIndex = 0;

    private int[] birdPitchArray = new int[] {A4, FS4, G4, FS4, FS4, FS4, E4, FS4, E4, D4, D4, E4, REST};
    private double[] birdRhythmArray = new double[] {DOTTED_QUARTER_NOTE, DOTTED_QUARTER_NOTE, DOTTED_QUARTER_NOTE, DOTTED_QUARTER_NOTE,
            QN, QN, DOTTED_QUARTER_NOTE, DOTTED_QUARTER_NOTE, DOTTED_QUARTER_NOTE, DOTTED_QUARTER_NOTE,
            EN, EN, QN };


/*\
     * Constructor
     */

    public BirdLine(final Phrase phrase, Instrument[] instArray) {
        super(phrase, instArray);
        //this.phrase = phrase;
        this.phrase.setInstrument(JMC.BIRD);
    }


    /**
     * Generate the next note when requested.
     */

    public synchronized Note getNextNote() {
        n.setMyPhrase(phrase);
        n.setPitch(birdPitchArray[arrayIndex]);
        n.setRhythmValue(birdRhythmArray[arrayIndex]);
        n.setDynamic(dynoPosition);
        n.setDuration(n.getRhythmValue());

        arrayIndex++;
        if(arrayIndex == birdRhythmArray.length)
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
        if (actionNumber == 1) {
            JSlider slider = (JSlider) obj;
            double filterCutoff = (double) (slider.getValue() * 100);
            for (int i = 0; i < inst.length; i++) {
                double[] values = {filterCutoff};
                inst[i].setController(values);
            }
        }
    }
}

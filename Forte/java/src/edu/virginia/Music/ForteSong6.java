package edu.virginia.Music;

import jm.music.rt.RTLine;
import jm.audio.Instrument;
import jm.music.data.Note;
import javax.swing.*;
import jm.util.*;

import jm.JMC;

public class ForteSong6 extends RTLine implements JMC{
    private Note n = new Note(36, 0.5);
    private int dynoPosition = 40;

   //private int[] melodyPitchArray = new int[] {};
    //private double[] melodyRhythmArray = new double[] {1, 1, .5, .5, .5, .5, 4,         1, 1.5, .5, 1, 1, .5, .5, .5, .5, 1, 1, .5, .5, .5, .5, 1, 1, .5, .5, .5, .5, 1, 1, .5, .5, .5, .5, 1, 1, .5, .5, .5, .5, 1, 1, .5, .5, .5, .5};
    private int[] melodyPitchArray = new int[] {REST , REST, D5, REST, REST, D5,       REST, D5, REST,   REST , REST, D5, REST, REST, D5, REST, D5, REST, REST , REST, D5, REST, REST, D5, REST, D5, REST, E3, E3, REST, FS3, REST, FS3, REST, E3, E3, E3};
    private double[] melodyRhythmArray = new double[] {1, 1, .5, .5, .5, .5,      2, .5, 1.5,     1, 1, .5, .5, .5, .5,    2, .5, 1.5,       1, 1, .5, .5, .5, .5,    2, .5, 1.5,                                           1, 1, 2.5, .5, .5, .5, .5, .5, .5, .5};

    int arrayIndex = 0;


    public int[] getMelodyPitchArray() {
        return melodyPitchArray;
    }

    public void setMelodyPitchArray(int[] melodyPitchArray) {
        this.melodyPitchArray = melodyPitchArray;
    }

    public double[] getMelodyRhythmArray() {
        return melodyRhythmArray;
    }

    public void setMelodyRhythmArray(double[] melodyRhythmArray) {
        this.melodyRhythmArray = melodyRhythmArray;
    }



    private boolean[] muteArray = new boolean[melodyRhythmArray.length];



    public void setMuteArray(int index, boolean status){
        muteArray[index] = status;
    }
    public void clearMuteArray(){
        muteArray = new boolean[melodyRhythmArray.length];
    }
    public boolean[] getMuteArray()
    {
        return muteArray;
    }

    /**
     * Constructor
     */
    public ForteSong6(Instrument[] instArray) {
        super(instArray);
    }

    /**
     * Generate the next note when requested.
     */
    public synchronized Note getNextNote() {

        if(muteArray[arrayIndex])
        {
            n.setPitch(melodyPitchArray[arrayIndex]);
            n.setRhythmValue(melodyRhythmArray[arrayIndex]);
            n.setDynamic(dynoPosition);
            n.setDuration(n.getRhythmValue());

        }
        else
        {
            n = new Note(REST, melodyRhythmArray[arrayIndex]);

        }


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
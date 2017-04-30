package edu.virginia.Music;

import jm.music.rt.RTLine;
import jm.audio.Instrument;
import jm.music.data.Note;
import javax.swing.*;
import jm.util.*;

import jm.JMC;

public class ForteSong10 extends RTLine implements JMC{
    private Note n = new Note(36, 0.5);
    private int dynoPosition = 50;

    private int[] melodyPitchArray = new int[] {B3, CS4, FS3, B3, CS4, D4, CS4, B3, A3, E3, FS3, GS3, A3};
    private double[] melodyRhythmArray = new double[] {QN, 5, 5, 1, 8, 1, 1, 6, 5, 11, 6, 3, 2};


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
    public ForteSong10(Instrument[] instArray) {
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
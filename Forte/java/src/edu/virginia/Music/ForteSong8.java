package edu.virginia.Music;

import jm.music.rt.RTLine;
import jm.audio.Instrument;
import jm.music.data.Note;
import javax.swing.*;
import jm.util.*;

import jm.JMC;

public class ForteSong8 extends RTLine implements JMC{
    private Note n = new Note(36, 0.5);
    private int dynoPosition = 100;

    //i added these. currently not using.
    //private int[] pitchArray = new int[] {C4,C4,C4,D4,E4,E4,D4,E4,F4,G4,C5,C5,C5,G4,G4,G4,E4,E4,E4,C4,C4,C4,G4,F4,E4,D4,C4};
    //private double[] rhythmArray = new double[] {QN,QN,QNT,ENT,QN,QNT,ENT,QNT,QT,HN,
    //      ENT,ENT,ENT,ENT,ENT,ENT,ENT,ENT,ENT,ENT,ENT,ENT,QNT,ENT,QNT,ENT,HN};
    private int[] melodyPitchArray = new int[] {FS3, FS3, FS3, FS3, REST, E3, E3, E3, E3, REST, D3, D3, D3, D3, REST,
            E3, E3, E3, E3, REST, FS3, FS3, FS3, FS3, REST};
    private double[] melodyRhythmArray = new double[] {DOTTED_HALF_NOTE, DOTTED_HALF_NOTE, DOTTED_HALF_NOTE, QN, QN,
            DOTTED_HALF_NOTE, DOTTED_HALF_NOTE, DOTTED_HALF_NOTE, QN, QN, DOTTED_HALF_NOTE, DOTTED_HALF_NOTE,
            DOTTED_HALF_NOTE, QN, QN, DOTTED_HALF_NOTE, DOTTED_HALF_NOTE, DOTTED_HALF_NOTE, QN, QN, DOTTED_HALF_NOTE,
            DOTTED_HALF_NOTE, DOTTED_HALF_NOTE, QN, QN};


    int arrayIndex = 0;

    /**
     * Constructor
     */
    public ForteSong8(Instrument[] instArray) {
        super(instArray);
    }


    public int[] getMelodyPitchArray() {
        return melodyPitchArray;
    }
    public int[] getScore()
    {
        int count = 0;
        for (int i = 0; i < muteArray.length; i++)
        {
            if(muteArray[i])
            {
                count++;
            }
        }
        int[] ret = {count, muteArray.length};
        return ret;
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
     * Generate the next note when requested.
     */
    public synchronized Note getNextNote() {

        n.setPitch(melodyPitchArray[arrayIndex]);
        n.setRhythmValue(melodyRhythmArray[arrayIndex]);
        n.setDuration(n.getRhythmValue());
        if(muteArray[arrayIndex])
        {

            //n.setDynamic(dynoPosition);
            n.setDynamic(100);


        }
        else
        {
            n.setDynamic(20);
            //n = new Note(REST, melodyRhythmArray[arrayIndex]);

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
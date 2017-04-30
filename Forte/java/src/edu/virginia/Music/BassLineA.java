package edu.virginia.Music;//import jm.music.rt.RTLine;
//import jm.audio.Instrument;
//import jm.music.data.Note;
//import jm.*;
//
//public class BassLineA extends RTLine {
//    private Note n = new Note(36, 1.0);
//    private int pitch = 36;
//    private int[] intervals = {0, 0, 0, 4, 7, 10, 12};
//    private double panPosition = 0.5;
//
//    /** Constructor */
//    public BassLineA (Instrument[] instArray) {
//        super(instArray);
//    }
//    /**
//     * Generate the next note when requested.
//     */
//    public synchronized Note getNote() {
//        n.setPitch(pitch + intervals[(int)(Math.random() * intervals.length)]);
//        n.setDynamic((int)(Math.random()* 5 + 120));
//        n.setPan(panPosition);
//        n.setRhythmValue((int)(Math.random() * 2 + 1) * 0.25);
//        n.setDuration(n.getRhythmValue() * 0.9);
//        return n;
//    }
//}

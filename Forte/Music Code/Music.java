/**
 * Created by GannonCombs on 4/10/2017.
 */

import jm.music.data.Note;
import jm.music.data.Part;
import jm.music.data.Phrase;
import jm.music.data.Score;
import jm.music.tools.Mod;
import jm.util.Play;
import jm.JMC;
import jm.util.Write;


public class Music implements JMC{
    // declare instance variables
    Part flute, bowed_glass, clarinet;
    Phrase phrase1, phrase2, phrase3;
    Score score;
    int[] pitchArray;
    double[] rhythmArray;
    Note r, n, n2;

    public static void main(String[] args){

        //Note note = new Note();
        //note.setPitch(55); //higher number = higher pitch.
        //note.setPitch(JMC.C4); //this is G sharp in the 3rd octave.
        //note.setPitch(JMC.PP); // volume of note
       // note.setLength(3); //number is seconds
        //note.setDuration(1); // 1 = quarter note, 2 = half note, etc.
        //note.setDuration(JMC.EIGHTH_NOTE); //alternative

        //Play.midi(note);

        Music song = new Music();
        song.makeMusicData();
        song.structureData();
        song.playAndSave();
    }

    public Music() {
        //bowed_glass = new Part(bowed_glass, 0);
        bowed_glass = new Part(BOWED_GLASS, 1);
        //clarinet = new Part(CLARINET, 2);
        // create empty phrases
        phrase1 = new Phrase(0.0);
        //phrase2 = new Phrase(0.0);
        //phrase3 = new Phrase(0.0);
        //Create the data objects we want to use
        score = new Score("C song", 60);
        //Lets write the music in a convenient way.
        //Phrase phr2 = new Phrase(0.0);
        r = new Note(REST, QUARTER_NOTE);
        //phr2.addNote(r);
        n = new Note(C4, QUARTER_NOTE);
        n2 = new Note(G5, QUARTER_NOTE);
        //phr2.addNote(n2);
        //Note r2 = new Note(REST, QUARTER_NOTE);
        pitchArray = new int[] {D4};
        rhythmArray = new double[] {QN};
    }

    public void structureData() {
        //add phrases to the parts
        //bowed_glass.addPhrase(phrase1);
        //bowed_glass.addPhrase(phrase2);
        //clarinet.addPhrase(phrase3);
        bowed_glass.addPhrase(phrase1);

        //add parts to the score
        //score.addPart(bowed_glass);
        score.addPart(bowed_glass);
        //score.addPart(clarinet);
    }

    public void makeMusicData() {
        //add the notes to a phrase
        phrase1.addNote(n);
        phrase1.addNote(r);
        phrase1.addNote(n2);
        phrase1.addNote(r);
        //phrase1.addNoteList(pitchArray, rhythmArray);
        Mod.transpose(phrase1, 12);
    }

    private void playAndSave() {
        //Now we do a SMF write
        Write.midi(score, "C_song.wav");
        //Now play it back

        Play.midi(score);
        //Play.midi(score);

    }

}

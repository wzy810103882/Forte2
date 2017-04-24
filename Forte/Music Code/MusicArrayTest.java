import jm.JMC;
import jm.music.data.*;
import jm.util.*;
import jm.music.tools.*;
import jm.*;

public class MusicArrayTest implements JMC{
    // declare instance variables
    Part flute, trumpet, clarinet;
    Phrase phrase1, phrase2, phrase3;
    Score score;
    int[] pitchArray;
    double[] rhythmArray;

    public static void main(String[] args){
        MusicArrayTest row = new MusicArrayTest();
        row.makeMusicData();
        row.structureData();
        row.playAndSave();
    }

    // Constructor
    // Called when 'new MusicArrayTest' is executed
    public MusicArrayTest() {
        flute = new Part(FLUTE, 0);
        //trumpet = new Part(TRUMPET, 1);
        //clarinet = new Part(CLARINET, 2);
        // create empty phrases
        phrase1 = new Phrase(0.0);
        //phrase2 = new Phrase(0.0);
        //phrase3 = new Phrase(0.0);
        //Create the data objects we want to use
        score = new Score("Row Your Boat", 120);
        //Lets write the music in a convenient way.
        pitchArray = new int[] {C4,C4,C4,D4,E4,E4,D4,E4,F4,G4,C5,C5,C5,G4,G4,G4,E4,E4,E4,C4,C4,C4,G4,F4,E4,D4,C4};
        rhythmArray = new double[] {QN,QN,QNT,ENT,QN,QNT,ENT,QNT,QT,HN,
                ENT,ENT,ENT,ENT,ENT,ENT,ENT,ENT,ENT,ENT,ENT,ENT,QNT,
                ENT,QNT,ENT,HN};
    }

    public void structureData() {
        //add phrases to the parts
        flute.addPhrase(phrase1);
        //trumpet.addPhrase(phrase2);
        //clarinet.addPhrase(phrase3);

        //add parts to the score
        score.addPart(flute);
        //score.addPart(trumpet);
        //score.addPart(clarinet);
    }

    public void makeMusicData() {
        //add the notes to a phrase
        phrase1.addNoteList(pitchArray, rhythmArray);


    }

    private void playAndSave() {
        //Now we do a SMF write
        //Write.midi(score, "Rowboat.mid");

        long startTime = System.currentTimeMillis();
        long elapsedTime = 0L;
        int counter = 0;

        //Now play it back
        Play.midi(phrase1);
        //Play.midi(score);

        while (counter < rhythmArray.length) {

            elapsedTime = System.currentTimeMillis() - startTime;
            if(elapsedTime >= (rhythmArray[counter]*500))
            {
                System.out.print("La, ");
                //System.out.print(rhythmArray[counter]);
                startTime = System.currentTimeMillis();
                counter++;
            }
            if(counter > 4)
            {
                //phrase1.setMute(false);
            }
            //else
            //{
            //    phrase1.setMute(false);
            //}
        }
        phrase1.setMute(true);
        Play.midi(phrase1);

        //for(int i = 0; i < rhythmArray.length; i++)
        //{
        //  System.out.println("Note " + i + " is: " + rhythmArray[i]);
        //Note r = new Note(pitchArray[i], rhythmArray[i]);
        // Play.midi(r);
        //}


    }
}

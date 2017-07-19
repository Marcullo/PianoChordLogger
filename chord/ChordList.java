package chord;

import javax.swing.DefaultListModel;
import chord.Note.Pitch;

public class ChordList {
    public static final String NEXT_CHORD_NAME = "NEXT";
    public static final String PREVIOUS_CHORD_NAME = "PREV";
    
    public static final Chord C_MAJOR = new Chord(
            new Note(Pitch.C), 
            new Note(Pitch.E), 
            new Note(Pitch.G),
            "C");
    
    public static final Chord C_MINOR = new Chord(
            new Note(Pitch.C), 
            new Note(Pitch.DIS), 
            new Note(Pitch.G),
            "Cm");
    
    public static final Chord CIS_MAJOR = new Chord(
            new Note(Pitch.CIS), 
            new Note(Pitch.F), 
            new Note(Pitch.GIS),
            "C#");
    
    public static final Chord CIS_MINOR = new Chord(
            new Note(Pitch.CIS), 
            new Note(Pitch.E), 
            new Note(Pitch.GIS),
            "C#");
    
    public static final Chord D_MAJOR = new Chord(
            new Note(Pitch.D), 
            new Note(Pitch.FIS),
            new Note(Pitch.A),
            "D");
    
    public static final Chord D_MINOR = new Chord(
            new Note(Pitch.D), 
            new Note(Pitch.F),
            new Note(Pitch.A),
            "Dm");
    
    public static final Chord DIS_MAJOR = new Chord(
            new Note(Pitch.DIS), 
            new Note(Pitch.G),
            new Note(Pitch.AIS),
            "D#");
    
    public static final Chord DIS_MINOR = new Chord(
            new Note(Pitch.DIS), 
            new Note(Pitch.GIS),
            new Note(Pitch.AIS),
            "D#m");
    
    public static final Chord E_MAJOR = new Chord(
            new Note(Pitch.E), 
            new Note(Pitch.GIS),
            new Note(Pitch.H),
            "E");
    
    public static final Chord E_MINOR = new Chord(
            new Note(Pitch.E), 
            new Note(Pitch.G),
            new Note(Pitch.H),
            "Em");
    
    public static final Chord F_MAJOR = new Chord(
            new Note(Pitch.F), 
            new Note(Pitch.A),
            new Note(Pitch.C),
            "F");
    
    public static final Chord F_MINOR = new Chord(
            new Note(Pitch.F), 
            new Note(Pitch.GIS),
            new Note(Pitch.C),
            "Fm");
    
    public static final Chord FIS_MAJOR = new Chord(
            new Note(Pitch.FIS), 
            new Note(Pitch.AIS),
            new Note(Pitch.CIS),
            "F#");
    
    public static final Chord FIS_MINOR = new Chord(
            new Note(Pitch.FIS), 
            new Note(Pitch.A),
            new Note(Pitch.CIS),
            "F#m");
    
    public static final Chord G_MAJOR = new Chord(
            new Note(Pitch.G), 
            new Note(Pitch.H),
            new Note(Pitch.D),
            "G");
    
    public static final Chord G_MINOR = new Chord(
            new Note(Pitch.G), 
            new Note(Pitch.AIS),
            new Note(Pitch.D),
            "Gm");
    
    public static final Chord GIS_MAJOR = new Chord(
            new Note(Pitch.GIS), 
            new Note(Pitch.C),
            new Note(Pitch.DIS),
            "G#");
    
    public static final Chord GIS_MINOR = new Chord(
            new Note(Pitch.GIS), 
            new Note(Pitch.H),
            new Note(Pitch.DIS),
            "G#m");
    
    public static final Chord A_MAJOR = new Chord(
            new Note(Pitch.A), 
            new Note(Pitch.CIS),
            new Note(Pitch.E),
            "A");
    
    public static final Chord A_MINOR = new Chord(
            new Note(Pitch.A), 
            new Note(Pitch.C),
            new Note(Pitch.E),
            "Am");
    
    public static final Chord B_MAJOR = new Chord(
            new Note(Pitch.AIS), 
            new Note(Pitch.D),
            new Note(Pitch.F),
            "B");
    
    public static final Chord B_MINOR = new Chord(
            new Note(Pitch.AIS), 
            new Note(Pitch.CIS),
            new Note(Pitch.F),
            "Bm");
    
    public static final Chord H_MAJOR = new Chord(
            new Note(Pitch.H), 
            new Note(Pitch.DIS),
            new Note(Pitch.FIS),
            "H");
    
    public static final Chord H_MINOR = new Chord(
            new Note(Pitch.H), 
            new Note(Pitch.D),
            new Note(Pitch.FIS),
            "Hm");
    
     public static final Chord NEXT_CHORD = new Chord(
            new Note(Pitch.C), 
            new Note(Pitch.CIS),
            new Note(Pitch.D),
            NEXT_CHORD_NAME);
     
     public static final Chord PREVIOUS_CHORD = new Chord(
            new Note(Pitch.DIS), 
            new Note(Pitch.E),
            new Note(Pitch.F),
            PREVIOUS_CHORD_NAME);
     
    private DefaultListModel<Chord> chordList;
    
    public ChordList() {
        chordList = new DefaultListModel<>();
        chordList.addElement(C_MAJOR);
        chordList.addElement(CIS_MAJOR);
        chordList.addElement(D_MAJOR);
        chordList.addElement(DIS_MAJOR);
        chordList.addElement(E_MAJOR);
        chordList.addElement(F_MAJOR);
        chordList.addElement(FIS_MAJOR);
        chordList.addElement(G_MAJOR);
        chordList.addElement(GIS_MAJOR);
        chordList.addElement(A_MAJOR);
        chordList.addElement(B_MAJOR);
        chordList.addElement(H_MAJOR);
        
        chordList.addElement(C_MINOR);
        chordList.addElement(CIS_MINOR);
        chordList.addElement(D_MINOR);
        chordList.addElement(DIS_MINOR);
        chordList.addElement(E_MINOR);
        chordList.addElement(F_MINOR);
        chordList.addElement(FIS_MINOR);
        chordList.addElement(G_MINOR);
        chordList.addElement(GIS_MINOR);
        chordList.addElement(A_MINOR);
        chordList.addElement(B_MINOR);
        chordList.addElement(H_MINOR);
        
        chordList.addElement(NEXT_CHORD);
        chordList.addElement(PREVIOUS_CHORD);
    }
    
    public int getNumberOfChords() {
        return chordList.getSize();
    }
    
    public Chord getChord(int i) {
        return chordList.get(i);
    }
}

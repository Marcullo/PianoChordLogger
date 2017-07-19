package chord;

public class Note {
    public enum Pitch {
        PREVIOUS_CHORD_NOTE,
        C,
        CIS,
        D,
        DIS,
        E,
        F,
        FIS,
        G,
        GIS,
        A,
        AIS,
        H,
        NEXT_CHORD_NOTE
    }
    
    public enum Octave {
        SUB_CONTRA,
        CONTRA,
        GREAT,
        SMALL,
        ONE_LINE,
        TWO_LINE
    }

    private Pitch pitch;
    private Octave octave;
    
    public Note(Note n) {
        this(n.pitch, n.octave);
    }
    
    public Note(Pitch pitch, Octave octave) {
        this.pitch = pitch;
        this.octave = octave;
    }
    
    public Note(Pitch pitch) {
        this.pitch = pitch;
    }
    
    public String getName() {
        return pitch.toString();
    }
    
    public Pitch getPitch() {
        return pitch;
    }
    
    public Octave getOctave() {
        return octave;
    }
    
}

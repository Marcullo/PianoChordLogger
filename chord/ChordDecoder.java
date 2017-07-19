package chord;

import javax.swing.DefaultListModel;

public class ChordDecoder {
    private ChordList chordList;
    private Chord lastChord;
    private int lastChordIndex;
    private boolean chordSpecialForPrevious;
    private boolean chordSpecialForNext;
    
    public ChordDecoder() {
        chordList = new ChordList();
        lastChord = new Chord();
        lastChordIndex = -1;
        chordSpecialForPrevious = false;
        chordSpecialForNext = false;
    }
    
    public boolean isChordFound() {
        return lastChordIndex > -1;
    }
    
    public boolean isChordSpecial() {
        return chordSpecialForPrevious || chordSpecialForNext;
    }
    
    public boolean isChordSpecialForNext() {
        return chordSpecialForNext;
    }
    
    public boolean isChordSpecialForPrevious() {
        return chordSpecialForPrevious;
    }
    
    public Chord getLastChord() {
        return lastChord;
    }
    
    public String getNameOfLastChord() {
        return lastChord.getName();
    }
    
    public void findChord(DefaultListModel<Note> notes) {
        this.flush();
        
        addToLastChord(notes);
        
        int lastchordNotesNumber = lastChord.getNumberOfNotes();
        int validChordsNumber = chordList.getNumberOfChords();
        
        for (int chordCnt = 0; chordCnt < validChordsNumber; chordCnt++) {
            Chord validChord = chordList.getChord(chordCnt);
            int validChordNotesNumber = validChord.getNumberOfNotes();
            
            if (validChordNotesNumber == lastchordNotesNumber) {
                if (areChordsEqual(lastChord, validChord)) {
                    lastChordIndex = chordCnt;
                    lastChord.setName(validChord.getName());
                    if (ChordList.NEXT_CHORD_NAME.equals(lastChord.getName())) {
                        chordSpecialForNext = true;
                    } else if (ChordList.PREVIOUS_CHORD_NAME.equals(lastChord.getName())) {
                        chordSpecialForPrevious = true;
                    }
                    return;
                }
            }
        }
        lastChordIndex = -1;
    }
    
    private void flush() {
        lastChordIndex = -1;
        lastChord = null;
        lastChord = new Chord();
        chordSpecialForNext = false;
        chordSpecialForPrevious = false;
    }
    
    private void addToLastChord(DefaultListModel<Note> notesToCheck) {
        for (int i = 0; i < notesToCheck.getSize(); i++) {
            lastChord.addNote(notesToCheck.get(i));
        }
    }
    
    private boolean areChordsEqual(Chord ch1, Chord ch2) {
        int ch1NotesNumber = ch1.getNumberOfNotes();
        
        for (int noteCnt = 0; noteCnt < ch1NotesNumber; noteCnt++) {
            Note chordNote = lastChord.getNote(noteCnt);
            if (!ch2.contains(chordNote)) {
                return false;
            }
        }
        
        return true;
    }
}

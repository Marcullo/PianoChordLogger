package chord;

import javax.swing.DefaultListModel;

public class Chord {
    private static final int MAXIMAL_NOTES_NUMBER = 3;
    
    private DefaultListModel<Note> noteCombination;
    private int notesNumber;
    private String name;
    
    public Chord() {
        noteCombination = new DefaultListModel<>();
        name = "";
    }
    
    public Chord(Note note1, Note note2, Note note3, Note note4, String name) {
        noteCombination = new DefaultListModel<>();
        noteCombination.addElement(note1);
        noteCombination.addElement(note2);
        noteCombination.addElement(note3);
        noteCombination.addElement(note4);
        notesNumber = noteCombination.getSize();
        this.name = name;
    }
    
    public Chord(Note note1, Note note2, Note note3, Note note4) {
        noteCombination = new DefaultListModel<>();
        noteCombination.addElement(note1);
        noteCombination.addElement(note2);
        noteCombination.addElement(note3);
        noteCombination.addElement(note4);
        notesNumber = noteCombination.getSize();
    }
    
    public Chord(Note note1, Note note2, Note note3, String name) {
        noteCombination = new DefaultListModel<>();
        noteCombination.addElement(note1);
        noteCombination.addElement(note2);
        noteCombination.addElement(note3);
        notesNumber = noteCombination.getSize();
        this.name = name;
    }
    
    public Chord(Note note1, Note note2, Note note3) {
        noteCombination = new DefaultListModel<>();
        noteCombination.addElement(note1);
        noteCombination.addElement(note2);
        noteCombination.addElement(note3);
        notesNumber = noteCombination.getSize();
    }
    
    public boolean contains(Note note) {
        Note.Pitch pitch = note.getPitch();
        for (int i = 0; i < notesNumber; i++) {
            Note.Pitch chordPitch = noteCombination.get(i).getPitch();
            
            if (pitch == chordPitch) {
                return true;
            }
        }
        return false;        
    }
    
    public int getNumberOfNotes() {
        return notesNumber;
    }
    
    public Note getNote(int i) {
        return noteCombination.get(i);
    }
    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public void addNote(Note note) {
        if (notesNumber < MAXIMAL_NOTES_NUMBER) {
            notesNumber++;
            noteCombination.addElement(note);
        }
    }
}

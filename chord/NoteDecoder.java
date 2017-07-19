package chord;

import java.util.LinkedList;
import javax.swing.DefaultListModel;

public class NoteDecoder {
    public static final int THRESHOLD_MIN = 100;
    public static final int THRESHOLD_MAX = 1600;
    public static final int AVERAGING_NUMBER = 8;
    public static final int DIFFERENT_NOTES_RECOGNITION_NUMBER = 3;
    
    private final int bufferLength;
    
    private NoteList noteList;
    private DefaultListModel<Note> matchingNotes;
    private DefaultListModel<Integer> matchingNotesValues;
    private LinkedList<int[]> averagingBuffer;
    private int[] averagedBuffer;
    
    public NoteDecoder() {
        noteList = new NoteList();
        bufferLength = noteList.getNumberOfNotes();
        matchingNotes = new DefaultListModel<>();
        matchingNotesValues = new DefaultListModel<>();
        
        averagingBuffer = new LinkedList<>();
        for (int i = 0; i < AVERAGING_NUMBER; i++) {
            averagingBuffer.add(new int[bufferLength]);
        }
        averagedBuffer = new int[bufferLength];
    }
    
    public boolean isAnyNoteFound() {
        return matchingNotes.getSize() > 0;
    }
    
    public DefaultListModel<Note> getMatchings() {
        return matchingNotes;
    }
    
    public DefaultListModel<Integer> getMatchingsValues() {
        return matchingNotesValues;
    }
    
    public void matchNotes(int signalComponents[]) {
        flush();
        shiftArrayIntoBuffer(signalComponents);
        computeAveragedBuffer();
        computeMatchingNotes();
    }
    
    private void flush() {
        if (matchingNotes.getSize() >= DIFFERENT_NOTES_RECOGNITION_NUMBER) {
            matchingNotes.removeAllElements();
            matchingNotesValues.removeAllElements();
        }
        
        for (int i = 0; i < averagedBuffer.length; i++) {
            averagedBuffer[i] = 0;
        }
    }
    
    private void shiftArrayIntoBuffer(int values[]) {
        averagingBuffer.add(values);
        averagingBuffer.removeFirst();
    }
    
    private void computeAveragedBuffer() {        
        for (int i = 0; i < AVERAGING_NUMBER; i++) {
            int samples[] = averagingBuffer.get(i);
            int length = samples.length;
            for (int j = 0; j < length; j++) {
                averagedBuffer[j] += samples[j];
            }
        }
        
        for (int i = 0; i < bufferLength; i++) {
            averagedBuffer[i] /= AVERAGING_NUMBER;
        }
    }
    
    private void computeMatchingNotes() {
        int length = averagedBuffer.length;
        if (length > 0) {
            for (int i = 0; i < length; i++) {
                int value = averagedBuffer[i];
                if (value > THRESHOLD_MIN && value < THRESHOLD_MAX) {
                    Note note = new Note(noteList.getNote(i));
                    
                    Note.Pitch pitch = note.getPitch();
                    int matchingNotesSize = matchingNotes.getSize();
                    
                    if (matchingNotesSize == 0) {
                        matchingNotes.addElement(note);
                        matchingNotesValues.addElement(value);
                    } else if (matchingNotesSize < DIFFERENT_NOTES_RECOGNITION_NUMBER) {
                        for (int j = 0; j < matchingNotesSize; j++) {
                            Note matchingNote = matchingNotes.get(j);
                            if (matchingNote.getPitch() == pitch) {
                                break;
                            }
                            if (j == matchingNotesSize - 1) {
                                matchingNotes.addElement(note);
                                matchingNotesValues.addElement(value);
                            }
                        }
                    }
                }
            }
        }
    }
}

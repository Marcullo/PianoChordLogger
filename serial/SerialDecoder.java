package serial;

public class SerialDecoder {
    public final String MESSAGE_SEPARATOR = "s";
    public final String ELEMENTS_SEPARATOR = " ";
    public final int NUMBER_OF_ELEMENTS_IN_ONE_MESSAGE = 256;
    
    private int lastFullMessage[];
    private int numOfElements;
    private String incompleteMessage;
    
    public SerialDecoder() {
        lastFullMessage = new int[NUMBER_OF_ELEMENTS_IN_ONE_MESSAGE];
        incompleteMessage = "";
        numOfElements = 0;
    }
    
    public boolean isLastFullMessageReady() {
        return lastFullMessage.length == numOfElements;
    }
    
    public int[] getElementsOfLastFullMessage() {
        return lastFullMessage;
    }
    
    public void clearLastFullMessage() {
        lastFullMessage = null;
        lastFullMessage = new int[NUMBER_OF_ELEMENTS_IN_ONE_MESSAGE];
    }
    
    public void updateMessageFrame(String msg) {
        String trimmedMsg = trimSpaces(msg);
        String[] messageElements = splitStringBySpace(trimmedMsg);
        int numberOfElements = messageElements.length;        
        
        int separatorIndex = getSeparatorIndex(messageElements);
        if (separatorIndex == -1) {
            addElementsToIncompleteMessage(messageElements, 0, numberOfElements - 1);
       } else {
            if (separatorIndex > 0) {
                addElementsToIncompleteMessage(messageElements, 0, separatorIndex - 1);
            }
            
            clearIncompleteMessage();
            
            if (separatorIndex < numberOfElements - 1){ 
                addElementsToIncompleteMessage(messageElements, separatorIndex + 1, numberOfElements - 1);
            }
            
            updateLastFullMessage(incompleteMessage);
        }
    }
    
    private String trimSpaces(String s) {
        return s.replaceAll("^\\s+|\\s+$", "");
    }
    
    private String[] splitStringBySpace(String s, int i) {
        return s.split("\\s+", i);
    }
    
    private String[] splitStringBySpace(String s) {
        return s.split("\\s+");
    }
    
    private int getSeparatorIndex(String[] messageElements) {
        int separatorIndex = -1;
        for (int i = 0; i < messageElements.length; i++) {
            if (messageElements[i].contains(MESSAGE_SEPARATOR)) {
                separatorIndex = i;
            }
        }
        return separatorIndex;
    }
    
    public void addElementsToIncompleteMessage(String elements[], int indexRangeBegin, int indexRangeEnd) {
        for (int i = indexRangeBegin; i <= indexRangeEnd; i++) {
            incompleteMessage += elements[i] + ELEMENTS_SEPARATOR;
        }
    }
    
    private void updateLastFullMessage(String msg) {
        String[] messageElements = splitStringBySpace(trimSpaces(msg), NUMBER_OF_ELEMENTS_IN_ONE_MESSAGE);
        int messageElementsLength = messageElements.length; 
        numOfElements = messageElementsLength;
        
        lastFullMessage = null;
        lastFullMessage = new int[NUMBER_OF_ELEMENTS_IN_ONE_MESSAGE];
        for (int i = 0; i < messageElementsLength; i++) {
            try{
                lastFullMessage[i] = Integer.valueOf(messageElements[i]);
            }
            catch (NumberFormatException ex) {
            }
        }
    }
    
    private void clearIncompleteMessage() {
        incompleteMessage = "";
    }
}
